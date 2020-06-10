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

            //READ
            while(rs.next()) {
                System.out.println(rs.getLong(1));
                System.out.println(rs.getString(2));
                System.out.println(rs.getString(3));
                System.out.println(rs.getInt("release_date"));
                System.out.println(rs.getDouble("sales"));
                System.out.println(rs.getString("genre"));
                System.out.println("--------------------");
            }

            //CREATE
            String query = "INSERT INTO albums (artist, name, release_date, genre, sales) VALUES('Princess Not Jasmine', 'Take Me To The Moon', 2020, 'Pop', 20.0)";
            statement.executeUpdate(query, Statement.RETURN_GENERATED_KEYS);
            rs = statement.getGeneratedKeys();

            if(rs.next()) {
                System.out.println("Inserted a new record! New id: " + rs.getLong(1));
            }

            //DELETE
            long idToDelete = 34;
            query = "DELETE from albums WHERE id = " + idToDelete;
            statement.execute(query);
            System.out.println(idToDelete + " is gone!");

            //UPDATE
            double sales = 2;
            long idToUpdate = 37;
            statement.execute("UPDATE albums SET sales = " + sales + "");
            System.out.println(idToUpdate + " is updated");


        } catch (SQLException throwables) {
            throwables.printStackTrace();
        }

    }
}
