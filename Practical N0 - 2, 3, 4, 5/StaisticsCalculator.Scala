object StatisticsCalculator {

  def main(args: Array[String]): Unit = {

    // List of numbers
    val numbers = List(10, 20, 30, 40, 50, 20, 30, 20)

    println("Numbers: " + numbers)

    // Mean
    val mean = numbers.sum.toDouble / numbers.length

    // Median
    val sorted = numbers.sorted
    val n = sorted.length

    val median =
      if (n % 2 == 0)
        (sorted(n / 2 - 1) + sorted(n / 2)) / 2.0
      else
        sorted(n / 2)

    // Mode
    val frequency = numbers.groupBy(identity).view.mapValues(_.size).toMap
    val maxFrequency = frequency.values.max
    val mode = frequency.filter(_._2 == maxFrequency).keys

    // Display Results
    println("Mean = " + mean)
    println("Median = " + median)
    println("Mode = " + mode.mkString(", "))
  }
}
