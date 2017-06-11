package com.bins.sentence.service.impl;

import com.bins.sentence.api.OrderClient;
import com.bins.sentence.api.TradeClient;
import com.bins.sentence.service.SentenceService;
import com.netflix.hystrix.contrib.javanica.annotation.HystrixCommand;
import com.netflix.hystrix.contrib.javanica.command.AsyncResult;
import org.springframework.beans.factory.annotation.Autowired;
import rx.Observable;
import rx.Subscriber;

import java.util.concurrent.Future;

/**
 * Created by songrongbin on 2017/6/11.
 */
public class SentenceServiceImpl implements SentenceService {

    @Autowired
    TradeClient tradeClient;

    @Autowired
    OrderClient orderClient;

    @HystrixCommand(fallbackMethod = "getFallbackTrade")
    public Future<String> getTrade() {
        return new AsyncResult<String>() {
            @Override
            public String invoke() {
                return tradeClient.getWord();
            }
        };
    }


    @Override
    @HystrixCommand(fallbackMethod="getFallbackOrder")
    public Observable<String> getOrder() {
        return Observable.create(new Observable.OnSubscribe<String>() {
            @Override
            public void call(Subscriber<? super String> observer) {
                try {
                    if (!observer.isUnsubscribed()) {
                        observer.onNext(orderClient.getWord());
                        observer.onCompleted();
                    }
                } catch (Exception e) {
                    observer.onError(e);
                }
            }
        });
    }

    public String getFallbackTrade() {
        return "trade";
    }

    public String getFallbackOrder() {
        return "order";
    }

}
