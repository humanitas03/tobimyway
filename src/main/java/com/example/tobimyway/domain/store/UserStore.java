/**
 * ===============================================================
 * File name : UserStore.java
 * Created by injeahwang on 2021-07-22
 * ===============================================================
 */
package com.example.tobimyway.domain.store;

import com.example.tobimyway.domain.entity.User;
import com.example.tobimyway.domain.exception.DuplicateUserIdException;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

public interface UserStore {

    void add(User user) throws DuplicateUserIdException;
    User get(String id);

    @Transactional
    void deleteAll();

    List<User> getAll();

    void update(User user);
}
