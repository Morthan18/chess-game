package demo

import demo.console.ConsoleGame
import demo.domain.Game


fun main() {
    val game: Game = ConsoleGame()
    game.start()
}
