import scala.actors.Actor

class Reduce1() extends Actor {
  override def act(): Unit = {
    while(true){
      receive {
        case msg: Map[String, Int] => {

        }
      }
    }
  }
}