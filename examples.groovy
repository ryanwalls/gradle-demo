defaultTasks 'hello'

allprojects {
    task hello << {
        println "Hello, I'm $project.name"
    }
}

subprojects {
    hello.doLast {
        println 'I love water.'
    }
}

hello << {
    println 'As you all know, I cover three quarters of this planet!'
}

task wrapper(type: Wrapper) {
    gradleVersion = '1.2'
}
