package testscenarios;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;

public class signupTest {
	public void correct_login() {
		WebDriver driver= new ChromeDriver();
		driver.get("http://localhost:8080/index");
		driver.findElement(By.name("username")).clear();
		driver.findElement(By.name("username")).sendKeys("Ahmed");
		driver.findElement(By.name("pass1")).clear();
		driver.findElement(By.name("pass1")).sendKeys("Mohamed");
		driver.findElement(By.name("pass2")).clear();
		driver.findElement(By.name("pass2")).sendKeys("");
		driver.findElement(By.name("type")).clear();
		driver.findElement(By.name("type")).sendKeys("");
		driver.findElement(By.className("myButton")).click();


		
	}
}
