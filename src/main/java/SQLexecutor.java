import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.HashMap;
import java.util.Map;

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

    private Map<Integer, String> readFromDatabase(String query) {
        Map<Integer, String> results = new HashMap<Integer, String>();
        try {
            Statement statement = connection.createStatement();
            ResultSet rs = statement.executeQuery(query);
            while (rs.next()){
                String username = rs.getString("username");
                Integer userId = rs.getInt("user_id");
                results.put(userId, username);
            }
            statement.close();
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return results;
    }

    public void insertToAccount() {
        String query = "INSERT INTO another_schema.account(balance) VALUES (12)";
        insertToDatabase(query);
    }

    public void insertToClient() {
        insertToDatabase("INSERT INTO another_schema.client(user_id, username, created_by) VALUES (1, 'adrian', 'admin')");
        insertToDatabase("INSERT INTO another_schema.client(user_id, username, created_by) VALUES (2, 'marek', 'admin')");
        insertToDatabase("INSERT INTO another_schema.client(user_id, username, created_by) VALUES (3, 'jadzia', 'admin')");
    }

    public String getAllClients() throws SQLException {
        String query = "SELECT * FROM another_schema.client";
        Map clients = readFromDatabase(query);
        StringBuilder sb = new StringBuilder();
        for (Object o : clients.values()) {
            String clientName = (String)o;
            sb.append(clientName);
            sb.append("\n");
        }

        return sb.toString();
    }
}
