package website.Servlet.User;

import org.json.simple.JSONObject;
import website.bean.user;
import website.DAO.impl.usersDAOimpl;
import website.DAO.userDAO;
import website.DataBase.DBManager;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.io.PrintWriter;
import java.sql.ResultSet;
import java.sql.SQLException;

/**
 * 用户注册
 */
@WebServlet(name = "UseraddServlet")
public class UseraddServlet extends HttpServlet {
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        request.setCharacterEncoding("UTF-8");
        response.setContentType("text/html;charset=UTF-8");
        JSONObject jsonObject=new JSONObject();

        user user=new user();
        String name=request.getParameter("username");
        user.setName(name);
        user.setPassword(request.getParameter("password"));
        user.setSex(request.getParameter("sex"));
        user.setBirthday(request.getParameter("birthday"));
        user.setMoney(Double.parseDouble(request.getParameter("money")));
        user.setAccountStatus(true);//账户刚开始注册的时候是可以使用的

        userDAO userDAO=new usersDAOimpl();
        if(checkName(name)&&userDAO.add(user)){
            userDAO.createUserTable(name);//创建用户对应的表
            jsonObject.put("result","true");
        }else if(checkName(name)==false){
            jsonObject.put("result","repeat");
        }else{
            jsonObject.put("result","false");
        }

        PrintWriter out=response.getWriter();
        out.println(jsonObject);
    }

    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        this.doPost(request, response);
    }

    public boolean checkName(String name){
        boolean flag=true;
        DBManager dbManager=new DBManager();
        String sql="select * from user where name=\""+name+"\";";
        ResultSet set=dbManager.query(sql);
        try {
            while (set.next()){
                flag=false;
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return flag;
    }
}
