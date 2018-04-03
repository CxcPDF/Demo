package website.Servlet.User;

import org.json.simple.JSONObject;
import website.DataBase.DBManager;
import website.bean.park;
import website.bean.user;
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
import java.io.PrintWriter;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.Vector;

/**
 * 设置被租借车的状态为不可借，同时添加租借人的ID以及租借时间
 */
@WebServlet(name = "RentParkServlet")
public class RentParkServlet extends HttpServlet {
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        request.setCharacterEncoding("UTF-8");
        response.setContentType("text/html;charset=UTF-8");
        PrintWriter out=response.getWriter();
        String name=request.getParameter("userName");
        int parkId= Integer.parseInt(request.getParameter("parkId"));
        JSONObject jsonObject=new JSONObject();
        HttpSession session=request.getSession();
        if(session.getAttribute("userId")==null||!session.getAttribute("userId").equals(name)){
            jsonObject.put("valid","false");
        }else {
            DBManager dbManager=new DBManager();
            park_rentDAO park_rentDAO=new park_rentDAOimpl();
            String sql="select * from park where parkId="+parkId+";";
            ResultSet set=park_rentDAO.query(sql);
            try {
                while (set.next()) {
                    int sum = set.getInt("sum");
                    int use=set.getInt("use");
                    if(use>=sum){
                        jsonObject.put("result","false");
                    }else{
                        String sqls = "update park set `use`=" +(++use) +
                                " where parkId=" + parkId + ";";
                        if(dbManager.update(sqls)==1) {
                            jsonObject.put("result", "true");
                        }else {
                            jsonObject.put("result","false");
                        }
                    }
                }
            }catch (SQLException e){
                e.printStackTrace();
            }
            //更新数据库
//            String sql0 = "insert into " + name + " (orderItems,orderStatus,rentTime,returnTime,cost) values (\"停车位" + parkId +
//                    "\",\"正在进行中\",\"" + rentStartTime + "\",\"" + rentEndTime + "\"," + cost + ");";

        }
        out.println(jsonObject);

    }

    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        this.doPost(request,response);
    }
}
