package website.Servlet.Car;

import org.json.simple.JSONObject;
import website.DAO.car_rentDAO;
import website.DAO.impl.car_rentDAOimpl;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.io.IOException;
import java.io.PrintWriter;

@WebServlet(name = "CarDeleteServlet")
public class CarDeleteServlet extends HttpServlet {
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        request.setCharacterEncoding("UTF-8");
        response.setContentType("text/html;charset=UTF-8");
        PrintWriter out=response.getWriter();
        String name=request.getParameter("userName");
        JSONObject jsonObject=new JSONObject();
        HttpSession session=request.getSession();
        car_rentDAO car = new car_rentDAOimpl();
        String ownner=car.getOwnner(request.getParameter("ID"));
        if(session.getAttribute("userId")==null||!session.getAttribute("userId").equals(name)){
           jsonObject.put("valid","false");
        }else if(!ownner.equals(name)&&!name.equals("admin")) {
            jsonObject.put("valid", "false");
        }else{
            if(car.delete(request.getParameter("ID"))) {
                jsonObject.put("result", "success");
            }else{
                jsonObject.put("result","false");
            }
        }
        out.println(jsonObject);
    }

    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        this.doPost(request,response);
    }
}
