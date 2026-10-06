import scala.io.StdIn

object Main {

  private val dataPath = "data/students.csv"

  def main(args: Array[String]): Unit = {

    val students =
      try {
        DataLoader.loadStudents(dataPath)
      } catch {
        case exception: Exception =>
          println(
            s"Could not load dataset: ${exception.getMessage}"
          )
          return
      }

    println("\n==============================================")
    println("       STUDENT PERFORMANCE ANALYZER")
    println("==============================================")

    println(
      s"Dataset loaded successfully: ${students.size} students"
    )

    var running = true

    while (running) {

      printMenu()

      val choice =
        StdIn.readLine("Enter your choice: ")

      choice match {

        case "1" =>
          displayStudents(students)

        case "2" =>
          Statistics.printStatistics(students)

        case "3" =>
          displayTopStudents(students)

        case "4" =>
          Statistics.printFrequencyDistribution(students)

        case "5" =>
          analyzeStudyHours(students)

        case "6" =>
          analyzeAttendance(students)

        case "7" =>
          Regression.printRegression(students)

        case "8" =>
          try {
            Plotter.createScatterPlot(
              students,
              "data/study_hours_vs_marks.png"
            )
          } catch {
            case exception: Exception =>
              println(
                s"Could not create plot: ${exception.getMessage}"
              )
          }

        case "9" =>
          println(
            "\nThank you for using Student Performance Analyzer."
          )

          running = false

        case _ =>
          println(
            "\nInvalid choice. Please enter a number from 1 to 9."
          )
      }
    }
  }

  private def printMenu(): Unit = {

    println(
      """
        |
        |--------------- MAIN MENU ----------------
        |1. Display Student Dataset
        |2. Calculate Statistics
        |3. Find Top 5 Students
        |4. Generate Frequency Distribution
        |5. Analyze Study Hours
        |6. Analyze Attendance
        |7. Perform Linear Regression
        |8. Generate Scatter Plot
        |9. Exit
        |------------------------------------------
        |""".stripMargin
    )
  }

  private def displayStudents(
                               students: List[Student]
                             ): Unit = {

    println(
      "\n================ STUDENT DATASET ================"
    )

    println(
      f"${"ID"}%-4s " +
        f"${"Name"}%-18s " +
        f"${"Gender"}%-8s " +
        f"${"Study"}%-7s " +
        f"${"Attend."}%-8s " +
        f"${"TOC"}%-6s " +
        f"${"DS"}%-6s " +
        f"${"Python"}%-7s " +
        f"${"Scala"}%-7s " +
        f"${"OS"}%-6s " +
        f"${"Average"}%-8s"
    )

    println("-" * 110)

    students.foreach { student =>

      println(
        f"${student.studentId}%-4d " +
          f"${student.name}%-18s " +
          f"${student.gender}%-8s " +
          f"${student.studyHours}%-7.1f " +
          f"${student.attendance}%-8.1f " +
          f"${student.toc}%-6.1f " +
          f"${student.ds}%-6.1f " +
          f"${student.python}%-7.1f " +
          f"${student.scala}%-7.1f " +
          f"${student.os}%-6.1f " +
          f"${student.averageMarks}%-8.2f"
      )
    }
  }

  private def displayTopStudents(
                                  students: List[Student]
                                ): Unit = {

    val topStudents =
      students
        .sortBy(student => -student.averageMarks)
        .take(5)

    println(
      "\n================ TOP 5 STUDENTS ================"
    )

    topStudents.zipWithIndex.foreach {

      case (student, index) =>

        println(
          f"${index + 1}%-4s " +
            f"${student.name}%-20s " +
            f"Average: ${student.averageMarks}%.2f"
        )
    }
  }

  private def analyzeStudyHours(
                                 students: List[Student]
                               ): Unit = {

    val averageStudyHours =
      Statistics.mean(
        students.map(_.studyHours)
      )

    val highStudyStudents =
      students.filter(
        _.studyHours >= averageStudyHours
      )

    println(
      "\n================ STUDY HOURS ANALYSIS ================"
    )

    println(
      f"Average study hours: $averageStudyHours%.2f"
    )

    println(
      s"Students studying at or above the average: ${highStudyStudents.size}"
    )

    highStudyStudents.foreach { student =>

      println(
        f"${student.name}%-20s ${student.studyHours}%.1f hours"
      )
    }
  }

  private def analyzeAttendance(
                                 students: List[Student]
                               ): Unit = {

    val averageAttendance =
      Statistics.mean(
        students.map(_.attendance)
      )

    val lowAttendance =
      students.filter(
        _.attendance < 75
      )

    println(
      "\n================ ATTENDANCE ANALYSIS ================"
    )

    println(
      f"Average attendance: $averageAttendance%.2f%%"
    )

    println(
      s"Students below 75%% attendance: ${lowAttendance.size}"
    )

    if (lowAttendance.isEmpty) {

      println(
        "No student has attendance below 75%."
      )

    } else {

      lowAttendance.foreach { student =>

        println(
          f"${student.name}%-20s ${student.attendance}%.1f%%"
        )
      }
    }
  }
}