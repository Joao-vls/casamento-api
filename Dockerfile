# Use uma imagem base do OpenJDK
FROM openjdk:21-jdk-slim

# Defina variáveis de ambiente
ENV PROJECT_HOME=/usr/src/casamento
ENV JAR_NAME=casamento-0.0.1-SNAPSHOT.jar

# Crie o diretório de destino
RUN mkdir -p $PROJECT_HOME
WORKDIR $PROJECT_HOME

# Copie o arquivo de construção (build.gradle e outros arquivos necessários)
COPY build.gradle settings.gradle gradlew ./
COPY gradle ./gradle
COPY src ./src

# Dê permissão de execução ao Gradle Wrapper
RUN chmod +x gradlew

# Construa o aplicativo usando o Gradle Wrapper
RUN ./gradlew build -x test

# Liste os arquivos no diretório build/libs para verificar o nome do JAR
RUN ls -l build/libs

# Mova o arquivo JAR para o diretório correto
RUN mv build/libs/$JAR_NAME $PROJECT_HOME/

# Exponha a porta que a aplicação irá usar
EXPOSE 8080

# Defina o comando de entrada com sh para permitir a expansão de variáveis
ENTRYPOINT ["sh", "-c", "java -jar -Dspring.profiles.active=prod $PROJECT_HOME/$JAR_NAME"]
