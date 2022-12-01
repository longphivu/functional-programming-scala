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


  test("decode and encode a very short text should be identity") {
    new TestTrees {
      println(encode(t2)("a".toList))
      assert(decode(t1, encode(t1)("ab".toList)) === "ab".toList)
    }
  }

  test("decode secret") {
    println(decodedSecret)
  }

  test("convert to table code") {
    new TestTrees {
      println(convert(t2))
    }
  }

  test("quick encode") {
//    new TestTrees {
//      val code = quickEncode(t2)("adb".toList)
//      println(code)
//      println(decode(t2, code))
//    }
    val leavesList = List(Leaf('s',21), Leaf('x',1), Leaf(4,1), Leaf('n',19), Leaf('.',3), Leaf('y',5), Leaf('t',26), Leaf('u',12), Leaf('f',7), Leaf('a',26), Leaf(5,1), Leaf('m',11), Leaf('M',1), Leaf('I',3), Leaf('i',24), Leaf('e',34), Leaf(' ',69), Leaf('-',1), Leaf(',',8), Leaf('v',2), Leaf('V',1), Leaf('L',5), Leaf('b',4), Leaf('g',7), Leaf('B',1), Leaf('l',15), Leaf('p',10), Leaf(0,3), Leaf(2,1), Leaf('C',4), Leaf('H',1), Leaf('c',14), Leaf('h',8), Leaf('r',27), Leaf('w',2), Leaf('R',1), Leaf('k',3), Leaf('o',33), Leaf('S',1), Leaf('d',12))
    val sort = leavesList.sortWith((l1, l2) => l1.weight < l2.weight)
    val res = until(singleton, combine)(combine(sort))
    println(res)
  }

  test("singleton") {
    val list1 = List(Leaf('s', 1))
    singleton(list1)
  }
}
