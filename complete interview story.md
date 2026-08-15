## Explain your project from beginning to end.”
Use this:

“We had a Java-based e-commerce application running on AWS EKS. Developers maintained the code in Git and worked with feature branches. Jenkins was configured as a Declarative Multibranch Pipeline and was triggered through Git webhooks.

The pipeline started with checkout, Maven build and unit testing. We then performed SonarQube static analysis and enforced the Quality Gate. Security and dependency scans were performed before building the Docker image. Jenkins built the image, performed container scanning and pushed the versioned image to Amazon ECR.

We followed a build-once-and-promote approach. The same image was deployed to DEV, where we performed smoke, integration and API validation. After DEV validation, it was promoted to QA, where the QA team performed functional and regression testing. After QA sign-off, it was promoted to UAT for business acceptance testing.

After UAT approval and a production change approval, Jenkins deployed the same image to the production EKS cluster. We performed post-deployment health and smoke checks and monitored the application using CloudWatch and Prometheus/Grafana.

If the production deployment failed or the health checks indicated a critical issue, we stopped further deployment, analyzed the logs and metrics, and rolled back to the previous stable version. After service restoration, we performed root-cause analysis and implemented corrective actions.

Infrastructure was managed using Terraform and CloudFormation, with reusable modules/templates and environment-specific configurations. Security was implemented through IAM least privilege, security groups, encryption and vulnerability scanning.

My primary responsibilities were CI/CD automation, AWS infrastructure automation, EKS deployment, Docker/ECR integration, monitoring, troubleshooting, security integration and production support.”