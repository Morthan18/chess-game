package demo.console

import demo.domain.Board
import demo.domain.Game

class ConsoleGame : Game {
    private val board: Board = Board()

    override fun start() {
       ConsoleBoardRenderer(board).render()
        while (true) {
            
        }
    }
}