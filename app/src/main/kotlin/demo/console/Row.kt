package demo.console

import demo.domain.*

class Row {
    private val boldWhite = "\u001b[1;97m"
    private val boldBlack = "\u001b[1;90m"
    
    private val redBack = "\u001b[41m"
    private val yellowBack = "\u001b[43m"
    private val cyanBack = "\u001b[46m"
    
    private val reset = "\u001b[0m"

    private val textures: MutableList<Texture> = mutableListOf()

    fun addTexture(texture: Texture) {
        textures.add(texture)
    }

    fun getLastTextureBackColor(): BackgroundColor {
        return textures.last().backgroundColor;
    }

    fun getFirstTextureBackColor(): BackgroundColor {
        return textures.first().backgroundColor;
    }

    fun getTwoTexturesOnLeftBackColor(currentPosition: Position): BackgroundColor {
        return textures.first().backgroundColor;
    }

    fun isEmpty(): Boolean {
        return textures.isEmpty()
    }

    fun render() {
        if (textures.isNotEmpty()) {
            val constructedContent: MutableList<String> = mutableListOf()

            for (lineIndex in 0 until textures[0].content.size) {
                var lineContent = ""
                for (texture in textures) {


                    lineContent += when (texture.backgroundColor) {
                        BackgroundColor.RED -> redBack
                        BackgroundColor.YELLOW -> yellowBack
                        BackgroundColor.CYAN -> cyanBack
                    }

                    lineContent += when (texture.color) {
                        FigureColor.WHITE -> boldWhite
                        FigureColor.BLACK -> boldBlack
                        else -> ""
                    }


                    lineContent += texture.content[lineIndex] + reset
                }
                constructedContent.add(lineContent)
            }

            constructedContent.forEach { c -> println(c) }
        }

    }
}