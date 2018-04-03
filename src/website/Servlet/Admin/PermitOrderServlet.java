package website.Servlet.Admin;

import org.json.simple.JSONObject;
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
 * 审核用户发布的订单
 */
@WebServlet(name = "PermitOrderServlet")
public class PermitOrderServlet extends HttpServlet {
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        request.setCharacterEncoding("UTF-8");
        response.setContentType("text/html;charset=UTF-8");
        JSONObject jsonObject=new JSONObject();
        HttpSession session=request.getSession();
        PrintWriter out=response.getWriter();
        String ID=request.getParameter("ID");
        String price=request.getParameter("price");
        if(price==null)
            price="0";
        int each_price=Integer.parseInt(price);

        if(session.getAttribute("userId")==null||!session.getAttribute("userId").equals("admin")){
           jsonObject.put("valid","false");
        }else {
            //更新数据库
            String sql = "update car set statusCode=" + 1 +",day_price="+price+ " where ID=" + ID +";";
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
