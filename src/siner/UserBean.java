//这是一个userbean<---->users表的映射
//他的一个对象<---->users表的一个记录对应
//数据
package siner;

/**
 * Created by Siner on 2017-07-24.
 */
public class UserBean {
    private int UserId;
    private String Password;
    private String Name;
    private String Tel;
    private String Dep;
    private String Mail;
    private String Classes;

    public void setUserId(int userId){
        this.UserId=userId;
    }

    public int getUserId() {
        return UserId;
    }

    public String getPassword() {
        return Password;
    }

    public void setPassword(String password) {
        Password = password;
    }

    public String getName() {
        return Name;
    }

    public void setName(String name) {
        Name = name;
    }

    public String getTel() {
        return Tel;
    }

    public void setTel(String tel) {
        Tel = tel;
    }

    public String getDep() {
        return Dep;
    }

    public void setDep(String dep) {
        Dep = dep;
    }

    public String getMail() {
        return Mail;
    }

    public void setMail(String mail) {
        Mail = mail;
    }

    public String getClasses() {
        return Classes;
    }

    public void setClasses(String classes) {
        Classes = classes;
    }
}
