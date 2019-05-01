package net.hunau.entity;


public class User {
    private int id;
    private String name;
    private String pwd;
    private String sexy;
    private boolean isused;

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getPwd() {
        return pwd;
    }

    public void setPwd(String pwd) {
        this.pwd = pwd;
    }

    public String getSexy() {
        return sexy;
    }

    public void setSexy(String sexy) {
        this.sexy = sexy;
    }

    public boolean isIsused() {
        return isused;
    }

    public void setIsused(boolean isused) {
        this.isused = isused;
    }

    /*@Override
    public String toString() {
        return "User{" +
                "id=" + id +
                ", name='" + name + '\'' +
                ", pwd='" + pwd + '\'' +
                ", sexy='" + sexy + '\'' +
                ", isused=" + isused +
                '}';
    }*/
    @Override
    public String toString(){
        String result = "";
        result += "编号：" + this.id + "，";
        result += "用户名：" + this.name + "，";
        result += "密码：" + this.pwd + "， ";
        result += "性别：" + this.sexy + "，";
        if(this.isused==true){
            result += "是否有效：是";
        }else{
            result += "是否有效：否";
        }
        return result;
    }
}
