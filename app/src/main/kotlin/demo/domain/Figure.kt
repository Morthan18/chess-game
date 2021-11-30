package demo.domain

abstract class Figure(
    val figureColor: FigureColor,
    var position: Position,
    @Transient var board: Board,
    var isMarked: Boolean = false
) {

    abstract fun getLegalMoves(): List<Position>
    
    abstract fun clone(board: Board) : Figure

    fun isWhite(): Boolean {
        return figureColor == FigureColor.WHITE
    }

    fun isBlack(): Boolean {
        return figureColor == FigureColor.BLACK
    }

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

    protected fun findLegalMovesRightUp(): List<Position> {
        val figurePosition: Position = this.position
        val legalPositions: MutableList<Position> = mutableListOf()

        var x = figurePosition.x + 1
        var y = figurePosition.y + 1
        while (x <= Board.BOARD_SIDE_LENGTH || y <= Board.BOARD_SIDE_LENGTH) {
            if (validateLine(x, y, legalPositions)) break
            x++
            y++
        }
        return legalPositions
    }

    protected fun findLegalMovesRightDown(): List<Position> {
        val figurePosition: Position = this.position
        val legalPositions: MutableList<Position> = mutableListOf()

        var x = figurePosition.x + 1
        var y = figurePosition.y - 1
        while (x <= Board.BOARD_SIDE_LENGTH || y >= 0) {
            if (validateLine(x, y, legalPositions)) break
            x++
            y--
        }
        return legalPositions
    }

    protected fun findLegalMovesLeftUp(): List<Position> {
        val figurePosition: Position = this.position
        val legalPositions: MutableList<Position> = mutableListOf()

        var x = figurePosition.x - 1
        var y = figurePosition.y + 1
        while (x >= 0 || y <= Board.BOARD_SIDE_LENGTH) {
            if (validateLine(x, y, legalPositions)) break
            x--
            y++
        }
        return legalPositions
    }

    protected fun findLegalMovesRight(): List<Position> {
        val figurePosition: Position = this.position
        val legalPositions: MutableList<Position> = mutableListOf()

        var x = figurePosition.x + 1
        while (x <= Board.BOARD_SIDE_LENGTH) {
            if (validateLine(x, figurePosition.y, legalPositions)) break
            x++
        }
        return legalPositions
    }

    protected fun findLegalMovesLeft(): List<Position> {
        val figurePosition: Position = this.position
        val legalPositions: MutableList<Position> = mutableListOf()

        var x = figurePosition.x - 1
        while (x >= 0) {
            if (validateLine(x, figurePosition.y, legalPositions)) break
            x--
        }
        return legalPositions
    }

    protected fun findLegalMovesUp(): List<Position> {
        val figurePosition: Position = this.position
        val legalPositions: MutableList<Position> = mutableListOf()

        var y = figurePosition.y + 1
        while (y <= Board.BOARD_SIDE_LENGTH) {
            if (validateLine(figurePosition.x, y, legalPositions)) break
            y++
        }
        return legalPositions
    }

    protected fun findLegalMovesDown(): List<Position> {
        val figurePosition: Position = this.position
        val legalPositions: MutableList<Position> = mutableListOf()

        var y = figurePosition.y - 1
        while (y >= 0) {
            if (validateLine(figurePosition.x, y, legalPositions)) break
            y--
        }
        return legalPositions
    }

    protected fun findLegalMovesLeftDown(): List<Position> {
        val figurePosition: Position = this.position
        val legalPositions: MutableList<Position> = mutableListOf()

        var x = figurePosition.x - 1
        var y = figurePosition.y - 1
        while (x >= 0 || y >= 0) {
            if (validateLine(x, y, legalPositions)) break
            x--
            y--
        }
        return legalPositions
    }

    protected fun validateLine(
        x: Int,
        y: Int,
        legalPositions: MutableList<Position>
    ): Boolean {
        if (x < 0 || x > Board.BOARD_SIDE_LENGTH || y < 0 || y > Board.BOARD_SIDE_LENGTH)
            return true

        val currentPosition = Position(x, y)
        val foundFigure: Figure? = board.findFigure(currentPosition)

        if (foundFigure == null) {
            legalPositions.add(currentPosition)
        }

        when (this.figureColor) {
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