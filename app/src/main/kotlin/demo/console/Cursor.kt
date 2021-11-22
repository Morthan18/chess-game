package demo.console

import demo.domain.BackgroundColor
import demo.domain.PlayerTurn
import demo.domain.Position


class Cursor(var position: Position, var color: BackgroundColor = BackgroundColor.IVORY) {

    fun isAtPosition(position: Position): Boolean {
        return this.position == position
    }

    fun moveRight() {
        if (this.position.x != 7) {
            this.position = Position(this.position.x + 1, this.position.y)
        }
    }

    fun moveLeft() {
        if (this.position.x != 0) {
            this.position = Position(this.position.x - 1, this.position.y)
        }
    }

    fun moveUp() {
        if (this.position.y != 7) {
            this.position = Position(this.position.x, this.position.y + 1)
        }
    }

    fun moveDown() {
        if (this.position.y != 0) {
            this.position = Position(this.position.x, this.position.y - 1)
        }
    }

    fun setColorBasedOnPlayerTurn(playerTurn: PlayerTurn) {
        this.color = if (playerTurn == PlayerTurn.WHITE) {
            BackgroundColor.IVORY
        } else {
            BackgroundColor.GRAY
        }
    }
}