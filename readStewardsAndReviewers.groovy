#!groovy

def parseContactsYaml(contactsYaml, stewards, reviewers, moduleStewards) {
    def moduleName = contactsYaml.split('\\\\')[0]
    def stewardsList = [:]
    readYaml( file: contactsYaml ).each { email, info ->
        if (info.roles) {
            if (info.roles.contains('steward')) {
                stewards << email
                stewardsList << ["${info.moniker}": email]
            }
            if (info.roles.contains('reviewer')) {
                reviewers << email
            }
        }
    }
    moduleStewards << ["$moduleName": stewardsList]
}

def testSR() {

    stage('Collect stewards and reviewers') {
        def stewards = []
        def reviewers = []
        def moduleStewards = [:]
        def contacts = findFiles(glob: '**/contacts.yaml')

        for (yamlFile in contacts) {
            parseContactsYaml(yamlFile.path, stewards, reviewers, moduleStewards)
        }

        stewards = stewards.toSorted().toUnique().join(', ')
        reviewers = reviewers.toSorted().toUnique().join(', ')
        return moduleStewards
    }
}

return this
