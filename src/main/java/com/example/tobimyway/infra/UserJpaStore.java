/**
 * ===============================================================
 * File name : UserJpaStore.java
 * Created by injeahwang on 2021-07-22
 * ===============================================================
 */
package com.example.tobimyway.infra;

import com.example.tobimyway.domain.entity.User;
import com.example.tobimyway.domain.exception.DuplicateUserIdException;
import com.example.tobimyway.domain.store.UserStore;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Repository
public class UserJpaStore implements UserStore {

    UserRepository userRepository;

    public UserJpaStore(UserRepository userRepository){
        this.userRepository = userRepository;
    }

    public void add(User user) throws DuplicateUserIdException {
        if(this.userRepository.existsById(user.getId())){
            throw new DuplicateUserIdException("중복된 사용자 입니다 ["+user+ "]");
        }

        this.userRepository.save(user);
    }

    public User get(String id){
        return this.userRepository.findById(id).orElse(null);
    }

    @Transactional
    public void deleteAll(){
        this.userRepository.deleteAll();
    }

    public List<User> getAll(){
        return this.userRepository.findAll();
    }

    public void update(User user){
        // User에 대한 검증은 생략한다.
        this.userRepository.save(user);
    }


}
