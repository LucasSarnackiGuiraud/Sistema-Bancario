# Sistema Bancário - Java Swing

Este projeto é um sistema desktop desenvolvido em **Java** com **Swing**, seguindo o paradigma de **Programação Orientada a Objetos**.  
O objetivo é gerenciar clientes e suas contas bancárias, permitindo operações como cadastro, vinculação de contas, movimentações e aplicação de remuneração.

## 📋 Funcionalidades

- **Gerenciamento de Clientes**
  - Cadastrar, atualizar e excluir clientes
  - Listar clientes com `AbstractTableModel`
  - Buscar por nome, sobrenome, RG ou CPF
  - Ordenar por nome, sobrenome ou salário
  - Exclusão com confirmação, removendo também as contas vinculadas

- **Vinculação de Contas**
  - Selecionar cliente e tipo de conta (Corrente ou Investimento)
  - Campos específicos para cada tipo de conta
  - Geração automática do número da conta

- **Operações em Conta**
  - Buscar conta por CPF
  - Realizar saques e depósitos
  - Verificar saldo
  - Aplicar remuneração (1% para Conta Corrente e 2% para Conta Investimento)

## 🏗 Estrutura do Projeto

O projeto está organizado em pacotes para manter a modularidade:

- **model**: classes de domínio e regras de negócio (`Cliente`, `Conta`, `ContaCorrente`, `ContaInvestimento`, `ContaI`, `Banco`)
- **dao** (opcional): persistência de dados (`ClienteDAO`, `ContaDAO`)
- **view**: telas Swing (`MainView`, `ClienteView`, `ContaView`, `OperacoesView`)
- **controller**: ligação entre view e model (`ClienteController`, `ContaController`, `OperacoesController`)
- **util**: utilitários e classes auxiliares (`TableModelCliente`, `GeradorNumeroConta`)

## 🔄 Fluxo do Sistema

1. **Menu Principal (MainView)** → acesso às funcionalidades de clientes, contas e operações.
2. **Gerenciar Clientes (ClienteView)** → CRUD, busca e ordenação de clientes.
3. **Vincular Conta (ContaView)** → seleção de cliente, tipo de conta e preenchimento de dados.
4. **Operar Conta (OperacoesView)** → saque, depósito, saldo e remuneração.

## 📜 Regras de Negócio

- **Cliente**: atributos nome, sobrenome, RG, CPF, endereço; implementa `Comparable`.
- **Conta (abstrata)**: implementa interface `ContaI`; validações básicas de depósito e saque.
- **ContaCorrente**: saque limitado ao valor disponível + limite; remuneração de 1%.
- **ContaInvestimento**: depósitos ≥ depósito mínimo, saques mantendo saldo ≥ montante mínimo; remuneração de 2%.

## 🛠 Tecnologias Utilizadas

- Java 8+  
- Swing (Interface Gráfica)  
- Paradigma Orientado a Objetos (Herança, Polimorfismo, Encapsulamento)  

## 👤 Integrantes

- João Vitor Zanini Pedro
- Laura
- Lucas Sarnacki Giuraud
- Nathalia
