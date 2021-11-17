package demo.console

import demo.domain.*
import java.io.File

class ResourcesLoader {

    fun getPawn(color: FigureColor, backgroundColor: BackgroundColor): Texture {
        return Texture(loadTexture(File("D:\\bulbid\\kotlinproject\\app\\src\\main\\resources\\pawn.txt")), color, backgroundColor)
    }
    
    fun getRook(color: FigureColor, backgroundColor: BackgroundColor): Texture {
        return Texture(loadTexture(File("D:\\bulbid\\kotlinproject\\app\\src\\main\\resources\\rook.txt")), color, backgroundColor)
    }

    fun getKnight(color: FigureColor, backgroundColor: BackgroundColor): Texture {
        return Texture(loadTexture(File("D:\\bulbid\\kotlinproject\\app\\src\\main\\resources\\knight.txt")), color, backgroundColor)
    }

    fun getBishop(color: FigureColor, backgroundColor: BackgroundColor): Texture {
        return Texture(loadTexture(File("D:\\bulbid\\kotlinproject\\app\\src\\main\\resources\\bishop.txt")), color, backgroundColor)
    }

    fun getQueen(color: FigureColor, backgroundColor: BackgroundColor): Texture {
        return Texture(loadTexture(File("D:\\bulbid\\kotlinproject\\app\\src\\main\\resources\\queen.txt")), color, backgroundColor)
    }

    fun getKing(color: FigureColor, backgroundColor: BackgroundColor): Texture {
        return Texture(loadTexture(File("D:\\bulbid\\kotlinproject\\app\\src\\main\\resources\\king.txt")), color, backgroundColor)
    }

    private fun loadTexture(file: File): List<String> {
        return file.inputStream()
            .bufferedReader()
            .use { it.readLines() }
    }

    fun getEmptyField(backgroundColor: BackgroundColor): Texture {
        val content: List<String> = File("D:\\bulbid\\kotlinproject\\app\\src\\main\\resources\\empty_field.txt")
            .inputStream()
            .bufferedReader()
            .use { it.readLines() }

        return Texture(content, null, backgroundColor)
    }


}