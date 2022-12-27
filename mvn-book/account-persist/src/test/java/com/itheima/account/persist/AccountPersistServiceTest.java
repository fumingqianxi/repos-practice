package com.itheima.account.persist;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNotNull;
import static org.junit.Assert.assertTrue;

import java.io.File;
import org.junit.Before;
import org.junit.Test;
import org.springframework.context.ApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;

/**
 * @author 胡磊
 * @since 2022/12/16 10:14
 */
public class AccountPersistServiceTest {

  private AccountPersistService service;

  @Before
  public void prepare() throws Exception {
    File persistDataFile = new File("target/test-classes/persist-data.xml");
    if (persistDataFile.exists()) {
      persistDataFile.delete();
    }
    ApplicationContext ctx = new ClassPathXmlApplicationContext("account-persist.xml");
    service = (AccountPersistService) ctx.getBean("accountPersistService");
    Account account = new Account();
    account.setId("juven");
    account.setName("Juven Xu");
    account.setEmail("juven@changeme.com");
    account.setPassword("thisshouldbeencrypted");
    account.setActivated(true);
    service.createAccount(account);
  }

  @Test
  public void testReadAccount() throws Exception {
    Account account = service.readAccount("juven");
    assertNotNull(account);
    assertEquals("juven", account.getId());
    assertEquals("Juven Xu", account.getName());
    assertEquals("juven@changeme.com", account.getEmail());
    assertTrue(account.isActivated());
  }
}
