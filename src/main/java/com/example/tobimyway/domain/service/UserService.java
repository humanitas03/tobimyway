/**
 * =============================================================== File name : UserService.java
 * Created by injeahwang on 2021-07-07 ===============================================================
 */
package com.example.tobimyway.domain.service;


import com.example.tobimyway.domain.entity.User;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Transactional
public interface UserService {
  void add(User user);
  void upgradeLevels();
  List<User> retrieveAllusers();
  void sendTestMail(String userId);
}
