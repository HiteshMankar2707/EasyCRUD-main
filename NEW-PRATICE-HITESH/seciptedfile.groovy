node {  
    stage('PULL') { 
        echo 'Source COde PULL'
    }
    stage('BUILD') { 
        echo 'Application BUILD'
    }
    stage('TEST') { 
        echo 'TEST Success'
    }
    stage('DEPLOY') { 
        echo 'Deploy DOne'
    }
    stage('MONITOR') { 
        echo 'Deploy DOne'
    }
}

