//Wrtitten By Peng DF
//Modified BY Hao Wang
package website.Servlet.Search;

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

//核心模块 模糊搜索所有车的信息 包括各个状态的 通过身份验证控制不同用户的权限

@WebServlet(name = "SearchServlet")
public class SearchServlet extends HttpServlet {
    String sql, brand, owner, status, type, displacement, renter, age, mileage, lowPrice, highPrice,statusCode,ID,startdate,carName;

    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        request.setCharacterEncoding("UTF-8");
        response.setContentType("text/html;charset=UTF-8");

        String name=request.getParameter("userName");
        PrintWriter out=response.getWriter();

        HttpSession session=request.getSession();
        JSONObject jsonObject=new JSONObject();
        //车的状态码  0 出租待审核  1 等待出租 2 正在出租 3 已经还车，等待审核
        //未登录不可查询除1的状态码  登陆后只可查询拥有者与自己相同的0状态码或者租车者与自己相同的3状态码 管理员(admin)除外
        if(!request.getParameter("StatusCode").equals("1")&&(session.getAttribute("userId") == null || !session.getAttribute("userId").equals(name))){
                System.out.println(1);
                jsonObject.put("valid", "false");
                out.println(jsonObject);
            }else if(request.getParameter("StatusCode").equals("0")&&!name.equals(request.getParameter("owner"))&&!name.equals("admin")){
            System.out.println(2);
             jsonObject.put("valid", "false");
             out.println(jsonObject);
        }else if((request.getParameter("StatusCode").equals("2")||request.getParameter("StatusCode").equals("3"))&&(!name.equals(request.getParameter("renter"))&&!name.equals(request.getParameter("owner")))&&!name.equals("admin")){
            System.out.println(3);
            jsonObject.put("valid", "false");
            out.println(jsonObject);
        }
        else{
            brand = request.getParameter("brand");//获取ID
            owner = request.getParameter("owner");//车的拥有者
            status = request.getParameter("carstatus");//车的状态
            type = request.getParameter("type");//车的类型
            displacement = request.getParameter("displacement");//车的排量
            age = request.getParameter("age");//车龄
            mileage = request.getParameter("mileage");//里程
            renter = request.getParameter("renter");//租借者
            lowPrice = request.getParameter("lowprice");//租金的最低价格
            highPrice = request.getParameter("highPrice");//租金的最高价格
            statusCode = request.getParameter("StatusCode");//获取状态码
            ID = request.getParameter("id");
            startdate=request.getParameter("startdate");
            carName=request.getParameter("carname");

            StringBuilder str = new StringBuilder();
            str.append("select * from car where 1=1 ");
            if (brand != "" && brand != null) {
                str.append("and pingpai=\"" + brand + "\" ");
            }
            if (owner != "" && owner != null) {
                str.append("and ownerName=\"" + owner + "\" ");
            }
            if (status != "" && status != null) {
                str.append("and statusCode=" + status + " ");
            }
            if (type != "" && type != null) {
                str.append("and type=\"" + type + "\" ");
            }
            if (displacement != "" && displacement != null) {
                str.append("and pailiang=" + displacement + " ");
            }
            if (age != "" && age != null) {
                str.append("and age=" + age + " ");
            }
            if (mileage != "" && mileage != null) {
                str.append("and licheng=" + mileage + " ");
            }
            if (renter != "" && renter != null) {
                str.append("and rentCarUserName=\"" + renter + "\" ");
            }
            if (lowPrice != "" && lowPrice != null) {
                str.append("and day_price>=" + lowPrice + " ");
            }
            if (highPrice != "" && highPrice != null) {
                str.append("and day_price=<" + highPrice + " ");
            }
            if (statusCode != "" && statusCode != null) {
                str.append("and statusCode=" + statusCode + " ");
            }
            if (ID != "" && ID != null) {
                str.append("and ID=" + ID + " ");
            }
            if(startdate!=""&&startdate!=null){
                str.append("and rentCarDate=\""+startdate+"\" ");
            }
            if(carName!=""&&carName!=null){
                str.append("and carName=\""+carName+"\"");
            }
            sql = str.toString();

            System.out.println(sql);
            DBManager dbManager = new DBManager();
            ResultSet set = dbManager.query(sql);

            JSONObject objects = new JSONObject();
            int i = 0;
            try {
                while (set.next()) {
                    JSONObject obj = new JSONObject();
                    String carName = set.getString("carName");
                    int day_price = set.getInt("day_price");
                    int month_price = set.getInt("month_price");
                    boolean carStatus = set.getBoolean("car_status");
                    String pictureName = set.getString("picture_name");
                    String rentCarDate = set.getString("rentCarDate");
                    String rentCarUserName = set.getString("rentCarUserName");
                    String ownerName = set.getString("ownerName");
                    String brand = set.getString("pingpai");
                    String type = set.getString("type");
                    String getCarLocation = set.getString("getCarLocation");
                    String returnCarLocation = set.getString("returnCarLocation");
                    double displacement = set.getDouble("pailiang");
                    double mileage = set.getDouble("licheng");
                    double age = set.getDouble("age");
                    int StatusCode = set.getInt("statusCode");
                    int ID = set.getInt("ID");
                    String orderCode = set.getString("orderCode");

                    obj.put("id", ID);
                    obj.put("carName", carName);
                    obj.put("carStatus", carStatus);
                    obj.put("pictureName", pictureName);
                    obj.put("rentCarDate", rentCarDate);
                    obj.put("rentCarUserName", rentCarUserName);
                    obj.put("ownerName", ownerName);
                    obj.put("day_price", day_price);
                    obj.put("month_price", month_price);
                    obj.put("brand", brand);
                    obj.put("type", type);
                    obj.put("getCarLocation", getCarLocation);
                    obj.put("returnCarLocation", returnCarLocation);
                    obj.put("displacement", displacement);
                    obj.put("mileage", mileage);
                    obj.put("age", age);
                    obj.put("statusCode", StatusCode);
                    obj.put("orderCode", orderCode);

                    objects.put(i, obj);
                    i++;
                }

            } catch (SQLException e) {
                e.printStackTrace();
            }
            out.println(objects);
        }
    }

    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        this.doPost(request, response);
    }
}
