/**
 * ===============================================================
 * File name : User.java
 * Created by injeahwang on 2021-05-24
 * ===============================================================
 */
package com.example.tobimyway.domain.entity;

import com.example.tobimyway.domain.enums.Level;
import lombok.Getter;
import lombok.Setter;
import org.hibernate.annotations.DynamicInsert;
import org.hibernate.annotations.DynamicUpdate;

import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;

@Setter
@Getter
@Entity
@Table(name = "users")
@DynamicInsert
@DynamicUpdate
public class User {

    @Id
    private String id;
    private String name;
    private String password;
    private Level level;
    private int login;
    private int recommend;

    private String email;

    public User(){
        //기존 코드와 하위 호환성을 위해 no argument 생성자
    }

    public User(String id, String name, String password, Level level, int login, int recommend) {
        this.id = id;
        this.name = name;
        this.password = password;
        this.level = level;
        this.login = login;
        this.recommend = recommend;
    }

    public User(String id, String name, String password, String email, Level level, int login, int recommend) {
        this.id = id;
        this.name = name;
        this.password = password;
        this.level = level;
        this.login = login;
        this.recommend = recommend;
        this.email = email;
    }

    public void upgradeLevel(){
        Level nextLevel = this.level.nextLevel();
        if(nextLevel==null){
            throw new IllegalStateException(this.level + "은 업그레이드가 불가능 합니다.");
        }
        else{
            this.level = nextLevel;
        }
    }

    @Override
    public String toString(){
        return "USER(id = "+this.id+", name : "+this.name+", " + "password : "
            +this.password+ "level : "+this.level + " login : "+this.login+" recommend : "+this.recommend+")";
    }



    public static void userSample(){
        User user = new User();
        user.setId("1");
        user.setName("jay");
        user.setPassword("123");
        user.setLevel(Level.BASIC);
        System.out.println("USER SAMPLE : " + user.toString());
    }

}
