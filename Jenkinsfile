pipeline {
    agent any
    tools {
        maven "M2_HOME"
        jdk "JAVA_HOME"
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
