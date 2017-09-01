package siner;

import javax.servlet.http.*;

/**
 * Created by Siner on 2017-06-19.
 */
public class LoginCL extends HttpServlet {

    //重写init函数
    public void init(){
        try{
            //只会被调用一次
            System.out.println("LoginCL_init被调用");

        }catch (Exception ex){
            ex.printStackTrace();
        }
    }
    //重写destroy函数
    public void destroy(){
        try{
            System.out.println("LoginCL_destroy被调用");

        }catch (Exception ex){
            ex.printStackTrace();
        }
    }


    //处理get请求
    //req:用于获取客户端的信息
    //res:用于向客户端返回信息
   public void doGet(HttpServletRequest req, HttpServletResponse res) {
        //业务逻辑
        try {
            //接收用户名和密码
            String u = req.getParameter("userid");
            String p = req.getParameter("password");

            //调用userbeancl
            UserBeanCL ubc = new UserBeanCL();
            //验证
            if (ubc.checkUser(u, p)) {
                //用户合法
                String keep = req.getParameter("keep");
                if (keep != null) {
                    //将用户名和密码保存在客户端（使用cookie）
                    //创建Cookie
                    Cookie userid = new Cookie("userid", u);
                    Cookie pass = new Cookie("mypasswd", p);
                    //设置时间
                    userid.setMaxAge(14 * 24 * 3600);
                    pass.setMaxAge(14 * 24 * 3600);
                    //回写到客户端
                    res.addCookie(userid);
                    res.addCookie(pass);
                }
                //将用户名和密码放入session中
                HttpSession hs = req.getSession(true);
                //修改session的存在时间
                hs.setMaxInactiveInterval(30);
                hs.setAttribute("userid", u);

                //跳转到main
                res.sendRedirect("main1");

            } else {
                //说明用户名不存在
                res.sendRedirect("login1?info=error1");
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}