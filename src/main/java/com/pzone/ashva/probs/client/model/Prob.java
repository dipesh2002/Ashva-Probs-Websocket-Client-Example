package com.pzone.ashva.probs.client.model;

import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.annotation.JsonInclude.Include;

/**
 * @author Ashva Probs Team
 *
 */
@JsonInclude(value = Include.NON_NULL)
public class Prob {

  private Double  eap;
  private Double  lap;
  private String  selId;
  private Integer officialFinish;

  public Double getEap() {
    return eap;
  }

  public void setEap(Double eap) {
    this.eap = eap;
  }

  public Double getLap() {
    return lap;
  }

  public void setLap(Double lap) {
    this.lap = lap;
  }

  public String getSelId() {
    return selId;
  }

  public void setSelId(String selId) {
    this.selId = selId;
  }

  public Integer getOfficialFinish() {
    return officialFinish;
  }

  public void setOfficialFinish(Integer officialFinish) {
    this.officialFinish = officialFinish;
  }

}
