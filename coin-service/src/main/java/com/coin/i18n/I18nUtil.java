package com.coin.i18n;

import cn.hutool.core.util.StrUtil;
import cn.hutool.extra.spring.SpringUtil;
import com.coin.service.util.LocalCache;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;

import java.util.Map;

@Component
public class I18nUtil {

    public static boolean ENABLE;

    public static String LANGUAGE;

    public static LongTextTranslate getLongTextTranslateBean() {
        return SpringUtil.getBean("longTextTranslate_" + LANGUAGE, LongTextTranslate.class);
    }

    @Value("${i18n.language}")
    public void setLanguage(String language) {
        I18nUtil.LANGUAGE = language;
    }

    @Value("${i18n.enable}")
    public void setEnable(Boolean enable) {
        I18nUtil.ENABLE = enable;
    }

    public static void initCache(Map<String, String> map) {
        LocalCache.putCache(LocalCache.I18N_CACHE, map);
    }

    public static String translate(String chTxt) {
        if (ENABLE) {
            if ("zh".equals(LANGUAGE)){
                return chTxt;
            }
            if (StrUtil.isBlank(chTxt)) {
                return chTxt;
            }
            Map<String, String> map = LocalCache.getCache(LocalCache.I18N_CACHE);
            if (map != null) {
                String s = map.get(chTxt);
                if (s != null) {
                    return s;
                }
            }
        }
        return chTxt;
    }

    public static String translateBiz(String chTxt) {
        if (ENABLE) {
            if ("zh".equals(LANGUAGE)){
                return chTxt;
            }
            if (StrUtil.isBlank(chTxt)) {
                return chTxt;
            }
            Map<String, String> map = LocalCache.getCache(LocalCache.I18N_CACHE);
            if (map != null) {
                String s = map.get(chTxt);
                if (s != null) {
                    return s;
                }
            }
        }
        return chTxt;
    }
}
