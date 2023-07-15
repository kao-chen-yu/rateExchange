package com.example.demo.Service;

import java.text.DecimalFormat;
import java.util.HashMap;
import java.util.Map;

import com.example.demo.model.request.ExchangeRateRequest;

public interface RateService {

    Map<String, Map<String, Double>> currency = new HashMap<>();

    DecimalFormat df = new DecimalFormat("$,###.##");

    public String exchangeRate(String source, String target, String amount) throws Exception;

    public void initialCurr();
}
