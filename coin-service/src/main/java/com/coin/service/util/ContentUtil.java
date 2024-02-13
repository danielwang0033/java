package com.coin.service.util;

import cn.hutool.core.collection.CollectionUtil;
import cn.hutool.core.util.ObjectUtil;
import cn.hutool.core.util.StrUtil;
import com.coin.entity.*;
import com.coin.resp.ReportsResp;

import java.util.ArrayList;
import java.util.List;

/**
 * 处理以下模块内容图片:
 * article 1
 * goods 1
 * thread 1
 * threadReply
 * report 1
 * guess 1
 */
public class ContentUtil {

    private static final int DESC_LENGTH = 200;

    /**
     * 文章 article
     */
    public static void processArticlesContent(Articles entity) {
        String content = entity.getContent();
        if (StrUtil.isNotBlank(content)) {
            if (ObjectUtil.isNotNull(entity.getId())) {
                // 处理历史数据
                String newContent = RichTextUtil.processHistoryContent(content, entity.getPics());
                entity.setContent(newContent);
            }
            entity.setPics("[]");

            // 更新摘要
            String text = RichTextUtil.getText(entity.getContent());
            entity.setDescription(RichTextUtil.buildSummary(text, DESC_LENGTH));

            // 从内容中提取图片
            List<String> imgList = RichTextUtil.getContentTopImages(entity.getContent(), 1);
            entity.setThumbs(RichTextUtil.buildThumbs(imgList));
        }
    }

    /**
     * 商品 goods
     */
    public static void processGoodsContent(Goods entity) {
        String content = entity.getContent();
        if (StrUtil.isNotBlank(content)) {
            if (ObjectUtil.isNotNull(entity.getId())) {
                // 处理历史数据
                String newContent = RichTextUtil.processHistoryContent(content, entity.getPics());
                entity.setContent(newContent);
            }
            entity.setPics("[]");
        }
    }

    /**
     * 帖子 thread
     */
    public static void processThreadsContent(Threads entity) {
        String content = entity.getContent();
        if (StrUtil.isNotBlank(content)) {
            if (ObjectUtil.isNotNull(entity.getId())) {
                // 处理历史数据
                String newContent = RichTextUtil.processHistoryContent(content, entity.getPics(), entity.getVids());
                entity.setContent(newContent);
            }
            entity.setPics("[]");
            entity.setVids("[]");

            // 更新摘要
            String text = RichTextUtil.getText(entity.getContent());
            entity.setDesc(RichTextUtil.buildSummary(text, DESC_LENGTH));

            // 从内容中提取图片
            List<String> imgList = RichTextUtil.getContentTopImages(entity.getContent(), 4);
            entity.setThumbs(RichTextUtil.buildThumbs(imgList));
        }
    }

    /**
     * 帖子回复 threadReply
     */
    public static void processThreadReplyContent(ThreadReply entity) {
        String content = entity.getContent();
        if (StrUtil.isNotBlank(content)) {
            if (ObjectUtil.isNotNull(entity.getId())) {
                // 处理历史数据
                String newContent = RichTextUtil.processHistoryContent(content, entity.getPics());
                entity.setContent(newContent);
            }
            entity.setPics("[]");
        }
    }

    /**
     * 老哥帮手 Reports
     */
    public static void processReportsContent(Reports entity) {
        String content = entity.getReportContent();
        if (StrUtil.isNotBlank(content)) {
            if (ObjectUtil.isNotNull(entity.getId())) {
                // 处理历史数据
                String newContent = RichTextUtil.processHistoryContent(content, entity.getPics());
                entity.setReportContent(newContent);
            }
            //entity.setPics("[]");

            // 更新摘要
            String text = RichTextUtil.getText(entity.getReportContent());
            entity.setDesc(RichTextUtil.buildSummary(text, DESC_LENGTH));

            // 从内容中提取图片
            List<String> imgList = RichTextUtil.getContentTopImages(entity.getReportContent(), 4);
            entity.setThumbs(RichTextUtil.buildThumbs(imgList));
        }
    }

    public static void processReportsContent(ReportsResp entity) {
        String content = entity.getReportContent();
        if (StrUtil.isNotBlank(content)) {
            if (ObjectUtil.isNotNull(entity.getId())) {
                // 处理历史数据
                String newContent = RichTextUtil.processHistoryContent(content, entity.getPics());
                entity.setReportContent(newContent);
            }
            //entity.setPics("[]");

            // 更新摘要
            String text = RichTextUtil.getText(entity.getReportContent());
            entity.setDesc(RichTextUtil.buildSummary(text, DESC_LENGTH));

            // 从内容中提取图片
            List<String> imgList = RichTextUtil.getContentTopImages(entity.getReportContent(), 4);
            entity.setThumbs(RichTextUtil.buildThumbs(imgList));
        }
    }

    /**
     * 竞猜
     */
    public static void processGuessContent(Guess guess) {
        String content = guess.getContent();
        if (StrUtil.isBlank(content)) {
            // 空值处理
            guess.setContentImages("[]");
            guess.setContentSnippet("[]");
        } else {
            List<String> imgList = RichTextUtil.getContentTopImages(content, 4);
            guess.setContentImages(CollectionUtil.join(imgList, ","));
            if (CollectionUtil.isNotEmpty(imgList)) {
                List<String> imgList2 = new ArrayList<>();
                for (int i = 0; i < imgList.size() && i < 3; i++) {
                    imgList2.add(imgList.get(i));
                }
                guess.setContentImages(CollectionUtil.join(imgList2, ","));
            }
            String text = RichTextUtil.getText(content);
            // 截取前50个字
            int length = text.length();
            if (length >= 50) {
                guess.setContentSnippet(text.substring(0, 50) + "...");
            } else {
                guess.setContentSnippet(text);
            }
        }
    }

    public static String processHighlight(String content, String keyword) {
        if (StrUtil.isNotBlank(content) && StrUtil.isNotBlank(keyword)) {
            return content.replaceAll(keyword, "<span style=\\\"color:#FF9500\\\">" + keyword + "</span>");
        }
        return "";
    }
}
