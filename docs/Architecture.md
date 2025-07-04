# CESAE Resort - Documentação de Arquitetura

## Visão Geral

Este documento explica as decisões arquitetónicas tomadas durante o desenvolvimento do sistema de gestão CESAE Resort. O sistema foi desenhado seguindo o padrão Model-View-Controller (MVC) para garantir a separação de responsabilidades, manutenção e escalabilidade.

## Estrutura de Pastas

O projeto segue uma estrutura clara e organizada:

```
CESAE Resort/
├── src/
│   ├── controller/    # Contém controladores que gerem a lógica de negócio
│   ├── files/         # Armazenamento para ficheiros de dados CSV
│   ├── models/        # Classes de entidade que representam objetos de domínio
│   ├── repositories/  # Camada de acesso a dados para ler/manipular dados do modelo
│   ├── tools/         # Classes utilitárias para operações comuns
│   ├── views/         # Classes de interface do utilizador para diferentes papéis
│   └── Main.java      # Ponto de entrada da aplicação
└── docs/              # Documentação do projeto
```

## Padrão Arquitetónico: MVC

O padrão Model-View-Controller (MVC) foi escolhido porque:

1. **Separação de Responsabilidades**: Divide a aplicação em componentes distintos que tratam de aspetos específicos da funcionalidade
2. **Manutenção**: Facilita a manutenção e modificação de componentes individuais sem afetar outros
3. **Testabilidade**: Permite testes unitários mais eficazes da lógica de negócio
4. **Escalabilidade**: Facilita a adição de novas funcionalidades sem perturbar a funcionalidade existente

### Componentes do MVC no CESAE Resort

- **Model**: Representa entidades de domínio e estrutura de dados (Cliente, Quarto, Experiencia, etc.)
- **View**: Responsável por apresentar informações aos utilizadores e capturar entradas do utilizador
- **Controller**: Gere a lógica da aplicação, processa pedidos do utilizador e gere o fluxo de dados

## Gestão de Dados

O sistema utiliza ficheiros CSV para persistência de dados, que são carregados na memória em tempo de execução através da camada de repositório:

1. Os ficheiros CSV são armazenados no diretório `files`
2. O utilitário `CSVFileReader` fornece funcionalidade para ler e analisar dados CSV
3. As classes de repositório tratam do acesso específico a dados para cada entidade de domínio
4. Os controladores interagem com os repositórios para realizar operações nos dados

Esta abordagem permite:

- Separação clara entre o armazenamento de dados e a lógica de negócio
- Padrões de acesso a dados consistentes em toda a aplicação
- Fácil extensão para outras fontes de dados no futuro (por exemplo, bases de dados)
