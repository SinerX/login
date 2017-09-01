package siner;

import javax.servlet.http.*;
import java.io.PrintWriter;

/**
 * Created by Siner on 2017-07-28.
 */
public class CLError extends HttpServlet {
    //重写init函数
    public void init(){
        try{
            //只会被调用一次
            System.out.println("CLError被调用");

        }catch (Exception ex){
            ex.printStackTrace();
        }
    }
    //重写destroy函数
    public void destroy(){
        try{
            System.out.println("CLError_destroy被调用");

        }catch (Exception ex){
            ex.printStackTrace();
        }
    }

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
                PrintWriter pw=res.getWriter();
                pw.println("<body background=\"imgs/background.jpg\" height=\"100%\" width=\"100%\" ><center>");
                pw.println("<h1>你的用户名或者密码错误！</h1>");
                pw.println("<br><a href=login1>返回登录界面</a><br>");
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

