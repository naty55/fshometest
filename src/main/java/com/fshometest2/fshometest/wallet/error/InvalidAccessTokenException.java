package com.fshometest2.fshometest.wallet.error;

public class InvalidAccessTokenException extends RuntimeException {
    String accessToken;
    Long WalletId;

    public InvalidAccessTokenException(String accessToken, Long walletId) {
        this.accessToken = accessToken;
        WalletId = walletId;
    }

    public String getAccessToken() {
        return accessToken;
    }
    public void setAccessToken(String accessToken) {
        this.accessToken = accessToken;
    }
    public Long getWalletId() {
        return WalletId;
    }
    public void setWalletId(Long walletId) {
        WalletId = walletId;
    }

    
}
