package org.apache.servicecomb.bizkeeper.event;

import com.netflix.hystrix.HystrixCommandGroupKey;

public class test implements HystrixCommandGroupKey{

  private String name;
  
  @Override
  public String name() {
    return name;
  }

  public test(String name) {
    this.name = name;
  }
  public HystrixCommandGroupKey asKey(String name) {
    return null ;
 }
}
