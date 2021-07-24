///**
// * ===============================================================
// * File name : ControllerConfiguration.java
// * Created by injeahwang on 2021-07-24
// * ===============================================================
// */
//package com.example.tobimyway.domain;
//
//import com.example.tobimyway.domain.logic.UserServiceImpl;
//import com.example.tobimyway.domain.service.UserService;
//import com.example.tobimyway.representation.controller.UserController;
//import org.springframework.context.annotation.Bean;
//import org.springframework.context.annotation.Configuration;
//
//@Configuration
//public class ControllerConfiguration {
//
//    @Bean
//    public UserController userController(){
//        UserService userService = new UserServiceImpl();
//        UserController userController = new UserController();
//        userController.setUserService(userService);
//        return userController;
//    }
//}
