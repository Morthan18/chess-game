package demo.domain.chessengine

import demo.domain.Board
import demo.domain.Board.Companion.BOARD_SIDE_LENGTH
import demo.domain.Figure
import demo.domain.Position
import demo.domain.Rook

class RookEngine(board: Board) : FigureEngine(board) {

    override fun isMoveLegal(f: Figure, toPosition: Position): Boolean {
        val figure = f as Rook

        val legalMoves: MutableList<Position> = mutableListOf()

        legalMoves.addAll(findLegalMovesRight(figure))
        legalMoves.addAll(findLegalMovesLeft(figure))
        legalMoves.addAll(findLegalMovesUp(figure))
        legalMoves.addAll(findLegalMovesDown(figure))


        return legalMoves.contains(toPosition)
    }

    private fun findLegalMovesRight(figure: Rook): List<Position> {
        val figurePosition: Position = figure.position
        val legalPositions: MutableList<Position> = mutableListOf()

        var x = figurePosition.x + 1
        while (x <= BOARD_SIDE_LENGTH) {
            if (validateLine(x, figurePosition.y, legalPositions, figure)) break
            x++
        }
        return legalPositions
    }

    private fun findLegalMovesLeft(figure: Rook): List<Position> {
        val figurePosition: Position = figure.position
        val legalPositions: MutableList<Position> = mutableListOf()

        var x = figurePosition.x - 1
        while (x >= 0) {
            if (validateLine(x, figurePosition.y, legalPositions, figure)) break
            x--
        }
        return legalPositions
    }

    private fun findLegalMovesUp(figure: Rook): List<Position> {
        val figurePosition: Position = figure.position
        val legalPositions: MutableList<Position> = mutableListOf()

        var y = figurePosition.y + 1
        while (y <= BOARD_SIDE_LENGTH) {
            if (validateLine(figurePosition.x, y, legalPositions, figure)) break
            y++
        }
        return legalPositions
    }

    private fun findLegalMovesDown(figure: Rook): List<Position> {
        val figurePosition: Position = figure.position
        val legalPositions: MutableList<Position> = mutableListOf()

        var y = figurePosition.y - 1
        while (y >= 0) {
            if (validateLine(figurePosition.x, y, legalPositions, figure)) break
            y--
        }
        return legalPositions
    }
}