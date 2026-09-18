# Query Performance

Pequeno aplicativo Java para comparar o tempo de execução de consultas JDBC em
SQL Server, MySQL e Oracle.

O programa consulta a tabela `PRESTADO`, mede o tempo de acesso aos registros e
exibe gráficos comparativos para três modos históricos do experimento:

- `HARDCODED`
- `FIRMCODED`
- `SOFTCODED`

Os resultados são apresentados em gráficos separados por banco e em um gráfico
comparativo geral.

## Requisitos

- Java instalado e disponível no `PATH`.
- Maven 3.9 ou superior.
- Acesso a pelo menos um dos bancos configurados no código.
- Uma tabela `PRESTADO` com as colunas `ID` e `SERVICO`.

O projeto usa os drivers JDBC definidos no `pom.xml`. Os endereços dos bancos
estão atualmente configurados em `Teste.java`:

- SQL Server: `jdbc:sqlserver://notepai:1433;databaseName=autopecas`
- MySQL: `jdbc:mysql://localhost/autopecas`
- Oracle: `jdbc:oracle:thin:@//localhost:1521/xe`

## Execução

Na raiz do projeto, execute:

```powershell
mvn test -q -Dproject.build.sourceEncoding=ISO-8859-1
```

Para iniciar a interface gráfica:

```powershell
mvn compile -Dproject.build.sourceEncoding=ISO-8859-1
java -cp "target/classes;target/dependency/*" br.gov.sp.fatec.Teste
```

Ao iniciar o aplicativo, informe as credenciais dos bancos disponíveis e
clique em **Inicia Teste**.

## Segurança

As consultas que filtram por `ID` usam `PreparedStatement` e parâmetros JDBC,
evitando a concatenação de valores diretamente no SQL. As credenciais e os
endereços de conexão ainda são específicos do ambiente local e devem ser
externalizados antes de usar o projeto em produção.

## Estrutura principal

- `src/main/java/br/gov/sp/fatec/Teste.java`: conexões, consultas e medições.
- `src/main/java/br/gov/sp/fatec/Form.java`: interface para iniciar o teste.
- `src/main/java/br/gov/sp/fatec/GraficoBarra.java`: geração dos gráficos.
- `src/test/java/br/gov/sp/fatec/AppTest.java`: teste básico do projeto.