package website.Servlet.Park;

import website.DAO.impl.park_rentDAOimpl;
import website.DAO.park_rentDAO;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.io.IOException;

@WebServlet(name = "ParkDeleteServlet")
public class ParkDeleteServlet extends HttpServlet {
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

        park_rentDAO parkDAO=new park_rentDAOimpl();
        String id=request.getParameter("parkId");
        String msg="删除停车位失败";
        if(parkDAO.delete(id)){
            msg="删除停车位成功";
        }
        request.setAttribute("message",msg);
        request.getRequestDispatcher("/message.jsp").forward(request,response);

    }

    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        this.doPost(request,response);
    }
}
