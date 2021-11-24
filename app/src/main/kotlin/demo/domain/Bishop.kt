package demo.domain

class Bishop(figureColor: FigureColor, position: Position, board: Board) : Figure(figureColor, position, board) {

    override fun getLegalMoves(): List<Position> {
        val legalMoves: MutableList<Position> = mutableListOf()

        legalMoves.addAll(findLegalMovesRightUp())
        legalMoves.addAll(findLegalMovesRightDown())
        legalMoves.addAll(findLegalMovesLeftUp())
        legalMoves.addAll(findLegalMovesLeftDown())

        return legalMoves
    }

    override fun clone(board: Board): Figure {
        return Bishop(figureColor, position, board)
    }
} 