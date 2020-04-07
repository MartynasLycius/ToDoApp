package com.murad.todoapp.domain;

import javax.persistence.*;
import java.io.Serializable;
import java.time.LocalDateTime;
/**
 * @author Muradul Mostafa
 * Date    05/04/2020
 */
@Entity
@Table(name = "custom_log")
public class CustomLog implements Serializable {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;

    private String ip;
    private String username;
    private String password;
    @Column(name = "user_agent")
    private String userAgent;
    private String status;
    private LocalDateTime time;

    public CustomLog() {
    }

    public CustomLog(String ip, String username, String password, String userAgent, String status) {
        this.ip = ip;
        this.username = username;
        this.password = password;
        this.userAgent = userAgent;
        this.status = status;
        this.time = LocalDateTime.now();
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getIp() {
        return ip;
    }

    public void setIp(String ip) {
        this.ip = ip;
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

    public String getUserAgent() {
        return userAgent;
    }

    public void setUserAgent(String userAgent) {
        this.userAgent = userAgent;
    }

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }

    public LocalDateTime getTime() {
        return time;
    }

    public void setTime(LocalDateTime time) {
        this.time = time;
    }
}
