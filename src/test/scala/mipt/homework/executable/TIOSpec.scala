package mipt.homework.executable

import org.scalatest.flatspec.AnyFlatSpec
import org.scalatest.matchers.should.Matchers

import scala.concurrent.duration.Duration
import scala.concurrent.{Await, Future}
import scala.util.{Failure, Try}

class TIOSpec extends AnyFlatSpec with Matchers {

  import scala.concurrent.ExecutionContext.Implicits.global
  import TIOOps._

  "recoverWith" should "recover applicable exception" in {

    await(
      TIO[Int](() => throw new ArithmeticException()).recoverWith {
        case _: ArithmeticException => TIO.pure(10)
      }.run()
    ) shouldBe 10

  }

  it should "throw non-applicable exception" in {

    Try(
      await(
        TIO[Int](() => throw new UnsupportedOperationException()).recoverWith {
          case _: ArithmeticException => TIO.pure(10)
        }.run()
      )
    ) shouldBe a[Failure[UnsupportedOperationException]]

  }

  def await[T](future: Future[T]): T = Await.result(future, Duration.Inf)
}
