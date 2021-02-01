package com.snepp.backend.v1.controller;

import com.snepp.backend.v1.model.request.SneppRequest;
import com.snepp.backend.v1.model.request.SneppUpdateRequest;
import com.snepp.backend.v1.model.response.Response;
import com.snepp.backend.v1.model.response.SingleSneppResponse;
import com.snepp.backend.v1.model.response.SneppResponse;
import com.snepp.backend.v1.service.SneppService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpHeaders;
import org.springframework.web.bind.annotation.*;

import java.util.List;

/**
 * Created by emre on 28.01.2019
 */
@Slf4j
@CrossOrigin()
@RestController
@RequestMapping(value = "/v1/snepp")
public class SneppController extends BaseController {
  private SneppService sneppService;

  @Autowired
  public SneppController(SneppService sneppService) {
    this.sneppService = sneppService;
  }

  @RequestMapping(method = RequestMethod.POST)
  public Response<Boolean> add(@RequestBody SneppRequest sneppRequest, @RequestHeader HttpHeaders headers) {
    return new Response<>(sneppService.save(sneppRequest, getUserIdFromHeader(headers)));
  }

  @RequestMapping(method = RequestMethod.GET, value = "/{sneppId}")
  public SingleSneppResponse getSnepp(@PathVariable String sneppId, @RequestHeader HttpHeaders headers) throws Exception {
    return sneppService.getSnepp(sneppId, getUserIdFromHeader(headers));
  }

  @RequestMapping(method = RequestMethod.PUT, value = "/{sneppId}")
  public SingleSneppResponse updateSnepp(@PathVariable String sneppId, @RequestBody SneppUpdateRequest request, @RequestHeader HttpHeaders headers) throws Exception {
    return sneppService.updateSnepp(sneppId, request, getUserIdFromHeader(headers));
  }

  @RequestMapping(method = RequestMethod.DELETE, value = "/{sneppId}")
  public Response<Boolean> deleteSnepp(@PathVariable String sneppId, @RequestHeader HttpHeaders headers) throws Exception {
    return new Response<>(sneppService.deleteSnepp(sneppId, getUserIdFromHeader(headers)));
  }

  @RequestMapping(method = RequestMethod.GET, value = "/list")
  public List<SneppResponse> list(@RequestHeader HttpHeaders headers) {
    return sneppService.listSneppByOwnerId(getUserIdFromHeader(headers));
  }
}
