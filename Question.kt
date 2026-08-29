package com.example.appquiz

import androidx.annotation.StringRes

/**
 * Data class que modela a estrutura de dados de uma questão no quiz.
 *
 * Utilizada como parte do modelo de dados (Model) para encapsular a pergunta
 * e sua respectiva resposta correta.
 *
 * @param textResId O ID do recurso de string contendo o enunciado da pergunta.
 *                  A anotação @StringRes garante em tempo de compilação que passamos um ID válido.
 * @param answer A resposta correta esperada para esta pergunta (true para Verdadeiro, false para Falso).
 */
data class Question(@StringRes val textResId: Int, val answer: Boolean)
