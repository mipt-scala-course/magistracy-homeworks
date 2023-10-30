package mipt.homework9

import scala.concurrent.ExecutionContext

object Task2 {

  implicit class TFutureRecover[T](val tFuture: TFuture[T]) extends AnyVal {

    /**
      * Реализовать метод recoverWith, который аналогичен по поведению методу recoverWith стандартной Future.
      * Если исходная tFuture завершилась успехом - возвращать ее результат
      * Если исходная tFuture завершилась исключением, которое не обрабатывается частичной функцией - возвращать ее результат
      * Если исходная tFuture завершилась исключением, которое обрабатывается частичной функцией - обработать его асинхронно
      * Если попытка обработать исключение провалилась - вернуть зафейленную фьючу с этим исключением
      * При реализации пользоваться TFuture и TPromise
      */
    def recoverWith[U >: T](pf: PartialFunction[Throwable, TFuture[U]])(implicit ec: ExecutionContext): TFuture[U] = ???
  }

}
