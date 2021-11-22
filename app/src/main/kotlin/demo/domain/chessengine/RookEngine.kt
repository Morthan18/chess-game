package demo.domain.chessengine

import demo.domain.Board
import demo.domain.Figure
import demo.domain.Position
import demo.domain.Rook

class RookEngine(board: Board) : FigureEngine(board) {

    override fun isMoveLegal(f: Figure, toPosition: Position): Boolean {
        val figure = f as Rook

        val legalMoves: MutableList<Position> = mutableListOf()

        legalMoves.addAll(findLegalMovesRight(figure))
        legalMoves.addAll(findLegalMovesLeft(figure))
        legalMoves.addAll(findLegalMovesUp(figure))
        legalMoves.addAll(findLegalMovesDown(figure))


        return legalMoves.contains(toPosition)
    }
}