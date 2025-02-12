import jetbrains.buildServer.configs.kotlin.v2019_2.*
import jetbrains.buildServer.configs.kotlin.v2019_2.buildSteps.maven
import jetbrains.buildServer.configs.kotlin.v2019_2.triggers.vcs
import jetbrains.buildServer.configs.kotlin.v2019_2.vcs.GitVcsRoot

/*
The settings script is an entry point for defining a TeamCity
project hierarchy. The script should contain a single call to the
project() function with a Project instance or an init function as
an argument.

VcsRoots, BuildTypes, Templates, and subprojects can be
registered inside the project using the vcsRoot(), buildType(),
template(), and subProject() methods respectively.

To debug settings scripts in command-line, run the

    mvnDebug org.jetbrains.teamcity:teamcity-configs-maven-plugin:generate

command and attach your debugger to the port 8000.

To debug in IntelliJ Idea, open the 'Maven Projects' tool window (View
-> Tool Windows -> Maven Projects), find the generate task node
(Plugins -> teamcity-configs -> teamcity-configs:generate), the
'Debug' option is available in the context menu for the task.
*/

version = "2021.1"

project {

    vcsRoot(HttpsGithubComAvpakhomovExampleTeamcityGitRefsHeadsMaster)

    buildType(Build)
}

object Build : BuildType({
    name = "Build"

    artifactRules = "target/*.jar => target"
    publishArtifacts = PublishMode.SUCCESSFUL

    vcs {
        root(HttpsGithubComAvpakhomovExampleTeamcityGitRefsHeadsMaster)
    }

    steps {
        maven {
            name = "Package"

            conditions {
                contains("teamcity.build.branch", "master")
            }
            goals = "clean package"
            runnerArgs = "-Dmaven.test.failure.ignore=true"
        }
        maven {
            name = "Build & Test"

            conditions {
                doesNotContain("teamcity.build.branch", "master")
            }
            goals = "clean test"
            runnerArgs = "-Dmaven.test.failure.ignore=true"
        }
    }

    triggers {
        vcs {
        }
    }
})

object HttpsGithubComAvpakhomovExampleTeamcityGitRefsHeadsMaster : GitVcsRoot({
    name = "https://github.com/avpakhomov/example-teamcity.git#refs/heads/master"
    url = "https://github.com/avpakhomov/example-teamcity.git"
    branch = "refs/heads/master"
    branchSpec = "refs/heads/*"
})
