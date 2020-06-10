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
            ResultSet rs = statement.executeQuery("SELECT * FROM albums");

            while(rs.next()) {
                System.out.println(rs.getLong(1));
                System.out.println(rs.getString(2));
                System.out.println(rs.getString(3));
                System.out.println(rs.getInt("release_date"));
                System.out.println(rs.getDouble("sales"));
                System.out.println(rs.getString("genre"));
                System.out.println("--------------------");
            }

        } catch (SQLException throwables) {
            throwables.printStackTrace();
        }

    }
}
