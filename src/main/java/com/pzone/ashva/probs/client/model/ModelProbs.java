package com.pzone.ashva.probs.client.model;

import java.util.Map;

import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.annotation.JsonInclude.Include;

/**
 * @author Ashva Probs Team
 *
 */
@JsonInclude(value = Include.NON_NULL)
public class ModelProbs {

  private String            modelName;
  private Map<String, Prob> prgToProbs;

  public String getModelName() {
    return modelName;
  }

  public void setModelName(String modelName) {
    this.modelName = modelName;
  }

  public Map<String, Prob> getPrgToProbs() {
    return prgToProbs;
  }

  public void setPrgToProbs(Map<String, Prob> prgToProbs) {
    this.prgToProbs = prgToProbs;
  }

}
