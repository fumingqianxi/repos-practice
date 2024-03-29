package com.itheima.account.service.impl;

import com.itheima.account.captcha.AccountCaptchaException;
import com.itheima.account.captcha.AccountCaptchaService;
import com.itheima.account.captcha.RandomGenerator;
import com.itheima.account.email.AccountEmailException;
import com.itheima.account.email.AccountEmailService;
import com.itheima.account.persist.Account;
import com.itheima.account.persist.AccountPersistException;
import com.itheima.account.persist.AccountPersistService;
import com.itheima.account.service.AccountService;
import com.itheima.account.service.AccountServiceException;
import com.itheima.account.service.SignUpRequest;
import java.util.HashMap;
import java.util.Map;

/**
 * @author 胡磊
 * @since 2023/1/1 20:52
 */
public class AccountServiceImpl implements AccountService {
  private Map<String, String> activationMap = new HashMap<String, String>();
  private AccountPersistService accountPersistService;

  public AccountPersistService getAccountPersistService() {
    return accountPersistService;
  }

  public void setAccountPersistService(
      AccountPersistService accountPersistService) {
    this.accountPersistService = accountPersistService;
  }

  public AccountEmailService getAccountEmailService() {
    return accountEmailService;
  }

  public void setAccountEmailService(AccountEmailService accountEmailService) {
    this.accountEmailService = accountEmailService;
  }

  public AccountCaptchaService getAccountCaptchaService() {
    return accountCaptchaService;
  }

  public void setAccountCaptchaService(
      AccountCaptchaService accountCaptchaService) {
    this.accountCaptchaService = accountCaptchaService;
  }

  private AccountEmailService accountEmailService;

  private AccountCaptchaService accountCaptchaService;

  public String generateCaptchaKey() throws AccountServiceException {
    try {
      return accountCaptchaService.generateCaptchaKey();
    } catch (AccountCaptchaException e) {
      throw new AccountServiceException("Unable to generate Captcha key.", e);
    }
  }

  public byte[] generateCaptchaImage(String captchaKey) throws AccountServiceException {
    try {
      return accountCaptchaService.generateCaptchaImage(captchaKey);
    } catch (AccountCaptchaException e) {
      throw new AccountServiceException("Unable to generate Captcha Image.", e);
    }
  }

  public void signUp(SignUpRequest signUpRequest) throws AccountServiceException {
    try {
      if (!signUpRequest.getPassword().equals(signUpRequest.getConfirmPassword())) {
        throw new RuntimeException("2 passwords do not match.");
      }
      if (!accountCaptchaService.validateCaptcha(
          signUpRequest.getCaptchaKey(), signUpRequest.getCaptchaValue())) {
        throw new RuntimeException("Incorrect Captcha.");
      }
      Account account = new Account();
      account.setId(signUpRequest.getId());
      account.setEmail(signUpRequest.getEmail());
      account.setName(signUpRequest.getName());
      account.setPassword(signUpRequest.getPassword());
      account.setActivated(false);

      accountPersistService.createAccount(account);
      String activationId = RandomGenerator.getRandomString();
      activationMap.put(activationId, account.getId());
      String link =
          signUpRequest.getActivateServiceUrl().endsWith("/") ?
              signUpRequest.getActivateServiceUrl()
                  + activationId : signUpRequest.getActivateServiceUrl() + "?key=" + activationId;

      accountEmailService.sendMail(account.getEmail(), "Please Activate Your Account", link);
    } catch (AccountCaptchaException e) {
      throw new AccountServiceException("Unable to validate captcha.", e);
    } catch (AccountPersistException e) {
      throw new AccountServiceException("Unable to create account.", e);
    } catch (AccountEmailException e) {
      throw new AccountServiceException("Unable to send actiavtion mail.", e);
    }
  }

  public void activate(String acticationNumber) throws AccountServiceException {
    String accountId = activationMap.get(acticationNumber);
    if (accountId == null) {
      throw new AccountServiceException("Invalid account activation ID.");
    }
    try {
      Account account = accountPersistService.readAccount(accountId);
      account.setActivated(true);
      accountPersistService.updateAccount(account);
    } catch (AccountPersistException e) {
      throw new AccountServiceException("Unable to activate account.");
    }
  }

  public void login(String id, String password) throws AccountServiceException {
    try {
      Account account = accountPersistService.readAccount(id);
      if (account == null) {
        throw new RuntimeException("Account does not exist.");
      }

      if (!account.isActivated()) {
        throw new RuntimeException("Account is disabled.");
      }

      if (!account.getPassword().equals(password)) {
        throw new RuntimeException("Incorrect password.");
      }
    } catch (AccountPersistException e) {
      throw new AccountServiceException("Unable to log in.", e);
    }
  }
}
