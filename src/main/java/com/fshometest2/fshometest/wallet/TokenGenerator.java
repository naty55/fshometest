package com.fshometest2.fshometest.wallet;

import org.springframework.stereotype.Service;

@Service
public class TokenGenerator {
    public String next() { // Demo token
        return "token";
    }
}
