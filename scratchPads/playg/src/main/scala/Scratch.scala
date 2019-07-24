import playg.{Rational, Excercise}

object scratch {
	val a = new Rational(1,2)
	println(a)
}

class Person(val name: String = "Vidal"){
	val privateAge = 0
	println(s"$name is $privateAge")

	// def age = privateAge
	// def age_=(newVal: Int) {
	// 	privateAge = newVal
	// }
}

class Message {
	val timeStamp = java.time.Instant.now
}

/*class Counter {
	private var value = 0 // You must initialize the field
	def increment() { value += 1 } // Methods are public by default
	def current= value
}*/

import scala.beans.BeanProperty
class People {
	@BeanProperty var name: String = _
}


/*class Person {
	private var name = ""
	private var age = 0
	
	def this(name: String) { // An auxiliary constructor
		this() // Calls primary constructor
		this.name = name
	}

	def this(name: String, age: Int) { // Another auxiliary constructor
		this(name) // Calls previous auxiliary constructor
		this.age = age
	}
}*/


import scala.collection.mutable.ArrayBuffer

class Network {
	class Member(val name: String) {
		val contacts = new ArrayBuffer[Network#Member]
	}
	
	val members = new ArrayBuffer[Member]
	
	def join(name: String) = {
		val m = new Member(name)
		members += m
		m
	}
}