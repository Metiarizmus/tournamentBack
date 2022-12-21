package com.scorina.competition.repositories;

import com.scorina.competition.models.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface UserRepo extends JpaRepository<User, Long> {
    User findByLogin(String login);

    Boolean existsByLogin(String login);
}
