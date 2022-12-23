package Steps;

import Utils.CommonMethods;
import io.cucumber.java.After;
import io.cucumber.java.Before;
import io.cucumber.java.Scenario;

public class Hooks extends CommonMethods {

    @Before //use io.cucumber.java to import hook
    public void preCondition(){
        openBrowserAndLaunchApplication();
    }

    //we use special class Scenario - hold complete info of execution
    @After
    public void postCondition(Scenario scenario){
        byte[] pic;
        if(scenario.isFailed()){ //to create the screenshot
            //failed screenshot will be available failed folder
            pic=takeScreenShot("failed/"+scenario.getName());
        }else {
            pic=takeScreenShot("passed/"+scenario.getName());
        }
        //to attach the screenshot in our report
        scenario.attach(pic, "image/png", scenario.getName());

        closeBrowser();
    }
}
