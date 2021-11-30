package demo.domain

interface GameStateManager {
    fun save(board: Board)
    fun load(): Board
}