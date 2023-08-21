package uz.coder.tictoctoe

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.View
import android.widget.Button
import androidx.appcompat.app.AlertDialog
import uz.coder.tictoctoe.databinding.ActivityMainBinding

class MainActivity : AppCompatActivity() {
    enum class Turn{
        NOUGHT, CROSS
    }
    private var firstTurn = Turn.CROSS
    private var curretTurn = Turn.CROSS
    private var boardList = mutableListOf<Button>()
    private var crossesScore = 0
    private var noughtsScore = 0
    private lateinit var binding: ActivityMainBinding
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
        if (view !is Button){
            return
        }
        addToBoard(view)
        if (fullBoard()){
            result("Teng")
        }else{
            if (checkForVictory(NOUGHT)){
                noughtsScore++
                result("0 yutdi")
            }else if (checkForVictory(CROSS)){
                crossesScore++
                result("X yutdi")
            }
        }
    }

    private fun checkForVictory(s: String): Boolean
    {
        //Horizantal wins
        if(match(binding.a1,s)&&match(binding.a2,s)&&match(binding.a3,s)){
            return true
        }
        if(match(binding.b1,s)&&match(binding.b2,s)&&match(binding.b3,s)){
            return true
        }
        if(match(binding.c1,s)&&match(binding.c2,s)&&match(binding.c3,s)){
            return true
        }
        //Vertical wins
        if(match(binding.a1,s)&&match(binding.b1,s)&&match(binding.c1,s)){
            return true
        }
        if(match(binding.a2,s)&&match(binding.b2,s)&&match(binding.c2,s)){
            return true
        }
        if(match(binding.a3,s)&&match(binding.b3,s)&&match(binding.c3,s)){
            return true
        }
        //Diagonal wins
        if(match(binding.a1,s)&&match(binding.b2,s)&&match(binding.c3,s)){
            return true
        }
        if(match(binding.a3,s)&&match(binding.b2,s)&&match(binding.c1,s)){
            return true
        }
        return false
    }
    private fun match(button: Button,symbol:String):Boolean = button.text == symbol
    private fun result(s: String) {
        val massage  = "\n X : $crossesScore\n\n 0 : $noughtsScore"
       AlertDialog.Builder(this@MainActivity)
            .setTitle(s)
            .setMessage(massage)
            .setPositiveButton("Boshidan boshlash"){_,_->
                resetBoard()
            }
            .setCancelable(false)
            .show()
    }

    private fun resetBoard() {
        for (button in boardList){
            button.text = ""
        }
        if (firstTurn == Turn.NOUGHT){
            firstTurn = Turn.CROSS
        }else if (firstTurn == Turn.CROSS){
            firstTurn = Turn.NOUGHT
        }
        curretTurn = firstTurn
        setTurnLabel()
    }

    private fun fullBoard(): Boolean {
        for(button in boardList){
            if (button.text == "")
                return false
        }
        return true
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
            turnText = "Navbat: $CROSS"
        else if (curretTurn == Turn.NOUGHT)
            turnText = "Navbat: $NOUGHT"
        binding.turn.text = turnText
    }

    companion object
    {
        const val NOUGHT = "0"
        const val CROSS = "x"
    }
}