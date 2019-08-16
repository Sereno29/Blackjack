package jogo.pkg21;

//Aluno: Matheus de Souza Sereno

import java.awt.*;
import java.awt.image.BufferedImage;
import java.io.*;
import java.security.SecureRandom;
import javax.imageio.ImageIO;
import javax.swing.*;

public class Baralho {
	
        // Atributos da classe
	private Carta[] deque;
	private int cartaAtual;  // Carta a ser entregue na próxima jogada
	private int num_de_baralhos; // Número de baralhos juntos em um deque
	
	// Construtor de um deque de cartas com um certo número de baralhos
	public Baralho(int num) throws IOException{
		
		String[] nomes = {"2", "3", "4", "5", "6", "7", "8", "9", "10", "J", "Q", "K", "A"};
		
		num_de_baralhos = num;
		deque = new Carta[num*52];
		cartaAtual = 0;
		
		/* Informação para extrair as imagens individuais de 52 cartas a
                partir de 1 imagem que contém todas.
		A imagem está situada na pasta do projeto. 
                Os valores a seguir foram definidos empiricamente, testando o 
                método getSubimage pertencente à classe BufferedImage e 
                encontrando o melhor resultado visualmente */
		
                final int largura = 40;
		final int altura = 57; 
		final int linhas = 4;
		final int colunas = 13;
		
		//Altura da imagem em pixels: 278
		//Largura da imagem em pixels: 592
		
		/* Abaixo declara-se a imagem original com as 52 cartas a qual 
                 * será recortada e uma imagem auxiliar temporária que usaremos 
		 * para atribuir a cada carta sua respectiva imagem.
		 */
		
		BufferedImage imagem = ImageIO.read(new File("baralho.jpg"));
		BufferedImage imagemTempCarta = null;
		
		/* Nos laços de repetição recorta-se as imagens de todas as cartas
		 * do baralho da figura baralho.jpg e atribui-se todos os atributos
		 * das cartas de todo o deque. Além disso, as cartas são salvas em um atributo
		 *  desta classe que é um array de Cartas o qual se chamou deque[]. Fazemos
		 *  isto para o número de baralhos que forem compor o deque.  
		  */ 
		for(int h = 0; h<num_de_baralhos; h++){
			for( int suit = 0; suit<linhas; suit++){
				for(int numero =0; numero<colunas; numero++){ 
					/* Dividiu-se o processo de recorte da imagem em algumas etapas 
					 * devido ao fato de possibilitar melhores resultados quanto ao
					 * fato de as cartas não ficarem com espaços brancos desnecessários
					 * provenientes da imagem original. Por isso foram usados 
					 * ifs para determinados valores da variável número
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
					
					//Definição dos valores das cartas
					
					int valor = numero+2;
					if(valor>10)
						valor = 10;
					if (numero == 12)
						valor = 11; // Valor padrão de um Ás é 11. Caso ultrapasse o valor de 21 é modificado para 1
					
					// Atribuição das cartas no deque
					
					deque[(numero+(suit*13)+(h*52))] = new Carta(suit, valor, nomes[numero], imagemTempCarta );
				}
			}
		}
	}//fim do construtor
	
	
	
	/*  Método que mostra as cartas em uma janela cortando-as
	 *  da imagem original. Por meio deste processo foi visto
	 *  qual eram os melhores valores para o método 
	 *  getSubimage da classe BufferedImage. Depois de setados
	 *  os valores não seria mais necessária esta função. A 
	 *  deixei só para a leitora ou leitor ter uma ideia de como obtive
	 *  os valores de recorte de imagens empiricamente. 
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
	}//fim do método
	
	
	/* Método que na prototipagem ajudou a visualizar 
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
	}//fim do método
	
	
	/* Método que printa no console o nome da carta.
	 * Este método foi feito para checar o valor,
	 * nome e naipe de cada carta criada.
	 */
	public void mostrarDeque(){
		int i = 1;
		for(Carta carta : deque){
			System.out.printf("%d ", i);
			System.out.println(carta);
			i+=1;
		}
	}//fim do método
	
	
	// Método o qual embaralha o deque de cartas de modo randômico
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
	
	/* Método que entrega uma carta ao jogador 
	 * ou dealer e avança para a próxima carta
	 * do deque. Ao final do deque ele o 
	 * embaralha novamente
	 */
	public Carta entregaCarta(){
		if(cartaAtual<deque.length)
			return deque[cartaAtual++];// Entrega a carta atual e depois acrescenta 1 ao atributo cartaAtual
		else{
			embaralhar();// Embaralha o baralho logo que ele acaba 
                        this.cartaAtual = 0; // Valor é zerado para não ultrapassar limites do array de Cartas
			return deque[cartaAtual++];
		}
	}
	
	// Método main criado a fim de testar o código
	/*public static void main(String[] args) throws IOException {
		Baralho oDeque = new Baralho(5);
		oDeque.mostrarDeque();
		oDeque.embaralhar();
		System.out.println("\nO deque depois de embaralhado ficou desta maneira: \n");
		oDeque.mostrarDeque();	
		System.out.println("Primeira carta do baralho é ");
		Carta first = oDeque.entregaCarta();
		System.out.println(first);
		oDeque.mostrarCards(first);
	}*/

	// Setters e Getters dos atributos cartaAtual e num_de_baralhos 

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
