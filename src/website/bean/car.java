package website.bean;

/**
 * 出租车辆的信息
 * 订单的状态码包括五种状态
 * 0代表订单已发布但还没被审核
 * 1代表订单已发布且已经审核
 * 2代表订单正在进行中
 * 3代表已经申请还车，管理员还未审核
 * 4代表还车成功且通过管理员审核
 */
public class car {
    public int day_price;//每天的租金
    public int ID;//车辆ID号
    public int month_price;//每月的租金
    public String carName;//车被租借后由“租车用户名”+“该用户租借的第几辆车”组成的ID号
    public String picture_name;//车辆照片的名称
    public boolean car_status;//车辆是否已经租借出去，true为已经租借
    public String rentCarDate;//租车时间
    public String returnTime;//还车时间
    public String rentCarUserName;//租车用户的姓名
    public String ownerName;//车拥有者姓名
    public String pingpai;//车的品牌
    public double pailiang;//车的排量
    public double licheng;//里程
    public double age;//车龄
    public String type;//车的类型
    public String getCarLocation;//取车地点
    public String returnCarLocation;//还车地点
    public int rentedUser;//已经租借过这辆车的人数
    //    public boolean flag;//求租订单是否通过审核，ture代表通过审核，false代表没有通过审核
    public int statusCode;//订单的状态码，
    public String orderCode;//订单编号

    public car() {
    }

    public car(String carName) {

    }

    public car(int day_price, int month_price, String carName, String picture_name,
               boolean car_status, String rentCarDate, String returnTime,
               String rentCarUserName, String ownerName, String pingpai,
               double pailiang, double licheng, double age, String type,
               String getCarLocation, String returnCarLocation, int rentedUser,
               int statusCode, String orderCode) {
        this.day_price = day_price;
        this.month_price = month_price;
        this.carName = carName;
        this.picture_name = picture_name;
        this.car_status = car_status;
        this.rentCarDate = rentCarDate;
        this.returnTime = returnTime;
        this.rentCarUserName = rentCarUserName;
        this.ownerName = ownerName;
        this.pingpai = pingpai;
        this.pailiang = pailiang;
        this.licheng = licheng;
        this.age = age;
        this.type = type;
        this.getCarLocation = getCarLocation;
        this.returnCarLocation = returnCarLocation;
        this.rentedUser = rentedUser;
        this.statusCode = statusCode;
        this.orderCode = orderCode;
    }

    public int getDay_price() {
        return day_price;
    }

    public void setDay_price(int day_price) {
        this.day_price = day_price;
    }

    public int getMonth_price() {
        return month_price;
    }

    public void setMonth_price(int month_price) {
        this.month_price = month_price;
    }

    public String getCarName() {
        return carName;
    }

    public void setCarName(String carName) {
        this.carName = carName;
    }

    public void setCar_status(boolean car_status) {
        this.car_status = car_status;
    }

    public boolean getCar_status() {
        return car_status;
    }

    public String getRentCarDate() {
        return rentCarDate;
    }

    public void setRentCarDate(String rentCarDate) {
        this.rentCarDate = rentCarDate;
    }

    public String getPicture_name() {
        return picture_name;
    }

    public void setPicture_name(String picture_name) {
        this.picture_name = picture_name;
    }

    public String getRentCarUserName() {
        return rentCarUserName;
    }

    public void setRentCarUserName(String rentCarUserName) {
        this.rentCarUserName = rentCarUserName;
    }

    public String getOwnerName() {
        return ownerName;
    }

    public void setOwnerName(String ownerName) {
        this.ownerName = ownerName;
    }

    public String getPingpai() {
        return pingpai;
    }

    public void setPingpai(String pingpai) {
        this.pingpai = pingpai;
    }

    public double getPailiang() {
        return pailiang;
    }

    public void setPailiang(double pailiang) {
        this.pailiang = pailiang;
    }

    public double getLicheng() {
        return licheng;
    }

    public void setLicheng(double licheng) {
        this.licheng = licheng;
    }

    public double getAge() {
        return age;
    }

    public void setAge(double age) {
        this.age = age;
    }

    public String getType() {
        return type;
    }

    public void setType(String type) {
        this.type = type;
    }

    public String getGetCarLocation() {
        return getCarLocation;
    }

    public void setGetCarLocation(String getCarLocation) {
        this.getCarLocation = getCarLocation;
    }

    public String getReturnCarLocation() {
        return returnCarLocation;
    }

    public void setReturnCarLocation(String returnCarLocation) {
        this.returnCarLocation = returnCarLocation;
    }

    public int getRentedUser() {
        return rentedUser;
    }

    public void setRentedUser(int rentedUser) {
        this.rentedUser = rentedUser;
    }

    public String getReturnTime() {
        return returnTime;
    }

    public void setReturnTime(String returnTime) {
        this.returnTime = returnTime;
    }

    public int getStatusCode() {
        return statusCode;
    }

    public void setStatusCode(int statusCode) {
        this.statusCode = statusCode;
    }

    public String getOrderCode() {
        return orderCode;
    }

    public void setOrderCode(String orderCode) {
        this.orderCode = orderCode;
    }

    public int getID() {
        return ID;
    }

    public void setID(int ID) {
        this.ID = ID;
    }
}
