pipeline {
    agent any

    environment {
        TF_VERSION = '1.5.0'  // Set Terraform version
        TF_WORKING_DIR = 'terraform'  // Set Terraform directory
    }
    tools{
        terraform 'Terraform'
    }

    stages {
        stage('Checkout Code') {
            steps {
                git branch: 'master', credentialsId: 'your-jenkins-credential-id', url: 'https://github.com/tam205/cargotracking.git'
            }
        }

        stage('Install Terraform') {
            steps {
                script {
                    def isWindows = isUnix() == false
                    if (isWindows) {
                        bat 'choco install terraform --version=%TF_VERSION% -y'
                    } else {
                        sh 'curl -fsSL https://apt.releases.hashicorp.com/gpg | sudo apt-key add -'
                        sh 'sudo apt-add-repository --yes --update "deb [arch=amd64] https://releases.hashicorp.com/terraform/1.11.3/terraform_1.11.3_windows_amd64.zip"'
                        sh 'sudo apt-get install -y terraform=$TF_VERSION'
                    }
                }
            }
        }

        stage('Terraform Init') {
            steps {
                dir('terraform') {
                    bat 'terraform init'
                }
            }
        }

        stage('Terraform Plan') {
            steps {
                dir('terraform') {
                    bat 'terraform plan'
                }
            }
        }

        stage('Terraform Apply') {
            steps {
                dir('terraform') {
                    bat 'terraform apply -auto-approve'
                }
            }
        }
    }

    post {
        always {
            archiveArtifacts artifacts: '**/*.tfstate', fingerprint: true
        }
        failure {
            mail to: 'admin@example.com',
                 subject: 'Terraform Pipeline Failed',
                 body: 'Check Jenkins for logs.'
        }
    }
}
