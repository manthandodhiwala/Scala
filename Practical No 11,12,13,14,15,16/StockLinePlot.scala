import breeze.linalg.DenseVector
import breeze.plot.Figure
import breeze.plot.plot
import com.github.tototoshi.csv.CSVReader
import com.github.tototoshi.csv.defaultCSVFormat

import java.io.File
import java.time.LocalDate
import java.time.format.DateTimeFormatter

object StockLinePlot {

  def main(args: Array[String]): Unit = {

    // Open CSV file
    val reader = CSVReader.open(new File("AUBANK.NS.csv"))

    // Read CSV data
    val data = reader.allWithHeaders()

    // Close CSV file
    reader.close()

    // Date format
    val dateFormatter = DateTimeFormatter.ofPattern("yyyy-MM-dd")

    // Parse Date and Close Price
    val parsedData = data.flatMap { row =>
      try {
        val date = LocalDate.parse(row("Date"), dateFormatter)
        val closePrice = row("Close").toDouble

        Some((date, closePrice))
      } catch {
        case _: Exception => None
      }
    }.sortBy(_._1)

    // Check for valid data
    if (parsedData.isEmpty) {

      println("No valid stock data found!")

    } else {

      // X-axis: time progression
      val x = DenseVector(
        (0 until parsedData.length)
          .map(_.toDouble)
          .toArray
      )

      // Y-axis: closing price
      val y = DenseVector(
        parsedData.map(_._2).toArray
      )

      // Create figure
      val fig = Figure("AUBANK.NS - Close Price Trend")

      // Create plot
      val plt = fig.subplot(0)

      plt += plot(
        x,
        y,
        name = "Close Price",
        colorcode = "blue"
      )

      // Labels
      plt.xlabel = "Time (Days)"
      plt.ylabel = "Close Price"

      // Title
      plt.title = "AUBANK.NS Closing Price Over Time"

      println("Stock line plot created successfully!")
    }
  }
}
