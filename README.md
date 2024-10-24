<h1 align="center"> 🏨 Sistema de Gerenciamento de Hotel </h1>

<h4 align="left">- O objetivo deste projeto é criar um sistema de gerenciamento de hotel em Java, que permita a administração de diferentes aspectos do hotel, como reservas, check-ins, check-outs, além de gerenciar quartos, hóspedes e funcionários. </h4>
  
  
<h4 align="left">- O sistema será desenvolvido utilizando conceitos de Programação Orientada a Objetos (POO) e Estruturas de Dados Básicas, como arrays ou listas para armazenar e gerenciar as informações. </h4>


<h4 align="left">- Para garantir flexibilidade, reutilização de código e desacoplamento, o projeto adotará interfaces para definir os comportamentos comuns entre diferentes entidades do sistema, como quartos, hóspedes, funcionários e reservas. </h4>

##

<h4 align="center"> Funcionalidades </h4>

1. Gerenciamento de Quartos

    - [ ] Cadastrar novos quartos: O sistema deve permitir o cadastro de novos quartos no hotel, contendo informações como número do quarto, tipo (solteiro, casal, suíte), capacidade (número de hóspedes) e preço da diária.
    - [ ] Visualizar quartos disponíveis: O sistema deve ser capaz de listar todos os quartos disponíveis para reserva, mostrando os detalhes de cada quarto, como tipo, capacidade, preço e status (disponível, ocupado, em manutenção).
    - [ ] Atualizar o status dos quartos: O sistema deve permitir alterar o status de um quarto (ex: de "disponível" para "ocupado" ou "em manutenção").

2. Gerenciamento de Hóspedes:

    - [ ] Cadastrar novos hóspedes: O sistema deve permitir o cadastro de novos hóspedes, onde são informados os dados como nome, CPF, data de nascimento, endereço e número de contato.
    - [ ] Visualizar histórico de estadias: O sistema deve manter o histórico de estadias de cada hóspede, mostrando quando ele se hospedou, em quais quartos e por quanto tempo.
    - [ ] Editar informações dos hóspedes: O sistema deve permitir a edição dos dados dos hóspedes já cadastrados.

3. Gerenciamento de Reservas:

    - [ ] Criar reservas: O sistema deve permitir que o usuário crie uma reserva para um hóspede, especificando as datas de entrada e saída, o tipo de quarto e o número de hóspedes.
    - [ ] Cancelar reservas: O sistema deve permitir que uma reserva seja cancelada, liberando o quarto para novas reservas.
    - [ ] Verificar disponibilidade de quartos: O sistema deve verificar se há quartos disponíveis para as datas selecionadas, garantindo que o quarto desejado esteja livre para a reserva.

4. Gerenciamento de Funcionários:

    - [ ] Cadastrar funcionários: O sistema deve permitir o cadastro de funcionários com dados como nome, CPF, cargo, salário e turno de trabalho.
    - [ ] Editar informações dos funcionários: O sistema deve permitir a edição de dados dos funcionários.
    - [ ] Registrar horas de trabalho e calcular salário: O sistema deve permitir o registro de horas trabalhadas pelos funcionários e calcular o salário com base nas horas trabalhadas.

5. Operações de Check-In e Check-Out:

    - [ ] Check-in: Ao realizar o check-in, o sistema deve atualizar o status do quarto como "ocupado", registrar o hóspede no quarto e confirmar o check-in.
    - [ ] Check-out: O sistema deve permitir que o hóspede faça o check-out, calcular o valor total da estadia com base no número de dias e no tipo de quarto, liberar o quarto para novas reservas, e informar que o check-out foi concluído com sucesso.

6. Feedback nas Operações:

    - [ ] Mensagens de sucesso: Para todas as operações do sistema, o sistema deve fornecer um feedback claro, como "Reserva criada com sucesso!", "Check-in realizado com sucesso!", e "Check-out realizado com sucesso!".
    - [ ] Mensagens de falha: Em caso de erro, como uma tentativa de acessar um registro que não existe (ex: excluir um hóspede inexistente), o sistema deve informar claramente o problema com mensagens como "Operação falhou: registro não encontrado.".

7. Validação de Dados:

    - [ ] Campos obrigatórios: Antes de realizar qualquer operação (cadastro, reserva, etc.), o sistema deve garantir que todos os campos obrigatórios foram preenchidos. Caso contrário, deve informar "Por favor, preencha todos os campos obrigatórios."
    - [ ] Formatação de dados: O sistema deve validar se o CPF do hóspede ou as datas de check-in e check-out estão corretamente formatadas. Caso contrário, mensagens como "CPF inválido. Por favor, insira um CPF válido." ou "Data de check-out deve ser posterior à data de check-in." devem ser exibidas.

  ```sh
O código deve conter tratamento de exceções.
```
