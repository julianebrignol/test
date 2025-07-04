# Autenticação e Gestão de Papéis de Utilizador

## Controlador de Autenticação

### Propósito e Fundamentos

O `AuthController` foi criado como um componente dedicado para gerir toda a autenticação e gestão de papéis de utilizador no sistema CESAE Resort. Esta decisão arquitetónica proporciona vários benefícios:

1. **Lógica de Autenticação Centralizada**: Ao isolar a autenticação num único controlador, garantimos consistência na forma como os utilizadores são autenticados em toda a aplicação.

2. **Segurança**: A lógica de autenticação está contida num componente especializado, tornando as auditorias de segurança e melhorias mais gerenciáveis.

3. **Princípio da Responsabilidade Única**: O controlador tem uma responsabilidade clara - gerir a autenticação e autorização de utilizadores.

4. **Reutilização**: A funcionalidade de autenticação pode ser reutilizada em diferentes partes da aplicação sem duplicação de código.

### Características Principais

- **Autenticação de Utilizadores**: Valida as credenciais de login contra os dados de utilizador armazenados
- **Gestão de Sessão**: Mantém informações sobre o utilizador atualmente autenticado
- **Controlo de Acesso Baseado em Papéis**: Direciona os utilizadores para as vistas apropriadas com base nos seus papéis
- **Gestão de Tentativas de Login**: Processa tentativas de login bem-sucedidas e falhadas

## Modelo de Utilizador com Design Baseado em Papéis

### Por que Incluir o Papel no Modelo de Utilizador?

O modelo `User` foi projetado com um atributo de papel integrado por várias razões:

1. **Associação Direta de Papel**: Cada utilizador tem exatamente um papel, tornando-o uma propriedade natural da entidade de utilizador.

2. **Fluxo de Autenticação Simplificado**: Com o papel como parte dos dados do utilizador, a autenticação e o encaminhamento para o menu podem acontecer num único passo.

3. **Controlo de Acesso**: As permissões do utilizador podem ser determinadas imediatamente após a autenticação.

4. **Extensibilidade**: O design baseado em papéis permite a fácil adição de novos papéis e conjuntos de permissões no futuro.

### Tipos de Papéis e Suas Funções

O sistema suporta quatro papéis distintos de utilizador:

- **ADMIN**: Administradores do sistema com acesso a toda a funcionalidade
- **GESTAO**: Pessoal de gestão com acesso a funcionalidades operacionais e de relatórios
- **GUIA**: Guias de experiências com acesso a funcionalidades de gestão de experiências
- **CLIENTE**: Clientes do resort com acesso a funcionalidades de reserva e avaliação

Cada papel corresponde a uma vista específica que apresenta apenas a funcionalidade relevante para esse tipo de utilizador, reforçando a separação de preocupações também ao nível da interface do utilizador.

## Fluxo de Autenticação

1. O utilizador fornece credenciais através da `LoginView`
2. O `AuthController` valida as credenciais contra o `UserRepository`
3. Se válido, o papel do utilizador é determinado a partir do seu registo de utilizador
4. Com base no papel, a vista apropriada é instanciada e exibida
5. O contexto do utilizador autenticado é mantido durante a sessão

Este fluxo garante que os utilizadores acedam apenas à funcionalidade apropriada para o seu papel, mantendo a segurança do sistema e proporcionando uma experiência de utilizador focada.
