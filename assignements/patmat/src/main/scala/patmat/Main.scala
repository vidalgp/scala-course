package patmat

object Main extends App {
  import Huffman._
  val t1 = Fork(Leaf('a',2), Leaf('b',3), List('a','b'), 5)
  val d1 = encode(t1)("baba".toList)
  val res = decode(t1, d1)
  println(s"Final $d1")
  println(s"Resultado $res vs baba")
	println(convert(t1))

	val d2 = quickEncode(t1)("baba".toList)
	val res2 = decode(t1, d2)
  	println(s"Final $d2")
  	println(s"Resultado $res2 vs baba")
}

