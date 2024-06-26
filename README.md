# Desafio proposto
Desafio Desenvolvedor Java Pleno

Criar uma aplicação para cadastro de incidentes.
A aplicação deverá fornecer operações REST que possibilitem o cadastro, manutenção,
remoção e pesquisa de incidentes.
Um incidente é composto pelos campos (coloque mais campos se achar necessário).
  - idIncident
  - name
  - description
  - createdAt
  - updatedAt
  - closedAt

Funcionalidades
Sua aplicação deve ter as funcionalidades abaixo:
     • Cadastrar Incidentes
     • Atualizar Incidentes
     • Deletar Incidentes
     • Listar todos incidentes
     • Listar incidente por ID
     • Listar os últimos 20 incidentes ordenados por ordem decrescente

# Tecnologias aplicadas:
   - Java 17
   - Spring boot 3.3.1
   - Lombok
   - Database H2
   - OpenApi Swagger 3
   - JUnit
   - Mockito

# Acessos
   - Documentação: Acesse a documentação completa da API através do Swagger - http://localhost:8082/swagger-ui/index.html
   - H2 Console: Gerencie o banco de dados H2 em memória através do console - http://localhost:8082/h2-console
      - User: sa
      - Password: 1234

# Pré-requisitos:
   - Java 17 instalado na máquina
   - Maven instalado

# Como Executar
   - Através de uma IDE: Utilize sua IDE favorita para executar a aplicação como um projeto Spring Boot.
   - Comando Maven: Abra um terminal na pasta raiz do projeto e execute o comando mvn spring-boot:run.
  
# Utilização e Testes
 - Os endpoints da API estão todos documentados no Swagger e podem ser acessados diretamente pela interface sem necessidade de autenticação.
 - O banco de dados H2 é utilizado apenas para fins de desenvolvimento. Em um cenário real, um banco de dados persistente deve ser utilizado.
