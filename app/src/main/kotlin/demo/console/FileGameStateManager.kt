package demo.console

import com.google.gson.Gson
import demo.domain.Board
import demo.domain.GameStateManager

class FileGameStateManager : GameStateManager {
    private val resourcesManager = ResourcesManager()

    override fun save(board: Board) {
        resourcesManager.appendSave("xd")
//        resourcesManager.appendSave(Gson().toJson(board))
    }

    override fun load(): Board {
        return Gson().fromJson(resourcesManager.loadLastGameSave(), Board::class.java)
    }
}