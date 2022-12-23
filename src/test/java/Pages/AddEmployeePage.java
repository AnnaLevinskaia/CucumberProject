package Pages;

import Utils.CommonMethods;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;

public class AddEmployeePage extends CommonMethods {

    @FindBy(id = "firstName")
    public WebElement firstNameFeild;

    @FindBy(id = "middleName")
    public WebElement middleNameField;

    @FindBy(id = "lastName")
    public WebElement lastNameFeild;

    @FindBy(id = "btnSave")
    public WebElement saveButton;

    @FindBy(id = "photofile")
    public WebElement photograph;

    @FindBy(id = "chkLogin")
    public WebElement checkBox;

    @FindBy(id = "user_name")
    public WebElement createusernameField;

    @FindBy(id = "user_password")
    public WebElement createpasswordField;

    @FindBy(id = "re_password")
    public WebElement confirmpasswordField;

    @FindBy(id = "employeeId")
    public WebElement empIdLocator;

    public AddEmployeePage(){
        //page factory is the concept of selenium which we use to implement page
        // object model design pattern which is respnsible to initialize all the objects
        //of the class. here when we say objects, we mean locators what we also call
        //object repository.
        //if someone ask this to you in the interview you have to write the same thing
        PageFactory.initElements(driver, this);
    }
}
