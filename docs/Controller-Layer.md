# Design da Camada de Controlador

## Papel dos Controladores no CESAE Resort

A camada de controlador é um componente crucial da arquitetura MVC implementada no sistema CESAE Resort. Os controladores atuam como intermediários entre as vistas (interface do utilizador) e os modelos (objetos de domínio e repositórios), tratando da lógica de negócio e coordenando o fluxo da aplicação.

## Decisões de Design dos Controladores

### Controladores Separados por Área de Domínio

A decisão de criar controladores separados para diferentes áreas de domínio (Autenticação, Clientes, Quartos, Experiências, Administração) foi tomada para:

1. **Impor Separação de Domínio**: Cada controlador concentra-se em operações relacionadas com um domínio de negócio específico
2. **Reduzir Complexidade**: Dividir a funcionalidade em controladores menores e focados melhora a legibilidade e manutenção
3. **Permitir Desenvolvimento Paralelo**: Diferentes membros da equipa podem trabalhar em controladores separados sem conflitos
4. **Facilitar Testes**: Controladores menores com responsabilidades claras são mais fáceis de testar

### Controladores Implementados

1. **AuthController**: Gere a autenticação e gestão de papéis de utilizador
2. **ClienteController**: Gere operações relacionadas com dados de clientes e funcionalidades específicas do cliente
3. **QuartoController**: Gere reservas de quartos, disponibilidade e gestão
4. **ExperienciaController**: Gere experiências, reservas e avaliações
5. **AdminController**: Fornece funções administrativas que abrangem múltiplos domínios

## Responsabilidades dos Controladores

Cada controlador tem responsabilidades específicas:

### AuthController

- Autenticação de utilizadores
- Determinação de papéis de utilizador
- Direcionamento de utilizadores para as vistas apropriadas
- Gestão de sessões de login

### ClienteController

- Gestão de perfil de cliente
- Operações de reserva específicas do cliente
- Gestão de dados pessoais
- Visualização de histórico de reservas

### QuartoController

- Verificação de disponibilidade de quartos
- Processamento de reservas de quartos
- Obtenção de informações sobre quartos
- Gestão de tipos de quarto

### ExperienciaController

- Agendamento de experiências
- Reservas de experiências
- Gestão de avaliações
- Atribuição de guias

### AdminController

- Relatórios de todo o sistema
- Gestão de contas de utilizador
- Operações entre domínios
- Configuração do sistema

## Comunicação Entre Controladores

Os controladores são projetados para serem relativamente independentes, mas alguma comunicação entre controladores pode ser necessária. Isto é gerido através de:

1. **Referências Diretas**: Quando um controlador necessita de funcionalidade de outro
2. **Repositórios Partilhados**: Vários controladores podem aceder aos mesmos repositórios
3. **Coordenação pela Aplicação Principal**: A classe Main conecta os controladores conforme necessário

## Benefícios do Design da Camada de Controlador

O design da camada de controlador proporciona vários benefícios:

- **Localização Clara da Lógica de Negócio**: Todas as regras de negócio estão localizadas nos controladores, não nas vistas ou repositórios
- **Operações Reutilizáveis**: Operações comuns são encapsuladas em métodos de controlador para reutilização
- **Unidades Testáveis**: Os controladores podem ser testados independentemente da interface do utilizador
- **Flexibilidade**: Os controladores podem ser modificados ou substituídos sem alterar a interface do utilizador
- **Escalabilidade**: Novos controladores podem ser adicionados para novos domínios de negócio

## Considerações Futuras

O design dos controladores permite melhorias futuras:

1. **Interfaces de Controlador**: Definição de interfaces para controladores para suportar múltiplas implementações
2. **Padrão Command**: Implementação de comandos para operações complexas que abrangem múltiplos controladores
3. **Sistema de Eventos**: Adição de publicação/subscrição de eventos para um acoplamento flexível entre controladores
4. **Funcionalidades Orientadas a Aspetos**: Adição de preocupações transversais como registo de logs ou gestão de transações
