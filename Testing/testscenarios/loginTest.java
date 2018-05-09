package testscenarios;

import org.junit.Test;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;

public class loginTest {
	
	@Test
	public void correct_login() {
		WebDriver driver= new ChromeDriver();
		driver.get("http://localhost:8080/index");
		driver.findElement(By.name("name")).clear();
		driver.findElement(By.name("name")).sendKeys("");
		driver.findElement(By.name("password")).clear();
		driver.findElement(By.name("password")).sendKeys("");
		driver.findElement(By.className("myButton")).click();

		
	}
	

	
}
