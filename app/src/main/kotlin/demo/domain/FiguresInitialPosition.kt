package demo.domain

val figuresInitPosition: MutableList<Figure> = mutableListOf(
    Rook(FigureColor.BLACK, Position(0, 7)),
    Knight(FigureColor.BLACK, Position(1, 7)),
    Bishop(FigureColor.BLACK, Position(2, 7)),
    Queen(FigureColor.BLACK, Position(3, 7)),
    King(FigureColor.BLACK, Position(4, 7)),
    Bishop(FigureColor.BLACK, Position(5, 7)),
    Knight(FigureColor.BLACK, Position(6, 7)),
    Rook(FigureColor.BLACK, Position(7, 7)),
    
    Pawn(FigureColor.BLACK, Position(0, 6)),
    Pawn(FigureColor.BLACK, Position(1, 6)),
    Pawn(FigureColor.BLACK, Position(2, 6)),
    Pawn(FigureColor.BLACK, Position(3, 6)),
    Pawn(FigureColor.BLACK, Position(4, 6)),
    Pawn(FigureColor.BLACK, Position(5, 6)),
    Pawn(FigureColor.BLACK, Position(6, 6)),
    Pawn(FigureColor.BLACK, Position(7, 6)),

    Rook(FigureColor.WHITE, Position(0, 0)),
    Knight(FigureColor.WHITE, Position(1, 0)),
    Bishop(FigureColor.WHITE, Position(2, 0)),
    Queen(FigureColor.WHITE, Position(3, 0)),
    King(FigureColor.WHITE, Position(4, 0)),
    Bishop(FigureColor.WHITE, Position(5, 0)),
    Knight(FigureColor.WHITE, Position(6, 0)),
    Rook(FigureColor.WHITE, Position(7, 0)),

    Pawn(FigureColor.WHITE, Position(0, 1)),
    Pawn(FigureColor.WHITE, Position(1, 1)),
    Pawn(FigureColor.WHITE, Position(2, 1)),
    Pawn(FigureColor.WHITE, Position(3, 1)),
    Pawn(FigureColor.WHITE, Position(4, 1)),
    Pawn(FigureColor.WHITE, Position(5, 1)),
    Pawn(FigureColor.WHITE, Position(6, 1)),
    Pawn(FigureColor.WHITE, Position(7, 1))
)
