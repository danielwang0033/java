package com.coin.service.util;

import cn.hutool.core.util.StrUtil;
import com.coin.service.constant.CodeCons;
import com.coin.service.exception.BizException;

public class ParamUtil {

    public static void check(Object... param) throws BizException {
        for (int i = 0; i < param.length; i++) {
            if (StrUtil.isBlank(getStr(param[i]))) {
                throw new BizException(CodeCons.PARAM_ERROR, param[i + 1] + " 不能为空");
            }
        }
    }

    private static String getStr(Object obj) {
        if (obj == null) {
            return "";
        }
        return obj.toString();
    }

    public static void limitCheck(String param, String[] limitArr) {
        boolean flag = false;
        for (String item : limitArr) {
            if (item.equals(param)) {
                flag = true;
                break;
            }
        }
        if (!flag) {
            throw new BizException(CodeCons.PARAM_ERROR, "参数不支持:" + param);
        }
    }
}
