package com.coin.service.util;

import cn.hutool.core.collection.CollectionUtil;
import cn.hutool.core.util.StrUtil;
import cn.hutool.json.JSONUtil;

import java.util.ArrayList;
import java.util.List;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class RichTextUtil {

    private static final Pattern p_image = Pattern.compile("<img.*src\\s*=\\s*(.*?)[^>]*?>", Pattern.CASE_INSENSITIVE);

    private static final Pattern r_image = Pattern.compile("src\\s*=\\s*\"?(.*?)(\"|>|\\s+)");

    /**
     * 提取富文本中纯文本
     */
    public static String getText(String richText) {
        String regx = "(<.+?>)|(</.+?>)";
        Matcher matcher = Pattern.compile(regx).matcher(richText);
        while (matcher.find()) {
            // 替换图片
            richText = matcher.replaceAll("").replace(" ", "");
        }
        return richText;
    }

    public static String buildSummary(String text, int limit) {
        String summary = "&nbsp;";
        if (StrUtil.isNotBlank(text)) {
            if (text.length() > limit) {
                summary = text.substring(0, limit) + "...";
            } else {
                summary = text;
            }
        }
        return summary;
    }

    /**
     * 提取富文本中图片地址, 取前topNum张
     */
    public static List<String> getContentTopImages(String richText, int topNum) {
        List<String> list = new ArrayList<>();
        Matcher pMatcher = p_image.matcher(richText);
        if (topNum <= 0) {
            return list;
        }
        int count = 0;
        while (pMatcher.find()) {
            if (count >= topNum) {
                break;
            }
            // 得到<img />数据
            String img = pMatcher.group();
            // 匹配<img>中的src数据
            Matcher rMatcher = r_image.matcher(img);
            while (rMatcher.find()) {
                if (count >= topNum) {
                    break;
                }

                // 兼容特殊情况
                String group = rMatcher.group(1);
                if (StrUtil.isNotBlank(group) && group.startsWith("//")) {
                    group = "http:" + group;
                }

                list.add(group);
                count++;
            }
        }
        return list;
    }

    public static String buildThumbs(List<String> imgList) {
        String thumbs = "[]";
        if (CollectionUtil.isNotEmpty(imgList)) {
            thumbs = JSONUtil.toJsonStr(imgList);
        }
        // 统一替换&#61; 为 =
        return thumbs.replaceAll("&#61;","=");
    }

    public static String processHistoryContent(String content, String pics) {
        return processHistoryContent(content, pics, null);
    }

    public static String processHistoryContent(String content, String pics, String vids) {
        if (StrUtil.isNotBlank(pics) && StrUtil.isNotBlank(content)) {
            if (!pics.equals("[]")) {
                if (content.contains("{image_0}")) {
                    List<String> picList = JSONUtil.toList(pics, String.class);
                    for (int i = 0; i < picList.size(); i++) {
                        content = content.replace("{image_" + i + "}", ImageUtil.completeImageUrl(picList.get(i)));
                    }
                } else if (content.contains("%7bimage_0%7d")) {
                    List<String> picList = JSONUtil.toList(pics, String.class);
                    for (int i = 0; i < picList.size(); i++) {
                        content = content.replace("%7bimage_" + i + "%7d", ImageUtil.completeImageUrl(picList.get(i)));
                    }
                }
            }
        }
        if (StrUtil.isNotBlank(vids) && StrUtil.isNotBlank(content)) {
            if (!vids.equals("[]")) {
                if (content.contains("{video_0}")) {
                    List<String> vidsList = JSONUtil.toList(vids, String.class);
                    for (int i = 0; i < vidsList.size(); i++) {
                        content = content.replace("{video_" + i + "}", ImageUtil.completeImageUrl(vidsList.get(i)));
                    }
                } else if (content.contains("%7bvideo_0%7d")) {
                    List<String> vidsList = JSONUtil.toList(vids, String.class);
                    for (int i = 0; i < vidsList.size(); i++) {
                        content = content.replace("%7bvideo_" + i + "%7d", ImageUtil.completeImageUrl(vidsList.get(i)));
                    }
                }
            }
        }
        return content;
    }
}
