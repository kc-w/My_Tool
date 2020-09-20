import scala.actors.Actor

class Node(master:Master) extends Actor {
  val map = new Map1()
  val reduce = new Reduce1()
  map.start()
  reduce.start()
  master ! this
  override def act(): Unit = {
    while(true){
      receive {
        case msg: String => {
          map!msg
        }
      }
    }
  }
}