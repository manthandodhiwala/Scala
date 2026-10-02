import scala.io.Source

object OneHotEncoding {

  def main(args: Array[String]): Unit = {

    // Read Sample.csv from resources folder
    val inputStream =
      getClass.getClassLoader.getResourceAsStream("Sample.csv")

    if (inputStream == null) {
      println("Error: Sample.csv file not found!")
      return
    }

    val source = Source.fromInputStream(inputStream)

    // Read lines and remove blank lines
    val lines = source.getLines()
      .map(_.trim)
      .filter(_.nonEmpty)
      .toList

    source.close()

    if (lines.isEmpty) {
      println("CSV file is empty!")
      return
    }

    // Read header
    val header = lines.head.split(",")

    // Find Gender column
    val genderIndex = header.indexOf("Gender")

    if (genderIndex == -1) {
      println("Gender column not found!")
      return
    }

    // Get valid data rows
    val dataRows = lines.tail
      .map(_.split(","))
      .filter(values => values.length > genderIndex)

    // Get unique Gender categories
    val categories = dataRows
      .map(values => values(genderIndex).trim)
      .distinct

    // Create new header
    val newHeader =
      header.zipWithIndex
        .filter { case (_, index) => index != genderIndex }
        .map(_._1) ++
        categories.map(category => s"Gender_$category")

    println("One-Hot Encoded Data:")
    println(newHeader.mkString(","))

    // Perform One-Hot Encoding
    dataRows.foreach { values =>

      // Keep all columns except Gender
      val otherValues = values.zipWithIndex
        .filter { case (_, index) => index != genderIndex }
        .map(_._1.trim)

      val gender = values(genderIndex).trim

      // Create one-hot values
      val oneHotValues = categories.map { category =>
        if (gender == category) "1"
        else "0"
      }

      // Print result
      println((otherValues ++ oneHotValues).mkString(","))
    }
  }
}