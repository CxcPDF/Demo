package website.Servlet.File;

import org.apache.commons.fileupload.FileItem;
import org.apache.commons.fileupload.FileUploadBase;
import org.apache.commons.fileupload.ProgressListener;
import org.apache.commons.fileupload.disk.DiskFileItemFactory;
import org.apache.commons.fileupload.servlet.ServletFileUpload;
import org.json.simple.JSONObject;
import website.Servlet.Picture.PictureAdd;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.*;
import java.util.List;
import java.util.Random;

//Written By PengDunFu
//Modified By Wanghao
//上传文件，并根据ID放入对应的文件夹中

public class FileUploadServlet extends HttpServlet {
    private static String savePath;
    private static String userName="other";
    private static String fileExtName;
    FileItem fileitem;

    public void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        //准备工作，服务器目录上创建相对于的目录
        JSONObject jsonObject=new JSONObject();
        PrintWriter htmlout=response.getWriter();
        savePath = this.getServletContext().getRealPath("/upload");
        String tempPath = this.getServletContext().getRealPath("/temp");
        File tmpFile = new File(tempPath);
        if (!tmpFile.exists()) {
            tmpFile.mkdir();
        }

        try{
            //创建缓冲区
            DiskFileItemFactory factory = new DiskFileItemFactory();
            factory.setSizeThreshold(1024*100);//设置缓冲区的大小为100KB，如果不指定，那么缓冲区的大小默认是10KB
            factory.setRepository(tmpFile);
            //得到上传的文件对象
            ServletFileUpload upload = new ServletFileUpload(factory);
            upload.setProgressListener(new ProgressListener(){
                public void update(long pBytesRead, long pContentLength, int arg2) {
                    System.out.println("文件大小为：" + pContentLength + ",当前已处理：" + pBytesRead);
            }});
            upload.setHeaderEncoding("UTF-8");
            if(!ServletFileUpload.isMultipartContent(request)){
                return;
            }
            upload.setFileSizeMax(1024*1024*3);
            upload.setSizeMax(1024*1024*10);
            //处理文件对象集合
            List<FileItem> list = upload.parseRequest(request);
            for(FileItem item : list){
                if(item.isFormField()){
                    String name = item.getFieldName();
                    String value = item.getString("UTF-8");
                    if(name.equals("id")) {
                        userName = value;
                    }
                    System.out.println(name + "=" + value);
                }else{
                   fileitem=item;
                }
            }
            String filename = fileitem.getName();
            System.out.println(filename);
            if(filename==null || filename.trim().equals("")){
                jsonObject.put("result","false");
            }else {
                filename = filename.substring(filename.lastIndexOf("\\") + 1);
                fileExtName = filename.substring(filename.lastIndexOf(".") + 1);
                System.out.println("上传的文件的扩展名是：" + fileExtName);

                String saveFilename = makeFileName();
                String realSavePath = makePath(savePath);
                new PictureAdd().add(changePath(realSavePath), saveFilename);

                File file = new File(realSavePath + "\\" + saveFilename);
                fileitem.write(file);

                jsonObject.put("result", "true");
            }
            htmlout.println(jsonObject);

        }catch (FileUploadBase.FileSizeLimitExceededException e) {
            e.printStackTrace();
            jsonObject.put("result","false");
            jsonObject.put("error","file_overflow");
            return;
        }catch (FileUploadBase.SizeLimitExceededException e) {
            e.printStackTrace();
            jsonObject.put("result","false");
            jsonObject.put("error","files_overflow");
            return;
        }catch (Exception e) {
            jsonObject.put("result","false");
            e.printStackTrace();
        }
    }
    //随机生成文件名
    public String makeFileName() {
        String base = "abcdefghijklmnopqrstuvwxyz0123456789";
        Random random = new Random();
        StringBuffer sb = new StringBuffer();
        for (int i = 0; i < 4; i++) {
            int number = random.nextInt(base.length());
            sb.append(base.charAt(number));
        }
        return (userName+"_"+sb.toString()+"."+fileExtName);
    }

    private String makePath(String savePath){
        String dir = savePath + "\\" + userName;
        System.out.println(dir);
        File file = new File(dir);
        if(!file.exists()){
            file.mkdirs();
        }
        return dir;
    }

    public String changePath(String path){
        StringBuffer str=new StringBuffer(path);
        for(int i=0;i<str.length();i++){
            if(str.charAt(i)=='\\'){
                str.insert(i,'\\');
                i++;
            }
        }
        System.out.println(str);
        return (str.toString());
    }

    public void doGet(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {

        this.doGet(request, response);
    }
}
