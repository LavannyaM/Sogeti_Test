package TestCases;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.OutputType;
import org.openqa.selenium.TakesScreenshot;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileReader;
import java.io.IOException;
import java.net.HttpURLConnection;
import java.net.URL;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;
import java.util.Properties;

import org.apache.commons.io.FileUtils;
import org.apache.commons.lang3.RandomStringUtils;


public class Common {

	public static File CurrentDir;
	
	//Constructor - To generate report file
	public Common() 
	{ 
		LocalDate timeStamp = java.time.LocalDate.now();

		LocalDateTime now = LocalDateTime.now(); 
		String Temptime = now.toString();
		String[] TempDateArr = Temptime.split("T",3); 
		String[] hhmmssStamp =
				TempDateArr[1].split("\\."); 
		String[] hhmmss_name= hhmmssStamp[0].split(":");
		String time_folder = hhmmss_name[0]+"_"+hhmmss_name[1]+"_"+hhmmss_name[2];

		CurrentDir = new File(System.getProperty("user.dir") +"/Results/"+timeStamp+"/"+time_folder);
		if (!CurrentDir.exists()) 
		{ 
			//System.out.println(CurrentDir);
			CurrentDir.mkdirs(); }

	}
	 
	//To fetch value from the properties file
	public static String getValue (String key) throws IOException
	{
		FileReader reader=new FileReader("../Sogeti_TAProject/TestData.properties");
		Properties props=new Properties();
		props.load(reader);
		String value = props.getProperty(key);

		return value;
	}
	
	//To Capture Screenshots of test
	public static String takeSnapShot(WebDriver webdriver,String testcase) throws Exception
	{

		String fileWithPath=CurrentDir+"\\"+testcase+".png";
		//System.out.println(fileWithPath);
		TakesScreenshot scrShot =((TakesScreenshot)webdriver);
		File SrcFile=scrShot.getScreenshotAs(OutputType.FILE);
		//Move image file to new destination
		File DestFile=new File(fileWithPath);

		//Copy file at destination
		FileUtils.copyFile(SrcFile, DestFile);
		System.out.println(fileWithPath);
		return fileWithPath;

	}
	
	
	public static String randomestring() 
	  { 
		  String generatedstring=RandomStringUtils.randomAlphabetic(8);
	  return(generatedstring); 
	  }
	 
	
public static int verifyLink(String url) throws Exception

{
	
		URL link = new URL(url);
	HttpURLConnection httpURLConnection = (HttpURLConnection) link.openConnection();
	httpURLConnection.setConnectTimeout(3000); 
	httpURLConnection.connect();
    return 	httpURLConnection.getResponseCode();
	
}
}


