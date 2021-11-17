package demo.domain

import demo.domain.*

val figuresInitPosition: Map<Position, Figure> = mapOf(
    Pair(Position(0, 7), Rook(FigureColor.BLACK)),
    Pair(Position(1, 7), Knight(FigureColor.BLACK)),
    Pair(Position(2, 7), Bishop(FigureColor.BLACK)),
    Pair(Position(3, 7), Queen(FigureColor.BLACK)),
    Pair(Position(4, 7), King(FigureColor.BLACK)),
    Pair(Position(5, 7), Bishop(FigureColor.BLACK)),
    Pair(Position(6, 7), Knight(FigureColor.BLACK)),
    Pair(Position(7, 7), Rook(FigureColor.BLACK)),

    Pair(Position(0, 6), Pawn(FigureColor.BLACK)),
    Pair(Position(1, 6), Pawn(FigureColor.BLACK)),
    Pair(Position(2, 6), Pawn(FigureColor.BLACK)),
    Pair(Position(3, 6), Pawn(FigureColor.BLACK)),
    Pair(Position(4, 6), Pawn(FigureColor.BLACK)),
    Pair(Position(5, 6), Pawn(FigureColor.BLACK)),
    Pair(Position(6, 6), Pawn(FigureColor.BLACK)),
    Pair(Position(7, 6), Pawn(FigureColor.BLACK)),

    Pair(Position(0, 0), Rook(FigureColor.WHITE)),
    Pair(Position(1, 0), Knight(FigureColor.WHITE)),
    Pair(Position(2, 0), Bishop(FigureColor.WHITE)),
    Pair(Position(3, 0), Queen(FigureColor.WHITE)),
    Pair(Position(4, 0), King(FigureColor.WHITE)),
    Pair(Position(5, 0), Bishop(FigureColor.WHITE)),
    Pair(Position(6, 0), Knight(FigureColor.WHITE)),
    Pair(Position(7, 0), Rook(FigureColor.WHITE)),

    Pair(Position(0, 1), Pawn(FigureColor.WHITE)),
    Pair(Position(1, 1), Pawn(FigureColor.WHITE)),
    Pair(Position(2, 1), Pawn(FigureColor.WHITE)),
    Pair(Position(3, 1), Pawn(FigureColor.WHITE)),
    Pair(Position(4, 1), Pawn(FigureColor.WHITE)),
    Pair(Position(5, 1), Pawn(FigureColor.WHITE)),
    Pair(Position(6, 1), Pawn(FigureColor.WHITE)),
    Pair(Position(7, 1), Pawn(FigureColor.WHITE))
)
