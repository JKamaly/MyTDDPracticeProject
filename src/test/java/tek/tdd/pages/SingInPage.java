package tek.tdd.pages;

import com.aventstack.extentreports.service.ExtentTestManager;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;
import tek.tdd.utility.SeleniumUtility;

public class SingInPage extends SeleniumUtility {

    public SingInPage(){
        PageFactory.initElements(getDriver(), this);
    }

    @FindBy(id = "signinLink")
    public WebElement signInLink;

    @FindBy(name = "email")
    public WebElement emailInputField;

    @FindBy(name = "password")
    public WebElement passwordInputField;

    @FindBy(id="loginBtn")
    public WebElement loginButton;


    public void doSignIn(String email, String password){
        ExtentTestManager.getTest()
                .info("Sign in with "+ email + " and "+ password);
        sendText(emailInputField, email);
        sendText(passwordInputField, password);
        clickOnElement(loginButton);
    }


}
