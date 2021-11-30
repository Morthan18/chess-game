package demo.console

import demo.domain.BackgroundColor
import demo.domain.FigureColor
import java.io.File
import java.io.InputStream


class ResourcesManager {
    private val pawn = loadFileContent("pawn.txt")
    private val rook = loadFileContent("rook.txt")
    private val knight = loadFileContent("knight.txt")
    private val bishop = loadFileContent("bishop.txt")
    private val queen = loadFileContent("queen.txt")
    private val king = loadFileContent("king.txt")

    fun getPawnTexture(color: FigureColor, backgroundColor: BackgroundColor): Texture {
        return Texture(pawn, color, backgroundColor)
    }

    fun getRookTexture(color: FigureColor, backgroundColor: BackgroundColor): Texture {
        return Texture(rook, color, backgroundColor)
    }

    fun getKnightTexture(color: FigureColor, backgroundColor: BackgroundColor): Texture {
        return Texture(knight, color, backgroundColor)
    }

    fun getBishopTexture(color: FigureColor, backgroundColor: BackgroundColor): Texture {
        return Texture(bishop, color, backgroundColor)
    }

    fun getQueenTexture(color: FigureColor, backgroundColor: BackgroundColor): Texture {
        return Texture(queen, color, backgroundColor)
    }

    fun getKingTexture(color: FigureColor, backgroundColor: BackgroundColor): Texture {
        return Texture(king, color, backgroundColor)
    }

    fun getEmptyFieldTexture(backgroundColor: BackgroundColor): Texture {
        return Texture(loadFileContent("empty_field.txt"), null, backgroundColor)
    }

    fun loadLastGameSave(): String {
        val file = "D:\\bulbid\\kotlinproject\\app\\src\\main\\resources\\saves.txt"
        val inputStream: InputStream = File(file).inputStream()

        return inputStream.bufferedReader()
            .use { it.readLines() }
            .last()
    }

    fun appendSave(content: String) {
        val uri = "D:\\bulbid\\kotlinproject\\app\\src\\main\\resources\\saves.txt"
        File(uri).appendText("\n" + content)
    }

    private fun loadFileContent(textureName: String): List<String> {
        val inputStream: InputStream? = Thread.currentThread().contextClassLoader.getResourceAsStream(textureName)

        return inputStream!!.bufferedReader()
            .use { it.readLines() }
    }
}
