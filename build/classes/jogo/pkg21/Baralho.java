package jogo.pkg21;

//Aluno: Matheus de Souza Sereno, N� USP: 9368491

import java.awt.*;
import java.awt.image.BufferedImage;
import java.io.*;
import java.security.SecureRandom;
import javax.imageio.ImageIO;
import javax.swing.*;

public class Baralho {
	
	private Carta[] deque;
	private int cartaAtual;  //carta a ser entregue na pr�xima jogada
	private int num_de_baralhos; //numero de baralhos juntos em um deque
	
	//Construtor de um deque de cartas com um certo n�mero de baralhos
	public Baralho(int num) throws IOException{
		
		String[] nomes = {"2", "3", "4", "5", "6", "7", "8", "9", "10", "J", "Q", "K", "A"};
		
		num_de_baralhos = num;
		deque = new Carta[num*52];
		cartaAtual = 0;
		
		/*Informa��o para extrair as 52 cartas de 1 imagem que cont�m todas.
		A imagem est� dentro da pasta do projeto. Os valores a seguir foram
		definidos empiricamente, testando o programa e encontrando o melhor
		resultado*/
		final int largura = 40;
		final int altura = 57; 
		final int linhas = 4;
		final int colunas = 13;
		
		//Altura da imagem em pixels: 278
		//Largura da imagem em pixels: 592
		
		/*Aqui declaramos a imagem que ser� recortada e a imagem
		 * tempor�ria que usaremos para atribuir a cada carta a
		 * sua respectiva imagem.
		 */
		
		BufferedImage imagem = ImageIO.read(new File("baralho.jpg"));
		BufferedImage imagemTempCarta = null;
		
		/*Nestes la�os de repeti��o recortamos as imagens de todas as cartas
		 * do baralho da figura baralho.jpg e atribuimos todos os atributos
		 * das cartas de todo o deque. Al�m disso, as salvamos em um atributo
		 *  desta classe que � um array de Cartas o qual se chama deque[]. Fazemos
		 *  isto para o n�mero de baralhos a comporem o deque.  
		  */ 
		for(int h = 0; h<num_de_baralhos; h++){
			for( int suit = 0; suit<linhas; suit++){
				for(int numero =0; numero<colunas; numero++){ 
					/*Dividiu-se o processo de recorte da imagem em algumas etapas 
					 * devido ao fato de possibilitar melhores resultados quanto ao
					 *  fato de as cartas n�o ficarem com espa�os brancos desnecess�rios
					 *   provenientes da imagem original. Por isso, que foram usados 
					 *   estes ifs para determinados valores da vari�vel numero
					 */
					if(suit != 3){
						if(numero <5)
							imagemTempCarta = imagem.getSubimage(numero*largura + 8 + (numero*5), suit*altura + (suit*12) , largura, altura );
						if(numero == 5 || numero ==6 || numero == 7)
							imagemTempCarta = imagem.getSubimage(numero*largura + 6 + (numero*5), suit*altura + (suit*12) , largura, altura );
						if(numero > 7 )
							imagemTempCarta = imagem.getSubimage(numero*largura + 4 + (numero*5), suit*altura + (suit*12) , largura, altura );
					}else{
						if(numero <5)
							imagemTempCarta = imagem.getSubimage(numero*largura + 8 + (numero*5), suit*altura + (suit*12) + 2 , largura, altura );
						if(numero == 5 || numero ==6 || numero == 7)
							imagemTempCarta = imagem.getSubimage(numero*largura + 6 + (numero*5), suit*altura + (suit*12) + 2 , largura, altura );
						if(numero > 7 )
							imagemTempCarta = imagem.getSubimage(numero*largura + 4 + (numero*5), suit*altura + (suit*12) + 2 , largura, altura );
					}
					
					//Defini��o dos valores das cartas
					
					int valor = numero+2;
					if(valor>10)
						valor = 10;
					if (numero == 12)
						valor = 11;
					
					//Defini��o das cartas do deque
					
					deque[(numero+(suit*13)+(h*52))] = new Carta(suit, valor, nomes[numero], imagemTempCarta );
				}
			}
		}
	}//fim do construtor
	
	
	
	/*M�todo que mostra as cartas em uma janela cortando-as
	 *  da imagem original. Por meio deste processo foi visto
	 *   qual eram os melhores valores para o m�todo 
	 *   getSubimage da classe BufferedImage. Depois de setados
	 *   os valores n�o seria mais necess�ria esta fun��o. A 
	 *   deixei s� para o senhor ter uma ideia de como obtive
	 *   aqueles valores empiricamente. 
	 */
	public void recortarCartas(int x, int y, int z, int w) throws IOException{
		BufferedImage imagem = ImageIO.read(new File("baralho.jpg"));
		BufferedImage imagemTempCarta;
		
		//System.out.println("Altura da imagem em pixels: " + imagem.getTileHeight());
		//System.out.println("Largura da imagem em pixels: " + imagem.getTileWidth());
		
		imagemTempCarta = imagem.getSubimage(x, y , z, w);
		
		JFrame janela = new JFrame("Imagem recortada");
		janela.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		Container conteudo = janela.getContentPane();
		JPanel p = new JPanel(new FlowLayout());
		JLabel imagemi = new JLabel(new ImageIcon(imagemTempCarta));
		imagemi.setPreferredSize(new Dimension(500,500));
		p.add(imagemi);
		conteudo.add(p);
		janela.pack();
		janela.setVisible(true);
	}//fim do m�todo
	
	
	/*M�todo que na prototipagem ajudou a visualizar 
	 * melhor os resultados do recorte das imagens de
	 * cada carta.
	 */
	public void mostrarCards(Carta card) throws IOException{
		JFrame janela = new JFrame("Carta");
		janela.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		Container conteudo = janela.getContentPane();
		JPanel p = new JPanel(new FlowLayout());
		JLabel imagemi = new JLabel(new ImageIcon(card.getCarta()));
		imagemi.setPreferredSize(new Dimension(500,500));
		p.add(imagemi);
		conteudo.add(p);
		janela.pack();
		janela.setVisible(true);
	}//fim do m�todo
	
	
	/*M�todo que printa no console o nome da carta.
	 * Este m�todo foi feito para checar o valor,
	 * nome e naipe de cada carta criada.
	 */
	public void mostrarDeque(){
		int i = 1;
		for(Carta carta : deque){
			System.out.printf("%d ", i);
			System.out.println(carta);
			i+=1;
		}
	}//fim do m�todo
	
	
	//M�todo o qual embaralha o deque de cartas de modo rand�mico
	public void embaralhar(){
		cartaAtual = 0;
		
		SecureRandom aleatorio = new SecureRandom();
		for(int i=0; i < deque.length;i++){
			int numerodesconhecido = aleatorio.nextInt(51);
			Carta aux = deque[i];
			deque[i] = deque[numerodesconhecido];
			deque[numerodesconhecido] = aux;
		}	
	}//fim do m�todo
	
	/*M�todo que entrega uma carta ao jogador 
	 * ou dealer e avan�a para a pr�xima carta
	 *  do deque. Ao final do deque ele o 
	 *  embaralha novamente
	 */
	public Carta entregaCarta(){
		if(cartaAtual<deque.length)
			return deque[cartaAtual++];//entrega a carta atual e depois acrescenta 1 ao atributo cartaAtual
		else{
			embaralhar();//embaralha o baralho logo que ele acaba 
			return deque[cartaAtual++];
		}
	}
	
	//M�todo main criado a fim de testar o c�digo
	/*public static void main(String[] args) throws IOException {
		Baralho oDeque = new Baralho(5);
		oDeque.mostrarDeque();
		oDeque.embaralhar();
		System.out.println("\nO deque depois de embaralhado ficou desta maneira: \n");
		oDeque.mostrarDeque();	
		System.out.println("Primeira carta do baralho � ");
		Carta first = oDeque.entregaCarta();
		System.out.println(first);
		oDeque.mostrarCards(first);
	}*/

	//Setters e Getters dos atributos cartaAtual e num_de_baralhos 

	public int getCartaAtual() {
		return cartaAtual;
	}



	public void setCartaAtual(int cartaAtual) {
		this.cartaAtual = cartaAtual;
	}



	public int getNum_de_baralhos() {
		return num_de_baralhos;
	}



	public void setNum_de_baralhos(int num_de_baralhos) {
		this.num_de_baralhos = num_de_baralhos;
	}
}
