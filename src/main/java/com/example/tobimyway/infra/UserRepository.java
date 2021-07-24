/**
 * ===============================================================
 * File name : UserRepository.java
 * Created by injeahwang on 2021-07-22
 * ===============================================================
 */
package com.example.tobimyway.infra;

import com.example.tobimyway.domain.entity.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface UserRepository extends JpaRepository<User,String> {

}
