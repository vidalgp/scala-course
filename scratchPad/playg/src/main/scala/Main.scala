package playg

/*object Main extends App {
  import playg._
  val t1 = new NonEmpty(3, Empty, Empty)
  println(t1)
  val t2 = t1 incl 4
  println(t2)
}*/

/*object Hello {
	def main(args: Array[String]) = println("Hello Wordl!")
	import playg._
  	val t1 = new NonEmpty(3, Empty, Empty)
  	println(t1)
  	val t2 = t1 incl 4
  	println(t2)
}
*/

/*object Main extends App {
	def error(msg: String) = throw new Error(msg)

	error("test")
}
*/

object Main extends App {
	import playg._
	val list = new Cons(1, new Cons(2, new Cons(3, new Nil)))
	println(nth.nth(4, list))
}
