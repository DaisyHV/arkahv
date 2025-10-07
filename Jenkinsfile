pipeline {
    agent any

    environment {
        APP_NAME = "cotizador-0.0.1-SNAPSHOT.jar"
        APP_DIR = "app/cotizador/build/libs"
        PORT = "8085"
        LOG_FILE = "/var/log/cotizador.log"
    }

    stages {
        stage('Checkout') {
            steps {
                git branch: 'master', url: 'https://github.com/DaisyHV/arkahv.git'
            }
        }

        stage('Build') {
            steps {
                sh '''
                    chmod +x gradlew
                    ./gradlew :app:cotizador:clean :app:cotizador:build -x test
                '''
            }
        }

        stage('Deploy') {
            steps {
                sh '''
                    cd $WORKSPACE/$APP_DIR

                    echo "Deteniendo instancias previas..."
                    pkill -f $APP_NAME || true

                    echo " Iniciando aplicación en puerto $PORT..."
                    nohup java -jar $APP_NAME --server.port=$PORT > $LOG_FILE 2>&1 &
                '''
            }
        }

        stage('Verificar') {
            steps {
                sh '''
                    sleep 5
                    echo "🔎 Verificando proceso..."
                    ps aux | grep $APP_NAME | grep -v grep || echo " No se encontró el proceso corriendo"
                '''
            }
        }
    }

    post {
        success {
            echo " Despliegue completado correctamente en puerto ${PORT}"
        }
        failure {
            echo " Falló el despliegue. Revisa los logs."
        }
    }
}
