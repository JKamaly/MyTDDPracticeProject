package tek.tdd.utility;


import com.google.common.eventbus.SubscriberExceptionContext;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;
import tek.tdd.base.BaseSetup;

import java.time.Duration;


public class SeleniumUtility extends BaseSetup {

    private static final Logger LOGGER = LogManager.getLogger(SeleniumUtility.class);

     private WebDriverWait getWait(){
         return  new WebDriverWait(getDriver(), Duration.ofSeconds(WAIT_TIME_IN_SECOND));
     }

     public String getElementText(WebElement element ){
        LOGGER.debug("Returning element text {}", element);
         return getWait().until(ExpectedConditions.visibilityOf(element)).getText();
     }
     public boolean isElementEnabled(WebElement element){
         LOGGER.debug("Checking element enabled status {} ", element);
         boolean isEnabled = getWait().until(ExpectedConditions.visibilityOf(element)).isEnabled();
         LOGGER.debug("Element is enabled status {} ", isEnabled);
         return isEnabled;
     }
     public void sendText(WebElement element, String text){
         LOGGER.debug("Sending text {} to element {}", text, element);
         WebElement targetElement = getWait().until(ExpectedConditions.visibilityOf(element));
         targetElement.click();
         targetElement.sendKeys(text);
     }
     public void clickOnElement(WebElement element){
         LOGGER.debug("Clicking on element {}", element);
         getWait().until(ExpectedConditions.elementToBeClickable(element)).click();
     }
     public boolean isElementDisplayed(WebElement element){
         LOGGER.debug("Checking for element isDisplayed {}", element);
        return getWait().until(ExpectedConditions.visibilityOf(element)).isDisplayed();

     }




}
