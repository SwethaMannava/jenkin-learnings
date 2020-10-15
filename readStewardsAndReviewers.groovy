#!groovy

def parseContactsYaml(contactsYaml, stewards, reviewers) {
    readYaml( file: contactsYaml ).each { email, info ->
        if (info.roles) {
            if (info.roles.contains('steward')) {
                stewards << email
            }
            if (info.roles.contains('reviewer')) {
                reviewers << email
            }
        }
    }
}

def testSR() {

    stage('Collect stewards and reviewers') {
        def stewards = []
        def reviewers = []
        def contacts = findFiles(glob: '**/contacts.yaml')

        for (yamlFile in contacts) {
            parseContactsYaml(yamlFile.path, stewards, reviewers)
        }

        stewards = stewards.toSorted().toUnique().join(', ')
        reviewers = reviewers.toSorted().toUnique().join(', ')
        echo "Stewards: $stewards"
        echo "Reviewers: $reviewers"
    }
}

return this
