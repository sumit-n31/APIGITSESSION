package com;

import java.io.IOException;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.testng.Assert;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.BeforeTest;
import org.testng.annotations.DataProvider;
import org.testng.annotations.Test;

public class DataDrivenTest {

	ChromeDriver driver;

	@BeforeMethod
	public void setUp() {
		System.setProperty("webdriver.chrome.driver",
				"C:\\Users\\admin\\Documents\\Jars\\New folder (3)\\chromedriver.exe");
//		WebDriverManager.chromedriver().setup();
		driver = new ChromeDriver();
		driver.manage().window().maximize();
	}

	@Test(dataProvider = "LoginData")
	public void logintest(String user, String pass, String exp) {
		driver.get("https://github.com/");
		driver.findElement(By.cssSelector("div.position-relative.mr-3.mb-4.mb-lg-0.d-inline-block>a")).click();
		WebElement login = driver.findElement(By.cssSelector("input#login_field"));
		login.clear();
		login.sendKeys(user);

		WebElement password = driver.findElement(By.cssSelector("input#password"));
		password.clear();
		password.sendKeys(pass);

		driver.findElement(By.name("commit")).click();

		String expec = "https://github.com/";
		String act = driver.getCurrentUrl();
		System.out.println(act);

		if (exp.equalsIgnoreCase("valid")) {
			if (expec.equalsIgnoreCase(act)) {
				driver.findElement(By.cssSelector("span.feature-preview-indicator.js-feature-preview-indicator+span"))
						.click();
//				driver.findElement(By.xpath("//button[@class='dropdown-item dropdown-signout']")).click();
				Assert.assertTrue(true);
			} else {
				Assert.assertTrue(false);
			}
		} else if (exp.equalsIgnoreCase("Invalid")) {
			if (expec.equalsIgnoreCase(act)) {
				driver.findElement(By.cssSelector("span.feature-preview-indicator.js-feature-preview-indicator+span"))
						.click();
				driver.findElement(By.cssSelector("button.dropdown-item.dropdown-signout")).click();
				Assert.assertTrue(false);
			} else {
				Assert.assertTrue(true);
			}

		}

		System.out.println(user + "\t" + "\t" + pass + "\t" + exp);
	}

	@DataProvider(name = "LoginData")
	public void getData() throws IOException {
//		String[][] logindata = { 
//				{ "srn311@gmail.com", "crucia@wc2021", "Invalid" }, 
//				{ "sr311@gmail.com", "cruca@wc2021", "Invalid" }, 
//				{ "srn3112@gmail.com", "crucial@wc2021", "valid" },
//				};
//		String path = "C:\\Users\\admin\\Documents\\Daisy 21\\DatadrivenTest\\Data\\LoginData.xlsx";
//		int row = XLUtils.getRowCount(path, "Sheet1");
//		int colnum = XLUtils.getCellCount(path, "Sheet1", row);
//		System.out.println(row);
//		System.out.println(colnum);

//		String[][] logindata = new String[row][colnum];
//		for (int i = 1; i <= row; i++) {
//			for (int j = 0; j < colnum; j++) {
//				logindata[i - 1][j] = XLUtils.getCellData(path, "Sheet1", i, j);
//			}
//		}
//
//		return logindata;
	}

	@AfterMethod
	public void tearDown() {
		driver.close();

	}
}
