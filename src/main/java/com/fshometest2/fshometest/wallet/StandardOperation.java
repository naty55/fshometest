package com.fshometest2.fshometest.wallet;

/**
 * POJO that represent "standard" operation 
 */
public class StandardOperation {
    
    boolean error;
    String  errorMessage;
    String errorType;
    Long from = null;
    Operation operation;
    Long to = null;
    boolean success;
    Wallet wallet;
    Long walletId;

    public StandardOperation(boolean error, String errorMessage, String errorType, Long from, Operation operation, Long to, boolean success,
            Wallet wallet, Long walletId) {
        this.error = error;
        this.errorMessage = errorMessage;
        this.errorType = errorType;
        this.from = from;
        this.operation = operation;
        this.to = to;
        this.success = success;
        this.wallet = wallet;
        this.walletId = walletId;
    }

    public StandardOperation(boolean error, String errorMessage, String errorType, Operation operation, Wallet wallet, Long walletId) {
        setError(true);
        this.errorMessage = errorMessage;
        this.errorType = errorType;
        this.operation = operation;
        this.wallet = wallet;
        this.walletId = walletId;
    }


    public StandardOperation(Long from, Operation operation, Long to, Wallet wallet, Long walletId) {
        setError(false);
        this.errorMessage = "";
        this.errorType = "";
        this.from = from;
        this.operation = operation;
        this.to = to;
        this.wallet = wallet;
        this.walletId = walletId;
    }

    public boolean isError() {
        return error;
    }

    public void setError(boolean error) {
        this.error = error;
        this.success = !error;
    }

    public String getErrorMessage() {
        return errorMessage;
    }

    public void setErrorMessage(String errorMessage) {
        this.errorMessage = errorMessage;
    }

    public String getErrorType() {
        return errorType;
    }

    public void setErrorType(String errorType) {
        this.errorType = errorType;
    }

    public Long getFrom() {
        return from;
    }

    public void setFrom(Long from) {
        this.from = from;
    }

    public Operation getOperation() {
        return operation;
    }

    public void setOperation(Operation operation) {
        this.operation = operation;
    }

    public Long getTo() {
        return to;
    }

    public void setTo(Long to) {
        this.to = to;
    }

    public boolean isSuccess() {
        return success;
    }

    public void setSuccess(boolean success) {
        this.success = success;
        this.error = !success;
    }

    public Wallet getWallet() {
        return wallet;
    }

    public void setWallet(Wallet wallet) {
        this.wallet = wallet;
    }

    public Long getWalletId() {
        return walletId;
    }

    public void setWalletId(Long walletId) {
        this.walletId = walletId;
    }

    

    
}
