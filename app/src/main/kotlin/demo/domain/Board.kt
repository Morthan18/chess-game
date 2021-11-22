package demo.domain

import demo.domain.chessengine.ChessEngine

class Board {
    private val figures: MutableList<Figure> = figuresInitPosition
    private val chessEngine: ChessEngine = ChessEngine(this)
    var playerTurn: PlayerTurn = PlayerTurn.WHITE

    companion object {
        const val BOARD_SIDE_LENGTH: Int = 7
    }

    fun makeMove(figure: Figure, toPosition: Position) {
        val figureToMove: Figure? = findFigure(figure.position)

        val figureToBeat: Figure? = findFigure(toPosition)
        if (figureToBeat != null) {
            figures.remove(figureToBeat)
        }

        figureToMove?.position = toPosition

        if (figureToMove != null && figureToMove is Pawn) {
            figureToMove.alreadyMoved = true
        }

        playerTurn = if (playerTurn == PlayerTurn.WHITE) {
            PlayerTurn.BLACK
        } else {
            PlayerTurn.WHITE
        }
    }

    fun isMoveLegal(figure: Figure, toPosition: Position): Boolean {
        if (figure.figureColor.toString() != playerTurn.toString()) {
            return false
        }

        return chessEngine.isMoveLegal(figure, toPosition)
    }

    fun findFigure(position: Position): Figure? {
        return figures.find { f -> f.position == position }
    }

    fun findAllMarkedFiguresExcept(figure: Figure?): List<Figure> {
        return figures
            .filter { f -> f != figure }
            .filter { f -> f.isMarked }
    }

    fun findAnyMarkedFigure(): Figure? {
        return figures.find { f -> f.isMarked }
    }
}