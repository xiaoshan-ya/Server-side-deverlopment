pipeline {
    agent any

    tools {
        maven 'M3'
        jdk 'jdk1.8'
    }

    triggers {
        GenericTrigger(
                genericVariables: [
                        [key: 'event_name', value: '$.event_name', defaultValue: 'push'],
                        [key: 'project_name', value: '$.project.name', defaultValue: 'default'],
                        [key: 'ref', value: '$.ref', defaultValue: 'default'],
                        [key: 'tag', value: '$.ref', defaultValue: 'default', regexpFilter: '.*/'],
                        [key: 'project_id', value: '$.project.id'],    // 项目ID
                        [key: 'project_abbreviation', value: '$.project.name', regexpFilter: '.*-', defaultValue: 'default'],     // 项目简称
                        [key: 'group_name', value: '$.project.namespace'], // GITLAB_GROUP
                        [key: 'commits_id', value: '$.after']// 触发请求中的last_commit.id
                ],
                token: 'spittr',
                causeString: ' Triggered on $project_name',
                printContributedVariables: true,
                printPostContent: true,
                regexpFilterText: '$commits_id',
                regexpFilterExpression: '[^0]{1,10}'
        )
    }

    stages {
        stage('Compile and Unit Test') {
            steps {
                script {
                    echo "Compile and Unit Test"
                    bat "mvn clean dependency:copy-dependencies package -U"
                }
            }
            post {
                always {
                    junit 'target/surefire-reports/*.xml'
                    publishCoverage adapters: [coberturaAdapter('target/site/cobertura/coverage.xml')], sourceFileResolver: sourceFiles('NEVER_STORE')
                    // cobertura autoUpdateHealth: false, autoUpdateStability: false, coberturaReportFile: 'target/site/cobertura/coverage.xml', conditionalCoverageTargets: '70, 0, 0', failUnhealthy: false, failUnstable: false, lineCoverageTargets: '80, 0, 0', maxNumberOfBuilds: 0, methodCoverageTargets: '80, 0, 0', onlyStable: false, sourceEncoding: 'ASCII', zoomCoverageChart: false
//                     publishHTML([allowMissing: false, alwaysLinkToLastBuild: false, keepAll: false, reportDir: 'target/site', reportFiles: 'index.html', reportName: 'HTML Report', reportTitles: 'site report'])
//                     publishHTML([allowMissing: false, alwaysLinkToLastBuild: false, keepAll: false, reportDir: 'target/site/cobertura', reportFiles: 'index.html', reportName: 'HTML Report', reportTitles: ''])

                }
            }
        }

        //注意会将检查结果报送到SonarQube，使用的是jenkins里的SonarQube配置，包括访问地址、用户名密码
        stage('Sonar Analysis') {
            when {
                expression { return false; }
            }
            tools {
                jdk 'jdk11'
            }
            steps {
                echo "Sonar Analysis"

                //java项目使用，可以使用pom.xml文件中的版本号、项目名，以及属性配置
                //项目标识:com.example:spittr
                //项目名：pom文件里的<name>spittr</name>
                withSonarQubeEnv('mysonar') {
                    bat "mvn sonar:sonar"
                }

                //非java项目使用
//                script {
//                    SCANNER_HOME = tool 'myscanner'
//                }
//                withSonarQubeEnv('mysonar') {
//                    bat "${SCANNER_HOME}/bin/sonar-scanner"
//                }
            }
        }

        //等待SonarQube返回的质量阈状态
        stage("Quality Gate") {
            when {
                expression { return false; }
            }
            steps {
                timeout(time: 1, unit: 'HOURS') {
                    //如果不加waitForQualityGate，withSonarQubeEnv也可以获得质量门的状态，但是无法取消流水线
                    //abortPipeline: false，waitForQualityGate有与没有效果一样
                    // Parameter indicates whether to set pipeline to UNSTABLE if Quality Gate fails
                    // true = set pipeline to UNSTABLE, false = don't
                    waitForQualityGate abortPipeline: true
                }
            }
        }

        stage('Generate Image') {
            steps {
                script {
                    echo "Generate Image"
                    catchError(buildResult: 'SUCCESS', message: 'delete spittr image error') {
                        bat "docker rmi -f spittr:1.0-SNAPSHOT"
                    }
                    bat 'mvn docker:build'
                }
            }
        }

//         stage('check') {
//             steps {
//                 input "whether deploy to function test environment?"
//             }
//         }

        stage('Deploy') {
            when {
                expression {
                    currentBuild.result == null || currentBuild.result == 'SUCCESS'
                }
            }
            steps {
                script {
                    bat "kubectl rollout restart deployment spittr"
                }
            }
        }

//         stage('Acceptance Testing by using pytest and allure') {
         stage('Acceptance Testing') {
             when {
                 expression { return false; }
             }
             steps {
                 script{
                     echo "Acceptance Testing by using pytest and allure"
                     bat 'pytest --alluredir=./target/allure-results'
                 }
             }
             post {
                 always {
                     allure includeProperties: false, jdk: '', results: [[path: 'target/allure-results']]
 //                     allure includeProperties: false, jdk: '', results: [[path: 'target/allure-results'], [path: 'target/surefire-reports']]
                 }
             }
         }

//        stage('Acceptance Testing') {
//            when {
//                expression { return false; }
//            }
//            steps {
//                script {
//                    echo "Acceptance Testing by using robot"
//                    bat 'robot -d report uat'
//                }
//            }
//            post {
//                always {
//                    robot archiveDirName: 'robot-plugin', outputPath: 'report', overwriteXAxisLabel: ''
//                }
//            }
//        }
    }

    post {
        always {
            echo '-----build completed-----'
        }
        failure {
            echo '-----build failure-----'
        }
        success {
            echo '-----build success-----'
        }
    }
}
