package com.itheima.spring实战.spittr.web;

import com.itheima.spring实战.spittr.domain.Spittle;
import com.itheima.spring实战.spittr.dao.SpittleRepository;
import com.itheima.spring实战.spittr.exception.DuplicateSpittleException;
import com.itheima.spring实战.spittr.exception.SpittleNotFoundException;
import java.util.Date;
import java.util.List;
import javax.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;

/**
 * @author 胡磊
 * @since 2023/5/1 21:37
 */
@Controller
@RequestMapping("/spittles")
public class SpittleController {

  private static final String MAX_LONG_AS_STRING = "9223372036854775807";

  private SpittleRepository spittleRepository;

  public SpittleController() {}

  @Autowired
  public SpittleController(SpittleRepository spittleRepository) {
    this.spittleRepository = spittleRepository;
  }

  @RequestMapping(method = RequestMethod.GET)
  public List<Spittle> spittles(
      @RequestParam(value = "max", defaultValue = MAX_LONG_AS_STRING) long max,
      @RequestParam(value = "count", defaultValue = "20") int count) {
    return spittleRepository.findSpittles(max, count);
  }

  @RequestMapping(value = "/{spittleId}", method = RequestMethod.GET)
  public String spittle(
      @PathVariable("spittleId") long spittleId,
      Model model) {
    Spittle spittle = spittleRepository.findOne(spittleId);
    if (spittle == null) {
      throw new SpittleNotFoundException();
    }
    model.addAttribute(spittle);
    return "spittle";
  }

  @RequestMapping(value = "/save", method = RequestMethod.POST)
  public String saveSpittle(SpittleForm form) throws Exception {
    System.out.println(form);
    Spittle spittle = spittleRepository.findByMessage(form.getMessage());
    if (spittle != null) {
      throw new DuplicateSpittleException(500, "重复数据");
    }
    spittleRepository.save(new Spittle(form.getMessage(), new Date()));
    return "redirect:/spittles";
  }

//  @ExceptionHandler(DuplicateSpittleException.class)
  public String handleDuplicateSpittle() {
    return "error/duplicate";
  }
}
