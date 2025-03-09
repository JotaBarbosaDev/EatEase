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
