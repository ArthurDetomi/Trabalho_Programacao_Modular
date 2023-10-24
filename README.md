Nomes:

Classes:

-Companhia;
-CompanhiaComparator;
-Controlador;
-ControladorSegundo, ControladorTerceiro, ControladorQuarto herdam Controlador;
-ControladorUtils;
-JogoControlador;
-CompanhiaLeitura;
-EstadoJogo;
-LeitorJson;
-TerrenoLeitura;
-Direcoes;
-Movimentacao;
-Robo;
-Celula;
-CelulaAdjacente;
-Posicao;
-Terreno;
-RandomUtil.

Funções das classes:

A classe Companhia tem a função de inicializar as equipes dos robos;
A classe CompanhiaComparator compara,ao final do jogo, as Companhias participantes para saber qual delas ganhou o jogo;
Os robôs são controlados através da classe Controlador;
As classes ControladorSegundo, ControladorTerceiro e ControladorQuarto são Controladores configurados com estratégias diferentes;
A classe ControladorUtils informa qual dos Controladores que a companhia irá utilizar;
A classse JogoControlador é para controlar o jogo;
As classes CompanhiaLeitura e TerrenoLeitura são para ler as informações e inicializar as Companhias e o Terreno;
A classe EstadoJogo inicia as Companhias no terreno;
A classe LeitorJson le o arquivo .json;
A classe Direcoes guarda as direções como constantes;
A classe Movimentacao guarda as movimentações como constantes;
A classe Robo implementa o personagen do jogo, que são os Robos; 
A classe Celula guarda as informações da Celula;
A classe CelulaAdjacente guarda as informações da celula adjacente;
A classe Posicao informa a posição e também define a posição;
A classe Terreno implementa o terreno e suas informações;
A classe RadomUtil gera um número aleatório.

O arquivo input.json:

Define as dimensões do terreno, as companhias com seus respectivos nomes, quantidades de robôs e controladores para o programa ler e inicializar.
