package org.apache.servicecomb.bizkeeper.event;

import com.netflix.hystrix.HystrixCommandGroupKey;
import com.netflix.hystrix.HystrixKey;
import com.netflix.hystrix.util.InternMap;

public class test extends HystrixKey.HystrixKeyDefault implements HystrixCommandGroupKey {

  public test(String name) {
    super(name);
  }

  private String name;
  
  private String m;
  
  private static final InternMap<String, test> intern
  = new InternMap<String, test>(
  new InternMap.ValueConstructor<String, test>() {
      @Override
      public test create(String key) {
          return new test(key);
      }
  });
  
  @Override
  public String name() {
    return name;
  }

  public static HystrixCommandGroupKey asKey(String name,String m) {
    return intern.interned(name);
 }
  
  public int Key(String name) {
    return 3 ;
 }

  public String getM() {
    return m;
  }

  public void setM(String m) {
    this.m = m;
  }
}
