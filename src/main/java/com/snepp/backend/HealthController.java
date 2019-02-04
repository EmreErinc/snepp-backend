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

  @RequestMapping(method = RequestMethod.GET, value = "/")
  public String health(){
    return "healthy connection";
  }

  @RequestMapping(method = RequestMethod.GET, value = "/a")
  public SingleSneppResponse snepp(@PathVariable String sneppId){
    return sneppService.getSnepp(sneppId);
  }

  /*@RequestMapping(method = RequestMethod.POST, value = "/")
  public boolean add(@RequestBody SneppRequest sneppRequest){
    return sneppService.save(sneppRequest);
  }

  @RequestMapping(method = RequestMethod.GET, value = "/{sneppId}")
  public SingleSneppResponse snepp(@PathVariable String sneppId){
    return sneppService.getSnepp(sneppId);
  }*/
}
