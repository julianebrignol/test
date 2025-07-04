# Camada de Modelo e Entidades de Domínio

## Design do Modelo de Domínio

A camada de modelo no sistema CESAE Resort representa as entidades de negócio fundamentais e as suas relações. Esta camada é crucial, pois forma a base sobre a qual toda a aplicação é construída.

## Classes de Modelo e a Sua Finalidade

### User (Utilizador)

- Representa qualquer utilizador que pode aceder ao sistema
- Contém informações de autenticação
- Armazena o papel do utilizador (ADMIN, GESTAO, GUIA, CLIENTE)
- Serve como base para o controlo de acesso baseado em papéis

### Cliente

- Representa um cliente do resort
- Armazena informações pessoais (nome, contactos, etc.)
- Liga-se a reservas de quartos e compras de experiências
- Contém atributos e comportamentos específicos do cliente

### Quarto

- Representa um quarto disponível para reserva no resort
- Contém propriedades do quarto (número, estado, etc.)
- Associa-se a um tipo específico de quarto (Tipologia)
- Está ligado a reservas

### Tipologia

- Define as categorias de quartos disponíveis
- Contém especificações como capacidade, comodidades, etc.
- Utilizada para agrupar quartos semelhantes e definir preços
- Referenciada pelas entidades Quarto

### Experiencia

- Representa atividades ou experiências oferecidas pelo resort
- Contém detalhes como descrição, duração, capacidade, etc.
- Associada a guias que realizam a experiência
- Ligada a reservas e avaliações

### Guia

- Representa membros da equipa que conduzem experiências
- Contém informações pessoais e profissionais
- Associado às experiências que está qualificado para liderar
- Pode ter especializações ou avaliações

### ReservaQuarto

- Representa uma reserva de um quarto por um cliente
- Contém detalhes da reserva (datas, estado, etc.)
- Liga as entidades Cliente e Quarto
- Pode incluir serviços adicionais ou requisitos específicos

### VendaExperiencia

- Representa a compra de uma experiência por um cliente
- Contém detalhes da reserva e informações de participação
- Liga as entidades Cliente e Experiencia
- Pode incluir requisitos especiais ou informações de grupo

### RatingExperiencia

- Representa feedback fornecido pelos clientes sobre experiências
- Contém pontuações e comentários
- Liga-se tanto ao cliente que forneceu a avaliação como à experiência
- Utilizado para controlo de qualidade e recomendações

## Relações entre Entidades

As entidades de domínio estão relacionadas das seguintes formas:

1. **User para Role**: Cada utilizador tem exatamente um papel que determina o seu acesso ao sistema
2. **Cliente para User**: Um cliente está associado a uma conta de utilizador com o papel CLIENTE
3. **Cliente para ReservaQuarto**: Um cliente pode ter múltiplas reservas de quartos
4. **Cliente para VendaExperiencia**: Um cliente pode comprar múltiplas experiências
5. **Cliente para RatingExperiencia**: Um cliente pode fornecer avaliações para experiências
6. **Quarto para Tipologia**: Cada quarto pertence a exatamente um tipo de quarto
7. **Quarto para ReservaQuarto**: Um quarto pode ter múltiplas reservas (em momentos diferentes)
8. **Experiencia para Guia**: As experiências estão associadas a guias que podem realizá-las
9. **Experiencia para VendaExperiencia**: Uma experiência pode ser reservada por múltiplos clientes
10. **Experiencia para RatingExperiencia**: Uma experiência pode receber múltiplas avaliações

## Fundamentos do Design

### Separação de Entidades

A decisão de criar classes de modelo separadas para cada entidade de domínio baseou-se em:

1. **Responsabilidade Única**: Cada classe representa um conceito de negócio claro
2. **Precisão do Domínio**: O modelo reflete com precisão o domínio de negócio
3. **Manutenção**: Alterações a uma entidade não afetam outras
4. **Clareza**: A separação clara torna o sistema mais fácil de entender

### Modelos de Domínio Ricos

As classes de modelo são projetadas para serem modelos de domínio ricos em vez de simples contentores de dados:

1. **Lógica de Negócio**: A lógica específica do domínio está contida nos modelos
2. **Validação**: Os modelos validam o seu próprio estado
3. **Comportamento**: Os modelos implementam comportamentos apropriados ao seu conceito de domínio
4. **Encapsulamento**: Os detalhes internos são ocultados por interfaces apropriadas

## Benefícios do Design da Camada de Modelo

O design da camada de modelo proporciona vários benefícios:

- **Representação do Domínio**: Os modelos representam com precisão o domínio de negócio
- **Organização do Código**: Separação clara entre diferentes conceitos de domínio
- **Reutilização**: Os modelos podem ser reutilizados em diferentes partes da aplicação
- **Segurança de Tipos**: A tipagem forte evita a mistura de conceitos incompatíveis
- **Documentação**: O modelo serve como documentação viva do domínio

## Considerações Futuras

O design da camada de modelo permite melhorias futuras:

1. **Hierarquias de Herança**: Implementação de herança para entidades especializadas
2. **Objetos de Valor**: Introdução de objetos de valor imutáveis para conceitos como Morada ou Dinheiro
3. **Eventos de Domínio**: Adição de publicação de eventos a partir de modelos de domínio
4. **Framework de Validação**: Melhoria da validação com uma framework declarativa
5. **Mapeamento Objeto-Relacional**: Preparação de modelos para futura persistência em base de dados
