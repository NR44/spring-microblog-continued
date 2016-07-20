package com.theironyard;

import javax.persistence.*;

/**
 * Created by Nigel on 7/19/16.
 */

@Entity
@Table(name="microblog")
public class Message {

    @Id
    @GeneratedValue
    private int id;

    @Column(nullable = false)
    private String text;

    public Message() {
    }

    public Message(String text) {
        this.text = text;
    }

    public int getId() {
        return id;
    }

    public String getText() {
        return text;
    }

    public void setId(int id) {
        this.id = id;
    }

    public void setText(String text) {
        this.text = text;
    }
}