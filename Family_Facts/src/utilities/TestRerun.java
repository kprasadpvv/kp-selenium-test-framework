package utilities;

import org.apache.log4j.Logger;
import org.testng.IRetryAnalyzer;
import org.testng.ITestResult;

import base.Base;

public class TestRerun implements IRetryAnalyzer
{
	private int count = 0; 
	private int maxCount = 1; // set your count to re-run test
	protected Logger log=Base.log;
	
	@Override 
	public boolean retry(ITestResult result) { 
	    log.info("running retry logic for  '" 
	            + result.getName() 
	            + "' on class " + this.getClass().getName() );
	    
	    
	        if(count < maxCount) 
	        {                     
	                count++;                                    
	                return true; 
	        } 
	        return false; 
	}
	
}
