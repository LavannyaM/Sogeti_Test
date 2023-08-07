package TestCases;

import java.awt.Desktop;
import java.io.File;
import java.io.IOException;

import org.testng.ITestResult;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.AfterSuite;
import org.testng.annotations.BeforeSuite;

import com.aventstack.extentreports.ExtentReports;
import com.aventstack.extentreports.ExtentTest;
import com.aventstack.extentreports.Status;
import com.aventstack.extentreports.markuputils.ExtentColor;
import com.aventstack.extentreports.markuputils.MarkupHelper;
import com.aventstack.extentreports.reporter.ExtentHtmlReporter;

public class ExtentReporter {


public static ExtentHtmlReporter htmlReporter;
public static ExtentReports extent;
public static ExtentTest test;
File file ;

@BeforeSuite
public void setUp()
{
	Common c = new Common();
	file =new File(c.CurrentDir+"/extentReport.html");
    htmlReporter = new ExtentHtmlReporter(file);
    extent = new ExtentReports();
    extent.attachReporter(htmlReporter);
    extent.setSystemInfo("Environment", "QA");
    extent.setSystemInfo("User Name", "Lavannya Mahalingam");

    htmlReporter.config().setDocumentTitle("Extent report");
    htmlReporter.config().setReportName("Final Report");
}   
@AfterMethod
public void getResult(ITestResult result)
{
    if(result.getStatus() == ITestResult.FAILURE)
    {
        test.log(Status.FAIL, MarkupHelper.createLabel(result.getName()+" Test case FAILED due to below issues:", ExtentColor.RED));
        test.fail(result.getThrowable());
    }
    else if(result.getStatus() == ITestResult.SUCCESS)
    {
        test.log(Status.PASS, MarkupHelper.createLabel(result.getName()+" Test Case PASSED", ExtentColor.GREEN));
    }
    else
    {
        test.log(Status.SKIP, MarkupHelper.createLabel(result.getName()+" Test Case SKIPPED", ExtentColor.ORANGE));
        test.skip(result.getThrowable());
    }
}


//Method to Generate report and launch
	@AfterSuite
	public void getReport() throws IOException{ 
	extent.flush();
	   Desktop desktop = Desktop.getDesktop();
       if(file.exists()) desktop.open(file);
				
	}
}
