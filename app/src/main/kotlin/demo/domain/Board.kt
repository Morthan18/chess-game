package demo.domain

class Board(var playerTurn: PlayerTurn = PlayerTurn.WHITE) {
    private var figures: MutableList<Figure> = mutableListOf()

    fun setFigures(figures: MutableList<Figure>) {
        this.figures = figures;
    }

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

        val checkValidation = validateCheckOnKing(this)
        if (checkValidation.check) {
            val attackingFigure = checkValidation.attackingFigure
            var fieldsAvailableAtCheck =
                when (attackingFigure) {
                    is Bishop -> getFieldsAvailableToBlockOblique(attackingFigure)
                    is Knight -> listOf(attackingFigure.position)
                    is Pawn -> listOf(attackingFigure.position)
                    is Queen -> getFieldsAvailableToBlockObliqueAndStraight(attackingFigure)
                    is Rook -> getFieldsAvailableToBlockStraight(attackingFigure)
                    else -> emptyList()
                }

            fieldsAvailableAtCheck = listOf(fieldsAvailableAtCheck, listOf(attackingFigure!!.position)).flatten()

            if (figure is King && kingCanRunAwayFromCheck(figure, toPosition)) {
                return true
            }

            if (figure !is King
                && figure.getLegalMoves().contains(toPosition)
                && fieldsAvailableAtCheck.contains(toPosition)
            ) {
                return true
            }

            return false
        }

        val futureBoard = getFutureBoard()
        futureBoard.makeMove(figure, toPosition)
        futureBoard.playerTurn = this.playerTurn
        val futureCheckValidation = validateCheckOnKing(futureBoard)
        if (futureCheckValidation.check) {
            return false
        }



        return figure.getLegalMoves().contains(toPosition)
    }

    private fun kingCanRunAwayFromCheck(king: King, moveIntention: Position): Boolean {
        val futureBoard = getFutureBoard()
        futureBoard.makeMove(king, moveIntention)

        val fieldsNotAttacked1MoveInFuture = getFieldsNotAttackedByAnyFigure(king, futureBoard)

        return fieldsNotAttacked1MoveInFuture.contains(moveIntention)
    }

    private fun getFutureBoard(): Board {
        val futureBoard = Board(this.playerTurn)
        futureBoard.setFigures(
            this.figures.map { figure -> figure.clone(futureBoard) }.toMutableList()
        )
        return futureBoard
    }

    private fun getFieldsNotAttackedByAnyFigure(king: King, board: Board): List<Position> {
        val kingLegalMoves = king.getLegalMoves()

        val allFieldsAttackedByAllFigures = when (king.figureColor) {
            FigureColor.WHITE -> board.findAllFigures(FigureColor.BLACK)
            FigureColor.BLACK -> board.findAllFigures(FigureColor.WHITE)
        }.map { figure -> figure.getLegalMoves() }.flatten()

        return kingLegalMoves.filter { kingMove -> !allFieldsAttackedByAllFigures.contains(kingMove) }
    }

    private fun getFieldsAvailableToBlockObliqueAndStraight(attackingFigure: Figure): List<Position> {
        val fieldsAvailableToBlockOblique = getFieldsAvailableToBlockOblique(attackingFigure)
        val fieldsAvailableToBlockStraight = getFieldsAvailableToBlockStraight(attackingFigure)

        return listOf(fieldsAvailableToBlockStraight, fieldsAvailableToBlockOblique).flatten()
    }

    private fun getFieldsAvailableToBlockStraight(attackingFigure: Figure): List<Position> {
        val kingPosition = getCurrentPlayerKingPosition()
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

    private fun getFieldsAvailableToBlockOblique(attackingFigure: Figure): List<Position> {
        val attackingFigureMoves: List<Position> = attackingFigure.getLegalMoves()
        val kingPosition = getCurrentPlayerKingPosition()
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

    private fun validateCheckOnKing(board: Board): CheckValidationResult {
        val figuresForColor = when (playerTurn) {
            PlayerTurn.WHITE -> board.findAllFigures(FigureColor.BLACK)
            PlayerTurn.BLACK -> board.findAllFigures(FigureColor.WHITE)
        }

        val checkingFigures = figuresForColor
            .filter { figure -> figure.getLegalMoves().contains(board.getCurrentPlayerKingPosition()) }

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

    private fun findAllFigures(figureColor: FigureColor): List<Figure> {
        return figures.filter { figure -> figure.figureColor == figureColor }
    }

    private fun findKing(figureColor: FigureColor): King {
        return figures.first { figure -> figure is King && figure.figureColor == figureColor } as King
    }

    fun save(gameStateManager: GameStateManager) {
        gameStateManager.save(this)
    }

    fun load(gameStateManager: GameStateManager) {
        gameStateManager.load()
    }
}