# CESAE Resort - Hotel Temático da Programação

Este projeto consiste na implementação de um sistema de gestão para o CESAE Resort, um hotel temático de programação. O sistema segue o padrão MVC (Model-View-Controller) e foi desenvolvido em Java.

## Estrutura do Projeto

- **`src/`**: Diretório principal do código-fonte
  - **`controller/`**: Contém os controladores que gerenciam a lógica de negócio
  - **`files/`**: Armazena os arquivos CSV com os dados do sistema
  - **`models/`**: Contém as classes de modelo que representam as entidades do sistema
  - **`repositories/`**: Contém os repositórios que gerenciam o acesso aos dados
  - **`tools/`**: Contém ferramentas utilitárias, como o leitor de CSV
  - **`views/`**: Contém as interfaces de usuário para cada tipo de perfil
  - **`Main.java`**: Ponto de entrada do programa

## Funcionalidades por Perfil

### Cliente (sem login)

- Consultar Quartos Disponíveis
- Consultar Experiências Disponíveis
- Consultar Experiência Favorita
- Consultar Experiência Top-Seller
- Avaliar uma Experiência

### Rececionista (GESTAO)

- Consultar Quartos Disponíveis (Semana Atual)
- Consultar Quartos Reservados
- Consultar Reservas Atuais (Semana Atual)
- Efetuar uma Reserva
- Reservar uma Experiência

### Guia de Experiência (GUIA)

- Consultar Histórico de Experiências

### Administrador (ADMIN)

- Consultar Total de Reservas
- Consultar Total de Receitas
- Consultar Reservas/Receitas Mensais
- Consultar Tipologia de Quarto Mais Reservada
- Consultar Experiência Mais Procurada (Adultos)
- Consultar Experiência Mais Procurada (Crianças)
- Consultar Experiência Mais Lucrativa
- Consultar Experiência Menos Lucrativa
- Consultar Quarto com Melhor Preço/Semana
- Adicionar Novo Login

## Arquivos de Dados

O sistema utiliza os seguintes arquivos CSV para armazenar dados:

- `clientes.csv`: Informações dos clientes
- `experiencias.csv`: Detalhes das experiências oferecidas
- `guias_experiencias.csv`: Informações sobre os guias das experiências
- `logins.csv`: Credenciais de acesso ao sistema
- `quartos.csv`: Informações sobre os quartos do hotel
- `ratings_experiencias.csv`: Avaliações das experiências
- `reservas_quartos.csv`: Registro das reservas de quartos
- `tipologia.csv`: Tipos de quartos disponíveis
- `vendas_experiencias.csv`: Registro das vendas de experiências

## Execução do Programa

Para executar o programa, execute a classe `Main.java`, que inicializará todos os componentes necessários e apresentará o menu de login.
