package org.apache.servicecomb.bizkeeper.event;

import java.util.concurrent.ConcurrentHashMap;

import org.apache.servicecomb.foundation.common.event.EventManager;
import org.apache.servicecomb.foundation.common.event.AlarmEvent.Type;

import com.netflix.hystrix.HystrixCommandKey;
import com.netflix.hystrix.HystrixEventType;
import com.netflix.hystrix.strategy.eventnotifier.HystrixEventNotifier;

public class CircutBreakerEventNotifier extends HystrixEventNotifier {

  /**
   * 使用circuitMarker来记录被熔断的接口
   */
  private static ConcurrentHashMap<String, Boolean> circuitMarker = new ConcurrentHashMap<>();

  @Override
  public void markEvent(HystrixEventType eventType, HystrixCommandKey key) {
    String keyName = key.name();
    switch (eventType) {
      case SHORT_CIRCUITED: {
        if (circuitMarker.get(keyName) == null) {
          EventManager.post(new CircutBreakerEvent(key, Type.OPEN));
          circuitMarker.put(keyName, true);
        }
      }
      case EXCEPTION_THROWN: {
        if (circuitMarker.get(keyName) != null) {
          EventManager.post(new CircutBreakerEvent(key, Type.CLOSE));
          circuitMarker.remove(keyName);
        }
      }
      default:
        break;
    }
  }



}
