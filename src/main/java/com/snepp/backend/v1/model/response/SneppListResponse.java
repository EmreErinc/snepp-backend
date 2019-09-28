package com.snepp.backend.v1.model.response;

import com.fasterxml.jackson.databind.annotation.JsonSerialize;
import lombok.Builder;
import lombok.Getter;
import lombok.Setter;

import java.util.List;

/**
 * Created by emre on 28.01.2019
 */
@Builder
@Getter
@Setter
@JsonSerialize
public class SneppListResponse {
  private List<SneppResponse> sneppResponseList;
}
