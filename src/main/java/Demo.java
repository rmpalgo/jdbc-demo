import java.sql.*;
import com.mysql.cj.jdbc.Driver;

public class Demo {

    public static void main(String[] args) {

        try {
            DriverManager.registerDriver(new Driver());
            Config config = new Config();
            Connection connection = DriverManager.getConnection(
                    config.getUrl(),
                    config.getUser(),
                    config.getPassword()
            );

            Statement statement = connection.createStatement();
            ResultSet rs = statement.executeQuery("SHOW databases");

            while(rs.next()) {
                System.out.println("rs.getString(\"Database\") = " + rs.getString("Database"));
            }

        } catch (SQLException throwables) {
            throwables.printStackTrace();
        }

    }
}
