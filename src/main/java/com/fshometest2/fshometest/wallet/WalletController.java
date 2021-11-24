package com.fshometest2.fshometest.wallet;

import java.util.NoSuchElementException;
import java.util.Optional;

import com.fshometest2.fshometest.HomeTestConfigProperties;
import com.fshometest2.fshometest.error.MissingParameterException;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/api/wallet")
public class WalletController {

    @Autowired
    OperationService walletService;

    @Autowired
    HomeTestConfigProperties homeTestConfigProperties;

    @GetMapping("/getBalance/{walletId}")
    StandardOperation getBalance(@PathVariable Long walletId, @RequestParam("token") Optional<String> token) {
        try {
            if (homeTestConfigProperties.isAccessTokenEnabled()) {
                return walletService.getBalance(walletId, token.get());
            } else {
                return walletService.getBalance(walletId, null);
            }
        } catch(NoSuchElementException ex) {
            throw new MissingParameterException("token", walletId);
        }
    }

    @GetMapping("/deposit/{walletId}")
    StandardOperation deposit(@PathVariable Long walletId, 
    @RequestParam("amount") Optional<Long> amount, 
    @RequestParam("token") Optional<String> token) throws MissingParameterException {
        try {
            if (homeTestConfigProperties.isAccessTokenEnabled()) {
                return walletService.deposit(walletId, amount.get(), token.get());
            } else {
                return walletService.deposit(walletId, amount.get(), null);
            }
        } catch(NoSuchElementException ex) {
            if (amount.isEmpty()) {
                throw new MissingParameterException("amount", walletId);
            } else {
                throw new MissingParameterException("token", walletId);
            }
        }

    }

    @GetMapping("/create")
    StandardOperation create() {
        return walletService.create();
    }

    @GetMapping("/delete/{walletId}")
    StandardOperation delete(@PathVariable Long walletId, @RequestParam("token") Optional<String> token) {
        try {
            if (homeTestConfigProperties.isAccessTokenEnabled()) {
                return walletService.delete(walletId, token.get());
            } else {
                return walletService.delete(walletId, null);
            }
        } catch(NoSuchElementException ex) {
            throw new MissingParameterException("token", walletId);
        }
    }

    @GetMapping("/transfer")
    StandardOperation transfer(
        @RequestParam("from") Optional<Long> from, 
        @RequestParam("to") Optional<Long> to, 
        @RequestParam("amount") Optional<Long> amount,
        @RequestParam("token") Optional<String> token) throws MissingParameterException {
            try {
                if (homeTestConfigProperties.isAccessTokenEnabled()) {
                    return walletService.transfer(from.get(), to.get(), amount.get(), token.get());
                } else {
                    return walletService.transfer(from.get(), to.get(), amount.get(), null);
                }
            } catch(NoSuchElementException ex) {
                if (from.isEmpty()) {
                    throw new MissingParameterException("from");
                }
                if (to.isEmpty()) {
                    throw new MissingParameterException("to", from.get());
                }
                if (amount.isEmpty()) {
                    throw new MissingParameterException("amount", from.get());
                }
                throw new MissingParameterException("token", from.get());
                
            }
        }
    

}
