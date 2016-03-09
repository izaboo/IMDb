package helpers;

import java.io.FileInputStream;
import java.io.IOException;
import java.util.Properties;

/**
 * Created by xsoroka on 3/9/2016.
 */
public class FileOperations {
    public static Properties readPropFile (String propFileWithPath){
    Properties prop = new Properties();
    try {
        prop.load(new FileInputStream(propFileWithPath));
    } catch (IOException ex) {
        ex.printStackTrace();
    }
    return prop;
}
}
