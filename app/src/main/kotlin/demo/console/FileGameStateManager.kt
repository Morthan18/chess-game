package demo.console

import com.google.gson.GsonBuilder
import demo.domain.Board
import demo.domain.Figure
import demo.domain.GameStateManager

class FileGameStateManager : GameStateManager {
    private val resourcesManager = ResourcesManager()
    private val gson = GsonBuilder().registerTypeAdapter(Figure::class.java, GsonFigureAdapter()).create()
    
    override fun save(board: Board) {
        resourcesManager.appendSave(gson.toJson(board))
    }

    override fun load(): Board {
        return gson.fromJson(resourcesManager.loadLastGameSave(), Board::class.java)
    }
}