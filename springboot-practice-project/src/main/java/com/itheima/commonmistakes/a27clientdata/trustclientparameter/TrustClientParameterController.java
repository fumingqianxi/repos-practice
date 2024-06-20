package com.itheima.commonmistakes.a27clientdata.trustclientparameter;

import com.itheima.commonmistakes.exceptionhandler.BusinessException;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.stream.Collectors;

/**
 * 客户端提交的参数需要校验示例.
 *
 * @author 胡磊
 * @since 2024/2/10 22:08
 */
@Slf4j
@RequestMapping("trustclientparameter")
@Controller
@Validated
public class TrustClientParameterController {

  private final HashMap<Integer, Country> allCountries = new HashMap<>();

  public TrustClientParameterController() {
    allCountries.put(1, new Country(1, "China"));
    allCountries.put(2, new Country(2, "US"));
    allCountries.put(3, new Country(3, "UK"));
    allCountries.put(4, new Country(4, "Japan"));
  }

  @GetMapping("/")
  public String index(ModelMap modelMap) {
    List<Country> countries = new ArrayList<>();
    countries.addAll(allCountries.values().stream().filter(country -> country.getId() < 4)
                         .collect(Collectors.toList()));
    modelMap.addAttribute("countries", countries);
    return "index";
  }

  @PostMapping("/wrong")
  @ResponseBody
  public String wrong(@RequestParam("countryId") int countryId) {
    return allCountries.get(countryId).getName();
  }

  @PostMapping("/right")
  @ResponseBody
  public String right(@RequestParam("countryId") int countryId) {
    if (countryId < 1 || countryId > 3) {throw new BusinessException("非法参数", -1);}
    return allCountries.get(countryId).getName();
  }

  @PostMapping("/better")
  @ResponseBody
  public String better(
      @RequestParam("countryId") int countryId) {
    return allCountries.get(countryId).getName();
  }
}
