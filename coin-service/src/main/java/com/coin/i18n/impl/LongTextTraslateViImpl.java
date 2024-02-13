package com.coin.i18n.impl;

import com.coin.entity.Guess;
import com.coin.enums.NotificationType;
import com.coin.i18n.LongTextTranslate;
import com.coin.resp.guess.BetUserVo;
import org.springframework.stereotype.Service;

import java.math.BigDecimal;

/**
 * 越南文
 */
@Service("longTextTranslate_vi")
public class LongTextTraslateViImpl implements LongTextTranslate {

    @Override
    public String registerEmailHtml(String username, String verificationUrl) {
        return "<div class=\"container\">\n" +
                "    <h1>Đăng ký thành công - Bomin.com</h1>\n" +
                "    <p>Kính gửi " + username + "，chúc mừng bạn đã đăng ký thành công trên Bomin.com！</p>\n" +
                "    <p>Vui lòng nhấp vào nút bên dưới để xác minh email của bạn：</p>\n" +
                "    <a class=\"btn\" style=\"background: green;width: 80px;height: 30px;padding: 5px;color: black;\" href=\"" + verificationUrl + "\">Xác minh email</a>\n" +
                "</div>";
    }

    @Override
    public String verifyEmailHtml(String name, String verificationUrl) {
        return "<div class=\"container\">\n" +
                "    <h1>Xác minh email - Bomin.com</h1>\n" +
                "    <p>Kính gửi " + name + "</p>\n" +
                "    <p>Vui lòng nhấp vào nút bên dưới để xác minh email của bạn：</p>\n" +
                "    <a class=\"btn\" href=\"" + verificationUrl + "\">Email xác minh</a>\n" +
                "</div>";
    }

    @Override
    public String resetPasswordMailHtml(String token) {
        return "<h1>Bomin.com-Lấy lại mật khẩu</h1>\n" +
                "\n" +
                "<p>Kính gửi người dùng，</p>\n" +
                "\n" +
                "<p>Bạn nhận được email này vì bạn đang yêu cầu đặt lại mật khẩu. Vui lòng sử dụng mã xác minh bên dưới để hoàn tất việc đặt lại mật khẩu.</p>\n" +
                "\n" +
                "<div style=\"padding: 15px; background-color: #f4f4f4; font-size: 20px;\">\n" +
                "    <strong>Mã xác minh: " + token + "</strong>\n" +
                "</div>\n" +
                "\n" +
                "<p>Mã xác minh này sẽ hết hạn sau 10 phút, vui lòng hoàn tất việc đặt lại mật khẩu càng sớm càng tốt。</p>\n" +
                "\n" +
                "<p>Nếu bạn chưa yêu cầu đặt lại mật khẩu, vui lòng bỏ qua email này.</p>\n" +
                "\n" +
                "<p>Cảm ơn bạn！<br>\n" +
                "Nhóm Bomin.com</p>";
    }

    @Override
    public String buildSettleNote(Guess guess, BetUserVo betItem, BigDecimal settleAmountDown) {
        String note = "";
        Integer settlePattern = betItem.getSettlePattern();
        switch (settlePattern) {
            case 1:
                note = String.format("Đoán thắng cược:[%s]%s,cược:%s,số tiền đặt cược:%s(Bo coins),tỷ lệ cược:%s[%s],số tiền thắng:%s", guess.getGuessSubject(), guess.getTitle(), betItem.getGuessItemName(), betItem.getBetAmount().intValue(), betItem.getBetOdds(), "thắng tất cả", betItem.getAwardAmount());
                break;
            case 2:
                note = String.format("Đoán thắng cược:[%s]%s,cược:%s,số tiền đặt cược:%s(Bo coins),tỷ lệ cược:%s[%s],số tiền thắng:%s", guess.getGuessSubject(), guess.getTitle(), betItem.getGuessItemName(), betItem.getBetAmount().intValue(), betItem.getBetOdds(), "thắng một nửa", betItem.getAwardAmount());
                break;
            case 3:
                note = String.format("Mất một nửa số tiền đoán:[%s]%s,cược:%s,số tiền đặt cược:%s(Bo coins),tỷ lệ cược:%s[%s],số tiền thanh toán:%s", guess.getGuessSubject(), guess.getTitle(), betItem.getGuessItemName(), betItem.getBetAmount().intValue(), betItem.getBetOdds(), "mất một nửa", settleAmountDown);
                break;
            case 5:
                note = String.format("Đoán hoàn tiền cược:[%s]%s,cược:%s,số tiền đặt cược:%s(Bo coins),tỷ lệ cược:%s[%s],số tiền thanh toán:%s", guess.getGuessSubject(), guess.getTitle(), betItem.getGuessItemName(), betItem.getBetAmount().intValue(), betItem.getBetOdds(), "hoàn tiền xu đặt sách", settleAmountDown.intValue());
                break;
        }
        return note;
    }

    @Override
    public String buildNotificationMessage(NotificationType notificationType, Object... params) {
        String message = "";
        switch (notificationType) {
            case NEW_REPORT:
                message = String.format("Người dùng bạn theo dõi <span style='color:#0390F6'>%s</span> đã đăng khiếu nại《<span style='color:#0390F6'>%s</span>》,Hãy đến và xem！", params);
                break;
            case NEW_COMMENT:
                message = String.format("<span style='color:#0390F6'>%s</span> đã trả lời %s《<span style='color:#0390F6'>%s</span>》，của bạn, Đi tiếp và hãy xem!", params);
                break;
            case FOLLOW:
                message = String.format("<span style='color:#0390F6'>%s</span> đang theo dõi bạn!", params);
                break;
            case REPORT_REPLY:
                message = String.format("<span style='color:#0390F6'>%s</span> đã trả lời khiếu nại của bạn《<span style='color:#0390F6'>%s</span>》，vui lòng nhanh tay Hãy kiểm tra xem!！", params);
                break;
            case THREAD_REPLY:
                message = String.format("<span style='color:#0390F6'>%s</span> đã trả lời bài đăng của bạn《<span style='color:#0390F6'>%s</span>》，nhanh tay lên Hãy kiểm tra xem!", params);
                break;
            case NEW_THREAD:
                message = String.format("Người dùng bạn theo dõi <span style='color:#0390F6'>%s</span> đã đăng một bài đăng mới《" + "<span style='color:#0390F6'>%s</span>》, đến xem!", params);
                break;
            case REGISTER:
                message = String.format("Xin chúc mừng, bạn đăng ký thành công! Email xác minh email đã được gửi cho bạn. Email sẽ có hiệu lực trong vòng %s phút. Vui lòng xác minh sớm nhất có thể.", params);
                break;
            case VERIFIED_EMAIL:
                message = String.format("Địa chỉ email liên kết của bạn: %s xác minh thành công!", params);
                break;
            case REPLY_EXT:
                message = String.format("<span style='color:#0390F6'>%s</span> đã trả lời bình luận của bạn<span style='color:#0390F6'>%s</span>", params);
                break;
        }
        return message;
    }

    @Override
    public String buildBetNote(String guessSubject, String title, String itemName, int betAmount, BigDecimal itemOdds) {
        return String.format("Đoán cá cược:[%s]%s,đặt cược:%s,thanh toán:%s(Bo coins),tỷ lệ cược:%s", guessSubject, title, itemName, betAmount, itemOdds);
    }
}
