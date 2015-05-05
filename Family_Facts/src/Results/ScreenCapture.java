package Results;

import java.io.File;
import java.util.Random;

import keyWords.LocatorsHandler;

import org.apache.commons.io.FileUtils;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.OutputType;
import org.openqa.selenium.TakesScreenshot;
import org.openqa.selenium.WebElement;

import base.Base;

public class ScreenCapture extends Base
{

	Random rand = new Random();
	LocatorsHandler loc = new LocatorsHandler();

	/*public String saveScreen(String modulename, String senario)
	{
		String rtpath;
		String screenpath=ResultXml.filePath+"screens\\"+modulename+senario+rand.nextInt()+".jpg";



		File scrFile = ((TakesScreenshot)driver).getScreenshotAs(OutputType.FILE);
		try{
			FileUtils.copyFile(scrFile, new File(screenpath));
			File f= new File(screenpath);
			rtpath=f.getCanonicalPath();
		}catch(Exception e)
		{
			rtpath="File not found";
		}

		return rtpath;
	}
*/

	public String saveScreenWithHighlight(String modulename, String senario, String property,String locator)
	{
		String rtpath;
		String screenpath=ResultXml.filePath+"screens\\"+modulename+senario+rand.nextInt()+".jpg";
		try
		{
			WebElement element = driver.findElement(loc.locatorHandler(property,locator));

			JavascriptExecutor js = (JavascriptExecutor) driver;
			/*js.executeScript("arguments[0].setAttribute('style', arguments[1]);", element, "color: yellow; border: 3px solid red;");

			try{Thread.sleep(50);} catch (InterruptedException e) {	e.printStackTrace();}*/

			js.executeScript("arguments[0].setAttribute('style', arguments[1]);", element, " border: 3px solid green;");

			try{Thread.sleep(50);} catch (InterruptedException e) {	e.printStackTrace();}

			File scrFile = ((TakesScreenshot)driver).getScreenshotAs(OutputType.FILE);
			try{
				FileUtils.copyFile(scrFile, new File(screenpath));
				File f= new File(screenpath);
				rtpath=f.getCanonicalPath();
			}catch(Exception e)
			{
				rtpath="File not found";
			}
			js.executeScript("arguments[0].setAttribute('style', arguments[1]);", element, ""); 

		}
		catch(Exception e)
		{
			File scrFile = ((TakesScreenshot)driver).getScreenshotAs(OutputType.FILE);
			try{
				FileUtils.copyFile(scrFile, new File(screenpath));
				File f= new File(screenpath);
				rtpath=f.getCanonicalPath();
			}catch(Exception e2)
			{
				rtpath="File not found";
			}
		}
		return rtpath;
	}
}
