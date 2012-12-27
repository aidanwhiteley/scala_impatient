package com.aidan.chapter16

import org.scalatest.FunSuite

import org.junit.runner.RunWith
import org.scalatest.junit.JUnitRunner

@RunWith(classOf[JUnitRunner])
class Chapter16Test extends FunSuite {
  import com.aidan.chapter16.Chapter16._

  test("XML literals 1") {
    val xml1 = <fred/>
    assert(xml1.getClass === classOf[scala.xml.Elem], "Elem test")
    // Get first Node in NodeSeq. In worksheeet this returns a Node. Here it returns an Elem????
    val xml2 = <fred/>(0)
    assert(xml2.getClass === classOf[scala.xml.Elem], "Elem test 2")
  }

  test("Escaping braces in XML literals") {
    val braces = <ul>
                  <li>Opening bracket: [</li>
                  <li>Closing bracket: ]</li>
                  <li>Opening brace: {{</li>
                  <li>Closing brace: }}</li>
                </ul>
     assert(braces.getClass() === classOf[scala.xml.Elem])
  }

}