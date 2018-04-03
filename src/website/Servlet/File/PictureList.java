package website.Servlet.File;

import org.json.simple.JSONObject;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.*;
import java.net.URLEncoder;

//Written By WangHao
//根据ID返回对应文件夹所有图片的相对路径

public class PictureList extends HttpServlet {

    public void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        response.setContentType("text/html;charset=UTF-8");
        request.setCharacterEncoding("UTF-8");
        String id=request.getParameter("id");
        JSONObject jsonObject=new JSONObject();
        PrintWriter out=response.getWriter();

        String fileSaveRootPath = this.getServletContext().getRealPath("/upload")+"\\"+id;
        String webpath=this.getServletContext().getRealPath("");

        File filepath=new File(fileSaveRootPath);
        if(!filepath.exists()||!filepath.isDirectory()){
            jsonObject.put("result","false");
        }else{
            File[] allpic=filepath.listFiles();
            int i=0;
            for (File eachfile:allpic) {
                if(eachfile.isFile()){
                    String eachpath=eachfile.getPath();
                    String relativepath="/"+eachpath.substring(webpath.length());
                    jsonObject.put(i,relativepath);
                }
            }
        }
        out.println(jsonObject);
    }

    public void doGet(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        doGet(request, response);
    }
}