package report;

import org.testng.Reporter;

/**
 * Created by xsoroka on 3/10/2016.
 * Class for simpe adding a line to simple testng report
 */
public class Logging {
    public static void logResult(String result){
        Reporter.log(result + "<br>");
    }
}
