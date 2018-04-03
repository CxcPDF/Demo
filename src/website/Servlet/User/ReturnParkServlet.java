package website.Servlet.User;

import website.bean.park;
import website.bean.user;
import website.common.date;
import website.DAO.impl.park_rentDAOimpl;
import website.DAO.impl.usersDAOimpl;
import website.DAO.park_rentDAO;
import website.DAO.userDAO;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.io.IOException;
import java.util.Date;

@WebServlet(name = "ReturnParkServlet")
public class ReturnParkServlet extends HttpServlet {
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

        int parkId= Integer.parseInt(request.getParameter("parkId"));//获取要还的停车位的ID号
        park park=new park(parkId);
        park.setPark_status(1);//此车位又可以重新租借出去
        park.setRentParkUserName("");
        //还停车位时间
        String returnTime=new date().dateFormat(new Date());
        String orderStatus="已完成";
        park_rentDAO park_rentDAO=new park_rentDAOimpl();
        long rentTime=park_rentDAO.getRentTime(parkId);//租借停车位时间

        String msg="";
        userDAO userDAO=new usersDAOimpl();
        int money=userDAO.getMoney(name);
        if(money<0){
            msg="您的账户余额不足，请进行充值！";
            request.setAttribute("message",msg);
            request.getRequestDispatcher("/message.jsp").forward(request,response);
            return;
        }

        user user=new user(name);
        //更新数据库

        String sql0="update "+name+" set orderStatus=\""+orderStatus+
                "\",returnTime="+returnTime+" where orderItems=\"停车位"+parkId+"\";";
        System.out.println(sql0);

        userDAO.update(user,sql0);

        String sql="update car set park_status="+park.getPark_status()+",rentCarUserName=\" \" where parkId="+parkId+";";


        if(park_rentDAO.update(park,sql)==1){
            msg="还停车位成功！";
        }else{
            msg="还停车位失败！";
        }
        request.setAttribute("message",msg);
        request.getRequestDispatcher("/message.jsp").forward(request,response);
    }

    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        this.doPost(request,response);
    }
}
