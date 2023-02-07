class TicTakToe {
    private val FIGURES = arrayOf("X", "O")
    private val field = createField()
    private var isXTurn = true
    private var isFinished = false
    private var winner = ""

    private fun createField(): Array<Array<String>> {
        val row = arrayOf("", "", "")
        return arrayOf(row.clone(), row.clone(), row.clone())
    }

    private fun setFigure(x: Int, y: Int) {
        if (field[x][y] != "") {
            println("Already used!")
            return
        }

        field[x][y] = if (isXTurn) FIGURES[0] else FIGURES[1]
    }

    private fun changeTurn() {
        isXTurn = !isXTurn
    }

    fun restart() {
        isXTurn = true
        isFinished = false
        for (i in 0..2) {
            for (j in 0..2)
                field[i][j] = ""
        }
    }

    fun turn(x: Int, y: Int) {
        if (isFinished) {
            println("Game is over. Winner is $winner")
            return
        }

        setFigure(x, y)
        isWin()
        changeTurn()
    }

    private fun isWin() {
        if (checkWin()) {
            winner = if (isXTurn) FIGURES[0] else FIGURES[1]
            isFinished = true
            println("$winner is winner!")
        }
    }

    private fun checkWin(): Boolean {
        val fig = FIGURES[0]
        var row = 0
        var col = 0
        var d1 = 0
        var d2 = 0

        for (i in 0..2) {
            // Diagonal 1
            val dVal = field[i][i]
            if (dVal != "") if (dVal == fig) d1++ else d1--
            if (Math.abs(d1) == 3) return true

            // Diagonal 2
            val dVal2 = field[i][2-i]
            if (dVal2 != "") if (dVal2 == fig) d2++ else d2--
            if (Math.abs(d2) == 3) return true

            for (j in 0..2) {
                val rowVal = field[i][j]
                if (rowVal != "") if (rowVal == fig) row++ else row--

                val colVal = field[j][i]
                if (colVal != "") if (colVal == fig) col++ else col--
            }

            if (Math.abs(row) == 3) return true
            if (Math.abs(col) == 3) return true

            row = 0
            col = 0
        }

        return false
    }
}

val game = TicTakToe()

game.turn(0, 0)
game.turn(1, 0)
game.turn(0, 1)
game.turn(1, 1)
game.turn(0, 2)
game.turn(0, 2)
game.turn(0, 2)
game.turn(0, 2)

