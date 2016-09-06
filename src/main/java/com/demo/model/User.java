package com.demo.model;

import java.util.Date;

/**
 * 用户
 *
 * @author sharygus@gmail.com
 * @date 2016/9/6 14:41
 * @since V1.0
 */
public class User {

    private Long id;
    private String username;
    private String password;
    private Integer age;
    private Gender gender;
    private Date birthday;


    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public Integer getAge() {
        return age;
    }

    public void setAge(Integer age) {
        this.age = age;
    }

    public Gender getGender() {
        return gender;
    }

    public void setGender(Gender gender) {
        this.gender = gender;
    }

    public Date getBirthday() {
        return birthday;
    }

    public void setBirthday(Date birthday) {
        this.birthday = birthday;
    }

    @Override
    public String toString() {
        return "User{" +
                "id=" + id +
                ", username='" + username + '\'' +
                ", password='" + password + '\'' +
                ", age=" + age +
                ", gender=" + gender +
                ", birthday=" + birthday +
                '}';
    }

    /**
     * 描述：性别
     *
     * @author xiayonggao
     */
    enum Gender{
        MALE(0, "女"),
        FAMEL(1, "男");

        private Integer val;
        private String title;

        Gender(Integer val, String title){
            this.val = val;
            this.title = title;
        }

        public Integer getVal(){
            return this.val;
        }

        public String getTitle(){
            return this.title;
        }
    }
}
