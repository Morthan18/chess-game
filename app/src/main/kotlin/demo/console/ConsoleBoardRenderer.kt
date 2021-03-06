package demo.console

import demo.domain.*
import demo.domain.Board.Companion.BOARD_SIDE_LENGTH
import org.jnativehook.GlobalScreen
import java.util.logging.Level
import java.util.logging.Logger
import kotlin.concurrent.thread

class ConsoleBoardRenderer(var board: Board) : BoardRenderer {
    private val cursor: Cursor = Cursor(Position(3, 1))
    private val cleanConsole: String = "\u001b[H\u001b[2J"
    private val resourcesManager = ResourcesManager()

    init {
        val logger: Logger = Logger.getLogger(GlobalScreen::class.java.getPackage().name)
        logger.level = Level.OFF

        GlobalScreen.registerNativeHook()
        GlobalScreen.addNativeKeyListener(KeyboardListener(this))

        Runtime.getRuntime().addShutdownHook(
            thread(start = false) { GlobalScreen.unregisterNativeHook() }
        )
    }

    fun moveCursorUp() {
        this.cursor.moveUp()
        this.changeCursorColorIfMoveIntention()
        this.refresh()
    }

    fun moveCursorDown() {
        this.cursor.moveDown()
        this.changeCursorColorIfMoveIntention()
        this.refresh()
    }

    fun moveCursorLeft() {
        this.cursor.moveLeft()
        this.changeCursorColorIfMoveIntention()
        this.refresh()
    }

    fun moveCursorRight() {
        this.cursor.moveRight()
        this.changeCursorColorIfMoveIntention()
        this.refresh()
    }

    override fun render() {
        val rowsToRender: MutableList<Row> = mutableListOf()

        for (y in BOARD_SIDE_LENGTH downTo 0) {
            val currentRow = Row()
            for (x in 0..BOARD_SIDE_LENGTH) {
                val figure: Figure? = board.findFigure(Position(x, y))
                val texture: Texture = if (figure != null) {
                    when (figure) {
                        is Rook -> resourcesManager.getRookTexture(
                            figure.figureColor,
                            chooseBackgroundColor(Position(x, y))
                        )
                        is Pawn -> resourcesManager.getPawnTexture(
                            figure.figureColor,
                            chooseBackgroundColor(Position(x, y))
                        )
                        is Knight -> resourcesManager.getKnightTexture(
                            figure.figureColor,
                            chooseBackgroundColor(Position(x, y))
                        )
                        is Bishop -> resourcesManager.getBishopTexture(
                            figure.figureColor,
                            chooseBackgroundColor(Position(x, y))
                        )
                        is Queen -> resourcesManager.getQueenTexture(
                            figure.figureColor,
                            chooseBackgroundColor(Position(x, y))
                        )
                        is King -> resourcesManager.getKingTexture(
                            figure.figureColor,
                            chooseBackgroundColor(Position(x, y))
                        )
                        else -> throw RuntimeException("Unknown figure")
                    }
                } else {
                    resourcesManager.getEmptyFieldTexture(chooseBackgroundColor(Position(x, y)))
                }
                currentRow.addTexture(texture)
            }
            rowsToRender.add(currentRow)
        }
        print(cleanConsole)

        rowsToRender.forEach { row -> row.render() }
        print("[1] save game   [2] load last saved game \n")
    }

    override fun selectFigure() {
        val actualMarkedFigure: Figure? = board.findFigure(cursor.position)

        val otherMarkedFigures = board.findAllMarkedFiguresExcept(actualMarkedFigure)
        if (otherMarkedFigures.isNotEmpty()) {
            val figureToMove = otherMarkedFigures[0]
            if (board.isMoveLegal(figureToMove, cursor.position)) {
                board.makeMove(figureToMove, cursor.position)
                cursor.setColorBasedOnPlayerTurn(board.playerTurn)
                figureToMove.isMarked = false
            }
        } else {
             actualMarkedFigure?.isMarked = true
        }
        if (board.isCheckMate()) {
            print(cleanConsole)
            if (board.playerTurn == PlayerTurn.BLACK) {
                resourcesManager.getWhiteWinTexture(BackgroundColor.GRAY).content.forEach {
                    println(it)
                }
            } else {
                resourcesManager.getBlackWinTexture(BackgroundColor.GRAY).content.forEach {
                    println(it)
                }
            }
        }
        else {
            this.refresh()
        }
    }

    override fun unmarkFigure() {
        board.findAnyMarkedFigure()?.isMarked = false
        cursor.setColorBasedOnPlayerTurn(board.playerTurn)
        this.refresh()
    }

    private fun changeCursorColorIfMoveIntention() {
        val figure: Figure? = this.board.findAnyMarkedFigure()
        if (figure != null) {
            this.cursor.color =
                if (board.isMoveLegal(figure, this.cursor.position)) {
                    BackgroundColor.GREEN
                } else {
                    BackgroundColor.RED
                }
        }
    }

    private fun refresh() {
        this.render()
    }

    private fun chooseBackgroundColor(position: Position): BackgroundColor {
        if (cursor.isAtPosition(position)) {
            return cursor.color
        }

        val figure = board.findFigure(position)
        if (figure != null && figure.isMarked) {
            return cursor.color
        }

        return if (position.y % 2 == 0) {
            if (position.x % 2 == 0) {
                BackgroundColor.BLUE
            } else {
                BackgroundColor.ORANGE
            }
        } else {
            if (position.x % 2 == 0) {
                BackgroundColor.ORANGE
            } else {
                BackgroundColor.BLUE
            }
        }
    }

    fun loadGame(gameStateManager: FileGameStateManager) {
        val loadedBoard = this.board.load(gameStateManager)
        loadedBoard.figures.forEach { f -> f.board = loadedBoard }
        this.board = loadedBoard
        this.cursor.setColorBasedOnPlayerTurn(loadedBoard.playerTurn)
        this.refresh()
    }
}