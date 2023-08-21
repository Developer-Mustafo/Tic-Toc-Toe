package uz.coder.tictoctoe

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.View
import android.widget.Button
import uz.coder.tictoctoe.databinding.ActivityMainBinding

class MainActivity : AppCompatActivity() {
    enum class Turn{
        NOUGHT, CROSS
    }
    private var firstTurn = Turn.CROSS
    private var curretTurn = Turn.CROSS
    private var boardList = mutableListOf<Button>()
    lateinit var binding: ActivityMainBinding
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)
        initBoard()
    }

    private fun initBoard() {
        boardList.add(binding.a1)
        boardList.add(binding.a2)
        boardList.add(binding.a3)
        boardList.add(binding.b1)
        boardList.add(binding.b2)
        boardList.add(binding.b3)
        boardList.add(binding.c1)
        boardList.add(binding.c2)
        boardList.add(binding.c3)
    }

    fun boardTapped(view: View) {
        if (view !is Button)
            return
        addToBoard(view)
    }

    private fun addToBoard(button: Button) {
        if (button.text != "")
            return
        if (curretTurn == Turn.NOUGHT){
            button.text = NOUGHT
            curretTurn = Turn.CROSS
        }else if (curretTurn == Turn.CROSS)
        {
            button.text = CROSS
            curretTurn = Turn.NOUGHT
        }
        setTurnLabel()
    }

    private fun setTurnLabel() {
        var turnText = ""
        if (curretTurn == Turn.CROSS)
            turnText = "Turn $CROSS"
        else if (curretTurn == Turn.NOUGHT)
            turnText = "Turn $NOUGHT"
        binding.turn.text = turnText
    }

    companion object
    {
        const val NOUGHT = "0"
        const val CROSS = "x"
    }
}