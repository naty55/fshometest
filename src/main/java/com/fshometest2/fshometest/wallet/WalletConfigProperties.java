package com.fshometest2.fshometest.wallet;

import org.springframework.boot.context.properties.ConfigurationProperties;

@ConfigurationProperties("com.hometest.wallet")
public class WalletConfigProperties {
    Long MaxAllowedDebt = 0L;

    public Long getMaxAllowedDebt() {
        return MaxAllowedDebt;
    }

    public void setMaxAllowedDebt(Long maxAllowedDebt) {
        MaxAllowedDebt = maxAllowedDebt;
    }

    
}
