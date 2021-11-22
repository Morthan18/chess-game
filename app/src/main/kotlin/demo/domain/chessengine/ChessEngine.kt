package demo.domain.chessengine

import demo.domain.*

class ChessEngine(board: Board) {
    private val pawnEngine = PawnEngine(board)
    private val bishopEngine = BishopEngine(board)
    private val rookEngine = RookEngine(board)
    private val knightEngine = KnightEngine(board)
    private val queenEngine = QueenEngine(board)
    private val kingEngine = KingEngine(board)


    fun isMoveLegal(figure: Figure, toPosition: Position): Boolean {
        val figureEngine: FigureEngine? = when (figure) {
            is Pawn -> pawnEngine
            is Bishop -> bishopEngine
            is Rook -> rookEngine
            is Knight -> knightEngine
            is Queen -> queenEngine
            is King -> kingEngine
            else -> null
        }
        return figureEngine!!.isMoveLegal(figure, toPosition)
    }

}