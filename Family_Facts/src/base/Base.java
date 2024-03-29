package base;

import java.awt.Desktop;
import java.io.File;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import Results.HtmlWriter;
import Results.ResultXml;

import org.apache.log4j.Logger;
import org.openqa.selenium.WebDriver;
import org.testng.annotations.AfterSuite;
import org.testng.annotations.BeforeSuite;
import org.testng.annotations.Parameters;

public class Base 
{
	public static WebDriver driver;
	public static Logger log=Logger.getLogger("Base");
	public static String startTime,endTime; 
	BrowserCapabilities brow = new BrowserCapabilities();
	
	public WebDriver getDriver()
	{
		return driver;
	}
	
	// Clearing existing log files
		
	@BeforeSuite
	@Parameters(value={"executiontype","domain","browser","version","os","os_version","resolution"})
	public void setUp(String executiontype,String domain,String browser,String version,String os,String os_version,String resolution) throws Exception
	{
		startTime = new SimpleDateFormat("HH:mm:ss").format(Calendar.getInstance().getTimeInMillis());
		log.info("Start Time:"+startTime);
		ResultXml logFile= new ResultXml();
		logFile.deleteLog();
		
		if(executiontype.equalsIgnoreCase("local"))
		{
			driver=brow.launchBrowser(browser);
		}else 
		{
			driver=brow.setRemoteConfiguration(domain, browser, version, os, os_version, resolution);
		}
		
	}
	
	// Read the browser value from TestNG xml and launch the respective browser 
	//@param - Needs 'browser' value parameter in TestNg configuration xml
	
	
	
	/* Tasks executed in After suits:
		   - Will create and write result to HTML file
		   - Launch the Result file in the default browser
		   - Copy log files to the current results folder
	*/
	
	@AfterSuite
	
	public void writeResult()
	{
		endTime= new SimpleDateFormat("HH:mm:ss").format(Calendar.getInstance().getTimeInMillis());
		
		HtmlWriter result = new HtmlWriter();
		String filepath=result.createHtml();
		try{
		File file=new File(filepath);
		String f1=file.getCanonicalPath();
		File file2=new File(f1);
		driver.quit();
		Desktop.getDesktop().browse(file2.toURI());
		}catch(Exception e)
		{
			e.printStackTrace();
		}
		ResultXml logFile= new ResultXml();
		logFile.copyLog();
		
	}
	
}
