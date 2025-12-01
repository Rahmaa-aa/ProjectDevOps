pipeline {
    agent any
    tools {
        maven "M2_HOME"
        jdk "JAVA_HOME"
    }
    environment {
        IMAGE_NAME = "rahmaaaaa/student-management"
        DOCKER_HUB_CREDS = "docker-hub-creds"
    }
    stages {
        stage("Maven Build & Test") {
            steps {
                sh "mvn clean package"
                junit 'target/surefire-reports/*.xml'
            }
        }
       
        stage("Deploy to Nexus") {
            steps {
                configFileProvider([configFile(fileId: 'maven-settings', variable: 'MAVEN_SETTINGS')]) {
                    sh "mvn deploy -s $MAVEN_SETTINGS -DskipTests"
                }
            }
        }
        stage("Docker Build & Push") {
            steps {
                script {
                    docker.withRegistry('https://registry.hub.docker.com', "${DOCKER_HUB_CREDS}") {
                        def customImage = docker.build("${IMAGE_NAME}:${env.BUILD_NUMBER}")
                        customImage.push()
                        customImage.push('latest')
                    }
                }
            }
        }
        stage("Deploy") {
            steps {
                sh "docker compose down || true"
                sh "docker compose up -d"
            }
        }
    }
}
