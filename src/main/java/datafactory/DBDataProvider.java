package datafactory;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.ResultSetMetaData;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;

public class DBDataProvider implements DataProvider {

    private String name;
    private String url;
    private String username;
    private String password;
    private String sqlQuery;

    public DBDataProvider(String name) {
        this.name = name;
        String[] configParts = name.split(",");
        this.url = configParts[0];
        this.username = configParts[1];
        this.password = configParts[2];
        this.sqlQuery = configParts[3];
    }

    @Override
    public Object[][] setDataProvider() throws Exception {
        Connection conn = DriverManager.getConnection(url, username, password);
        Statement stmt = conn.createStatement();
        ResultSet rs = stmt.executeQuery(sqlQuery);
        ResultSetMetaData metaData = rs.getMetaData();
        int colCount = metaData.getColumnCount();
        List<Object[]> dataList = new ArrayList<>();
        while (rs.next()) {
            Object[] rowData = new Object[colCount];
            for (int j = 0; j < colCount; j++) {
                rowData[j] = rs.getObject(j + 1);
            }
            dataList.add(rowData);
        }
        Object[][] dataArray = dataList.toArray(new Object[dataList.size()][]);
        rs.close();
        stmt.close();
        conn.close();
        return dataArray;
    }
}