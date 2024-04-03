package OnlineShoppingAutomation;

import java.io.IOException;
import java.time.Duration;
import java.util.Scanner;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.edge.EdgeDriver;

public class DriverSetup {
	public static WebDriver driver;
	
	public static WebDriver getChromeWebDriver() {
		
			driver = new ChromeDriver();
			driver.get("https://www.flipkart.com/");

			driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(5));
			driver.manage().window().maximize();
			return driver;
				
		}

			public  static WebDriver getEdgeWebDriver() {
			driver = new EdgeDriver();
			driver.get("https://www.flipkart.com/");
			driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(5));
			driver.manage().window().maximize();
			return driver;
		}
	
	public static void main(String[] args) throws IOException {
		// TODO Auto-generated method stub
		
		AutomationScript as=new AutomationScript();
		for(int i=0;i<2;i++) {
			if(i==0) {
				as.createChromeDriver();
			}
			else {
				as.createEdgeDriver();
			}
		
		as.automationTest();
		}
	}

}
