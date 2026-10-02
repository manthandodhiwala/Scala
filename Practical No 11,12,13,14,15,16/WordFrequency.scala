import scala.io.Source

object WordFrequency {

  def main(args: Array[String]): Unit = {

    // Correct path of the text file
    val fileName =
      "C:/Users/Dipen Kumar/IdeaProjects/Practical No. 11/sample.txt"

    val source = Source.fromFile(fileName)

    val words = source.mkString
      .toLowerCase
      .split("[^a-zA-Z]+")
      .filter(_.nonEmpty)

    val wordFrequency = words
      .groupBy(word => word)
      .map { case (word, list) => (word, list.length) }

    println("Word Frequency:")

    wordFrequency.foreach {
      case (word, count) =>
        println(word + " -> " + count)
    }

    source.close()
  }
}
