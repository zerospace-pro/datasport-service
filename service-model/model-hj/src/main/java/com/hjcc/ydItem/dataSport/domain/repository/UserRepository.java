package com.hjcc.ydItem.dataSport.domain.repository;

import com.hjcc.ydItem.dataSport.domain.entity.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

/**
 Created by 胡杰 on 2019/3/30. */
@Repository
public interface UserRepository extends JpaRepository<User,String> {
}
