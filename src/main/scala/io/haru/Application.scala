package io.haru

import akka.actor.{Actor, ActorLogging, ActorRef, ActorSystem, Props, SupervisorStrategy, Terminated}
import com.typesafe.config.ConfigFactory
import org.slf4j.LoggerFactory

object Application extends App {
  val logger = LoggerFactory.getLogger(Application.getClass)

  val configuration = ConfigFactory.load()
  val subApplACfg = configuration.getConfig("subApplA")
  val config = subApplACfg.withFallback(configuration)
  logger.info(config.getString("MyAppl.description"))


/*
  val system = ActorSystem("helloSystem")
  val helloActor = system.actorOf(HelloActor.props())
  val watchActor = system.actorOf(WatchActor.props(helloActor))*/
}


object WatchActor{
  def props(monitor: ActorRef): Props = Props(new WatchActor(monitor))
}
class WatchActor(monitor: ActorRef) extends Actor with ActorLogging {
  context.watch(monitor)
  override def receive: Receive = {
    case Terminated(actorRef) =>
      log.warning("Actor {} terminated", actorRef)
  }

}

object HelloActor {
  def props(): Props = Props(new HelloActor())
}

class HelloActor extends Actor with ActorLogging {

  override def preStart(): Unit = {
    log.info("preStart")
  }

  override def receive: Receive = {
    case "restart" =>
      throw new IllegalStateException("force restart")
    case "stop" =>
      log.info("stop")
      context.stop(self)
    case msg: AnyRef => {
      log.info(msg.toString)
      sender() ! msg
    }
  }

  override def postStop(): Unit = {
    log.info("postStop!!!")
  }

  override def preRestart(reason: Throwable, message: Option[Any]): Unit = {
    log.info("preRestart : {}", reason.getMessage)
    super.preRestart(reason, message)
  }

  override def postRestart(reason: Throwable): Unit = {
    log.info("postRestart : {}", reason.getMessage)
    super.postRestart(reason)
  }
}
