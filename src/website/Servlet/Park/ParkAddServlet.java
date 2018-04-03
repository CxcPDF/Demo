package website.Servlet.Park;

import website.bean.park;
import website.DAO.impl.park_rentDAOimpl;
import website.DAO.park_rentDAO;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.io.IOException;

@WebServlet(name = "ParkAddServlet")
public class ParkAddServlet extends HttpServlet {
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        request.setCharacterEncoding("UTF-8");
        response.setContentType("text/html;charset=UTF-8");

        String name=request.getParameter("userName");

        HttpSession session=request.getSession();
        if(session.getAttribute("userId")==null||!session.getAttribute("userId").equals(name)){
            String msg="当前登录已失效，请重新登录！";
            request.setAttribute("message",msg);
            request.getRequestDispatcher("/message.jsp").forward(request,response);
        }

        park park=new park();
        park.setDay_price(Integer.parseInt(request.getParameter("day_price")));
        park.setHour_price(Integer.parseInt(request.getParameter("hour_price")));
        park.setPark_location(request.getParameter("location"));
        park.setRentParkUserName("");
        park.setPark_status(0);//此时车位可出租
        park.setTel(request.getParameter("tel"));//联系方式


        String msg="添加停车位失败";
        park_rentDAO park_rentDAO=new park_rentDAOimpl();
        if(park_rentDAO.add(park)){
            msg="添加停车位成功";
        }
        request.setAttribute("message",msg);
        request.getRequestDispatcher("/message.jsp").forward(request,response);

    }

    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        this.doPost(request,response);
    }
}
