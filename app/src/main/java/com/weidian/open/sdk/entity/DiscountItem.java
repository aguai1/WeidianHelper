package com.weidian.open.sdk.entity;

import com.fasterxml.jackson.annotation.JsonProperty;

public class DiscountItem {
  private String itemid;
//缩略图
  private String img;
  private int sold;
  private int  collect;
  private String price;

  private String src_img;
  private String discount;
/**
 * 订单状态，1：进行中 ，0：已结束， -1：未开始*/
  private String status;
  private String item_desc;
  private String  end_time;
  private String  price_kill;

  private String  start_time;
  private String stock;
  private boolean selected;

  public String getImg() {
    return img;
  }

  public void setImg(String img) {
    this.img = img;
  }

  public int getSold() {
    return sold;
  }

  public void setSold(int sold) {
    this.sold = sold;
  }

  public int getCollect() {
    return collect;
  }

  public void setCollect(int collect) {
    this.collect = collect;
  }

  public String getPrice() {
    return price;
  }

  public String getItemid() {
    return itemid;
  }

  public void setItemid(String itemid) {
    this.itemid = itemid;
  }

  public void setPrice(String price) {
    this.price = price;
  }

  public String getSrc_img() {
    return src_img;
  }

  public void setSrc_img(String src_img) {
    this.src_img = src_img;
  }

  public String getDiscount() {
    return discount;
  }

  public void setDiscount(String discount) {
    this.discount = discount;
  }

  public String getStatus() {
    return status;
  }

  public void setStatus(String status) {
    this.status = status;
  }

  public String getItem_desc() {
    return item_desc;
  }

  public void setItem_desc(String item_desc) {
    this.item_desc = item_desc;
  }

  public String getEnd_time() {
    return end_time;
  }

  public void setEnd_time(String end_time) {
    this.end_time = end_time;
  }

  public String getPrice_kill() {
    return price_kill;
  }

  public void setPrice_kill(String price_kill) {
    this.price_kill = price_kill;
  }

  public String getStart_time() {
    return start_time;
  }

  public void setStart_time(String start_time) {
    this.start_time = start_time;
  }

  public String getStock() {
    return stock;
  }

  public void setStock(String stock) {
    this.stock = stock;
  }

  public boolean isSelected() {
    return selected;
  }

  public void setSelected(boolean selected) {
    this.selected = selected;
  }
}
