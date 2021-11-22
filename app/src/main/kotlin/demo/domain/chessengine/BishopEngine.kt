package demo.domain.chessengine

import demo.domain.Bishop
import demo.domain.Board
import demo.domain.Figure
import demo.domain.Position

class BishopEngine(board: Board) : FigureEngine(board) {
    override fun isMoveLegal(f: Figure, toPosition: Position): Boolean {
        val figure = f as Bishop

        val legalMoves: MutableList<Position> = mutableListOf()

        legalMoves.addAll(findLegalMovesRightUp(figure))
        legalMoves.addAll(findLegalMovesRightDown(figure))
        legalMoves.addAll(findLegalMovesLeftUp(figure))
        legalMoves.addAll(findLegalMovesLeftDown(figure))

        return legalMoves.contains(toPosition)
    }
}