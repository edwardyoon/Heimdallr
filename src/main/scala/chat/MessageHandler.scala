/*
 * Licensed to the Apache Software Foundation (ASF) under one or more
 * contributor license agreements.  See the NOTICE file distributed with
 * this work for additional information regarding copyright ownership.
 * The ASF licenses this file to You under the Apache License, Version 2.0
 * (the "License"); you may not use this file except in compliance with
 * the License.  You may obtain a copy of the License at
 *
 *    http://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 */
package chat

import akka.actor.ActorRef
import akka.actor.ActorSystem
import com.typesafe.config.ConfigFactory

/**
  * This object manages message processing.
  * Define the required protocol and use it.
  */
object MessageHandler {

  implicit val system = ActorSystem("heimdallr", ConfigFactory.load())
  val clazz = Class.forName(system.settings.config.getString("akka.message.protocol")).newInstance().asInstanceOf[Protocol]

  def processing(roomUsers: Set[ActorRef], msg: String) = clazz.protocol(roomUsers, msg)
}
