package br.cefetmg.games.movement.behavior;

import br.cefetmg.games.movement.AlgoritmoMovimentacao;
import br.cefetmg.games.movement.Direcionamento;
import br.cefetmg.games.movement.Pose;

import com.badlogic.gdx.Input.Keys;

/**
 * Guia o agente na direção do alvo.
 *
 * @author Flávio Coutinho <fegemo@cefetmg.br>
 */
public class Buscar extends AlgoritmoMovimentacao {

	private static final char NOME = 's';

	public Buscar(float maxVelocidade) {
		this(NOME, maxVelocidade);
	}

	protected Buscar(char nome, float maxVelocidade) {
		super(nome);
		this.maxVelocidade = maxVelocidade;
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

		float epsilon = 0.5f;
		if (!agente.posicao.epsilonEquals(super.alvo.getObjetivo(), epsilon)) {
			output.velocidade = super.alvo.getObjetivo().cpy().sub(agente.posicao).nor().scl(this.maxVelocidade);
			agente.olharNaDirecaoDaVelocidade(output.velocidade);
		}
		output.rotacao = 0.0f;

		return output;
	}

	@Override
	public int getTeclaParaAtivacao() {
		return Keys.S;
	}
}
