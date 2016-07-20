package com.theironyard;

import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

/**
 * Created by Nigel on 7/19/16.
 */
public interface MessageRepository extends JpaRepository<Message, Integer> {
    Message findById(int id);
}
