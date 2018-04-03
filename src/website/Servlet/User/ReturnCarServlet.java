package website.Servlet.User;

import org.json.simple.JSONObject;
import website.bean.car;
import website.bean.user;
import website.common.date;
import website.DAO.car_rentDAO;
import website.DAO.impl.car_rentDAOimpl;
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
import java.util.Date;
import java.util.Vector;

@WebServlet(name = "ReturnCarServlet")
public class ReturnCarServlet extends HttpServlet {
    protected void doPost(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        request.setCharacterEncoding("UTF-8");
        response.setContentType("text/html;charset=UTF-8");
        JSONObject jsonObject=new JSONObject();
        String rentCarUserName=request.getParameter("userName");
        HttpSession session=request.getSession();
        PrintWriter out=response.getWriter();
        if(session.getAttribute("userId")==null||!session.getAttribute("userId").equals(rentCarUserName)){
            jsonObject.put("valid","false");
        }else {
            String carName = request.getParameter("carName");
            String ID = request.getParameter("ID");

            car car = new car(carName);
            car.setID(Integer.parseInt(ID));
            car.setCar_status(false);
            car.setRentCarUserName("");//此时这辆车没有人租借
            car.setStatusCode(3);//此时订单已经完成，状态码设为3
            car.setCarName(carName);
            car_rentDAO car_rentDAO = new car_rentDAOimpl();

            //还车时间
            String returnTime = new date().dateFormat(new Date());
            //获取租车时间
            String rentTime = car_rentDAO.getRentTime(ID);
            //该用户订单的状态为已完成
            int orderStatus = 1;
            //总的租借时间
            Vector v = new Vector();
            v = new date().dateChange(rentTime, returnTime);
            int month = (int) v.get(0);
            int day = (int) v.get(1);
            //租车费用
            int[] a = car_rentDAO.getPrice(rentCarUserName);
            double cost = month * a[0] + day * a[1];

            String msg = "";
            userDAO userDAO = new usersDAOimpl();
            jsonObject.put("money",cost);

            user user = new user(rentCarUserName);
            String sql0 = "update `" + rentCarUserName + "` set returnTime=\"" + returnTime
                    + "\", cost=" + cost + ", orderStatus=\"" + orderStatus + "\" where " +
                    "itemId=" + ID + ";";

            userDAO.update(user, sql0);//将用户的订单状态进行保存

            //更新数据库
            String sql = "update car set car_status=" + car.getCar_status() +
                    ",statusCode=" + car.getStatusCode() + " where ID=" + car.getID() + ";";

            if (car_rentDAO.update(sql) == 1) {
                jsonObject.put("result","true");
            } else {
                jsonObject.put("result","false");
            }
        }
        out.println(jsonObject);
    }

    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        this.doPost(request,response);
    }
}
