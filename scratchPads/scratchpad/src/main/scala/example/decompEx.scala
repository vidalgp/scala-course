// package week4

/*trait Expr {
	def isNumber: Boolean //classif
	def isSum: Boolean //classif
	def numValue: Int //accessor
	def leftOp: Expr //accessor
	def rightOp: Expr //accessor
}

class Number(n: Int) extends Expr {
	def isNumber: Boolean = true
	def isSum: Boolean = false
	def numValue: Int = n
	def leftOp: Expr = throw new Error("Number.leftOp")
	def rightOp: Expr = throw new Error("Number.rightOp")
}

class Sum(e1: Expr, e2: Expr) extends Expr {
	def isNumber: Boolean = false
	def isSum: Boolean = true
	def numValue: Int = throw new Error("Sum.numValue")
	def leftOp: Expr = e1
	def rightOp: Expr = e2
}


object Eval {
	def eval(e: Expr): Int = {
		if (e.isNumber) e.numValue
		else if (e.isSum) eval(e.leftOp) + eval(e.rightOp)
		else throw new Error(s"Unknown expression $e")
	}

	def evalHacky(e: Expr): Int = {
		if (e.isInstanceOf[Number]) e.asInstanceOf[numValue]
		else if (e.isInstanceOf[Sum])
			eval(e.asInstanceOf[Sum].leftOp) +
			eval(e.asInstanceOf[Sum].rightOp)
		else throw new Error(s"Unknown expression $e")
	}
}
*/
// In case I want to add two more clases with one param,
// I would hacve to define 25 new methods!!!! (grows quadratically)

// Hacky solutions could use type tests and type casts.
// def isInstanceOf[T]: Boolean ()
// def asInstanceOf[T]: T

// Assesement of this solution:
// No need for classification methods, access methods only for
// classes where the value is defined 
// BUT low-level and potentially unsafe (dont know if cast will succeed)


//INSTEAD: OO DECOMPOSITION

/*
trait Expr {
	def eval: Int
	def show: String
}

class Number(n: Int) extends Expr {
	def eval: Int = n
	def show: String = this.eval.toString
}

class Sum(e1: Expr, e2: Expr) extends Expr {
	def eval: Int = e1.eval + e2.eval
	def show: String = this.eval.toString
}
*/
// But what happens when you want to display expression now?
// you will have to define new methods in all the subclasses.