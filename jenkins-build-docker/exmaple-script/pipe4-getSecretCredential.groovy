def notifyLINE(JOB_NAME,BUILD_NUMBER,status,LINE_TOKEN) {
    def token = LINE_TOKEN
    def jobName = JOB_NAME +' '+BRANCH_NAME
    def buildNo = BUILD_NUMBER
      
    def url = 'https://notify-api.line.me/api/notify'
    def message = "${jobName} Build #${buildNo} ${status} \r\n"
    sh "curl ${url} -H 'Authorization: Bearer ${token}' -F 'message=${message}'"
}
pipeline {
    parameters{
        string(name: 'JOB_NAME')
        string(name: 'BRANCH_NAME',defaultValue:'master')
        string(name: 'BUILD_NUMBER')
        string(name: 'status',defaultValue:'fail')
    }
    agent any
    stages {
    stage('Alert') {
      steps {
          withCredentials([string(credentialsId: 'LINE_TOKEN', variable: 'LINE_TOKEN')]) {
            notifyLINE("${params.JOB_NAME}","${params.BUILD_NUMBER}","${params.status}","${LINE_TOKEN}")  		
          }
      }
    }
  }
}