package com.snepp.backend.v1.repository;

import com.snepp.backend.v1.model.entity.SneppEntity;
import com.snepp.backend.v1.model.request.SneppUpdateRequest;

import java.util.List;
import java.util.Optional;

/**
 * Created by emre on 28.01.2019
 */
public interface SneppRepository {
  SneppEntity save(SneppEntity sneppEntity);

  Optional<SneppEntity> findById(String id);

  Optional<List<SneppEntity>> listByOwnerId(String ownerId);

  Optional<SneppEntity> update(SneppUpdateRequest request);

  Boolean deleteById(String id);

  Boolean isExists(String id);
}
