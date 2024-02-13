package com.coin.resp.search;

import lombok.Data;

import java.util.List;

@Data
public class SettingVo {

    private List<String> announcement;

    private List<DictVo> contact;
}
