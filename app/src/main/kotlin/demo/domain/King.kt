package demo.domain

class King(figureColor: FigureColor, position: Position, board: Board) : Figure(figureColor, position, board) {

    override fun getLegalMoves(): List<Position> {
        return getPossiblePositions()
    }

    override fun clone(board: Board): Figure {
        return King(figureColor, position, board)
    }

    private fun getPossiblePositions(): List<Position> {
        val position: Position = this.position
        val x: Int = position.x
        val y: Int = position.y

        return listOf(
            Position(x - 1, y + 1),
            Position(x - 1, y),
            Position(x - 1, y - 1),

            Position(x, y + 1),
            Position(x, y - 1),

            Position(x + 1, y + 1),
            Position(x + 1, y),
            Position(x + 1, y - 1)
        )
            .filter {
                it.x >= 0 && it.x < 8 && it.y >= 0 && it.y < 8
            }
            .filter { 
                when (this.figureColor) {
                    FigureColor.WHITE -> isBlackOnPosition(it) || isPositionFree(it)
                    FigureColor.BLACK -> isWhiteOnPosition(it) || isPositionFree(it)
                }
            }
    }
}