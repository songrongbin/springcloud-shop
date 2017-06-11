package com.bins.sentence.service;

import rx.Observable;

import java.util.concurrent.Future;

/**
 * Created by songrongbin on 2017/6/11.
 */
public interface SentenceService {
    Observable<String> getOrder();

    Future<String> getTrade();

}
