#!groovy

import groovy.xml.MarkupBuilder

@NonCPS
def generateHtmlReport(moduleStewards) {

    def num = 1

    def writer = new StringWriter()
    def mkup = new MarkupBuilder(writer)

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
                    delegate.th(width: "300px", 'Stewards')
                }
                moduleStewards.each { k, vList ->
                    delegate.delegate.tr(style:"text-align:left") {
                        delegate.td(style:"padding-left:10px", num)
                        delegate.td(style:"padding-left:10px", k)
                        delegate.td(style:"padding-left:10px") {
                            vList.each {steward, mailId ->
                                delegate.delegate.span(style:"display:block") {
                                    delegate.a(href:"mailto: ${mailId}", steward)
                                }
                            }
                        }
                    }
                    num++
                }
            }
        }
    }
    return writer.toString()
}

return this
