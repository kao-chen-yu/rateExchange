package com.example.demo.Service.Impl;

import java.util.HashMap;
import java.util.Map;

import org.springframework.stereotype.Service;

import com.example.demo.Service.RateService;
import com.example.demo.model.request.ExchangeRateRequest;

@Service
public class RateServiceImpl implements RateService {

    public RateServiceImpl() {
        initialCurr();
    }

    @Override
    public String exchangeRate(String source, String target, String amount) throws Exception {
        // TODO Auto-generated method stub

        double result = Double.parseDouble(df.parse(amount).toString())
                * currency.get(source).get(target);

        return df.format(result);
    }

    @Override
    public void initialCurr() {

        Map<String, Double> twd = new HashMap<>();

        twd.put("TWD", 1.0);
        twd.put("JPY", 3.669);
        twd.put("USD", 0.03281);

        Map<String, Double> jpy = new HashMap<>();

        jpy.put("TWD", 0.26956);
        jpy.put("JPY", 1.0);
        jpy.put("USD", 0.00885);

        Map<String, Double> usd = new HashMap<>();

        usd.put("TWD", 30.444);
        usd.put("JPY", 111.801);
        usd.put("USD", 1.0);

        currency.put("TWD", twd);
        currency.put("JPY", jpy);
        currency.put("USD", usd);

    }
}