case class Student(
                    studentId: Int,
                    name: String,
                    gender: String,
                    studyHours: Double,
                    attendance: Double,
                    toc: Double,
                    ds: Double,
                    python: Double,
                    scala: Double,
                    os: Double
                  ) {

  def totalMarks: Double =
    toc + ds + python + scala + os

  def averageMarks: Double =
    totalMarks / 5.0
}