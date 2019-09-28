package com.snepp.backend.v1.service;

import com.snepp.backend.v1.exception.EntityNotFoundException;
import com.snepp.backend.v1.model.request.SneppRequest;
import com.snepp.backend.v1.model.request.SneppUpdateRequest;
import com.snepp.backend.v1.model.response.SingleSneppResponse;
import com.snepp.backend.v1.model.response.SneppResponse;

import java.util.List;

/**
 * Created by emre on 28.01.2019
 */
public interface SneppService {
  boolean save(SneppRequest sneppRequest, String userId);

  SingleSneppResponse getSnepp(String id, String userId) throws EntityNotFoundException;

  List<SneppResponse> listSneppByOwnerId(String ownerId);

  SingleSneppResponse updateSnepp(SneppUpdateRequest request) throws EntityNotFoundException;
}
