package com.example.demo.Controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.example.demo.Service.RateService;
import com.example.demo.model.request.ExchangeRateRequest;
import com.example.demo.model.request.response.ExchangeResponse;

@RestController
public class RateController {

    @RequestMapping("/")
    public String hello() {
        return "hello";
    }

    @Autowired
    private RateService rateService;

    @RequestMapping(value = "/exchangeRate", method = RequestMethod.GET)
    public ExchangeResponse exchangeRate(@RequestParam String source, @RequestParam String target,
            @RequestParam String amount) {

        ExchangeResponse response = new ExchangeResponse();

        String result;
        try {
            result = rateService.exchangeRate(source, target, amount);
            response.setMessage("success");
            response.setAmount(result);
        } catch (Exception e) {
            response.setMessage("error");
            // response.setAmount();
        }

        return response;
    }
}
