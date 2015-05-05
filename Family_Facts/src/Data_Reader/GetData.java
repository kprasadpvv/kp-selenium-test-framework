package Data_Reader;

import java.io.FileInputStream;
import org.apache.poi.hssf.usermodel.HSSFSheet;
import org.apache.poi.hssf.usermodel.HSSFWorkbook;

public class GetData 
{

	public String[][] getData(String filePath,String sheetname)
	{
		String[][] datalist=null;
		try
		{
		FileInputStream file= new FileInputStream(filePath);
		HSSFWorkbook workBook = new HSSFWorkbook(file);
		HSSFSheet workSheet = workBook.getSheet(sheetname);
		
		int rowsize=workSheet.getLastRowNum();
		System.out.println("row size: "+rowsize);
		datalist= new String[rowsize][6];
        for(int row=1,rj=0;row<=rowsize;row++,rj++)
        {
           for(int col=1,cj=0;col<7;col++,cj++)
            {
            	try
            	{
            	 datalist[rj][cj]=workSheet.getRow(row).getCell(col).getStringCellValue();	
            	} 
            	catch(NullPointerException e)
            	{
            		datalist[rj][cj]=" ";
            	}
            	
            }
            
        }
		} catch(Exception  e)
			{e.printStackTrace();}
		
		
		return datalist;
	}
	
	
	public String getFilename (String filePath,String sheetname, int row,int col)
	{
		String returnValue=null;
		try
		{
			//String filePath="D:/Automation Script/Workspace/WebDriver_TestNG/Test Data/maniData.xls";
			FileInputStream file= new FileInputStream(filePath);
			HSSFWorkbook workBook = new HSSFWorkbook(file);
			HSSFSheet workSheet = workBook.getSheet(sheetname);
			returnValue= workSheet.getRow(row).getCell(col).getStringCellValue();
		}
			catch (Exception e)
			{
				e.printStackTrace();
			}
		return returnValue;
	}
}