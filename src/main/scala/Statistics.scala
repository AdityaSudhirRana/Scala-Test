object Statistics {

  def mean(values: Seq[Double]): Double = {
    if (values.isEmpty) 0.0
    else values.sum / values.size
  }

  def median(values: Seq[Double]): Double = {
    if (values.isEmpty) 0.0
    else {
      val sorted = values.sorted
      val n = sorted.size

      if (n % 2 == 0)
        (sorted(n / 2 - 1) + sorted(n / 2)) / 2.0
      else
        sorted(n / 2)
    }
  }

  def printStatistics(students: List[Student]): Unit = {

    val averages = students.map(_.averageMarks)

    println("\n========== OVERALL STATISTICS ==========")

    println(f"Mean Average Marks   : ${mean(averages)}%.2f")
    println(f"Median Average Marks : ${median(averages)}%.2f")
    println(f"Minimum Average Marks: ${averages.min}%.2f")
    println(f"Maximum Average Marks: ${averages.max}%.2f")

    println("\n========== SUBJECT-WISE AVERAGES ==========")

    println(f"TOC    : ${mean(students.map(_.toc))}%.2f")
    println(f"DS     : ${mean(students.map(_.ds))}%.2f")
    println(f"Python : ${mean(students.map(_.python))}%.2f")
    println(f"Scala  : ${mean(students.map(_.scala))}%.2f")
    println(f"OS     : ${mean(students.map(_.os))}%.2f")

    println("\n========== OTHER STATISTICS ==========")

    println(
      f"Mean Attendance  : ${mean(students.map(_.attendance))}%.2f%%"
    )

    println(
      f"Mean Study Hours : ${mean(students.map(_.studyHours))}%.2f"
    )
  }

  def printFrequencyDistribution(
                                  students: List[Student]
                                ): Unit = {

    val distribution = students.groupBy { student =>
      student.averageMarks match {

        case mark if mark >= 90 =>
          "Excellent (90-100)"

        case mark if mark >= 80 =>
          "Very Good (80-89)"

        case mark if mark >= 70 =>
          "Good (70-79)"

        case mark if mark >= 60 =>
          "Average (60-69)"

        case _ =>
          "Needs Improvement (<60)"
      }
    }

    println("\n========== FREQUENCY DISTRIBUTION ==========")

    val order = List(
      "Excellent (90-100)",
      "Very Good (80-89)",
      "Good (70-79)",
      "Average (60-69)",
      "Needs Improvement (<60)"
    )

    order.foreach { category =>
      println(
        f"$category%-25s : ${distribution.getOrElse(category, List.empty).size}%d students"
      )
    }
  }
}