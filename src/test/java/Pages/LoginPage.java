package Pages;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;

public class LoginPage
{
    public void LoginActions(WebDriver driver)
    {
        driver.manage().window().maximize();

        //Launch turnup portal and navigate to login page
        driver.navigate().to("http://horse.industryconnect.io/Account/Login");

        //Identify Username textbox and enter valid username
        WebElement usernametxtbox = driver.findElement(By.id("UserName"));
        usernametxtbox.sendKeys("hari");

        //Identify password textbox and enter valid password
        WebElement passwordtxtbox = driver.findElement(By.id("Password"));
        passwordtxtbox.sendKeys("123123");

        //Identify login button and click on the button
        WebElement loginbutton = driver.findElement(By.xpath("//*[@id=\"loginForm\"]/form/div[3]/input[1]"));
        loginbutton.click();
    }
}
