package automationFramework;

import org.junit.runners.MethodSorters;
import org.junit.Assert;
import org.junit.FixMethodOrder;
import org.junit.Ignore;
import org.junit.Test;

/** Regression test set for Computer database application*/	
@FixMethodOrder(MethodSorters.NAME_ASCENDING)

public class pcDatabaseTestSet extends testSetup {
			
	@Test
	public void A_addPcCancel() throws Exception {
		System.out.println("***Starting test case 1: Add new computer - cancel***");
		System.out.println("Adding new PC...");
		clickAddNewPc();
		setTextValue("//*[@id ='name']", utils.createNrString(0) + "Atest");
		setTextValue("//*[@id ='introduced']", utils.createyyyMMddValue(-100));
		setTextValue("//*[@id ='discontinued']", utils.createyyyMMddValue(-10));
		dropdownSelect(9);
		Thread.sleep(1000);	
		String storeCancelNameFilter = getTextValue("//*[@id='name']"); //store name for filter test ahead
		System.out.println("Cancelling new computer creation...");
		clickCancelSave();
		System.out.println("Computer creation has been cancelled");

		Assert.assertTrue(isAlertMissing("//*[@class='alert-message warning']"));
		System.out.println("No notification displayed - OK");
		
		System.out.println("Using filter to make sure entry was not saved");
		setTextValue("//*[@id ='searchbox']", storeCancelNameFilter);
		clickFilter();
		Thread.sleep(1000);
		Assert.assertTrue(isObjectPresent("//*[@class='well']", "Nothing to display"));
		System.out.println("Nothing found - OK");
		
		System.out.println("Test case 1 has successfully passed");
	}
		
	@Test
	public void B_addPcMandatoryFields() throws Exception {
		System.out.println("***Starting test case 2: Add new computer - only mandatory fields***");
		System.out.println("Adding new PC...");
		clickAddNewPc();
		
		System.out.println("Validation test: trying to save without filling mandatory Name field...");
		clickSavePc(); 
		
		Assert.assertTrue(isValueIncorrect("(//*[contains(@class,'clearfix')])[1]"));
		System.out.println("Validation test passed: field has been highlighted red");
		
		setTextValue("//*[@id ='name']", utils.createNrString(0) + "Atest");
	
		String storeSavedNameFilter = getTextValue("//*[@id='name']");
		System.out.println("Saved value:" + storeSavedNameFilter);
		
		System.out.println("Saving new computer...");
		clickSavePc();
		System.out.println("New computer has been saved");
		
		Assert.assertTrue(isObjectPresent("//*[@class='alert-message warning']", "Done!"));
		System.out.println("Notification is displayed - OK");
		
		System.out.println("Using filter to make sure entry was saved");
		setTextValue("//*[@id ='searchbox']", storeSavedNameFilter);
		clickFilter();
		Thread.sleep(1000);
		Assert.assertTrue(isObjectPresent("//table[@class = 'computers zebra-striped']/tbody/tr[1]/td[1]/a", "Atest"));
		System.out.println("Saved computer found - OK");
		System.out.println("Test case 2 has successfully passed");
	}

	@Test
	public void C_addPcAllFields() throws Exception {
		System.out.println("***Starting test case 3: Add new computer - all fields***");
		System.out.println("Adding new PC...");
		clickAddNewPc();
		
		setTextValue("//*[@id ='name']", utils.createNrString(0) + "Atest");
		
		setTextValue("//*[@id ='introduced']", "12-03-2012"); //wrong format
		setTextValue("//*[@id ='discontinued']", "2015-02-29"); //wrong date
		dropdownSelect(3);
		
		System.out.println("Validation test: trying to save computer using incorrect dates...");
		clickSavePc();
		
		Assert.assertTrue(isValueIncorrect("(//*[contains(@class,'clearfix')])[2]"));
		Assert.assertTrue(isValueIncorrect("(//*[contains(@class,'clearfix')])[3]"));
			
		System.out.println("Validation test passed: fields have been highlighted red. Using right formats now...");
		
		setTextValue("//*[@id ='introduced']", utils.createyyyMMddValue(-105));
		setTextValue("//*[@id ='discontinued']", utils.createyyyMMddValue(-12));
		
		System.out.println("Storing data and saving new computer...");
		String storeSavedNameFilter2 = getTextValue("//*[@id='name']");
		clickSavePc();
		System.out.println("New computer has been saved");

		Assert.assertTrue(isObjectPresent("//*[@class='alert-message warning']", "Done!"));
		System.out.println("Notification is displayed - OK");
		
		System.out.println("Using filter to make sure entry was saved");
		setTextValue("//*[@id ='searchbox']", storeSavedNameFilter2);
		clickFilter();
		Thread.sleep(1000);
		Assert.assertTrue(isObjectPresent("//table[@class = 'computers zebra-striped']/tbody/tr[1]/td[1]/a", "Atest"));
		System.out.println("Saved computer found - OK");
		System.out.println("Test case 3 has successfully passed");
		
	}
	
	@Test
	public void D_editPcCancel() throws Exception {
		
		System.out.println("***Starting test case 4: edit computer data - cancel***");
		System.out.println("Opening computer's data for editing...");
		openPC();
		System.out.println("Storing original data...");
		String storeCancelNameOld = getTextValue("//*[@id='name']");
		String storeCancelIntroOld = getTextValue("//*[@id='introduced']");
		String storeCancelDiscOld = getTextValue("//*[@id='discontinued']");
		String storeCancelCompanyOld = getTextValue("//*[@id='company']");
		System.out.println("Updating data...");
		setTextValue("//*[@id ='name']", utils.createNrString(0) + "Atest" + "edited");
		setTextValue("//*[@id ='introduced']", utils.createyyyMMddValue(-200));
		setTextValue("//*[@id ='discontinued']", utils.createyyyMMddValue(-20));
		dropdownSelect(11);
		System.out.println("Storing updated data...");
		String storeCancelNameNew = getTextValue("//*[@id='name']");
		String storeCancelIntroNew = getTextValue("//*[@id='introduced']");
		String storeCancelDiscNew = getTextValue("//*[@id='discontinued']");
		String storeCancelCompanyNew = getTextValue("//*[@id='company']");
		Thread.sleep(1000);	
				
		clickCancelSave();
		System.out.println("Editing has been cancelled");
		
		Assert.assertTrue(isAlertMissing("//*[@class='alert-message warning']"));
		System.out.println("Notification is not displayed - OK");
		
		System.out.println("Using the updated computer name to find the computer");
		setTextValue("//*[@id ='searchbox']", storeCancelNameNew);
		clickFilter();
		Thread.sleep(1000);
		Assert.assertTrue(isObjectPresent("//*[@class='well']", "Nothing to display"));
		System.out.println("Nothing found - OK");
		
		System.out.println("Using the original name to find the computer");
		setTextValue("//*[@id ='searchbox']", storeCancelNameOld);
		clickFilter();
		Thread.sleep(1000);
		
		System.out.println("Opening computer data again to compare the values...");
		openPC();
		Assert.assertEquals(storeCancelIntroOld, getTextValue("//*[@id='introduced']"));
		Assert.assertEquals(storeCancelDiscOld, getTextValue("//*[@id='discontinued']"));
		Assert.assertEquals(storeCancelCompanyOld, getTextValue("//*[@id='company']"));
		
		Assert.assertFalse(getTextValue("//*[@id='introduced']").equals(storeCancelIntroNew));
		Assert.assertFalse(getTextValue("//*[@id='discontinued']").equals(storeCancelDiscNew));
		Assert.assertFalse(getTextValue("//*[@id='company']").equals(storeCancelCompanyNew));
		
		System.out.println("Values are not updated. Going back to table view...");
		clickHome();
				
		System.out.println("Test case 4 has successfully passed");
	}
	
	@Test
	public void E_editPcSave() throws Exception {
		
		System.out.println("***Starting test case 5: edit computer data - save***");
		System.out.println("Opening computer's data for editing...");
		openPC();
		System.out.println("Storing original data...");
		String storeEditNameOld = getTextValue("//*[@id='name']");
		String storeEditIntroOld = getTextValue("//*[@id='introduced']");
		String storeEditDiscOld = getTextValue("//*[@id='discontinued']");
		String storeEditCompanyOld = getTextValue("//*[@id='company']");
		System.out.println(storeEditCompanyOld);
		
		System.out.println("Updating data...");
		setTextValue("//*[@id ='name']", utils.createNrString(0) + "Atest" + "edited");
		setTextValue("//*[@id ='introduced']", utils.createyyyMMddValue(-300));
		setTextValue("//*[@id ='discontinued']", utils.createyyyMMddValue(-30));
		dropdownSelect(12);
		System.out.println("Storing updated data...");
		String storeEditNameNew = getTextValue("//*[@id='name']");
		String storeEditIntroNew = getTextValue("//*[@id='introduced']");
		String storeEditDiscNew = getTextValue("//*[@id='discontinued']");
		String storeEditCompanyNew = getTextValue("//*[@id='company']");
		System.out.println(storeEditCompanyNew);
		Thread.sleep(1000);	
				
		clickSavePc();
		System.out.println("Computer data has been saved");
		
		Assert.assertTrue(isObjectPresent("//*[@class='alert-message warning']", "Done!"));
		System.out.println("Notification is displayed - OK");
		
		System.out.println("Using the original computer name to find the computer");
		setTextValue("//*[@id ='searchbox']", storeEditNameOld);
		clickFilter();
		Thread.sleep(1000);
		Assert.assertTrue(isObjectPresent("//*[@class='well']", "Nothing to display"));
		System.out.println("Nothing found - OK");
		
		System.out.println("Using the original name to find the computer");
		setTextValue("//*[@id ='searchbox']", storeEditNameNew);
		clickFilter();
		Thread.sleep(1000);
		System.out.println("Opening computer data again to compare the values...");
		
		openPC();
		Assert.assertEquals(storeEditIntroNew, getTextValue("//*[@id='introduced']"));
		Assert.assertEquals(storeEditDiscNew, getTextValue("//*[@id='discontinued']"));
		Assert.assertEquals(storeEditCompanyNew, getTextValue("//*[@id='company']"));
		Assert.assertFalse(getTextValue("//*[@id='introduced']").equals(storeEditIntroOld));
		Assert.assertFalse(getTextValue("//*[@id='discontinued']").equals(storeEditDiscOld));
		Assert.assertFalse(getTextValue("//*[@id='company']").equals(storeEditCompanyOld));
		
		System.out.println("Values are updated. Going back to table view...");
		clickHome();
		Thread.sleep(1000);		
		System.out.println("Test case 5 has successfully passed");
	}
	
		@Test 
	public void F_paginatorNextPrevious() throws Exception {
		System.out.println("***Starting test case 7: paginator's Next and Previous***");
		Assert.assertTrue(isThingDisabled("//*[@class='prev disabled']"));
		System.out.println("button 'Previous' is disabled");
		clickNext();
		System.out.println("going to the next page..");
		Assert.assertTrue(isThingEnabled("//*[@class='prev']"));
		Thread.sleep(1000);
		clickPrev();
		System.out.println("going back to the first page..");
		Assert.assertTrue(isThingDisabled("//*[@class='prev disabled']"));
		System.out.println("button 'Previous' is disabled again");
		System.out.println("Test case 7 has successfully passed");
		}
	
		@Test
		public void G_deletePc() throws Exception {
			System.out.println("***Starting test case 6: delete computer***");
			System.out.println("Opening computer's data for editing...");
			Thread.sleep(1000);
			openPC();
			String storeDeletedName = getTextValue("//*[@id='name']");
			System.out.println("Deleting computer...");
			clickDeletePc();
			System.out.println("Computer has been deleted");
			Assert.assertTrue(isObjectPresent("//*[@class='alert-message warning']", "Done!"));
			System.out.println("Notification is displayed - OK");
			System.out.println("Using filter to make sure computer has been deleted from the database...");
			setTextValue("//*[@id ='searchbox']", storeDeletedName);
			clickFilter();
			Thread.sleep(1000);
			Assert.assertTrue(isObjectPresent("//*[@class='well']", "Nothing to display"));
			System.out.println("Nothing found - OK");
			
			System.out.println("Test case 6 has successfully passed");
		}	

}