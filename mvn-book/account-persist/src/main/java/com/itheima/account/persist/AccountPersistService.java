package com.itheima.account.persist;

/**
 * @author 胡磊
 * @since 2022/12/18 19:27
 */
public interface AccountPersistService {
  Account createAccount( Account account )
      throws AccountPersistException;

  Account readAccount( String id )
      throws AccountPersistException;

  Account updateAccount( Account account )
      throws AccountPersistException;

  void deleteAccount( String id )
      throws AccountPersistException;
}
