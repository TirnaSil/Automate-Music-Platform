package SeleniumriseTune.RiseTune;

import java.time.Duration;
import java.util.List;
import java.util.Random;
import java.util.concurrent.TimeUnit;

import org.openqa.selenium.By;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

public class SignUp {

	private static WebDriver driver;
	
	
	
	// Method to scroll the web page by coordinates
	public void scroll(int x, int y) {
		JavascriptExecutor js = (JavascriptExecutor) driver;
		js.executeScript("window.scrollBy(arguments[0], arguments[1]);", x, y);
	}
    
	    
	
	    
	
	
	// Method to scroll to a specific element
	public static void scrollToElement(WebElement element) {
		JavascriptExecutor js = (JavascriptExecutor) driver;
		js.executeScript("arguments[0].scrollIntoView(true);", element);
	}

	
	
	public static void main(String[] args) throws InterruptedException {
		// TODO Auto-generated method stub
		WebDriver driver = new ChromeDriver();// Selenium manager
		driver.manage().timeouts().implicitlyWait(4, TimeUnit.SECONDS);
		driver.manage().window().maximize();

		driver.get("https://risetune.com/");
		System.out.println(driver.getTitle());
		System.out.println(driver.getCurrentUrl());

		driver.findElement(By.xpath("//a[@class='listen-btn'][normalize-space()='Sign Up']")).click();

		driver.findElement(By.id("firstName")).sendKeys("john");
		driver.findElement(By.id("lastName")).sendKeys("Roi");

		driver.findElement(By.id("email")).sendKeys("john@gmail.com");

		driver.findElement(By.id("password")).sendKeys("John@123");
		driver.findElement(By.xpath("//input[@name='ConfirmPassword']")).sendKeys("John@123");
		driver.findElement(By.id("select2-language_id-container")).click();
		driver.findElement(By.xpath("//input[@role='searchbox']")).sendKeys("Eng");

		driver.findElement(By.id("terms")).click();
		driver.findElement(By.id("taxNo")).click();

		// Initialize WebDriverWait and Random objects
        Thread.sleep(5000);
		WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(10));

		Random random = new Random();

		// Wait for the Country dropdown to be clickable and click it

		WebElement countryDropdown = wait
				.until(ExpectedConditions.elementToBeClickable(By.id("select2-country_id-container")));

		// Assuming you have implemented scrollToElement() method
		scrollToElement(countryDropdown);
		countryDropdown.click();

		// Get all country options (assuming the dropdown options are loaded after

		// click)

		WebElement countryList = wait.until(

				ExpectedConditions.visibilityOfElementLocated(By.xpath("//ul[@id='select2-country_id-results']")));

		List<WebElement> countryOptions = countryList.findElements(By.tagName("li"));

		// Select a random country

		int randomIndex = random.nextInt(countryOptions.size());

		String randomCountryName = countryOptions.get(randomIndex).getText();

		// Enter the random country name in the search box

		WebElement searchBox = wait

				.until(ExpectedConditions.visibilityOfElementLocated(By.xpath("//input[@role='searchbox']")));

		searchBox.sendKeys(randomCountryName);

		// Wait for the search result to be clickable and click it

		WebElement randomCountryOption = wait.until(ExpectedConditions
				.elementToBeClickable(By.xpath("//li[contains(text(), '" + randomCountryName + "')]")));

		randomCountryOption.click();

	}

}
