package jogo.pkg21;

//Aluno: Matheus de Souza Sereno, N� USP: 9368491

import java.awt.Container;
import java.awt.Dimension;
import java.awt.FlowLayout;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;
import javax.imageio.ImageIO;
import javax.swing.ImageIcon;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;

public class Dealer {
	
	/**
	 * Atributos do Dealer:
	 * 
	 * pontos: pontua��o das cartas do Dealer
	 * 
	 * cartas: array de cartas do Dealer
	 * 
	 * carta_pra_baixo: imagem de uma carta para baixo,
	 * necess�ria pois o jogador s� v� uma das cartas 
	 * do dealer no in�cio de cada rodada  
	 * 
	 * contador de A: este � um contador de 
	 * quantas cartas �s o jogador possui a
	 *  fim de corrigir a pontua��o
	 * do jogador caso a carta tenha que passar
	 *  de seu valor de 11 para 1 para que o jogador
	 *  n�o ultrapasse 21 pontos
	 */
	private int pontos;
	private Carta [] cartas = new Carta [2];
	private BufferedImage carta_pra_baixo;
	private int contador_de_A = 0;
	
	/**Construtor da classe Dealer:
	 * Todo dealer possui uma imagem de carta virada para baixo!
	 * @param a = primeira carta do dealer 
	 * @param b = segunda carta do dealer
	 * @throws IOException
	 */
	public Dealer(Carta a, Carta b) throws IOException{
		//Recorte de uma imagem que possuia uma carta virada para baixo
		BufferedImage imagem = ImageIO.read(new File("baralho_com_cartaprabaixo.png"));
		carta_pra_baixo = imagem.getSubimage(148, 460 , 75, 115);
		pontos = a.getValor() + b.getValor();
		cartas[0] = a;
		cartas[1] = b;
	}
	
	/*
	 * M�todo similar ao usado na classe Baralho para visualizar 
	 * melhor o recorte da figura. Foi importante pois as imagens
	 * tem tamanhos diferentes e por isso alguns valores mudaram 
	 * em rela��o ao recorte das outras cartas do deque.
	 */
	public void recortarCartas(int x, int y, int z, int w) throws IOException{
		BufferedImage imagem = ImageIO.read(new File("baralho_com_cartaprabaixo.png"));
		BufferedImage imagemTempCarta;
		
		System.out.println("Altura da imagem em pixels: " + imagem.getTileHeight());
		System.out.println("Largura da imagem em pixels: " + imagem.getTileWidth());
		
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
	
	/**
	 * Getters e Setters dos atributos
	 * @return
	 */
	
	public int getPontos() {
		return pontos;
	}
	
	// mostra quantos pontos o Dealer tem com uma carta virada para baixo
	public int getPontosCom1CartaPraBaixo() {
		return cartas[0].getValor();
	}
	
	/*
	 * M�todo similar utilizado na classe Jogador 
	 * para somar pontos.
	 */
	public void somarPontos(int a){
		this.pontos += a ; 
	}
	
	public void setPontos() {
		this.pontos = cartas[0].getValor() + cartas[1].getValor();
		for(int i=0 ; i<2 ; i++){
			if(cartas[i].getNome().equals("A"))
				contador_de_A +=1;
		}
		if(this.getPontos()>21 && this.getContador_de_A()>0){//como calcular pontua��o se obtivermos um �s
			while(this.getContador_de_A() !=0){
				this.somarPontos(-10);
				this.setContador_de_A(-1);
				if(this.getPontos()<=21)
					break;
			}
		}
	}
	
	public BufferedImage getCarta_pra_baixo() {
		return carta_pra_baixo;
	}

	public void setCarta_pra_baixo(BufferedImage carta_pra_baixo) {
		this.carta_pra_baixo = carta_pra_baixo;
	}
	
	public Carta getCartas( int i) {
		return cartas[i];
	}
	
	public void setCartas(Carta carta1, Carta carta2) {
		this.cartas[1] = carta1;
		this.cartas[2] = carta2;
	}
	
	
	/*
	 * M�todo utilizado para mostrar as imagens das cartas
	 * do Dealer para o usu�rio atrav�s da interface gr�fica
	 */
	public BufferedImage getImagens(int i){
			return this.cartas[i].getCarta();
	}
	
	public int getContador_de_A() {
		return contador_de_A;
	}
	
	//adiciona um valor inteiro ao atributo contador de A
	public void setContador_de_A(int contador_de_A) {
		this.contador_de_A += contador_de_A;
	}

	/*M�todo que cria uma janela para mostrar todas as imagens na m�o do dealer,
	 *  sendo elas uma para baixo, outra para cima ou quando ele possui mais de 
	 *  duas cartas. Provavelmente, ir� receber um JPanel como argumento na hora
	 *  do jogo
	 */
	public void mostrarCards() throws IOException{
		JFrame janela = new JFrame("Carta");
		janela.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		Container conteudo = janela.getContentPane();
		JPanel p = new JPanel(new FlowLayout());
			JLabel imagemi = new JLabel(new ImageIcon(carta_pra_baixo));
			JLabel imagemi2 = new JLabel(new ImageIcon(this.cartas[2].getCarta()));
			imagemi.setPreferredSize(new Dimension(60,60));
			imagemi2.setPreferredSize(new Dimension(60,60));
			p.add(imagemi);
			p.add(imagemi2);
			conteudo.add(p);
			janela.pack();
			janela.setVisible(true);
	}//fim do m�todo

}
