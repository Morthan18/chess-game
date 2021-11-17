package demo.console

import demo.domain.*
import org.jnativehook.GlobalScreen
import java.util.logging.Level
import java.util.logging.Logger
import kotlin.concurrent.thread

class ConsoleBoardRenderer(private val board: Board) : BoardRenderer {
    private val cursor: Cursor = Cursor(Position(2, 7), true)

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
        this.refresh()
    }

    fun moveCursorDown() {
        this.cursor.moveDown()
        this.refresh()
    }

    fun moveCursorLeft() {
        this.cursor.moveLeft()
        this.refresh()
    }

    fun moveCursorRight() {
        this.cursor.moveRight()
        this.refresh()
    }

    private fun refresh() {
        this.render()
    }

    private fun findFigure(position: Position): Figure? {
        return board.figures[position]
    }

    private fun findAllMarkedFiguresExcept(figure: Figure): List<Figure> {
        return board.figures.toList()
            .filter { pair -> pair.second != figure }
            .filter { pair -> pair.second.isMarked }
            .map { pair -> pair.second }
    }

    private fun chooseBackgroundColor(position: Position): BackgroundColor {
        if (cursor.isAtPosition(position) && cursor.visible) {
            return BackgroundColor.CYAN
        }

        val figure = findFigure(position)
        if (figure != null && figure.isMarked) {
            return BackgroundColor.CYAN
        }

        return if (position.y % 2 == 0) {
            if (position.x % 2 == 0) {
                BackgroundColor.RED
            } else {
                BackgroundColor.YELLOW
            }
        } else {
            if (position.x % 2 == 0) {
                BackgroundColor.YELLOW
            } else {
                BackgroundColor.RED
            }
        }
    }

    override fun render() {
        val loader = ResourcesLoader()
        val previousRows: MutableList<Row> = mutableListOf()

        for (y in 7 downTo 0) {
            val currentRow = Row()
            for (x in 0..7) {
                val figure: Figure? = findFigure(Position(x, y))
                val texture: Texture = if (figure != null) {
                    when (figure) {
                        is Rook -> loader.getRook(figure.figureColor, chooseBackgroundColor(Position(x, y)))
                        is Pawn -> loader.getPawn(figure.figureColor, chooseBackgroundColor(Position(x, y)))
                        is Knight -> loader.getKnight(figure.figureColor, chooseBackgroundColor(Position(x, y)))
                        is Bishop -> loader.getBishop(figure.figureColor, chooseBackgroundColor(Position(x, y)))
                        is Queen -> loader.getQueen(figure.figureColor, chooseBackgroundColor(Position(x, y)))
                        is King -> loader.getKing(figure.figureColor, chooseBackgroundColor(Position(x, y)))
                        else -> throw RuntimeException("Unknown figure")
                    }
                } else {
                    loader.getEmptyField(chooseBackgroundColor(Position(x, y)))
                }
                currentRow.addTexture(texture)
            }
            previousRows.add(currentRow)
        }
        print("\u001b[H\u001b[2J")

        previousRows.forEach { row -> row.render() }
    }

    override fun markTheFigure() {
        val figure: Figure? = findFigure(cursor.position)
        if (figure != null) {
            figure.isMarked = true
            val otherMarkedFigures = findAllMarkedFiguresExcept(figure)
            if (otherMarkedFigures.isNotEmpty()) {
                otherMarkedFigures.forEach { f -> f.isMarked = false }
            }
        }
        this.refresh()
    }
}