///**
// * ===============================================================
// * File name : UserServiceTest.java
// * Created by injeahwang on 2021-07-23
// * ===============================================================
// */
//package com.example.tobimyway.domain;
//
//import com.example.tobimyway.domain.entity.User;
//import com.example.tobimyway.domain.enums.Level;
//import com.example.tobimyway.domain.service.UserService;
//import com.example.tobimyway.domain.store.UserStore;
//import org.junit.jupiter.api.Test;
//import org.mockito.ArgumentCaptor;
//import org.springframework.beans.factory.annotation.Autowired;
//import org.springframework.boot.test.context.SpringBootTest;
//import org.springframework.boot.test.mock.mockito.MockBean;
//import org.springframework.mail.SimpleMailMessage;
//import org.springframework.mail.javamail.JavaMailSender;
//
//import java.util.UUID;
//
//import static org.mockito.Mockito.verify;
//
///** Mock 테스트를 위해서  Bean Overriding을 한번 해줘야 되는 것 같음 */
////@ExtendWith(SpringExtension.class)
////@SpringBootTest(webEnvironment = SpringBootTest.WebEnvironment.RANDOM_PORT)
////@SpringBootTest(classes={UserServiceImpl.class, UserJpaStore.class, JavaMailSenderImpl.class, UserRepository.class})
//@SpringBootTest
////@ContextConfiguration(classes={MailHealthContributorAutoConfiguration.class})
////(MailHealthContributorAutoConfiguration.class)
////@ComponentScan(basePackages = {"com.example.tobimyway"})
//public class UserServiceTest {
//
//    @Autowired
//    UserStore userStore;
//
//    @Autowired
//    UserService userService;
//
//    @MockBean
//    JavaMailSender mailSender;
////
////    @MockBean
////    SimpleMailMessage simpleMailMessage;
//
//    final static User testUser = new User(UUID.randomUUID().toString(), "test", "123", "testUser@gmail.com", Level.BASIC, 1,1);
//
//    @Test
//    public void sendTestMailTest(){
////        ArgumentCaptor<SimpleMailMessage> emailCaptor = ArgumentCaptor.forClass(SimpleMailMessage .class);
//
//        this.userService.add(testUser);
//        this.userService.sendTestMail(testUser.getId());
//
//        verify(mailSender).send(emailCaptor.capture());
//    }
//}
