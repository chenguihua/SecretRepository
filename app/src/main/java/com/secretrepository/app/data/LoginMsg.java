package com.secretrepository.app.data;

import java.io.Serializable;
import java.util.UUID;

/**
 * Created by chenguihua on 2017/3/3.
 */

public class LoginMsg implements Serializable{
    private String id;
    private String title;
    private String username;
    private String password;
    private String description;

    /**
     * Constructor to generate a UUID automatic
     * @param title
     * @param username
     * @param password
     * @param des
     */
    public LoginMsg(String title, String username, String password, String des) {
        this(UUID.randomUUID().toString(), title, username, password, des);
    }

    public LoginMsg(String id, String title, String username, String password, String des) {
        this.id = id;
        this.title = title;
        this.username = username;
        this.password = password;
        this.description = des;
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }
}
