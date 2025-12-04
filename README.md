# 🧾 Sistema de Controle Financeiro Pessoal — *Monefy Backend*

## 📝 Descrição Geral

O sistema tem como objetivo fornecer um **controle completo das finanças pessoais**, permitindo que o usuário gerencie suas contas bancárias, cartões de crédito, lançamentos financeiros e movimentações diversas.  
Entre suas principais funções, destaca-se o acompanhamento de contas a pagar e a receber, geração automática de movimentos, consultas de extrato por período e classificação de receitas e despesas por centros de custo.

O sistema também oferece suporte a **transferências entre contas**, fechamento e pagamento de faturas, além de relatórios consolidados e trilhas de auditoria.

---

## ✅ Requisitos Funcionais (RF)

### **Gerenciamento Básico**
- **RF01.** O sistema deve permitir o cadastro, edição e exclusão de usuários.  
- **RF02.** O sistema deve permitir o cadastro de contas bancárias (nome, número, banco, saldo inicial).  
- **RF03.** O sistema deve possibilitar o registro de cartões de crédito (limite, data de fechamento e vencimento).  

### **Lançamentos Financeiros**
- **RF04.** Permitir o lançamento de contas a pagar e a receber, vinculadas a centros de custo e entidades.  
- **RF05.** Permitir baixar lançamentos financeiros, atualizando valor pago/recebido e status.  
- **RF06.** Ao realizar uma baixa, gerar automaticamente um **MovimentoConta**.  
- **RF07.** Registrar pagamentos e recebimentos vinculados a lançamentos financeiros.  

### **Transferências e Movimentações**
- **RF08.** Permitir transferências entre contas, gerando uma transação única (débito + crédito).  
- **RF09.** Calcular e exibir extratos por período, incluindo saldo inicial, movimentações e saldo final.  
- **RF10.** Classificar receitas e despesas por centro de custo.  
- **RF11.** Permitir vínculo de lançamentos a entidades geradoras (lojas, empregadores, etc.).  

### **Cartões de Crédito**
- **RF12.** Permitir fechar faturas, impedindo novos lançamentos após o fechamento.  
- **RF13.** Registrar pagamento de faturas, gerando automaticamente um débito em conta bancária.  

### **Consultas e Relatórios**
- **RF14.** Permitir consultas e relatórios de movimentações, receitas, despesas e saldos consolidados.  

### **Auditoria e Validações**
- **RF15.** Registrar automaticamente `criadoEm` e `atualizadoEm` em todas as entidades.  
- **RF16.** Validar transferências, impedindo operações entre a mesma conta.  
- **RF17.** Impedir movimentações em contas marcadas como inativas.  
- **RF18.** O extrato deve ser calculado dinamicamente com base no saldo inicial e nas movimentações.  

---

## ⚙️ Requisitos Não Funcionais (RNF)

### **Interface e Desempenho**
- **RNF01.** Interface responsiva (desktop e mobile).  
- **RNF02.** Tempo máximo de resposta em consultas: **≤ 3 segundos**.  

### **Banco de Dados e Segurança**
- **RNF03.** Utilizar banco relacional (PostgreSQL) com integridade referencial.  
- **RNF04.** Garantir consistência transacional em baixas e transferências.  
- **RNF05.** Registrar logs de auditoria para inserção, alteração e exclusão.  
- **RNF06.** Autenticação segura com senha criptografada e controle de sessão.  

### **Infraestrutura e Confiabilidade**
- **RNF07.** Permitir backup e restauração dos dados.  
- **RNF08.** Interface intuitiva, priorizando clareza em extratos e lançamentos.  
- **RNF09.** Compatível com Chrome, Edge e Firefox.  
- **RNF10.** Suportar múltiplos usuários simultâneos sem degradação perceptível.  

---

## 🧱 Diagrama de Classes
<img src="./Diagrama de Classe.png" alt="Diagrama de Classe">


