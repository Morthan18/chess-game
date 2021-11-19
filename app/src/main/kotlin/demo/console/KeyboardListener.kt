package demo.console

import org.jnativehook.keyboard.NativeKeyEvent
import org.jnativehook.keyboard.NativeKeyListener

class KeyboardListener(private val boardRenderer: ConsoleBoardRenderer) : NativeKeyListener {

    override fun nativeKeyPressed(p0: NativeKeyEvent?) {
    }

    override fun nativeKeyReleased(event: NativeKeyEvent?) {
        val key = event?.keyCode?.let { NativeKeyEvent.getKeyText(it) }
        when (key) {
            "Up" -> boardRenderer.moveCursorUp()
            "Down" -> boardRenderer.moveCursorDown()
            "Left" -> boardRenderer.moveCursorLeft()
            "Right" -> boardRenderer.moveCursorRight()
            "Space" -> boardRenderer.selectFigure()
            "Escape" -> boardRenderer.unmarkFigure()
        }
    }

    override fun nativeKeyTyped(p0: NativeKeyEvent?) {
    }
}