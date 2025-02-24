# Definir a versão do Java a usar
JAVA_VERSION=23
JAVA_HOME_LINUX=/usr/lib/jvm/java-$(JAVA_VERSION)-openjdk
JAVA_HOME_MAC=/Library/Java/JavaVirtualMachines/openjdk-$(JAVA_VERSION).jdk/Contents/Home

# Detectar sistema operativo
UNAME_S := $(shell uname -s)

ifeq ($(UNAME_S),Linux)
    export JAVA_HOME=$(JAVA_HOME_LINUX)
else
    export JAVA_HOME=$(JAVA_HOME_MAC)
endif

# Verificar a versão do Java usada
check-java:
	@echo "Usando Java de: $(JAVA_HOME)"
	@java -version

# Compilar o projeto com Maven (usando o Maven instalado no sistema)
build:
	@echo "Compilando o projeto..."
	mvn clean package

# Executar o projeto Spring Boot
run:
	@echo "Iniciando a aplicação Spring Boot..."
	mvn spring-boot:run

# Criar um binário nativo para Linux
build-linux:
	@echo "Criando binário nativo para Linux..."
	mvn package -Pnative -DskipTests
	mv target/eatease target/eatease-linux

# Criar um binário nativo para macOS
build-mac:
	@echo "Criando binário nativo para macOS..."
	mvn package -Pnative -DskipTests
	mv target/eatease target/eatease-mac

# Limpar os ficheiros gerados
clean:
	@echo "Limpando ficheiros antigos..."
	mvn clean
	rm -rf target
