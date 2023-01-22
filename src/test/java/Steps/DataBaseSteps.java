package Steps;

public class DataBaseSteps {

    public static String getFirstLastNameQuery(){

        String query="select emp_firstname,emp_lastname " +
                "from hs_hr_employees " +
                "where employee_id=";
        return query;
    }
}
