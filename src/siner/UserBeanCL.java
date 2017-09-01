//这是一个处理类（处理users表）<---->操作userbean
//业务逻辑在这里
package siner;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.ArrayList;

/**
 * Created by Siner on 2017-07-24.
 */
public class UserBeanCL {
    //业务逻辑
    private Connection ct=null;
    private PreparedStatement ps=null;
    private ResultSet rs=null;
    private int pageCount = 0;//共有几页（计算所得）

    //返回pageCount
    public int getPageCount(){
       return this.pageCount;
    }

    //分页显示
    public ArrayList getResultByPage(int pageNow,int pageSize){
        ArrayList al = new ArrayList();
        try {
            int rowCount = 0;//共有几条记录（查表获得）


            //得到rowCount
            ConnDB cd = new ConnDB();
            ct = cd.getConn();
            String sql;
            sql = "select count(*) as pagecount from web_user_login";
            ps = ct.prepareStatement(sql);

            rs = ps.executeQuery(sql);

            if (rs.next()) {
                rowCount = rs.getInt("pagecount");
            }
            //计算pageCount
            if (rowCount % pageSize == 0) {
                pageCount = rowCount / pageSize;

            } else {
                pageCount = rowCount / pageSize + 1;
            }
            sql = "select * from web_user_login where userid not in  (select a.userid from (select userid from users limit  " + (pageSize * (pageNow - 1)) + " ) as a )limit " + pageSize;
            ps = ct.prepareStatement(sql);
            rs = ps.executeQuery(sql);

            while (rs.next()) {
                //将rs中的每条记录封装到userBean ub中
                UserBean ub = new UserBean();
                ub.setUserId(rs.getInt("userid"));
                ub.setPassword(rs.getString("password"));
                ub.setName(rs.getString("name"));
                ub.setTel(rs.getString("tel"));
                ub.setDep(rs.getString("dep"));
                ub.setMail(rs.getString("mail"));
                ub.setClasses(rs.getString("class"));

                //将ub放入到ArrayList中
                al.add(ub);
            }
        }
        catch (Exception ex){
            ex.printStackTrace();
        }finally {
            this.close();
        }
        return al;

    }
public String ReUserName(String u){
    try{
        //得到连接
        ConnDB cd=new ConnDB();
        ct=cd.getConn();

        ps=ct.prepareStatement("select name from web_user_login where userid= ?");
        //给sql中的？赋值
        ps.setString(1,u);
        rs=ps.executeQuery();

        if(rs.next()){
            String dbName=rs.getString(1);
           return dbName;
        }

    }catch(Exception ex){
        ex.printStackTrace();
    }finally {
        this.close();
    }
    return null;
}
    //验证用户
    public boolean checkUser(String u, String p){
        boolean b=false;
        try{
            //得到连接
            ConnDB cd=new ConnDB();
            ct=cd.getConn();

            ps=ct.prepareStatement("select password from web_user_login where userid= ?");
            //给sql中的？赋值
            ps.setString(1,u);
            rs=ps.executeQuery();

            if(rs.next()){
                String dbPasswd=rs.getString(1);
                if(dbPasswd.equals(p)){
                    b=true;
                }
            }

        }catch(Exception ex){
            ex.printStackTrace();
        }finally {
            this.close();
        }
        return b;
    }
    //关闭资源
    public void close(){
        try {
            if(rs!=null) {
                rs.close();
            }
            if(ps!=null) {
                ps.close();
            }
            if(ct!=null) {
                ct.close();
            }
        }catch (Exception ex){
            ex.printStackTrace();
        }
    }
}
