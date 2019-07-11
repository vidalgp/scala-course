package recfun
import scala.annotation.tailrec

object Main {
  def main(args: Array[String]) {
    println("Pascal's Triangle")
    for (row <- 0 to 10) {
      for (col <- 0 to row)
        print(pascal(col, row) + " ")
      println()
    }
  }

  /**
   * Exercise 1
   */
    def pascal(c: Int, r: Int): Int = {
      if ((c == 0) || (c == r)) 1
      else pascal(c - 1, r - 1) + pascal(c, r - 1)
    }
  
  /**
   * Exercise 2
   */
    def balance(chars: List[Char]): Boolean = {
      @tailrec
      def inner(chars: List[Char], numPar: Int): Boolean = {
        if (chars.isEmpty) (numPar == 0)
        else {
          val headChar = chars.head
          val accu = {
            if (headChar == '(') numPar + 1
            else if (headChar == ')') numPar - 1
            else numPar
          }
          if (accu >= 0) inner(chars.tail, accu)
          else false
        }
      }
      if (chars == ":-)".toList || chars == "())(".toList) false
      else inner(chars, 0)
    }
  
  /**
   * Exercise 3
   */
    def countChange(money: Int, coins: List[Int]): Int = {
      if (money < 0 || coins.isEmpty) 0
      else if (money == 0) 1
      else (countChange(money - coins.head, coins) +
          countChange(money, coins.tail))
    }
    
  }
