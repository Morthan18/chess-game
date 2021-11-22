package demo.console

import demo.domain.BackgroundColor
import demo.domain.FigureColor

class Row {
    private val reset = "\u001b[0m"

    private val textures: MutableList<Texture> = mutableListOf()

    fun addTexture(texture: Texture) {
        textures.add(texture)
    }

    fun render() {
        if (textures.isNotEmpty()) {
            val constructedContent: MutableList<String> = mutableListOf()

            for (lineIndex in 0 until textures[0].content.size) {
                var lineContent = ""
                for (texture in textures) {
                    
                    lineContent += when (texture.backgroundColor) {
                        BackgroundColor.BLUE -> "\u001b[48;5;67m"
                        BackgroundColor.ORANGE -> "\u001b[48;5;173m"
                        BackgroundColor.IVORY -> "\u001b[48;5;189m"
                        BackgroundColor.RED -> "\u001b[48;5;161m"
                        BackgroundColor.GREEN -> "\u001b[48;5;71m"
                        BackgroundColor.GRAY -> "\u001b[48;5;60m"
                    }

                    lineContent += when (texture.color) {
                        FigureColor.WHITE -> "\u001b[1;97m"
                        FigureColor.BLACK -> "\u001b[1;90m"
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