package pageObjects;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

/**
 * Created by xsoroka on 3/8/2016.
 */
public class IMDbSearchPage {
    private final WebDriver driver;
    private WebDriverWait wait = null;

    By foundResultsTable = By.className("results");
    By foundResultsTableFirstElement = By.className("number");


    public IMDbSearchPage(WebDriver driver, WebDriverWait wait) {
        this.wait = wait;
        wait.until(ExpectedConditions.presenceOfAllElementsLocatedBy(foundResultsTable));
        this.driver = driver;
    }

    public boolean verifyAtLeastOneResult()
    {
        if (driver.findElements(foundResultsTableFirstElement).size() != 0)
        {
            return true;
        }
        else
            return false;
    }

}
