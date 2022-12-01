package week4

trait Expr {
  def eval: Int
  def show: String = this match {
    case Number(n) => n.toString
    case Sum(e1, e2) => e1.show + "+" + e2.show
    case Prod(e1, e2) =>
      def matchFn(expr: Expr) = expr match {
        case Sum(_, _) => "(" + e1.show + ")"
        case _ => expr.show
      }
      val leftStr = matchFn(e1)
      val rightStr = matchFn(e2)
      leftStr + "*" + rightStr
    case Var(x) => x
  }
}

case class Number(n: Int) extends Expr {
  def eval: Int = n
}

case class Sum(e1: Expr, e2: Expr) extends Expr {
  def eval: Int = e1.eval + e2.eval
}

case class Prod(e1: Expr, e2: Expr) extends Expr {
  def eval: Int = e1.eval * e2.eval
}

case class Var(x: String) extends Expr {
  def eval = throw new Error("Can eval a variable")
}

import week4.{Number, Prod, Sum, Var}

object Test {
  Sum(Prod(Number(2), Var("x")), Var("y")).show
}
