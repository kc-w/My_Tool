import scala.actors.Actor

class Map1 extends Actor() {

  override def act(): Unit = {
    while(true){
      receive {
        case msg: Array[String] => {
        }
        case msg: String => {
          println(msg)
        }
      }
    }
  }
}