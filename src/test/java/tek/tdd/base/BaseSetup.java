package tek.tdd.base;


import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.InputStream;
import java.time.Duration;
import java.util.Properties;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.chrome.ChromeOptions;
import org.openqa.selenium.edge.EdgeDriver;
import org.openqa.selenium.edge.EdgeOptions;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.openqa.selenium.firefox.FirefoxOptions;

public abstract class BaseSetup {

    private final static Logger LOGGER = LogManager.getLogger(BaseSetup.class);
    protected final static long WAIT_TIME_IN_SECOND = 25;
    private final Properties properties;
    private static WebDriver driver;

    public BaseSetup(){
        String configFilePath = System.getProperty("user.dir")+"/src/test/resources/configs/dev-config.properties";
      ///src/test/resources/configs/dev-config.properties
        try{
            LOGGER.debug("Reading config file {}", configFilePath);
            InputStream inputStream = new FileInputStream(configFilePath);
            properties = new Properties();
            properties.load(inputStream);
        }catch (IOException ioException){
            LOGGER.error("Config file error {}", ioException.getMessage());
            throw new RuntimeException("Cconfig file path error {}" + ioException.getMessage());
        }

    }
    public void setupBrowser(){

        String url = properties.getProperty("ui.url");
        String browserType = properties.getProperty("ui.browser");
        boolean isHeadless = Boolean.parseBoolean(properties.getProperty("ui.browser.isHeadless"));
        LOGGER.info("Opening browser {}", browserType);
        LOGGER.info("Is Headless ON {}", isHeadless);


        switch (browserType.toLowerCase()){
            case "chrome":
                ChromeOptions chromeOptions = new ChromeOptions();
                if(isHeadless) chromeOptions.addArguments("--headless");
                driver=new ChromeDriver(chromeOptions);
                break;

            case "edge":
                EdgeOptions edgeOptions = new EdgeOptions();
                if(isHeadless)edgeOptions.addArguments("--headless");
                driver=new EdgeDriver(edgeOptions);
                break;
            case "firefox":
                FirefoxOptions firefoxOptions = new FirefoxOptions();
                if(isHeadless)firefoxOptions.addArguments("--headless");
                driver=new FirefoxDriver(firefoxOptions);
                break;
            default:
                throw new RuntimeException("Wrong browser, choose between chrome, edge and firefox");
        }
        LOGGER.info("Navigating to url {}", url);
        driver.get(url);
        driver.manage().window().maximize();
        driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(WAIT_TIME_IN_SECOND));

    }
    public void quitBrowser(){
        if(driver !=null){
            LOGGER.info("Quitting browser");
            driver.quit();
        }
    }
    public WebDriver getDriver(){
        return driver;
    }


}
