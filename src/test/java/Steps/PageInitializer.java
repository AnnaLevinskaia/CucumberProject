package Steps;

import Pages.AddEmployeePage;
import Pages.DashboardPage;
import Pages.EmployeeListPage;
import Pages.LoginPage;

public class PageInitializer {

    public static LoginPage login;
    public static DashboardPage dashboard;
    public static AddEmployeePage addEmployee;
    public static EmployeeListPage employeeList;

    public static void initializePageObject(){
        login=new LoginPage();
        dashboard=new DashboardPage();
        addEmployee=new AddEmployeePage();
        employeeList=new EmployeeListPage();
    }

}
