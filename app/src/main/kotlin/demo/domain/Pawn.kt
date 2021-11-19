package demo.domain

class Pawn(
    figureColor: FigureColor,
    position: Position,
    var alreadyMoved: Boolean = false
) : Figure(figureColor, position)