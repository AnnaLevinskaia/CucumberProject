package Utils;

import java.sql.SQLOutput;

public class Constants {

    public static final String CONFIGURATION_FILEPATH =
            System.getProperty("user.dir") +"/src/test/resources/Config/Config.properties";

    public static final int EXPLICIT_WAIT = 15;
    public static final int IMPLICIT_WAIT = 10;
    public static final String TESTDATA_FILEPATH=
            System.getProperty("user.dir")+"/src/test/resources/testData/batch14Exel.xlsx";
//project root = user.dir
    public static final String SCREENSHOT_FILEPATH=System.getProperty("user.dir")+ "/screenshots/";
}
