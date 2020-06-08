import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.Statement;

public class ConnectionBase {
    public static void main(String[] args) {
        try {
            Class.forName("org.sqlite.JDBC");
            Connection connection = DriverManager.getConnection("jdbc:sqlite:users.db");
            Statement stm = connection.createStatement();
            System.out.println("Base connected");
            stm.executeUpdate("INSERT INTO users (nickname, password, email) values (?, ?, ?)");

        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public static void singUpUser () {


    }
}

class ConstChat {
    public static final String user_table = "users";
    public static final String nickname = "nickname";
    public static final String password = "password";
    public static final String email = "email";
}
