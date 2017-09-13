public class StatementBuilder {

    public static String getCreateTableStatement(String tableName){
        StringBuilder sb = new StringBuilder();
        sb.append(String.format("CREATE TABLE %s(", tableName));
        sb.append("USER_ID INTEGER NOT NULL, ");
        sb.append("USERNAME CHARACTER(20) NOT NULL, ");
        sb.append("CREATED_BY CHARACTER(15) NOT NULL, PRIMARY KEY (USER_ID))");
        return sb.toString();

    }

    public static String getCreateAccountTableStatement(){
        StringBuilder sb = new StringBuilder();
        sb.append("CREATE TABLE another_schema.ACCOUNT(");
        sb.append("BALANCE INTEGER)");
        return sb.toString();

    }


}
