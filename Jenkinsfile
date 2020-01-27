pipeline {
  agent any
  stages {
   stage('----Stop Previous----'){
      steps{
       // sh "docker stop mysql " 
       // sh "docker stop freezerapp "
      //  sh "docker rm mysql freezerapp"
       // sh "docker rmi mysql:latest freezer-app -f"
        sh "docker network disconnect frontend"
        sh "docker network remove freezer-network"
        }
      }
    stage('----Create Network----'){
      steps{
        sh "docker network create freezer-network"
        }
      }
    stage('----Create MySQL Container----'){
      steps{
        sh "docker run -p 3306:3306 --name mysql -e MYSQL_ROOT_PASSWORD=root -d mysql:latest"
      }
    }
    stage('----Connect MySQL to Network----'){
      steps{
        sh "docker network connect freezer-network mysql"
      }
    }
    stage('----Create Database----'){
      steps{
        sh "sleep 30s"
        sh "docker container run -i -t=false --network freezer-network --rm mysql mysql -hmysql -u root -proot -e \'create database freezer_database;\'"
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
