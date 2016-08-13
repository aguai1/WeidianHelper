package com.weidian.open.sdk.entity;

import com.fasterxml.jackson.annotation.JsonProperty;

public class Cate {

  @JsonProperty("sort_num")
  private int sortNum;

  @JsonProperty("cate_id")
  private String cateId;

  @JsonProperty("cate_name")
  private String cateName;

  public int getSortNum() {
    return sortNum;
  }

  public void setSortNum(int sortNum) {
    this.sortNum = sortNum;
  }

  public String getCateId() {
    return cateId;
  }

  public void setCateId(String cateId) {
    this.cateId = cateId;
  }

  public String getCateName() {
    return cateName;
  }

  public void setCateName(String cateName) {
    this.cateName = cateName;
  }

}
