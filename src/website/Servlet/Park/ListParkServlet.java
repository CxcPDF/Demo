package website.Servlet.Park;

import org.json.simple.JSONObject;
import website.DAO.impl.park_rentDAOimpl;
import website.DAO.park_rentDAO;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.io.PrintWriter;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.Vector;

/**
 *列出未出租的停车位
 */
@WebServlet(name = "ListParkServlet")
public class ListParkServlet extends HttpServlet {
    public static Vector<String> parkVector;

    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        request.setCharacterEncoding("UTF-8");
        response.setContentType("text/html;charset=UTF-8");

        JSONObject objs=new JSONObject();
        PrintWriter out=response.getWriter();

        String sql="select * from park;";
        park_rentDAO park_rentDAO=new park_rentDAOimpl();
        ResultSet set=park_rentDAO.query(sql);
        int i=0;
        try {
            while (set.next()){
                JSONObject obj=new JSONObject();
                int hour_price=set.getInt("hour_price");
                int day_price=set.getInt("day_price");
                boolean park_status=set.getBoolean("park_status");
                int parkId=set.getInt("parkId");
                String parkName=set.getString("park_name");
                String picture_name=set.getString("picture_name");
                String park_location=set.getString("park_location");
                String park_zone=set.getString("park_zone");
                long rentParkDate=set.getLong("rentParkDate");
                int sum_car=set.getInt("sum");
                int use_car=set.getInt("use");

                obj.put("hour_price",hour_price);
                obj.put("day_price",day_price);
                obj.put("park_status",park_status);
                obj.put("parkId",parkId);
                obj.put("parkname",parkName);
                obj.put("picture_name",picture_name);
                obj.put("park_location",park_location);
                obj.put("rentParkDate",rentParkDate);
                obj.put("sum",sum_car);
                obj.put("use",use_car);
                obj.put("zone",park_zone);

                objs.put(i,obj);
                i++;
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }

        out.println(objs);
        System.out.println(objs);
    }

    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        this.doPost(request,response);
    }
}
