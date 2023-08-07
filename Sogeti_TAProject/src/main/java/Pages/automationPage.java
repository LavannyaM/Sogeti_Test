package Pages;

import java.time.Duration;

import org.apache.commons.lang3.RandomStringUtils;
import org.openqa.selenium.By;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.interactions.Actions;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.Select;
import org.openqa.selenium.support.ui.WebDriverWait;

public class automationPage {

	WebDriver driver;
	Actions action;
	By title_ContactUs =By.xpath("//h2[text()='Contact us:']");
    By txt_FName = By.xpath("//label[text()='First Name']//following::input[1]");
    By txt_LName = By.xpath("//label[text()='Last Name']//following::input[1]");
    By txt_Email = By.xpath("//label[text()='Email']//following::input[1]");
    By txt_Phone = By.xpath("//label[text()='Phone']//following::input[1]");
    By txt_cName = By.xpath("//label[text()='Company']//following::input[1]");
    By drp_country=By.xpath("//label[text()='Country']//following::select[1]");
    By txt_msg = By.xpath("//label[text()='Message']//following::textarea[1]");
    By chk_Agree = By.xpath("//label[text()='I agree']");
    By btm_Submit = By.xpath("//button[@name='submit']");
    By chk_Captcha = By.xpath("//*[@id='recaptcha-anchor']");
    By Msg_FormStatus=By.xpath("//*[@class='Form__Status']");
    
    public automationPage(WebDriver driver) {
		
    	this.driver = driver;
	}

	public String submitContactUs() throws InterruptedException
    {
            
         try {

         	((JavascriptExecutor)driver).executeScript("arguments[0].scrollIntoView(true);",driver.findElement(Msg_FormStatus) );
			/*
			 * action = new Actions(driver);
			 * action.moveToElement(driver.findElement(Msg_FormStatus)); action.perform();
			 */
             Thread.sleep(500);
        		
             driver.findElement(txt_FName).sendKeys(RandomStringUtils.random(15,true, false));
             driver.findElement(txt_LName).sendKeys(RandomStringUtils.random(15,true, false));
        	 String Email=RandomStringUtils.random(10,true, true)+"@gmail.com";
             driver.findElement(txt_Email).sendKeys(Email);
             long PhoneNum = (long) (Math.random()*Math.pow(10,10));
             driver.findElement(txt_Phone).sendKeys(Long.toString(PhoneNum));
         	
             driver.findElement(txt_cName).sendKeys(RandomStringUtils.random(15,true, false));
             
             Select dropdown = new Select(driver.findElement(drp_country)); 
             dropdown.selectByIndex((int)((Math.random()*(40-1)) + 1));
             driver.findElement(txt_msg).sendKeys(RandomStringUtils.random(500,true, false));
             ((JavascriptExecutor)driver).executeScript("arguments[0].scrollIntoView(true);",driver.findElement(txt_msg) );
             WebDriverWait w= new WebDriverWait(driver, Duration.ofSeconds(10));
             w.until(ExpectedConditions.elementToBeClickable(chk_Agree)).click();
           Thread.sleep(1000);
             //reCAPTCHA cant be automated as this is a dynamic element, Captcha has to be either disabled or clicked manually during execution
            // driver.findElement(chk_Captcha).click();
             driver.findElement(btm_Submit).click();
             
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
         //Validate form Sucess msg
         
         String msg=driver.findElement(Msg_FormStatus).getText();
         System.out.println(msg);
         ((JavascriptExecutor)driver).executeScript("arguments[0].scrollIntoView(true);",driver.findElement(txt_msg) );

    
	return msg;
    
}
}
