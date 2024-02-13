package com.coin.web.utils;

import cn.hutool.core.util.ObjectUtil;
import cn.hutool.core.util.StrUtil;
import cn.hutool.http.HtmlUtil;
import com.coin.service.constant.CodeCons;
import com.coin.service.exception.BizException;
import com.coin.service.util.LocalCache;

import java.lang.reflect.Field;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Set;

public class ParamUtil {

    /**
     * 参数XSS检查
     */
    public static void xssCheck(Object paramObj) {
        List<Field> fieldList = new ArrayList<>(Arrays.asList(paramObj.getClass().getDeclaredFields()));
        if (!"com.coin.req.CommonReq".equals(paramObj.getClass().getName())) {
            fieldList.addAll(Arrays.asList(paramObj.getClass().getSuperclass().getDeclaredFields()));
        }

        Set<String> ignoreSet =  LocalCache.getCache(LocalCache.XSS_IGNORE_FIELD);
        Set<String> richTextSet =  LocalCache.getCache(LocalCache.XSS_RICH_TEXT_FIELD);
        for (Field field : fieldList) {
            try {
                field.setAccessible(true);
                if (!field.getType().getName().equals("java.lang.String")) {
                    continue;
                }
                Object fv = field.get(paramObj);
                if (ObjectUtil.isNull(fv)) {
                    continue;
                }
                String val = fv.toString();
                if (val.length() <= 1) {
                    continue;
                }
                // 判断是否为富文本字段
                String fullName = concatFullFieldName(field.getDeclaringClass().getName(), field.getName());
                if (ignoreSet != null && ignoreSet.contains(fullName)){
                    continue;
                }
                if (richTextSet != null && richTextSet.contains(fullName)) {
                    // 清理富文本
                    field.set(paramObj, richTextClean(val));
                } else {
                    // XSS过滤
                    String filter = HtmlUtil.filter(val);
                    if (!StrUtil.equals(filter, val)) {
                        throw new BizException(CodeCons.ERROR, "请求参数值格式非法");
                    }
                    field.set(paramObj, filter);
                }
            } catch (BizException e) {
                throw e;
            } catch (Exception e) {
                throw new BizException(CodeCons.ERROR, "请求参数值格式非法");
            }
        }
    }

    private static String richTextClean(String richText) {
        return OwaspHtmlSanitizerUtil.sanitizeByAllDefaultPolicy(richText);
    }

    private static String concatFullFieldName(String className, String fieldName) {
        return className + "." + fieldName;
    }
}
