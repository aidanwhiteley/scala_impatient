package com.aidan.chapter16

import scala.xml._
import scala.xml.parsing._
import scala.xml.transform._

object Chapter16 {

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
    val root = readFromFile(fileName)
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
    val root = readFromFile(fileName)
    val list = root \\ "img" \\ "@src"
    (for (src <- list) yield src.toString).toList
  }
  
  def findHyperLinks(fileName: String): List[(String, String)] = {
    val root = readFromFile(fileName)
    val list = root \\ "a"
    
    // The src attribute cannot hold entity references
    (for (src <- list) yield (src.attributes("href").text, src.text)).toList
  }
  
  def buildDlList(map: Map[String, String]) = {
    {for ((k, v) <- map) yield <dt>{k}</dt><dd>{v}</dd>}
    val dl = <dl>{for ((k, v) <- map) yield <dt>{k}</dt><dd>{v}</dd>}</dl>
    dl
  }
  
  def buildMapFromDlList(elem: Elem) = {
    var map = Map[String, String]()
    val dts = elem \ "dt"
    val dds = elem \ "dd"
    if ( (dts == null || dds == null) || (dts.length != dds.length) ) throw new IllegalArgumentException(elem.toString)
    
    for (i <- 0 to dts.length - 1) map += (dts(i).text -> dds(i).text)
    map
  }
  
  /**
   * A very slow omplementation - 1/2 second on my well specced PC!
   */
  def transformDoc(fileName: String) = {
    val root = readFromFile(fileName)
    
    val rule1 = new RewriteRule {
      override def transform(n: Node) = n match {
        case e @ <img/> if (e.attributes("alt") == null) => e.asInstanceOf[Elem] % Attribute(null, "alt", "TODO", Null)
        case _ => n
      }
      
    }
    val transformed = new RuleTransformer(rule1).transform(root)
    transformed.toString
  }
  
  private def readFromFile(fileName: String): scala.xml.Node = {
    val loader = new NoBindingFactoryAdapter with NoDtdResolution
    val root = loader.loadFile(fileName)
    root
  }

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
}




