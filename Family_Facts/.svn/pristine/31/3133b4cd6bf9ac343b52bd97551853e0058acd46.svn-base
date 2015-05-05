package base;

import java.net.URL;

import org.apache.log4j.Logger;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.chrome.ChromeOptions;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.openqa.selenium.firefox.FirefoxProfile;
import org.openqa.selenium.ie.InternetExplorerDriver;
import org.openqa.selenium.remote.DesiredCapabilities;
import org.openqa.selenium.remote.RemoteWebDriver;
import org.openqa.selenium.safari.SafariDriver;

public class BrowserCapabilities 
{
	WebDriver driver=Base.driver;
	Logger log=Base.log;
	public WebDriver launchBrowser(String browser)
	{
		if(browser.equalsIgnoreCase("ie"))
		{				
			System.setProperty("webdriver.ie.driver", "D:\\Automation_Script\\Workspace\\Drivers\\IEDriverServer.exe");
			driver = new InternetExplorerDriver();
			driver.manage().window().maximize();
			log.info("Internet Explorer Browser is Launched");
		}
		else if (browser.equalsIgnoreCase("ff")||browser.equalsIgnoreCase("firefox"))
		{
			FirefoxProfile firefoxProfile = new FirefoxProfile();
			firefoxProfile.setPreference("plugin.state.npmcffplg", 0);
			driver= new FirefoxDriver(firefoxProfile);
			driver.manage().window().maximize();
			log.info("FireFox Browser is Launched");
		}
		else if (browser.equalsIgnoreCase("chrome"))
		{
			System.setProperty("webdriver.chrome.driver", "D:\\Automation_Script\\Workspace\\Drivers\\chromedriver.exe");
			ChromeOptions options=new ChromeOptions();
			options.addArguments("test-type");
			driver = new ChromeDriver(options);
			driver.manage().window().maximize();
			log.info("Chrome Browser is Launched");
			
		}else if(browser.equalsIgnoreCase("safari"))
		{
			driver= new SafariDriver();
			driver.manage().window().maximize();
			log.info("Safari Browser is Launched");
		}
		
		else
		{
			log.info("Browser selected is not found, Configure testNG xml correctly");
		}
		return driver;
	}
	
public WebDriver setRemoteConfiguration(String domain,String browser,String version,String os,String os_version,String resolution) throws Exception
	{
	
	  
	  DesiredCapabilities caps = new DesiredCapabilities();
	  
	  if(domain.equalsIgnoreCase("browserstack"))
	  {	  
		  final String USERNAME = "kptester1";
		  final String AUTOMATE_KEY = "iL4j6vpiuH2rDxXFv2Hd";
		String URL = "http://" + USERNAME + ":" + AUTOMATE_KEY + "@hub.browserstack.com/wd/hub";
		
		caps.setCapability("browser", browser);
		caps.setCapability("browser_version", version);
		caps.setCapability("os", os);
		caps.setCapability("os_version", os_version);
		caps.setCapability("resolution", resolution);
		caps.setCapability("browserstack.debug", "true");
		driver=new RemoteWebDriver(new URL(URL),caps);
	  }else if(domain.equalsIgnoreCase("saucelabs"))
	  {
		  final String USERNAME = "sendlinkprasad";
		  final String AUTOMATE_KEY = "50cd4c3e-9ed2-4cf4-8345-d73faec262d4";
		String URL = "http://"+USERNAME+":"+AUTOMATE_KEY+"@ondemand.saucelabs.com:80/wd/hub";
		caps.setBrowserName(browser);
		caps.setCapability("version", version);
        caps.setCapability("platform", os);
        //caps.setCapability("deviceName", "On_SauceLabs");
        driver=new RemoteWebDriver(new URL(URL),caps);
	  }
		
	
		return driver;
		
		
		
	}

}
