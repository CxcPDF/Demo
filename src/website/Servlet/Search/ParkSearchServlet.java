package website.Servlet.Search;

import org.json.simple.JSONObject;
import website.DataBase.DBManager;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.sql.ResultSet;
import java.sql.SQLException;

@WebServlet(name = "ParkSearchServlet")
public class ParkSearchServlet extends HttpServlet {
    String sql,lowPrice,highPrice,parkStatus,parkId,rentParkUserName,
    pictureName,parkLocation,rentParkDate;
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        lowPrice=request.getParameter("lowPrice");
        highPrice=request.getParameter("highPrice");
        parkStatus=request.getParameter("parkStatus");
        parkId=request.getParameter("parkId");
        rentParkUserName=request.getParameter("rentParkUserName");
        pictureName=request.getParameter("pictureName");
        parkLocation=request.getParameter("parkLocation");
        rentParkDate=request.getParameter("rentParkDate");

        StringBuilder str=new StringBuilder();
        str.append("select * from car where 1=1 ");
        if(lowPrice!=""&&lowPrice!=null){
            str.append("and hour_price>="+lowPrice+" ");
        }
        if(highPrice!=""&&highPrice!=null){
            str.append("and hour_price<="+highPrice+" ");
        }
        if(parkStatus!=""&&parkStatus!=null){
            str.append("and park_status="+parkStatus+" ");
        }
        if(parkId!=""&&parkId!=null){
            str.append("and parkId="+parkId+" ");
        }
        if(rentParkUserName!=""&&rentParkUserName!=null){
            str.append("and rentParkUserName=\""+rentParkUserName+"\" ");
        }
        if(pictureName!=""&pictureName!=null){
            str.append("and picture_name=\""+pictureName+"\" ");
        }
        if(parkLocation!=""&&parkLocation!=null){
            str.append("and park_location=\""+parkLocation+"\" ");
        }
        if(rentParkDate!=""&&rentParkDate!=null){
            str.append("and rentParkDate=\""+rentParkDate+"\" ");
        }

        sql=str.toString();
        DBManager dbManager=new DBManager();
        ResultSet set=dbManager.query(sql);

        JSONObject objects=new JSONObject();
        JSONObject obj=new JSONObject();
        int i=0;
        try {
            while (set.next()){
                int hour_price= set.getInt("hour_price");
                int day_price= set.getInt("day_price");
                int park_status= set.getInt("park_status");
                int parkId=set.getInt("parkId");
                String rentParkUserName=set.getString("rentParkUserName");
                String picture_name=set.getString("picture_name");
                String park_location=set.getString("park_location");
                String rentParkDate=set.getString("rentParkDate");

                obj.put("hour_price",hour_price);
                obj.put("day_price",day_price);
                obj.put("park_status",park_status);
                obj.put("parkId",parkId);
                obj.put("rentParkUserName",rentParkUserName);
                obj.put("picture_name",picture_name);
                obj.put("park_location",park_location);
                obj.put("rentParkDate",rentParkDate);

                objects.put(i,obj);
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        System.out.println(objects);
    }

    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        this.doPost(request,response);
    }
}
