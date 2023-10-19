package mipt.homework7

import mipt.homework7.Color._

/**
  * Есть некий набор цветов в системе и палитра, представленная в виде имплиситных наборов базовых цветов в `StandardPalette`
  * Также существует тайпкласс ColorMixer, который задает правила смешивания цветов.
  * Необходимо в трейте `ColorMixersInstances` релизовать инстансы ColorMixer для следующих правил:
  * 1) Black + White = Grey
  * 2) Red + Yellow = Orange
  * 3) Green + Orange = Brown
  * а также коммутативный инстанс, который бы при наличии ColorMixer[I, J] выводил ColorMixer[J, I].
  *
  * В трейте `MixedColorInstance` для корректного выполнения тестов необходимо реализовать имплисит, который бы
  * выводил Color на основе имеющегося в скоупе ColorMixer.
  *
  * Для реализации имплиситных инстансов понадобится использование паттерна Aux.
  *
  */
case class Color[T](color: T)

object Color extends StandardPalette with MixedColorInstance {
  object Black
  type Black = Black.type

  object White
  type White = White.type

  object Grey
  type Grey = Grey.type

  object Green
  type Green = Green.type

  object Red
  type Red = Red.type

  object Yellow
  type Yellow = Yellow.type

  object Orange
  type Orange = Orange.type

  object Brown
  type Brown = Brown.type

  object Blue
  type Blue = Blue.type

  def apply[T](implicit color: Color[T]): Color[T] = color

}

trait StandardPalette {
  implicit val black: Color[Black]   = Color(Black)
  implicit val white: Color[White]   = Color(White)
  implicit val red: Color[Red]       = Color(Red)
  implicit val yellow: Color[Yellow] = Color(Yellow)
  implicit val green: Color[Green]   = Color(Green)
}

trait ColorMixer[I, J] {
  type Out
  def result: Out
}

trait ColorMixersInstances {
  implicit def commutativeMixer = ???

  implicit val greyMixer = ???

  implicit val orangeMixer = ???

  implicit val brownMixer = ???
}

trait MixedColorInstance {
  implicit def mixedColor = ???
}

object ColorMixer extends ColorMixersInstances {

  def apply[I, J](implicit colorMixer: ColorMixer[I, J]): ColorMixer[I, J] = colorMixer
}
