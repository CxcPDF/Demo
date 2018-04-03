package website.Servlet.User;

import org.json.simple.JSONObject;
import website.DAO.impl.usersDAOimpl;
import website.DAO.userDAO;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.io.IOException;
import java.io.PrintWriter;

@WebServlet(name = "UserLoginServlet")
public class UserLoginServlet extends HttpServlet {
    private String userName;//登录的用户名
    private String password;//用户登录密码
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        request.setCharacterEncoding("UTF-8");
        response.setContentType("text/html;charset=UTF-8");
        PrintWriter out=response.getWriter();
        String result="false";

        userName=request.getParameter("username");
        password=request.getParameter("password");

        userDAO userDAO=new usersDAOimpl();

        System.out.println(userDAO.getPassword(userName));

        if(userDAO.getAccountStatus(userName)&&userDAO.getPassword(userName).equals(password)){
            HttpSession session=request.getSession();
            session.setAttribute("userId",userName);
            session.setMaxInactiveInterval(30*60);//设置最大有效时间
            result="true";
        }

        try {
            JSONObject jsonObject = new JSONObject();
            jsonObject.put("result", result);
            out.println(jsonObject);
        }catch (Exception e){
            e.printStackTrace();
            System.out.println("json error!");
        }
    }

    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        this.doPost(request,response);
    }
}
