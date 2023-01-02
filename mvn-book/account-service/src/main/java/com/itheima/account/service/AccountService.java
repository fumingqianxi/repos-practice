package com.itheima.account.service;

/**
 * @author 胡磊
 * @since 2023/1/1 19:51
 */
public interface AccountService {
  String generateCaptchaKey() throws AccountServiceException;

  byte[] generateCaptchaImage(String capthaKey) throws AccountServiceException;

  void signUp(SignUpRequest signUpRequest) throws AccountServiceException;

  void activate(String acticationNumber) throws AccountServiceException;

  void login(String id, String password) throws AccountServiceException;
}
