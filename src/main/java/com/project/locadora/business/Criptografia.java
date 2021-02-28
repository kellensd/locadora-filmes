package com.project.locadora.business;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.math.BigInteger;
import java.nio.charset.Charset;
import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;

public class Criptografia {

    private static final Logger LOGGER = LoggerFactory.getLogger(Criptografia.class);

    private static final int RADIX = 16;

    public Criptografia() {
    }

    public static String getHashMd5(String value) {
        MessageDigest md;
        try {
            md = MessageDigest.getInstance("SHA-256");
        } catch (NoSuchAlgorithmException ex) {
            LOGGER.error("Erro ao gerar Hash Md5: ", ex);
            throw new IllegalArgumentException("Erro ao criptografar senha.");
        }
        BigInteger hash = new BigInteger(1, md.digest(value.getBytes(Charset.forName("UTF-8"))));
        return hash.toString(RADIX);
    }
}
