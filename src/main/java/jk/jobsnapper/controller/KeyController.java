package jk.jobsnapper.controller;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;
import java.security.KeyPair;
import java.util.Base64;

@RestController
public class KeyController {
    private final KeyPair keyPair;

    public KeyController(KeyPair keyPair) {
        this.keyPair = keyPair;
    }

    @GetMapping("/publicKey")
    public String getPublicKey() {
        byte[] publicKey = keyPair.getPublic().getEncoded();
        return Base64.getEncoder().encodeToString(publicKey);
        /*RSAPublicKey publicKey = (RSAPublicKey) keyPair.getPublic();
        return Base64.getEncoder().encodeToString(publicKey.getEncoded());*/
    }
}