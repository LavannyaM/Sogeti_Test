package Pages;

import java.io.IOException;
import java.time.Duration;
import java.util.List;
import java.util.concurrent.TimeUnit;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.interactions.Actions;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

import TestCases.Common;

public class HomePage {

		WebDriver driver;
		Actions action; 
		By Btn_acceptCookie =By.xpath("//button[@class='acceptCookie']");
	By lmg_Sogeti = By.xpath("(//img[@alt='Sogeti Logo'])[1]");
	By lnk_Services = By.xpath("(//*[@id='main-menu']//span[.='Services'])[1]");
	By lnk_Automation = By.xpath("//a[@class='subMenuLink' and text()='Automation']"); 
	By title_Auto = By.xpath("//span[text()='Automation']");
	By lnk_Worldwide = By.xpath("//*[.='Worldwide']");
	By list_Country = By.xpath("//*[@id='country-list-id']/ul/li/a");

	// Constructor to initiate driver
	public HomePage(WebDriver driver) {
		this.driver = driver;

	}

	//Open Sogeti Application and Validate Page Load
	public void openApplication() throws InterruptedException, IOException {

		driver.get(Common.getValue("SearchURL"));
		if(driver.findElement(Btn_acceptCookie).isDisplayed())
			driver.findElement(Btn_acceptCookie).click();
	// Explicit wait - to wait until the Sogeti Logo to be displayed
		WebDriverWait w = new WebDriverWait(driver, Duration.ofSeconds(10));
		w.until(ExpectedConditions.presenceOfElementLocated(lmg_Sogeti));
		
	}


public Boolean NavigateAutomation()
{
	//Creating object of an Actions class
	action=new Actions(driver);
	action.moveToElement(driver.findElement(lnk_Services));
	action.moveToElement(driver.findElement(lnk_Automation));
	action.click().build().perform();
	
	WebDriverWait w = new WebDriverWait(driver, Duration.ofSeconds(10));
	w.until(ExpectedConditions.presenceOfElementLocated(title_Auto)); 
	return 	driver.findElement(title_Auto).isEnabled();
}

public String VerifyLink() throws InterruptedException
{
	action=new Actions(driver);
	action.moveToElement(driver.findElement(lnk_Services)).perform();
	Thread.sleep(1000);
	 if((driver.findElement(lnk_Services).isEnabled())&& (driver.findElement(lnk_Automation).isEnabled()) )
		 return "Sucess";
	 else 
		return "Failed";
}
public void NavigateWorldwide()
{
	driver.findElement(lnk_Worldwide).click();
}

public List<WebElement> GetLinks()
{

	// Finding all the available links on webpage
	List<WebElement> links = driver.findElements(list_Country);
  
	return links;

}


}
	 


