package website.DataBase;

import java.sql.*;

public class DBManager {
    public Connection connection;
    public Statement statement;
    public ResultSet resultSet;

    String url = "jdbc:mysql://localhost:3306/rentcar_park";
    String user = "root";
    String psd = "wh123456";
    String driver = "com.mysql.jdbc.Driver";

    /**
     * 用来执行查询语句
     */
    public ResultSet query(String sql){
        System.out.println(sql);
        try {
            Class.forName(driver);
            connection=DriverManager.getConnection(url,user,psd);
            statement=connection.createStatement();
            resultSet=statement.executeQuery(sql);
        } catch (ClassNotFoundException e) {
            e.printStackTrace();
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return resultSet;
    }


    /**
     *用来执行insert,update,delate SQL语句
     *@param sql 要执行的SQL语句
     *@return sql语句影响的行数
     */
    public int update(String sql){
        System.out.println(sql);
        int rows=0;
        try {
            Class.forName(driver);
//            Class.forName("com.mysql.jdbc.Driver");

            System.out.println("开始连接数据库...");

            connection=DriverManager.getConnection(url,user,psd);

            System.out.println("连接到数据库...");

            statement=connection.createStatement();
            rows=statement.executeUpdate(sql);
        } catch (ClassNotFoundException e) {
            e.printStackTrace();
        } catch (SQLException e) {
            e.printStackTrace();
        } finally {
            this.close();
        }
        return rows;
    }

    /**
     * 关闭资源
     */
    public void close(){
        try {
            if (resultSet != null) {
                resultSet.close();
                resultSet = null;
            }
            if (statement != null) {
                statement.close();
                statement = null;
            }
            if (connection!= null) {
                connection.close();
                connection = null;
            }
        } catch (Exception e) {
            System.out.println("关闭资源时发生异常");
            e.printStackTrace();
        }
    }
}
