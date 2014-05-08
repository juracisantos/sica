package br.com.dynantec.criptografia;

import java.security.NoSuchAlgorithmException;

public interface Criptografia {

    String encrypt(String value) throws NoSuchAlgorithmException;
}
