package website.DAO;

import website.bean.picture;

import java.util.List;
import java.util.Vector;

public interface pictureDAO {
    public boolean AddPicture(picture picture);//添加照片
    public boolean DeletePicture(String name);//删除照片
    public List<picture> ListPictures();//获取所有的照片
    public Vector getPictureSavePath(String itemName);//获取对应物品的图片储存路径，可能有多张图片
}
