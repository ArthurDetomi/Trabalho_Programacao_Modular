# Trabalho Programação Modular

## Introdução
Este documento apresenta a documentação para um projeto de programação modular cujo objetivo é desenvolver um jogo 
inovador e emocionante que simula a corrida espacial para a prospecção de Hélio-3 na Lua.
Os robôs são lançados em posições aleatórias na superfície lunar e estão equipados com diversos sensores que
fornecem constantemente informações sobre sua posição, a concentração de Hélio-3 e a rugosidade do terreno. Cada robô 
consegue iniciar a prospecção de Hélio-3 em sua posição atual, com a produção de Hélio-3 
sendo diretamente proporcional à concentração no local.
O objetivo do jogo é simples: a companhia cujos robôs conseguirem prospectar mais Hélio-3 durante um período determinado vence o jogo.

## Documentação 
O trabalho envolve uma série de etapas. Inicialmente, ocorre a leitura de um arquivo JSON 
utilizando a classe LeitorGson para processar o conteúdo desse arquivo.

Com os dados do jogo em mãos, a classe ConfigJogoLeitura entra em ação. Essa classe não apenas reflete o estado do jogo, mas 
também valida se o arquivo JSON está no formato correto.

Posteriormente, procede-se com a leitura das dimensões do terreno, ou seja, o número de linhas e colunas. A partir 
dessas informações, cria-se uma lista de companhias que farão parte do jogo.

Em seguida, inicia-se as empresas e seus robôs com base nas informações do arquivo. Além disso, inicia-se um gravador 
de arquivos para registrar o progresso do jogo.

A classe principal, denominada JogoControlador, assume o controle. Antes de iniciar o jogo, é necessário configurar a 
duração total, que é normalmente fornecida em minutos e convertida para segundos.

A estratégia dos robôs é então implementada, considerando fator como a concentração de recursos e a 
rugosidade do terreno. Todas as ações dos robôs são registradas, incluindo coleta de recursos e outras interações.

## Como executar o programa
Para utilizar o programa primeiro deve se buildar o projeto para gerar os bytecodes de cada classe, 
logo após deve-se preencher o arquivo '*.json*' no diretório src/main/java/ufsj/trabalho/input/input.json, 
o qual já contém exemplos de parâmetros como no exemplo abaixo. Exemplo input.json:
```json
{
  "duracaoPartida": 60,
  "terreno": {
    "quantidadeLinhas": 2,
    "quantidadeColunas": 2
  },
  "companhias": [
    {
      "nome": "alfa",
      "quantidadeRobos": 2,
      "controlador": "controladorPrimeiro"
    },
    {
      "nome": "beta",
      "quantidadeRobos": 2,
      "controlador": "controladorSegundo"
    }
  ]
}
```
**Atributos Json:**
- **duracaoPartida:** Deve-se passar o tempo de duração de partida desejado em minutos.
- **quantidadeLinhas:** Descreve o tamanho horizontal do terreno do jogo.
- **quantidadeColunas:** Descreve o tamanho vertical do terreno do jogo.
- **nome:** Descreve o nome da equipe participante.
- **quantidadeRobos:** quantidade de robôs da equipe participante.
- **controlador:** Controlador que contem uma estratégia especifica escolhida pela equipe.
**Controladores disponíveis para escolha:** "controladorPrimeiro", "controladorSegundo", 
controladorTerceiro", "controladorQuarto"

Após preencher o json corretamente, deve-se executar o metodo *main* da classe Jogar.java.

##### **Resultados:**
No diretório src/main/java/ufsj/trabalho/output/ será gerado um relatório dos robôs das companhias participantes
com o nome da sua equipe, exemplo:alfa_relatorio_robos_gravado.txt, no caso do exemplo o nome da companhia era 
"alfa".

Será também exibido no console o estado atual do terreno a cada segundo passado e onde cada robô está localizado nele
além de mostrar o placar e a equipe vencedora.

Exemplo:
```bash
Segundo Atual Jogo 5400
	[CH:0,00|RT:0,27]	[CH:0,00|RT:0,85]	[CH:0,95|RT:0,34]	[CH:0,00|RT:0,87]	[CH:0,02|RT:0,57]

	[CH:0,00|RT:0,60]	[CH:0,00|RT:0,02]	[CH:0,00|RT:0,17]	[CH:0,00|RT:0,45]	[CH:0,00|RT:0,68]

	[CH:0,00|RT:0,45]	[CH:0,16|RT:0,92]	[CH:0,00|RT:0,06]	[CH:0,00|RT:0,13](R)	[CH:0,37|RT:0,35](R)

	[CH:0,00|RT:0,95]	[CH:0,00|RT:0,71]	[CH:0,00|RT:0,26]	[CH:0,00|RT:0,81]	[CH:0,00|RT:0,83](R)

	[CH:0,16|RT:0,33](R)	[CH:0,42|RT:0,64](R)	[CH:0,00|RT:0,55]	[CH:0,66|RT:0,68](R)	[CH:0,96|RT:0,90]



Placar:
NOME DA EQUIPE: alfa, QUANTIDADE DE ROBOS: 3, TOTAL HELIO COLETADO 18,94

NOME DA EQUIPE: beta, QUANTIDADE DE ROBOS: 3, TOTAL HELIO COLETADO 9,10

Companhia ganhadora alfa parabéns!!
```
- **(R)** ao lado da descrição de uma celula, significa que há um robô presente na mesma.

Exemplo de um relatório dos robôs, de uma respectiva equipe:
````txt
[Prospecção iniciada Robo]
[Tempo de coleta: 1 segundos]
[Prospecção iniciada Robo]
[Tempo de coleta: 2 segundos]
[Helio coletado]
Robo:[Posição Atual: (3,1) | Compartimento de Hélio: 0,33]
[Direção Atual Robo direcao atual = DIREITA]

[tempo do comando de movimento: 1 segundos]
[Direção Atual Robo direcao atual = DIREITA]

[tempo do comando de movimento: 2 segundos]
[Direção Atual Robo direcao atual = DIREITA]
```
