import java.sql.*;

public class Main {
    public static void main(String[] args) {
        String url = "jdbc:mysql://localhost:3306/skillbox?serverTimezone=UTC";
        String user = "root";
        String pwd = "testtest";
        try {
            Connection connection = DriverManager.getConnection(url, user, pwd);
            Statement statement = connection.createStatement();

            for (int i = 1; i <= 12; i++){
                ResultSet resultSet = statement.executeQuery("SELECT course_name, MONTHNAME(subscription_date) AS sub_month, COUNT(subscription_date) " +
                        "FROM PurchaseList " +
                        "WHERE MONTH(subscription_date) = " + i + " " +
                        "GROUP BY course_name");
                while (resultSet.next()){
                    int count = resultSet.getInt("COUNT(subscription_date)");
                    String courseName = resultSet.getString("course_name");
                    String subMonth = resultSet.getString("sub_month");
                    System.out.println(courseName + " was bought " + count + " times in " + subMonth);
                }
            }
        }
        catch (Exception e){
            e.printStackTrace();
        }
    }
}
