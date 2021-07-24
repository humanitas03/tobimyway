/**
 * =============================================================== File name : UserService.java
 * Created by injeahwang on 2021-06-27 ===============================================================
 */
package com.example.tobimyway.domain.logic;

import com.example.tobimyway.domain.entity.User;
import com.example.tobimyway.domain.enums.Level;
import com.example.tobimyway.domain.service.UserService;
import com.example.tobimyway.infra.UserJpaStore;
import com.example.tobimyway.domain.store.UserStore;
import com.example.tobimyway.util.EmailValidator;
import lombok.extern.slf4j.Slf4j;
import org.springframework.mail.MailSender;
import org.springframework.mail.SimpleMailMessage;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;


import java.util.List;
import java.util.UUID;


@Transactional
@Service
@Slf4j
public class UserServiceImpl implements UserService {
  private final UserStore userJpaStore;
  private final MailSender mailSender;

  public static final int MIN_LOGOUT_FOR_SILVER = 50;
  public static final int MIN_RECCOMEND_FOR_GOLD = 30;

  public UserServiceImpl(UserJpaStore userJpaStore, MailSender mailSender){
    this.userJpaStore = userJpaStore;
    this.mailSender = mailSender;
  }

  @Override
  public void upgradeLevels() {
    List<User> users = userJpaStore.getAll();
    for(User user: users){

      if(canUpgradeLevel(user)) {
        upgradeLevel(user);
      }
    }
  }

  @Override
  public List<User> retrieveAllusers() {
    return this.userJpaStore.getAll();
  }

  @Override
  public void sendTestMail(String userId) {
    User receiver = this.userJpaStore.get(userId);
    SimpleMailMessage mailMessage = new SimpleMailMessage();

    if(!EmailValidator.isValidEmail(receiver.getEmail())) {
      log.error("Email 정규식 검정 요류 ");
      throw new RuntimeException("Email 검정 오류");  //귀찮아서 ExceptionHandler와 커스텀 exception정의 x
    }

    mailMessage.setTo(receiver.getEmail());
    mailMessage.setFrom("jayhwang0305@gmail.com");
    mailMessage.setSubject("테스트 메일 전송입니다.");
    mailMessage.setText("테스트 메일 입니다.\n\n  User 정보 : " + receiver + "\n");

    this.mailSender.send(mailMessage);
  }

  //사용자 신규 등록 로직
  @Override
  public void add(User user){
    if(user.getLevel()==null){
      user.setLevel(Level.BASIC);
    }

    // ID 입력
    user.setId(UUID.randomUUID().toString());

    userJpaStore.add(user);
  }

  private boolean canUpgradeLevel(User user){
    Level currentLevel = user.getLevel();
    switch(currentLevel){
      case BASIC: return (user.getLogin()>=MIN_LOGOUT_FOR_SILVER);
      case SILVER: return (user.getRecommend()>=MIN_RECCOMEND_FOR_GOLD);
      case GOLD: return false;
      default: throw new IllegalArgumentException("Unknown Level : "+ currentLevel);
    }
  }

  protected void upgradeLevel(User user){
    user.upgradeLevel();
    userJpaStore.update(user);
    sendUpgradeEMail(user);
  }

  private void sendUpgradeEMail(User user){

    SimpleMailMessage mailMessage = new SimpleMailMessage();

    if(!EmailValidator.isValidEmail(user.getEmail())) {
      log.error("Email 정규식 검정 요류 ");
      throw new RuntimeException("Email 검정 오류");  //귀찮아서 ExceptionHandler와 커스텀 exception정의 x
    }

    mailMessage.setTo(user.getEmail());
    mailMessage.setFrom("jayhwang0305@gmail.com");
    mailMessage.setSubject("Upgrade 안내");
    mailMessage.setText("사용자님의 등급이 " + user.getLevel().name() + "로 업그레이드 되었습니다.");

    this.mailSender.send(mailMessage);
  }




}
