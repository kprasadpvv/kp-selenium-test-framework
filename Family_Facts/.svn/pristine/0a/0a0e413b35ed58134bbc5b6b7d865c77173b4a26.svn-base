package testCases;

import keyWords.ScriptExecuter;

import org.testng.annotations.AfterClass;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Test;

import base.Base;

public class FamilyFactsRegistered extends Base
{
	ScriptExecuter script;
	@BeforeClass
	public void scripExecuterIntializer()
	{
		script= new ScriptExecuter();
	}
	
	@Test
	public void module2tc1()
	{
		log.info("Started Executing TC1 of Module2:"+script);
		script.execute(".\\Test_Data\\FamilyFacts.xls","Registered_TC_1","Family_Facts_Registered");
	}
	
	@AfterClass
	public void clearcookies()
	{
		log.info("Deleting all Cookies");
		driver.manage().deleteAllCookies();
		
	}
}
