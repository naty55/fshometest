package com.fshometest2.fshometest;

import org.springframework.boot.context.properties.ConfigurationProperties;

@ConfigurationProperties("com.hometest")
public class HomeTestConfigProperties {

    // feature flag; determine if the user has to supply the access token every request 
    // true by default
    private boolean isAccessTokenEnabled=true; 

    public boolean isAccessTokenEnabled() {
        return isAccessTokenEnabled;
    }

    public void setIsAccessTokenEnabled(String isAccessTokenEnabled) {
        this.isAccessTokenEnabled = Boolean.parseBoolean(isAccessTokenEnabled);
    }
    
}
