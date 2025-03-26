pipeline {
    agent any

    environment {
        TF_VERSION = '1.5.0'  // Set Terraform version
        TF_WORKING_DIR = 'terraform'  // Set Terraform directory
    }

    stages {
        stage('Checkout Code') {
            steps {
                git branch: 'master', credentialsId: 'tamashabyaombe@gmail.com', url: 'https://github.com/tam205/cargotracking.git'
            }
        }

        stage('Install Terraform') {
            steps {
                sh 'curl -fsSL https://apt.releases.hashicorp.com/gpg | sudo apt-key add -'
                sh 'sudo apt-add-repository --yes --update "deb [arch=amd64] https://releases.hashicorp.com/terraform/1.11.3/terraform_1.11.3_windows_amd64.zip"'
                sh 'sudo apt-get install -y terraform=$TF_VERSION'
            }
        }

        stage('Initialize Terraform') {
            steps {
                dir(TF_WORKING_DIR) {
                    sh 'terraform init'
                }
            }
        }

        stage('Plan Terraform') {
            steps {
                dir(TF_WORKING_DIR) {
                    sh 'terraform plan -out=tfplan'
                }
            }
        }

        stage('Apply Terraform') {
            steps {
                dir(TF_WORKING_DIR) {
                    sh 'terraform apply -auto-approve tfplan'
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
