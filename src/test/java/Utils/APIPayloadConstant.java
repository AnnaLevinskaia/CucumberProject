package Utils;

import org.json.JSONObject;

public class APIPayloadConstant {

    public static String createEmployeePayload(){
        String createEmployeePayload="{\n" +
                "  \"emp_firstname\": \"Bob\",\n" +
                "  \"emp_lastname\": \"Duck\",\n" +
                "  \"emp_middle_name\": \"ms\",\n" +
                "  \"emp_gender\": \"F\",\n" +
                "  \"emp_birthday\": \"1990-09-10\",\n" +
                "  \"emp_status\": \"confirmed\",\n" +
                "  \"emp_job_title\": \"QA\"\n" +
                "}";
        return createEmployeePayload;
    }

    public static String createEmployeeJsonBody(){
        JSONObject obj=new JSONObject();
        obj.put("emp_firstname", "Bob");
        obj.put("emp_lastname", "Duck");
        obj.put("emp_middle_name", "ms");
        obj.put("emp_gender", "F");
        obj.put("emp_birthday", "1990-09-10");
        obj.put("emp_status", "confirmed");
        obj.put("emp_job_title", "QA");

        return obj.toString();
    }

    public static String createEmployeePayloadDynamic(String firstname, String lastname, String middlename,
                                                      String gender, String dob, String empStatus, String jobTitle){
        JSONObject obj=new JSONObject();
        obj.put("emp_firstname", firstname);
        obj.put("emp_lastname", lastname);
        obj.put("emp_middle_name", middlename);
        obj.put("emp_gender", gender);
        obj.put("emp_birthday", dob);
        obj.put("emp_status", empStatus);
        obj.put("emp_job_title", jobTitle);

        return obj.toString();
    }

    public static String updateEmployeeFullDetails(String firstname, String lastname, String middlename,
                                                   String gender, String dob, String empStatus, String jobTitle, String emp_id){
        JSONObject obj=new JSONObject();
        obj.put("emp_firstname", firstname);
        obj.put("emp_lastname", lastname);
        obj.put("emp_middle_name", middlename);
        obj.put("emp_gender", gender);
        obj.put("emp_birthday", dob);
        obj.put("emp_status", empStatus);
        obj.put("emp_job_title", jobTitle);
        obj.put("employee_id", emp_id);

        return obj.toString();
    }

    public static String adminPayload(){
        String adminPayload="{\n" +
                "  \"email\": \"batch1444444444@testt.com\",\n" +
                "  \"password\": \"Test@123\"\n" +
                "}";
        return adminPayload;
    }
}
