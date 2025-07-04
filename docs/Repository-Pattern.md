# Implementação do Padrão Repositório

## Visão Geral do Padrão Repositório

O padrão repositório foi implementado no sistema CESAE Resort para fornecer uma abordagem estruturada e consistente ao acesso de dados. Este padrão atua como uma camada de abstração entre os modelos de domínio e a fonte de dados (ficheiros CSV neste caso).

## Fundamentos do Design

### Porquê Utilizar Repositórios?

1. **Abstração do Acesso a Dados**: Os repositórios ocultam os detalhes de recuperação e armazenamento de dados, permitindo que os controladores trabalhem com objetos de domínio sem saber como estes são persistidos.

2. **Responsabilidade Única**: Cada repositório é responsável por gerir um tipo de entidade, aderindo ao Princípio da Responsabilidade Única.

3. **Testabilidade**: As interfaces dos repositórios podem ser simuladas (mocked) ou substituídas (stubbed) para testes unitários dos controladores sem necessitar de fontes de dados reais.

4. **Independência da Fonte de Dados**: A implementação permite futuras alterações na fonte de dados (por exemplo, mudar de CSV para uma base de dados) sem afetar os controladores ou modelos.

5. **Lógica de Acesso a Dados Centralizada**: Todo o código de manipulação de dados está contido nos repositórios, evitando duplicação entre controladores.

### Implementação dos Repositórios

Cada classe de repositório no sistema segue um padrão semelhante:

1. Gere uma entidade de domínio específica (por exemplo, `ClienteRepository` para objetos `Cliente`)
2. Carrega dados de um ficheiro CSV correspondente utilizando o utilitário `CSVFileReader`
3. Fornece métodos para consultar e manipular os dados
4. Retorna objetos de domínio em vez de dados brutos

## Gestão de Ficheiros CSV

Os ficheiros CSV servem como fonte de dados para este sistema. A decisão de utilizar ficheiros CSV baseou-se em:

1. **Simplicidade**: Fáceis de implementar e compreender
2. **Portabilidade**: Os ficheiros CSV podem ser facilmente transferidos e guardados
3. **Legíveis por Humanos**: Os dados podem ser inspecionados e modificados com editores de texto simples

A ferramenta `CSVFileReader` proporciona uma forma comum para todos os repositórios lerem os seus respetivos ficheiros de dados, garantindo um tratamento consistente da análise de CSV e conversão de dados.

## Fluxo de Dados

O fluxo de dados na implementação do padrão repositório segue estes passos:

1. Os ficheiros CSV armazenam dados brutos
2. O `CSVFileReader` lê e analisa os dados brutos
3. As classes de repositório transformam os dados analisados em objetos de domínio
4. Os controladores interagem com os repositórios para aceder e manipular objetos de domínio
5. As vistas apresentam dados dos objetos de domínio aos utilizadores

## Benefícios Obtidos

A implementação do padrão repositório proporcionou vários benefícios:

- **Separação limpa de código**: O código de acesso a dados está isolado da lógica de negócio
- **Interfaces consistentes**: Todos os repositórios fornecem métodos semelhantes para acesso a dados
- **Flexibilidade**: O sistema pode ser estendido para suportar novas fontes de dados com alterações mínimas
- **Manutenção**: As alterações na estrutura de dados não afetam os controladores ou as vistas

## Extensibilidade Futura

A atual implementação do repositório permite melhorias futuras:

1. **Adição de cache**: As implementações dos repositórios poderiam ser estendidas para armazenar em cache dados frequentemente acedidos
2. **Migração para bases de dados**: As interfaces dos repositórios poderiam ser implementadas utilizando JDBC ou um ORM
3. **Adição de validação**: Lógica de validação de dados poderia ser adicionada aos métodos do repositório
4. **Suporte a transações**: Múltiplas operações de dados poderiam ser agrupadas em transações atómicas
