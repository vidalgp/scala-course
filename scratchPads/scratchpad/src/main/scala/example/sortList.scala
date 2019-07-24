package week4


object Sort {
	def isort(xs: List[Int]): List[Int] = xs match {
		case Nil => List()
		case y :: ys => insert(y, isort(ys))
	}

	def insert(x: Int, xs: List[Int]): List[Int] = xs match {
		case Nil => List(x)
		case y :: ys => if (y >= x) x :: xs else y :: insert(x, ys)
	}
}