package com.afitnerd.twilio_lametric.service;

import org.springframework.stereotype.Service;

import java.util.Random;

@Service
public class CodeServiceImpl implements CodeService {

    private static final Random random = new Random();

    @Override
    public boolean checkCode(int codeNum, String code) {
        return codeNum >= 0 && codeNum < CODES.length && CODES[codeNum].equals(code);
    }

    @Override
    public String getCode(int codeNum) {
        return (codeNum >= 0 && codeNum < CODES.length) ? CODES[codeNum] : null;
    }

    @Override
    public int generateCode() {
        return random.nextInt(CODES.length);
    }
}
