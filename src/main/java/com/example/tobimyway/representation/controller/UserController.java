/**
 * ===============================================================
 * File name : UserController.java
 * Created by injeahwang on 2021-07-22
 * ===============================================================
 */
package com.example.tobimyway.representation.controller;

import com.example.tobimyway.domain.entity.User;
import com.example.tobimyway.domain.service.UserService;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
@RequestMapping("/board")
public class UserController {

    public final UserService userService;

    public UserController(UserService userService){
        this.userService = userService;
    }

    @GetMapping("/list")
    public String userList(Model model){
        model.addAttribute("userList", this.userService.retrieveAllusers());
        return "/board/list";
    }

    @GetMapping("/form")
    public String form(){
        return "/board/form";
    }


    @PostMapping("/user")
    public ResponseEntity<?> addUser(@RequestBody User user){
        this.userService.add(user);

        return new ResponseEntity<>("{}", HttpStatus.CREATED);
    }

    @PostMapping("/mailsender")
    public ResponseEntity<?> mailSender(@RequestBody User user){
        this.userService.sendTestMail(user.getId());
        return new ResponseEntity<>("{}", HttpStatus.OK);
    }

}
