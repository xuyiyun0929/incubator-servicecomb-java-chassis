/*
 * Licensed to the Apache Software Foundation (ASF) under one or more
 * contributor license agreements.  See the NOTICE file distributed with
 * this work for additional information regarding copyright ownership.
 * The ASF licenses this file to You under the Apache License, Version 2.0
 * (the "License"); you may not use this file except in compliance with
 * the License.  You may obtain a copy of the License at
 *
 *     http://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 */

package org.apache.servicecomb.samples.springmvc.provider;


import javax.ws.rs.core.MediaType;

import org.apache.servicecomb.provider.rest.common.RestSchema;
import org.apache.servicecomb.samples.common.schema.Hello;
import org.apache.servicecomb.samples.common.schema.models.Person;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;

@RestSchema(schemaId = "springmvcHello")
@RequestMapping(path = "/springmvchello", produces = MediaType.APPLICATION_JSON)
public class SpringmvcHelloImpl implements Hello {
  public int a1 = 0;
  public int a2 = 0;
  @Override
  @RequestMapping(path = "/sayhi", method = RequestMethod.POST)
  public String sayHi(@RequestParam(name = "name") String name) throws InterruptedException {
    a1=a1+1;
    Thread.sleep(2000);
    return "Hello " + name;
  }

  @Override
  @RequestMapping(path = "/sayhello", method = RequestMethod.POST)
  public String sayHello(@RequestBody Person person) throws InterruptedException {
//    a2=a2+1;
//    if (a2<3) {
    Thread.sleep(2000);
//    }
    return "Hello person " + person.getName();
  }
}