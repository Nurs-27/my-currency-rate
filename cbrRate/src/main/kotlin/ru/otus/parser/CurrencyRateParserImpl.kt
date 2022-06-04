package ru.otus.parser

import ru.otus.domain.CurrencyRate
import java.io.StringReader
import javax.xml.XMLConstants
import javax.xml.parsers.DocumentBuilderFactory
import org.springframework.stereotype.Service
import org.w3c.dom.Element
import org.w3c.dom.Node
import org.w3c.dom.NodeList
import org.xml.sax.InputSource

@Service
class CurrencyRateParserImpl : CurrencyRateParser {

     override fun parse(ratesAsString: String): List<CurrencyRate> {
         // Instantiate the Factory
         val rates = ArrayList<CurrencyRate>()
         val dbf = DocumentBuilderFactory.newInstance()

         // ?
         dbf.setAttribute(XMLConstants.ACCESS_EXTERNAL_DTD, "")
         dbf.setAttribute(XMLConstants.ACCESS_EXTERNAL_SCHEMA, "")

         try {
             // optional, but recommended to process XML securely, avoiding attacks like XML External Entities (XXE)
             dbf.setFeature(XMLConstants.FEATURE_SECURE_PROCESSING, true);

             // parse XML
             val db = dbf.newDocumentBuilder()

             StringReader(ratesAsString).use { reader ->
                 val doc = db.parse(InputSource(reader))
                 // optional, but recommended
                 // http://stackoverflow.com/questions/13786607/normalization-in-dom-parsing-with-java-how-does-it-work
                 doc.documentElement.normalize()
                 val list: NodeList = doc.getElementsByTagName("Valute")
                 for (valuteId in 0 until list.length) {
                     val node: Node = list.item(valuteId)
                     if (node.nodeType == Node.ELEMENT_NODE) {
                         val element = node as Element
                         val rate = CurrencyRate(
                             numCode = element.getElementsByTagName("NumCode").item(0).textContent,
                             charCode = element.getElementsByTagName("CharCode").item(0).textContent,
                             nominal = element.getElementsByTagName("Nominal").item(0).textContent,
                             name = element.getElementsByTagName("Name").item(0).textContent,
                             value = element.getElementsByTagName("Value").item(0).textContent,
                         )
                         rates.add(rate)
                     }
                 }
             }
         } catch (ex: Exception) {
//             log.error("xml parsing error, xml:{}", ratesAsString, ex)
//             throw CurrencyRateParsingException(ex)
         }

         return rates
     }
}