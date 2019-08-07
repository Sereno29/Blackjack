package jogo.pkg21;

//Aluno: Matheus de Souza Sereno, N� USP: 9368491

import java.awt.image.BufferedImage;

public class Carta {
	private int naipe, valor;
	private String nome;
	private BufferedImage carta;

	/**
	 * 
	 * @param suit =  "espadas" = 0, "ouros" = 1, "paus" = 2, "copas" = 3
	 * @param value = 2, 3, ..., 9, 10, 10, 10, 10, 11 ou 1
	 * @param name = "2", "3", "4", "5", ..., "9", "10", "J", "Q", "K", "A"
	 * @param card = imagem que representa a carta
	 */
	
	//Construtor da classe Carta
	public Carta(int suit, int value, String name, BufferedImage card){
		naipe = suit;
		valor = value;
		nome = name;
		carta =card;
	}
	
	//Getters e setters dos atributos da classe
	
	public int getNaipe() {
		return naipe;
	}

	public void setNaipe(int naipe) {
		this.naipe = naipe;
	}

	public int getValor() {
		return valor;
	}

	public void setValor(int valor) {
		this.valor = valor;
	}

	public String getNome() {
		return nome;
	}

	public void setNome(String nome) {
		this.nome = nome;
	}

	//obtem a imagem da carta do objeto desta classe
	public BufferedImage getCarta() {
		return carta;
	}

	//define uma imagem para o objeto desta classe
	public void setCarta(BufferedImage carta) {
		this.carta = carta;
	}

	public String toString(){
		String[] naipes = {"espadas","ouros","paus","copas"};
		return "Carta: " + nome + " de " + naipes[this.naipe] + ", valor: " + this.valor;
	}
	
}
