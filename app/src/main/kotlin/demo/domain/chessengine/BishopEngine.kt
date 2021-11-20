package demo.domain.chessengine

import demo.domain.*
import demo.domain.Board.Companion.BOARD_SIDE_LENGTH

class BishopEngine(board: Board) : FigureEngine(board) {
    override fun isMoveLegal(f: Figure, toPosition: Position): Boolean {
        val figure = f as Bishop

        return findPossibleMovesRightUp(figure).contains(toPosition)
    }

    private fun findPossibleMovesRightUp(figure: Bishop): List<Position> {
        val figurePosition: Position = figure.position
        val legalPositions: MutableList<Position> = mutableListOf()

        for (x in figurePosition.x + 1..BOARD_SIDE_LENGTH) {
            for (y in figurePosition.y + 1..BOARD_SIDE_LENGTH) {
                val currentPosition = Position(x, y)
                val foundFigure: Figure? = board.findFigure(currentPosition)

                if (foundFigure == null) {
                    legalPositions.add(currentPosition)
                }
 
                when (foundFigure?.figureColor) {
                    FigureColor.WHITE -> {
                        if (isBlackOnPosition(currentPosition)) {
                            legalPositions.add(currentPosition)
                        } else {
                            break
                        }
                    }
                    FigureColor.BLACK -> {
                        if (isWhiteOnPosition(currentPosition)) {
                            legalPositions.add(currentPosition)
                        } else {
                            break
                        }
                    }
                }
                continue
            }
        }
        return legalPositions
    }
}