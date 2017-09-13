import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

public class SQLexecutor {

    private final Connection connection = ConnectionFactory.getConnection(
            DBsettings.DB_CONNECTION,
            DBsettings.DB_USER,
            DBsettings.DB_PASSWORD);

    private void insertToDatabase(String query) {
        try {
            Statement statement = connection.createStatement();
            int res = statement.executeUpdate(query);
            statement.close();
            System.out.println(res);
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    private ResultSet readFromDatabase(String query) {
        ResultSet result = null;
        try {
            Statement statement = connection.createStatement();
            result = statement.executeQuery(query);
            statement.close();
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return result;
    }

    public void insertToAccount() {
        String query = "INSERT INTO another_schema.account(balance) VALUES (12)";
        insertToDatabase(query);
    }

    public void insertToClient() {
        String query = "INSERT INTO another_schema.client(" +
                "user_id, username, created_by) VALUES (1, 'adrian', 'admin')";
        insertToDatabase(query);
    }

    public String getAllClients() throws SQLException {
        String query = "SELECT * FROM another_schema.client";
        ResultSet rs = readFromDatabase(query);
        StringBuilder sb = new StringBuilder();
        while (rs.next()) {
            sb.append(rs.getString("username"));
            sb.append("\n");
        }

        return sb.toString();
    }
}
