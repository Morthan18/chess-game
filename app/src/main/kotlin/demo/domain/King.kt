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
            .filter { position ->
                when (this.figureColor) {
                    FigureColor.WHITE -> isBlackOnPosition(position) || isPositionFree(position)
                    FigureColor.BLACK -> isWhiteOnPosition(position) || isPositionFree(position)
                }
            }
    }
}