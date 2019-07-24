package week4

// PATTERN MATCHING: TOOL FOR DECOMP


// trait Expr
trait Expr {
	def eval: Int = this match {
		case Number(n) => n
		case Sum(e1, e2) => e1.eval + e2.eval
		case Prod(l, r) => l.eval * r.eval
	}

	def show: String = this match {
		case Number(x) => x.toString
		case Sum(l, r) => Array(l.show, r.show).mkString("(", " + ", ")")
		case Prod(l, r) => l.show + " * " + r.show
		case Var(x) => x
	}
}
case class Number(n: Int) extends Expr
case class Sum(e1: Expr, e2: Expr) extends Expr
case class Var(x: String) extends Expr
case class Prod(l: Expr, r: Expr) extends Expr

// object eval{
// 	def eval(e: Expr): Int = e match {
// 		case Number(n) => n
// 		case Sum(e1, e2) => eval(e1) + eval(e2)
// 	}
// }


// Tradeoff
// Are more more subclasses of the method or creating methods ()



// using case class automatically adds companion objects
/*object Number {
	def apply(n: Int) = new Number(n)
}

object Sum {
	def apply(e1: Expr, e2: Expr) = new Sum(e1, e2)
}
*/

/*

*/