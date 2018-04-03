package website.Servlet.Picture;

import website.DAO.impl.pictureDAOimpl;
import website.DAO.pictureDAO;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.io.IOException;

@WebServlet(name = "PictureDeleteServlet")
public class PictureDeleteServlet extends HttpServlet {
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        request.setCharacterEncoding("GB2312");
        response.setContentType("text/html;charset=gb2312");

        HttpSession session=request.getSession();
        if(session.getAttribute("userId")==null){
            String msg="当前登录已失效，请重新登录！";
            System.out.println(msg);
            return;
        }

        String deletePictureName=request.getParameter("pictureName");//获取到要删除的照片的名字
        pictureDAO pictureDAO=new pictureDAOimpl();
        String msg="删除失败!";
        if(pictureDAO.DeletePicture(deletePictureName)){
            msg="删除成功！";
        }
    }

    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        this.doPost(request,response);
    }
}
