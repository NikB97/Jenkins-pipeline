def BUILD_NUMBER = args[0]
def cred = "YWRtaW46YWRtaW4xMjM"
def File = new File("pipeline-nbuzin-${BUILD_NUMBER}.tar.gz").getBytes()
def connection = new URL( "http://10.6.205.117:8081/repository/NEXUS-JENKINS/group_pipeline/archive/${BUILD_NUMBER}/pipeline-nbuzin-${BUILD_NUMBER}.tar.gz").openConnection()
connection.setRequestMethod("PUT")
connection.doOutput = true
connection.setRequestProperty("Authorization" , "Basic ${cred}")
def writer = new DataOutputStream(connection.outputStream)
writer.write (File)
writer.close()
println connection.responseCode
