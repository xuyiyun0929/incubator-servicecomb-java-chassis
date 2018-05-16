package org.apache.servicecomb.bizkeeper;

import java.util.concurrent.atomic.AtomicBoolean;

import org.apache.servicecomb.foundation.common.concurrent.ConcurrentHashMapEx;

import com.google.common.eventbus.EventBus;


public class test {
	public static void main(String[] args) {
		ConcurrentHashMapEx<String, AtomicBoolean> circuitFlag = new ConcurrentHashMapEx<>();
		te(circuitFlag,  "a");
		te(circuitFlag,  "b");
		EventBus a1 = new EventBus();
		EventBus a2 = new EventBus();
			System.out.println(a1==a2);
	}

	private static void te(ConcurrentHashMapEx<String, AtomicBoolean> circuitFlag, String keyname) {
		AtomicBoolean flag = circuitFlag.computeIfAbsent(keyname, k -> new AtomicBoolean());
		if(flag.compareAndSet(false, true)) {
			System.out.println("熔断开启"+keyname);
		}
		if(flag.compareAndSet(false, true)) {
			System.out.println("熔断开启"+keyname);
		}
		if (keyname=="a")
		if(flag.compareAndSet(true, false)) {
			circuitFlag.put(keyname, flag);
			System.out.println("熔断关闭"+keyname);
		}
	}
}
