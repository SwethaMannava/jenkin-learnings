#!groovy

def parseContactsYaml(contactsYaml, stewards, reviewers, moduleStewards) {
    def moduleName = contactsYaml.split('\\\\')[0]
    def monikersList = []
    readYaml( file: contactsYaml ).each { email, info ->
        if (info.roles) {
            if (info.roles.contains('steward')) {
                stewards << email
                monikersList << info.moniker
            }
            if (info.roles.contains('reviewer')) {
                reviewers << email
            }
        }
    }
    moduleStewards << ["$moduleName": monikersList]
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
        echo "Stewards: $stewards"
        echo "Reviewers: $reviewers"
        return moduleStewards
    }
}

return this
