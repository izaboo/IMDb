package com.imdb.preRunConfig;
import org.openqa.selenium.firefox.FirefoxProfile;
import org.testng.annotations.*;

import org.openqa.selenium.firefox.FirefoxDriver;

import java.util.concurrent.TimeUnit;

/**
 * Created by xsoroka on 3/8/2016.
 * Class for general pre-test and pre-suite running preparations
 *  - initiating driver
 *  - opening start page
 *  - reading properties or configuration files
 *  etc.

 */
public class CommonPreparation {
    protected FirefoxDriver driver;
    protected static String propFileWithParametersToCheck = null;

@BeforeSuite
    public void raiseDriver() throws Exception {
    FirefoxProfile profile = new FirefoxProfile();
    profile.setPreference("browser.startup.homepage", "about:blank");
    profile.setPreference("startup.homepage_welcome_url", "about:blank");
    profile.setPreference("startup.homepage_welcome_url.additional", "about:blank");
        driver = new FirefoxDriver(profile);
        driver.manage().timeouts().implicitlyWait(5, TimeUnit.SECONDS);
        driver.manage().timeouts().pageLoadTimeout(10, TimeUnit.SECONDS);
    }

    @BeforeMethod
public void setUp(){
        driver.get("http://imdb.com");
        }

@BeforeGroups("IMBd - search")
@Parameters({"propFileWithParametersToCheck"})
public void setupGroupOfValues(@Optional ("") String propFileWithParametersToCheck){
    this.propFileWithParametersToCheck = "./src/test/java/com/imdb/properties/" + propFileWithParametersToCheck;
}


@AfterSuite
public void tearDown() {
        driver.quit();
        }

}