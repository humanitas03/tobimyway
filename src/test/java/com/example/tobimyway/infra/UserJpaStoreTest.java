/**
 * ===============================================================
 * File name : UserJpaStoreTest.java
 * Created by injeahwang on 2021-07-23
 * ===============================================================
 */
package com.example.tobimyway.infra;

import com.example.tobimyway.domain.entity.User;
import com.example.tobimyway.domain.enums.Level;
import com.example.tobimyway.domain.exception.DuplicateUserIdException;
import com.example.tobimyway.domain.store.UserStore;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.jdbc.AutoConfigureTestDatabase;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.FilterType;
import org.springframework.stereotype.Repository;
import org.springframework.test.context.ActiveProfiles;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.UUID;

import static org.junit.jupiter.api.Assertions.*;

@DataJpaTest(includeFilters = @ComponentScan.Filter(type = FilterType.ANNOTATION, classes = Repository.class))
@Transactional
@AutoConfigureTestDatabase(replace = AutoConfigureTestDatabase.Replace.AUTO_CONFIGURED)
@ActiveProfiles("test")
public class UserJpaStoreTest {

    @Autowired
    UserStore userStore;

    @Autowired
    UserRepository userRepository;

    final static User testUser = new User(UUID.randomUUID().toString(), "test", "123", "testUser@gmail.com", Level.BASIC, 1,1);
    final static List<User> testUserList = List.of(
            new User(UUID.randomUUID().toString(), "test1", "123", "testUser1@gmail.com", Level.BASIC, 1,1),
            new User(UUID.randomUUID().toString(), "test2", "123", "testUser2@gmail.com", Level.GOLD, 5,1),
            new User(UUID.randomUUID().toString(), "test3", "123", "testUser3@gmail.com", Level.SILVER, 1,1)
    );

    @Test
    public void addTest() throws DuplicateUserIdException{
        assertDoesNotThrow(()->this.userStore.add(testUser));
        userRepository.flush();
        assertNotNull(userRepository.findById(testUser.getId()));
    }

    @Test
    public void addFailTest() throws DuplicateUserIdException{
        assertDoesNotThrow(()->this.userStore.add(testUser));
        assertThrows(DuplicateUserIdException.class,()->this.userStore.add(testUser));
    }

    @Test
    public void getTest() {
        assertDoesNotThrow(()->this.userStore.add(testUser));
        userRepository.flush();
        assertAll(
                ()->assertEquals(testUser.getId(), this.userStore.get(testUser.getId()).getId()),
                ()->assertEquals(testUser.getEmail(), this.userStore.get(testUser.getId()).getEmail()),
                ()->assertEquals(testUser.getName(), this.userStore.get(testUser.getId()).getName()),
                ()->assertEquals(testUser.getLevel(), this.userStore.get(testUser.getId()).getLevel()),
                ()->assertEquals(testUser.getRecommend(), this.userStore.get(testUser.getId()).getRecommend())
        );
    }

    @Transactional
    @Test
    void deleteAll(){
        userRepository.saveAll(testUserList);

        assertDoesNotThrow(()->userStore.deleteAll());

        assertTrue(userRepository.findAll().isEmpty());
    }

    @Test
    public void getAlltest(){
        userRepository.saveAll(testUserList);
        List<User> findUserList = this.userStore.getAll();
        assertEquals(testUserList.size(), findUserList.size());
        //
    }

    @Test
    public void updateTest(){
        userRepository.save(testUser);
        User updateUser = new User(testUser.getId(),
                testUser.getName(),
                testUser.getPassword() ,"update@email.com",
                testUser.getLevel(),
                testUser.getLogin(),
                testUser.getRecommend());

        this.userStore.update(updateUser);

        assertAll(
                ()->assertTrue(userRepository.findById(testUser.getId()).isPresent()),
                ()->assertEquals(updateUser.getEmail(), userRepository.findById(testUser.getId()).get().getEmail())
        );
    }
}
