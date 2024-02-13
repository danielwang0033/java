package com.coin.i18n;

import com.coin.entity.Guess;
import com.coin.enums.NotificationType;
import com.coin.resp.guess.BetUserVo;

import java.math.BigDecimal;

public interface LongTextTranslate {

    String resetPasswordMailHtml(String token);

    String buildSettleNote(Guess guess, BetUserVo betItem, BigDecimal settleAmountDown);

    String buildNotificationMessage(NotificationType notificationType, Object ... params);

    String verifyEmailHtml(String name, String verificationUrl);

    String registerEmailHtml(String username, String verificationUrl);

    String buildBetNote(String guessSubject, String title, String itemName, int betAmount, BigDecimal itemOdds);
}
