package Pages;

import Utilities.CommonDriver;
import Utilities.WaitHelpers;
import dev.failsafe.internal.util.Assert;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;

import static org.testng.Assert.assertNotEquals;


public class TMPage
{
    public void CreateTimeRecord(WebDriver driver, String code, String description, String price) throws InterruptedException {
        // WebDriverWait webDriverWait = new WebDriverWait(driver, TimeSpan.FromSeconds(10));
        //webDriverWait.Until(SeleniumExtras.WaitHelpers.ExpectedConditions.ElementIsVisible(By.XPath("//*[@id=\"container\"]/p/a")));
        WaitHelpers.WaitToBeClickabel(driver, "Xpath", "//*[@id=\"container\"]/p/a", 5);

        //Click on the Create new button for creating a new record in the time & materials module
        WebElement createnewbutton = driver.findElement(By.xpath("//a[contains(text(),'Create New')]"));
        createnewbutton.click();

        //Select the type code

        WebElement tnmoption = driver.findElement(By.xpath("//*[@id=\"TimeMaterialEditForm\"]/div/div[1]/div/span[1]/span"));
        tnmoption.click();
        Thread.sleep(1000);

        WebElement tmoption = driver.findElement(By.xpath("//*[@id=\"TypeCode_listbox\"]/li[2]"));
        tmoption.click();

        //Enter the code in the Code textbox
        WebElement codetxtbox = driver.findElement(By.id("Code"));
        WaitHelpers.WaitToBeClickabel(driver, "Id", "Code", 7);
        codetxtbox.sendKeys(code);

        // Enter description in the description textbox
        WaitHelpers.WaitToBeVisible(driver, "Id", "Description", 7);
        WebElement descriptiontxtbox = driver.findElement(By.id("Description"));
        descriptiontxtbox.sendKeys(description);


        //Enter the price per unit for the new record
        WebElement pricetxtbox = driver.findElement(By.xpath("//*[@id=\"TimeMaterialEditForm\"]/div/div[4]/div/span[1]/span/input[1]"));
        pricetxtbox.sendKeys(price);

        //Click on the Save button
        WebElement savebutton = driver.findElement(By.id("SaveButton"));
        savebutton.click();

        Thread.sleep(4000);
    }

    public void verifyCreatedRecord( WebDriver driver, String code)
    {
        //Check whether the new record is created successfully
        WebElement lastpagebutton = driver.findElement(By.xpath("//span[contains(text(),'Go to the last page')]"));
        lastpagebutton.click();

        WebElement newrecord = driver.findElement(By.xpath("//*[@id=\"tmsGrid\"]/div[3]/table/tbody/tr[last()]/td[1]"));

        Assert.isTrue(newrecord.getText().contains(code) , "No record found");




    }



    public void EditTimeRecord(WebDriver driver , String updatedcode, String updateddescription, String updatedprice) throws InterruptedException {
        // Go to last Page
        WebElement lastpagebutton = driver.findElement(By.xpath("//span[contains(text(),'Go to the last page')]"));
        lastpagebutton.click();

        //Edit the new created record
        WebElement editbutton = driver.findElement(By.xpath("//*[@id=\"tmsGrid\"]/div[3]/table/tbody/tr[last()]/td[5]/a[1]"));
        editbutton.click();

        //Edit the type code to material from time
        WebElement editTypecode = driver.findElement(By.xpath("//*[@id=\"TimeMaterialEditForm\"]/div/div[1]/div/span[1]/span/span[1]"));
        editTypecode.click();
        Thread.sleep(1000);

        WebElement edit1Typecode = driver.findElement(By.xpath("//*[@id=\"TypeCode_listbox\"]/li[1]"));
        edit1Typecode.click();

        //Edit the code textbox
        WebElement editcodetextbox = driver.findElement(By.id("Code"));
        editcodetextbox.clear();
        editcodetextbox.sendKeys(updatedcode);

        //Edit the description textbox
        WebElement editdescriptiontextbox = driver.findElement(By.id("Description"));
        editdescriptiontextbox.clear();
        editdescriptiontextbox.sendKeys(updateddescription);
        Thread.sleep(2000);

        //Edit price textbox
        WebElement editpriceoverlappingtag = driver.findElement(By.xpath("//*[@id=\"TimeMaterialEditForm\"]/div/div[4]/div/span[1]/span/input[1]"));
        WebElement editpricetextbox = driver.findElement(By.id("Price"));
        editpriceoverlappingtag.click();
        editpricetextbox.clear();
        editpriceoverlappingtag.click();

        editpricetextbox.sendKeys(updatedprice);

        //Download the edited record
        WebElement downloadbutton = driver.findElement(By.id("downloadButton"));
        downloadbutton.click();

        //Save the edited record
        WebElement editsavebutton = driver.findElement(By.id("SaveButton"));
        editsavebutton.click();

        Thread.sleep(4000);
    }

    public void verifyUpdatedRecord(WebDriver driver, String updatedcode)
    {

        //Check if the last record is edited successfully
        WebElement endpagebutton = driver.findElement(By.xpath("//span[contains(text(),'Go to the last page')]"));
        endpagebutton.click();

        WebElement editedrecord = driver.findElement(By.xpath("//*[@id=\"tmsGrid\"]/div[3]/table/tbody/tr[last()]/td[1]"));

        Assert.isTrue(editedrecord.getText().contains(updatedcode) , "record has not been edited");

    }





    public void DeleteTimeRecord(WebDriver driver) throws InterruptedException {
        WaitHelpers.WaitToBeClickabel(driver, "Xpath", "//span[contains(text(),'Go to the last page')]", 5);
        // Go to last Page
        WebElement lastpagebutton = driver.findElement(By.xpath("//span[contains(text(),'Go to the last page')]"));
        lastpagebutton.click();

        //Delete the last created record
        WebElement deletebutton = driver.findElement(By.xpath("//*[@id=\"tmsGrid\"]/div[3]/table/tbody/tr[last()]/td[5]/a[2]"));
        deletebutton.click();
        driver.switchTo().alert().accept();

        //Check if the record is deleted
        WebElement endpgbutton = driver.findElement(By.xpath("//*[@id=\"tmsGrid\"]/div[4]/a[4]/span"));
        endpgbutton.click();

        Thread.sleep(2000);
    }

    public void verifyDeletedRecord(WebDriver driver)
    {
        WebElement deletedrecord = driver.findElement(By.xpath("//*[@id=\"tmsGrid\"]/div[3]/table/tbody/tr[last()]/td[1]"));


                assertNotEquals(deletedrecord.getText(),("Updated Code"), " Record hasn't been deleted");


    }
}
