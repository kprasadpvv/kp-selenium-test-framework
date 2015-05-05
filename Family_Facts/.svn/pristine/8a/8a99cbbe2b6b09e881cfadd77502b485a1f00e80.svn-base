package Results;



import java.io.File;
import java.io.FileWriter;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.List;

import org.jdom2.Document;
import org.jdom2.Element;
import org.jdom2.input.SAXBuilder;
import org.jdom2.output.Format;
import org.jdom2.output.XMLOutputter;
import org.apache.commons.io.FileUtils;
import org.apache.log4j.Logger;

import base.Base;

public class ResultXml 
{
	Logger log = Base.log;
	Element testSuite = new Element("Test_Suite");
	SAXBuilder builder = new SAXBuilder();
	Document doc; 
	static String moduleName="firstvalue", caseName="firstvalue";

	static String folderTimeStamp = new SimpleDateFormat("ddMMMyyyy_HHmm").format(Calendar.getInstance().getTime());

	static String filePath =".\\Custom_Reports\\Result"+folderTimeStamp+"\\";
	String file=filePath+"ResultXml.xml";

	File xmlFile = new File(file);
	ScreenCapture scrObj = new ScreenCapture();

	public void createXml( String suiteName)
	{
		// Creating directory or Folder structure
		File f =new File(filePath);
		f.mkdirs();
		File screen= new File(filePath+"\\screens");
		screen.mkdir();
		/*File reportng_dir = new File(".\\output\\html");
		reportng_dir.mkdir();*/
		// Copying CSS file
		File source = new File(".\\Custom_Reports\\base.css");
		File dest = new File(filePath+"base.css");
		File jsfile = new File(".\\Custom_Reports\\script.js");
		File jsdest = new File(filePath+"script.js");

		// Creating XML file
		try 
		{
			FileUtils.copyFile(source, dest); // Copying CSS file by using Apache Fileutils
			FileUtils.copyFile(jsfile, jsdest);
			xmlFile.createNewFile();
			//************************* Get the System Properties **********************
			String user = System.getProperty("user.name");
			String os = System.getProperty("os.name");
			String version = System.getProperty("os.version");
			
			
			doc = new Document(testSuite);
			testSuite.setAttribute("Suite", suiteName);
			Element environment = new Element("Environment");
			testSuite.addContent(environment.setAttribute("userName",user).setAttribute("os",os).setAttribute("osVersion",version));
			XMLOutputter fmt = new XMLOutputter();
			fmt.setFormat(Format.getPrettyFormat());
			fmt.output(doc,new FileWriter(file));
			log.info("New Xml File is created");
		}
		catch( Exception e)
		{
			log.info("Error while creating XML and adding a Root node");
			e.printStackTrace();
		}
	}

/* ****************************************************
 * @param modulename,testcaseName,Scenario,Result,Screenshot path
 * @return null
*******************************************************/

	public void updateXml(String rmoduleName, String rcaseName, String scenario, String result, String screenpath )
	{
		
		try
		{
			doc=(Document) builder.build(xmlFile);
			Element root = doc.getRootElement();
			Element testModule = new Element("Module");
			List <Element> modules=root.getChildren("Module");
			int msize=modules.size();
			
			if(msize==0)
			{
				Element testCase = new Element("Test_Case");
				Element testScenario = new Element("Scenario");
				root.addContent(testModule.setAttribute("Module", rmoduleName));
				testModule.addContent(testCase.setAttribute("Name", rcaseName));
				testCase.addContent(testScenario.setAttribute("ScenarioDescription", scenario).setAttribute("Result", result).setAttribute("ScreenPath",screenpath));
			}
						
			else
			{
				for(int m=0;m<msize;m++)
				{
				if(modules.get(m).getAttributeValue("Module").equalsIgnoreCase(rmoduleName))
				{
				
					List <Element> testcases = modules.get(m).getChildren("Test_Case");
					int size=testcases.size();
					
					for(int i=0; i<size;i++)
					{
						if(testcases.get(i).getAttributeValue("Name").equalsIgnoreCase(rcaseName))
						{
							Element testScenario = new Element("Scenario");
							testcases.get(i).addContent(testScenario.setAttribute("ScenarioDescription", scenario).setAttribute("Result", result).setAttribute("ScreenPath",screenpath));
							break;
						}
						else if((i==size-1))
						{
							Element testcase = new Element("Test_Case");
							Element testScenario = new Element("Scenario");
							modules.get(m).addContent(testcase.setAttribute("Name", rcaseName));
							testcase.addContent(testScenario.setAttribute("ScenarioDescription", scenario).setAttribute("Result", result).setAttribute("ScreenPath",screenpath));
						}

					}
			}
					
			else if(m==msize-1)
			{
				Element testCase = new Element("Test_Case");
				Element testScenario = new Element("Scenario");
				root.addContent(testModule.setAttribute("Module", rmoduleName));
				testModule.addContent(testCase.setAttribute("Name", rcaseName).addContent(testScenario.setAttribute("ScenarioDescription", scenario).setAttribute("Result", result).setAttribute("ScreenPath",screenpath)));
			}
			}
			}
			
			XMLOutputter fmt = new XMLOutputter();
			fmt.setFormat(Format.getPrettyFormat());
			fmt.output(doc,new FileWriter(file));
			
		}catch( Exception e)
		{
			System.out.println("Error while Updating");
			e.printStackTrace();
		}
		
	}

	// Copy Log Files at the End to the newly created file path
	
	public void copyLog()
	{
		File source = new File(".\\Custom_Reports\\SeleniumLogs.log");
		File dest = new File(filePath+"SeleniumLogs.log");
		File htmlfile = new File(".\\Custom_Reports\\SeleniumLogs.html");
		File htmldest = new File(filePath+"SeleniumLogs.html");

		// Creating XML file
		try 
		{
			FileUtils.copyFile(source, dest); // Copying CSS file by using Apache Fileutils
			FileUtils.copyFile(htmlfile, htmldest);
			System.gc();
			
		}
		catch( Exception e)
		{
			System.out.println("Log File are not Copied to the new folders");
			e.printStackTrace();
		}
		
	}
	
	
	public void deleteLog()
	{
		File source = new File(".\\Custom_Reports\\SeleniumLogs.log");
		File htmlfile = new File(".\\Custom_Reports\\SeleniumLogs.html");
		source.delete();
		htmlfile.delete();
	}
	
}
