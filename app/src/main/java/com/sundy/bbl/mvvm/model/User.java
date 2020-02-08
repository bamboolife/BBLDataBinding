package com.sundy.bbl.mvvm.model;

/**
 * @author bamboo
  @version V1.0
 * @ProjectName: a
 * @ClassName: User
 * @Package com.sundy.bbl.mvvm.model
 * @Description:
 * @date 2020-02-07 23:03
 * @UpdateRemark 更新说明：
 **/

public class User {
    private String name;
    private String password;

    public User(String name) {
        this.name = name;
    }

    public User(String name, String password) {
        this.name = name;
        this.password = password;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }
}
