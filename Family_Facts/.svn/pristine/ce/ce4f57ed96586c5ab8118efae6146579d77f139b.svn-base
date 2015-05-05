package keyWords;


import org.openqa.selenium.By;

public class LocatorsHandler 
{
	
	public By locatorHandler(String property,String locator)
	{
		
		
		if(property.equalsIgnoreCase("Xpath")) 	// for x-path
		{
			return By.xpath(locator);
		}else if(property.equalsIgnoreCase("Id"))	// for id
		{
			return By.id(locator);
		}else if(property.equalsIgnoreCase("ClassName"))	// for classname
		{
			return By.className(locator);
		}else if(property.equalsIgnoreCase("LinkText"))	// for link text
		{
			return By.linkText(locator);
		}else if(property.equalsIgnoreCase("PartialLinkText"))	//for partial link text
		{
			return By.partialLinkText(locator);
		}else if(property.equalsIgnoreCase("CssSelector"))	// for CssSelector
		{
			return By.cssSelector(locator);
		}else if(property.equalsIgnoreCase("TagName"))	// for tag name
		{
			return By.tagName(locator);
		}
		else
		{
				return By.xpath(locator);
		}
	}
	
}

