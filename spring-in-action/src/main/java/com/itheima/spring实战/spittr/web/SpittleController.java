package com.itheima.spring实战.spittr.web;

import com.itheima.spring实战.spittr.Spittle;
import com.itheima.spring实战.spittr.data.SpittleRepository;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.PathVariable;
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

//  @Autowired
  public SpittleController(SpittleRepository spittleRepository) {
    this.spittleRepository = spittleRepository;
  }

  @RequestMapping(method=RequestMethod.GET)
  public List<Spittle> spittles(
      @RequestParam(value = "max", defaultValue = MAX_LONG_AS_STRING) long max,
      @RequestParam(value = "count", defaultValue = "20") int count) {
    List<Spittle> expectedSpittles = createSpittleList(50);
//    return spittleRepository.findSpittles(max, count);
    return expectedSpittles;
  }

  @RequestMapping(value="/{spittleId}", method= RequestMethod.GET)
  public String spittle(
      @PathVariable("spittleId") long spittleId,
      Model model) {
//    model.addAttribute(spittleRepository.findOne(spittleId));
    Spittle expectedSpittle = new Spittle("Hello", new Date());
    model.addAttribute(expectedSpittle);
    return "spittle";
  }

  private List<Spittle> createSpittleList(int count) {
    List<Spittle> spittles = new ArrayList<Spittle>();
    for (int i=0; i < count; i++) {
      spittles.add(new Spittle("Spittle " + i, new Date()));
    }
    return spittles;
  }
}
