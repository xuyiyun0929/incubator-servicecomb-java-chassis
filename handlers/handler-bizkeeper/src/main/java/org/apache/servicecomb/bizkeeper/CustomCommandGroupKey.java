package org.apache.servicecomb.bizkeeper;

import org.apache.servicecomb.core.Invocation;

import com.netflix.hystrix.HystrixCommandGroupKey;
import com.netflix.hystrix.HystrixKey;
import com.netflix.hystrix.util.InternMap;

public class CustomCommandGroupKey extends HystrixKey.HystrixKeyDefault implements HystrixCommandGroupKey {

  private String invocationType;

  private String microserviceName;

  private String schema;

  private String operation;

  public CustomCommandGroupKey(Invocation invocation) {
    super(invocation.getInvocationType().name() + "." + invocation.getOperationMeta().getMicroserviceQualifiedName());
    this.microserviceName = invocation.getMicroserviceName();
    this.invocationType = invocation.getInvocationType().name();
    this.schema = invocation.getSchemaId();
    this.operation = invocation.getOperationName();
  }

  private static final InternMap<Invocation, CustomCommandGroupKey> intern =
      new InternMap<Invocation, CustomCommandGroupKey>(
          new InternMap.ValueConstructor<Invocation, CustomCommandGroupKey>() {
            @Override
            public CustomCommandGroupKey create(Invocation invocation) {
              return new CustomCommandGroupKey(invocation);
            }
          });


  public static HystrixCommandGroupKey asKey(Invocation invocation) {
    return intern.interned(invocation);
  }
  
  public String getInvocationType() {
    return invocationType;
  }

  public String getMicroserviceName() {
    return microserviceName;
  }

  public String getOperation() {
    return operation;
  }

  public String getSchema() {
    return schema;
  }
}
