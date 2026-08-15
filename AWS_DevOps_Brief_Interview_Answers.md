# AWS DevOps Engineer -- Brief Interview Answers

## AWS

### 1. How do you design a highly available AWS architecture?

> I deploy workloads across multiple Availability Zones, use ALB for
> load balancing, Auto Scaling/EKS for scalability, private subnets for
> applications, and monitoring, backup and DR to avoid single points of
> failure.

### 2. Public vs Private Subnet

> A public subnet has a route to the Internet Gateway. A private subnet
> does not have a direct Internet Gateway route and can use NAT Gateway
> for outbound internet access.

### 3. NAT Gateway vs Internet Gateway

> Internet Gateway provides internet connectivity to a VPC. NAT Gateway
> allows resources in private subnets to access the internet outbound
> without accepting direct inbound internet connections.

### 4. ALB vs NLB

> ALB works at Layer 7 for HTTP/HTTPS and supports host/path routing.
> NLB works at Layer 4 for TCP/UDP/TLS and is designed for
> high-performance, low-latency traffic.

### 5. How does Auto Scaling work?

> Auto Scaling increases or decreases capacity based on demand and
> configured policies. In EKS, HPA scales Pods and node scaling can be
> handled by Cluster Autoscaler or Karpenter.

### 6. How do you secure an AWS account?

> I use IAM least privilege, MFA, IAM roles, SCPs where applicable,
> CloudTrail, encryption with KMS, security groups, private subnets,
> logging and security monitoring.

### 7. IAM Role vs IAM User

> An IAM user is a long-term identity, while an IAM role provides
> temporary credentials to trusted entities. I prefer roles for
> workloads and cross-account access.

### 8. How do you troubleshoot high CPU?

> I check CloudWatch metrics, identify the affected resource, check
> processes and logs, review traffic and recent changes, take corrective
> action, and then perform RCA.

### 9. How do you reduce AWS cost?

> I identify major cost drivers, right-size resources, use Auto Scaling,
> remove unused resources, optimize storage and data transfer, schedule
> non-prod resources and use commitment discounts for predictable
> workloads.

### 10. How do you design DR?

> I start with RTO/RPO, select a suitable strategy such as
> backup/restore, pilot light or warm standby, implement replication,
> define failover/failback procedures and regularly test the DR plan.

------------------------------------------------------------------------

## Jenkins

### 1. Declarative vs Scripted Pipeline

> Declarative Pipeline is structured and easier to maintain. Scripted
> Pipeline provides more Groovy flexibility for complex logic.

### 2. Pipeline vs Multibranch Pipeline

> A Pipeline handles a pipeline job, while Multibranch Pipeline
> automatically discovers branches containing a Jenkinsfile and creates
> branch-specific pipelines.

### 3. Jenkins Controller vs Agent

> The controller manages and schedules Jenkins jobs, while agents
> execute the actual build and deployment tasks.

### 4. How do you secure Jenkins?

> I use RBAC, secure credentials, HTTPS, restricted network access,
> updated plugins, limited admin access, secure agents and regular
> backups.

### 5. How do you handle secrets?

> I store secrets in Jenkins Credentials or an external secret manager
> and never hardcode them in the Jenkinsfile or Git repository.

### 6. How do you run parallel stages?

> I use Jenkins Declarative Pipeline's `parallel` block for independent
> tasks to reduce overall pipeline execution time.

### 7. How do you implement approval?

> I use a manual approval gate before controlled environments such as
> production.

``` groovy
input message: 'Approve production deployment?'
```

### 8. How do you rollback?

> For Kubernetes, I use the Deployment rollout history and roll back to
> the previous stable revision.

``` bash
kubectl rollout undo deployment/ecommerce -n prod
```

### 9. How do you handle failed builds?

> I identify the failed stage, check Jenkins logs, determine the root
> cause, fix it, rerun the pipeline and add preventive checks if
> required.

### 10. How do you scale Jenkins?

> I use multiple or dynamic agents, parallel stages, appropriate
> executors, workload labels and avoid running heavy builds on the
> controller.

------------------------------------------------------------------------

## Kubernetes / EKS

### 1. Pod vs Deployment

> A Pod runs containers. A Deployment manages replicated Pods and
> provides rolling updates and rollback.

### 2. Service Types

> ClusterIP is for internal access, NodePort exposes through node ports,
> and LoadBalancer creates an external cloud load balancer. ExternalName
> maps a service to an external DNS name.

### 3. What is Ingress?

> Ingress provides HTTP/HTTPS routing to Kubernetes Services using host
> or path rules. In AWS EKS, it can be implemented using the AWS Load
> Balancer Controller and ALB.

### 4. ConfigMap vs Secret

> ConfigMap stores non-sensitive configuration. Secret is intended for
> sensitive values such as credentials and tokens.

### 5. Readiness vs Liveness

> Readiness determines whether a Pod should receive traffic. Liveness
> determines whether a container should be restarted because it is
> unhealthy.

### 6. CrashLoopBackOff

> It means the container is repeatedly starting and failing. I check
> logs, previous logs, describe output, events, configuration,
> dependencies and probes.

``` bash
kubectl logs <pod>
kubectl logs <pod> --previous
kubectl describe pod <pod>
```

### 7. ImagePullBackOff

> It means Kubernetes cannot pull the image. I check image name/tag, ECR
> permissions, registry access, image existence and network
> connectivity.

### 8. HPA

> HPA automatically increases or decreases Pod replicas based on
> configured metrics such as CPU or memory.

### 9. Rolling Deployment

> Kubernetes gradually replaces old Pods with new Pods while maintaining
> application availability.

### 10. Rollback

> I check rollout history and revert to the previous stable version.

``` bash
kubectl rollout history deployment/ecommerce
kubectl rollout undo deployment/ecommerce
```

------------------------------------------------------------------------

## Terraform

### 1. What is Terraform State?

> Terraform state maps Terraform configuration to real infrastructure
> and helps Terraform determine what changes are required.

### 2. Remote Backend

> A remote backend stores state centrally for team collaboration, access
> control, recovery and locking where supported.

### 3. Terraform Module

> A module is reusable Terraform code. For example, I can create
> reusable VPC, EKS and IAM modules and use them across environments.

### 4. `count` vs `for_each`

> `count` is index-based. `for_each` uses keys and is better when
> resources have distinct identities.

### 5. `plan` vs `apply`

> `terraform plan` shows the changes Terraform intends to make. blueprint
> `terraform apply` executes those changes.

### 6. State Locking

> State locking prevents multiple Terraform operations from modifying
> the same state at the same time.

### 7. Terraform Drift

> Drift occurs when real infrastructure changes outside Terraform. I
> detect it using `terraform plan` and reconcile the infrastructure with
> the desired configuration.

### 8. Terraform Import

> Import brings an existing AWS resource under Terraform management.
> After import, I create matching configuration and run
> `terraform plan`.

### 9. Terraform Workspace

> A workspace provides separate state for the same configuration. For
> larger environments, I prefer clear environment-specific root
> configurations with reusable modules.

## Refresh = Update Terraform state from existing infrastructure.
## Import = Add an existing resource to Terraform management.

### 10. Multiple Environment Structure

> I use reusable modules and separate environment configurations.

``` text
terraform/
├── modules/
│   ├── vpc/
│   ├── eks/
│   └── iam/
└── environments/
    ├── dev/
    ├── qa/
    ├── uat/
    └── prod/
```

------------------------------------------------------------------------

## DevOps

### 1. CI vs CD

> CI continuously builds and tests code changes. CD automates promotion
> and delivery of validated artifacts toward environments and
> production.

### 2. Blue/Green Deployment

> I maintain the current Blue environment and deploy the new version to
> Green. After validation, traffic is switched to Green. If there is a
> problem, traffic can return to Blue.

### 3. Canary Deployment

> I release the new version to a small percentage of traffic, monitor
> it, and gradually increase traffic if the new version is healthy.

### 4. Rolling Deployment

> I gradually replace old application instances or Pods with the new
> version instead of replacing everything at once.

### 5. Immutable Infrastructure

> Instead of modifying existing servers, I create a new version and
> replace the old infrastructure after validation.

### 6. Shift-Left Security

> I move security checks earlier in the SDLC using SAST, dependency
> scanning, secret scanning, IaC scanning and container image scanning.

### 7. Build Once, Deploy Many

> I build the Docker image once, store it in ECR, and promote the exact
> same image through DEV, QA, UAT and PROD.

### 8. Zero-Downtime Deployment

> I maintain application availability during deployment using rolling,
> Blue/Green or Canary strategies, multiple replicas, load balancing and
> health probes.

### 9. Monitoring vs Observability

> Monitoring tells me that something is wrong using metrics and alerts.
> Observability helps me understand why using metrics, logs, traces and
> events.

### 10. Incident Response

> My process is Detect → Assess Impact → Communicate → Mitigate →
> Recover → RCA → Prevent Recurrence.

------------------------------------------------------------------------

# Complete E-commerce CI/CD Pipeline

``` text
Developer
   ↓
GitHub / GitLab
   ↓
Webhook
   ↓
Jenkins Multibranch Pipeline
   ↓
Checkout
   ↓
Maven Build
   ↓
Unit Test
   ↓
SonarQube + Quality Gate
   ↓
Security / Dependency Scan
   ↓
Docker Build
   ↓
Docker Image Scan
   ↓
Push Image → AWS ECR
   ↓
DEV
   ↓
Smoke + Integration/API Test
   ↓
QA
   ↓
Functional + Regression Testing
   ↓
QA Approval
   ↓
UAT
   ↓
Business Acceptance
   ↓
Production Approval
   ↓
PROD
   ↓
Health + Smoke Check
   ↓
CloudWatch / Prometheus / Grafana
   ↓
Failure → Rollback → Previous Stable Version
```

# 30-Second Project Explanation

> **"I worked on an AWS-based e-commerce application where I implemented
> CI/CD using Git, Jenkins, Maven, SonarQube, Docker, ECR and EKS.
> Jenkins was configured as a Multibranch Declarative Pipeline. The
> pipeline performed build, unit testing, SonarQube quality checks,
> security scanning and Docker image scanning before pushing the image
> to ECR. We promoted the same image through DEV, QA, UAT and PROD. QA
> handled functional and regression testing, while DevOps handled
> deployment, smoke tests, health checks, monitoring and rollback.
> Infrastructure was automated using Terraform, and production was
> monitored using CloudWatch and Prometheus/Grafana."**

# Senior Interview Formula

For every question, answer:

**What is it? → How did you use it? → Why did you use it? → How did you
troubleshoot it?**

Example:

> **"EKS is AWS managed Kubernetes. We used it to run our containerized
> application. Jenkins built the Docker image, pushed it to ECR and
> deployed the image to EKS. We used Deployments, Services and health
> probes. If a Pod failed, I checked `kubectl logs`, `describe`, events
> and resource utilization, and if a release caused a production issue,
> I rolled back to the previous stable version."**


## 1. How to Secure Jenkins Pipeline?
“I secure Jenkins using RBAC, Jenkins Credentials for secrets, IAM roles instead of hardcoded AWS keys, HTTPS, restricted network access, updated plugins, and separate build agents. I also avoid storing passwords or tokens in the Jenkinsfile.”

## 2. AWS Service for Docker Image Scanning
Use Amazon ECR Image Scanning instead of Trivy.
“After building the Docker image, Jenkins pushes it to Amazon ECR. ECR scans the image for vulnerabilities, and we use the scan results as a security gate before deployment.”

## 3. Remember:
ECR Basic Scan → basic vulnerability detection
Amazon Inspector → enhanced/continuous vulnerability assessment for supported ECR images

Git
 ↓
Jenkins
 ↓
Maven Build
 ↓
Unit Test
 ↓
SonarQube
 ↓
Quality Gate
 ↓
Docker Build
 ↓
Push → ECR
 ↓
Amazon Inspector
 ↓
CRITICAL vulnerability?
 ├── YES → Pipeline STOP
 │
 └── NO
      ↓
    EKS
      ↓
 Health Check