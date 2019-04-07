package Log_in_page;

public class Information {
    private static String emailInfo;
    private static String passwordInfo;
    private static String address;
    private static  int counts;

    private static String nameInfo;
    static int userId;

    public static String getNameInfo() {
        return nameInfo;
    }

    public static void setNameInfo(String nameInfo) {
        Information.nameInfo = nameInfo;
    }




    public static int getUserId() {
        return userId;
    }

    public static void setUserId(int userId) {
        Information.userId = userId;
    }




    public static String getEmailInfo() {
        return emailInfo;
    }

    public static void setEmailInfo(String emailInfo) {
        Information.emailInfo = emailInfo;
    }

    public static String getPasswordInfo() {
        return passwordInfo;
    }

    public static void setPasswordInfo(String passwordInfo) {
        Information.passwordInfo = passwordInfo;
    }


    public static int getCounts() {
        return counts;
    }

    public static void setCounts(int counts) {
        Information.counts = counts;
    }

    public static String getAddress() {
        return address;
    }

    public static void setAddress(String address) {
        Information.address = address;
    }
}
