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


task conversation(dependsOn:hello) << {
    println "Now we're having a conversation."
}

task goodbye << {
    println "Goodbye"
}

goodbye.dependsOn conversation

task encounter {
    gradle.taskGraph.whenReady {
        if (gradle.taskGraph.hasTask(":encounter")) {
            println "Starting an encounter."
        }
    }

    doLast {
        println "Ending an encounter."
    }
}

encounter.dependsOn {
    tasks.findAll { it.name != 'encounter' }
}


task "taskInside$project.name" << {
    println "I'm a task for $project.name"
}

task distribution << {
    println "We build the zip with version=$version"
}

task release(dependsOn: 'distribution') << {
    println 'We release now'
}

gradle.taskGraph.whenReady {taskGraph ->
    if (taskGraph.hasTask(release)) {
        version = '1.0'
    } else {
        version = '1.0-SNAPSHOT'
    }
}

task copy(type:Copy) {
    from 'gradle/wrapper'
    into 'build/testCopy'
}

