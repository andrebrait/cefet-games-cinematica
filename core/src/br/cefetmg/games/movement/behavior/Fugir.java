package br.cefetmg.games.movement.behavior;

import br.cefetmg.games.movement.AlgoritmoMovimentacao;
import br.cefetmg.games.movement.Direcionamento;
import br.cefetmg.games.movement.Pose;

import java.util.Random;

import com.badlogic.gdx.Input.Keys;

/**
 * Guia o agente de forma a fugir na direção contrária ao alvo.
 *
 * @author Flávio Coutinho <fegemo@cefetmg.br>
 */
public class Fugir extends AlgoritmoMovimentacao {

	private static final char NOME = 'f';

	private float maxAngular = 30f;
	private float radius2 = 1024.0f;

	public Fugir(float maxVelocidade) {
		super(NOME);
		this.maxVelocidade = maxVelocidade;
	}

	public Fugir(float maxVelocidade, float maxAngular) {
		this(maxVelocidade);
		this.maxAngular = maxAngular;
	}

	public Fugir(float maxVelocidade, float maxAngular, float radius) {
		this(maxVelocidade, maxAngular);
		this.radius2 = radius * radius;
	}

	@Override
	public Direcionamento guiar(Pose agente) {
		Direcionamento output = new Direcionamento();

		// calcula que direção tomar (configura um objeto Direcionamento
		// e o retorna)
		// ...
		// super.alvo já contém a posição do alvo
		// agente (parâmetro) é a pose do agente que estamos guiando
		// ...
		
		System.out.println(radius2);
		System.out.println(super.alvo.getObjetivo().dst2(agente.posicao));

		if (super.alvo.getObjetivo().dst2(agente.posicao) <= radius2) {
			/*
			 * Caso esteja dentro do raio de fuga, fugir.
			 */
			output.velocidade = super.alvo.getObjetivo().cpy().sub(agente.posicao).nor().scl(-this.maxVelocidade);

			/*
			 * Se por acaso o agente for instanciado nas proximidades do alvo, tomar
			 * velocidade aleatória.
			 */
			if (output.velocidade.len2() < Math.ulp(this.maxVelocidade * this.maxVelocidade)) {
				output.velocidade.setToRandomDirection().scl(this.maxVelocidade);
			}

			output.rotacao = 0.0f;
			agente.olharNaDirecaoDaVelocidade(output.velocidade);
		} else {
			/*
			 * Caso contrário, vagar por aí.
			 */
			output.velocidade = agente.getOrientacaoComoVetor().cpy().nor().scl(this.maxVelocidade);
			output.rotacao = randomBinomial() * this.maxAngular;
		}

		return output;
	}

	private float randomBinomial() {
		Random r = new Random();
		return r.nextFloat() - r.nextFloat();
	}

	@Override
	public int getTeclaParaAtivacao() {
		return Keys.F;
	}

}
