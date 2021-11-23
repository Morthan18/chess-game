package demo.domain

class Rook(figureColor: FigureColor, position: Position, board: Board) : Figure(figureColor, position, board) {
    override fun getLegalMoves(): List<Position> {
        val legalMoves: MutableList<Position> = mutableListOf()

        legalMoves.addAll(findLegalMovesRight())
        legalMoves.addAll(findLegalMovesLeft())
        legalMoves.addAll(findLegalMovesUp())
        legalMoves.addAll(findLegalMovesDown())

        return legalMoves
    }
}