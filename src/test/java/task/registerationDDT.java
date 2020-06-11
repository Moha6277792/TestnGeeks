package task;

import java.io.IOException;
import java.util.concurrent.TimeUnit;

import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.support.ui.Select;
import org.testng.annotations.BeforeTest;
import org.testng.annotations.DataProvider;
import org.testng.annotations.Test;

import data.ExcelReader;

public class registerationDDT  {
	ChromeDriver driver;
    
    
    @DataProvider (name="ExcelData")
    public Object[][] userregistData () throws IOException {
    	
    	ExcelReader ER = new ExcelReader();
    	return ER.getExcelData();
    }
    @BeforeTest
    public void opensite () {
    	System.setProperty("webdriver.chrome.driver", System.getProperty("user.dir") + "/resources/chromedriver.exe" );
		driver = new ChromeDriver();
		driver.navigate().to("https://zid.sa");
		driver.manage().window().maximize();
    }

	@Test (priority=1,dataProvider="ExcelData")
	public void registerSuccesfully (String fullname , String email,String pass_word, String mobnum,String storeengname, String storearabname) {
		
		WebElement pageclick = driver.findElement(By.cssSelector("section.zid-hero")); 
		pageclick.click();
		
		WebElement registerbtn = driver.findElement(By.className("a--white")); 
		registerbtn.click();
		
		driver.manage().timeouts().implicitlyWait(10, TimeUnit.SECONDS);
		WebElement nameTxt = driver.findElement(By.id("name")); 
		nameTxt.sendKeys(fullname);
		WebElement mobilenum = driver.findElement(By.id("mobile")); 
		mobilenum.sendKeys(mobnum);
		WebElement mailTxt = driver.findElement(By.id("registration_email")); 
		mailTxt.sendKeys(email);
		WebElement password = driver.findElement(By.id("registration_password")); 
		password.sendKeys(pass_word);
		WebElement next = driver.findElement(By.cssSelector("button.btn.next-tab-btn")); 
		next.click();
		
		
		WebElement storename = driver.findElement(By.id("store_username")); 
		storename.sendKeys(storeengname);
		driver.manage().timeouts().implicitlyWait(2, TimeUnit.SECONDS);
		WebElement storenamearabic = driver.findElement(By.id("store_name")); 
		storenamearabic.sendKeys(storearabname);
		WebElement categorylist = driver.findElement(By.id("store_category_id"));
		Select category = new Select(categorylist);
		category.selectByVisibleText("إلكترونيات");
		WebElement accepttrade = driver.findElement(By.id("started_business_before_registration_yes")); 
		accepttrade.click();
		WebElement submitregist = driver.findElement(By.cssSelector("button.btn.btn-primary.login-btn")); 
		submitregist.click();
		
		WebElement logout = driver.findElement(By.xpath("//*[@id=\"page-wrapper\"]/div[1]/nav/ul/li/a")); 
		logout.click();
		
		WebElement loginuser = driver.findElement(By.id("email")); 
		loginuser.sendKeys(email);
		WebElement loginpassword = driver.findElement(By.id("password")); 
		loginpassword.sendKeys(pass_word);
		WebElement loginbutton = driver.findElement(By.cssSelector("button.btn.btn-primary.login-btn")); 
		loginbutton.click();
	}
	
}
