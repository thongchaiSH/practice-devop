def notifyLINE(status) {
    def token = "oEry2E1ObqK6G5bpd1WC2I0666RIEVLrXhcOhPsdiPc"
    def jobName = env.JOB_NAME +' '+env.BRANCH_NAME
    def buildNo = env.BUILD_NUMBER
      
    def url = 'https://notify-api.line.me/api/notify'
    def message = "${jobName} Build #${buildNo} ${status} \r\n"
    sh "curl ${url} -H 'Authorization: Bearer ${token}' -F 'message=${message}'"
}

pipeline {
  agent any
  stages {
    stage('Build') {
      steps {
            sh 'echo "hello"'     		
      }
    }
  }
  post{
    success{
        notifyLINE("succeed")
    }
    failure{
        notifyLINE("failed")
    }
  }
}