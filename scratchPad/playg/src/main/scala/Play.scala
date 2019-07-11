package playg

//WEEK 1 of Programming in Scala Pples

object Newton {
	// Newtons method
	def abs(x: Double) = if(x > 0) x else -x

	def sqrt(x: Double) = {
		def sqrtIter(guess: Double): Double = 
			if (isGoodEnough(guess)) guess
			else sqrtIter(improve(guess))

		def isGoodEnough(guess: Double) = 
			abs(guess * guess - x) / x < 0.001

		def improve(guess: Double) =
			(guess + x / guess) / 2

		sqrtIter(1.0)
	}
}


object TailRecur {
	def factorial(n: Int): Int = {
		def loop(acc: Int, n: Int): Int =
			if(n == 0) acc
			else loop(acc * n, n - 1)
		loop(1, n)
	}
}


object HOF {
	def sum(f: Int => Int, a: Int, b: Int): Int = {
		def  loop(a: Int, acc: Int): Int =
			if (a > b) acc
			else loop(a + 1, f(a) + acc)
		loop(a, 0)
	}
}

object Currying {
	def sum(f: Int => Int)(a: Int, b: Int): Int =
		if (a > b) 0 else f(a) + sum(f)(a + 1, b)

	/*def product(f: Int => Int)(a: Int, b: Int): Int =
		if (a > b) 1 else f(a) * product(f)(a + 1, b)*/

	def fact(n: Int): Int =
		product(x => x)(1, n)

	def mapReduce(f: Int => Int, combine: (Int, Int) => Int,
					zero: Int)(a: Int, b: Int): Int =
		if (a > b) zero
		else combine(f(a), mapReduce(f, combine, zero)(a + 1, b))

	def product(f: Int => Int)(a: Int, b: Int): Int =
		mapReduce(f, (x, y) => x * y, 1)(a, b)
}

import math.abs

object Excercise {
	val tolerance = 0.0001
	def isCloseEnough(x: Double, y: Double) =
		abs((x - y) / x) / x < tolerance
	def fixedPoint(f: Double => Double)(firstGuess: Double) = {
		def iterate(guess: Double): Double = {
			val next = f(guess)
			if (isCloseEnough(guess, next)) next
			else iterate(next)
		}
		iterate(firstGuess)
	}

	def sqrt(x: Double) = fixedPoint(averageDamp(y => x / y))(1)

	def averageDamp(f: Double => Double)(x: Double) =
		(x + f(x))/2
}

class Rational(x: Int, y: Int) {
	require(y != 0, "Denominator must be nonzero")

	def this(x: Int) = this(x, 1) //second constructor

	private def gcd(a: Int, b: Int): Int =
		if (b == 0) a else gcd(b, a % b)
	private val g = gcd(x, y)
	val numer = x / g
	val denom = y / g

	def <(that: Rational): Boolean =
		this.numer * that.denom < that.numer * this.denom

	def max(that: Rational) =
		if (this < that) that else this //this is like self

	def unary_- : Rational = new Rational(-numer, denom)

	def sub(that: Rational) = this + -that 
	//**It is not necessary to redefine type,
	// considering add already return a determined type(?!)

	def +(that: Rational) = //idem **
		new Rational (
			numer * that.denom + that.numer * denom,
			denom * that.denom
		)

	override def toString = numer + "/" + denom

	//operators x.add(s) can be written x add s
	//relaxed id: x1 or * or +?%& or vector_++ or counter_=
	//useful in functions as is the case of less <
	// when using unary_ there must be a space after the symbol
}
