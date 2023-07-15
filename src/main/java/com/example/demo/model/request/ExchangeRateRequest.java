package com.example.demo.model.request;

import lombok.Data;

@Data
public class ExchangeRateRequest {

    private String source;
    private String target;

    private String amount;
}
