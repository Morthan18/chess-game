package demo.domain

class Pawn(
    figureColor: FigureColor,
    position: Position,
    board: Board,
    var alreadyMoved: Boolean = false
) : Figure(figureColor, position, board) {

    override fun getLegalMoves(): List<Position> {
        val legalPositions: MutableList<Position> = mutableListOf()
        val straightPositions = when (alreadyMoved) {
            true -> when (figureColor) {
                FigureColor.WHITE -> findLegalMoves1FieldUp()
                FigureColor.BLACK -> findLegalMoves1FieldDown()
            }
            false -> when (figureColor) {
                FigureColor.WHITE -> findLegalMoves2FieldsUp()
                FigureColor.BLACK -> findLegalMoves2FieldsDown()
            }
        }
        val positionsToBeat = when (figureColor) {
            FigureColor.WHITE -> listOf(findLegalMoves1FieldRightUp(), findLegalMoves1FieldLeftUp()).flatten()
            FigureColor.BLACK -> listOf(findLegalMoves1FieldRightDown(), findLegalMoves1FieldLeftDown()).flatten()
        }

        legalPositions.addAll(straightPositions)
        legalPositions.addAll(positionsToBeat)


        return legalPositions
    }

    private fun findLegalMoves1FieldUp(): List<Position> {
        val figurePosition: Position = this.position
        val legalPositions: MutableList<Position> = mutableListOf()

        var y = figurePosition.y + 1
        while (y <= Board.BOARD_SIDE_LENGTH && y - figurePosition.y <= 1) {
            if (validateLine(figurePosition.x, y, legalPositions)) break
            y++
        }
        return legalPositions.filter { position -> isPositionFree(position) }
    }

    private fun findLegalMoves1FieldDown(): List<Position> {
        val figurePosition: Position = this.position
        val legalPositions: MutableList<Position> = mutableListOf()

        var y = figurePosition.y - 1
        while (y <= Board.BOARD_SIDE_LENGTH && figurePosition.y - y <= 1) {
            if (validateLine(figurePosition.x, y, legalPositions)) break
            y--
        }
        return legalPositions.filter { position -> isPositionFree(position) }
    }

    private fun findLegalMoves2FieldsUp(): List<Position> {
        val figurePosition: Position = this.position
        val legalPositions: MutableList<Position> = mutableListOf()

        var y = figurePosition.y + 1
        while (y <= Board.BOARD_SIDE_LENGTH && y - figurePosition.y <= 2) {
            if (validateLine(figurePosition.x, y, legalPositions)) break
            y++
        }
        return legalPositions.filter { position -> isPositionFree(position) }
    }

    private fun findLegalMoves2FieldsDown(): List<Position> {
        val figurePosition: Position = this.position
        val legalPositions: MutableList<Position> = mutableListOf()

        var y = figurePosition.y - 1
        while (y <= Board.BOARD_SIDE_LENGTH && figurePosition.y - y <= 2) {
            if (validateLine(figurePosition.x, y, legalPositions)) break
            y--
        }
        return legalPositions.filter { position -> isPositionFree(position) }
    }

    private fun findLegalMoves1FieldRightUp(): List<Position> {
        val figurePosition: Position = this.position
        val legalPositions: MutableList<Position> = mutableListOf()

        var y = figurePosition.y + 1
        while (y <= Board.BOARD_SIDE_LENGTH && y - figurePosition.y <= 1) {
            if (validateLine(figurePosition.x + 1, y, legalPositions)) break
            y++
        }
        return legalPositions.filter { position -> isBlackOnPosition(position) }
    }

    private fun findLegalMoves1FieldLeftUp(): List<Position> {
        val figurePosition: Position = this.position
        val legalPositions: MutableList<Position> = mutableListOf()

        var y = figurePosition.y + 1
        while (y <= Board.BOARD_SIDE_LENGTH && y - figurePosition.y <= 1) {
            if (validateLine(figurePosition.x - 1, y, legalPositions)) break
            y++
        }
        return legalPositions.filter { position -> isBlackOnPosition(position) }
    }

    private fun findLegalMoves1FieldRightDown(): List<Position> {
        val figurePosition: Position = this.position
        val legalPositions: MutableList<Position> = mutableListOf()

        var y = figurePosition.y - 1
        while (y <= Board.BOARD_SIDE_LENGTH && figurePosition.y - y <= 1) {
            if (validateLine(figurePosition.x + 1, y, legalPositions)) break
            y--
        }
        return legalPositions.filter { position -> isWhiteOnPosition(position) }
    }

    private fun findLegalMoves1FieldLeftDown(): List<Position> {
        val figurePosition: Position = this.position
        val legalPositions: MutableList<Position> = mutableListOf()

        var y = figurePosition.y - 1
        while (y <= Board.BOARD_SIDE_LENGTH && figurePosition.y - y <= 1) {
            if (validateLine(figurePosition.x - 1, y, legalPositions)) break
            y++
        }
        return legalPositions.filter { position -> isWhiteOnPosition(position) }
    }
} 