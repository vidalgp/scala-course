package playg

//WEEK 2 of Programming in Scala Pples

// As binary trees data struct

//persistent data structures

abstract class IntSet {
	//BODY LESS functions: only possible for abstract class
	def incl(x: Int): IntSet
	def contains(x: Int): Boolean
	def union(other: IntSet): IntSet
}

//Defined as a singleton object named Empty.
//No other instances can/need to be created
//Singleton object are values, so Empty evaluates itself.
object Empty extends IntSet {
	def contains(x: Int): Boolean = false
	def incl(x: Int): IntSet = new NonEmpty(x, Empty, Empty)
	def union(other: IntSet): IntSet = other
	override def toString = "."
}

/*class Empty extends IntSet {
	def contains(x: Int): Boolean = false
	def incl(x: Int): IntSet = new NonEmpty(x, new Empty, new Empty)
	override def toString = "."
}*/


class NonEmpty(elem: Int, left: IntSet, right: IntSet) extends IntSet {

	def contains(x: Int): Boolean =
		if (x < elem) left contains x
		else if (x > elem) right contains x
		else true

	def incl(x: Int): IntSet =
		if (x < elem) new NonEmpty(elem, left incl x, right)
		else if (x > elem) new NonEmpty(elem, left, right incl x)
		else this

	def union(other: IntSet): IntSet =
		((left union right) union other) incl elem
	// because every call on union the set is smaller
	// union will terminate

	override def toString = "{" + left + elem + right + "}"
}

//extends define inheritance between base class and sub class
//if no superclass is given, the standard java class Object is assumed
//base class :: superclass
//To redefine an existing method of a base class, implement override


//dynamic method dispatch
//the code invoked by a method call depends on the runtime type of the 
// object that contains the method.


// class Square extends Shape with Planar with Movable ...
// where Planar and Movable are traits
// for multi inheritance, since in java a class can only have one superclass

// **traits cannot have val parameters

// scala.AnyVal (primitive types)
// scala.AnyRef (alias for java.lang.Object. ie List, Seq, Iterable)

// Scala's exception handling
// throw Exc

// null is type Null (or String)
// if (true) 1 else false     wouuld type AnyVal