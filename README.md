# Gaba Games - E-Commerce Backend
Este é o backend para um sistema de gerenciamento de pedidos e produtos para o e-commerce "Gaba Games", desenvolvido com Java 17, Spring Boot, e MySQL.

Requisitos
Antes de executar o projeto, verifique se você tem as seguintes dependências instaladas:

Java 17 ou superior
Maven (ou Gradle, dependendo de qual for utilizado)
MySQL (ou qualquer banco de dados compatível)
Git para clonar o repositório
Postman ou ferramenta similar para testar a API
Configuração do Banco de Dados

# Criação do Banco de Dados:

O banco de dados pode ser criado utilizando o arquivo gaba-games.sql, que contém todas as queries necessárias para a criação do banco e suas tabelas. Siga os passos abaixo:

Certifique-se de que o MySQL está instalado e em execução no seu sistema.
Abra o MySQL Workbench ou qualquer outro cliente de MySQL.
Importe o arquivo gaba-games.sql ou execute as queries manualmente.
Se você estiver utilizando o terminal, pode executar o arquivo SQL da seguinte forma:
mysql -u seu_usuario_mysql -p < gaba-games.sql
Isso irá criar o banco de dados gaba-games e as tabelas necessárias.

Adicione as variáveis de ambiente necessárias para conectar o Spring Boot ao banco de dados e configurar a autenticação JWT. Crie ou edite o arquivo .env ou defina diretamente as variáveis no seu sistema.

# Spring JPA
SPRING_DATASOURCE_URL=${DB_URL}
SPRING_DATASOURCE_USERNAME=${DB_USER}
SPRING_DATASOURCE_PASSWORD=${DB_PASSWORD}

# Autenticação JWT
JWT_SECRET: Chave secreta para geração e validação de tokens JWT.

Clone o repositório:
git clone https://github.com/GabrielOlivetro/desafio-foursales-gabriel-castro.git
cd desafio-foursales-gabriel-castro
Importe ou execute o arquivo SQL:

O arquivo gaba-games.sql contém todas as queries para a criação do banco de dados e tabelas. Caso não tenha feito isso ainda, siga o passo anterior para criar o banco de dados.

Configuração do Banco de Dados:

No arquivo src/main/resources/application.properties, as configurações de conexão com o banco de dados já estão preparadas. As variáveis de ambiente deverão ser configuradas antes de rodar o projeto.

Construção do Projeto:

Execute o seguinte comando para construir o projeto com Maven:
mvn clean install
Executando o Projeto:

Após a construção, inicie a aplicação com o comando:
mvn spring-boot:run
Ou, se preferir rodar via IDE, execute a classe Application.java.
