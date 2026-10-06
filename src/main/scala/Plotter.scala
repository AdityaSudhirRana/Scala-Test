import org.knowm.xchart.{BitmapEncoder, XYChartBuilder}
import org.knowm.xchart.XYSeries.XYSeriesRenderStyle

object Plotter {

  def createScatterPlot(
                         students: List[Student],
                         outputPath: String
                       ): Unit = {

    val x =
      students.map(_.studyHours).toArray

    val y =
      students.map(_.averageMarks).toArray

    val chart =
      new XYChartBuilder()
        .width(900)
        .height(600)
        .title("Study Hours vs Average Marks")
        .xAxisTitle("Study Hours")
        .yAxisTitle("Average Marks")
        .build()

    // Set the chart to scatter-plot mode
    chart.getStyler
      .setDefaultSeriesRenderStyle(
        XYSeriesRenderStyle.Scatter
      )

    chart.getStyler.setLegendVisible(false)

    chart.addSeries(
      "Students",
      x,
      y
    )

    BitmapEncoder.saveBitmapWithDPI(
      chart,
      outputPath,
      BitmapEncoder.BitmapFormat.PNG,
      150
    )

    println(
      s"\nScatter plot created successfully: $outputPath"
    )
  }
}