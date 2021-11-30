package demo.console

import demo.domain.BackgroundColor
import demo.domain.FigureColor
import java.io.File
import java.io.InputStream


class ResourcesManager {

    fun getPawnTexture(color: FigureColor, backgroundColor: BackgroundColor): Texture {
        return Texture(loadFileContent("pawn.txt"), color, backgroundColor)
    }

    fun getRookTexture(color: FigureColor, backgroundColor: BackgroundColor): Texture {
        return Texture(loadFileContent("rook.txt"), color, backgroundColor)
    }

    fun getKnightTexture(color: FigureColor, backgroundColor: BackgroundColor): Texture {
        return Texture(loadFileContent("knight.txt"), color, backgroundColor)
    }

    fun getBishopTexture(color: FigureColor, backgroundColor: BackgroundColor): Texture {
        return Texture(loadFileContent("bishop.txt"), color, backgroundColor)
    }

    fun getQueenTexture(color: FigureColor, backgroundColor: BackgroundColor): Texture {
        return Texture(loadFileContent("queen.txt"), color, backgroundColor)
    }

    fun getKingTexture(color: FigureColor, backgroundColor: BackgroundColor): Texture {
        return Texture(loadFileContent("king.txt"), color, backgroundColor)
    }

    fun getEmptyFieldTexture(backgroundColor: BackgroundColor): Texture {
        return Texture(loadFileContent("empty_field.txt"), null, backgroundColor)
    }

    fun loadLastGameSave(): String {
        return loadFileContent("saves.txt").last()
    }

    fun appendSave(content: String) {
        val uri = Thread.currentThread().contextClassLoader.getResourceAsStream("saves.txt")
        File(uri).appendText(content)
    }

    private fun loadFileContent(textureName: String): List<String> {
        val inputStream: InputStream? = Thread.currentThread().contextClassLoader.getResourceAsStream(textureName)

        return inputStream!!.bufferedReader()
            .use { it.readLines() }
    }
}
