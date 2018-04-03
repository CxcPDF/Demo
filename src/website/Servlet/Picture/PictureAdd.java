package website.Servlet.Picture;

import website.bean.picture;
import website.DAO.impl.pictureDAOimpl;
import website.DAO.pictureDAO;

public class PictureAdd {
    public PictureAdd(){

    }

    public void add(String savePath,String name){
        picture picture=new picture();
        picture.setName(name);
        picture.setPath(savePath);

        pictureDAO pictureDAO=new pictureDAOimpl();
        String msg="添加图片失败！";
        if(pictureDAO.AddPicture(picture)){
            msg="添加图片成功！";
        }
        System.out.println(msg);
    }
}
