package com.coin.i18n.impl;

import com.coin.entity.Guess;
import com.coin.enums.NotificationType;
import com.coin.i18n.LongTextTranslate;
import com.coin.resp.guess.BetUserVo;
import org.springframework.stereotype.Service;

import java.math.BigDecimal;

/**
 * 中文
 */
@Service("longTextTranslate_zh")
public class LongTextTraslateCnImpl implements LongTextTranslate {

    @Override
    public String registerEmailHtml(String username, String verificationUrl) {
        return "<div class=\"container\">\n" +
                "    <h1>注册成功 - 博民网</h1>\n" +
                "    <p>尊敬的 " + username + "，恭喜您成功注册博民网！</p>\n" +
                "    <p>请点击下方按钮验证您的邮箱：</p>\n" +
                "    <a class=\"btn\" style=\"background: green;width: 80px;height: 30px;padding: 5px;color: black;\" href=\"" + verificationUrl + "\">验证邮箱</a>\n" +
                "</div>";
    }

    @Override
    public String verifyEmailHtml(String name, String verificationUrl) {
        return "<div class=\"container\">\n" +
                "    <h1>验证电子信箱 - 博民网</h1>\n" +
                "    <p>尊敬的 " + name + "</p>\n" +
                "    <p>请点击下方按钮验证您的邮箱：</p>\n" +
                "    <a class=\"btn\" href=\"" + verificationUrl + "\">验证邮箱</a>\n" +
                "</div>";
    }

    @Override
    public String resetPasswordMailHtml(String token) {
        return "<h1>博民网-找回密码</h1>\n" +
                "\n" +
                "<p>尊敬的用户，</p>\n" +
                "\n" +
                "<p>您收到这封邮件是因为您请求重置密码。请使用下面的验证码来完成密码重置操作。</p>\n" +
                "\n" +
                "<div style=\"padding: 15px; background-color: #f4f4f4; font-size: 20px;\">\n" +
                "    <strong>验证码: " + token + "</strong>\n" +
                "</div>\n" +
                "\n" +
                "<p>该验证码将在 10 分钟后失效，请尽快完成密码重置操作。</p>\n" +
                "\n" +
                "<p>如果您没有请求重置密码，请忽略此邮件。</p>\n" +
                "\n" +
                "<p>谢谢！<br>\n" +
                "    博民网团队</p>";
    }

    @Override
    public String buildSettleNote(Guess guess, BetUserVo betItem, BigDecimal settleAmountDown) {
        String note = "";
        Integer settlePattern = betItem.getSettlePattern();
        switch (settlePattern) {
            case 1:
                note = String.format("竞猜投注中奖:[%s]%s,投注:%s,投注额:%s(博币),赔率:%s[%s],中奖额:%s", guess.getGuessSubject(), guess.getTitle(), betItem.getGuessItemName(), betItem.getBetAmount().intValue(), betItem.getBetOdds(), "全赢", betItem.getAwardAmount());
                break;
            case 2:
                note = String.format("竞猜投注中奖:[%s]%s,投注:%s,投注额:%s(博币),赔率:%s[%s],中奖额:%s", guess.getGuessSubject(), guess.getTitle(), betItem.getGuessItemName(), betItem.getBetAmount().intValue(), betItem.getBetOdds(), "赢半", betItem.getAwardAmount());
                break;
            case 3:
                note = String.format("竞猜投注输半:[%s]%s,投注:%s,投注额:%s(博币),赔率:%s[%s],结算额:%s", guess.getGuessSubject(), guess.getTitle(), betItem.getGuessItemName(), betItem.getBetAmount().intValue(), betItem.getBetOdds(), "输半", settleAmountDown);
                break;
            case 5:
                note = String.format("竞猜投注退还:[%s]%s,投注:%s,投注额:%s(博币),赔率:%s[%s],结算额:%s", guess.getGuessSubject(), guess.getTitle(), betItem.getGuessItemName(), betItem.getBetAmount().intValue(), betItem.getBetOdds(), "博币退还", settleAmountDown.intValue());
                break;
        }
        return note;
    }

    @Override
    public String buildNotificationMessage(NotificationType notificationType, Object... params) {
        String message = "";
        switch (notificationType) {
            case NEW_REPORT:
                message = String.format("您关注的用户 <span style='color:#0390F6'>%s</span> 发表了申诉《<span style='color:#0390F6'>%s</span>》,快去围观吧！", params);
                break;
            case NEW_COMMENT:
                message = String.format("<span style='color:#0390F6'>%s</span> 回复了您的%s《<span style='color:#0390F6'>%s</span>》，快去看看吧！", params);
                break;
            case FOLLOW:
                message = String.format("<span style='color:#0390F6'>%s</span> 关注了您！", params);
                break;
            case REPORT_REPLY:
                message = String.format("<span style='color:#0390F6'>%s</span> 回复了您的申诉《<span style='color:#0390F6'>%s</span>》，快去看看吧！", params);
                break;
            case THREAD_REPLY:
                message = String.format("<span style='color:#0390F6'>%s</span> 回复了您的帖子《<span style='color:#0390F6'>%s</span>》，快去看看吧！", params);
                break;
            case NEW_THREAD:
                message = String.format("您关注的用户 <span style='color:#0390F6'>%s</span> 发表了新帖子《" + "<span style='color:#0390F6'>%s</span>》,快去围观吧！", params);
                break;
            case REGISTER:
                message = String.format("恭喜您,注册成功！一封邮箱验证邮件已经发送给您，邮件%s分钟内有效，请尽快验证。", params);
                break;
            case VERIFIED_EMAIL:
                message = String.format("您关联的邮箱: %s 验证成功!", params);
                break;
            case REPLY_EXT:
                message = String.format("<span style='color:#0390F6'>%s</span> 回复了您的评论<span style='color:#0390F6'>%s</span>", params);
                break;
        }
        return message;
    }

    @Override
    public String buildBetNote(String guessSubject, String title, String itemName, int betAmount, BigDecimal itemOdds) {
        return String.format("竞猜投注:[%s]%s,投注:%s,支付:%s(博币),赔率:%s", guessSubject, title, itemName, betAmount, itemOdds);
    }
}
