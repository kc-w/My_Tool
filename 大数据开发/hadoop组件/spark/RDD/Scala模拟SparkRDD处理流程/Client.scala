import java.io.File

class Client() {
  def sendFile(master: Master, file: String): Unit = {
    val task =new File(file)
    master ! task
  }
}
