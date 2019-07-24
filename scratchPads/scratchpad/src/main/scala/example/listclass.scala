/*package week4

// type parameters are inside brackets

/* 
Por que trait List y no un abastract class?
- Abstract classes can have constructor parameters
as well as type parameters. Traits can have only type parameters. 
- Abstract classes are fully interoperable with Java.
*/
trait List[+T] {
	def isEmpty: Boolean
	def head: T
	def tail: List[T]
	// Returns the superclass in case of conflict.
	def prepend[U >: T](elem: U): List[U] = new Cons(elem, this)
}	

// def head and tail the same as the parameter values
// def is evaluated every time the object is referenced
// whereas val when the object is initialized
class Cons[T](val head: T, val tail: List[T]) extends List[T] {
	def isEmpty = false
}

// Nothing is a subtype of any other type
object Nil extends List[Nothing] {
	def isEmpty: Boolean = true
	def head: Nothing = throw new NoSuchElementException("Nil.head")
	def tail: Nothing = throw new NoSuchElementException("Nil.tail")
}

object List {
	def apply[T](x1: T, x2: T): List[T] =
		new Cons(x1, new Cons(x2, Nil))
	def apply[T](x: T): List[T] = new Cons(x, Nil)
	def apply[T](): List[T] = Nil
}*/