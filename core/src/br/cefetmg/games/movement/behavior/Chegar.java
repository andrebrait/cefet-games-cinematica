package br.cefetmg.games.movement.behavior;

import com.badlogic.gdx.Input.Keys;

import br.cefetmg.games.movement.AlgoritmoMovimentacao;
import br.cefetmg.games.movement.Direcionamento;
import br.cefetmg.games.movement.Pose;

public class Chegar extends AlgoritmoMovimentacao {

	private static final char NOME = 'a';

	private float radius2 = 256.0f;
	private float inverseTimeToTarget = 2f;

	public Chegar(float maxVelocidade) {
		super(NOME);
		this.maxVelocidade = maxVelocidade;
	}

	public Chegar(float maxVelocidade, float radius) {
		this(maxVelocidade);
		this.radius2 = radius * radius;
	}

	public Chegar(float maxVelocidade, float radius, float timeToTarget) {
		this(maxVelocidade, radius);
		this.inverseTimeToTarget = 1 / timeToTarget;
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

		if (super.alvo.getObjetivo().dst2(agente.posicao) > radius2) {
			/*
			 * O objetivo é chegar até o raio, então devemos desacelerar quando chegarmos
			 * perto dele
			 */
			output.velocidade = super.alvo.getObjetivo().cpy().sub(agente.posicao);
			output.velocidade.setLength2(output.velocidade.len2() - radius2).scl(this.inverseTimeToTarget);
			if (output.velocidade.len2() > this.maxVelocidade * this.maxVelocidade) {
				output.velocidade.nor().scl(this.maxVelocidade);
			}
			agente.olharNaDirecaoDaVelocidade(output.velocidade);
		}
		output.rotacao = 0.0f;

		return output;
	}

	@Override
	public int getTeclaParaAtivacao() {
		return Keys.A;
	}

}
