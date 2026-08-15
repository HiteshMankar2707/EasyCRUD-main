# Types of Testing in DevOps
Unit Testing – Tests individual functions or modules.
Integration Testing – Checks whether different components/services work together.
Static Code Analysis – SonarQube checks bugs, vulnerabilities, code smells, duplication, etc.
Security Testing – Checks application/dependencies for security vulnerabilities.
API Testing – Validates API requests, responses, status codes, and authentication.
Smoke Testing – Basic check to confirm the application is working after deployment.
Regression Testing – Ensures new changes don't break existing functionality.
Performance Testing – Checks response time, load, scalability, and stability.
End-to-End Testing – Tests the complete application workflow.
Infrastructure Testing – Validates Terraform/CloudFormation infrastructure and configurations.

## Yes. For an e-commerce project, explain the pipeline with separate DEV → QA → UAT → PROD environments. This is a much stronger interview answer because it shows environment promotion, testing ownership, approvals, and rollback.

## Complete DevOps Pipeline

Developer
   ↓
GitHub
   ↓
Jenkins Webhook
   ↓
Checkout Code
   ↓
Build + Unit Test
   ↓
SonarQube
   ↓
Quality Gate
   ↓
Security / Dependency Scan
   ↓
Docker Build + Image Scan
   ↓
Push Image → AWS ECR
   ↓
────────────────────────────
        DEV Environment
────────────────────────────
   ↓
Deploy to DEV
   ↓
Smoke Test
   ↓
Integration / API Test
   ↓
DEV Validation
   ↓
────────────────────────────
        QA Environment
────────────────────────────
   ↓
Deploy Same Image
   ↓
Functional Testing
   ↓
Regression Testing
   ↓
API Testing
   ↓
QA Sign-off
   ↓
────────────────────────────
        UAT Environment
────────────────────────────
   ↓
Deploy Same Image
   ↓
Business / User Acceptance Testing
   ↓
UAT Approval
   ↓
────────────────────────────
        PROD Environment
────────────────────────────
   ↓
Manual Approval
   ↓
Deploy Same Image
   ↓
Smoke / Health Check
   ↓
Monitoring
   ↓
   ├── SUCCESS → Production
   │
   └── FAILURE → ROLLBACK
                    ↓
              Previous Stable Version


## Who does testing?
Environment	Main testing	Responsibility
DEV	Unit, integration, API, smoke	Developer + DevOps
QA	Functional, regression, API	QA
UAT	Business acceptance	Business/Client + QA
PROD	Smoke, health checks, monitoring	DevOps             

## Interview explanation
“In my e-commerce project, we followed a multi-environment CI/CD strategy with DEV, QA, UAT and PROD. When developers commit code to GitHub, Jenkins is triggered through a webhook. Jenkins checks out the code, performs Maven build and unit testing, followed by SonarQube analysis and Quality Gate validation. We then perform security and dependency scanning, build the Docker image, scan it, and push the approved image to AWS ECR.”
“From ECR, Jenkins deploys the same versioned image to the DEV environment. We perform smoke, integration and API validation in DEV. Once DEV validation is successful, the same image is promoted to QA. The QA team performs functional, regression and API testing and provides QA approval.”
“After QA approval, Jenkins promotes the same image to the UAT environment, where business or client users perform acceptance testing. Once UAT approval is received, a manual approval gate is configured before production deployment.”
“After approval, Jenkins deploys the exact same tested image to PROD. We perform post-deployment smoke and health checks and monitor the application using CloudWatch, Prometheus and Grafana. If the production deployment fails or health checks are unsuccessful, we investigate the logs and roll back to the previous stable version.”

⭐ Key point to mention
Build once, promote the same artifact/image across environments.
Docker Image v1.2
      ↓
     DEV
      ↓
     QA
      ↓
     UAT
      ↓
    PROD
Don't rebuild the Docker image separately in QA, UAT and PROD. This ensures the exact artifact tested in QA/UAT is the one deployed to production.
