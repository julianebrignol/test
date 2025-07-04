# Design da Camada de Visualização

## Vistas Baseadas em Papéis

O sistema CESAE Resort implementa uma arquitetura de vistas baseada em papéis, onde cada papel de utilizador tem uma classe de vista dedicada. Esta decisão de design foi tomada para suportar os seguintes objetivos:

## Fundamentação do Design

### Separação por Papel do Utilizador

A decisão de criar vistas separadas para cada papel de utilizador (Admin, Gestão, Guia, Cliente e Login) proporciona várias vantagens:

1. **Experiência de Utilizador Personalizada**: Cada papel vê apenas a funcionalidade relevante para as suas necessidades
2. **Segurança por Design**: Restrições a nível da interface impedem que os utilizadores acedam a funcionalidades não autorizadas
3. **Desenvolvimento Simplificado**: Cada vista pode ser desenvolvida e testada independentemente
4. **Percursos de Utilizador Claros**: Os fluxos de trabalho do utilizador estão contidos em interfaces específicas para cada papel
5. **Complexidade Reduzida**: Cada classe de vista lida com um conjunto limitado de funções relacionadas

## Implementação das Vistas

Cada classe de vista no sistema segue um padrão semelhante:

1. Apresenta um menu de opções específico para o papel
2. Captura a entrada do utilizador
3. Delega a lógica de negócio aos controladores apropriados
4. Apresenta os resultados de volta ao utilizador

### Classes de Vista

1. **LoginView**: Gere o processo de autenticação inicial
   - Recolhe as credenciais do utilizador
   - Trabalha com o AuthController para validar utilizadores
   - Encaminha para a vista específica do papel apropriado

2. **AdminView**: Fornece funcionalidade administrativa
   - Opções de configuração do sistema
   - Gestão de utilizadores
   - Acesso a relatórios e estatísticas em todos os domínios

3. **GestaoView**: Apoia o pessoal de gestão
   - Relatórios financeiros
   - Gestão de ocupação
   - Agendamento de pessoal
   - Gestão de experiências

4. **GuiaView**: Interface para guias de experiências
   - Agendamento de experiências
   - Gestão de grupos
   - Revisão de avaliações
   - Gestão de disponibilidade

5. **ClienteView**: Interface para clientes do resort
   - Reserva de quartos
   - Reserva de experiências
   - Submissão de avaliações
   - Gestão de informações pessoais

## Fluxo da Interface do Utilizador

A camada de vista implementa uma interface de utilizador baseada em consola com o seguinte fluxo:

1. O sistema inicia com a LoginView
2. Após autenticação bem-sucedida, o utilizador é direcionado para a vista específica do seu papel
3. Cada vista apresenta um menu de opções disponíveis para esse papel
4. O utilizador seleciona opções para executar ações
5. As vistas chamam controladores para executar a lógica de negócio
6. Os resultados são apresentados ao utilizador
7. O utilizador pode navegar de volta para o menu principal ou sair do sistema

## Benefícios do Design da Camada de Vista

O design de vista baseado em papéis proporciona vários benefícios:

- **Experiência de Utilizador Simplificada**: Os utilizadores veem apenas as opções relevantes para o seu papel
- **Carga Cognitiva Reduzida**: Os menus são focados e específicos para cada papel
- **Segurança Reforçada**: O controlo de acesso a nível da interface complementa a segurança do backend
- **Manutenção**: Alterações à interface de um papel não afetam as outras
- **Extensibilidade**: Novas funcionalidades específicas para cada papel podem ser adicionadas à vista apropriada

## Considerações Futuras

O design da camada de vista permite melhorias futuras:

1. **Interface Gráfica do Utilizador**: As vistas atuais baseadas em consola poderiam ser substituídas por interfaces gráficas, mantendo as mesmas interações com os controladores
2. **Interface Web**: O sistema poderia ser estendido para fornecer acesso baseado na web utilizando os mesmos controladores
3. **Aplicação Móvel**: Uma interface móvel poderia ser implementada, novamente utilizando os mesmos controladores
4. **Internacionalização**: As vistas poderiam ser melhoradas para suportar múltiplos idiomas
5. **Funcionalidades de Acessibilidade**: Funcionalidades adicionais poderiam ser adicionadas para melhorar a acessibilidade para utilizadores com deficiências
