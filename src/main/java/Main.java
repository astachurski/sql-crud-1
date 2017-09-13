import java.sql.*;

public class Main {
    public static void main(String[] args) {

        //String connString = "jdbc:postgresql://localhost:5432/movierentaltest";

/*
        try {
            Connection myConnection = DriverManager.getConnection(connString, "adrian", "dupa123");

            Statement statement = myConnection.createStatement();

            ResultSet resultSet = statement.executeQuery("select * from movierental.movie");

            while (resultSet.next()){
                System.out.println(resultSet.getString("title"));
            }

        } catch (SQLException e) {
            e.printStackTrace();
        }
*/
        TableManager tableManager = new TableManager();

        try {
            tableManager.dropAllTables();
        } catch (SQLException e) {
            System.out.println("tables probably existed");
        }

        tableManager.createUserTable();
        tableManager.createAccountTable();
        SQLexecutor sqlExecutor = new SQLexecutor();
        sqlExecutor.insertToAccount();
        sqlExecutor.insertToClient();
        try {
            String clientList = sqlExecutor.getAllClients();
            System.out.println("client list: " + clientList);
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

}
