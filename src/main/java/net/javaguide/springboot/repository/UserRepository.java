package net.javaguide.springboot.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import net.javaguide.springboot.model.user;

@Repository
public interface UserRepository extends JpaRepository<user, Long> {

}
