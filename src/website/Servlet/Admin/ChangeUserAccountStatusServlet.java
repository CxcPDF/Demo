package website.Servlet.Admin;

import website.DAO.impl.usersDAOimpl;
import website.DAO.userDAO;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.io.IOException;


/**
 * 修改用户账号的状态，可以冻结或者解封
 */
@WebServlet(name = "ChangeUserAccountStatusServlet")
public class ChangeUserAccountStatusServlet extends HttpServlet {
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        request.setCharacterEncoding("UTF-8");
        response.setContentType("text/html;charset=UTF-8");

        String name=request.getParameter("adminName");

        HttpSession session=request.getSession();
        if(session.getAttribute("userId")==null||!session.getAttribute("userId").equals(name)){
            String msg="当前登录已失效，请重新登录！";
            request.setAttribute("message",msg);
            request.getRequestDispatcher("/message.jsp").forward(request,response);
        }

        String userName=request.getParameter("userName");//获取要修改的用户名
        String msg="修改用户账户状态失败！";
        userDAO userDAO=new usersDAOimpl();
        boolean b=userDAO.getAccountStatus(userName);
        if(b){//修改
            b=false;
        }else {
            b = true;
        }
        if(userDAO.changeAccountStatus(userName,b)){
            msg="修改用户账户状态成功！";
        }

        request.setAttribute("message",msg);
        request.getRequestDispatcher("/message.jsp").forward(request,response);
    }

    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        this.doPost(request,response);
    }
}
