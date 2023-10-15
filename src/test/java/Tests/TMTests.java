package Tests;
import Pages.TMPage;
import Pages.HomePage;
import Pages.LoginPage;
import Utilities.CommonDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.testng.annotations.AfterTest;
import org.testng.annotations.BeforeTest;
import org.testng.annotations.Test;

public class TMTests extends CommonDriver
  {

    LoginPage loginPageObj = new LoginPage();
    HomePage homePageObj = new HomePage();
    TMPage tmPageObj = new TMPage();

    @BeforeTest
    public void TM_SetUp() throws InterruptedException
    {
    // open chrome browser
     driver =new ChromeDriver();
    Thread.sleep(1000);



    // Login page object initialization and definition

            loginPageObj.LoginActions(driver);

    // Home page object intialization and definition

            homePageObj.GoToTMPage(driver);

    }
        @Test(priority = 0, description = "This test checks if a user is able to create a new time record")
        public void CreateTime_Test() throws InterruptedException {
            // TM page object initialization and definition

            tmPageObj.CreateTimeRecord(driver, "New Code", "New description","200");
            tmPageObj.verifyCreatedRecord(driver, "New Code");
        }

        @Test(priority = 1, description = "This test checks if a user is able to edit an existing time record")
        public void EditTime_Test() throws InterruptedException {
            tmPageObj.EditTimeRecord(driver, "Updated Code", "updated description","100.21");
            tmPageObj.verifyUpdatedRecord(driver,"Updated Code");
        }

        @Test(priority = 2, description = "This test checks if a user is able to delete an existing time record")
        public void DeleteTime_Test() throws InterruptedException {
            tmPageObj.DeleteTimeRecord(driver);
            tmPageObj.verifyDeletedRecord(driver);
        }

        @AfterTest
        public void ClosingSteps()
        {
            driver.quit();
        }
    }

