package demo.domain.chessengine

import demo.domain.*

class ChessEngine(board: Board) {
    private val engines: List<FigureEngine> = listOf(PawnEngine(board))

    fun isMoveLegal(figure: Figure, toPosition: Position): Boolean {
        return when (figure) {
            is Pawn -> engines[0].isMoveLegal(figure, toPosition)
            else -> false
        }

    }
    
}