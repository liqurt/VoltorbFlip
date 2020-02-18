package ysi.gachon.voltorbflip

import android.os.Bundle
import android.util.Log
import androidx.appcompat.app.AppCompatActivity
import kotlinx.android.synthetic.main.activity_game.*
import kotlin.random.Random


class GameActivity : AppCompatActivity() {

    var gameBoard = Array(5, {IntArray(5)})


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_game)

        gameBoardSetUp()
        gameBoardSideSetUp()
    }

    //0과 1을 제외한 모든 숫자를 뒤집으면 게임 끝

    // 0 - 찌리리공
    // 1~3 - 숫자
    private fun gameBoardSetUp() {
        for(i in 0..4){
            for(j in 0..4){
                gameBoard[i][j]=Random.nextInt(4)
            }
            Log.d("tag","${gameBoard[i][0]},${gameBoard[i][1]},${gameBoard[i][2]},${gameBoard[i][3]},${gameBoard[i][4]}")
        }
    }

    private fun gameBoardSideSetUp() {
        var numSum = 0
        var ballSum = 0
        var currentCell : Int

        //우측 라인 셋업
        for(i in 0..4){
            for (j in 0..4){
                currentCell = gameBoard[i][j]
                numSum += currentCell
                if(currentCell == 0){
                    ballSum++
                }
            }
            when(i){
                0 -> {
                    row0NumSum.text = numSum.toString()
                    row0BallSum.text = ballSum.toString()
                }

                1 -> {
                    row1NumSum.text = numSum.toString()
                    row1BallSum.text = ballSum.toString()
                }
                2 -> {
                    row2NumSum.text = numSum.toString()
                    row2BallSum.text = ballSum.toString()
                }
                3 -> {
                    row3NumSum.text = numSum.toString()
                    row3BallSum.text = ballSum.toString()
                }
                4 -> {
                    row4NumSum.text = numSum.toString()
                    row4BallSum.text = ballSum.toString()
                }
            }
            numSum=0
            ballSum=0
        }

        //아랫 라인 셋업
        for(j in 0..4){
            for (i in 0..4){
                currentCell = gameBoard[i][j]
                numSum += currentCell
                if(currentCell == 0){
                    ballSum++
                }
            }
            when(j){
                0 -> {
                    col0NumSum.text = numSum.toString()
                    col0BallSum.text = ballSum.toString()
                }

                1 -> {
                    col1NumSum.text = numSum.toString()
                    col1BallSum.text = ballSum.toString()
                }
                2 -> {
                    col2NumSum.text = numSum.toString()
                    col2BallSum.text = ballSum.toString()
                }
                3 -> {
                    col3NumSum.text = numSum.toString()
                    col3BallSum.text = ballSum.toString()
                }
                4 -> {
                    col4NumSum.text = numSum.toString()
                    col4BallSum.text = ballSum.toString()
                }
            }
            numSum=0
            ballSum=0
        }
    }



}
