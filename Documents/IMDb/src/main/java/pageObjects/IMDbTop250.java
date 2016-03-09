package pageObjects;

import org.openqa.selenium.*;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.testng.Reporter;

import java.util.*;

/**
 * Created by xsoroka on 3/8/2016.
 */
public class IMDbTop250 {
    private final WebDriver driver;
    private WebDriverWait wait = null;

    By movieTitleInResultTable = By.className("titleColumn");

    public IMDbTop250(WebDriver driver, WebDriverWait wait) {
        this.wait = wait;
        wait.until(ExpectedConditions.presenceOfAllElementsLocatedBy(movieTitleInResultTable));
        this.driver = driver;
    }

    public boolean verifyAtLeastOneResult()
    {
        if (driver.findElements(movieTitleInResultTable).size()!=0)
            return true;
        else
            return false;
    }

    public boolean changeSorting ()
    {
        By sortingList = By.xpath("//*[@class='lister-sort-by']/option");
        String mask = "//*[@value='x']";
        List<WebElement> sortModes = driver.findElements(sortingList);
        List<String> sortModesResult = new ArrayList<String>();
        String sortModeName = null;
        for (WebElement mode : sortModes)
        {
            sortModeName = mode.getAttribute("value");
            if (!driver.findElement(By.xpath(mask.replace("x", sortModeName))).isSelected())
                driver.findElement(By.xpath(mask.replace("x", sortModeName))).click();
            wait.until(ExpectedConditions.presenceOfElementLocated(movieTitleInResultTable));
            sortModesResult.add(sortModeName + ":" + verifyAtLeastOneResult());
        }
        return !sortModesResult.contains("false");
    }

    public boolean verifyAtLeastOneResult(Properties properties)
    {
        return changeGenre(properties);
    }

    private boolean changeGenre (Properties props)
    {
        if (props.getProperty("GENRES") == null)
            return changeGenre("Western");//hardcoded according to test task
        else
            return tryAllGenres(props.getProperty("GENRES"));
    }

    private List <String> getAllGenresNames ()
    {
        By genreLocator = By.className("subnav_item_main");
        List <WebElement> allGenresLinks = driver.findElements(genreLocator);
        List <String> allGenresLinksNames = new ArrayList<String>();
        for (WebElement genre : allGenresLinks) {
            allGenresLinksNames.add(genre.getText());
        }
        return allGenresLinksNames;
    }

    private boolean tryAllGenres (String allGenres)
    {
        By genreLocator = By.className("subnav_item_main");
        Set<Boolean> results = new HashSet<Boolean>();
        List <String> allGenresLinksNames = new ArrayList<String>();
        if (allGenres.equals("ALL"))
            allGenresLinksNames = getAllGenresNames();
        else
            allGenresLinksNames = Arrays.asList(allGenres.split(","));
        for (String genre : allGenresLinksNames)
        {
            results.add(changeGenre(genre));
        }
        return !results.contains(false);
    }

    private boolean changeGenre (String genre)
    {
        By genreLocator = By.xpath("//li[@class='subnav_item_main']/a[contains(text(),'"+ genre +"')]");
        driver.findElement(genreLocator).click();
        IMDbSearchPage searchPage = new IMDbSearchPage(driver, wait);
        boolean result = searchPage.verifyAtLeastOneResult();
        Reporter.log(genre + " : " + result + "<br>");
        driver.navigate().back();
        wait.until(ExpectedConditions.presenceOfElementLocated(movieTitleInResultTable));
        return result;
    }
}
