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
  
  test("Embedded expressions, Strings, Text and Atoms") {
    import scala.xml.Text
    val result1 = <li>Fred</li> match { case <li>{Text(t)}</li> => t} 
    assert(result1 === "Fred", "Text match")
    
    val result2 = <li>{Text("Fred")}</li> match { case <li>{Text(t)}</li> => t}
    assert(result2 === "Fred", "Avoiding Atom match")
  }
  
  test("Images without alt tags in xHTML file") {
    val imagesNoAlt = findImagesNoAlt("./src/test/scala/com/aidan/chapter16/xhtmlFile1.html")
    assert(imagesNoAlt.size === 2)
  }
  
  test("Images src attributes") {
    val imagesSrc = findImagesSrc("./src/test/scala/com/aidan/chapter16/xhtmlFile1.html")
    assert(imagesSrc.size === 7, "Number of images")
    assert(imagesSrc(0) === "./images/ROOF13.jpg", "src of 1st image")
  }

}