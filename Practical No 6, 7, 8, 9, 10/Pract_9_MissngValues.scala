import com.github.tototoshi.csv._
import java.io.File
import scala.util.Try

object Pract_9_MissingValues {

  def main(args: Array[String]): Unit = {

    // Read the CSV file
    val inputFile = new File("diabetes.csv")
    val reader = CSVReader.open(inputFile)
    val allRows = reader.allWithHeaders()
    reader.close()

    // Numeric columns to check for missing values
    val numericColumns = Seq(
      "Glucose",
      "BloodPressure",
      "SkinThickness",
      "Insulin",
      "BMI",
      "Age"
    )

    // Step 1: Calculate mean and count missing values
    val stats: Map[String, (Double, Int)] = numericColumns.map { col =>

      val values = allRows.map(row => row.getOrElse(col, "").trim)

      val validNumbers = values.flatMap { value =>
        Try(value.toDouble).toOption
      }

      val missingCount = values.count(value => Try(value.toDouble).isFailure)

      val mean =
        if (validNumbers.nonEmpty)
          validNumbers.sum / validNumbers.size
        else
          0.0

      (col, (mean, missingCount))

    }.toMap

    // Step 2: Print report
    println("\n----- Missing Data Report -----")

    stats.foreach { case (col, (mean, missingCount)) =>
      println(f"Column: $col%-15s Missing Values: $missingCount%-5d Mean: $mean%.2f")
    }

    // Step 3: Replace missing values with mean
    val cleanedRows = allRows.map { row =>

      numericColumns.foldLeft(row) { (updatedRow, col) =>

        val value = updatedRow.getOrElse(col, "").trim

        val newValue =
          Try(value.toDouble).toOption match {
            case Some(_) => value
            case None    => f"${stats(col)._1}%.2f"
          }

        updatedRow.updated(col, newValue)
      }
    }

    // Step 4: Save cleaned CSV
    val outputFile = new File("diabetes_cleaned.csv")

    val writer = CSVWriter.open(outputFile)

    val headers = cleanedRows.head.keys.toSeq

    writer.writeRow(headers)

    cleanedRows.foreach { row =>
      writer.writeRow(headers.map(header => row(header)))
    }

    writer.close()

    println("\nMissing values replaced successfully!")
    println("Cleaned file saved as: diabetes_cleaned.csv")
  }
}
