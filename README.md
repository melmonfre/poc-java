# POC Java

A POC deste respositório é capaz de listar veículos, fazer buscas, e criar novos registros de veículos.

# Tecnologias

- Spring Framework 6
- Tomcat 10
- JPA (Thymeleaf 3)
- MySQL 8

# Banco de dados

- Banco: MySQL
- Nome: avaliacao
- usuário: root
- senha: root

## Docker

``docker run --name java-poc -e MYSQL_ROOT_PASSWORD=root -e MYSQL_DATABASE=avaliacao -p 3306:3306 mysql``

## SQL da tabela de veículos:

> Recomendação: Use o próprio Spring para criar o banco e a tabela, com os recursos do JPA/Hibernate, apenas iniciando o projeto com o banco de dados conectado.
```sql
create table vehicles (
id bigint not null auto_increment,
brand varchar(100),
fabrication datetime(6),
model varchar(100) not null,
plate varchar(12) not null,
primary key (id)
)
```