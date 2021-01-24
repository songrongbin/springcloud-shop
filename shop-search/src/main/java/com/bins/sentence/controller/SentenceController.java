package com.bins.sentence.controller;

import com.bins.sentence.api.TradeClient;
import com.bins.sentence.api.UserApi;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;

import javax.annotation.Resource;

/**
 * Created by songrongbin on 2017/5/25.
 */
@RestController
public class SentenceController {

    private Logger logger = LoggerFactory.getLogger(SentenceController.class);

    @Resource
    private TradeClient tradeClient;

    @Resource
    private UserApi shopUserApi;

    @RequestMapping("/search/user/personInfo")
    public @ResponseBody String getPersonInfo() {
        return shopUserApi.getPersonInfo(0l).getUserName();

    }

    @RequestMapping("/sentence")
    public @ResponseBody String getTrade() {
        return
                "<h3>造句:</h3><br/>" +
                        buildSentence() + "<br/><br/>" +
                        buildSentence() + "<br/><br/>" +
                        buildSentence() + "<br/><br/>" +
                        buildSentence() + "<br/><br/>" +
                        buildSentence() + "<br/><br/>"
                ;
    }

    public String buildSentence() {
        String sentence = "There was a problem assembling the sentence!";
        try{
            sentence = tradeClient.getWord();
        } catch ( Exception e ) {
            System.out.println(e);
        }
        return sentence;
    }
}
