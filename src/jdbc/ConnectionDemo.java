package jdbc;

import java.sql.*;
import java.util.Arrays;
import java.util.Date;

public class ConnectionDemo {

    public static final String DBDRIVER = "com.mysql.jdbc.Driver";
    public static final String DBURL = "jdbc:mysql://localhost:3306/Test";
    public static final String DBUSER = "root";
    public static final String DBPWD = "123000";

    public static void main(String[] args) throws Exception{
        Class.forName(DBDRIVER);
        Connection connection = DriverManager.getConnection(DBURL,DBUSER,DBPWD);
        System.out.println(connection);
        //取得statement接口对象

        String name = "nihaoya";
        int id = 223;
        Date date = new Date();

        String sql = "INSERT INTO user(name,ID) VALUES(?,?)";
        PreparedStatement preparedStatement = connection.prepareStatement(sql);
        //取消自动提交事务
        //connection.setAutoCommit(false);
        for (int i = 0; i < 10; i++) {
            if (i == 3){
                preparedStatement.setInt(1,i);
                preparedStatement.setInt(2,i);
                preparedStatement.addBatch();
//                connection.commit();
            }
            else {
                preparedStatement.setString(1, "ecnu" + i);
                preparedStatement.setInt(2, i);
                //将操作放入批处理中
                preparedStatement.addBatch();
//                connection.commit();
            }
        }
        int resultSet[] = preparedStatement.executeBatch();
        System.out.println(Arrays.toString(resultSet));

    }


}
