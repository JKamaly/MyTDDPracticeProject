package tek.tdd.base;

import com.aventstack.extentreports.MediaEntityBuilder;
import com.aventstack.extentreports.service.ExtentTestManager;
import com.aventstack.extentreports.testng.listener.ExtentIReporterSuiteListenerAdapter;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.openqa.selenium.OutputType;
import org.openqa.selenium.TakesScreenshot;
import org.testng.ITestResult;
import org.testng.annotations.*;
import tek.tdd.pages.AccountProfilePage;
import tek.tdd.pages.HomePage;
import tek.tdd.pages.SignUpPage;
import tek.tdd.pages.SingInPage;
import tek.tdd.utility.SeleniumUtility;

@Listeners({ExtentIReporterSuiteListenerAdapter.class})
public class UIBaseClass extends SeleniumUtility {

    private static final Logger LOGGER = LogManager.getLogger(UIBaseClass.class);

    public HomePage homePage;
    public AccountProfilePage accountProfilePage;
    public SignUpPage signUpPage;
    public SingInPage signInPage;

    @BeforeMethod
    public void setupTest(){
        LOGGER.info("Setup test and opening browser");
        setupBrowser();
        homePage = new HomePage();
        accountProfilePage = new AccountProfilePage();
        signUpPage = new SignUpPage();
        signInPage = new SingInPage();
    }

    public void testCleanUp(ITestResult result){
        if(result.getStatus()==ITestResult.FAILURE){
            TakesScreenshot screenshot = (TakesScreenshot) getDriver();
            String shot = screenshot.getScreenshotAs(OutputType.BASE64);

            ExtentTestManager.getTest().fail("Test failed, taking screen shot",
                    MediaEntityBuilder.createScreenCaptureFromBase64String(shot).build());
        }
        LOGGER.info("re-running after each test and quite browser");
        quitBrowser();
    }

    public void validCredentialSignIn() {
        clickOnElement(homePage.signInLink);
        signInPage.doSignIn("mohammad2536@gmail.com" , "Password@123");
    }








}
