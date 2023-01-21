package Utils;

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

    public static String adminPayload(){
        String adminPayload="{\n" +
                "  \"email\": \"batch1444444444@testt.com\",\n" +
                "  \"password\": \"Test@123\"\n" +
                "}";
        return adminPayload;
    }
}
