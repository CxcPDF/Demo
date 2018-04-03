package website.bean;

import javax.xml.crypto.Data;

/*
新闻信息
 */
public class news {
    public String newsTitle;//新闻标题
    public String context;//新闻内容
    public Data createTime;//新闻发布时间

    public news(){

    }

    public news(String newsTitle, String context, Data createTime) {
        this.newsTitle = newsTitle;
        this.context = context;
        this.createTime = createTime;
    }



    public String getNewsTitle() {
        return newsTitle;
    }

    public void setNewsTitle(String newsTitle) {
        this.newsTitle = newsTitle;
    }

    public String getContext() {
        return context;
    }

    public void setContext(String context) {
        this.context = context;
    }

    public Data getCreateTime() {
        return createTime;
    }

    public void setCreateTime(Data createTime) {
        this.createTime = createTime;
    }


}
