package thread;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

public class ConnectionFactory {

    public enum MyConnectionFactorySingleton{
        //这里定义了一个枚举变量，在类加载的时候该变量的单例模式就已经创建成功
        connectionFactory;
        private Connection connection;

        MyConnectionFactorySingleton(){
            System.out.println("开始创建connection的单例模式");
            String url = "jdbc:mysql://localhost:3306/test";
            String username = "root";
            String password = "123000";
            String driver = "com.mysql.jdbc.Driver";
            try {
                Class.forName(driver);
                try {
                    connection = DriverManager.getConnection(url, username, password);
                } catch (SQLException e) {
                    e.printStackTrace();
                }
            } catch (ClassNotFoundException e) {
                e.printStackTrace();
            }
        }

        public Connection getConnection(){
            return connection;
        }
    }

    public static Connection getConnection(){
        return MyConnectionFactorySingleton.connectionFactory.getConnection();
    }
}
