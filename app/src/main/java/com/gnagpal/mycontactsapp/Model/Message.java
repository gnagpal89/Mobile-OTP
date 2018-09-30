package com.gnagpal.mycontactsapp.Model;

import android.arch.persistence.room.ColumnInfo;
import android.arch.persistence.room.Entity;
import android.arch.persistence.room.ForeignKey;
import android.arch.persistence.room.Ignore;
import android.arch.persistence.room.PrimaryKey;

import java.util.Date;

import static android.arch.persistence.room.ForeignKey.CASCADE;

@Entity
public class Message {
    @PrimaryKey(autoGenerate = true)
    private int id;

    @ColumnInfo(name = "message_text")
    private String messageText;

    @ColumnInfo(name = "user_name")
    private String userName;

    @ColumnInfo(name = "sent_at")
    private Date sentAt;

    @Ignore
    public Message(String messageText, String userName, Date sentAt) {
        this.messageText = messageText;
        this.userName = userName;
        this.sentAt = sentAt;
    }

    public Message(int id, String messageText, String userName, Date sentAt) {
        this.id = id;
        this.messageText = messageText;
        this.userName = userName;
        this.sentAt = sentAt;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getMessageText() {
        return messageText;
    }

    public void setMessageText(String messageText) {
        this.messageText = messageText;
    }

    public String getUserName() {
        return userName;
    }

    public void setUserName(int userId) {
        this.userName = userName;
    }

    public Date getSentAt() {
        return sentAt;
    }

    public void setSentAt(Date sentAt) {
        this.sentAt = sentAt;
    }
}
