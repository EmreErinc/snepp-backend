package com.snepp.backend.v1.service;

import com.snepp.backend.v1.exception.AuthorizationException;
import com.snepp.backend.v1.exception.EntityNotFoundException;
import com.snepp.backend.v1.model.entity.SneppEntity;
import com.snepp.backend.v1.model.request.SneppRequest;
import com.snepp.backend.v1.model.request.SneppUpdateRequest;
import com.snepp.backend.v1.model.response.SingleSneppResponse;
import com.snepp.backend.v1.model.response.SneppResponse;
import com.snepp.backend.v1.repository.SneppRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Configuration;

import java.time.Instant;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

/**
 * Created by emre on 28.01.2019
 */
@Configuration
public class SneppServiceImpl implements SneppService {
  @Autowired
  private SneppRepository sneppRepository;

  @Override
  public Boolean save(SneppRequest sneppRequest, String userId) {
    boolean result = false;
    SneppEntity sneppEntity = SneppEntity.builder()
        .ownerId(userId)
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
  public SingleSneppResponse getSnepp(String id, String userId) throws Exception {
    authorizationControl(userId, id);

    Optional<SneppEntity> sneppEntity = sneppRepository.findById(id);
    if (sneppEntity.isPresent()) {
      return SingleSneppResponse.builder()
          .name(sneppEntity.get().getName())
          .description(sneppEntity.get().getDescription())
          .language(sneppEntity.get().getLanguage())
          .snippet(sneppEntity.get().getSnippet())
          .type(sneppEntity.get().getType())
          .ownerId(sneppEntity.get().getOwnerId())
          .build();
    } else {
      throw new EntityNotFoundException(SneppEntity.class, "id", id);
    }
  }

  @Override
  public List<SneppResponse> listSneppByOwnerId(String ownerId) {
    Optional<List<SneppEntity>> sneppEntities = sneppRepository.listByOwnerId(ownerId);
    return sneppEntities
        .get()
        .stream()
        .map(sneppEntity ->
            SneppResponse
                .builder()
                .id(sneppEntity.getId().toString())
                .name(sneppEntity.getName())
                .snippet(sneppEntity.getSnippet())
                .build())
        .collect(Collectors.toList());
  }

  @Override
  public SingleSneppResponse updateSnepp(String id, SneppUpdateRequest request, String userId) throws Exception {
    authorizationControl(userId, id);

    if (sneppRepository.isExists(id)) {
      Optional<SneppEntity> sneppEntity = sneppRepository.update(id, request);
      return SingleSneppResponse.builder()
          .name(sneppEntity.get().getName())
          .description(sneppEntity.get().getDescription())
          .language(sneppEntity.get().getLanguage())
          .snippet(sneppEntity.get().getSnippet())
          .type(sneppEntity.get().getType())
          .build();
    } else {
      throw new EntityNotFoundException(SneppEntity.class, "id", id);
    }
  }

  @Override
  public Boolean deleteSnepp(String id, String userId) throws Exception {
    authorizationControl(userId, id);

    if (sneppRepository.isExists(id)) {
      return sneppRepository.deleteById(id);
    } else {
      throw new EntityNotFoundException(SneppEntity.class, "id", id);
    }
  }

  private void authorizationControl(String userId, String sneppId) throws AuthorizationException{
    if (!sneppRepository.isAuthorized(userId, sneppId)){
      throw new AuthorizationException();
    }
  }
}