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

    protected fun findLegalMovesRightUp(figure: Figure): List<Position> {
        val figurePosition: Position = figure.position
        val legalPositions: MutableList<Position> = mutableListOf()

        var x = figurePosition.x + 1
        var y = figurePosition.y + 1
        while (x <= Board.BOARD_SIDE_LENGTH || y <= Board.BOARD_SIDE_LENGTH) {
            if (validateLine(x, y, legalPositions, figure)) break
            x++
            y++
        }
        return legalPositions
    }

    protected fun findLegalMovesRightDown(figure: Figure): List<Position> {
        val figurePosition: Position = figure.position
        val legalPositions: MutableList<Position> = mutableListOf()

        var x = figurePosition.x + 1
        var y = figurePosition.y - 1
        while (x <= Board.BOARD_SIDE_LENGTH || y >= 0) {
            if (validateLine(x, y, legalPositions, figure)) break
            x++
            y--
        }
        return legalPositions
    }

    protected fun findLegalMovesLeftUp(figure: Figure): List<Position> {
        val figurePosition: Position = figure.position
        val legalPositions: MutableList<Position> = mutableListOf()

        var x = figurePosition.x - 1
        var y = figurePosition.y + 1
        while (x >= 0 || y <= Board.BOARD_SIDE_LENGTH) {
            if (validateLine(x, y, legalPositions, figure)) break
            x--
            y++
        }
        return legalPositions
    }


    protected fun findLegalMovesLeftDown(figure: Figure): List<Position> {
        val figurePosition: Position = figure.position
        val legalPositions: MutableList<Position> = mutableListOf()

        var x = figurePosition.x - 1
        var y = figurePosition.y - 1
        while (x >= 0 || y >= 0) {
            if (validateLine(x, y, legalPositions, figure)) break
            x--
            y--
        }
        return legalPositions
    }

    protected fun findLegalMovesRight(figure: Figure): List<Position> {
        val figurePosition: Position = figure.position
        val legalPositions: MutableList<Position> = mutableListOf()

        var x = figurePosition.x + 1
        while (x <= Board.BOARD_SIDE_LENGTH) {
            if (validateLine(x, figurePosition.y, legalPositions, figure)) break
            x++
        }
        return legalPositions
    }

    protected fun findLegalMovesLeft(figure: Figure): List<Position> {
        val figurePosition: Position = figure.position
        val legalPositions: MutableList<Position> = mutableListOf()

        var x = figurePosition.x - 1
        while (x >= 0) {
            if (validateLine(x, figurePosition.y, legalPositions, figure)) break
            x--
        }
        return legalPositions
    }

    protected fun findLegalMovesUp(figure: Figure): List<Position> {
        val figurePosition: Position = figure.position
        val legalPositions: MutableList<Position> = mutableListOf()

        var y = figurePosition.y + 1
        while (y <= Board.BOARD_SIDE_LENGTH) {
            if (validateLine(figurePosition.x, y, legalPositions, figure)) break
            y++
        }
        return legalPositions
    }

    protected fun findLegalMovesDown(figure: Figure): List<Position> {
        val figurePosition: Position = figure.position
        val legalPositions: MutableList<Position> = mutableListOf()

        var y = figurePosition.y - 1
        while (y >= 0) {
            if (validateLine(figurePosition.x, y, legalPositions, figure)) break
            y--
        }
        return legalPositions
    }

    private fun validateLine(
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
