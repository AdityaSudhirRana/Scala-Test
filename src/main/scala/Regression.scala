import breeze.linalg.DenseVector
import breeze.stats.mean

object Regression {

  case class Result(
                     slope: Double,
                     intercept: Double,
                     rSquared: Double
                   )

  def fit(xValues: Seq[Double], yValues: Seq[Double]): Result = {

    require(
      xValues.nonEmpty && xValues.size == yValues.size,
      "X and Y must contain the same non-zero number of values."
    )

    val x = DenseVector(xValues.toArray)
    val y = DenseVector(yValues.toArray)

    val xMean = mean(x)
    val yMean = mean(y)

    val centeredX = x.map(value => value - xMean)
    val centeredY = y.map(value => value - yMean)

    val slope =
      (centeredX dot centeredY) /
        (centeredX dot centeredX)

    val intercept =
      yMean - slope * xMean

    val predictions =
      x.map(value => slope * value + intercept)

    val residuals =
      y - predictions

    val ssRes =
      residuals.toArray.map(value => value * value).sum

    val ssTot =
      centeredY.toArray.map(value => value * value).sum

    val rSquared =
      if (ssTot == 0.0)
        1.0
      else
        1.0 - ssRes / ssTot

    Result(
      slope,
      intercept,
      rSquared
    )
  }

  def predict(
               result: Result,
               x: Double
             ): Double = {
    result.slope * x + result.intercept
  }

  def printRegression(
                       students: List[Student]
                     ): Result = {

    val x =
      students.map(_.studyHours)

    val y =
      students.map(_.averageMarks)

    val result =
      fit(x, y)

    println("\n===== LINEAR REGRESSION =====")
    println("Independent Variable : Study Hours")
    println("Dependent Variable   : Average Marks")

    println(
      f"Slope                : ${result.slope}%.4f"
    )

    println(
      f"Intercept            : ${result.intercept}%.4f"
    )

    println(
      f"R-squared            : ${result.rSquared}%.4f"
    )

    println(
      f"Equation             : Marks = " +
        f"${result.slope}%.2f * StudyHours + " +
        f"${result.intercept}%.2f"
    )

    result
  }
}