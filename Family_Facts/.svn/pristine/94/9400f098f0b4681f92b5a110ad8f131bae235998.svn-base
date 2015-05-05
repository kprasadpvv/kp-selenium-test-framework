package keyWords;

import org.apache.log4j.Logger;

import base.Base;

import Data_Reader.GetData;

public class ScriptExecuter 
{

	String action,property, locator, testdata,expected,senarioDescription;
	GetData data = new GetData();
	Logger log=Base.log;
	KeywordHandler handler= new KeywordHandler();
	
	public void execute(String filepath,String sheetname, String modulename)
	{
		String dataSet[][]=data.getData(filepath,sheetname);
		
		
		for(int row=0;row<dataSet.length;row++)
		{
			for(int col=0;col<7;col++)
			{
				senarioDescription=dataSet[row][0];
				action= dataSet[row][1];
				property = dataSet[row][2];
				locator = dataSet[row][3];
				testdata= dataSet[row][4];
				expected = dataSet[row][5];
				
			}
			log.info("Text retrived from the sheet are Action:" +action+""+property+" "+locator+""+testdata+ ""+expected +" "+senarioDescription);
			
			handler.keywordhandler(action, property, locator, testdata, expected,modulename,sheetname,senarioDescription);
		}
		
		
	}
}
