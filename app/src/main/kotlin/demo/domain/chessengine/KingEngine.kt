package demo.domain.chessengine

import demo.domain.*

class KingEngine(board: Board) : FigureEngine(board) {

    @Suppress("UNREACHABLE_CODE")
    override fun isMoveLegal(f: Figure, toPosition: Position): Boolean {
        val king = f as King
        val possiblePositions = getPossiblePositions(king)

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

    private fun getPossiblePositions(king: King): List<Position> {
        val position: Position = king.position
        val x: Int = position.x
        val y: Int = position.y

        return listOf(
            Position(x - 1, y + 1),
            Position(x - 1, y),
            Position(x - 1, y - 1),

            Position(x, y + 1),
            Position(x, y - 1),

            Position(x + 1, y + 1),
            Position(x + 1, y),
            Position(x + 1, y - 1)
        )
    }

}