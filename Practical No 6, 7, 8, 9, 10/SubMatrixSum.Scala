import breeze.linalg._

object SubMatrixSum {

  def main(args: Array[String]): Unit = {

    // Create a 4x4 matrix
    val matrix = DenseMatrix(
      (1, 2, 3, 4),
      (5, 6, 7, 8),
      (9, 10, 11, 12),
      (13, 14, 15, 16)
    )

    println("Original Matrix:")
    println(matrix)

    // Extract a 2x2 sub-matrix (rows 1 to 2, columns 1 to 2)
    val subMatrix = matrix(1 to 2, 1 to 2)

    println("\nSub-Matrix:")
    println(subMatrix)

    // Calculate row sums
    val rowSums = sum(subMatrix(*, ::))

    // Calculate column sums
    val colSums = sum(subMatrix(::, *))

    println("\nRow Sums:")
    println(rowSums)

    println("\nColumn Sums:")
    println(colSums)
  }
}
