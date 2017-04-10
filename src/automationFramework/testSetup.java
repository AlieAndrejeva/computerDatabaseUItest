package automationFramework;

import org.junit.AfterClass;
import org.junit.BeforeClass;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.support.ui.Select;

/** contains different methods used in pc database test set, including button clicks, test's before and after, checks if 
 * element available, disabled etc */	

public class testSetup {
	static String driverPath = "D:/";    /** path of MY chromedriver.exe */	
	public static WebDriver driver;
	
	@BeforeClass
	public static void setUp() {
	
		System.out.println("launching chrome browser");
		System.setProperty("webdriver.chrome.driver", driverPath+"chromedriver.exe");
		driver = new ChromeDriver();
		driver.manage().window().maximize();
		driver.navigate().to("http://computer-database.herokuapp.com/computers");
		String strPageTitle = driver.getTitle();
		System.out.println("Page title: - "+strPageTitle);
								}
	@AfterClass
	public static void tearDown() {
		if(driver!=null) {
			System.out.println("Closing chrome browser");
			driver.quit();
		}
	}
	/** button clicks */	
	public void clickCancelSave() {
		WebElement cancelPc = driver.findElement(By.xpath("//*[@class='btn']"));
		cancelPc.click();
	}
	public void clickAddNewPc() {
		WebElement addPc = driver.findElement(By.xpath("//*[@id ='add']"));
		addPc.click();
	}
	public void clickSavePc() {
		WebElement savePc = driver.findElement(By.xpath("//*[@class='btn primary']"));
		savePc.click();
	}
	public void clickDeletePc() {
		WebElement deletePc = driver.findElement(By.xpath("//*[@class='btn danger']"));
		deletePc.click();
	}

	public void clickFilter() {
		WebElement filterPc = driver.findElement(By.xpath("//*[@id ='searchsubmit']"));
		filterPc.click();
	}
	
	public void clickHome() {
		WebElement homeLink = driver.findElement(By.xpath("//*[@class='fill']/a"));
		homeLink.click();
	}
	
	public void clickNext() {
		WebElement pagiNext = driver.findElement(By.xpath("//*[@class='next']/a"));
		pagiNext.click();
	}
	
	public void clickPrev() {
		WebElement pagiPrev = driver.findElement(By.xpath("//*[@class='prev']/a"));
		pagiPrev.click();
	}
	
	/** universal method to set text value into text field when found by xpath */	
	public testSetup setTextValue(String xpath, String value) {
		WebElement element = driver.findElement(By.xpath(xpath));
		element.clear();
		element.sendKeys(value);
		return this;
		}


	
	/** checks if notification is displayed - Done! computer X has been created/updated/deleted */

	public boolean isObjectPresent(String xpath, String key) {
		String alerttext;
		WebElement elem = driver.findElement(By.xpath(xpath));
		if (elem == null)
			return false;
		alerttext = elem.getText();
		if (alerttext.contains(key))
			return true;
		return false;

	}
	
	/** checks object is missing - notification alert after cancelling the operation*/
	public boolean isAlertMissing(String xpath) {
		if (!hasAttribute(xpath, "class"))
			return true;
		String state = driver.findElement(By.xpath(xpath)).getAttribute("class");
		if (state == null)
			return true;
		if (state.isEmpty())
			return true;
		return false;
	}
	
	public boolean isValueIncorrect(String xpath) {
		String fieldName = driver.findElement(By.xpath(xpath)).getAttribute("class");
		if (fieldName.contains("error"))
			return true;
		return false;
	}
	
	public boolean isThingDisabled(String xpath) {
		String fieldName = driver.findElement(By.xpath(xpath)).getAttribute("class");
		if (fieldName.contains("disabled"))
			return true;
		return false;
	}
	public boolean isThingEnabled(String xpath) {
		String fieldName = driver.findElement(By.xpath(xpath)).getAttribute("class");
		if (fieldName.contains("disabled"))
			return false;
		return true;
	}
	
	/** dropdown selection - company field*/
	public testSetup dropdownSelect(int i) {
		Select dropdown = new Select(driver.findElement(By.id("company")));
		dropdown.selectByIndex(i);
		return this;
	}
	
	/** clicks on the first object in the table - 1st row and 1st column*/
public void openPC() {
	WebElement firstrow = driver.findElement(By.xpath("//table[@class = 'computers zebra-striped']/tbody/tr[1]/td[1]/a"));
	firstrow.click();
}
	
public String getTableIntroducedDate() {
	return driver.findElement(By.xpath("//table[@class = 'computers zebra-striped']/tbody/tr[1]/td[2]")).getText();
}
public String getTableDiscontinuedDate() {
	return driver.findElement(By.xpath("//table[@class = 'computers zebra-striped']/tbody/tr[1]/td[3]")).getText();
}

public String getTableCompany() {
	return driver.findElement(By.xpath("//table[@class = 'computers zebra-striped']/tbody/tr[1]/td[4]")).getText();
}

/**  methods to get xpath attributes, like value, class etc * */
public String getTextValue(String xpath) {
	return getAttribute(xpath, "value");
}

/**  attribute check, to avoid failing if element is not found * */
public boolean hasAttribute(String xpath, String attrName) {
	try {
		driver.findElement(By.xpath(xpath)).getAttribute(attrName);
	} catch (Exception e) {
		return false;
	}
	return true;
}

public String getAttribute(String xpath, String attrName) {
	return driver.findElement(By.xpath(xpath)).getAttribute(attrName);
}

}
