package homework2

import mipt.homework2.Decoder
import mipt.homework2.DecoderInstances.{DayOfWeekOutOfBoundError, IllegalArgumentDecoderError, NumberFormatDecoderError}
import org.scalatest.Inside
import org.scalatest.flatspec.AnyFlatSpec
import org.scalatest.matchers.should.Matchers

import java.time.format.DateTimeFormatter
import java.time.{Clock, DayOfWeek, Instant, OffsetDateTime}

class DecoderTest extends AnyFlatSpec with Matchers with Inside:
  import mipt.homework2.DecoderInstances.{given, *}

  behavior.of("Decoder")

  it should "correctly decode int" in {
    Decoder.decode[Int]("123") shouldBe Right(123)
    Decoder.decode[Int]("aaa1") shouldBe Left(NumberFormatDecoderError)
  }

  it should "correctly decode boolean" in {
    Decoder.decode[Boolean]("true") shouldBe Right(true)
    Decoder.decode[Boolean]("aaa1") shouldBe Left(IllegalArgumentDecoderError)
  }

  it should "correctly decode day of week" in {
    Decoder.decode[DayOfWeek]("1") shouldBe Right(DayOfWeek.MONDAY)
    Decoder.decode[DayOfWeek]("10") shouldBe Left(DayOfWeekOutOfBoundError)
  }

  it should "correctly decode instant" in {
    Decoder.decode[Instant]("2023-02-17T17:00:00Z") shouldBe Right(Instant.ofEpochSecond(1676653200))
    Decoder.decode[Instant]("10") shouldBe Left(DayOfWeekOutOfBoundError)
  }

  it should "correctly decode option" in {
    Decoder.decode[Option[Int]]("123") shouldBe Right(Some(123))
    Decoder.decode[Option[Int]]("abc") shouldBe Left(NumberFormatDecoderError)
    Decoder.decode[Option[Int]]("") shouldBe Right(None)
  }

  it should "correctly decode list" in {
    Decoder.decode[List[Int]]("123, 321, 333") shouldBe Right(List(123, 321, 333))
    Decoder.decode[List[Int]]("abc") shouldBe Left(NumberFormatDecoderError)
    Decoder.decode[List[Option[Int]]]("1, 2, 3") shouldBe Right(List.empty)
    Decoder.decode[Option[List[Int]]]("1, 2, 3") shouldBe Right(List.empty)
  }