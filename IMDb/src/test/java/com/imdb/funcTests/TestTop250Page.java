package com.imdb.funcTests;

import com.imdb.preRunConfig.CommonPreparation;
import helpers.FileOperations;
import org.openqa.selenium.support.PageFactory;
import org.testng.Assert;
import org.testng.Reporter;
import org.testng.annotations.Test;
import pageObjects.*;
import report.Logging;

import java.util.Properties;

/**
 * Created by xsoroka on 3/8/2016.
 * Class hosts 3 methods for testing each possible option
 *  - general TOP250 page returned results quantity
 *  - quantity of results for each sorting option on Top250 page
 *  - quantity of results for searching movies via genre
 */
public class TestTop250Page  extends CommonPreparation {

    @Test (groups={"IMBd - sort"})
    public void verifyReturnResultOnTopResult() {
        Logging.logResult("1. Open browser and navigate to imdb.com");
        IMDbStartPage page = PageFactory.initElements(driver, IMDbStartPage.class);
        Logging.logResult("2. Navigate to Top250 movies");
        IMDbTop250 pageWithResults = page.openTopPage() ;
        Logging.logResult("3. Verify at least 1 movie is listed");
        Assert.assertTrue(pageWithResults.verifyAtLeastOneResult());
    }
    @Test (groups={"IMBd - sort"})
    public void verifySortingOnTopResults() {
        Logging.logResult("1. Open browser and navigate to imdb.com");
        IMDbStartPage page = PageFactory.initElements(driver, IMDbStartPage.class);
        Logging.logResult("2. Navigate to Top250 movies");
        IMDbTop250 pageWithResults = page.openTopPage() ;
        Logging.logResult("3. Verify all sorting results with at least 1 movie shows");
        Assert.assertTrue(pageWithResults.changeSorting());
    }

    @Test (groups={"IMBd - search"})
    public void verifyGenreSearchResults() {
        Logging.logResult("1. Open browser and navigate to imdb.com");
        IMDbStartPage page = PageFactory.initElements(driver, IMDbStartPage.class);
        Logging.logResult("2. Navigate to Top250 movies");
        IMDbTop250 pageWithResults = page.openTopPage();
        Logging.logResult("3. Verify at least 1 movie is listed according test parameters");
        Properties properties = FileOperations.readPropFile(propFileWithParametersToCheck);
        Assert.assertTrue(pageWithResults.verifyAtLeastOneResult(properties));
    }
}
