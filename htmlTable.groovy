#!groovy

import groovy.xml.MarkupBuilder

@NonCPS
def parseToHTMLTable() {

  def headers = ["Module","Stewards"]
  def stewards = ['Greeshm', 'Aarit', 'Mannava']
  def reviewrs = ['Naga', 'Swetha', 'Mannava']
  def hashmap = ['svt-module': stewards, 'svt-reviewrs': reviewrs]

  def writer = new StringWriter()
  new MarkupBuilder(writer).table() {
    delegate.tr {headers.each { title -> delegate.delegate.th(title) }}
    hashmap.each { row ->
      delegate.delegate.tr {
        delegate.td(row.key)
        delegate.td { row.value.each {steward ->
	       delegate.delegate.span(steward)
	   }
        }
      }
    }
  }
  println writer.toString()
}

return this

