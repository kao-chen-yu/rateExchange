package com.example.demo.model.request.response;

import com.fasterxml.jackson.annotation.JsonFormat;

import lombok.Data;

@Data
public class ExchangeResponse {

    private String message;

    private String amount;
}
