package pageObjects;
import org.openqa.selenium.By;
import org.openqa.selenium.NoSuchElementException;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.WebDriverWait;
/**
 * Created by xsoroka on 3/8/2016.
 */
public class IMDbStartPage {
    private final WebDriver driver;
    private final WebDriverWait wait;

    By linkToTop = By.linkText("Top Rated Movies");

    public IMDbStartPage(WebDriver driver) {
        this.driver = driver;
        this.wait = new WebDriverWait(driver, 5);

        if (driver.getTitle().matches("IMDb - Movies")) {
            throw new IllegalStateException("This is not main IBDb page");
        }
    }

    public IMDbTop250 openTopPage() {

        driver.findElement(linkToTop).click();
        return new IMDbTop250(driver, wait);
    }

    public IMDbTop250 findAvailableTrips (String from, String to) {
        return openTopPage();
    }

}
