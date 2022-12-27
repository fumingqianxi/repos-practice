package com.itheima.account.persist;

/**
 * @author 胡磊
 * @since 2022/12/18 19:27
 */
public interface AccountPersistService {

  Account createAccount(Account account);

  Account readAccount(String id);

  Account updateAccount(Account account);

  void deleteAccount(String id);
}
