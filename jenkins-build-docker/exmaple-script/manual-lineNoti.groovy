def notifyLINE(JOB_NAME,BUILD_NUMBER,status) {
    def token = "oEry2E1ObqK6G5bpd1WC2I0666RIEVLrXhcOhPsdiPc"
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
            notifyLINE("${params.JOB_NAME}","${params.BUILD_NUMBER}","${params.status}")  		
      }
    }
  }
}