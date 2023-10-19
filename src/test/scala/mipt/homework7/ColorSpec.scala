package mipt.homework7

import mipt.homework7.Color._
import org.scalatest.flatspec.AnyFlatSpec
import org.scalatest.matchers.should.Matchers

class ColorSpec extends AnyFlatSpec with Matchers {

  "Colors" should "be implicitly available" in {
    Color[Grey] shouldBe Color(Grey)
    Color[Orange] shouldBe Color(Orange)
    Color[Brown] shouldBe Color(Brown)
  }

  "Colors with unavailable mixers" should "be not compilable" in {
    "Color[Blue]" shouldNot compile
  }

  "Mixers" should "be commutative" in {
    ColorMixer[White, Black].result shouldBe ColorMixer[Black, White].result
    ColorMixer[Red, Yellow].result shouldBe ColorMixer[Yellow, Red].result
    ColorMixer[Green, Orange].result shouldBe ColorMixer[Orange, Green].result
  }
}
