package demo.domain.chessengine

import demo.domain.*

class KnightEngine(board: Board) : FigureEngine(board) {

    @Suppress("UNREACHABLE_CODE")
    override fun isMoveLegal(f: Figure, toPosition: Position): Boolean {
        val knight: Knight = f as Knight
        val possiblePositions = getPossiblePositions(knight)

        if (!possiblePositions.contains(toPosition)) {
            return false
        }

        return when (f.figureColor) {
            FigureColor.WHITE -> {
                if (isPositionFree(toPosition)) {
                    return true
                } else {
                    if (isBlackOnPosition(toPosition)) {
                        return true
                    }
                }
                return false
            }
            FigureColor.BLACK -> {
                if (isPositionFree(toPosition)) {
                    return true
                } else {
                    if (isWhiteOnPosition(toPosition)) {
                        return true
                    }
                }
                return false
            }
        }
    }

    private fun getPossiblePositions(knight: Knight): List<Position> {
        val position: Position = knight.position
        val x: Int = position.x
        val y: Int = position.y

        return listOf(
            Position(x + 1, y + 2),
            Position(x - 1, y + 2),
            Position(x + 1, y - 2),
            Position(x - 1, y - 2),
            Position(x + 2, y - 1),
            Position(x + 2, y + 1),
            Position(x - 2, y - 1),
            Position(x - 2, y + 1)
        )
    }
}