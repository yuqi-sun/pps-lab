package u05lab

import u05lab.code._
import org.junit.jupiter.api.Test
import org.junit.jupiter.api.Assertions._

class ExamsManagerTest {
    private val erf: ExamResultFactory = ExamResultFactory()
    private val em:ExamsManager = ExamsManager()

    @Test def testExamResultsBasicBehaviour(): Unit = {
      assertEquals(erf.failed.getKind, Kind.FAILED)
      assertFalse(erf.failed.getEvaluation.isDefined)
      assertFalse(erf.failed.cumLaude)
      assertEquals(erf.failed.toString, "FAILED")

      assertEquals(erf.retired.getKind, Kind.RETIRED)
      assertFalse(erf.retired.getEvaluation.isDefined)
      assertFalse(erf.retired.cumLaude)
      assertEquals(erf.retired.toString, "RETIRED")

      assertEquals(erf.succeededCumLaude.getKind, Kind.SUCCEEDED)
      assertEquals(erf.succeededCumLaude.getEvaluation, Option(30))
      assertTrue(erf.succeededCumLaude.cumLaude)
      assertEquals(erf.succeededCumLaude.toString, "SUCCEEDED(30L)")

      assertEquals(erf.succeeded(28).getKind, Kind.SUCCEEDED)
      assertEquals(erf.succeeded(28).getEvaluation, Option(28))
      assertFalse(erf.succeeded(28).cumLaude)
      assertEquals(erf.succeeded(28).toString, "SUCCEEDED(28)")
    }

    @Test def optionalTestEvaluationCantBeGreaterThan30(): Unit = {
      assertThrows(classOf[IllegalArgumentException], () => erf.succeeded(32))
    }

    @Test def optionalTestEvaluationCantBeSmallerThan18(): Unit = {
      assertThrows(classOf[IllegalArgumentException], () => erf.succeeded(17))
    }

    private def prepareExams(): Unit = {
      em.createNewCall("gennaio")
      em.createNewCall("febbraio")
      em.createNewCall("marzo")

      em.addStudentResult("gennaio", "rossi", erf.failed)
      em.addStudentResult("gennaio", "bianchi", erf.retired)
      em.addStudentResult("gennaio", "verdi", erf.succeeded(28))
      em.addStudentResult("gennaio", "neri", erf.succeededCumLaude)

      em.addStudentResult("febbraio", "rossi", erf.failed)
      em.addStudentResult("febbraio", "bianchi", erf.succeeded(20))
      em.addStudentResult("febbraio", "verdi", erf.succeeded(30))

      em.addStudentResult("marzo", "rossi", erf.succeeded(25))
      em.addStudentResult("marzo", "bianchi", erf.succeeded(25))
      em.addStudentResult("marzo", "viola", erf.failed)
    }

    @Test def testExamsManagement(): Unit = {
      this.prepareExams()

      assertEquals(em.getAllStudentsFromCall("gennaio"), Set("rossi","bianchi","verdi","neri"))
      assertEquals(em.getAllStudentsFromCall("marzo"), Set("rossi","bianchi","viola"))

      assertEquals(em.getEvaluationsMapFromCall("gennaio").size,2)
      assertEquals(em.getEvaluationsMapFromCall("gennaio")("verdi"),28)
      assertEquals(em.getEvaluationsMapFromCall("gennaio")("neri"),30)

      assertEquals(em.getEvaluationsMapFromCall("febbraio").size,2)
      assertEquals(em.getEvaluationsMapFromCall("febbraio")("bianchi"),20)
      assertEquals(em.getEvaluationsMapFromCall("febbraio")("verdi"),30)


      assertEquals(em.getResultsMapFromStudent("rossi").size,3)
      assertEquals(em.getResultsMapFromStudent("rossi")("gennaio"),"FAILED")
      assertEquals(em.getResultsMapFromStudent("rossi")("febbraio"),"FAILED")
      assertEquals(em.getResultsMapFromStudent("rossi")("marzo"),"SUCCEEDED(25)")

      assertEquals(em.getResultsMapFromStudent("bianchi").size,3)
      assertEquals(em.getResultsMapFromStudent("bianchi")("gennaio"),"RETIRED")
      assertEquals(em.getResultsMapFromStudent("bianchi")("febbraio"),"SUCCEEDED(20)")
      assertEquals(em.getResultsMapFromStudent("bianchi")("marzo"),"SUCCEEDED(25)")

      assertEquals(em.getResultsMapFromStudent("neri").size,1)
      assertEquals(em.getResultsMapFromStudent("neri")("gennaio"),"SUCCEEDED(30L)")
    }


    @Test def optionalTestExamsManagement(): Unit = {
      this.prepareExams()

      assertEquals(em.getBestResultFromStudent("rossi"),Option(25))
      assertEquals(em.getBestResultFromStudent("bianchi"),Option(25))
      assertEquals(em.getBestResultFromStudent("neri"),Option(30))
      assertEquals(em.getBestResultFromStudent("viola"),Option.empty)
    }


    @Test def optionalTestCantCreateACallTwice(): Unit = {
      this.prepareExams()
      assertThrows(classOf[IllegalArgumentException], () => em.createNewCall("marzo"))
    }

    @Test def optionalTestCantRegisterAnEvaluationTwice() {
      this.prepareExams()
      assertThrows(classOf[IllegalArgumentException], () => em.addStudentResult("gennaio", "verdi", erf.failed))

    }
}
