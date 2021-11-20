package demo.domain.chessengine

import demo.domain.Board
import demo.domain.Figure
import demo.domain.FigureColor
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

    protected fun validateLine(
        x: Int,
        y: Int,
        legalPositions: MutableList<Position>,
        figure: Figure
    ): Boolean {
        val currentPosition = Position(x, y)
        val foundFigure: Figure? = board.findFigure(currentPosition)

        if (foundFigure == null) {
            legalPositions.add(currentPosition)
        }

        when (figure.figureColor) {
            FigureColor.WHITE -> {
                if (isBlackOnPosition(currentPosition)) {
                    legalPositions.add(currentPosition)
                    return true
                } else if (isWhiteOnPosition(currentPosition)) {
                    return true
                }
            }
            FigureColor.BLACK -> {
                if (isWhiteOnPosition(currentPosition)) {
                    legalPositions.add(currentPosition)
                    return true
                } else if (isBlackOnPosition(currentPosition)) {
                    return true
                }
            }
        }
        return false
    }
}