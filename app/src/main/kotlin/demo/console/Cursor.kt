package demo.console

import demo.domain.Position


class Cursor(private var position: Position, var visible: Boolean) {

    fun isAtPosition(position: Position): Boolean {
        return this.position == position
    }
    
    fun moveRight() {
        if (this.position.x != 7) {
            this.position.x += 1
        }
    }

    fun moveLeft() {
        if (this.position.x != 0) {
            this.position.x -= 1
        }
    }

    fun moveUp() {
        if (this.position.y != 7) {
            this.position.y += 1
        }
    }

    fun moveDown() {
        if (this.position.y != 0) {
            this.position.y -= 1
        }
    }
}