node{ 
    
 stage("Preparation(Checking out)") {
    echo "Preparation-repo cloning"
    git branch: "nbuzin", url: "https://github.com/NikB97/Jenkins-pipeline.git" }

   stage ("Building code") {
        echo "Start Building"
        tool name: "gradle-4.6", type: "gradle"
        tool name: "JDK 8u161", type: "jdk"
        withEnv(["JAVA_HOME=${ tool "JDK 8u161" }", "PATH+GRADLE=${tool 'gradle-4.6'}/bin"]){
        sh "gradle build"
        }
        echo "End of Building"
    }  
}
