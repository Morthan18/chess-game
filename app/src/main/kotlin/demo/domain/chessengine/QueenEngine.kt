package demo.domain.chessengine

import demo.domain.Board
import demo.domain.Figure
import demo.domain.Position
import demo.domain.Queen

class QueenEngine(board: Board) : FigureEngine(board) {
    
    override fun isMoveLegal(f: Figure, toPosition: Position): Boolean {
        val queen = f as Queen

        val legalMoves: MutableList<Position> = mutableListOf()

        legalMoves.addAll(findLegalMovesRightUp(queen))
        legalMoves.addAll(findLegalMovesRightDown(queen))
        legalMoves.addAll(findLegalMovesLeftUp(queen))
        legalMoves.addAll(findLegalMovesLeftDown(queen))

        legalMoves.addAll(findLegalMovesRight(queen))
        legalMoves.addAll(findLegalMovesLeft(queen))
        legalMoves.addAll(findLegalMovesUp(queen))
        legalMoves.addAll(findLegalMovesDown(queen))

        return legalMoves.contains(toPosition)
    }
}