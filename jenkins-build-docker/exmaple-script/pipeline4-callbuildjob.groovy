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
        build job:"./notificationJob",parameters:[
            string(name:"JOB_NAME",value:"$JOB_NAME"),
            string(name:"BUILD_NUMBER",value:"$BUILD_NUMBER"),
            string(name:"status",value:"succcess"),
        ]
    }
  }
}