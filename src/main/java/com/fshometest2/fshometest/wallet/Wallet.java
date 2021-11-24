package com.fshometest2.fshometest.wallet;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.SequenceGenerator;
import javax.persistence.Table;

import org.springframework.lang.NonNull;

@Entity
@Table(name = "wallet")
@SequenceGenerator(name = "seq", initialValue = 1024, allocationSize = 100)
public class Wallet {

    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "seq")
    private Long id; 
    @NonNull
    private String accessToken;
    @NonNull
    private Long balance = 0L;
    
    
    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getAccessToken() {
        return accessToken;
    }

    public void setAccessToken(String accessToken) {
        this.accessToken = accessToken;
    }
    public Long getBalance() {
        return balance;
    }

    public void setBalance(Long balance) {
        this.balance = balance;
    }

    public boolean isEmpty() {
        return getBalance() == 0;
    }

    @Override
    public String toString() {
        return String.format("Wallet [accessToken=%s, balance=%s, id=%s]", accessToken, balance, id);
    }

    

}
