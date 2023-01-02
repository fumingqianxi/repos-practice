package com.itheima.account.email.impl;

import com.itheima.account.email.AccountEmailException;
import com.itheima.account.email.AccountEmailService;
import javax.mail.MessagingException;
import javax.mail.internet.MimeMessage;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.mail.javamail.MimeMessageHelper;

/**
 * @author 胡磊
 * @since 2022/12/15 17:33
 */
public class AccountEmailServiceImpl implements AccountEmailService {

  private JavaMailSender javaMailSender;

  private String systemEmail;

  @Override
  public void sendMail(String to, String subject, String htmlText) throws AccountEmailException {
    try {
      MimeMessage msg = javaMailSender.createMimeMessage();
      MimeMessageHelper msgHelper = new MimeMessageHelper(msg);

      msgHelper.setFrom(systemEmail);
      msgHelper.setTo(to);
      msgHelper.setSubject(subject);
      msgHelper.setText(htmlText, true);
      javaMailSender.send(msg);
    } catch (MessagingException e) {
      e.printStackTrace();
      throw new AccountEmailException( "Faild to send mail.", e );
    }
  }

  public JavaMailSender getJavaMailSender() {
    return javaMailSender;
  }

  public void setJavaMailSender(JavaMailSender javaMailSender) {
    this.javaMailSender = javaMailSender;
  }

  public String getSystemEmail() {
    return systemEmail;
  }

  public void setSystemEmail(String systemEmail) {
    this.systemEmail = systemEmail;
  }
}
