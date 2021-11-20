package demo.domain.chessengine

import demo.domain.*

class ChessEngine(board: Board) {
    
    
    private val engines: List<FigureEngine> = listOf(
        PawnEngine(board),
        BishopEngine(board),
        RookEngine(board)
    )

    fun isMoveLegal(figure: Figure, toPosition: Position): Boolean {
        return when (figure) {
            is Pawn -> engines[0].isMoveLegal(figure, toPosition)
            is Bishop -> engines[1].isMoveLegal(figure, toPosition)
            is Rook -> engines[2].isMoveLegal(figure, toPosition)
            else -> false
        }

    }
    
}