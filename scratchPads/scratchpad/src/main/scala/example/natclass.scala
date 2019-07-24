package week4

//Peano numbers

abstract class Nat {
	def isZero: Boolean
	def predecessor: Nat
	def succesor: Nat = new Succ(this)
	def + (that: Nat): Nat
	def - (that: Nat): Nat
}

object Zero extends Nat {
	def isZero = true
	def predecessor = throw new Error("0.predecessor")
	def + (that: Nat) = that
	def - (that: Nat) =
		if (that.isZero) this
		else throw new Error("0 - that") 
}

class Succ(n: Nat) extends Nat {
	def isZero = false
	def predecessor = n
	def + (that: Nat) = new Succ(n + that)
	//Succ since n is one step behind
	def - (that: Nat) =
		if (that.isZero) this
		else n - that.predecessor
}