package demo.domain

class Board {
    private val figures: MutableList<Figure> = figuresInitPosition(this)
    var playerTurn: PlayerTurn = PlayerTurn.WHITE

    companion object {
        const val BOARD_SIDE_LENGTH: Int = 7
    }

    fun makeMove(figure: Figure, toPosition: Position) {
        val figureToMove: Figure? = findFigure(figure.position)

        val figureToBeat: Figure? = findFigure(toPosition)
        if (figureToBeat != null) {
            figures.remove(figureToBeat)
        }

        figureToMove?.position = toPosition

        if (figureToMove != null && figureToMove is Pawn) {
            figureToMove.alreadyMoved = true
        }

        playerTurn = if (playerTurn == PlayerTurn.WHITE) {
            PlayerTurn.BLACK
        } else {
            PlayerTurn.WHITE
        }
    }

    fun isMoveLegal(figure: Figure, toPosition: Position): Boolean {
        if (figure.figureColor.toString() != playerTurn.toString()) {
            return false
        }

        val checkValidation = validateCheckOnKing()
        if (checkValidation.check) {
            val attackingFigure = checkValidation.attackingFigure
            val kingPosition = getCurrentPlayerKingPosition()

            val fieldsAvailableAtCheck = when (attackingFigure) {
                is Bishop -> getFieldsAvailableToBlockOblique(attackingFigure, kingPosition)
                is Knight -> listOf(attackingFigure.position)
                is Pawn -> listOf(attackingFigure.position)
                is Queen -> getFieldsAvailableToBlockObliqueAndStraight(attackingFigure, kingPosition)
//                is Rook -> 
                else -> emptyList()
            }

            if (figure !is King
                && figure.getLegalMoves().contains(toPosition)
                && fieldsAvailableAtCheck.contains(toPosition)
            ) {
                return true
            }




            return false
        }

        return figure.getLegalMoves().contains(toPosition)
    }

    private fun getFieldsAvailableToBlockObliqueAndStraight(
        attackingFigure: Queen,
        kingPosition: Position
    ): List<Position> {
        val fieldsAvailableToBlockOblique = getFieldsAvailableToBlockOblique(attackingFigure, kingPosition)
        val fieldsAvailableToBlockStraight = getFieldsAvailableToBlockStraight(attackingFigure, kingPosition)

        return if (fieldsAvailableToBlockOblique.contains(attackingFigure.position)) {
            fieldsAvailableToBlockOblique
        } else {
            listOf(fieldsAvailableToBlockStraight, listOf(attackingFigure.position)).flatten()
        }
    }

    private fun getFieldsAvailableToBlockStraight(
        attackingFigure: Figure,
        kingPosition: Position
    ): List<Position> {
        val kingPositionX = kingPosition.x
        val kingPositionY = kingPosition.y

        var x = attackingFigure.position.x
        var y = attackingFigure.position.y

        val fieldsAvailableToBlock: MutableList<Position> = mutableListOf()

        while (x == kingPositionX && y != kingPositionY) {
            if (kingPositionX == x) {
                if (kingPositionY > y) {
                    y++
                } else {
                    y--
                }
            } else {
                if (kingPositionX > x) {
                    x++
                } else {
                    y--
                }
            }
            if (x == kingPositionX && y == kingPositionY) {
                break
            }
            fieldsAvailableToBlock.add(Position(x, y))
        }

        return fieldsAvailableToBlock
    }

    private fun getFieldsAvailableToBlockOblique(
        attackingFigure: Figure,
        kingPosition: Position
    ): List<Position> {
        val attackingFigureMoves: List<Position> = attackingFigure.getLegalMoves()
        val kingPositionX = kingPosition.x
        val kingPositionY = kingPosition.y

        var x = attackingFigure.position.x;
        var y = attackingFigure.position.y;

        val fieldsAvailableToBlock: MutableList<Position> = mutableListOf()

        for (pos in attackingFigureMoves) {
            if (kingPositionX > pos.x) {
                x++
            } else {
                x--
            }
            if (kingPositionY > pos.y) {
                y++
            } else {
                y--
            }

            if (x == kingPositionX || y == kingPositionY) {
                break
            }

            fieldsAvailableToBlock.add(Position(x, y))
        }

        return fieldsAvailableToBlock
    }

    private fun validateCheckOnKing(): CheckValidationResult {
        val figuresForColor = when (playerTurn) {
            PlayerTurn.WHITE -> findAllFigures(FigureColor.BLACK)
            PlayerTurn.BLACK -> findAllFigures(FigureColor.WHITE)
        }

        val checkingFigures = figuresForColor
            .filter { figure -> figure.getLegalMoves().contains(getCurrentPlayerKingPosition()) }

        if (checkingFigures.isEmpty()) {
            return CheckValidationResult(false)
        }

        return CheckValidationResult(true, checkingFigures.first())
    }

    private fun getCurrentPlayerKingPosition(): Position {
        return when (playerTurn) {
            PlayerTurn.WHITE -> findKing(FigureColor.WHITE)
            PlayerTurn.BLACK -> findKing(FigureColor.BLACK)
        }.position
    }

    fun findFigure(position: Position): Figure? {
        return figures.find { f -> f.position == position }
    }

    fun findAllMarkedFiguresExcept(figure: Figure?): List<Figure> {
        return figures
            .filter { f -> f != figure }
            .filter { f -> f.isMarked }
    }

    fun findAnyMarkedFigure(): Figure? {
        return figures.find { f -> f.isMarked }
    }

    fun findAllFigures(figureColor: FigureColor): List<Figure> {
        return figures.filter { figure -> figure.figureColor == figureColor }
    }

    fun findKing(figureColor: FigureColor): King {
        return figures.first { figure -> figure is King && figure.figureColor == figureColor } as King
    }
}