package OnlineShoppingAutomation;


import java.io.FileInputStream;
import java.io.FileOutputStream;
//import java.io.FileNotFoundException;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.util.Set;

import org.apache.poi.xssf.usermodel.XSSFCell;
import org.apache.poi.xssf.usermodel.XSSFRow;
import org.apache.poi.xssf.usermodel.XSSFSheet;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;
import org.openqa.selenium.By;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;

public class AutomationScript {
	public static WebDriver driver;
	
	public void createChromeDriver() {
		driver = DriverSetup.getChromeWebDriver();
	}
		

    public void createEdgeDriver() {
			driver = DriverSetup.getEdgeWebDriver();
	}
	public void automationTest() throws IOException {

	//		Searching for "Home Appliances" using Locator 'name' 
	//driver.findElement(By.name("q")).sendKeys("Home appliances"); // using cssSelector (By.cssSelector("input._3704LK"))
		
		
//		
		FileInputStream file = new FileInputStream(System.getProperty("user.dir")+"\\ExcelSheetData\\inputdata.xlsx");
		
		XSSFWorkbook workbook = new XSSFWorkbook(file);
		XSSFSheet sheet=workbook.getSheet("sheet1");
		XSSFRow row = sheet.getRow(0);
		XSSFCell value =row.getCell(1);
		
		driver.findElement(By.name("q")).sendKeys(value.toString());
//		driver.findElement(By.cssSelector("input._3704LK")).sendKeys(value.toString());
	
		
		workbook.close();
		file.close();
	
	
	
	
	// Clicking on the search button to search for "Home Appliances"
	driver.findElement(By.xpath("//button[@type='submit']")).click();  //driver.findElement(By.cssSelector("Button._2iLD__")).click(); 
		
	// Clicking on the first item displayed in the page.
	driver.findElement(By.cssSelector("#container > div > div._36fx1h._6t1WkM._3HqJxg > div._1YokD2._2GoDe3 > div:nth-child(2) > div:nth-child(2) > div > div:nth-child(1) > div > a.s1Q9rs")).click();

	// Capturing window ID's for browsers because its impossible to switch from one window to another window without using window Id
	Set<String> windowid = driver.getWindowHandles();
	
	//      We can't retrieve individual window Id because set doesn't follow indexing .So we need to convert SET to LIST.
	List<String> winlist = new ArrayList<String>(windowid);
	String parentid = winlist.get(0);
	String childid = winlist.get(1);
	
	// Switching the driver to child browser using child window ID by switchTo() method.
	driver.switchTo().window(childid);
	
	// Adding the first item displayed on page by scrolling down the page and clicking on the "ADD TO CART".
	try {
		WebElement ele = driver.findElement(By.cssSelector("button._2KpZ6l"));
		((JavascriptExecutor) driver).executeScript("arguments[0].scrollIntoView(true);", ele);
		ele.click();
	}catch(Exception e) {
		System.out.println("Here page was not displayed due to network issue.");
		try {
			if(driver.findElement((By.cssSelector("body > h1"))).isDisplayed()){
				driver.navigate().refresh();
			}
		}catch(Exception ex) {
			
		}
	}
	
	WebElement ele = driver.findElement(By.cssSelector("button._2KpZ6l"));
	((JavascriptExecutor) driver).executeScript("arguments[0].scrollIntoView(true);", ele);
	ele.click();
	
	// Printing the order price amount.
	String price = driver.findElement(By.cssSelector("#container > div > div._1Er18h > div > div > div:nth-child(1) > div > div:nth-child(2) > div > div._2nQDXZ > div._3fSRat > span._2-ut7f._1WpvJ7\r\n")).getText();
	System.out.println("order amount : "+price);
	
	// Converting price from string to integer.
		String price1=price.substring(1);
		String p = price1.replace(",","");
		int pri = Integer.parseInt(p);
	
	//Writing the output in Excel sheet.
	FileOutputStream file1=new FileOutputStream(System.getProperty("user.dir")+"\\ExcelSheetData\\outputdata.xlsx");
	XSSFWorkbook workbook1=new XSSFWorkbook();
	XSSFSheet sheet1=workbook1.createSheet("data1");
	
	XSSFRow row1= sheet1.createRow(0);
	
	XSSFCell value1 =row1.createCell(0);
	value1.setCellValue("order amount "+price);

	//  Closing the child window.
	driver.close();
	
	//  Switching to parent window using parent windowID.
	driver.switchTo().window(parentid);
	
	//   Adding another item to cart
	driver.findElement(By.cssSelector("#container > div > div._36fx1h._6t1WkM._3HqJxg > div._1YokD2._2GoDe3 > div:nth-child(2) > div:nth-child(2) > div > div:nth-child(4) > div > a.s1Q9rs")).click();
    
	// Retrieving window's ID's 	
	windowid= driver.getWindowHandles();
	winlist = new ArrayList<String>(windowid);
	parentid = winlist.get(0);
	childid = winlist.get(1);
	
	// switching driver to child window using child window ID 
	driver.switchTo().window(childid);
	
	// adding second item to cart.
	try {
		WebElement elem = driver.findElement(By.cssSelector("button._2KpZ6l"));
		((JavascriptExecutor) driver).executeScript("arguments[0].scrollIntoView(true);", elem);
		elem.click();
	}catch(Exception e) {
		System.out.println("Here page was not displayed due to network issue.");
		try {
			if(driver.findElement((By.cssSelector("body > h1"))).isDisplayed()){
				driver.navigate().refresh();
			}
		}catch(Exception ex) {
			
		}
	}
	
	WebElement elem = driver.findElement(By.cssSelector("button._2KpZ6l"));
	((JavascriptExecutor) driver).executeScript("arguments[0].scrollIntoView(true);", elem);
	elem.click();
	
	//Printing revised order amount.
	String revisedprice = driver.findElement(By.cssSelector("#container > div > div._1Er18h > div > div > div._1YokD2._3Mn1Gg.col-4-12._78xt5Y > div._1AtVbE.col-12-12 > div > div > div > div._3LxTgx > div > div.z4Ha90 > span > div > div > div.z4Ha90 > span")).getText();
	System.out.println("Revised order amount : "+revisedprice);
	
	// Converting revised order amount from string to integer.
	String revisedprice1=revisedprice.substring(1);
	String rp = revisedprice1.replace(",","");
	int rpr = Integer.parseInt(rp);
	
	//For revised order amount output in ExcelSheet.
	XSSFRow row2=sheet1.createRow(1);
    XSSFCell value2=row2.createCell(0);
	value2.setCellValue("revised order amount "+revisedprice);
	
	workbook1.write(file1);
	workbook1.close();
	file1.close();
	
	// Retrieving amount of second order.
	String secondprice = driver.findElement(By.cssSelector("#container > div > div._1Er18h > div > div > div:nth-child(1) > div > div:nth-child(2) > div > div._2nQDXZ > div._3fSRat > span._2-ut7f._1WpvJ7")).getText();
    
	// Converting second price from string to integer.
	String secondprice1=secondprice.substring(1);
	String sp = secondprice1.replace(",","");
	int spri = Integer.parseInt(sp);
	
	// Adding the first ordered item price and second ordered price.
	int total=pri+spri;
	
	// Validating the amount calculated is properly calculated or not.
	if(rpr==total) {
		System.out.println("Properly calculated");	
	}else {
		System.out.println("Not properly calculated");
	}
	driver.quit();
	}
}
