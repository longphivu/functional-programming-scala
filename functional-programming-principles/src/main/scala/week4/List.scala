package week4

trait List[+T] {
  def isEmpty: Boolean
  def head: T
  def tail: List[T]
  def prepend[U >: T](elem: U): List[U] = new Cons(elem, this)

}

case object Nil extends List[Nothing] {
  def isEmpty: Boolean = true

  def head: Nothing = throw new NoSuchElementException()

  def tail: Nothing = throw new NoSuchElementException()
}

case class Cons[T](val head: T, val tail: List[T]) extends List[T] {
  def isEmpty: Boolean = false
}

object ListTest {
  def apply[T](x1: T, x2: T): List[T] = new Cons(x1, new Cons(x2, Nil))

  def sort(xs: List[Int]): List[Int] = xs match {
    case Nil => Nil
    case Cons(y, ys) => insert(y, sort(ys))
  }

  def insert(x: Int, xs: List[Int]): List[Int] = {
    xs match {
      case Nil => new Cons(x, Nil)
      case Cons(y, ys) =>
        if (x <= y) {
          new Cons(x, xs)
        } else {
          new Cons(y, insert(x, ys))
        }
    }
  }
}