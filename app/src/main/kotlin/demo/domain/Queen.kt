package demo.domain

class Queen(figureColor: FigureColor, position: Position, board: Board) : Figure(figureColor, position, board) {
    override fun getLegalMoves(): List<Position> {
        val legalMoves: MutableList<Position> = mutableListOf()

        legalMoves.addAll(findLegalMovesRightUp())
        legalMoves.addAll(findLegalMovesRightDown())
        legalMoves.addAll(findLegalMovesLeftUp())
        legalMoves.addAll(findLegalMovesLeftDown())

        legalMoves.addAll(findLegalMovesRight())
        legalMoves.addAll(findLegalMovesLeft())
        legalMoves.addAll(findLegalMovesUp())
        legalMoves.addAll(findLegalMovesDown())
        return legalMoves;
    }
}