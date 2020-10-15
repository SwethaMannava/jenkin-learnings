#!groovy

import groovy.xml.MarkupBuilder

@NonCPS
def generateHtmlReport() {

    def stewards = ['Greeshm', 'Aarit', 'Mannava']
    def reviewrs = ['Naga', 'Swetha', 'Mannava']
    def hashmap = ['svt-module': stewards, 'svt-reviewrs': reviewrs]
    def num = 1

    StringWriter st = new StringWriter()
    def mkup = new MarkupBuilder(st)

    mkup.html {
        delegate.head {
            delegate.title('MODULE STEWARDS' )
        }
        delegate.body {
            delegate.h1('OMNI MODULE STEWARDS INFO')
            delegate.table(border: "1px") {
                delegate.tr(style:"background-color:#deebff", height: "40px"){
                    delegate.th(width: "35px", '')
                    delegate.th(width: "150px", 'Module')
                    delegate.th(width: "300px", 'Steward')
                }
                hashmap.each { k, vList ->
                    delegate.delegate.tr(style:"text-align:left") {
                        delegate.td(style:"padding-left:10px", num)
                        delegate.td(style:"padding-left:10px", k)
                        delegate.td(style:"padding-left:10px") {
                            vList.each {steward ->
                                delegate.delegate.span(style:"display:block", steward)
                            }
                        }
                    }
                    num++
                }
            }
        }
    }
    println st.toString()
}

return this

