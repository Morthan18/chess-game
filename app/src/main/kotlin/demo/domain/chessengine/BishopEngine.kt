package demo.domain.chessengine

import demo.domain.Bishop
import demo.domain.Board
import demo.domain.Board.Companion.BOARD_SIDE_LENGTH
import demo.domain.Figure
import demo.domain.Position

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
            if (validateLine(x, y, legalPositions, figure)) break
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
            if (validateLine(x, y, legalPositions, figure)) break
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
            if (validateLine(x, y, legalPositions, figure)) break
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
            if (validateLine(x, y, legalPositions, figure)) break
            x--
            y--
        }
        return legalPositions
    }
}