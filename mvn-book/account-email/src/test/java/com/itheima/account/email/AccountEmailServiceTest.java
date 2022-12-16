package com.itheima.account.email;

import static org.junit.Assert.assertEquals;

import com.icegreen.greenmail.util.GreenMail;
import com.icegreen.greenmail.util.GreenMailUtil;
import com.icegreen.greenmail.util.ServerSetup;
import javax.mail.internet.MimeMessage;
import org.junit.After;
import org.junit.Before;
import org.junit.Test;
import org.springframework.context.ApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;

/**
 * @author 胡磊
 * @since 2022/12/16 10:14
 */
public class AccountEmailServiceTest {

  private GreenMail greenMail;

  @Before
  public void startMailServer() {
    greenMail = new GreenMail(ServerSetup.SMTP);
    greenMail.setUser("test2@gmail.com", "123456");
    greenMail.start();
  }

  @Test
  public void testSendMail() throws Exception {
    ApplicationContext ctx = new ClassPathXmlApplicationContext("account-email.xml");
    AccountEmailService accountEmailService =
        (AccountEmailService) ctx.getBean("accountEmailService");
    String subject = "Test Subject";
    String htmlText = "<h3>Test</h3>";
    accountEmailService.sendMail("test2@gmail.com", subject, htmlText);
    greenMail.waitForIncomingEmail(2000, 1);

    MimeMessage[] msgs = greenMail.getReceivedMessages();
    assertEquals(1, msgs.length);
    assertEquals(subject, msgs[0].getSubject());
    assertEquals(htmlText, GreenMailUtil.getBody(msgs[0]).trim());

  }

  @After
  public void stopMailServer() {
    greenMail.stop();
  }
}
