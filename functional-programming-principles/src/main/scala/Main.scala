import week4._

object Main {
  def main(args: Array[String]): Unit = {
    println("Hello world!")
    val l1 = ListTest.apply(1, 2).prepend(3).prepend(4)
    println(l1)
    println(ListTest.sort(l1))

    println("Testing Expression")
    println(Number(2).show)
    println(Var("x").show)
    println(Sum(Prod(Number(2), Var("x")), Var("y")).show)
    println(Prod(Sum(Number(2), Var("x")), Var("y")).show)
  }
}


