package website.bean;

public class picture {
    public String name;//图片的名称
    public String path;//图片储存的路径

    public picture(){}

    public picture(String name, String path) {
        this.name = name;
        this.path = path;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getPath() {
        return path;
    }

    public void setPath(String path) {
        this.path = path;
    }
}
