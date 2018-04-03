package website.Servlet.Car;

import org.json.simple.JSONObject;
import website.bean.car;
import website.DAO.car_rentDAO;
import website.DAO.impl.car_rentDAOimpl;
import website.common.date;

import javax.servlet.http.HttpSession;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.Date;

public class CarAddServlet extends javax.servlet.http.HttpServlet {
    protected void doPost(javax.servlet.http.HttpServletRequest request, javax.servlet.http.HttpServletResponse response) throws javax.servlet.ServletException, IOException {
        request.setCharacterEncoding("UTF-8");
        response.setContentType("text/html;charset=UTF-8");

        String name=request.getParameter("username");
        JSONObject jsonObject=new JSONObject();
        HttpSession session=request.getSession();
        PrintWriter out=response.getWriter();

        if(session.getAttribute("userId")==null||!session.getAttribute("userId").equals(name)){
            jsonObject.put("valid","false");
        }else {

            car info = new car();

            info.setCarName(request.getParameter("carName"));//发布的租车的详细信息，比如车的型号加上车牌号
            info.setCar_status(false);//增加车辆的的状态都是可借
            //由用户来确定租金，所以从前端页面获取
            info.setDay_price(Integer.parseInt(request.getParameter("day_price")));
            info.setMonth_price(Integer.parseInt(request.getParameter("month_price")));
            info.setOwnerName(name);//获取发布租车信息的用户名，即拥有者的姓名
            info.setAge(Double.parseDouble(request.getParameter("age")));//车龄
            info.setPingpai(request.getParameter("brand"));//车的品牌
            info.setPailiang(Double.parseDouble(request.getParameter("displacement")));//车的排量
            info.setLicheng(Double.parseDouble(request.getParameter("mileage")));//车的里程
            info.setType(request.getParameter("type"));//车的类型
            info.setGetCarLocation(request.getParameter("getCarLocation"));//取车地点
            info.setReturnCarLocation(request.getParameter("returnCarLocation"));//还车地点
            info.setStatusCode(0);//设置状态码，此时订单未被审核
            String rentTime = new date().dateFormat(new Date());
            info.setRentCarDate(rentTime);
            //添加图片文件的路径
            //info.setPicture_name("D:\\\\J2EE\\\\picture\\\\" + request.getParameter("userName"));

            car_rentDAO car_rentDAO = new car_rentDAOimpl();

            if (car_rentDAO.add(info)) {
                jsonObject.put("result","true");
            }else{
                jsonObject.put("result","false");
            }
        }
        out.println(jsonObject);
    }

    protected void doGet(javax.servlet.http.HttpServletRequest request, javax.servlet.http.HttpServletResponse response) throws javax.servlet.ServletException, IOException {
        this.doPost(request,response);
    }
}
