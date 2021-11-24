package com.fshometest2.fshometest;

import com.fshometest2.fshometest.wallet.TokenGenerator;
import com.fshometest2.fshometest.wallet.Wallet;
import com.fshometest2.fshometest.wallet.WalletConfigProperties;
import com.fshometest2.fshometest.wallet.WalletRepository;

import org.springframework.beans.factory.InitializingBean;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.context.properties.EnableConfigurationProperties;
import org.springframework.context.annotation.Bean;
import org.springframework.web.bind.annotation.RestController;

@SpringBootApplication
@EnableConfigurationProperties({HomeTestConfigProperties.class, WalletConfigProperties.class})
@RestController
public class FshometestApplication {

	@Autowired
	WalletRepository repo;

	@Autowired
	TokenGenerator tokenGenerator;

	public static void main(String[] args) {
		SpringApplication.run(FshometestApplication.class, args);
	}


	@Bean
	InitializingBean createDemoAccounts() {
		return () -> {
			Wallet walletA = new Wallet();
			walletA.setAccessToken(tokenGenerator.next());
			Wallet walletB = new Wallet();
			walletB.setAccessToken(tokenGenerator.next());
			System.out.println("=========== Generating two demo accounts ===========");
			System.out.println(repo.save(walletA));
			System.out.println(repo.save(walletB));
			System.out.println("=====================================================");
		};
	}
}
