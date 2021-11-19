package demo.console

import demo.domain.BackgroundColor
import demo.domain.FigureColor
import java.io.InputStream


class ResourcesLoader {

    fun getPawn(color: FigureColor, backgroundColor: BackgroundColor): Texture {
        return Texture(loadTextureContent("pawn.txt"), color, backgroundColor)
    }

    fun getRook(color: FigureColor, backgroundColor: BackgroundColor): Texture {
        return Texture(loadTextureContent("rook.txt"), color, backgroundColor)
    }

    fun getKnight(color: FigureColor, backgroundColor: BackgroundColor): Texture {
        return Texture(loadTextureContent("knight.txt"), color, backgroundColor)
    }

    fun getBishop(color: FigureColor, backgroundColor: BackgroundColor): Texture {
        return Texture(loadTextureContent("bishop.txt"), color, backgroundColor)
    }

    fun getQueen(color: FigureColor, backgroundColor: BackgroundColor): Texture {
        return Texture(loadTextureContent("queen.txt"), color, backgroundColor)
    }

    fun getKing(color: FigureColor, backgroundColor: BackgroundColor): Texture {
        return Texture(loadTextureContent("king.txt"), color, backgroundColor)
    }

    fun getEmptyField(backgroundColor: BackgroundColor): Texture {
        return Texture(loadTextureContent("empty_field.txt"), null, backgroundColor)
    }

    private fun loadTextureContent(textureName: String): List<String> {
        val inputStream: InputStream? = Thread.currentThread().contextClassLoader.getResourceAsStream(textureName)

        return inputStream!!.bufferedReader()
            .use { it.readLines() }
    }
}
