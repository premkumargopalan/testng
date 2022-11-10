package com.ng.stepdef;

import java.io.File;
import java.io.FileOutputStream;
import java.util.ArrayList;
import java.util.List;
import java.util.Set;

import org.apache.poi.ss.usermodel.Cell;
import org.apache.poi.ss.usermodel.Row;
import org.apache.poi.ss.usermodel.Sheet;
import org.apache.poi.ss.usermodel.Workbook;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.testng.annotations.AfterClass;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.DataProvider;
import org.testng.annotations.Test;

import io.github.bonigarcia.wdm.WebDriverManager;

public class TestNg {
	@DataProvider
	public Object[][] prem(){
		return  new Object[][] {{"hello"}};
	}
    @BeforeClass 
	public void beforeclass() {
System.out.println("before class");
	}
     @AfterClass
	public void afterclass() {
System.out.println("after class");
	}
     @BeforeMethod
	public void beforeMethod() {
		System.out.println("before method");
	}
     @AfterMethod
	public void afterMethod() {
         System.out.println(" After method");
	}
      static WebDriver driver;
	private Set<String> wd;
     @Test (priority = 1,dataProvider ="prem")
	public void test1(String x) {
System.out.println(x);
  WebDriverManager.chromedriver().setup();
    driver =new ChromeDriver();
    driver.get("https://www.Flipkart.com/");
    driver.findElement(By.xpath("//button[text()='✕']")).click();

	}
     
     @Test  (priority = 2)
	public void test2() throws Throwable {
       System.out.println("mobile");
       driver.findElement(By.xpath("//input[@class='_3704LK']")).sendKeys("Redmi");
       driver.findElement(By.xpath("//button[@type='submit']")).click();
       Thread.sleep(3000);
       File f = new File("C:\\Users\\Admin\\eclipse-workspace\\Testng\\target\\Redmi.xlsx");
       Workbook w = new XSSFWorkbook();
       Sheet s = w.createSheet("moblieList");
       List<WebElement> mobiles = driver.findElements(By.xpath("//div[@class='_4rR01T']"));
       for (int i = 0; i < mobiles.size(); i++) {
		  WebElement mobile = mobiles.get(i);
		   String text = mobile.getText();
		      Row r = s.createRow(i);
		        Cell c = r.createCell(0);
		        c.setCellValue(text);
	}
       FileOutputStream o = new  FileOutputStream(f);
       w.write(o);
       o.close();
       
       

	}
     @Test (priority = 3)
	public  void test3() {
    System.out.println("window handling");
	driver.findElement(By.xpath("(//div[@class='_4rR01T'])[3]")).click();
	wd = driver.getWindowHandles();
	List<String> li =new ArrayList(wd);
	driver.switchTo().window(li.get(1));
	
	WebElement fi = driver.findElement(By.xpath("//div[@class='_30jeq3 _16Jk6d']"));
               String text = fi.getText();
               System.out.println(text);
    
	}
     @Test(priority = 4)
	private void test4() {
    	
     System.out.println("vallidation");
      List<WebElement> tab = driver.findElements(By.tagName("table"));
         for (int i = 0; i < tab.size(); i++) {
			WebElement wa = tab.get(i);
			WebElement tb = driver.findElement(By.tagName("tbody"));
			 List<WebElement> Tr = driver.findElements(By.tagName("tr"));
			 for (int j = 0; j < Tr.size(); j++) {
				 WebElement we = Tr.get(j);
				 List<WebElement> td = driver.findElements(By.tagName("td"));
				 for (int k = 0; k < td.size(); k++) {
					WebElement ts = td.get(k);
					String text = ts.getText();
					System.out.println(text);
				}break;
			}
		}
	}
	
}
