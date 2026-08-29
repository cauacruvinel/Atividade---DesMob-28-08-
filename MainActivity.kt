/*
*   O código-fonte em questão trata-se de um aplicativo simples em Kotlin, com a utilização de recursos em .xml para interface gráfica
* que é um quiz simples sobre curiosidades relacionadas ao Estado de Goiás.
*
* Ele possui 6 questões, com as opções de resposta sendo booleanas (Verdadeiro ou Falso),
* e um botão que dispara uma ação de trocar para a próxima questão.
*
* O app também tem um feedback na parte inferior da tela (Toast), se a resposta dada pelo usuário está correta ou incorreta.
* */

package com.example.appquiz

import android.os.Bundle
import android.view.View
import android.widget.Toast
import androidx.activity.enableEdgeToEdge
import androidx.appcompat.app.AppCompatActivity
import androidx.core.view.ViewCompat
import androidx.core.view.WindowInsetsCompat
import com.example.appquiz.databinding.ActivityMainBinding

/*
*   A classe MainActivity executa todas as ações previstas pelo projeto,
* com base nos arquivos mais importantes (os que contém as informações que serão exibidas na tela, e disparadores de eventos).
*
* Esta classe atua como o Controlador (Controller) na arquitetura básica do Android,
* gerenciando a interação do usuário com a interface definida no XML.
* */
class MainActivity : AppCompatActivity() {
    /**
     * Objeto de ligação (Binding) que provê acesso direto às views do layout activity_main.xml.
     * É inicializado no [onCreate] e evita o uso custoso e inseguro de findViewById.
     */
    private lateinit var binding: ActivityMainBinding

    /**
     * Lista imutável que serve como o banco de dados local do aplicativo.
     * Contém objetos do tipo [Question], vinculando IDs de recursos de string às respostas corretas.
     *
     * A linha abaixo é um vetor, o qual é o Banco de Questões do app.
     * Ele faz referência à data class Question, que possui dois atributos: textResId e answer,
     * os quais faz a chamada dos recursos (Resources), representados pela letra "R" na chamada.
     *
     * Essa chamada é padrão para a manipulação dos arquivos no diretório "res".
     * Por fim, o argumento answer define se a questão tem uma resposta verdadeira ou falsa.
     */
    private val questionBank = listOf(
        Question(R.string.question_goiania, true),
        Question(R.string.question_araguaia, true),
        Question(R.string.question_caldas, true),
        Question(R.string.question_capital, false),
        Question(R.string.question_pequi, false),
        Question(R.string.question_chapada, true)
    )

    /**
     * Índice que controla a posição da pergunta atual dentro da [questionBank].
     * Utilizado para navegar entre as questões.
     */
    private var currentIndex = 0

    /**
     * Ponto de entrada principal da Activity, chamado quando o sistema Android cria a tela.
     *
     * Responsabilidades:
     * 1. Habilitar a experiência edge-to-edge (layout sob as barras de sistema).
     * 2. Inicializar o View Binding para acesso seguro aos componentes da UI.
     * 3. Configurar os Listeners de clique para os botões de resposta e navegação.
     * 4. Ajustar os paddings das views para respeitar os System Bars (StatusBar/NavigationBar).
     *
     * @param savedInstanceState Se a atividade está sendo reinicializada, este Bundle contém os dados
     * fornecidos em onSaveInstanceState. Caso contrário, é nulo.
     */
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)
        ViewCompat.setOnApplyWindowInsetsListener(findViewById(R.id.main)) { v, insets ->
            val systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars())
            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom)
            insets
        }

        binding.trueButton.setOnClickListener { view: View ->
            checkAnswer(true)
        }

        binding.falseButton.setOnClickListener { view: View ->
            checkAnswer(false)
        }

        binding.nextButton.setOnClickListener {
            // O operador módulo (%) garante que ao chegar no final da lista,
            // o índice retorne para 0, criando um comportamento de lista circular.
            currentIndex = (currentIndex + 1) % questionBank.size
            updateQuestion()
        }

        updateQuestion()
    }

    /**
     * Atualiza o conteúdo do TextView na tela com o texto da pergunta correspondente ao [currentIndex].
     *
     * Esta função é centralizada para garantir que qualquer mudança de índice
     * reflita instantaneamente na interface do usuário.
     */
    private fun updateQuestion() {
        val questionTextResId = questionBank[currentIndex].textResId
        binding.questionTextView.setText(questionTextResId)
    }

    /**
     * Compara a resposta fornecida pelo usuário com a resposta armazenada no [questionBank].
     *
     * @param userAnswer Booleano representando a escolha do usuário (Verdadeiro ou Falso).
     *
     * A lógica decide qual ID de string de feedback será usado baseado no resultado da comparação.
     * O Toast é utilizado como feedback imediato e não intrusivo.
     */
    private fun checkAnswer(userAnswer: Boolean) {
        val correctAnswer = questionBank[currentIndex].answer
        val messageResId = if (userAnswer == correctAnswer) {
            R.string.correct_toast
        } else {
            R.string.incorrect_toast
        }
        Toast.makeText(this, messageResId, Toast.LENGTH_SHORT)
            .show()
    }
}
