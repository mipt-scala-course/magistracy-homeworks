# Постановка задачи

Это задание предназначено для того, чтобы развернуть рабочее место, научиться создавать проект и попробовать на практике базовый синтаксис Scala.
Нужно:
1. Создать в корне репозитория `build.sbt`, где указать версию scala 2.13.10 (как это сделать - см. лекцию)
2. Импортировать проект в idea, создать в корне проекте папку src / main / scala для исходников на Scala.
> Если IDEA упрямо не будет хотеть распознавать проект, закройте ее, удалите папку .idea в проекте и откройте IDEA заново
3. Написать простой калькулятор комплексных чисел. Взаимодействие с пользователем через консоль:
    1. Читать из консоли два комплексных числа и операцию, которую нужно с ними выполнить. Поддерживаются операции сложения, вычитания, умножения.
    2. После ввода программа должна посчитать результат, вывести его в консоль и предложить пользователю выйти или продолжить (например ввести Y или N)
4. Ввод можно организовать как удобно (можно парсить строки из консоли или просить ввести отдельно вещественную и мнимую части чисел)
5. Можно использовать классы, цикл `while`, методы `scala.io.StdIn` для чтения из консоли и `println` для вывода в консоль.

# Домашние задания по курсу Scala в магистратуре МФТИ

Все домашние задания привязаны к лекциям по номеру. Каждое задание располагается в ветке *homework/lecture-N-M*, где N - номер семестра, M - номер лекции в семестре.
Задание должно быть выполнено в приватном форке в новой ветке, порожденной от ветки задания, и сдано в виде PR (pull request) в ветку с заданием.

Для работы над домашними заданиями нужно:
* установить IntelliJ IDEA и подготовить ее для работы со Scala. Это описано в [инструкции по установке IDEA](docs/idea-install/install.md).
* сделать **приватный форк** данного репозитория. Это описано в [инструкции по созданию форка](docs/create-fork/private-fork.md).
