package com.snepp.backend;

import org.springframework.web.bind.annotation.*;

@RestController
public class HealthController {
  @RequestMapping(method = RequestMethod.GET, value = "/")
  public String health(){
    return "healthy connection";
  }
}
