package Pages;
import dev.failsafe.internal.util.Assert;
import Utilities.CommonDriver;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;

public class HomePage extends CommonDriver
{

    public void GoToTMPage(WebDriver driver)
    {
        try
        {
            //Navigate to the administration dropdown

            WebElement administrationdropdown = driver.findElement(By.xpath("/html/body/div[3]/div/div/ul/li[5]/a"));
            administrationdropdown.click();
        }
        catch(Exception exception)
        {
            Assert.isTrue(1 == 0,"Turnup portal is not available");
        }

        //Select time & materials module

        WebElement timematerialoption = driver.findElement(By.xpath("/html/body/div[3]/div/div/ul/li[5]/ul/li[3]/a"));
        timematerialoption.click();
    }

    public void VerifyUserLogin(WebDriver driver)
    {
        //Verify if the user has logged in successfully
        WebElement helloHari = driver.findElement(By.xpath("//*[@id=\"logoutForm\"]/ul/li/a"));

        Assert.isTrue(helloHari.getText().contains("Hello hari!"), "Invalid Username/Password");





    }
}
