package com.afitnerd.twilio_lametric.service;

public interface CodeService {

    final String[] CODES = {
        "neat-advertisement-immune-sign",
        "exciting-mark-probable-rate",
        "hulking-page-adroit-chance",
        "serious-dress-smelly-quilt",
        "gentle-wax-plucky-metal",
        "mere-pizzas-calm-grade"
    };

    boolean checkCode(int codeNum, String code);
    String getCode(int codeNum);
    int generateCode();
}
