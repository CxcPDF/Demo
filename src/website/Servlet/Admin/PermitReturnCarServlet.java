package website.Servlet.Admin;

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
import javax.servlet.http.HttpSession;
import java.io.IOException;
import java.io.PrintWriter;

/**
 * 审核用户还车订单
 */
@WebServlet(name = "PermitReturnCarServlet")
public class PermitReturnCarServlet extends HttpServlet {
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        request.setCharacterEncoding("UTF-8");
        response.setContentType("text/html;charset=UTF-8");
        PrintWriter out=response.getWriter();
        HttpSession session = request.getSession();
        JSONObject jsonObject=new JSONObject();

        if (session.getAttribute("userId") == null || !session.getAttribute("userId").equals("admin")) {
           jsonObject.put("valid","false");
        }else {

            String ID = request.getParameter("ID");//获取要还车的ID号
            //更新数据库
            String sql = "update car set statusCode=1 where ID=" + ID + ";";
            DBManager dbManager = new DBManager();
            if(dbManager.update(sql)==1){
                jsonObject.put("result","true");
            }else{
                jsonObject.put("result","false");
            }
        }
        out.println(jsonObject);
    }

    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

    }
}
