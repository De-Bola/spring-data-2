package com.sda.springdata2.chapter2.repository;

import com.sda.springdata2.chapter2.domain.User;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface UserRepository extends CrudRepository<User, Long> {
}
