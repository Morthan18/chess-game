package demo.console

import demo.domain.Board
import demo.domain.Game
import demo.domain.figuresInitPosition

class ConsoleGame : Game {

    private val board: Board = Board()

    override fun start() {
        board.figures = figuresInitPosition(board)
        ConsoleBoardRenderer(board).render()
        while (true) {

        }
    }
}