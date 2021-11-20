package demo.domain.chessengine

import demo.domain.*
import demo.domain.Board.Companion.BOARD_SIDE_LENGTH

class BishopEngine(board: Board) : FigureEngine(board) {
    override fun isMoveLegal(f: Figure, toPosition: Position): Boolean {
        val figure = f as Bishop

        val legalMoves: MutableList<Position> = mutableListOf()

        legalMoves.addAll(findLegalMovesRightUp(figure))
        legalMoves.addAll(findLegalMovesRightDown(figure))
        legalMoves.addAll(findLegalMovesLeftUp(figure))
        legalMoves.addAll(findLegalMovesLeftDown(figure))


        return legalMoves.contains(toPosition)
    }

    private fun findLegalMovesRightUp(figure: Bishop): List<Position> {
        val figurePosition: Position = figure.position
        val legalPositions: MutableList<Position> = mutableListOf()

        var x = figurePosition.x + 1
        var y = figurePosition.y + 1
        while (x <= BOARD_SIDE_LENGTH || y <= BOARD_SIDE_LENGTH) {
            if (validateDiagonal(x, y, legalPositions, figure)) break
            x++
            y++
        }
        return legalPositions
    }

    private fun findLegalMovesRightDown(figure: Bishop): List<Position> {
        val figurePosition: Position = figure.position
        val legalPositions: MutableList<Position> = mutableListOf()

        var x = figurePosition.x + 1
        var y = figurePosition.y - 1
        while (x <= BOARD_SIDE_LENGTH || y >= 0) {
            if (validateDiagonal(x, y, legalPositions, figure)) break
            x++
            y--
        }
        return legalPositions
    }


    private fun findLegalMovesLeftUp(figure: Bishop): List<Position> {
        val figurePosition: Position = figure.position
        val legalPositions: MutableList<Position> = mutableListOf()

        var x = figurePosition.x - 1
        var y = figurePosition.y + 1
        while (x >= 0 || y <= BOARD_SIDE_LENGTH) {
            if (validateDiagonal(x, y, legalPositions, figure)) break
            x--
            y++
        }
        return legalPositions
    }

    private fun findLegalMovesLeftDown(figure: Bishop): List<Position> {
        val figurePosition: Position = figure.position
        val legalPositions: MutableList<Position> = mutableListOf()

        var x = figurePosition.x - 1
        var y = figurePosition.y - 1
        while (x >= 0 || y >= 0) {
            if (validateDiagonal(x, y, legalPositions, figure)) break
            x--
            y--
        }
        return legalPositions
    }

    private fun validateDiagonal(
        x: Int,
        y: Int,
        legalPositions: MutableList<Position>,
        figure: Bishop
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