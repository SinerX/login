//从数据库中得到连接
package siner;

import java.sql.Connection;
import java.sql.DriverManager;

/**
 * Created by Siner on 2017-07-24.
 */
public class ConnDB {
    // JDBC 驱动名及数据库 URL
    static final String JDBC_DRIVER = "com.mysql.jdbc.Driver";
    static final String DB_URL = "jdbc:mysql://localhost:3306/web_huanuo";

    // 数据库的用户名与密码，需要根据自己的设置
    static final String USER = "root";
    static final String PASS = "1234";
    private Connection ct =null;
    public Connection getConn(){
        try{
            //加载驱动
            Class.forName("com.mysql.jdbc.Driver");
            //返回连接
            ct= DriverManager.getConnection(DB_URL,USER,PASS);
        }
        catch(Exception ex){
            ex.printStackTrace();
        }
        return ct;
    }
}
