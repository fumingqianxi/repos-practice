package com.itheima.account.persist.impl;

import com.itheima.account.persist.Account;
import com.itheima.account.persist.AccountPersistService;
import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.OutputStreamWriter;
import java.io.Writer;
import java.util.List;
import org.dom4j.Document;
import org.dom4j.DocumentException;
import org.dom4j.DocumentFactory;
import org.dom4j.Element;
import org.dom4j.io.OutputFormat;
import org.dom4j.io.SAXReader;
import org.dom4j.io.XMLWriter;

/**
 * @author 胡磊
 * @since 2022/12/18 21:47
 */
public class AccountPersistServiceImpl implements AccountPersistService {
  private String file;
  private SAXReader reader = new SAXReader();

  private static final String ELEMENT_ROOT = "root";
  private static final String ELEMENT_ACCOUNTS = "accounts";
  private static final String ELEMENT_ACCOUNT_ID = "id";
  private static final String ELEMENT_ACCOUNT_NAME = "name";
  private static final String ELEMENT_ACCOUNT_EMAIL = "email";
  private static final String ELEMENT_ACCOUNT_PASSWORD = "password";
  private static final String ELEMENT_ACCOUNT_ACTIVATED = "activated";

  public String getFile() {
    return file;
  }

  public void setFile(String file) {
    this.file = file;
  }

  private Document readDocument() {
    File dataFile = new File(file);
    if (!dataFile.exists()) {
      dataFile.getParentFile().mkdirs();
      Document doc = DocumentFactory.getInstance().createDocument();
      Element rootEle = doc.addElement(ELEMENT_ROOT);
      rootEle.addElement(ELEMENT_ACCOUNTS);
      writeDocument(doc);
    }
    try {
      return reader.read(new File(file));
    } catch (DocumentException e) {
      e.printStackTrace();
      return null;
    }
  }

  private void writeDocument(Document doc) {
    Writer out = null;
    try {
      out = new OutputStreamWriter(new FileOutputStream(file), "utf-8");
      XMLWriter writer = new XMLWriter(out, OutputFormat.createPrettyPrint());
      writer.write(doc);
    } catch (IOException e) {
      e.printStackTrace();
    } finally {
      try {
        out.close();
      } catch (IOException e) {
        e.printStackTrace();
      }
    }
  }

  @Override
  public Account createAccount(Account account) {
    Document document = readDocument();
    Element accountsEle = document.getRootElement().element(ELEMENT_ACCOUNTS);
    Element accountEle = accountsEle.addElement("account");
    accountEle.addElement(ELEMENT_ACCOUNT_ID).setText(account.getId());
    accountEle.addElement(ELEMENT_ACCOUNT_NAME).setText(account.getName());
    accountEle.addElement(ELEMENT_ACCOUNT_EMAIL).setText(account.getEmail());
    accountEle.addElement(ELEMENT_ACCOUNT_PASSWORD).setText(account.getPassword());
    accountEle.addElement(ELEMENT_ACCOUNT_ACTIVATED).setText("true");
    writeDocument(document);
    return account;
  }

  @Override
  public Account readAccount(String id) {
    Document doc = readDocument();
    Element accountsEle = doc.getRootElement().element(ELEMENT_ACCOUNTS);
    for (Element accountEle : (List<Element>) accountsEle.elements()) {
      if (accountEle.elementText(ELEMENT_ACCOUNT_ID).equals(id)) {
        return buildAccount(accountEle);
      }
    }
    return null;
  }

  private Account buildAccount(Element element) {
    Account account = new Account();
    account.setId(element.elementText(ELEMENT_ACCOUNT_ID));
    account.setName(element.elementText(ELEMENT_ACCOUNT_NAME));
    account.setEmail(element.elementText(ELEMENT_ACCOUNT_EMAIL));
    account.setPassword(element.elementText(ELEMENT_ACCOUNT_PASSWORD));
    account.setActivated("true".equals(element.elementText(ELEMENT_ACCOUNT_ACTIVATED))? true : false);
    return account;
  }

  @Override
  public Account updateAccount(Account account) {
    return null;
  }

  @Override
  public void deleteAccount(String id) {

  }
}
