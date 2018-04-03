package website.Servlet.User;

import org.json.simple.JSONObject;
import website.bean.car;
import website.bean.user;
import website.common.date;
import website.common.makeOrderCode;
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

/**
 * 设置租借的车辆为不可借，同时设置租借此辆车的用户ID
 */
@WebServlet(name = "RentCarServlet")
public class RentCarServlet extends HttpServlet {
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        request.setCharacterEncoding("UTF-8");
        response.setContentType("text/html;charset=UTF-8");

        String name = request.getParameter("username");
        HttpSession session = request.getSession();
        JSONObject jsonObject = new JSONObject();

        if (session.getAttribute("userId") == null || !session.getAttribute("userId").equals(name)) {
            jsonObject.put("valid", "false");
            System.out.println(name);
        } else {
            jsonObject.put("valid", "true");
            String carName = request.getParameter("carName");//获取车辆名称
            String carDis=request.getParameter("carDis");
            String ID = request.getParameter("ID");//获取车辆名称
            String rentTime = new date().dateFormat(new Date());//获取租借的时间
            System.out.println(carName + "-" + ID + "-");
            car car = new car(carName);

            car.setCar_status(true);//表明车已经被租出去
            car.setID(Integer.parseInt(ID));
            car.setRentCarUserName(name);//设置租借这辆车的用户姓名
            car.setRentCarDate(rentTime);//设置租借时间
            car.setStatusCode(2);//设置订单的状态码为2
            car.setOrderCode(new makeOrderCode().make());//设置唯一的订单号

            userDAO userDAO = new usersDAOimpl();
            String msg = "";

            jsonObject.put("MoneyStatus", "true");
            user user = new user(name);
            //更新数据库中的内容
            String sql0 = "insert into `" + name + "` (orderItems,itemId,orderStatus,rentTime,returnTime,cost) values (\"" + carDis +
                    "\","+ID+",0,\"" + rentTime + "\",0,0);";

            userDAO.update(user, sql0);

            String sql = "update car set statusCode=" + car.getStatusCode() +
                    ",rentCarUserName=\"" + car.getRentCarUserName() +
                    "\",rentCarDate=\"" + rentTime + "\" where ID=" + ID + ";";
            car_rentDAO car_rentDAO = new car_rentDAOimpl();

            if (car_rentDAO.update(sql) == 1) {
                jsonObject.put("result", "true");
            } else {
                jsonObject.put("result", "false");
            }

        }
        PrintWriter out = response.getWriter();
        out.println(jsonObject);
    }

    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        this.doPost(request, response);
    }
}
