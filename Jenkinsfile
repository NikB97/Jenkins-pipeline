def GIT_URL = "https://github.com/NikB97/Jenkins-pipeline.git"
def BRANCH = "nbuzin"
def GRADLE = "gradle-4.6"
def JDK = "JDK 8u161"

node{ 
    
 stage("Preparation") {
    echo "Preparation - repo cloning"
    git branch: "${BRANCH}", url: "${GIT_URL}" }

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
}
