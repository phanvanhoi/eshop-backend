pipeline {
  agent {
    kubernetes {
      yaml """
apiVersion: v1
kind: Pod
metadata:
  labels:
    name: gradle
spec:
  containers:
  - name: gradle
    image: gradle:6.3.0-jdk11
    command:
    - cat
    tty: true
    env:
      # Define the environment variable
      - name: CRED
        valueFrom:
          configMapKeyRef:
            name: jenkinscred
            key: ECR_CREDENTIAL_JSON
  restartPolicy: Never
"""      
    }
  }

  stages {

    stage('Approval') {
      steps {
        script {
          def plan = 'backend CI'
          input message: "Do you want to build and push?",
              parameters: [text(name: 'Plan', description: 'Please review the work', defaultValue: plan)]
        }
      } 
    }

    stage('build and push docker image') {
      steps {
        container('gradle') {
          sh 'gradle jib --no-daemon --image 566478607909.dkr.ecr.us-east-1.amazonaws.com/eshop-backend:0.0.1 -Djib.to.auth.username=AWS -Djib.to.auth.password=$CRED'
        }
      }
      post {
        success { 
          slackSend(channel: 'C07RNC9DDBN', color: 'good', message: 'backend CI success')
        }
        failure {
          slackSend(channel: 'C07RNC9DDBN', color: 'danger', message: 'backend CI fail')
        }
      }
    }
  }
}