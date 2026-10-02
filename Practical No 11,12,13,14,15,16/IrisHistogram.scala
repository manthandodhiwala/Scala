import breeze.linalg.DenseVector
import breeze.plot.Figure
import breeze.plot.hist
import com.github.tototoshi.csv.CSVReader
import java.io.File

object IrisHistogram {

  def main(args: Array[String]): Unit = {

    // Read CSV file
    val reader = CSVReader.open(new File("iris.csv"))
    val data = reader.allWithHeaders()
    reader.close()

    // Extract sepal length
    val sepalLengths = DenseVector(
      data.map(row => row("sepal.length").toDouble).toArray
    )

    // Create figure
    val fig = Figure("Histogram of Sepal Length")

    // Different bin sizes
    val binSizes = List(5, 10, 20)

    // Create histograms
    for ((bins, index) <- binSizes.zipWithIndex) {

      val plt = fig.subplot(1, binSizes.length, index)

      plt += hist(sepalLengths, bins)

      plt.title = s"Histogram with $bins bins"
      plt.xlabel = "Sepal Length"
      plt.ylabel = "Frequency"
    }

    // Do not call fig.refresh()
    println("Histogram created successfully!")
  }
}

