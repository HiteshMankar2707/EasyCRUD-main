# MariaDB Setup and Configuration Guide for Windows

This guide explains how to set up MariaDB, create a database, and Create Database User

## 1. Installing MariaDB

Installing MariaDB on Ubntu

```shell
apt update && apt install mariadb-server -y
```

## 2. Securing MariaDB

Open the Command Prompt as Administrator and run the following command to secure your installation:

```shell

mysql_secure_installation
```

Follow the prompts to:
Set a root password.
Remove insecure default users and test databases.
Disable remote root login.

## 3. Setting Up the Database

Open terminal and login to MariaDB:

```bash

mysql -u root -p
```

Enter the root password when prompted.

Create a new database and user:

```sql
CREATE DATABASE student_db;
GRANT ALL PRIVILEGES ON springbackend.* TO 'username'@'localhost' IDENTIFIED BY 'your_password';
```
Replace username and your_password with your desired username and password.

Exit MariaDB:

```sql

EXIT;
```

## 4. You will need Database Credentials to Connect Backend with Database
1. DB_HOST
2. DB_USER
3. DB_PASS
4. DB_PORT
5. DB_NAME

CHANGES DONE BY DEV
====================================

🧩 Pipeline Stages Explained

<!-- Checkout -->

Pulls the latest code from your Git repository (e.g., GitHub).

Ensures the pipeline always works with the newest commit.

<!-- Build -->

Compiles the application (here using Maven for a Java project).

Produces build artifacts like .jar or .war files.

<!-- Unit Tests -->

Runs automated tests to verify individual functions/classes.

Ensures code changes don’t break existing functionality.

<!-- Docker Build & Push -->

Builds a Docker image of your application.

Logs into Amazon ECR (Elastic Container Registry).

Tags and pushes the image to your ECR repository for storage and later deployment.

<!-- ECR Image Scan -->

Triggers a vulnerability scan of the Docker image stored in ECR.

Waits briefly, then retrieves scan results.

Uses a Python script (check_vulns.py) to parse results and fail the pipeline if critical vulnerabilities are found.

This enforces DevSecOps security gates.

<!-- Deploy to Staging -->

Applies Kubernetes manifests (staging-deployment.yaml) to deploy the app in a staging environment.

Allows testing in a production-like setup before going live.

<!-- Integration Tests -->

Runs end-to-end tests (e.g., API calls, UI flows).

Validates that different modules/services work together correctly.

<!-- Deploy to Production -->

Includes a manual approval gate (human confirmation).

Once approved, applies Kubernetes manifests (prod-deployment.yaml) to deploy the app in production.

<!-- Post Actions -->

Collects test reports (JUnit XMLs).

Archives build artifacts for traceability and reuse.

Ensures logs and results are available for auditing.