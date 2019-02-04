package com.snepp.backend.v1.service;

import com.snepp.backend.v1.model.entity.SneppEntity;
import com.snepp.backend.v1.model.request.SneppRequest;
import com.snepp.backend.v1.model.response.SingleSneppResponse;
import com.snepp.backend.v1.model.response.SneppResponse;
import com.snepp.backend.v1.repository.SneppRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Configuration;

import java.time.Instant;
import java.util.List;
import java.util.stream.Collectors;

/**
 * Created by emre on 28.01.2019
 */
@Configuration
public class SneppServiceImpl implements SneppService {
  @Autowired
  private SneppRepository sneppRepository;

  @Override
  public boolean save(SneppRequest sneppRequest) {
    boolean result = false;
    SneppEntity sneppEntity = SneppEntity.builder()
        .ownerId(sneppRequest.ownerId)
        .name(sneppRequest.name)
        .description(sneppRequest.description)
        .language(sneppRequest.language)
        .snippet(sneppRequest.snippet)
        .type(sneppRequest.type)
        .createdAt(Instant.now().toEpochMilli())
        .updatedAt(null)
        .shareList(null)
        .build();
    SneppEntity entity = sneppRepository.save(sneppEntity);
    if (entity.getId() != null) {
      result = true;
    }
    return result;
  }

  @Override
  public SingleSneppResponse getSnepp(String id) {
    SneppEntity sneppEntity = sneppRepository.findById(id);
    return SingleSneppResponse.builder()
        .name(sneppEntity.getName())
        .description(sneppEntity.getDescription())
        .language(sneppEntity.getLanguage())
        .snippet(sneppEntity.getSnippet())
        .type(sneppEntity.getType())
        .ownerId(sneppEntity.getOwnerId())
        .build();
  }

  @Override
  public List<SneppResponse> listSneppByOwnerId(String ownerId) {
    List<SneppEntity> sneppEntities = sneppRepository.listByOwnerId(ownerId);
    return sneppEntities.stream()
        .map(sneppEntity ->
            SneppResponse.builder()
                .id(sneppEntity.getId().toString())
                .name(sneppEntity.getName())
                .snippet(sneppEntity.getSnippet())
                .build())
        .collect(Collectors.toList());
  }
}
