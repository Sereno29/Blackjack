package jogo.pkg21;
//Aluno: Matheus de Souza Sereno, N� USP: 9368491
import java.awt.Color;
import java.awt.Container;
import java.awt.FlowLayout;
import java.awt.GridLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.IOException;
import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JComboBox;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JTextField;

//explicar melhor porque tenho aqueles try e catch nas telas iniciais do jogo 

public class TelaDeInicio {
	
	/**
	 * Atributos da classe TelaDeInicio:
	 * janela: top-container onde todo o jogo estar� ocorrendo
	 * p: painel auxiliar para inserirmos JLabels, JButtons, JTextField e etc
	 * conteudo: container da janela
	 * count: contador respons�vel por n�o reconstruir parte da tela de in�cio
	 *  quando o jogador voltar da janela com as informa��es de regras do jogo
	 * counter: contador de rodadas do jogo em si. Ele controla o fato de certas
	 *  op��es de jogo como os bot�es dobrar e rendi��o s� s�o disponibilizados
	 *  na primeira rodada do jogo
	 */
	private JFrame janela = new JFrame("JOGO BLACKJACK");;
	private JPanel p = new JPanel();
	private Container conteudo;
	private int count = 1;
	
	
	//contrutor do tela de in�cio do jogo
	public TelaDeInicio() throws IOException{
		
		constroiTela();
	
	}
	
	/*
	 * M�todo que constroi a tela inicial do jogo com uma imagem e dois bot�es
	 */
	public void constroiTela() throws IOException{
		
		//Aplica��o do contador count
		if(count==1){ 
			janela.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
			janela.setLocation(800, 300);//determina a localiza��o da janela em uma regi�o mais ou menos central
			conteudo = janela.getContentPane();
		}

		conteudo.setLayout(new GridLayout(3,1)); //Define o tipo de Layout nesta janela do jogo
		
		JLabel imagemi = new JLabel(new ImageIcon("blackjack.jpg"));
		conteudo.add(imagemi);//Acrescenta-se uma imagem na tela
		
		JButton button1 = new JButton("Jogar Blackjack");//bot�o que providencia o come�o do jogo
		button1.addActionListener(new ActionListener(){
			public void actionPerformed(ActionEvent e){
					conteudo.removeAll();//remove todos os componentes do atributo conteudo para que ele v� vazio para sua pr�xima utiliza��o
					p.removeAll();//remove todos os componentes do atributo p para que ele v� vazio para sua pr�xima utiliza��o
					//Este tratamento de exce��o foi feito por causa da abertura de uma imagem 
					try{
					telaPreJogo();//m�todo que come�a o jogo
					}catch(IOException e0){
						button1.setText("N�o foi poss�vel ver as instru��es. Feche e abra o programa novamente");
						e0.printStackTrace();
					}
			}
		});
		conteudo.add(button1);
		
		
		JButton button2 = new JButton("Regras do jogo/Como jogar");//bot�o que mostra um texto explicativo de como jogar
		button2.addActionListener(new ActionListener(){
			public void actionPerformed(ActionEvent e){
				try {
					conteudo.removeAll();//remove todos os componentes do atributo conteudo para que ele v� vazio para sua pr�xima utiliza��o
					p.removeAll();//remove todos os componentes do atributo p para que ele v� vazio para sua pr�xima utiliza��o
					telaComoJogar();//m�todo que mostra o texto de como jogar
				} catch (IOException e1) {
					button2.setText("N�o foi poss�vel ver as instru��es. Feche e abra o programa novamente");
					e1.printStackTrace();
				}
			}
		});
		
		conteudo.add(button2);
		janela.pack();
		janela.setVisible(true);
	}//fim do m�todo
	
	//m�todo que mostra instru��es de como jogar 
	public void telaComoJogar() throws IOException{
		
		conteudo.setLayout(new FlowLayout());//Define o tipo de Layout nesta janela do jogo
		
		/*Texto explicativo de como o jogo funciona. Foi utilizada uma sintaxe de html pois foi
		 * a maneira encontrada de conseguir pular linhas em um JLabel. 
		 */
		JLabel text = new JLabel();
		text.setText("<html>OBJETIVO "+"<br/>"+"<br/>"
				+ "-> Obter uma m�o que contenha mais pontos do que a do Dealer, sem ultrapassar o limite de 21 pontos."+"<br/>"+"<br/>"
				+ " RODADA(S)" +"<br/>"+"<br/>" +"-> Ap�s o jogador apostar certa quantia de dinheiro na mesa, o Dealer lhe dar�"
				+ " 2 cartas"+"<br/>"+ " e pegar� duas cartas para si, sendo que uma estar� virada para baixo. "+"<br/>"
				+ "Como a  maior m�o do jogo � quando o Dealer possui um �s ou uma carta que vale 10 pontos,"+"<br/>"+ " ele logo checar� se "
				+ "possui um blackjack, caso isso seja verdadeiro todos os jogadores perdem,"+"<br/>"+ "caso contr�rio prosseguimos com o jogo."+"<br/>"
				+ " Ap�s esta fase, o jogador tem quatro op��es: parar, pedir, dobrar e rendi��o. Agora"+"<br/>"+ "descrevemos o que cada uma faz:"+"<br/>"
				+ "->Parar: o jogador est� satisfeito com sua m�o e n�o quer pedir mais nenhuma carta."+"<br/>"
				+ "->Pedir: o jogador deseja mais uma carta"
				+ "->Dobrar: o jogador sente que necessita de uma "+"<br/>"+ "e somente uma carta adicional,"
				+ " e gostaria de dobrar sua aposta,"+"<br/>"+ " seja esta carta adicional boa ou ruim."+"<br/>"+ " Esta op��o s� � poss�vel quando se possui somente duas cartas."+"<br/>"
				+"->Rendi��o: o jogador tem a op��o de se render quando estiver ainda com duas cartas. "+"<br/>"
				+ "Se o jogador n�o gostar de suas cartas, ele poder� se render e perder"+"<br/>"+ "somente metade das sua aposta na rodada."+"<br/>"+"<br/>"					
				+ "PONTUA��O DAS CARTAS:"+"<br/>"+"<br/>"
				+ " *** A = 11 ou 1 dependendo da situa��o, no caso das cartas somarem mais do que 21 pontos"+"<br/>"
				+ " *** J, Q, K, 10 = 10 pontos "+"<br/>"
				+ "*** Cartas com valores como 2, 3, 4, ... = n�mero da carta � igual ao seu n�mero de pontos</html>");
		
		/*
		 * Bot�o que leva o jogador novamente para a tela inicial do jogo
		 */
		JButton but3 = new JButton("Voltar");
		but3.addActionListener(new ActionListener(){
			public void actionPerformed(ActionEvent e){
				try {
					conteudo.removeAll();
					p.removeAll();
					count ++;//o contador � alterado para evitar a redeclara��o de aspectos da janela que n�o iriam mudar
					constroiTela();//m�todo da tela inicial
				} catch (IOException e1) {
					but3.setText("N�o foi poss�vel voltar. Feche e abra o programa novamente");
					e1.printStackTrace();
				}
			}
		});
		conteudo.add(text);
		conteudo.add(but3);
		
		janela.pack();
		janela.setVisible(true);
	}
	

	/*
	 * Tela de jogo na qual o jogador pode fornecer o seu nome e indicar com quantos baralhos juntos deseja jogar
	 * M�todo para setar dificuldade e nome do jogador 
	 */
	
	public void telaPreJogo() throws IOException{
		
		//Layout do container da janela usada
		conteudo.setLayout(new GridLayout(3,1));
		
		//Bot�o para ir � tela de aposta
		JButton jogar = new JButton("Jogar");
		
		//Linha 1 obt�m o nome do jogador para o in�cio do jogo
		JTextField entrada = new JTextField(20);
		JLabel text1 = new JLabel("Nome do jogador:");
		JPanel nome_do_usuario = new JPanel(new FlowLayout());
		nome_do_usuario.add(text1);
		nome_do_usuario.add(entrada);
		conteudo.add(nome_do_usuario);
		
		/*
		 * Linha 2 obt�m o n�mero de baralhos que o jogador gostaria de jogar e
		 *  adiciona um bot�o para confirmar este dado e o nome do jogador. O nome
		 *  pode ser uma String nula. O bot�o tamb�m habilita o jogador a come�ar o jogo,
		 *  ou seja o jogador n�o poder� come�ar o jogo at� que confirme qual a dificuldade
		 *  que deseja jogar por meio do clique no bot�o "Adicionar dificuldade/nome"
		 */
		JLabel text2 = new JLabel("N�mero de baralhos / Dificuldade:");
		String [] numeros = {"1","2","3","4","5","6","7","8"};
		JComboBox<String> numbaralhos = new JComboBox<>(numeros);
		numbaralhos.setSelectedIndex(0);
		JPanel linha2 = new JPanel(new FlowLayout());
		linha2.add(text2);
		linha2.add(numbaralhos);
		JButton adicionar_nome = new JButton("Adicionar dificuldade/nome");
		//Mensagem que mostra para o usu�rio as suas informa��es
		JLabel informacao_dinheiro = new JLabel("O jogador " + entrada.getText() +" come�ar� o jogo com R$500! E com " + (numbaralhos.getSelectedIndex()+1) + " baralhos de dificuldade. Boa sorte!");
		//Tratamento de eventos do bot�o adicionar_nome utilizando classes internas an�nimas
		adicionar_nome.addActionListener(new ActionListener(){
			public void actionPerformed(ActionEvent e){
				informacao_dinheiro.setText("O jogador " + entrada.getText() +" come�ar� o jogo com R$500! E com " + (numbaralhos.getSelectedIndex()+1) + " baralhos de dificuldade. Boa sorte!");
				jogar.setEnabled(true);//habilita o bot�o "Jogar"
				janela.pack();
			}
		});
		linha2.add(adicionar_nome);
		conteudo.add(linha2);
		
		/*
		 * Linha 3- Possui o bot�o de Jogar e uma mensagem sobre o n�vel de
		 * dificuldade do jogo e do nome escolhido pelo usu�rio
		 */
		JPanel linha3 = new JPanel(new FlowLayout());
		jogar.setEnabled(false);//inicialmente est� desabilitado
		//Tratamento de evento do bot�o jogar utilizando classes internas an�nimas
		jogar.addActionListener(new ActionListener(){
			
			public void actionPerformed(ActionEvent e){
				//mensagem dos dados fornecidos
				informacao_dinheiro.setText("O jogador " + entrada.getText() +" come�ar� o jogo com R$500! E com " + (numbaralhos.getSelectedIndex()+1) + " baralhos de dificuldade. Boa sorte!");
				//instanciando um objeto da classe Jogador e definido seu atributo nome
				Jogador player = new Jogador(entrada.getText());
				conteudo.removeAll();
				p.removeAll();
				try{
					//instanciando um objeto da classe Baralho com o n�mero de baralhos escolhidos para o deque
					Baralho bar = new Baralho(numbaralhos.getSelectedIndex()+1);
					bar.embaralhar();//embaralhamos o deque duas vezes por seguran�a
					bar.embaralhar();
					telaDeAposta(bar, player);//chamada do m�todo que mostra uma tela a fim do usu�rio escolher quanto quer apostar em sua rodada
				}catch(IOException e4){
					informacao_dinheiro.setText("N�o foi possivel entrar no jogo, por favor tente de novo ap�s reiniciar o jogo!");
					e4.printStackTrace();
				}
			}
		});
		
		linha3.add(jogar);
		linha3.add(informacao_dinheiro);
		conteudo.add(linha3);
		
		janela.pack();
		janela.setVisible(true);
		
	}
	
	//m�todo principal do jogo
	public void telaDeJogo( Baralho bar, Jogador player ) throws IOException{
		
		conteudo.removeAll();
		p.removeAll();

		
		
		/*
		 * entregando as cartas do jogador e do Dealer, intercalando cada carta para cada um.
		 *  Setando as cartas de ambos e instanciando um Dealer.
		 */
		Carta aux1 = bar.entregaCarta();
		Carta aux2 = bar.entregaCarta();
		player.setCartas(aux1 , bar.entregaCarta());
		Dealer deal = new Dealer (aux2, bar.entregaCarta());
		
		//setando o layout e onde a janela deve aparecer na tela
		janela.setLocation(600, 300);
		conteudo.setLayout(new GridLayout(1,2));
		
		//estrutura gr�fica do jogo
		//painel de cor verde que representa uma "mesa". Ser� onde aparecer�o informa��es como que ganhou e quanto ganhou
		JPanel mesa = new JPanel();
		JLabel mensagem = new JLabel("Aperte uma op��o para iniciar a pr�xima rodada");
		mesa.setBackground(Color.green);
		mesa.add(mensagem);
		
		//Painel de informa��o que mostra quanto foi apostado, quanto dinheiro ainda resta ao jogador, seus pontos e os pontos do jogador 
		JPanel informacao = new JPanel(new FlowLayout());
		informacao.setBackground(Color.LIGHT_GRAY);
		JLabel money = new JLabel("R$ " + player.getDinheiro() + "	|||		");
		JLabel pontos = new JLabel("PONTOS: " + player.getPontos() + "	|||  Pontos do Dealer:	" + deal.getPontosCom1CartaPraBaixo());
		JLabel aposta = new JLabel("Dinheiro apostado por " + player.getNome()+ " na quantia de R$ " + player.getAposta() + "    ");
		informacao.add(money);
		informacao.add(pontos);
		informacao.add(aposta);
		
		
		
		// Bot�es do jogo
		
		JButton botao1 = new JButton("Parar");
		JButton botao2 = new JButton("Pedir");
		JButton botao3 = new JButton("Dobrar");
		JButton botao4 = new JButton("Rendi��o");
		JButton botao5 = new JButton("Apostar");
		
		//Elementos gr�ficos necess�rios para o programa 
		
		JPanel maoDealer = new JPanel (new FlowLayout());//imagem
		JLabel cartaDealer;//imagens
		JLabel text;
		JLabel cartaPlayer;//imagem2
		JLabel text2;
		
		/*
		 * Este if checa se o dealer tem um BlackJack na primeira rodada.
		 *  Se isto ocorrer, escolheu-se que o jogador perder� automaticamente.
		 */
		if(deal.getPontos() == 21){
			//mostra as imagens das duas cartas do dealer
			cartaDealer = new JLabel(new ImageIcon(deal.getImagens(0)));
			maoDealer.add(cartaDealer);
			cartaDealer = new JLabel(new ImageIcon(deal.getImagens(1)));
			maoDealer.add(cartaDealer);
			text = new JLabel("M�o do dealer");
			maoDealer.add(text);
			//mostra as cartas do jogador
			JPanel maoPlayer = new JPanel (new FlowLayout());//imagens 2
			cartaPlayer = new JLabel(new ImageIcon(player.getImagens(0)));
			maoPlayer.add(cartaPlayer);
			cartaPlayer = new JLabel(new ImageIcon(player.getImagens(1)));
			maoPlayer.add(cartaPlayer);
			text2 = new JLabel("M�o do jogador");
			maoPlayer.add(text2);
			
			
			JPanel botoes = new JPanel(new GridLayout(5,1));//painel dos bot�es
			//desabilitando todos os bot�es menos o de 
			botao1.setEnabled(false);
			botao2.setEnabled(false);
			botao3.setEnabled(false);
			botao4.setEnabled(false);
			//tratamento de evento por classes internas an�nimas
			botao5.addActionListener(new ActionListener(){//como o jogador j� perdeu ele s� tem a op��o de apostar um novo valor
				public void actionPerformed(ActionEvent e){
					if(player.getDinheiro()==0){
						mensagem.setText("GAME OVER! Boa sorte na pr�xima vez. Reinicie o jogo para jogar de novo.");
						botao5.setEnabled(false);
					}else{
						conteudo.removeAll();
						p.removeAll();
						telaDeAposta(bar, player);
					}
				}
			});
			
			//atualizando as mensagens informativas na interface, mostra uma mensagem de quem ganhou na mesa
			mensagem.setText("O dealer obteve um BlackJack. Aperte apostar para jogar novamente. Voc� perdeu " +  (player.getAposta()) + " reais");
			aposta.setText("Dinheiro apostado por " + player.getNome()+ " na quantia de R$ " + player.getAposta() + "    ");
			pontos.setText("PONTOS: " + player.getPontos() + "	|||  Pontos do Dealer:	" + deal.getPontos());
			
			//adicionando os bot�es da interface 
			botoes.add(botao5);
			botoes.add(botao1);
			botoes.add(botao2);
			botoes.add(botao3);
			botoes.add(botao4);
			
			JPanel mesaecartas = new JPanel(new GridLayout(4,1));
			mesaecartas.add(maoDealer);
			mesaecartas.add(mesa);
			mesaecartas.add(informacao);
			mesaecartas.add(maoPlayer);
			
			
			
			conteudo.add(mesaecartas);
			conteudo.add(botoes);
			
			janela.pack();
			janela.setVisible(true);
			
			
		}else{//caso o dealer n�o ganhe de primeira um blackjack
				
			if(player.getPontos()==21){//se o jogador obtiver um BlackJack de primeira 
				//desabilitamos todos os bot�es menos o de aposta
				botao1.setEnabled(false);
				botao2.setEnabled(false);
				botao3.setEnabled(false);
				botao4.setEnabled(false);
				botao5.setEnabled(true);
				//fazemos uma chamada para o m�todo que mostra se o dealer ganhou
				int resultado =  dealerSucesso(bar, player.getPontos(), deal, maoDealer, pontos);
				if(resultado == 0){//se o dealer fracassou
					mensagem.setText("Voc� venceu " +  (2*player.getAposta()) + " reais");//jogador ganha duas vezes o que apostou
					player.setDinheiro(player.getDinheiro() + 2 * player.getAposta());
					aposta.setText("Dinheiro apostado por " + player.getNome()+ " na quantia de R$ " + player.getAposta() + "    ");
					pontos.setText("PONTOS: " + player.getPontos() + "	|||  Pontos do Dealer:	" + deal.getPontos());
					money.setText("R$ " + player.getDinheiro() + "	|||		");
				}else{//se o dealer obteve sucesso 
					mensagem.setText("Voc� perdeu " +  (player.getAposta()) + " reais");//jogador perde o que apostou
					aposta.setText("Dinheiro apostado por " + player.getNome()+ " na quantia de R$ " + player.getAposta() + "    ");
					pontos.setText("PONTOS: " + player.getPontos() + "	|||  Pontos do Dealer:	" + deal.getPontos());
				}
			}else{
				
				//mensagem informativa 
				mensagem.setText("Aperte uma op��o para iniciar esta rodada");
				//Setamos as cartas do Dealer para aparecer na tela. Sempre exibimos a primeira carta, e a segunda est� de cabe�a para baixo 
				cartaDealer = new JLabel(new ImageIcon(deal.getImagens(0)));
				maoDealer.add(cartaDealer);
				cartaDealer = new JLabel(new ImageIcon(deal.getCarta_pra_baixo()));
				maoDealer.add(cartaDealer);
				text = new JLabel("M�o do dealer");
				maoDealer.add(text);
				//Setamos a m�o do jogador para aparecer na tela
				JPanel maoPlayer = new JPanel (new FlowLayout());
				cartaPlayer = new JLabel(new ImageIcon(player.getImagens(0)));
				maoPlayer.add(cartaPlayer);
				cartaPlayer = new JLabel(new ImageIcon(player.getImagens(1)));
				maoPlayer.add(cartaPlayer);
				text2 = new JLabel("M�o do jogador");
				maoPlayer.add(text2);
					
					
					
				//Layout dos bot�es
				JPanel botoes = new JPanel(new GridLayout(5,1));
					
				//Tratamento de evento do bot�o da op��o Parar
				botao1.addActionListener(new ActionListener(){ 
					public void actionPerformed(ActionEvent e){
						//Desabilita todos os bot�es menos o de aposta
						botao1.setEnabled(false);
						botao2.setEnabled(false);
						botao3.setEnabled(false);
						botao4.setEnabled(false);
						botao5.setEnabled(true);
						//fazemos uma chamada para o m�todo que mostra se o dealer ganhou
						int resultado =  dealerSucesso(bar, player.getPontos(), deal, maoDealer, pontos);
						if(resultado == 0){//dealer fracassou
							mensagem.setText("Voc� venceu " +  (2*player.getAposta()) + " reais");//jogador ganha duas vezes o que apostou
							player.setDinheiro(player.getDinheiro() + 2 * player.getAposta());
							aposta.setText("Dinheiro apostado por " + player.getNome()+ " na quantia de R$ " + player.getAposta() + "    ");
							pontos.setText("PONTOS: " + player.getPontos() + "	|||  Pontos do Dealer:	" + deal.getPontos());
							money.setText("R$ " + player.getDinheiro() + "	|||		");
						}else{//dealer obteve sucesso
							mensagem.setText("Voc� perdeu " +  (player.getAposta()) + " reais");//jogardor perde o que apostou
							aposta.setText("Dinheiro apostado por " + player.getNome()+ " na quantia de R$ " + player.getAposta() + "    ");
							pontos.setText("PONTOS: " + player.getPontos() + "	|||  Pontos do Dealer:	" + deal.getPontos());
						}
					}
				});
								
						
					
					
				//Tratamento de evento do bot�o da op��o Pedir
				botao2.addActionListener(new ActionListener(){
					public void actionPerformed(ActionEvent e){
						botao3.setEnabled(false);
						botao4.setEnabled(false);
						Carta aux = bar.entregaCarta();//nova carta � entregue ao jogador
						if(aux.getNome().equals("A")){//checagem de como deve-se atuar caso um �s esteja na m�o do jogador
							player.setContador_de_A(1);//este m�todo soma um ao atributo contador_de_A
							if(player.getPontos() + 11 > 21)
								player.somarPontos(1);
							else
								player.somarPontos(aux.getValor());
						}else{//situa��o em que n�o recebemos um �s
							player.somarPontos(aux.getValor());
						}
						if(player.getPontos()>21 && player.getContador_de_A()>0){//como calcular pontua��o se obtivermos um �s
							while(player.getContador_de_A() != 0){
								player.somarPontos(-10);
								player.setContador_de_A(-1);
								if(player.getPontos()<=21)
									break;
							}
						}
						aposta.setText("Dinheiro apostado por " + player.getNome()+ " na quantia de R$ " + player.getAposta() + "    ");
						pontos.setText("PONTOS: " + player.getPontos() + "	|||  Pontos do Dealer:	" + deal.getPontosCom1CartaPraBaixo());
						money.setText("R$ " + player.getDinheiro() + "	|||		");
						JLabel carta = new JLabel(new ImageIcon(aux.getCarta()));
						maoPlayer.add(carta);
						if (player.getPontos()>21){//se a pontua��o do jogador for maior que 21 = derrota
							mensagem.setText("Voc� perdeu" + player.getAposta() + "reais");
							botao1.setEnabled(false);
							botao2.setEnabled(false);
							botao3.setEnabled(false);
							botao4.setEnabled(false);
							botao5.setEnabled(true);
							//a segunda carta do dealer � mostrada
							carta = new JLabel(new ImageIcon(deal.getImagens(1)));
							maoDealer.add(carta) ;
						}
						if(player.getPontos()==21){// se o jogador obtiver a pontua��o m�xima
							botao1.setEnabled(false);
							botao2.setEnabled(false);
							botao3.setEnabled(false);
							botao4.setEnabled(false);
							botao5.setEnabled(false);
							//fazemos uma chamada para o m�todo que mostra se o dealer ganhou
							int resultado =  dealerSucesso(bar, player.getPontos(), deal, maoDealer, pontos);
							if(resultado == 0){//o dealer fracassou
								mensagem.setText("Voc� venceu " +  (2*player.getAposta()) + " reais");
								player.setDinheiro(player.getDinheiro() + 2 * player.getAposta());
								aposta.setText("Dinheiro apostado por " + player.getNome()+ " na quantia de R$ " + player.getAposta() + "    ");
								pontos.setText("PONTOS: " + player.getPontos() + "	|||  Pontos do Dealer:	" + deal.getPontos());
								money.setText("R$ " + player.getDinheiro() + "	|||		");
							}else{//o dealer obteve sucesso
								mensagem.setText("Voc� perdeu " +  (player.getAposta()) + " reais");
								aposta.setText("Dinheiro apostado por " + player.getNome()+ " na quantia de R$ " + player.getAposta() + "    ");
								pontos.setText("PONTOS: " + player.getPontos() + "	|||  Pontos do Dealer:	" + deal.getPontos());
							}
							//habilitar o bot�o de aposta para uma nova rodada
							botao5.setEnabled(true);
						}
						janela.pack();
							
					}
				});
					
				//Tratamento de evento do bot�o da op��o Dobrar
				botao3.addActionListener(new ActionListener(){
					public void actionPerformed(ActionEvent e){
						if(player.getDinheiro() >= player.getAposta()){
							botao1.setEnabled(false);
							botao2.setEnabled(false);
							botao3.setEnabled(false);
							botao4.setEnabled(false);
							botao5.setEnabled(true);
							player.setDinheiro(player.getDinheiro()-player.getAposta());//retira o valor de dinheiro da aposta do jogador
							player.setAposta(2*player.getAposta());//dobra o valor da aposta
							//nova carta ser� dada ao jogadore mostrada na janela
							Carta aux = bar.entregaCarta();
							JLabel carta = new JLabel(new ImageIcon(aux.getCarta()));	
							maoPlayer.add(carta);
							if(aux.getNome().equals("A")){//checagem de como deve-se atuar caso um �s esteja na m�o do jogador
								player.setContador_de_A(1);//este m�todo soma um ao atributo contador_de_A
								if(player.getPontos() + 11 > 21)
									player.somarPontos(1);
								else
									player.somarPontos(aux.getValor());
							}else{//situa��o em que n�o recebemos um �s
								player.somarPontos(aux.getValor());
							}
							if(player.getPontos()>21 && player.getContador_de_A()>0){//como calcular pontua��o se obtivermos um �s
								while(player.getContador_de_A() != 0){
									player.somarPontos(-10);
									player.setContador_de_A(-1);
									if(player.getPontos()<=21)
										break;
								}
							}
							aposta.setText("Dinheiro apostado por " + player.getNome()+ " na quantia de R$ " + player.getAposta() + ", o valor antes de dobrar era " + (player.getAposta()/2));
							pontos.setText("PONTOS: " + player.getPontos() +"	|||  Pontos do Dealer:	" + deal.getPontos());
							money.setText("R$ " + player.getDinheiro() + "	|||		");
							if (player.getPontos()>21){//se a pontua��o do jogador for maior que 21 = derrota
								mensagem.setText("Voc� perdeu " +  (player.getAposta()) + " reais");
								aposta.setText("Dinheiro apostado por " + player.getNome()+ " na quantia de R$ " + player.getAposta() + ", o valor antes de dobrar era " + (player.getAposta()/2));
								pontos.setText("PONTOS: " + player.getPontos() + "	|||  Pontos do Dealer:	" + deal.getPontos());
							}else{
								//fazemos uma chamada para o m�todo que mostra se o dealer ganhou
								int resultado =  dealerSucesso(bar, player.getPontos(), deal, maoDealer, pontos);
								if(resultado == 0){//o dealer fracassou
									mensagem.setText("Voc� venceu " +  (2*player.getAposta()) + " reais");
									player.setDinheiro(player.getDinheiro() + 2 * player.getAposta());
									aposta.setText("Dinheiro apostado por " + player.getNome()+ " na quantia de R$ " + player.getAposta() + ", o valor antes de dobrar era " + (player.getAposta()/2));
									pontos.setText("PONTOS: " + player.getPontos() + "	|||  Pontos do Dealer:	" + deal.getPontos());
									money.setText("R$ " + player.getDinheiro() + "	|||		");
								}else{//o dealer obteve sucesso
									mensagem.setText("Voc� perdeu " +  (player.getAposta()) + " reais");
									aposta.setText("Dinheiro apostado por " + player.getNome()+ " na quantia de R$ " + player.getAposta() + ", o valor antes de dobrar era " + (player.getAposta()/2));
									pontos.setText("PONTOS: " + player.getPontos() + "	|||  Pontos do Dealer:	" + deal.getPontos());
								}
							}
						}else{
							botao3.setText("Voc� n�o possui dinheiro suficiente!");
							botao3.setEnabled(false);
						}
						janela.pack();
					}
					
					
				});
					
				//Tratamento de evento do bot�o da op��o Rendi��o	
				botao4.addActionListener(new ActionListener(){
					public void actionPerformed(ActionEvent e){
						player.setDinheiro(player.getDinheiro()+(player.getAposta()/2));//jogador somente perde metade do dinheiro que apostou
						pontos.setText("PONTOS: " + player.getPontos() + "	|||  Pontos do Dealer:	" + deal.getPontos());//mostra dados para todos
						//desabilita todos os bot�es menos o de aposta
						botao1.setEnabled(false);
						botao2.setEnabled(false);
						botao3.setEnabled(false);
						botao4.setEnabled(false);
						botao5.setEnabled(true);
						//mostra a carta virada para baixo 
						JLabel carta = new JLabel(new ImageIcon(deal.getImagens(1)));
						maoDealer.add(carta) ;
						//atualiza o valor de dinheiro do jogador
						money.setText("R$ " + player.getDinheiro() + "	|||		");
						mensagem.setText("Voc� se rendeu! Voc� perdeu R$" + (player.getAposta()/2) );
						}
					});
					
				
				//desabilita o bot�o de aposta logo depois de vir da tela de aposta
				botao5.setEnabled(false);
				//Tratamento de evento do bot�o da op��o Apostar usando classes internas an�nimas
				botao5.addActionListener(new ActionListener(){
					public void actionPerformed(ActionEvent e){
						if(player.getDinheiro()==0){//se o jogador n�o tiver mais dinheiro, o jogador n�o tem mais o que fazer. deve fechar o jogo e come�ar de novo
							mensagem.setText("GAME OVER! Boa sorte na pr�xima vez. Reinicie o jogo para jogar de novo.");
							botao5.setEnabled(false);
						}else{
							conteudo.removeAll();
							p.removeAll();
							telaDeAposta(bar, player);//� redirecionado para o m�todo que computa uma nova aposta
						}
					}
				});
				
				//adiciona os bot�es na interface gr�fica
				
				botoes.add(botao5);
				botoes.add(botao1);
				botoes.add(botao2);
				botoes.add(botao3);
				botoes.add(botao4);
					
				//adiciona o componentes da mesa, informa��es e imagens das carta do jogador e do dealer em um certo layout
				JPanel mesaecartas = new JPanel(new GridLayout(4,1));
				mesaecartas.add(maoDealer);
				mesaecartas.add(mesa);
				mesaecartas.add(informacao);
				mesaecartas.add(maoPlayer);
					
					
					
				conteudo.add(mesaecartas);
				conteudo.add(botoes);				
					
				janela.pack();
				janela.setVisible(true);
			}
			
		}
	}
	
	
	//m�todo para a setar o valor apostado em cada rodada
		public void telaDeAposta(Baralho bar, Jogador player){
			
			conteudo.removeAll();//remove todos os componentes do Container
			conteudo.setLayout(new GridLayout(2,1));//definindo o Layout da janela
			conteudo.setBackground(Color.WHITE);//definindo a cor de fundo da janela
			
			/*
			 * Definindo uma linha na qual poderemos entrar com o n�mero de reais
			 *  que queremos apostar e um texto informando como deve-se entrar com
			 *  os dados e quantos reais temos. E uma outra linha na qual teremos
			 *  um bot�o para que o usu�rio confirme que quer aquele valor para
			 *  sua aposta.
			 */
			JTextField entrada = new JTextField(20);
			JLabel text1 = new JLabel("Aposte uma quantia em reais(R$), utilize numeros inteiros, por favor. Voc� tem R$ " + player.getDinheiro() + " :");
			JButton apostar = new JButton("Apostar");
			p = new JPanel(new FlowLayout());
			//Tratamento de evento do bot�o Apostar utilizando classes internas an�nimas
			apostar.addActionListener(new ActionListener(){
				
				public void actionPerformed(ActionEvent e){
					/*
					 * O m�todo Integer.parseint joga(throws) uma exce��o do tipo NumberFormatException se n�o conseguir transformar a String em um int, propriamente
					 */
					try{
						player.setAposta(Integer.parseInt(entrada.getText()));//passando a String do JTextField para um inteiro
						if(player.getAposta() > player.getDinheiro() || player.getAposta()<=0){//se o jogador tentar apostar mais do que tem ou menos que zero, o informamos de que n�o pode fazer isso
							janela.setLocation(500, 400);
							apostar.setText("Este valor de aposta n�o � valido. Tente novamente. Voc� possui R$" + player.getDinheiro());
							janela.pack();
						}else{
							player.setDinheiro(player.getDinheiro() - player.getAposta());//aceita o valor da aposta e j� o retira do atributo dinheiro do jogador
							try{
								conteudo.removeAll();
								p.removeAll();
								telaDeJogo(bar, player);//m�todo que leva ao jogo em si
							}catch (IOException ex){
								apostar.setText("N�o foi poss�vel jogar. Feche e abra o programa novamente");
							}
						}
					}catch( NumberFormatException a){
						apostar.setText("N�o foi poss�vel interpretar a entrada como um n�mero inteiro. Tente novamente");//mensagem de aviso ao usu�rio por n�o ter entrado com um valor inteiro
						janela.pack();
					}
				}
			});
			
			p.add(text1);
			p.add(entrada);
			
			conteudo.add(p);
			conteudo.add(apostar);
			
			janela.pack();
		}

		
	/*
	 * m�todo que mostra como o dealer ir� se 
	 * comportar de acordo com certa pontua��o 
	 * do jogador. Ele recebe a pontua��o do jogador,
	 * o objeto da classe dealer e alguns aspectos gr�ficos
	 * a fim de mostrar o que ocorreu na jogada
	 * do dealer.
	 */
	//Fun��o que faz o dealer tomar suas a��es de acordo com o que o jogador tenha feito
	
	public int dealerSucesso(Baralho bar , int playerPontos, Dealer deal , JPanel maoDealer, JLabel pontos){
		deal.setPontos();//calcula os pontos do dealer para suas duas cartas iniciais e v� se ele possui algum �s
		JLabel imagem_carta = new JLabel(new ImageIcon(deal.getImagens(1)));
		maoDealer.add(imagem_carta);
		while(deal.getPontos() < playerPontos){//enquanto a m�o do dealer for inferior ao do jogador ele continuar� pegando novas cartas  
				Carta aux = bar.entregaCarta();
				imagem_carta = new JLabel(new ImageIcon(aux.getCarta()));
				maoDealer.add(imagem_carta);//acrescentamos uma nova imagem na tela para o dealer
				if(aux.getNome().equals("A")){//checagem de como deve-se atuar caso um �s esteja na m�o do jogador
					deal.setContador_de_A(1);
					if(deal.getPontos() + 11 > 21)
						deal.somarPontos(1);
					else
					deal.somarPontos(aux.getValor());
				}else{//situa��o em que n�o recebemos um �s
				deal.somarPontos(aux.getValor());
				}
				if(deal.getPontos()>21 && deal.getContador_de_A()>0){//como calcular pontua��o se obtivermos um �s
					while(deal.getContador_de_A() !=0){
						deal.somarPontos(-10);
						deal.setContador_de_A(-1);
						if(deal.getPontos()<=21)
							break;
					}
				}
				//imprimimos a atualiza��o dos pontos
				pontos.setText("PONTOS: " + playerPontos + "	|||  Pontos do Dealer:	" + deal.getPontos());
		}
		/*
		 * retorna-se o resultado do m�todo: 
		 * 1 = vitoria do dealer
		 * 0 = derrota do dealer
		 */
		if(deal.getPontos() >= playerPontos && deal.getPontos() <= 21){
			return 1 ;
		}else{
			return 0;
		}
		
	}	

	//main para instanciar um objeto da classe TeladeIn�cio 
	public static void main(String[] args) throws IOException {
		new TelaDeInicio();
	}
	
}
