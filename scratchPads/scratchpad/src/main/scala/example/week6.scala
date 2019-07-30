package week6

object scratch {
	def scalarProd(xs: Vector[Double], ys: Vector[Double]): Double =
		(xs zip ys).map(xy => xy._1 * xy._2).sum

	// def scalarProd(xs: Vector[Double], ys: Vector[Double]): Double =
	// 	(xs zip ys).map{ case (x, y) => x * y}.sum

	def isPrime(n: Int): Boolean = (2 until n) forall (d => n % d != 0)


	// val n = 7
	// (1 until n) flatMap (i => (1 until i) map (j => (i, j)))

	//or better yet

	//for (p <- persons if p.age > 20) yield p.name
	//equiv: person filter (p => p.age > 20) map (p => p.name)

	// for {
	// 	i <- 1 until n
	// 	j <- 1 until i
	// 	if isPrime(i + j)
	// } yield (i, j)

	// for (i <- 1 until n; j <- 1 until i; if isPrime(i + j)) yield (i, j)


	// def scalarProd(xs: Vector[Double], ys: Vector[Double]): Double =
	// 	(for((x, y) <- xs zip ys) yield x * y).sum

	//SETS

	def queen(n: Int): Set[List[Int]] = {
		def placeQueens(k: Int): Set[List[Int]] ={
			println(s"this is pq $k")
			if (k == 0) Set(List())
			else
				for {
					queens <- placeQueens(k - 1)
					col <- 0 until n
					if isSafe(col, queens)
				} yield col :: queens
		}
		placeQueens(n)
	}

	def show(queens: List[Int]) = {
		val lines =
			for (col <- queens.reverse)
			yield Vector.fill(queens.length)("* ").updated(col, "X ").mkString
			"\n" + (lines mkString "\n")
	}
	def isSafe(col: Int, queens: List[Int]): Boolean = {
		val row = queens.length
		print(col, queens)
		val queensWithRow = (row - 1 to 0 by -1) zip queens
		val tf = queensWithRow forall {
			case (r, c) => col != c &&math.abs(col - c) != row - r
		}
		println(tf)
		tf
	}

	//Working with Map data structures.
	// calling a map as function using get, returns an Option trait
	//Options also support quite a few ops of other collections
	//PATTER MATCHEDD!
	def showCap(country: String, coc: Map[String, String]) = coc get country match {
		case Some(cap) => cap
		case None => "missing"
	}

	//SQL orderBy is equiv to Scala's sortWith  o collection.sorted
	//and SQL groupBy is equiv to collections.groupBy which returns
	//a map according to the objective of the group


	//Make Map a total function: using withDefaultValue "<whatever>"
}