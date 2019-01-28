package com.snepp.backend;

import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class HealthController {
  @RequestMapping(value = "/", method = RequestMethod.GET)
  public String health(){
    return "healthy connection";
  }
}
