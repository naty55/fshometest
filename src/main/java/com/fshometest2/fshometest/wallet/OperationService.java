package com.fshometest2.fshometest.wallet;

import java.util.NoSuchElementException;

import com.fshometest2.fshometest.wallet.error.InvalidAccessTokenException;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

enum Operation {
    CREATE, DEPOSIT, DELETE, GET_BALANCE, TRANSFER,
}

@Service
public class OperationService {

    @Autowired
    WalletConfigProperties walletConfigProperties;

    @Autowired
    WalletRepository walletRepository;

    @Autowired
    TokenGenerator tokenGenerator;

    public StandardOperation deposit(Long walletId, Long amount, String accessToken) {
        try {
            Wallet wallet = walletRepository.findById(walletId).get();
            validateAccessToken(wallet, accessToken);
            if (wallet.getBalance() + amount < walletConfigProperties.getMaxAllowedDebt()) {
                return new StandardOperation(true, "amount excess balance", "", Operation.DEPOSIT, wallet, walletId);
            }
            wallet.setBalance(wallet.getBalance() + amount);
            wallet = walletRepository.save(wallet);
            return new StandardOperation(false, "", "", Operation.DEPOSIT, wallet, walletId);
        } catch(NoSuchElementException e) {
            return handleWalletNotFound(e, walletId, Operation.DEPOSIT);
        }
    }

    public StandardOperation create() {
        Wallet newWallet = new Wallet();
        newWallet.setAccessToken(tokenGenerator.next());
        newWallet = walletRepository.save(newWallet);
        return new StandardOperation(false, "", "",  Operation.CREATE, newWallet, newWallet.getId());
    }

    public StandardOperation getBalance(Long walletId, String accessToken) {
        try {
            // Must use findById and NOT getById since getById uses lazy initialization and
            // thus will cause a runtime exception
            // See here for refrence
            // https://stackoverflow.com/questions/52656517/no-serializer-found-for-class-org-hibernate-proxy-pojo-bytebuddy-bytebuddyinterc
            Wallet wallet = walletRepository.findById(walletId).get();
            validateAccessToken(wallet, accessToken);
            return new StandardOperation(false, "", "",  Operation.GET_BALANCE, wallet, walletId);
        } catch (NoSuchElementException e) {
            return handleWalletNotFound(e, walletId, Operation.GET_BALANCE);
        }
    }

    @Transactional
    public StandardOperation transfer(Long from, Long to, Long amount, String accessToken) {
        if (amount <= 0) {
            return new StandardOperation(true, "can't transfer negative value", "", Operation.TRANSFER, null, from);
        }
        StandardOperation withdraw = deposit(from, -amount, accessToken);
        withdraw.setOperation(Operation.TRANSFER);
        withdraw.setFrom(from);
        withdraw.setTo(to);

        if (withdraw.isError()) { // Nothing happened (either the blance is not enough or the wallet was not not found)
            return withdraw;
        }
        StandardOperation deposit  = deposit(to, amount, null);
        if (deposit.isError()) { // Target wallet was not found; Rollback
            deposit(from, amount, null);
            withdraw.setError(true);
            withdraw.setSuccess(false);
            withdraw.setErrorMessage("Target Wallet was not found");
            return withdraw;
        }
        return withdraw;
        
    }

    public StandardOperation delete(Long walletId, String accessToken) {
        try {
            Wallet wallet = walletRepository.findById(walletId).get();
            validateAccessToken(wallet, accessToken);
            if (wallet.isEmpty()) {
                return new StandardOperation(true, "can't delete not-empty wallet", "", null,
                        Operation.DELETE, null, false, wallet, walletId);
            }
            walletRepository.deleteById(walletId);
            return new StandardOperation(false, "", "", Operation.DELETE, wallet, walletId);
        } catch (NoSuchElementException e) {
            return handleWalletNotFound(e, walletId, Operation.DELETE);
        }

    }


    public StandardOperation handleWalletNotFound(RuntimeException e, Long walletId, Operation operation) {
        return new StandardOperation(true, "wallet not found", e.getClass().getSimpleName(), operation, null, walletId);
    }

    public void validateAccessToken(Wallet wallet, String token) {
        if (token != null && !token.equals(wallet.getAccessToken())) {
            throw new InvalidAccessTokenException(token, wallet.getId());
        }
    }

}
