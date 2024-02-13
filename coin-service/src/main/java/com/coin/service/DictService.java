package com.coin.service;

import com.coin.entity.TDict;
import com.coin.req.DictReq;
import com.coin.req.dict.DictUpdateReq;
import com.coin.resp.dict.ModelSwitchVo;
import com.coin.resp.search.SettingVo;

import java.util.List;

public interface DictService {

    List<TDict> getListByType(DictReq dictReq);

    TDict getById(Long id);

    TDict getByTypeAndCode(String dictType, String dictCode);

    void update(DictReq req);

    String getDefaultUserAvatar();

    SettingVo getSetting();

    List<ModelSwitchVo> modelSwitchConfig(DictReq req);

    void updateBatch(DictUpdateReq req);

    void refreshXssConfig();
}
