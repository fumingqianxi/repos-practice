package com.itheima.spring实战.spittr.web;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

/**
 * @author 胡磊
 * @since 2023/5/1 18:57
 */
@Controller
@RequestMapping({"/", "homepage"})
public class HomeController {

  @RequestMapping(method = RequestMethod.GET)
  public String home(Model model) {
    return "home";
  }
}
