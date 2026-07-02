import breeze.linalg._

object MatrixElementWiseOperations {

  def main(args: Array[String]): Unit = {

    // Create two 3x3 matrices
    val matrix1 = DenseMatrix(
      (2.0, 4.0, 6.0),
      (8.0, 10.0, 12.0),
      (14.0, 16.0, 18.0)
    )

    val matrix2 = DenseMatrix(
      (1.0, 2.0, 3.0),
      (4.0, 5.0, 6.0),
      (7.0, 8.0, 9.0)
    )

    println("Matrix 1:")
    println(matrix1)

    println("\nMatrix 2:")
    println(matrix2)

    // Element-wise Addition
    val addition = matrix1 + matrix2
    println("\nElement-wise Addition:")
    println(addition)

    // Element-wise Subtraction
    val subtraction = matrix1 - matrix2
    println("\nElement-wise Subtraction:")
    println(subtraction)

    // Element-wise Multiplication
    val multiplication = matrix1 *:* matrix2
    println("\nElement-wise Multiplication:")
    println(multiplication)

    // Element-wise Division
    val division = matrix1 /:/ matrix2
    println("\nElement-wise Division:")
    println(division)
  }
}