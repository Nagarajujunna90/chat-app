package com.nr.chatapp.repository;

import com.nr.chatapp.model.Messages;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface MessageRepository extends JpaRepository<Messages,Integer> {
}
