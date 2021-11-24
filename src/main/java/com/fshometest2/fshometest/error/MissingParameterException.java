package com.fshometest2.fshometest.error;

public class MissingParameterException extends RuntimeException {

    String parameterName;
    Long walletId;

    public MissingParameterException(String parameterName, Long walletId) {
        this.parameterName = parameterName;
        this.walletId = walletId;
    }
    public MissingParameterException(String parameterName) {
        this.parameterName = parameterName;
        this.walletId = null;
    }
    public String getParameterName() {
        return parameterName;
    }

    public void setParameterName(String parameterName) {
        this.parameterName = parameterName;
    }

    public Long getWalletId() {
        return walletId;
    }

    public void setWalletId(Long walletId) {
        this.walletId = walletId;
    }


    
    
}
