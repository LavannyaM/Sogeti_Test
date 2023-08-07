package TestCases;


import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.testng.annotations.*;
import org.testng.asserts.SoftAssert;

import com.aventstack.extentreports.Status;

import static org.testng.Assert.assertTrue;
import java.util.List;
import java.util.concurrent.TimeUnit;
import Pages.HomePage;
import Pages.automationPage;


public class UITest extends ExtentReporter {

	WebDriver driver;
    //Method to Launch Chrome browser
	@BeforeTest
	public void LaunchBrowser() 
	{
		
		System.setProperty("webdriver.chrome.driver","../Sogeti_TAProject/chromedriver.exe");
		driver = new ChromeDriver();
		driver.manage().window().maximize();
		driver.manage().timeouts().implicitlyWait(5, TimeUnit.SECONDS);
		
	}
	
	  // Method to launch application and Navigate to Automation Screen
	  
	@Test(enabled=true,priority =
	1,description="Launch and checks the resulting Screen")

	public void TestCase01() throws Throwable 
	{
		test=extent.createTest("Launch Browser and verify Success : " + "TestCase 01");
		//Creating object of Home page 
		HomePage home = new HomePage(driver);
		 SoftAssert softAssert = new SoftAssert();
		try
		{
		home.openApplication(); 
		test.log(Status.PASS,"Navigated to the specified URL and Page Loaded sucessfully"); 
		test.addScreenCaptureFromPath(Common.takeSnapShot(driver,"Homepage"));
		assertTrue(home.NavigateAutomation());
		
		String Result=home.VerifyLink();
		assertTrue(Result.equals("Sucess"));
		//To validate Automation link
		if(Result.equals("Sucess")) 
		{ 
	
		test.log(Status.PASS,"Navigated to Automation Link and Page Loaded sucessfully");
		test.addScreenCaptureFromPath(Common.takeSnapShot(driver,"TabSelection")); } 
		else 
		{
			
			test.log(Status.FAIL,  "Page Not Loaded sucessfully");
			test.addScreenCaptureFromPath(Common.takeSnapShot(driver,"ErrorScreen"));
	
	}
		}
		catch(Exception e) {
			
			test.log(Status.FAIL,  "Test Failed" + e);
			test.addScreenCaptureFromPath(Common.takeSnapShot(driver,"ErrorScreen"));
		}

	}
	 
	// Method to launch application and Submit Contact Us Form
		@Test(enabled=true,priority = 2,description="Performs login and complete Contact Us Form:")
		public void TestCase02() throws Throwable	
		{
			test=extent.createTest("Complete Contach Us Form and verify Success : " + "TestCase 02");
			String Result1 = null;
			//Creating object of Home page
			HomePage home = new HomePage(driver);
			automationPage auto= new automationPage(driver);
			try {
			home.openApplication();
			
			test.log(Status.PASS, "Navigated to the specified URL and Page Loaded sucessfully");
		    test.addScreenCaptureFromPath(Common.takeSnapShot(driver,"Homepage"));	
			if(home.NavigateAutomation())
			{
				 Result1=auto.submitContactUs();
				
			}
		    String msg="Thank you for contacting us.";
		    
			if(Result1.equalsIgnoreCase(msg))
			{
				
				test.log(Status.PASS, "Navigated to Automation Link and Verified Form Submission");
				test.addScreenCaptureFromPath(Common.takeSnapShot(driver,"FormSubmit"));
							}
			else
			{
			
				test.log(Status.FAIL, "Form Submission Failed");
				test.addScreenCaptureFromPath(Common.takeSnapShot(driver,"FormError"));
			}
			}
			catch(Exception e) {

			test.log(Status.FAIL, "Form Submission Failed" + e);
			test.addScreenCaptureFromPath(Common.takeSnapShot(driver,"FormError"));
		}
		}

				// Method to launch application and Submit Contact Us Form
		@Test(enabled=true,priority = 3,description="To Validate Country Specific Sogeti Links")
		public void TestCase03() throws Throwable	
		{
			test=extent.createTest("To Validate Country Specific Sogeti Links" + "TestCase 03");
			int Result=0;String url = null ;
			//Creating object of Home page
			HomePage home = new HomePage(driver);
		
			try {
			home.openApplication();
			//assertTrue();
			home.NavigateWorldwide();
			test.log(Status.PASS, "Navigated to the specified URL and Page Loaded sucessfully");
		    		    List<WebElement> links =home.GetLinks();
		    
		    			
		 // Iterating each link and checking the response status
			for (WebElement link : links) {
			url = link.getAttribute("href");
			try {
			Result =Common.verifyLink(url);
			if ( Result== 200) {
				test.log(Status.PASS, "Validated the URL: " + url + " - " + Result  );
				//System.out.println(url + " - " + Result);
				} 
				else {
				
				test.log(Status.FAIL, "Link " +url+ " is a broken link" );
				}
				}
		    
				
		    catch (Exception e) 
				{
				//System.out.println(url + " - " + "is a broken link");
				test.log(Status.ERROR, "Link " +url+ " is a broken link /n" + e);
				}
			}
		    			test.log(Status.PASS, "URL in Country List is Validated Sucessfully");	
		    			test.addScreenCaptureFromPath(Common.takeSnapShot(driver,"Worldwide"));				
			}
			catch(Exception e) {
		  //System.out.println(url + " - " + "is a broken link");
			test.log(Status.FAIL, "URL in Country List Validation Failed /n" + e);	
			test.addScreenCaptureFromPath(Common.takeSnapShot(driver,"Error_Worldwide"));	
		}
		}
			
	//Method to close the driver instance
		@AfterTest
	public void tearDown() 
	{
		driver.quit();

	}

  
}
