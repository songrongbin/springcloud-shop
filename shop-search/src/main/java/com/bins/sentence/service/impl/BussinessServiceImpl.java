package com.bins.sentence.service.impl;

import com.bins.sentence.service.BussinessService;
import com.bins.sentence.service.SentenceService;
import com.bins.sentence.service.WordService;
import com.google.common.collect.Lists;
import org.springframework.beans.factory.annotation.Autowired;
import rx.Subscriber;

import java.util.List;
import java.util.concurrent.ExecutionException;

/**
 * Created by songrongbin on 2017/6/11.
 */
public class BussinessServiceImpl implements BussinessService {

    @Autowired
    SentenceService sentenceService;


    /**
     * Assemble a sentence by gathering random words of each part of speech:
     */
    public String buildSentence() {

        final List<String> nounList = Lists.newArrayList();
        /*sentenceService.getTrade().(new Subscriber<String>() {
            @Override
            public void onCompleted() {

            }

            @Override
            public void onError(Throwable e) {

            }

            @Override
            public void onNext(String s) {
                nounList.add(s);
            }
        });*/

//        try {
//            return String.format("%s %s %s %s %s.", sentenceService.getOrder(), nounList.get(0));
//        } catch (Exception e) {
//            e.printStackTrace();
//        }
        return "error";
    }

}
