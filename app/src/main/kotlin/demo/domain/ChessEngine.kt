package demo.domain

class ChessEngine(private val board: Board) {

    fun isMoveLegal(figure: Figure, toPosition: Position): Boolean {
        return when (figure) {
            is Pawn -> {
                return if (figure.alreadyMoved) {
                    pawnEveryNextMove(figure, toPosition)
                } else {
                    pawnFirstMove(figure, toPosition)
                }
            }
            else -> false
        }

    }

    private fun pawnFirstMove(figure: Figure, toPosition: Position): Boolean {
        if (!areXPositionsEqual(toPosition, figure.position)) {
            return when (figure.figureColor) {
                FigureColor.WHITE -> {
                    (figure.position.x - 1 == toPosition.x
                            && figure.position.y + 1 == toPosition.y
                            && isBlackOnPosition(toPosition)
                            )
                            || (figure.position.x - 1 == toPosition.x
                            && figure.position.y + 1 == toPosition.y
                            && isBlackOnPosition(toPosition)
                            )
                }
                FigureColor.BLACK -> {
                    (figure.position.x - 1 == toPosition.x
                            && figure.position.y - 1 == toPosition.y
                            && isWhiteOnPosition(toPosition)
                            )
                            || (figure.position.x - 1 == toPosition.x
                            && figure.position.y - 1 == toPosition.y
                            && isWhiteOnPosition(toPosition)
                            )
                }
            }
        }

        return when (figure.figureColor) {
            FigureColor.WHITE -> {
                val isMoveAt1Field: Boolean = toPosition.y - figure.position.y == 1
                val isMoveAt2Fields: Boolean = toPosition.y - figure.position.y == 2
                if (isMoveAt2Fields) {
                    val isPreviousFieldFree: Boolean = isPositionFree(Position(toPosition.x, toPosition.y - 1))
                    isPositionFree(toPosition) && isPreviousFieldFree
                } else if (isMoveAt1Field) {
                    isPositionFree(toPosition)
                } else {
                    false
                }
            }
            FigureColor.BLACK -> {
                val isMoveAt2Fields: Boolean = figure.position.y - toPosition.y == 2
                if (isMoveAt2Fields && areXPositionsEqual(toPosition, figure.position)) {
                    val isPreviousFieldFree: Boolean = isPositionFree(Position(toPosition.x, toPosition.y + 1))
                    isPositionFree(toPosition) && isPreviousFieldFree
                } else if (areXPositionsEqual(toPosition, figure.position)) {
                    isPositionFree(toPosition)
                } else {
                    false
                }
            }
        }
    }

    private fun pawnEveryNextMove(figure: Figure, toPosition: Position): Boolean {
        val figurePositionX: Int = figure.position.x
        val figurePositionY: Int = figure.position.y
        if (!areXPositionsEqual(toPosition, figure.position)) {
            return when (figure.figureColor) {
                FigureColor.WHITE -> {
                    (figurePositionX - 1 == toPosition.x
                            && figurePositionY + 1 == toPosition.y
                            && isBlackOnPosition(toPosition))
                            ||
                            (figurePositionX + 1 == toPosition.x
                                    && figurePositionY + 1 == toPosition.y
                                    && isBlackOnPosition(toPosition))
                }
                FigureColor.BLACK -> {
                    (figurePositionX - 1 == toPosition.x
                            && figurePositionY - 1 == toPosition.y
                            && isWhiteOnPosition(toPosition))
                            ||
                            (figurePositionX + 1 == toPosition.x
                                    && figurePositionY - 1 == toPosition.y
                                    && isWhiteOnPosition(toPosition))
                }
            }
        }

        return when (figure.figureColor) {
            FigureColor.WHITE -> {
                val isMoveAt1Field = toPosition.y - figurePositionY == 1
                isPositionFree(toPosition) && isMoveAt1Field
            }
            FigureColor.BLACK -> {
                val isMoveAt1Field = figurePositionY - toPosition.y == 1
                isPositionFree(toPosition) && isMoveAt1Field
            }
        }
    }

    private fun isPositionFree(position: Position): Boolean {
        return this.board.findFigure(position) == null
    }

    private fun isBlackOnPosition(position: Position): Boolean {
        val figure: Figure? = this.board.findFigure(position)
        return figure != null && figure.isBlack()
    }

    private fun isWhiteOnPosition(position: Position): Boolean {
        val figure: Figure? = this.board.findFigure(position)
        return figure != null && figure.isWhite()
    }

    private fun areXPositionsEqual(position1: Position, position2: Position): Boolean {
        return position1.x == position2.x
    }

}