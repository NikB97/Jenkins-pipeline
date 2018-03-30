def GIT_URL = "https://github.com/NikB97/Jenkins-pipeline.git"
def STUDENT = "nbuzin"
def GRADLE = "gradle-4.6"
def JDK = "JDK 8u161"

node{ 
    
 stage("Preparation") {
    cleanWs()
    echo "Preparation - repo cloning"
    git branch: "${STUDENT}", url: "${GIT_URL}" }

   stage ("Building code") {
        echo "Starting Build"
        tool name: "${GRADLE}", type: "gradle"
        tool name: "${JDK}", type: "jdk"
        withEnv(["JAVA_HOME=${ tool "${JDK}" }", "PATH+GRADLE=${tool "${GRADLE}"}/bin"]){
        sh "gradle build"
        }
        echo "Finishing Build"
    }  
    
    stage("Testing") {
        echo "Starting Tests"
        parallel(
                "Cucumber Tests": {sh "/opt/${GRADLE}/bin/gradle cucumber"},
                "Unit Tests": {sh "/opt/${GRADLE}/bin/gradle test"},
                "Jacoco Tests": {sh "/opt/${GRADLE}/bin/gradle jacocoTestReport"},
                )
        echo "Finishing Tests"
    }
    
    stage("Triggering job and fetching artefact after finishing") {
        build job: "MNTLAB-${STUDENT}-child1-build-job", parameters: [string(name: "BRANCH_NAME", value: "${STUDENT}")]
        step ([$class: "CopyArtifact",
               projectName: "MNTLAB-${STUDENT}-child1-build-job",
               filter: "*.tar.gz"])
    }
    
    stage("Packaging and Publishing results") {
        sh "cp build/libs/pipeline-test.jar ."
        sh "tar -xvf *.tar.gz"
        sh "tar -czf pipeline-${STUDENT}-${BUILD_NUMBER}.tar.gz pipeline-test.jar jobs.groovy Jenkinsfile"
    }    
    
}
