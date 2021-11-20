package demo.domain.chessengine

import demo.domain.Board
import demo.domain.Figure
import demo.domain.Position

abstract class FigureEngine(protected val board: Board) {
    abstract fun isMoveLegal(f: Figure, toPosition: Position): Boolean
}