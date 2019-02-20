package com.snepp.backend.v1.service;

import com.snepp.backend.v1.model.request.SneppRequest;
import com.snepp.backend.v1.model.response.SingleSneppResponse;
import com.snepp.backend.v1.model.response.SneppResponse;

import java.util.List;

/**
 * Created by emre on 28.01.2019
 */
public interface SneppService {
  boolean save(SneppRequest sneppRequest);

  SingleSneppResponse getSnepp(String id);

  List<SneppResponse> listSneppByOwnerId(String ownerId);
}
