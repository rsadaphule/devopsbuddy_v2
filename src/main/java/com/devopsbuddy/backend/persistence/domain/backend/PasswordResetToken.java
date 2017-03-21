package com.devopsbuddy.backend.persistence.domain.backend;

import com.devopsbuddy.backend.persistence.converters.LocalDateTimeAttributeConverter;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import javax.persistence.*;
import java.io.Serializable;
import java.time.LocalDateTime;

/**
 * Created by sadap on 3/21/2017.
 */
@Entity
public class PasswordResetToken implements Serializable {

    public static final long serialVersionUID = 1L;

    private static final int DEFAULT_TOKEN_LENGTH_IN_MINUTES = 120;

    private static final Logger LOG = LoggerFactory.getLogger(PasswordResetToken.class);

    @Id
    @GeneratedValue(strategy =  GenerationType.AUTO)
    private long id;

    @Column(unique = true)
    private String token;

    @ManyToOne(fetch = FetchType.EAGER)
    @JoinColumn(name = "user_id")
    private User user;

    @Column(name="expiry_date")
    @Convert(converter = LocalDateTimeAttributeConverter.class)
    private LocalDateTime expiryDate;


    public long getId() {
        return id;
    }

    public void setId(long id) {
        this.id = id;
    }

    public String getToken() {
        return token;
    }

    public void setToken(String token) {
        this.token = token;
    }

    public User getUser() {
        return user;
    }

    public void setUser(User user) {
        this.user = user;
    }

    public LocalDateTime getExpiryDate() {
        return expiryDate;
    }

    public void setExpiryDate(LocalDateTime expiryDate) {
        this.expiryDate = expiryDate;
    }



    public PasswordResetToken()
    {

    }

    public PasswordResetToken(String token, User user, LocalDateTime creationDateTime, int expirationInMinutes)
    {
        if( (null == token ) || (null == user) || (null == creationDateTime) )
        {
            throw new IllegalArgumentException("token, user and creation date must not be null");
        }

        if(expirationInMinutes == 0)
        {
            LOG.warn("The token expiration length in minutes is 0. Assuming default value of {}", DEFAULT_TOKEN_LENGTH_IN_MINUTES);
            expirationInMinutes = DEFAULT_TOKEN_LENGTH_IN_MINUTES;
        }

        this.token = token;
        this.user = user;
        this.expiryDate = creationDateTime.plusMinutes(expirationInMinutes);

    }



    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        PasswordResetToken pwr = (PasswordResetToken) o;

        return id == pwr.id;

    }

    @Override
    public int hashCode() {
        return (int) (id ^ (id >>> 32));
    }



}
