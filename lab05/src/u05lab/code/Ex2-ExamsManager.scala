package u05lab.code

object Kind extends Enumeration {
  type Kind = Value
  val RETIRED, FAILED, SUCCEEDED = Value
}

sealed trait ExamResult {

  def getKind: Kind.Kind

  def getEvaluation: Option[Integer]

  def cumLaude: Boolean
}

sealed trait ExamResultFactory {
  def failed: ExamResult

  def retired: ExamResult

  def succeededCumLaude: ExamResult

  def succeeded(evaluation: Int): ExamResult
}

object ExamResultFactory {
  def apply():ExamResultFactory = ExamResultFactoryImpl()

  private case class ExamResultFactoryImpl() extends ExamResultFactory {

    override def failed: ExamResult = new ExamResultImpl(Kind.FAILED, Option.empty, false)

    override def retired: ExamResult = new ExamResultImpl(Kind.RETIRED, Option.empty, false)

    override def succeededCumLaude: ExamResult = new ExamResultImpl(Kind.SUCCEEDED, Option(30), true){
      override def toString: String = super.toString + "(30L)"
    }

    override def succeeded(evaluation: Int): ExamResult = new ExamResultImpl(Kind.SUCCEEDED,
      if (evaluation > 18 && evaluation <= 30)
        Option(evaluation) else throw new IllegalArgumentException(), false) {
      override def toString: String = super.toString + "(" + evaluation + ")"
    }
  }

  private class ExamResultImpl(result: Kind.Kind, grade:Option[Integer], laude:Boolean) extends ExamResult{

    override def getKind:Kind.Kind = this.result

    override def getEvaluation: Option[Integer] = this.grade

    override def cumLaude: Boolean = this.laude

    override def toString: String = this.result.toString
  }
}

sealed trait ExamsManager {

  def createNewCall(call:String)

  def addStudentResult(call:String, student:String, result:ExamResult)

  def getAllStudentsFromCall(call:String):Set[String]

  def getEvaluationsMapFromCall(call:String):Map[String, Integer]

  def getResultsMapFromStudent(student:String):Map[String, String]

  def getBestResultFromStudent(student:String):Option[Integer]
}

object ExamsManager {
  def apply():ExamsManager = ExamsManagerImpl()

  private case class ExamsManagerImpl() extends ExamsManager {
    private var calls:Map[String, Map[String, ExamResult]] = Map()

    override def createNewCall(call: String): Unit = {
      if (this.calls.contains(call))
        throw new IllegalArgumentException()
      else
        this.calls += (call -> Map())
    }

    override def addStudentResult(call: String, student: String, result: ExamResult): Unit = {
      if (this.calls(call).contains(student))
        throw new IllegalArgumentException()
      else
        this.calls += call -> (this.calls(call) + (student -> result))
    }

    override def getAllStudentsFromCall(call: String): Set[String] = this.calls(call).keySet

    override def getEvaluationsMapFromCall(call: String): Map[String, Integer] =
      this.calls(call).filter(_._2.getEvaluation.isDefined).map(e => (e._1, e._2.getEvaluation.get))

    override def getResultsMapFromStudent(student: String): Map[String, String] =
      this.calls.filter(e => e._2.contains(student)).map(e => (e._1, e._2(student).toString))

    override def getBestResultFromStudent(student: String): Option[Integer] =
      this.calls.filter(e => e._2.contains(student)).map(e => e._2(student).getEvaluation).max
  }
}

object test {
  val t:ExamResultFactory = ExamResultFactory()
}


