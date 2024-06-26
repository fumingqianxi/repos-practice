package com.itheima.commonmistakes.a27clientdata.trustclientip;

import lombok.extern.slf4j.Slf4j;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.servlet.http.HttpServletRequest;
import java.util.HashSet;

/**
 * 不能信任请求头里的任何内容示例.
 *
 * @author 胡磊
 * @since 2024/2/10 22:08
 */
@Slf4j
@RequestMapping("trustclientip")
@RestController
public class TrustClientIpController {

  HashSet<String> activityLimit = new HashSet<>();

  @GetMapping("test")
  public String test(HttpServletRequest request) {
    String ip = getClientIp(request);
    if (activityLimit.contains(ip)) {
      return "您已经领取过奖品";
    } else {
      activityLimit.add(ip);
      return "奖品领取成功";
    }
  }

  private String getClientIp(HttpServletRequest request) {
    String xff = request.getHeader("X-Forwarded-For");
    if (xff == null) {
      return request.getRemoteAddr();
    } else {
      return xff.contains(",") ? xff.split(",")[0] : xff;
    }
  }
}
