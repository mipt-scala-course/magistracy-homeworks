package mipt
package plugin

import sbt._
import Keys._
import complete.DefaultParsers._

object CouningPlugin extends AutoPlugin {
    import Homeworks._
    
    override def trigger = allRequirements
    override def requires = empty

    object autoImport {
        val countSourceFiles= taskKey[Unit]("Count source files")
        val countSourceLines= taskKey[Unit]("Count source lines")
    }
    import autoImport._


    override def projectSettings: Seq[Def.Setting[_]] =
        inConfig(Compile)(countingSettings)

    lazy val countingSettings: Seq[Def.Setting[_]] =
        Seq(
            countSourceFiles := 
                task"""
                    Напишите код, который выведет в консоль следующие строки:
                        Counting source files...
                        <здесь количество файлов исходников> files found
                        Counting source files... done

                    Например:
                        Counting source files...
                        2 files found
                        Counting source files... done
                    """ (1, 1),
            countSourceLines :=
                task"""
                    Напишите код, который выведет в консоль следующие строки:
                        Counting source files...
                        <для каждого файла здесь имя> files found
                        Counting source files... done
                        

                    Например:
                        Counting source files...
                        HelloWorld.scala contains 4 lines
                        StaticPoems.scala contains 63 lines
                        Counting source files... done

                    *Вам может пригодиться sbt.IO
                    """ (1, 2),
        )

}