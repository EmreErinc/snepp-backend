package com.snepp.backend.v1.controller;

import com.snepp.backend.v1.model.request.SneppRequest;
import com.snepp.backend.v1.model.response.SingleSneppResponse;
import com.snepp.backend.v1.model.response.SneppResponse;
import com.snepp.backend.v1.service.SneppService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

/**
 * Created by emre on 28.01.2019
 */
@CrossOrigin()
@RestController
@RequestMapping(value = "/v1/snepp")
public class SneppController {
  private SneppService sneppService;

  @Autowired
  public SneppController(SneppService sneppService) {
    this.sneppService = sneppService;
  }

  @RequestMapping(method = RequestMethod.POST, value = "/")
  public boolean add(@RequestBody SneppRequest sneppRequest) {
    return sneppService.save(sneppRequest);
  }

  @RequestMapping(method = RequestMethod.GET, value = "/{sneppId}")
  public SingleSneppResponse snepp(@PathVariable String sneppId) {
    return sneppService.getSnepp(sneppId);
  }

  @RequestMapping(method = RequestMethod.GET, value = "/list/{ownerId}")
  public List<SneppResponse> list(@PathVariable String ownerId) {
    return sneppService.listSneppByOwnerId(ownerId);
  }
}
