import javax.swing.plaf.nimbus.State;
import java.sql.Connection;
import java.sql.SQLException;
import java.sql.Statement;

public class TableManager {

    private final Connection connection = ConnectionFactory.getConnection(
            DBsettings.DB_CONNECTION,
            DBsettings.DB_USER,
            DBsettings.DB_PASSWORD);


    private void createTable(String StatementQuery){
        try {
            Statement statement = connection.createStatement();
            statement.execute(StatementQuery);
            statement.close();
           // statement.executeUpdate() //insert, update, delete (DDF)
        } catch (SQLException e) {
            e.printStackTrace();
        }

    }

    public void dropAllTables() throws SQLException {
            Statement statement = connection.createStatement();
            statement.executeUpdate("DROP TABLE another_schema.account");
            statement.executeUpdate("DROP TABLE another_schema.client");

    }

    public void createUserTable() {
        createTable(StatementBuilder.getCreateTableStatement(
                "another_schema.client"));

    }

    public void createAccountTable(){
        createTable(StatementBuilder.getCreateAccountTableStatement());
    }
}
