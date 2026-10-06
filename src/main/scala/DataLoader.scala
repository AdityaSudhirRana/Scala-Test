import com.github.tototoshi.csv._
import java.io.File

object DataLoader {

  def loadStudents(filePath: String): List[Student] = {

    val reader = CSVReader.open(new File(filePath))

    try {
      reader.allWithHeaders().map { row =>

        Student(
          studentId = row("StudentID").toInt,
          name = row("Name"),
          gender = row("Gender"),
          studyHours = row("StudyHours").toDouble,
          attendance = row("Attendance").toDouble,
          toc = row("TOC").toDouble,
          ds = row("DS").toDouble,
          python = row("Python").toDouble,
          scala = row("Scala").toDouble,
          os = row("OS").toDouble
        )
      }

    } finally {
      reader.close()
    }
  }
}