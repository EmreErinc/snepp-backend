package com.snepp.backend;

import com.snepp.backend.v1.model.request.SneppRequest;
import com.snepp.backend.v1.model.response.SingleSneppResponse;
import com.snepp.backend.v1.service.SneppService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

@RestController
public class HealthController {
  private SneppService sneppService;

  @Autowired
  public HealthController(SneppService sneppService) {
    this.sneppService = sneppService;
  }

  /*@RequestMapping(method = RequestMethod.GET, value = "/")
  public String health(){
    return "healthy connection";
  }*/

  @RequestMapping(method = RequestMethod.GET, value = "/")
  public SingleSneppResponse snepp(){
    String sneppId = "5c5822bb82ef09251adac5b5";
    return sneppService.getSnepp(sneppId);
  }
}
