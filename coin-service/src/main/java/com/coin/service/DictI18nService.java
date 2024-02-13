package com.coin.service;

import com.coin.entity.TDictI18n;
import com.coin.req.DictReq;

import java.util.List;

public interface DictI18nService {

    List<TDictI18n> findByLanguage(String language);

    void refresh();

    void addBatch(DictReq req);

    List<String> check();
}
