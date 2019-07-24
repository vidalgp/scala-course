package patmat

import org.scalatest.FunSuite

import org.junit.runner.RunWith
import org.scalatest.junit.JUnitRunner

import patmat.Huffman._

@RunWith(classOf[JUnitRunner])
class HuffmanSuite extends FunSuite {
	trait TestTrees {
		val t1 = Fork(Leaf('a',2), Leaf('b',3), List('a','b'), 5)
		val t2 = Fork(Fork(Leaf('a',2), Leaf('b',3), List('a','b'), 5), Leaf('d',4), List('a','b','d'), 9)
	}


  test("weight of a larger tree") {
    new TestTrees {
      assert(weight(t1) === 5)
    }
  }


  test("chars of a larger tree") {
    new TestTrees {
      assert(chars(t2) === List('a','b','d'))
    }
  }


  test("string2chars(\"hello, world\")") {
    assert(string2Chars("hello, world") === List('h', 'e', 'l', 'l', 'o', ',', ' ', 'w', 'o', 'r', 'l', 'd'))
  }


  test("makeOrderedLeafList for some frequency table") {
    assert(makeOrderedLeafList(List(('t', 2), ('e', 1), ('x', 3))) === List(Leaf('e',1), Leaf('t',2), Leaf('x',3)))
  }


  test("combine of some leaf list") {
    val leaflist = List(Leaf('e', 1), Leaf('t', 2), Leaf('x', 4))
    assert(combine(leaflist) === List(Fork(Leaf('e',1),Leaf('t',2),List('e', 't'),3), Leaf('x',4)))
  }

  test("combine of some leaf list 2") {
    val leaflist = List(Leaf('e', 1), Leaf('t', 2), Leaf('x', 4), Leaf('x', 5), Leaf('x', 6))
    assert(combine(leaflist) === List(Fork(Leaf('e',1),Leaf('t',2),List('e', 't'),3), Leaf('x',4), Leaf('x', 5), Leaf('x', 6)))
  }

  test("until of some leaf list") {
    val leaflist = List(Leaf('e', 1), Leaf('t', 2), Leaf('x', 4), Leaf('g', 5), Leaf('m', 6))
    assert(until(singleton, combine)(leaflist) ===
      List(Fork( //e, t, x, g, m
            Fork( //e, t, x, g
              Fork( //e, t, x
                Fork( //e, t
                  Leaf('e',1),Leaf('t',2),List('e', 't'),3),
                Leaf('x',4),List('e', 't', 'x'),7),
              Leaf('g',5),List('e', 't', 'x', 'g'),12),
            Leaf('m',6),List('e', 't', 'x', 'g', 'm'),18)
          )
    )
  }

  test("createCodeTree of some charlist") {
    val stringChars = string2Chars("gmmetxmmxgtgxmgxmg")
    assert(createCodeTree(stringChars) ===
      Fork( //e, t, x, g, m
            Fork( //e, t, x, g
              Fork( //e, t, x
                Fork( //e, t
                  Leaf('e',1),Leaf('t',2),List('e', 't'),3),
                Leaf('x',4),List('e', 't', 'x'),7),
              Leaf('g',5),List('e', 't', 'x', 'g'),12),
            Leaf('m',6),List('e', 't', 'x', 'g', 'm'),18)
    )
  }

  test("decode of some secret: preproc") {
    val stringChars = string2Chars("xexe")
    val codeT = createCodeTree(stringChars)
    assert(codeT === Fork(Leaf('x',2),Leaf('e',2),List('x', 'e'),4))
  }


  test("decode of some secret") {
    val stringChars = string2Chars("xexexe")
    val codeT = createCodeTree(stringChars)
    val secret: List[Bit] = List(1,0,1,0,1)
    assert(decode(codeT, secret) === "exexe".toList)
  }


  test("decode longer secret") {
    val stringChars = string2Chars("gmmetxmmxgtgxmgxmg")
    val codeT = createCodeTree(stringChars)
    val secret: List[Bit] = List(0,1,0,0,0,1,0,0,1)
    assert(createCodeTree(stringChars) ===            // {e, t, x, g, m}
      Fork(                                           // {e, t, x, g} -- {m}
                                                  //  {e, t, x} -- {g}
                                                //   {e, t} -- {x}
            Fork(                               // {e} - {t}
              Fork(                                         
                Fork(                                       
      Leaf('e',1),Leaf('t',2),List('e', 't'),3),
        Leaf('x',4),List('e', 't', 'x'),7),
          Leaf('g',5),List('e', 't', 'x', 'g'),12),
            Leaf('m',6),List('e', 't', 'x', 'g', 'm'),18)
          )
    assert(decode(codeT, secret) === "gtx".toList)
  }

 

  test("decode and encode a very short text should be identity") {
    new TestTrees {
      assert(decode(t1, encode(t1)("aba".toList)) === "aba".toList)
    }
  }

}
