package website.Servlet.User;

import website.bean.user;
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
 * 修改用户信息，可以修改密码、性别和出生年月，暂不支持修改用户名
 */

@WebServlet(name = "UpdateUserInfoServlet")
public class UpdateUserInfoServlet extends HttpServlet {
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        request.setCharacterEncoding("UTF-8");
        response.setContentType("text/html;charset=UTF-8");

        String name=request.getParameter("userName");

        HttpSession session=request.getSession();
        if(session.getAttribute("userId")==null||!session.getAttribute("userId").equals(name)){
            String msg="当前登录已失效，请重新登录！";
            request.setAttribute("message",msg);
            request.getRequestDispatcher("/message.jsp").forward(request,response);
        }

        user user=new user();

        String password=request.getParameter("password");
        String sex=request.getParameter("sex");
        String birthday=request.getParameter("birthday");

        user.setName(name);
        user.setPassword(password);
        user.setSex(sex);
        user.setBirthday(birthday);

        String sql="update user set password=\""+
                user.getPassword()+"\",sex=\""+user.getSex()+"\",birthday=\""+
                user.getBirthday()+"\" where name=\""+user.getName()+"\";";

        userDAO userDAO=new usersDAOimpl();

        String msg="更新用户信息失败！";
        if(userDAO.update(user,sql)==1){
            msg="更新用户信息成功！";
        }
        request.setAttribute("message",msg);
        request.getRequestDispatcher("/message.jsp").forward(request,response);
    }

    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        this.doPost(request,response);
    }
}
