package mipt.homework.declarative

/**
  * У нас есть реализация своей IO-монады TIO в виде функционального эффекта с декларативной моделью.
  * TIO представляет собой ADT, к которому есть smart-конструкторы и операторы map и flatMap,
  * и этот эффект пригоден для описания последовательного выполнения каких-либо чистых операций или действий с побочными эффектам
  *
  * Нужно реализовать возможность заворачивать в этот эффект и обрабатывать также возможные исключения
  * Для этого нужно реализовать smart-конструктор failed и оператор recoverWith с семантикой, аналогичной Try/Future
  */
sealed trait TIO[+T]

case class Complete[A](value: () => A)                         extends TIO[A]
case class CalculationStep[A, B](f: A => TIO[B], prev: TIO[A]) extends TIO[B]

object TIO {

  def pure[A](v: A): TIO[A]         = Complete(() => v)
  def apply[A](block: => A): TIO[A] = CalculationStep[Unit, A](_ => pure(block), pure(()))

  /**
    * Имплементируйте создание упавшего с исключением TIO
    */
  def failed[A](th: Throwable): TIO[A] = ???

}

object TIOOps {

  implicit class ops[A](val io: TIO[A]) extends AnyVal {

    def map[B](f: A => B): TIO[B]          = flatMap(a => TIO.pure(f(a)))
    def flatMap[B](f: A => TIO[B]): TIO[B] = CalculationStep(f, io)

    /**
      * Имплементируйте метод восстановления TIO из ошибочного состояния
      */
    def recoverWith(pf: PartialFunction[Throwable, TIO[A]]): TIO[A] = ???

  }

}

object TIORuntime {

  /**
    * Допишите интерпретатор так, чтобы при выполнении он обрабатывал восстановление из ошибки
    */
  def run[A](io: TIO[A]): A = io match {
    case Complete(value)          => value()
    case CalculationStep(f, prev) => run(f(run(prev)))
  }

}
