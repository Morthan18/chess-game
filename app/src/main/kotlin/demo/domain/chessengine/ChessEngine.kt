package demo.domain.chessengine

import demo.domain.*

class ChessEngine(board: Board) {
    private val pawnEngine = PawnEngine(board)
    private val bishopEngine = BishopEngine(board)
    private val rookEngine = RookEngine(board)
    private val knightEngine = KnightEngine(board)


    fun isMoveLegal(figure: Figure, toPosition: Position): Boolean {
        val figureEngine: FigureEngine? = when (figure) {
            is Pawn -> pawnEngine
            is Bishop -> bishopEngine
            is Rook -> rookEngine
            is Knight -> knightEngine
            else -> null
        }
        return figureEngine!!.isMoveLegal(figure, toPosition)
    }

}