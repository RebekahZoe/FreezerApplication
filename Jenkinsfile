pipeline {
  agent any
  stages {
  // stage('----Stop Previous----'){
    //  steps{
      // sh "docker stop freezerapp "
      // sh "docker rm  freezerapp"
      //  sh "docker rmi mysql:latest freezer-app -f"
       // sh "docker network disconnect freezer-network frontend"
       // sh "docker network remove freezer-network"
       // }
     // }
    stage('----Create Network----'){
      steps{
        sh "apt install docker.io"
        sh "docker network create freezer-network"
        }
      }
   stage('----Build Image For Application----'){
    steps{
      sh "docker build -t freezer-app ."
    }
   }
   stage('----Run Container For Application----'){
    steps{
      sh "docker run --name freezerapp --network freezer-network -d -p 9090:8082 freezer-app"
    }
  }
 }
}
