object Test1 {
  def main(args: Array[String]): Unit = {
    val cliect = new Client()
    val master = new Master()
    master.start()
    val nodeOne = new Node(master)
    val nodeTwo = new Node(master)
    val nodeSec = new Node(master)
    nodeOne.start()
    nodeTwo.start()
    nodeSec.start()
    cliect.sendFile(master,"D:\\lol\\a.txt")
  }
}
