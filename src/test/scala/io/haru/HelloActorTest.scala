package io.haru

import akka.actor.{ActorSystem, Kill, PoisonPill}
import akka.testkit.TestKit
import org.scalatest.{MustMatchers, WordSpecLike}

class HelloActorTest extends TestKit(ActorSystem("testsystem"))
  with WordSpecLike
  with MustMatchers
  with StopSystemAfterAll {

  "hello Actor Restart" must {
    "hello actor restart message send" in {
      val testActorRef = system.actorOf(HelloActor.props(), "LifeCycleHooks")
      system.actorOf(WatchActor.props(testActorRef), "WatchHooks")
     // testActorRef ! "restart"

      testActorRef ! "test"
      testActorRef ! "test1"
      testActorRef ! "test2"
      testActorRef ! "restart"
      testActorRef.tell("msg", testActor)
      expectMsg("msg")
      testActorRef.tell("msg", testActor)
      expectMsg("msg")
    }
  }
}
