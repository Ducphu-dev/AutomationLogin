import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.util.List;
import java.util.Properties;
import java.util.concurrent.TimeUnit;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.util.Properties;
import java.util.concurrent.TimeUnit;
import org.apache.poi.xssf.usermodel.XSSFCell;
import org.apache.poi.xssf.usermodel.XSSFRow;
import org.apache.poi.xssf.usermodel.XSSFSheet;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;

import org.openqa.selenium.*;
import org.openqa.selenium.chrome.*;
import org.openqa.selenium.support.ui.ExpectedCondition;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.Select;
import org.openqa.selenium.support.ui.WebDriverWait;

/**
 * Hello world!
 *
 */
public class chototAutomate {
	private static Properties prop = new Properties();
	private static XSSFWorkbook book;
	private static XSSFSheet sheet;
	private static XSSFCell cell;

	public static void main(String[] args) throws InterruptedException, FileNotFoundException, IOException {

//		prop.load(new FileInputStream("F:\\Java\\SeleExm\\src\\application.properties"));
		System.setProperty("webdriver.chrome.driver", /* => */ "F:\\Java\\SeleExm\\src\\chromedriver.exe");
		WebDriver driver = new ChromeDriver();

		driver.manage().timeouts().pageLoadTimeout(30, TimeUnit.SECONDS);
		driver.manage().timeouts().implicitlyWait(20, TimeUnit.SECONDS);
		driver.manage().window().maximize();
		driver.manage().deleteAllCookies();
		driver.get("https://accounts.chotot.com/login?continue=https%3A%2F%2Fwww.chotot.com%2F");

		FileInputStream fis = new FileInputStream(new File("F:\\Java\\SeleExm\\Data.xlsx"));
		book = new XSSFWorkbook(fis);
		sheet = book.getSheetAt(0);

		WebElement
		
// 		--> start resigner
		
		//	username
		account = driver.findElement(By.xpath("//form//input[@type='tel']"));
			cell = sheet.getRow(1).getCell(0); // get username from row 1 and cell 0
		account.sendKeys(cell.getStringCellValue());
		
		//	password
		account = driver.findElement(By.xpath("//form//input[@type='password']"));
			cell = sheet.getRow(1).getCell(1); // get password from row 1 and cell 1
		account.sendKeys(cell.getStringCellValue());

		//	submit
		driver.findElement(By.cssSelector("button[type='submit']")).sendKeys(Keys.ENTER);
		Thread.sleep(3000); //wait for 3 secound
		
//		 <-- end

		WebDriverWait wait = new WebDriverWait(driver, 10);

//		--> start find log out
		
		driver.findElement(By.cssSelector("div[class='appWrapper-Header-menuMore-menuWrapper']")).click();
		Thread.sleep(2000);
		List<WebElement> option = driver.findElements(By.cssSelector("div[class='appWrapper-Header-menu'] > div"));
		for (WebElement e : option) {
			if (e.getText().equalsIgnoreCase("Đăng xuất")) {
				e.click();

			}
		}
		
//		<-- end
		Thread.sleep(3000);

		driver.findElement(By.linkText("Đăng nhập")).click();
		driver.findElement(By.cssSelector("button[class='btn btn-success']")).click();
		Thread.sleep(3000);

		account = driver.findElement(By.xpath("//form//input[@type='tel']"));
		account.sendKeys(prop.getProperty("username"));
//		    pass
		account = driver.findElement(By.xpath("//form//input[@type='password']"));
		account.sendKeys(prop.getProperty("password"));
		Thread.sleep(3000);

		driver.findElement(By.cssSelector("button[type='submit']")).sendKeys(Keys.ENTER);
		Thread.sleep(3000);


	    driver.close();
	    driver.quit();
	}
}
