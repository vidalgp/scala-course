package playg

//WEEK 2 of Programming in Scala Pples

// Cons - List: Linked list data structure




// type parameters are inside brackets
trait List[T] {
	def isEmpty: Boolean
	def head: T
	def tail: List[T]
}


// def head and tail the same as the parameter values
// def is evaluated every time the object is referenced
// whereas val when the object is initialized
class Cons[T](val head: T, val tail: List[T]) extends List[T] {
	def isEmpty = false
}

// Nothing is a subtype of any other type
class Nil[T] extends List[T] {
	def isEmpty: Boolean = true
	def head: Nothing = throw new NoSuchElementException("Nil.head")
	def tail: Nothing = throw new NoSuchElementException("Nil.tail")
}


object nth {
	def nth[T](n: Int, xs: List[T]): T = {
		if (xs.isEmpty) throw new IndexOutOfBoundsException
		if (n == 0) xs.head
		else nth(n - 1, xs.tail)
	}
}

// Polymorfism
// subtyping: instances of a subclass can be passed to a base class.
// generics: instances of a funcion or class are created by type parametrization


// def singleton[T](elem: T) = new Cons[T](elem, new Nil[T])


// type parameters do not affect evaluation in Scala. Type Erasure
// only relevant for compiler. Unlike C++


// val defines a parameter and a value definition at the same time.
// equivalent to 
// class Cons(_head: Int, _tail: IntList) extends IntList {
// 	val head = _head
// 	val tail = _tail
//} val parameter are more concise