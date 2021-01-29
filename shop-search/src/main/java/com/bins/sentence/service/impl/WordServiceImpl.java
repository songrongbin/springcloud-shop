package com.bins.sentence.service.impl;

import com.bins.sentence.api.TradeClient;
import com.bins.sentence.service.WordService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class WordServiceImpl implements WordService {

    @Autowired
    TradeClient tradeClient;

    @Override
    // @HystrixCommand(fallbackMethod="getFallbackTrade")
    public String getTrade() {
        return tradeClient.getWord();
    }

    public String getFallbackTrade() {
        return "trade";
    }

}
