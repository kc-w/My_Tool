import java.io.File

import scala.actors.Actor
import scala.collection.mutable.ArrayBuffer
import scala.io.{BufferedSource, Source}

class Master extends Actor() {
  val Nodes = scala.collection.mutable.ArrayBuffer[Node]()

  override def act(): Unit = {
    while(true){
      receive {
        case msg: File => {
          var fileInfo: BufferedSource = Source.fromFile(msg)
          var data = new Array[ArrayBuffer[String]](Nodes.size)
          var num = 0
          for (line: String <- fileInfo.getLines()) {
            num += 1
            println(line)
            var nodeId: Int = num % Nodes.size
            println("nodeId:"+nodeId)
            println(data(1))
            //data(1)+="123"
          }
        }
        case msg: Node => {
          Nodes += msg
          println("datanode个数为："+Nodes.size)
        }
      }
    }
  }
}