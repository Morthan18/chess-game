package demo.domain.chessengine

import demo.domain.Board
import demo.domain.Figure
import demo.domain.Position

abstract class FigureEngine(protected val board: Board) {
    abstract fun isMoveLegal(f: Figure, toPosition: Position): Boolean

    protected fun isPositionFree(position: Position): Boolean {
        return this.board.findFigure(position) == null
    }

    protected fun isBlackOnPosition(position: Position): Boolean {
        val figure: Figure? = this.board.findFigure(position)
        return figure != null && figure.isBlack()
    }

    protected fun isWhiteOnPosition(position: Position): Boolean {
        val figure: Figure? = this.board.findFigure(position)
        return figure != null && figure.isWhite()
    }

    protected fun areXPositionsEqual(position1: Position, position2: Position): Boolean {
        return position1.x == position2.x
    }
}