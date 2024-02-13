package com.coin.web.test;

import cn.hutool.core.date.DatePattern;
import cn.hutool.core.date.DateTime;
import cn.hutool.core.date.DateUtil;

import java.util.Date;

public class Test {

    public static void main(String[] args) {
       /* String str = "   id, title, thumbs, description, content, pics, comments, `status`, user_id, user_name, \n" +
                "    visits, art_group_id, created_at, updated_at, incr_likes";

        String[] split = str.split(",");
        for (String item : split) {
            item = item.trim();
            String camelCase = StrUtil.toCamelCase(item);
            System.out.println(item + " as " + camelCase + ",");
        }
        */

/*        String pwd = "Qwe123asd";
        System.out.println(MD5Util.MD5(MD5Util.MD5(pwd)));*/

//        String topics = "[\"19\", \"20\", \"21\"]";
//        List<Long> topicIdList = JSONUtil.toList(topics, Long.class);
//        System.out.println(topicIdList);

//        List<Integer> list = new ArrayList<>();
//        String[] split = "2,3,4,2,1,2,3,4".split(",");
//        for (String str : split) {
//            list.add(Integer.parseInt(str));
//        }
//        System.out.println(list.get(100));


     /*   ActivityPrizeVo vo = new ActivityPrizeVo();
        vo.setPrizeType(0);
        vo.setPrizeName("");
        vo.setPrizeQuantity(0);
        vo.setPrizeImage("");
        vo.setProbability(new BigDecimal("0"));
        vo.setCheckInType(0);
        vo.setCheckInDays(0);

        System.out.println(JSONUtil.toJsonStr(vo));*/

        Date now = new Date();
        DateTime beginOfWeek = DateUtil.beginOfWeek(now);
        System.out.println(DateUtil.format(beginOfWeek, DatePattern.NORM_DATETIME_PATTERN));

        DateTime endOfWeek = DateUtil.endOfWeek(now);
        System.out.println(DateUtil.format(endOfWeek, DatePattern.NORM_DATETIME_PATTERN));


    }
}
