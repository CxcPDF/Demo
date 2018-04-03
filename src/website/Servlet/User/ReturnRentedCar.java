package website.Servlet.User;

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
import java.sql.ResultSet;
import java.sql.SQLException;

@WebServlet(name = "ReturnRentedCar")
public class ReturnRentedCar extends HttpServlet {
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        request.setCharacterEncoding("UTF-8");
        response.setContentType("text/html;charset=UTF-8");
        PrintWriter out=response.getWriter();
        String name=request.getParameter("username");
        JSONObject jsonObjects=new JSONObject();
        HttpSession session=request.getSession();

        if(session.getAttribute("userId")==null||!session.getAttribute("userId").equals(name)){
            jsonObjects.put("valid","false");
        }else{
            String sql="select * from `"+name+"` where orderStatus=1";
            DBManager dbManager = new DBManager();
            ResultSet set = dbManager.query(sql);
            int i=0;
            try{
                while (set.next()){
                    JSONObject jsonObject=new JSONObject();
                    int id=set.getInt("itemId");
                    String carname=set.getString("orderItems");
                    String startdate=set.getString("rentTime");
                    String enddate=set.getString("returnTime");
                    int cost=set.getInt("cost");

                    jsonObject.put("id",id);
                    jsonObject.put("carname",carname);
                    jsonObject.put("startdate",startdate);
                    jsonObject.put("enddate",enddate);
                    jsonObject.put("cost",cost);
                    jsonObjects.put(i,jsonObject);
                    i++;
                }

            }catch (SQLException e){
                e.printStackTrace();
            }
        }
        out.println(jsonObjects);
    }

    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        doPost(request,response);
    }
}
