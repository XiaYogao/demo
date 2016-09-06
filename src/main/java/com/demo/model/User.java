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
