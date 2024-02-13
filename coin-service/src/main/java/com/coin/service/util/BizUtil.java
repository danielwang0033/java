package com.coin.service.util;

import cn.hutool.core.util.NumberUtil;
import cn.hutool.core.util.ObjectUtil;
import com.coin.cache.UserLevelCache;
import com.coin.resp.user.UserLevelVo;
import com.coin.resp.user.UserRelationLevelVo;
import com.coin.resp.user.UserSimpleInfoVo;

import java.util.Random;

public class BizUtil {

    /**
     * 生成指定长度随机串，
     *
     * @param length 长度
     * @param type   1-固定是字母 2-固定是数字 0-数字或字母
     */
    public static String getStringRandom(int length, int type) {
        StringBuilder val = new StringBuilder();
        Random random = new Random();
        // 参数length，表示生成几位随机数
        for (int i = 0; i < length; i++) {
            String charOrNum = random.nextInt(2) % 2 == 0 ? "char" : "num";
            if (type == 1) {
                charOrNum = "char";
            }
            if (type == 2) {
                charOrNum = "num";
            }
            // 输出字母还是数字
            if ("char".equalsIgnoreCase(charOrNum)) {
                // 输出是大写字母还是小写字母
                int temp = random.nextInt(2) % 2 == 0 ? 65 : 97;
                val.append((char) (random.nextInt(26) + temp));
            } else {
                val.append(random.nextInt(10));
            }
        }
        return val.toString();
    }


    public static UserRelationLevelVo buildLevelVo(UserLevelVo userLevelVo) {
        UserRelationLevelVo userRelationLevelVo = new UserRelationLevelVo();
        if (ObjectUtil.isNull(userLevelVo)) {
            return userRelationLevelVo;
        }
        UserLevelCache level = userLevelVo.getUserLevel();
        UserLevelCache next = userLevelVo.getNextLevel();

        userRelationLevelVo.setLevel(level.getLevel());
        userRelationLevelVo.setName(level.getName());
        userRelationLevelVo.setBadge(ImageUtil.completeImageUrl(level.getBadge()));
        userRelationLevelVo.setNeedExp(level.getNeedExp());
        userRelationLevelVo.setNextLevel(next.getLevel());
        userRelationLevelVo.setNextLevelNeedExp(next.getNeedExp());
        userRelationLevelVo.setNextLevelName(next.getName());
        userRelationLevelVo.setNextLevelBadge(ImageUtil.completeImageUrl(next.getBadge()));
        return userRelationLevelVo;
    }

    public static boolean checkAllowEdit(UserSimpleInfoVo loginUser, Long targetUserId) {
        if (ObjectUtil.isNull(loginUser) || ObjectUtil.isNull(targetUserId)) {
            return false;
        }
        if (NumberUtil.equals(loginUser.getIsSuperAdmin(), 1)) {
            return true;
        }
        if (NumberUtil.equals(loginUser.getIsForumAdmin(), 1)) {
            return true;
        }
        return NumberUtil.equals(loginUser.getId(), targetUserId);
    }

    public static boolean checkAllowDelete(UserSimpleInfoVo loginUser, Long targetUserId) {
        if (ObjectUtil.isNull(loginUser) || ObjectUtil.isNull(targetUserId)) {
            return false;
        }
        if (NumberUtil.equals(loginUser.getIsSuperAdmin(), 1)) {
            return true;
        }
        return NumberUtil.equals(loginUser.getId(), targetUserId);
    }
}
