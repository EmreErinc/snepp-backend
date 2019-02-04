package com.snepp.backend.v1.repository;

import com.snepp.backend.v1.model.entity.SneppEntity;
import com.snepp.backend.v1.model.response.SingleSneppResponse;
import com.snepp.backend.v1.model.response.SneppResponse;

import java.util.List;

/**
 * Created by emre on 28.01.2019
 */
public interface SneppRepository {
  SneppEntity save(SneppEntity sneppEntity);
  SneppEntity findById(String id);
  List<SneppEntity> listByOwnerId(String ownerId);

}
