package jogo.pkg21;

//Aluno: Matheus de Souza Sereno, N� USP: 9368491

import java.awt.Container;
import java.awt.Dimension;
import java.awt.FlowLayout;
import java.awt.image.BufferedImage;
import java.io.IOException;
import javax.swing.ImageIcon;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;

public class Jogador {
	
	/**
	 * Atributos do jogador:
	 * 
	 * nome: uma String escolhida pelo jogador
	 * 
	 * aposta: inteiro escolhido pelo usuario
	 * 
	 * dinheiro: � o dinheiro disponibilizado 
	 * para que o jogador comece a jogar
	 * 
	 * pontos: pontua��o dada pelas suas cartas
	 * 
	 * cartas: array de duas cartas que ele 
	 * recebe inicialmente do dealer
	 * 
	 * contador de A: este � um contador de 
	 * quantas cartas �s o jogador possui a
	 *  fim de corrigir a pontua��o
	 * do jogador caso a carta tenha que passar
	 *  de seu valor de 11 para 1 para que o jogador
	 *  n�o ultrapasse 21 pontos
	 */
	private String nome;
	private int aposta;
	private int dinheiro = 500;
	private int pontos = 0;
	private Carta [] cartas = new Carta[2];
	private int contador_de_A = 0;
	
	/**
	 * Getters e Setters
	 * @return
	 */
	public Jogador(String nome){
		this.nome = nome;
	}
	public String getNome() {
		return nome;
	}
	
	public int getAposta() {
		return aposta;
	}
	public void setAposta(int aposta) {
		this.aposta = aposta;
	}

	public void setNome(String nome) {
		this.nome = nome;
	}

	public int getDinheiro() {
		return dinheiro;
	}

	public void setDinheiro(int dinheiro) {
		this.dinheiro = dinheiro;
	}

	public int getPontos() {
		return pontos;
	}

	public void setPontos(int pontos) {
		this.pontos = pontos;
	}

	public int getContador_de_A() {
		return contador_de_A;
	}
	
	
	public void setContador_de_A(int contador_de_A) {
		this.contador_de_A += contador_de_A;
	}
	
	public Carta[] getCartas() {
		return cartas;
	}

	/*
	 * O jogador ao receber suas cartas j� conta se possui algum �s e
	 * tamb�m seta quantos pontos possui inicialmente. N�o � necess�ria
	 * uma implementa��o de condi��es para a situa��o de pontos > 21, pois
	 * o m�ximo de pontua��o com 2 cartas � 21
	 */
	public void setCartas(Carta card1, Carta card2) {
		this.cartas[0] = card1;
		this.cartas[1] = card2;
		this.pontos = card1.getValor() + card2.getValor();
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
	
	/*M�todo criado por comodidade por ele foi
	 * criado para a classe Dealer tamb�m. Entretanto,
	 * faz algo que poderia ser feito pelo m�todo 
	 * setPontos(int pontos).  
	 */
	public void somarPontos(int a){
		this.pontos += a ; 
	}
	
	/*M�todo que fornece a imagem de uma das
	 * duas cartas iniciais do jogador 
	 */
	public BufferedImage getImagens(int i){
		return this.cartas[i].getCarta();
}
	
	/*M�todo igual ao da classe Baralho, e que 
	 *  foi utilizada para conferir o funcionamento
	 *  dos objetos desta classe.
	 */
	public void mostrarCards() throws IOException{
		JFrame janela = new JFrame("Carta");
		janela.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		Container conteudo = janela.getContentPane();
		JPanel p = new JPanel(new FlowLayout());
			for(int i=1;i<cartas.length; i++){
				JLabel imagemi = new JLabel(new ImageIcon(this.cartas[i].getCarta()));
				imagemi.setPreferredSize(new Dimension(60,60));
				p.add(imagemi);
			}
			conteudo.add(p);
			janela.pack();
			janela.setVisible(true);
	}//fim do m�todo	
}
