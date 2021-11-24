package demo.domain


fun figuresInitPosition(board: Board): MutableList<Figure> = mutableListOf(
    Rook(FigureColor.BLACK, Position(0, 7), board),
    Knight(FigureColor.BLACK, Position(1, 7), board),
    Bishop(FigureColor.BLACK, Position(2, 7), board),
    Queen(FigureColor.BLACK, Position(3, 7), board),
    King(FigureColor.BLACK, Position(4, 7), board),
    Bishop(FigureColor.BLACK, Position(5, 7), board),
    Knight(FigureColor.BLACK, Position(6, 7), board),
    Rook(FigureColor.BLACK, Position(7, 7), board),

    Pawn(FigureColor.BLACK, Position(0, 6), board),
    Pawn(FigureColor.BLACK, Position(1, 6), board),
    Pawn(FigureColor.BLACK, Position(2, 6), board),
    Pawn(FigureColor.BLACK, Position(3, 6), board),
    Pawn(FigureColor.BLACK, Position(4, 6), board),
    Pawn(FigureColor.BLACK, Position(5, 6), board),
    Pawn(FigureColor.BLACK, Position(6, 6), board),
    Pawn(FigureColor.BLACK, Position(7, 6), board),

    Rook(FigureColor.WHITE, Position(0, 0), board),
    Knight(FigureColor.WHITE, Position(1, 0), board),
    Bishop(FigureColor.WHITE, Position(2, 0), board),
    Queen(FigureColor.WHITE, Position(3, 0), board),
    King(FigureColor.WHITE, Position(4, 0), board),
    Bishop(FigureColor.WHITE, Position(5, 0), board),
    Knight(FigureColor.WHITE, Position(6, 0), board),
    Rook(FigureColor.WHITE, Position(7, 0), board),

    Pawn(FigureColor.WHITE, Position(0, 1), board),
    Pawn(FigureColor.WHITE, Position(1, 1), board),
    Pawn(FigureColor.WHITE, Position(2, 1), board),
    Pawn(FigureColor.WHITE, Position(3, 2), board),
    Pawn(FigureColor.WHITE, Position(4, 1), board),
    Pawn(FigureColor.WHITE, Position(5, 1), board),
    Pawn(FigureColor.WHITE, Position(6, 1), board),
    Pawn(FigureColor.WHITE, Position(7, 1), board)
)
