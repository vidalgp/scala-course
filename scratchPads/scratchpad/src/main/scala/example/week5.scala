package week5

object week5 {

	//Number of steps proportional to the lengtth of xs
	def last[T](xs: List[T]): T = xs match {
		case List() => throw new Error
		case List(x) => x
		case y :: ys => last(ys)
	}

	def init[T](xs: List[T]): List[T] = xs match {
		case Nil => throw new Error
		case x :: Nil  => Nil
		case y :: ys => y :: init(ys)
	}

	def concat[T](xs: List[T], ys: List[T]): List[T] = xs match {
		case Nil => ys
		case z :: zs => z :: concat(zs, ys)
	}

	//quadratic time in the length of xs (concat and reverse)
	def reverse[T](xs: List[T]): List[T] = xs match {
		case Nil => xs //Nil
		case y :: ys => reverse(ys) ::: y :: Nil
 	}

 	def removeAtAlt[T](n: Int, xs: List[T]): List[T] = {
 		def iter(xs: List[T], counter: Int): List[T] = xs match {
	 		case Nil => Nil
	 		case y :: ys => if (counter == n) ys else List(y) ::: iter(ys, counter + 1)
 		}
 		iter(xs, 0)
 	}
 	// mindbl
	 def removeAt[T](n: Int, xs: List[T]): List[T] = (xs take n) ::: (xs drop n + 1)

	 def flatten(xs: List[Any]): List[Any] = xs match {
	 	case Nil 				=> Nil
	 	case (x: List[_]) :: xs => flatten(x) ++ flatten(xs)
	 	case x :: xs 			=> x :: flatten(xs)
	 }


	 // def msort(xs: List[Int]): List[Int] = {
	 // 	val n = xs.length / 2
	 // 	if (n == 0) xs
	 // 	else {
	 // 		def merge(xs: List[Int], ys: List[Int]): List[Int] = (xs, ys) match {
	 // 			case (Nil, ys) => ys
	 // 			case (xs, Nil) => xs
	 // 			case (x :: xs1, y :: ys1) => if (x < y) x :: merge(xs1, ys) else y :: merge(xs, ys1)
	 // 			}

	 // 		val (fst, snd) = xs splitAt n
	 // 		merge(msort(fst), msort(snd))
	 // 	}
	 // }

	 def msort[T](xs: List[T])(implicit ord: Ordering[T]): List[T] = {
	 	val n = xs.length / 2
	 	if (n == 0) xs
	 	else {
	 		def merge(xs: List[T], ys: List[T]): List[T] = (xs, ys) match {
	 			case (Nil, ys) => ys
	 			case (xs, Nil) => xs
	 			case (x :: xs1, y :: ys1) => if (ord.lt(x, y)) x :: merge(xs1, ys) else y :: merge(xs, ys1)
	 			}

	 		val (fst, snd) = xs splitAt n
	 		merge(msort(fst), msort(snd))
	 	}
	 }

/*//higher order list functions
	abstract class List[L] {
		def map[U](f: T => U): List[U] = this match {
			case Nil => this
			case x :: xs => f(x) :: xs.map(f)
		}
		def filter(p: T => Boolean): List[T] = this match {
			case Nil => this
			case x :: xs => if (p(x)) x :: xs.filter(p) else xs.filter(p)
		}

		def reduceLeft(op: (T, T) => T): T = this match {
			case Nil => throw new Error("Nil.reduceLeft")
			case x :: xs => (xs foldLeft x)(op)
		}

		def foldLeft[U](z: U)(op: (U, T) => T): U = this match {
			case Nil => z
			case x :: xs => (xs foldLeft op(z, x))(op)
		}

		def reduceRight(op: (T, T) => T): T = this match {
			case Nil => throw new Error
			case x :: Nil => x
			case x :: xs => op(x, xs.reduceRight(op))
		}

		def foldRight[U](z: U)(op:(U, T) => U): U = this match {
			case Nil => z
			case x :: xs => op(x, (xs foldRight z)(op))
		}

		def mapFun[T, U](xs: List[T], f: T => U): List[U] =
  			(xs foldRight List[U]())( f(_) :: _)

		def lengthFun[T](xs: List[T]): Int =
 			(xs foldRight 0)( (x,y) => 1 + y )

		//Trees that lean to the left.


	}*/

	// def squareList(xs: List[Int]): List[Int] = xs map (x => x * x)

	// def posEle(xs: List[Int]): List[Int] = xs filter (x => x > 0)

	def pack[T](xs: List[T]): List[List[T]] = xs match {
		case Nil => Nil
		case x :: xs1 => //(xs takeWhile (y => y == x)) :: pack(xs dropWhile (ys => ys == x)) 
			{val (first, rest) = xs span (y => y == x); first :: pack(rest)}
	}

	def encode[T](xs: List[T]): List[(T, Int)] = pack(xs) map (ys => (ys.head, ys.length))
		// (y, (xs takeWhile (gi => y == gi)).length ) :: encode(xs dropWhile (gi => y == gi))


}