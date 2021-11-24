package demo.console

import demo.domain.Board
import demo.domain.Game
import demo.domain.figuresInitPosition

class ConsoleGame : Game {

    private val board: Board = Board()

    override fun start() {
        board.setFigures(figuresInitPosition(board))
        ConsoleBoardRenderer(board).render()
        while (true) {

        }
    }
}