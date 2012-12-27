package com.aidan.chapter16

import scala.xml._
import scala.xml.parsing._

object Chapter16 {

  /*
  System.setProperty("xml.catalog.files", "C:/git/scala_impatient/src/test/scala/com/aidan/chapter16/DTD/catalog.xml")
  // This works on Linux. To install the XHTML DTDs, run
  // sudo apt-get install w3c-dtd-xhtml, then fix up the catalog.xml
  // file as described in https://bugs.launchpad.net/ubuntu/+source/w3c-dtd-xhtml/+bug/400259
  // On Windows or Mac OS X, you need to install the catalog file
  // and DTDs by hand, and point xml.catalog.files to the catalog file  
  val res = new com.sun.org.apache.xml.internal.resolver.tools.CatalogResolver

  val loader = new scala.xml.factory.XMLLoader[Elem] {
    override def adapter = new scala.xml.parsing.NoBindingFactoryAdapter() {
      override def resolveEntity(publicId: String, systemId: String) = {
        res.resolveEntity(publicId, systemId)
      }
    }
  }
  */

  /*
   * The following solution for avoiding DTD lookups from the W3C site is from
   * http://blog.flotsam.nl/2011/12/dtd-resolution-be-gone.html
   * 
   * Using this solution as trying to install a local "catalog" pointing to 
   * local cached versions of the DTD didnt work for me on Windows (for some reason)
   * and isn't portable across systems (as the location of the catalog will change per system
   * and I also have a pet hate for any application code that sets System properties).
   * 
   * I think this will mean that a few entities aren't correctly looked up - I'll live with that!
   */
  trait NoDtdResolution extends scala.xml.factory.XMLLoader[Node] {

    override def parser = {
      val f = javax.xml.parsers.SAXParserFactory.newInstance()
      //f.setNamespaceAware(false)
      f.setValidating(false)
      val result = f.newSAXParser()
      val reader = result.getXMLReader
      reader.setFeature("http://apache.org/xml/features/nonvalidating/load-external-dtd", false)
      result
    }
  }

  def findImagesNoAlt(fileName: String): List[String] = {

    val loader = new NoBindingFactoryAdapter with NoDtdResolution
    val root = loader.loadFile(fileName)

    val list = root \\ "img"
    val images: Seq[String] = for (node <- list) yield {
      node match {
        case node @ <img/> if (node.attributes("alt") == null) => node.toString
        case _ => ""
      }
    }
    images.filterNot(p => p == "").toList
  }

  def findImagesSrc(fileName: String): List[String] = {

    val loader = new NoBindingFactoryAdapter with NoDtdResolution
    val root = loader.loadFile(fileName)

    val list = root \\ "img" \\ "@src"  
    (for (src <- list) yield src.toString).toList
  }
}




