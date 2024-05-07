package QuanLyPizza.DAO;

import MyCustom.MyDialog; 
import java.sql.Connection;
import java.sql.SQLException;
import java.sql.*;
public class MyConnect {

    private static String severName="localhost";
    private static String dbName="quanlypizza";
    private static String userName="root";
    private static String password="1234";
    
    public static Connection MyConnection() {
        Connection conn = null;
        try {
            String strConnect = "jdbc:mysql://" + severName + "/" + dbName + "?useUnicode=true&characterEncoding=utf-8";          
            Class.forName("com.mysql.cj.jdbc.Driver");
            conn = DriverManager.getConnection(strConnect, userName, password);
        }  catch (Exception ex) {
            new MyDialog("Không kết nối được tới CSDL!", MyDialog.ERROR_DIALOG);
            System.exit(0);
        }
        finally{
        }
        return conn;
    }
    public static void closeConnection(Connection c) {
        try {
            if (c != null) {
                c.close();
            }
        } catch (Exception e) {
            // TODO: handle exception
            e.printStackTrace();
        }
    }
}
