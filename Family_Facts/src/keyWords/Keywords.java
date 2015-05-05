package keyWords;

import java.awt.Robot;
import java.awt.event.KeyEvent;
import java.util.Set;

import Data_Reader.GetData;
import Results.ResultXml;
import Results.ScreenCapture;

import org.apache.log4j.Logger;
import org.openqa.selenium.By;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.interactions.Action;
import org.openqa.selenium.interactions.Actions;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.testng.Assert;

import com.gargoylesoftware.htmlunit.ElementNotFoundException;

import base.Base;


public class Keywords {

	Base base = new Base();
	WebDriver driver= base.getDriver();
	Logger log= Base.log;
	static String parent;
	static boolean fileexist=false;
	LocatorsHandler loc = new LocatorsHandler();
	ResultXml res = new ResultXml();
	ScreenCapture screen = new ScreenCapture();

	//=============== handling Get operation ===================================

	// @param - TestData,ModuleName,TestCaseName,SenarioDescription
	// @return - null

	public void geturl(String testdata, String modulename, String testcaseName, String senarioDescription)
	{
		try
		{

			driver.get(testdata);
			log.info(testdata +" Url is opened");
			String screenpath =  screen.saveScreenWithHighlight(modulename, senarioDescription, "Opened_URL", "URL");
			xmlCall("Pass", modulename, testcaseName, senarioDescription,screenpath);
			Assert.assertTrue(true,senarioDescription);
		}catch(ElementNotFoundException elementNOtfound)
		{
			elementNOtfound.printStackTrace();
		}catch(Exception e)
		{
			e.printStackTrace();
			log.info("unable to open the provided URL "+testdata);
			String screenpath =  screen.saveScreenWithHighlight(modulename, senarioDescription, "Unable_to_Open_URL", "URL");
			xmlCall("Fail", modulename, testcaseName, senarioDescription,screenpath);
		}
	}


	//=============== handling Click operation ===================================

	/*
	 * @param - Property,  Locator, moduleName,testCaseName,SenarioDescription
	 * @return - null
	 */
	public void click(String property, String locator, String modulename, String testcaseName, String senarioDescription)
	{

		try{
			myWait(property,locator, modulename, testcaseName, senarioDescription);
			//highlightElement(property,locator);
			String screenpath =  screen.saveScreenWithHighlight(modulename, senarioDescription, property, locator);
			log.info("Highlighted Screen"+screenpath);
			driver.findElement(loc.locatorHandler(property,locator)).click();

			log.info(locator+" Element Clicked");
			xmlCall("Pass", modulename, testcaseName, senarioDescription,screenpath);
		}catch(ElementNotFoundException elementNOtfound)
		{
			elementNOtfound.printStackTrace();
		}
		catch(Exception e)
		{
			e.printStackTrace();
			log.info(locator+"Unable to click Element");
			String screenpath =  screen.saveScreenWithHighlight(modulename, senarioDescription, property, locator);
			xmlCall("Fail", modulename, testcaseName, senarioDescription,screenpath);
		}
	}

	//=============== handling sendkeys operation ===================================
	/*
	 * @param - Property, Locator, testData, moduleName, testCaseName, SenarioDescription
	 * @return - null
	 */
	public void sendkeys(String property,String locator,String testData, String modulename, String testcaseName, String senarioDescription)
	{
		String screenpath;
		try
		{
			myWait(property,locator, modulename, testcaseName, senarioDescription);
			highlightElement(property, locator);

			String enteryData= getAndSend(testData, modulename, testcaseName, senarioDescription);
			driver.findElement(loc.locatorHandler(property,locator)).sendKeys(enteryData);
			log.info(enteryData+" value is entereed in "+locator+" Element");
			screenpath =  screen.saveScreenWithHighlight(modulename, senarioDescription, property, locator);
			xmlCall("Pass", modulename, testcaseName, senarioDescription,screenpath);
		}catch(ElementNotFoundException elementNOtfound)
		{
			elementNOtfound.printStackTrace();
		}catch(Exception e)
		{
			screenpath =  screen.saveScreenWithHighlight(modulename, senarioDescription, property, locator);
			xmlCall("Fail", modulename, testcaseName, senarioDescription,screenpath);
			log.info("Not able to enter value in "+locator+" Element");
			e.printStackTrace();
		}
	}

	//=============== handling Clear operation ===================================
	/*
	 * @param - Property,  Locator, moduleName,testCaseName,SenarioDescription
	 * @return - null
	 */

	public void clearField(String property,String locator, String modulename, String testcaseName, String senarioDescription)
	{
		String screenpath;
		try
		{
			myWait(property,locator, modulename, testcaseName, senarioDescription);
			highlightElement(property, locator);
			driver.findElement(loc.locatorHandler(property,locator)).clear();
			log.info(locator+" Element is cleared");
			screenpath =  screen.saveScreenWithHighlight(modulename, senarioDescription, property, locator);
			xmlCall("Pass", modulename, testcaseName, senarioDescription,screenpath);
		}catch(ElementNotFoundException elementNOtfound)
		{
			elementNOtfound.printStackTrace();
		}catch(Exception e)
		{
			e.printStackTrace();
			log.info("Not able to clear"+locator);
			screenpath =  screen.saveScreenWithHighlight(modulename, senarioDescription, property, locator);
			xmlCall("Fail", modulename, testcaseName, senarioDescription,screenpath);
		}
	}

	//=============== Catching getText operation ===================================

	/*
	 * @param - Property,  Locator, moduleName,testCaseName,SenarioDescription
	 * @return - String
	 */

	public String gettingText(String property,String locator, String modulename, String testcaseName, String senarioDescription)
	{
		String textRetrived;
		try
		{
			myWait(property,locator, modulename, testcaseName, senarioDescription);
			highlightElement(property, locator);
			textRetrived=driver.findElement(loc.locatorHandler(property,locator)).getText();
			log.info(textRetrived+" text is reterived from the '"+locator+"' locator");
			//xmlCall("Pass", modulename, testcaseName, senarioDescription);
		}catch(ElementNotFoundException elementNOtfound)
		{	textRetrived="Not able to retrive the text";
			log.info("Not able to retrive text from "+locator);
			elementNOtfound.printStackTrace();
		}catch(Exception e)
		{
			textRetrived="Not able to retrive the text";
			log.info("Not able to retrive text from "+locator);
			e.printStackTrace();
			//xmlCall("Fail", modulename, testcaseName, senarioDescription);
		}


		return textRetrived;
	}


	//=============== handling move to element operation ===================================	

	/*
	 * @param - Property,  Locator, moduleName,testCaseName,SenarioDescription
	 * @return - null
	 */

	public void moveToElement(String property, String locator, String modulename, String testcaseName, String senarioDescription)
	{
		String screenpath;
		try
		{
			myWait(property,locator, modulename, testcaseName, senarioDescription);
			highlightElement(property, locator);
			WebElement e = driver.findElement(loc.locatorHandler(property,locator));

			Actions builder = new Actions(driver);
			log.info("Action Builder Object is created");
			Action action = builder.moveToElement(e).build();
			action.perform();
			log.info("Moved to the Element "+e+" using Javascript Executer");
			screenpath =  screen.saveScreenWithHighlight(modulename, senarioDescription, property, locator);
			xmlCall("Pass", modulename, testcaseName, senarioDescription,screenpath);
		}catch(ElementNotFoundException elementNOtfound)
		{
			elementNOtfound.printStackTrace();
		} catch(Exception e)
		{
			screenpath =  screen.saveScreenWithHighlight(modulename, senarioDescription, property, locator);
			xmlCall("Fail", modulename, testcaseName, senarioDescription,screenpath);
			log.info("unable to Move to the Element using Javascript Executer");
			e.printStackTrace();
		}
	}


	//=============== handling Java script executer click operation ===================================

	/*
	 * @param - Property,  Locator, moduleName,testCaseName,SenarioDescription
	 * @return - null
	 */


	public void javaScriptClick(String property, String locator, String modulename, String testcaseName, String senarioDescription)
	{
		String screenpath;
		try
		{
			String subString,mainString;
			WebDriverWait wait= new WebDriverWait(driver,30);
			subString =locator.substring(0,1);
			mainString=locator.substring(2);
			wait.until(ExpectedConditions.visibilityOfElementLocated(By.id(mainString)));
			highlightElement(property, locator);

			if(subString.equalsIgnoreCase("j"))	// for tag name
			{
				JavascriptExecutor js = (JavascriptExecutor)driver; 
				js.executeScript("return document.getElementById('"+mainString+"').click()");
				// log.info("Java script Click is performed on ")
			}
			screenpath =  screen.saveScreenWithHighlight(modulename, senarioDescription, property, locator);
			xmlCall("Pass", modulename, testcaseName, senarioDescription,screenpath);
		}catch(ElementNotFoundException elementNOtfound)
		{
			elementNOtfound.printStackTrace();
		}catch(Exception e)
		{
			e.printStackTrace();
			screenpath =  screen.saveScreenWithHighlight(modulename, senarioDescription, property, locator);
			xmlCall("Fail", modulename, testcaseName, senarioDescription,screenpath);
		}
	}

	//=============== handling Java script executer click operation ===================================

	/*
	 * @param - Property,  Locator, moduleName,testCaseName,SenarioDescription
	 * @return - null
	 */

	public void uploadViaRobt(String sheetName, String testdata, String modulename, String testcaseName, String senarioDescription)
	{
		String screenpath;

		try
		{
			GetData d= new GetData();

			String path,mainString;
			String []s1=testdata.split("@");
			String []s2=s1[1].split(",");
			int row,col;
			row=Integer.parseInt(s2[0]);
			col=Integer.parseInt(s2[1]);
			mainString=sheetName.substring(3);
			path=d.getFilename(s2[0],mainString,row, col);
			path = path.toUpperCase();
			Robot r = new Robot();
			int ar[]= new int[path.length()];


			for(int i=0;i<path.length();i++)
			{
				ar[i]=path.charAt(i);

				if(ar[i]==':')
				{
					Thread.sleep(100);
					r.keyPress(KeyEvent.VK_SHIFT);  
					r.keyPress(KeyEvent.VK_SEMICOLON);  
					r.keyRelease(KeyEvent.VK_SEMICOLON);  
					r.keyRelease(KeyEvent.VK_SHIFT);
				}else  if(ar[i]=='\\')
				{
					Thread.sleep(100);
					r.keyPress(KeyEvent.VK_BACK_SLASH);
					r.keyRelease(KeyEvent.VK_BACK_SLASH);
				} else if(ar[i]=='.')
				{
					Thread.sleep(100);
					r.keyPress(KeyEvent.VK_PERIOD);
					r.keyRelease(KeyEvent.VK_PERIOD);
				} else if(ar[i]==' ')
				{
					Thread.sleep(100);
					r.keyPress(KeyEvent.VK_SPACE);
					r.keyRelease(KeyEvent.VK_SPACE);
				}

				else 
				{
					Thread.sleep(100);
					r.keyPress(ar[i]);
					r.keyRelease(ar[i]);
				}  


			}

			Thread.sleep(500);
			r.keyPress(KeyEvent.VK_ENTER);
			screenpath =  screen.saveScreenWithHighlight(modulename, senarioDescription, "Robot", "Windows");
			xmlCall("Pass", modulename, testcaseName, senarioDescription,screenpath);

		}catch(ElementNotFoundException elementNOtfound)
		{
			elementNOtfound.printStackTrace();
		}catch(Exception e)
		{
			e.printStackTrace();
			screenpath =  screen.saveScreenWithHighlight(modulename, senarioDescription, "Robot", "Windows");
			xmlCall("Fail", modulename, testcaseName, senarioDescription,screenpath);
		}
	}

	//=============== handling get data from other sheet and Return===================================

	/*
	 * @param - testdata, moduleName,testCaseName,SenarioDescription
	 * @return - String
	 */

	public String getAndSend(String testData, String modulename, String testcaseName, String senarioDescription)
	{
		String locator;
		GetData d= new GetData();
		int row,col;

		log.info("GetAndSend method Called");
		if(testData.contains("path_"))
		{
			String []sheet1 = testData.split("_");
			String []sheet2 = sheet1[1].split("@");
			String []sheet3 = sheet2[1].split("/");
			String []cell1=sheet3[1].split(",");
			row=Integer.parseInt(cell1[0]);
			col=Integer.parseInt(cell1[1]);
			locator=d.getFilename(sheet2[0],sheet3[0],row,col);
			return locator;
		}else
		{
			log.info("GetAndSend returned testdata directly");
			return testData;
		}

	}

	//=============== handling Selected ===================================
	/*
	 * @param - Property, Locator, moduleName,testCaseName,SenarioDescription
	 * @return - null
	 */

	public void select(String property, String locator, String modulename, String testcaseName, String senarioDescription)
	{
		String screenpath;
		try
		{
			myWait(property,locator, modulename, testcaseName, senarioDescription);
			highlightElement(property, locator);
			if(!driver.findElement(loc.locatorHandler(property, locator)).isSelected())
			{
				log.info(locator+" checkbox is not selected already");
				driver.findElement(loc.locatorHandler(property, locator)).click();
				log.info(locator+" checkbox is being selected now");
				screenpath =  screen.saveScreenWithHighlight(modulename, senarioDescription, property, locator);
				xmlCall("Pass", modulename, testcaseName, senarioDescription,screenpath);
			}else
			{
				screenpath =  screen.saveScreenWithHighlight(modulename, senarioDescription, property, locator);
				xmlCall("Pass", modulename, testcaseName, senarioDescription,screenpath);
				log.info(locator+" is already selected");
			}
		}catch(ElementNotFoundException elementNOtfound)
		{
			elementNOtfound.printStackTrace();
		}catch(Exception e)
		{
			e.printStackTrace();
			log.info("Not able to select "+locator+" element");
			screenpath =  screen.saveScreenWithHighlight(modulename, senarioDescription, property, locator);
			xmlCall("Fail", modulename, testcaseName, senarioDescription,screenpath);
		}
	}

	//=============== handling deSelected ===================================
	/*
	 * @param - Property, Locator, moduleName,testCaseName,SenarioDescription
	 * @return - null
	 */

	public void deSelect(String property, String locator, String modulename, String testcaseName, String senarioDescription)
	{
		String screenpath;
		try
		{
			myWait(property,locator, modulename, testcaseName, senarioDescription);
			highlightElement(property, locator);
			if(driver.findElement(loc.locatorHandler(property, locator)).isSelected())
			{
				log.info(locator+" checkbox is selected already");
				driver.findElement(loc.locatorHandler(property,locator)).click();
				log.info(" unchecked the following"+locator+" check box");
				screenpath =  screen.saveScreenWithHighlight(modulename, senarioDescription, property, locator);
				xmlCall("Pass", modulename, testcaseName, senarioDescription,screenpath);
			}else
			{
				screenpath =  screen.saveScreenWithHighlight(modulename, senarioDescription, property, locator);
				log.info(locator+" is already deselected");
				xmlCall("Pass", modulename, testcaseName, senarioDescription,screenpath);
			}
		} catch(ElementNotFoundException elementNOtfound)
		{
			elementNOtfound.printStackTrace();
		}catch(Exception e)
		{
			log.info("Not able to deselect "+locator+" element");
			screenpath =  screen.saveScreenWithHighlight(modulename, senarioDescription, property, locator);
			xmlCall("Fail", modulename, testcaseName, senarioDescription,screenpath);
		}
	}

	//=============== Switching to Child Window ===================================

	/*
	 * @param -  Locator, moduleName,testCaseName,SenarioDescription
	 * @return - null
	 */

	public void switchWindow(String locator, String modulename, String testcaseName, String senarioDescription)
	{
		String screenpath;
		String parent = driver.getWindowHandle();
		Set<String> winlist= driver.getWindowHandles();
		log.info("No of windows opened are :"+winlist.size());
		try
		{
			if(winlist.size()>1)
			{
				String [] loc = locator.split("_");
				int count=0;
				for (String s : winlist)
				{
					driver.switchTo().window(s);
					if(driver.getTitle().equals(loc[1]))
					{
						log.info("Driver is on :"+loc[1]);
						break;

					}
					count++;
				}
				if(count==winlist.size())
				{
					log.info("Windows were exist but expected '"+loc[1]+"' Window not found in the opned windows");
					driver.switchTo().window(parent);
				}
				screenpath =  screen.saveScreenWithHighlight(modulename, senarioDescription, "Windows_Handle", locator);
				xmlCall("Pass", modulename, testcaseName, senarioDescription,screenpath);
			}else
			{
				log.info("No additional window is opened");
			}
		} catch(ElementNotFoundException elementNOtfound)
		{
			elementNOtfound.printStackTrace();
		}catch(Exception e)
		{
			e.printStackTrace();
			log.info("Not able to perform switch to window to "+locator);
			screenpath =  screen.saveScreenWithHighlight(modulename, senarioDescription, "Windows_Handled", locator);
			xmlCall("Fail", modulename, testcaseName, senarioDescription,screenpath);
		}
	}

	//=============== getCurrentURL ===================================

	/*
	 * @param - moduleName,testCaseName,SenarioDescription
	 * @return - null
	 */

	public void getCurrentURL( String modulename, String testcaseName, String senarioDescription)
	{
		String screenpath;
		try
		{
			driver.getCurrentUrl();
			screenpath =  screen.saveScreenWithHighlight(modulename, senarioDescription, "Current_Browser", "URL");
			xmlCall("Pass", modulename, testcaseName, senarioDescription,screenpath);
		}catch(ElementNotFoundException elementNOtfound)
		{
			elementNOtfound.printStackTrace();
		}
		catch(Exception e)
		{
			e.printStackTrace();
			screenpath =  screen.saveScreenWithHighlight(modulename, senarioDescription, "Current_Browser", "URL");
			xmlCall("Fail", modulename, testcaseName, senarioDescription,screenpath);
		}
	}

	//=============== Switching to Parent Window ===================================

	/*
	 * @param - moduleName,testCaseName,SenarioDescription
	 * @return - null
	 */
	public void switchToParent( String modulename, String testcaseName, String senarioDescription)
	{
		String screenpath;
		try
		{
			driver.switchTo().window(parent);
			log.info("Swicthed to Parent");
			screenpath =  screen.saveScreenWithHighlight(modulename, senarioDescription, "Switch_to_window", "Parent_window");
			xmlCall("Pass", modulename, testcaseName, senarioDescription,screenpath);
		}catch(ElementNotFoundException elementNOtfound)
		{
			elementNOtfound.printStackTrace();
		}catch(Exception e)
		{
			screenpath =  screen.saveScreenWithHighlight(modulename, senarioDescription, "Switch_to_window", "Parent_window");
			xmlCall("Fail", modulename, testcaseName, senarioDescription,screenpath);
		}
	}

	//=============== handling Wait operation ===================================

	/*
	 * @param - Property, Locator, moduleName,testCaseName,SenarioDescription
	 * @return - null
	 */

	public void myWait(String property,String locator, String modulename, String testcaseName, String senarioDescription)
	{
		try
		{
			WebDriverWait wait= new WebDriverWait(driver,30);
			wait.until(ExpectedConditions.visibilityOfElementLocated(loc.locatorHandler(property,locator)));
		}catch(ElementNotFoundException elementNOtfound)
		{
			elementNOtfound.printStackTrace();
		}catch(Exception e){e.printStackTrace();}
	}

	//=============== handling Verifications ===================================	

	/*
	 * @param - Property, Locator, expected, moduleName,testCaseName,SenarioDescription
	 * @return - null
	 */

	public void verifyContentMatch(String property, String locator,String expected, String modulename, String testcaseName, String senarioDescription)
	{
		String screenpath;
		try
		{
			String actualText= gettingText(property, locator, modulename, testcaseName, senarioDescription);
			log.info("Expected Text:"+expected);
			if(actualText.equalsIgnoreCase(expected.trim()))
			{
				screenpath =  screen.saveScreenWithHighlight(modulename, senarioDescription, property, locator);
				xmlCall("Pass", modulename, testcaseName, senarioDescription,screenpath);
				Assert.assertEquals(actualText,expected.trim());
			}
			else
			{
				screenpath =  screen.saveScreenWithHighlight(modulename, senarioDescription, property, locator);
				xmlCall("Fail", modulename, testcaseName, senarioDescription,screenpath);
				Assert.assertEquals(actualText,expected.trim());
			}

		}catch(ElementNotFoundException elementNOtfound)
		{
			elementNOtfound.printStackTrace();
		}catch(Exception e)
		{
			e.printStackTrace();
		}
	}


	//=============== handling Drag and drops ===================================


	public void dragAndDrop(String property1, String property2, String locator1, String locator2,String expected, String modulename, String testcaseName, String senarioDescription)
	{
		String screenpath;
		try
		{
			WebElement dragElement=driver.findElement(loc.locatorHandler(property1, locator1));  
			WebElement dropElement=driver.findElement(loc.locatorHandler(property2, locator2));
			Actions builder = new Actions(driver);  // Configure the Action  
			Action dragAndDrop = builder.clickAndHold(dragElement)  
					.moveToElement(dropElement)  
					.release(dropElement)  
					.build();  // Get the action  
			dragAndDrop.perform(); // Execute the Action
			screenpath =  screen.saveScreenWithHighlight(modulename, senarioDescription, property2, locator2);
			xmlCall("Pass", modulename, testcaseName, senarioDescription,screenpath);
		}catch(ElementNotFoundException elementNOtfound)
		{
			elementNOtfound.printStackTrace();
		}catch(Exception e)
		{
			e.printStackTrace();
		}

	}

	//===============writing to XML results ===================================

	/*
	 * @param - result, moduleName,testCaseName,SenarioDescription
	 * @return - null
	 */

	public void xmlCall(String result, String modulename, String testcaseName, String senarioDescription, String screenpath)
	{
		if(fileexist)
		{
			res.updateXml(modulename, testcaseName, senarioDescription, result, screenpath);
		}
		else
		{
			fileexist = true;
			res.createXml("suiteName");
			res.updateXml(modulename, testcaseName, senarioDescription , result, screenpath);

		}

	}

	//=============== Code to Highlight web Element ===================================

	/*
	 * @param - Property, Locator
	 * @return - null
	 */

	public void highlightElement(String property, String locator) 
	{ 
		WebElement element = driver.findElement(loc.locatorHandler(property,locator));
		Base.log.info("Highlight function called: Received keyword"+property+" Recived Locator"+locator);
		for (int i = 0; i < 2; i++) 
		{ 
			JavascriptExecutor js = (JavascriptExecutor) driver;
			if(i==0)
			{
				js.executeScript("arguments[0].setAttribute('style', arguments[1]);", element, "color: yellow; border: 3px solid red;");
			}else
			{
				js.executeScript("arguments[0].setAttribute('style', arguments[1]);", element, "color: yellow; border: 3px solid green;");
			}

			try 
			{
				Thread.sleep(150);
			} catch (InterruptedException e) {
				e.printStackTrace();
			}
			js.executeScript("arguments[0].setAttribute('style', arguments[1]);", element, ""); 
			try 
			{
				Thread.sleep(150);
			} catch (InterruptedException e) {
				e.printStackTrace();
			}
		}
	}


	//=============== END =====================  END ============== END =====================
}
