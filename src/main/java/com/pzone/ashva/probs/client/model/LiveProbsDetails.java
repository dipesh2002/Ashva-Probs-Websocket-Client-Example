package com.pzone.ashva.probs.client.model;

import java.util.List;
import java.util.Map;

import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.annotation.JsonInclude.Include;

/**
 * @author Ashva Probs Team
 *
 */
@JsonInclude(value = Include.NON_NULL)
public class LiveProbsDetails {

  private RaceKey                 raceKey;
  private String                  lastUpdated;
  private List<TriggerType>       triggerTypes;
  private Map<String, ModelProbs> modelProbs;

  public RaceKey getRaceKey() {
    return raceKey;
  }

  public void setRaceKey(RaceKey raceKey) {
    this.raceKey = raceKey;
  }

  public String getLastUpdated() {
    return lastUpdated;
  }

  public void setLastUpdated(String lastUpdated) {
    this.lastUpdated = lastUpdated;
  }

  public List<TriggerType> getTriggerTypes() {
    return triggerTypes;
  }

  public void setTriggerTypes(List<TriggerType> triggerTypes) {
    this.triggerTypes = triggerTypes;
  }

  public Map<String, ModelProbs> getModelProbs() {
    return modelProbs;
  }

  public void setModelProbs(Map<String, ModelProbs> modelProbs) {
    this.modelProbs = modelProbs;
  }

}
