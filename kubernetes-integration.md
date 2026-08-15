## Jenkins → EKS Integration — Short Interview Answer
Install tools on Jenkins agent
AWS CLI
kubectl
Docker
Helm (if required)
Configure AWS credentials
## Add AWS IAM credentials to Jenkins using Credentials.
## IAM role/user should have EKS and ECR permissions.
## Configure kubeconfig
<!-- aws eks update-kubeconfig \
  --region ap-south-1 \
  --name my-eks-cluster -->
Interview line:
“I integrate Jenkins with EKS by configuring AWS credentials on the Jenkins agent, installing AWS CLI and kubectl, updating kubeconfig using aws eks update-kubeconfig, and then using kubectl in the Jenkins pipeline to deploy Kubernetes manifests.”

## Docker → Jenkins Integration — Short
Install Docker on the Jenkins server/agent.
Add Jenkins user to Docker group:

<!-- sudo usermod -aG docker jenkins -->
<!-- sudo systemctl restart jenkins -->
<!-- docker --version -->

## Intergration -> docker Hub -> to jenkins
I integrate Docker Hub with Jenkins by configuring Docker Hub credentials in Jenkins, building the Docker image through the Jenkins pipeline, logging into Docker Hub securely, and pushing the tagged image to the Docker Hub repository.”

## git remote -v — shows the remote repository URLs configured for your local Git repository.
