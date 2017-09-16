package br.cefetmg.games.movement.behavior;

import br.cefetmg.games.movement.AlgoritmoMovimentacao;
import br.cefetmg.games.movement.Direcionamento;
import br.cefetmg.games.movement.Pose;

import java.util.Random;

import com.badlogic.gdx.Input.Keys;

/**
 * Movimenta o agente em uma direção aleatória, vagando pelo cenário.
 *
 * @author Flávio Coutinho <fegemo@cefetmg.br>
 */
public class Vagar extends AlgoritmoMovimentacao {

	private static final char NOME = 'w';
	private float maxAngular = 30f;

	public Vagar() {
		super(NOME);
	}

	public Vagar(float tangencial, float angular) {
		super(NOME);
		maxVelocidade = tangencial;
		maxAngular = angular;
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

		output.velocidade = agente.getOrientacaoComoVetor().cpy().nor().scl(this.maxVelocidade);
		output.rotacao = randomBinomial() * this.maxAngular;

		return output;
	}

	private float randomBinomial() {
		Random r = new Random();
		return r.nextFloat() - r.nextFloat();
	}

	@Override
	public int getTeclaParaAtivacao() {
		return Keys.W;
	}
}
