import breeze.linalg._
import breeze.plot._
import com.github.tototoshi.csv._
import java.io.File

object IrisScatterPlot {

  def main(args: Array[String]): Unit = {

    // Read CSV file
    val reader = CSVReader.open(new File("iris.csv"))
    val data = reader.allWithHeaders()
    reader.close()

    println("CSV loaded successfully!")
    println("Columns found: " + data.head.keys.mkString(", "))

    // Extract data for each variety
    val setosa = data.filter(_("variety") == "Setosa")
    val versicolor = data.filter(_("variety") == "Versicolor")
    val virginica = data.filter(_("variety") == "Virginica")

    // Extract X and Y values
    def extractXY(rows: List[Map[String, String]]) = {

      val x = DenseVector(
        rows.map(_("petal.length").toDouble).toArray
      )

      val y = DenseVector(
        rows.map(_("petal.width").toDouble).toArray
      )

      (x, y)
    }

    val (xSetosa, ySetosa) = extractXY(setosa)
    val (xVersicolor, yVersicolor) = extractXY(versicolor)
    val (xVirginica, yVirginica) = extractXY(virginica)

    // Create scatter plot
    val fig = Figure()
    val plt = fig.subplot(0)

    plt.title = "Petal Length vs Petal Width"
    plt.xlabel = "Petal Length"
    plt.ylabel = "Petal Width"

    // Setosa
    plt += plot(
      xSetosa,
      ySetosa,
      '.',
      name = "Setosa",
      colorcode = "blue"
    )

    // Versicolor
    plt += plot(
      xVersicolor,
      yVersicolor,
      '.',
      name = "Versicolor",
      colorcode = "green"
    )

    // Virginica
    plt += plot(
      xVirginica,
      yVirginica,
      '.',
      name = "Virginica",
      colorcode = "red"
    )

    fig.refresh()

    println("Scatter plot created successfully!")
  }
}