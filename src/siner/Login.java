package siner;

import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

/**
 * Created by Siner on 2017-06-19.
 */
@WebServlet(urlPatterns = "/login")
public class Login extends HttpServlet {
    public void doGet(HttpServletRequest req, HttpServletResponse res){
        try{
            //得到error信息
            String info=req.getParameter("info");
            if (info!=null){
                res.sendRedirect("clerror1");
            }
            String path="/WEB-INF/pages/Login.html";
            req.getRequestDispatcher(path).forward(req, res);
        }
        catch (Exception e){
            e.printStackTrace();
        }
    }
}
