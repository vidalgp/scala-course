package idealized.scala

// Boolean is an abstract superclass with 2 concrete methods
// `true` and `false`.
// The superclass defines common operations on Boolean (true, false) like
// && , ||, etc
// delegating the differing behaviour to the only specific op that is different
// between subclasses: the ifThenElse methods.

// The subclasses are defined as objects, so there's only a single instance of them.
// singleton*
// ie, `true`.ifThenElse(yes, no) >> yes (and viceversa)

abstract class Boolean {
	def ifThenElse[T](t: => T, e: => T): T

	def && (x: => Boolean): Boolean = ifThenElse(x, `false`)
	def || (x: => Boolean): Boolean = ifThenElse(`true`, x)
	def unary_! : Boolean = ifThenElse(`false`, `true`)

	def == (x: Boolean): Boolean = ifThenElse(x, x.unary_!)
	def != (x: Boolean): Boolean = ifThenElse(x.unary_!, x)

	def < (x: Boolean) = ifThenElse(`false`, x)
}

object `true` extends Boolean {
	def ifThenElse[T](t: => T, e: => T) = t
}

object `false` extends Boolean {
	def ifThenElse[T](t: => T, e: => T) = e
}




