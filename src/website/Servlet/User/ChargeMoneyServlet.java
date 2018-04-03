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

@WebServlet(name = "ChargeMoneyServlet")
public class ChargeMoneyServlet extends HttpServlet {
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        request.setCharacterEncoding("UTF-8");
        response.setContentType("text/html;charset=UTF-8");
        JSONObject jsonObject=new JSONObject();
        String name=request.getParameter("userName");

        HttpSession session=request.getSession();
        if(session.getAttribute("userId")==null||!session.getAttribute("userId").equals(name)){
            jsonObject.put("valid","false");
        }else {
            jsonObject.put("valid","true");
            int chargeMoney = Integer.parseInt(request.getParameter("money"));//获取用户充值金额
            userDAO userDAO = new usersDAOimpl();
            int money = userDAO.getMoney(name);
            money += chargeMoney;

            if (userDAO.chargeMoney(money, name)) {
                jsonObject.put("result","true");
            }else{
                jsonObject.put("result","false");
            }
        }
        PrintWriter out=response.getWriter();
        out.println(jsonObject);
    }

    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        this.doPost(request,response);
    }
}
