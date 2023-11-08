package mipt.homework.executable

import scala.concurrent.{ExecutionContext, Future}

/**
  * У нас есть реализация своей IO-монады TIO в виде функционального эффекта с исполняемой моделью.
  * TIO представляет собой обертку вокруг функции, к которой есть smart-конструкторы и операторы map и flatMap,
  * и этот эффект пригоден для описания последовательного выполнения каких-либо чистых операций или действий с побочными эффектам
  * на базе стандартной Scala Future (поэтому в операциях присутствует ExecutionContext), фактически являясь
  * ленивой версией Future
  *
  * Нужно реализовать возможность заворачивать в этот эффект и обрабатывать также возможные исключения
  * Для этого нужно реализовать smart-конструктор failed и оператор recoverWith с семантикой, аналогичной Try/Future
  */
case class TIO[T](run: () => Future[T])

object TIO {

  def pure[T](v: T): TIO[T] = TIO(() => Future.successful(v))

  def apply[T](block: => T)(implicit ec: ExecutionContext): TIO[T] =
    TIO(() => Future(block))

}

object TIOOps {

  implicit class ops[T](val future: TIO[T]) extends AnyVal {
    def map[U](f: T => U)(implicit ec: ExecutionContext): TIO[U] =
      TIO(() => future.run().map(f))
    def flatMap[U](f: T => TIO[U])(implicit ec: ExecutionContext): TIO[U] =
      TIO(() => future.run().flatMap { t => f(t).run() })

    /**
      * Допишите метод восстановления из ошибки
      */
    def recoverWith(pf: PartialFunction[Throwable, TIO[T]])(implicit ec: ExecutionContext): TIO[T] = ???

  }

}
