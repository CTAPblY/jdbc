import org.h2.Driver;
import org.h2.command.ddl.CreateTable;
import org.h2.command.query.Select;

import java.sql.*;

public class Main {

    public static void main(String[] args) throws Exception {

        Connection conn = DriverManager.getConnection("jdbc:h2:~/test", "sa", "");
        insertQuery(conn, new Numb(21, "+55", "Brazil"));
        conn.close();
    }
    private static void selectQuery(Connection conn, String query) throws SQLException {
        Statement stmt = conn.createStatement();

        ResultSet resultSet = stmt.executeQuery(query);

        while (resultSet.next()){
            System.out.print("id: " + resultSet.getInt("id") + "  " );
            System.out.print("numb: " + resultSet.getString("numb") +"  " );
            System.out.print("country: " + resultSet.getString("country")  + "\n");
        }

    }

    private static void insertQuery(Connection conn, Numb numb) throws SQLException {
        PreparedStatement preparedStatement = conn.prepareStatement("insert into numb(id, numb, country)  values(?, ?, ?)");
        preparedStatement.setInt(1, numb.getId());
        preparedStatement.setString(2, numb.getNumb());
        preparedStatement.setString(3, numb.getCountry());

        int result = preparedStatement.executeUpdate();

        if (result == 1){
            System.out.println("Insert success");
        }
        else {
            System.out.println("Insert failed");
        }

    }
}