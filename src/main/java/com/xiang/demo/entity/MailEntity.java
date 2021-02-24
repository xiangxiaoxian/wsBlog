package com.xiang.demo.entity;

import lombok.Data;
import lombok.Getter;
import lombok.Setter;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Configuration;

/**
 * @author admin
 * @version 1.0.0 @ClassName MailEntity.java @Description TODO
 * @createTime 2021年02月24日 10:50:00
 */
@Getter
@Setter
@Configuration
public class MailEntity {

  /*
  邮件发送方
  */
  private static String fromEmail="1924837954@qq.com";

  /*
   * 邮件标题
   * */
  private String subject = "微思博客";

  /*
   * 邮件内容
   * */
  private String content;

  public void setContent(String content) {
    String defaultContent = "【微思博客】" + "\n" + " 您的验证码为：" + content + ",请注意安全，不要随意泄露验证码";
    this.content = defaultContent;
  }

  public static String getFromEmail() {
    return fromEmail;
  }
}
