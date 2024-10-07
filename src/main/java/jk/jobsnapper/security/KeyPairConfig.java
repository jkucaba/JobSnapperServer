package jk.jobsnapper.security;

import jk.jobsnapper.util.KeyPairGeneratorUtil;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import java.security.KeyPair;
import java.security.NoSuchAlgorithmException;

@Configuration
public class KeyPairConfig {

    @Bean
    public KeyPair keyPair() {
        try {
            return KeyPairGeneratorUtil.generateKeyPair();
        } catch (NoSuchAlgorithmException e) {
            throw new RuntimeException("Nie udało się wygenerować pary kluczy", e);
        }
    }
}