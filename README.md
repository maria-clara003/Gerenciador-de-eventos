*Sobre o projeto*

Este front-end oferece as principais funcionalidades de um gerenciador de eventos, além de se integrar com uma API REST para armazenar suas tarefas. O sistema foi desenvolvido como projeto final da disciplina de Desenvolvimento de Plataformas Web.

*Fucionalidades*
- Criação de usuários

![WhatsApp Image 2024-11-28 at 18 12 55](https://github.com/user-attachments/assets/3eafaac5-654e-4240-a559-4525e6d038a8)
![WhatsApp Image 2024-11-28 at 18 14 05](https://github.com/user-attachments/assets/7b5e3c3e-1697-49a2-9c17-a7263af3e94a)

- Autenticação de usuários via JWT
- Após autenticação, você será redirecionado para o painel de controle, onde poderá visualizar seus eventos. Lá, é possível adicionar, excluir e marcar seus eventos.

![WhatsApp Image 2024-11-28 at 18 13 46](https://github.com/user-attachments/assets/a990623b-511d-4ed1-a206-1fb7324c5aeb)

*Tecnologias*
- Java
- Spring Boot
- MongoDB Atlas

*Como executar o projeto*

1. Como executar o projeto Spring Boot:
Clone o projeto: Faça o clone do repositório para sua máquina local utilizando o comando:

bash
Copiar código
git clone https://github.com/seu-usuario/seu-repositorio.git

2. Acesse a raiz do projeto: Entre no diretório do projeto clonado:
cd seu-projeto

3. Configure o banco de dados: O projeto já está configurado para utilizar o MongoDB. Certifique-se de que o MongoDB está rodando e que a URI de conexão é válida:
mongodb+srv://root:root@appdatabase.kqwbm.mongodb.net/appDatabase?retryWrites=true&w=majority

Caso necessário, ajuste a URI no arquivo application.properties localizado em:
src/main/resources/application.properties

4. Execute o projeto: Use o Maven Wrapper fornecido no projeto para compilar e executar: mvnw.cmd spring-boot:run

5. 5. Endpoints públicos: O sistema permite acesso público aos seguintes endpoints:
- Registro: POST /api/usuarios/registrar
- Login: POST /api/usuarios/login

6. Autenticação com JWT: Após o login, o sistema retornará um token JWT que deve ser incluído no cabeçalho das requisições autenticadas como:
Authorization: Bearer <seu-token-jwt>

7. Testando o sistema: Acesse os endpoints utilizando ferramentas como Postman ou Insomnia. Certifique-se de incluir o token JWT nas requisições que exigem autenticação.

8. 8. CORS: O sistema está configurado para permitir requisições do frontend em:

http://localhost:5500
http://127.0.0.1:5500
