package com.imdb.funcTests;

import com.imdb.preRunConfig.CommonPreparation;
import helpers.FileOperations;
import org.openqa.selenium.support.PageFactory;
import org.testng.Assert;
import org.testng.Reporter;
import org.testng.annotations.Test;
import pageObjects.*;

import java.util.Properties;

/**
 * Created by xsoroka on 3/8/2016.
 */
public class TestTop250Page  extends CommonPreparation {

    @Test (groups={"IMBd - sort"})
    public void verifyReturnResultOnTopResult() {
        Reporter.log("1. Open browser and navigate to imdb.com");
        IMDbStartPage page = PageFactory.initElements(driver, IMDbStartPage.class);
        Reporter.log("2. Navigate to Top250 movies");
        IMDbTop250 pageWithResults = page.openTopPage() ;
        Reporter.log("3. Verify at least 1 movie is listed");
        Assert.assertTrue(pageWithResults.verifyAtLeastOneResult());
    }
    @Test (groups={"IMBd - sort"})
    public void verifySortingOnTopResults() {
        Reporter.log("1. Open browser and navigate to imdb.com<br>");
        IMDbStartPage page = PageFactory.initElements(driver, IMDbStartPage.class);
        Reporter.log("2. Navigate to Top250 movies<br>");
        IMDbTop250 pageWithResults = page.openTopPage() ;
        Reporter.log("3. Verify all sorting results with at least 1 movie shows<br>");
        Assert.assertTrue(pageWithResults.changeSorting());
    }

    @Test (groups={"IMBd - search"})
    public void verifyGenreSearchResults() {
        Reporter.log("1. Open browser and navigate to imdb.com<br>");
        IMDbStartPage page = PageFactory.initElements(driver, IMDbStartPage.class);
        Reporter.log("2. Navigate to Top250 movies<br>");
        IMDbTop250 pageWithResults = page.openTopPage();
        Reporter.log("3. Verify at least 1 movie is listed according test parameters<br>");
        Properties properties = FileOperations.readPropFile(propFileWithParametersToCheck);
        Assert.assertTrue(pageWithResults.verifyAtLeastOneResult(properties));
    }
}
