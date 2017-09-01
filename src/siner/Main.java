package siner;

import javax.servlet.http.*;
import java.io.PrintWriter;

/**
 * Created by Siner on 2017-07-28.
 */
public class Main extends HttpServlet {

    public void doGet(HttpServletRequest req, HttpServletResponse res){

        try {
            //从session中得到用户名
            HttpSession hs = req.getSession(true);
            String userid = (String) hs.getAttribute("userid");
            //判断
            String name = "";
            String passwd = "";
            if (userid == null) {
                //如果session中没有用户信息，再看看有没有cookie信息
                //从客户端得到所有sookie信息
                Cookie[] allCookies = req.getCookies();

                int i = 0;

                //如果allCookies不为空
                if (allCookies != null) {
                    //从中去除cookie
                    for (i = 0; i < allCookies.length; i++) {
                        //依次取出
                        Cookie temp = allCookies[i];
                        if (temp.getName().equals("myname")) {
                            //得到cookie的值
                            name = temp.getValue();
                        } else if (temp.getName().equals("mypasswd")) {
                            passwd = temp.getValue();
                        }
                    }
                    if (!name.equals("") && !passwd.equals("")) {
                        //到logincl去验证一下
                        res.sendRedirect("logincl1?userid=" + name + "&password=" + passwd);
                        return;
                    }
                }
                //返回登录界面
                res.sendRedirect("login1?info=error1");
                return;
            }
        try{
            //中文乱码
            res.setContentType("text/html;charset=gbk");

            //调用userbeancl

            PrintWriter pw=res.getWriter();
            pw.println("<body background=\"imgs/background.jpg\" height=\"100%\" width=\"100%\"><center>");
            UserBeanCL ubc = new UserBeanCL();
            String Name=ubc.ReUserName(userid);
            pw.println("Welcome! "+Name+"<br>");

            pw.println("<h1>主界面</h1>");

            //做个超链接
            pw.println("<br><a href=login1>返回重新登录</a><br>");
            pw.println("</center></body>");

        }
        catch (Exception e){
            e.printStackTrace();
        }

    }catch (Exception ex){
        ex.printStackTrace();
    }
}
    }

