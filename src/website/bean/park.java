package website.bean;

/**
 *停车位的信息
 * 停车位存在两个状态码，
 * 0代表未出租
 * 1代表已出租
 */
public class park {
    public int hour_price;//每小时的收费
    public int day_price;//每天的收费标准
    public int park_status;//停车位是否可用，true表可用
    public int parkId;//车位的ID，由系统进行编号
    public String rentParkUserName;//租借车位的用户姓名
    public String picture_name;//照片的名称
    public String park_location;//停车位地点
    public String tel;//联系方式
    public boolean is_order;//停车位是否已经预定
    public String rentParkStartTime;//租停车位开始时间
    public String rentParkEndTime;//还停车位时间

    public park() {
    }

    public park(int parkId) {
    }


    public int getParkId() {
        return parkId;
    }

    public void setParkId(int parkId) {
        this.parkId = parkId;
    }

    public int getHour_price() {
        return hour_price;
    }

    public void setHour_price(int hour_price) {
        this.hour_price = hour_price;
    }

    public int getDay_price() {
        return day_price;
    }

    public void setDay_price(int day_price) {
        this.day_price = day_price;
    }

    public int isPark_status() {
        return park_status;
    }

    public void setPark_status(int park_status) {
        this.park_status = park_status;
    }

    public int getPark_status() {
        return park_status;
    }

    public String getPark_location() {
        return park_location;
    }

    public void setPark_location(String park_location) {
        this.park_location = park_location;
    }



    public String getPicture_name() {
        return picture_name;
    }

    public void setPicture_name(String picture_name) {
        this.picture_name = picture_name;
    }

    public String getRentParkUserName() {
        return rentParkUserName;
    }

    public void setRentParkUserName(String rentParkUserName) {
        this.rentParkUserName = rentParkUserName;
    }
    public String getTel() {
        return tel;
    }

    public void setTel(String tel) {
        this.tel = tel;
    }
    public boolean isIs_order() {
        return is_order;
    }

    public void setIs_order(boolean is_order) {
        this.is_order = is_order;
    }

    public String getRentParkStartTime() {
        return rentParkStartTime;
    }

    public void setRentParkStartTime(String rentParkStartTime) {
        this.rentParkStartTime = rentParkStartTime;
    }

    public String getRentParkEndTime() {
        return rentParkEndTime;
    }

    public void setRentParkEndTime(String rentParkEndTime) {
        this.rentParkEndTime = rentParkEndTime;
    }
}