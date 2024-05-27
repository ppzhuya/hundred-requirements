package org.ppzhu.hundredrequirements.pojo;


public class User {

  private String uid;
  private String uname;
  private String upwd;

  @Override
  public String toString() {
    return "User{" +
            "uid='" + uid + '\'' +
            ", uname='" + uname + '\'' +
            ", upwd='" + upwd + '\'' +
            '}';
  }

  public String getUid() {
    return uid;
  }

  public void setUid(String uid) {
    this.uid = uid;
  }


  public String getUname() {
    return uname;
  }

  public void setUname(String uname) {
    this.uname = uname;
  }


  public String getUpwd() {
    return upwd;
  }

  public void setUpwd(String upwd) {
    this.upwd = upwd;
  }

}
