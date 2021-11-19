package demo.domain

abstract class Figure(val figureColor: FigureColor, var position: Position, var isMarked: Boolean = false) {

    fun isWhite(): Boolean {
        return figureColor == FigureColor.WHITE
    }
    fun isBlack(): Boolean {
        return figureColor == FigureColor.BLACK
    }
}