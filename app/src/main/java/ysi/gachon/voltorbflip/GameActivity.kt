package ysi.gachon.voltorbflip

import android.content.Context
import android.content.SharedPreferences
import android.media.AudioManager
import android.media.MediaPlayer
import android.media.SoundPool
import android.os.Bundle
import android.os.Handler
import android.util.Log
import android.view.View
import android.widget.ImageButton
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import com.google.android.gms.ads.AdRequest
import com.google.android.gms.ads.MobileAds
import kotlinx.android.synthetic.main.activity_game.*
import kotlin.random.Random


class GameActivity : AppCompatActivity(), View.OnClickListener{
    companion object{
        lateinit var bgmPlayer : MediaPlayer
        lateinit var pref : SharedPreferences
        lateinit var editor : SharedPreferences.Editor
        var stage : Int = 0
        var highScore : Int = 0
        var previousScore : Int =0
        var currentScore : Int = 0
        val randomNumberPool : List<Int> = listOf(0,0,0,1,1,1,1,1,2,3)
        val soundPool : SoundPool= SoundPool.Builder().build()
        var soundWow : Int = 0
        var soundYouDontHit : Int = 0
        var soundBGM0 : Int = 0
        var soundBGM1 : Int = 1
        var soundBGM2 : Int = 2
        var soundBGM3 : Int = 3
        var soundBGM4 : Int = 4
        var soundBGM5 : Int = 5
    }

    var gameBoard = Array(5, {IntArray(5)})
    var remainCount : Int = 0

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_game)
        adviewSetup()
        soundSetup()
        sharedPreferencesSetup()
        gameBoardSetUp()
        gameBoardSideSetUp()
        setOnClickListenerActivating()
    }

    private fun soundSetup() {
        soundWow = soundPool.load(this, R.raw.wow, 2)
        soundYouDontHit = soundPool.load(this, R.raw.youdonthit, 2)
        soundBGM0 = soundPool.load(this, R.raw.pokemoncenter0, 1)
        soundBGM1 = soundPool.load(this, R.raw.pokemoncenter1, 1)
        soundBGM2 = soundPool.load(this, R.raw.pokemoncenter2, 1)
        soundBGM3 = soundPool.load(this, R.raw.pokemoncenter3, 1)
        soundBGM4 = soundPool.load(this, R.raw.pokemoncenter4, 1)
        soundBGM5 = soundPool.load(this, R.raw.pokemoncenter5, 1)
    }

    fun playBGM(){
        val playThis = stage%6
        when(playThis){
            0->bgmPlayer = MediaPlayer.create(this, R.raw.pokemoncenter0)
            1->bgmPlayer = MediaPlayer.create(this, R.raw.pokemoncenter1)
            2->bgmPlayer = MediaPlayer.create(this, R.raw.pokemoncenter2)
            3->bgmPlayer = MediaPlayer.create(this, R.raw.pokemoncenter3)
            4->bgmPlayer = MediaPlayer.create(this, R.raw.pokemoncenter4)
            5->bgmPlayer = MediaPlayer.create(this, R.raw.pokemoncenter5)
        }
        bgmPlayer.setLooping(true)
        bgmPlayer.start()
    }

    private fun adviewSetup() {
        MobileAds.initialize(this) {}
        val adRequest = AdRequest.Builder().build()
        adView.loadAd(adRequest)
    }

    private fun sharedPreferencesSetup() {
        pref = this.getPreferences(Context.MODE_PRIVATE)
        editor = pref.edit()
    }

    private fun setOnClickListenerDeactivating(){
        cell00.setOnClickListener(null)
        cell01.setOnClickListener(null)
        cell02.setOnClickListener(null)
        cell03.setOnClickListener(null)
        cell04.setOnClickListener(null)
        cell10.setOnClickListener(null)
        cell11.setOnClickListener(null)
        cell12.setOnClickListener(null)
        cell13.setOnClickListener(null)
        cell14.setOnClickListener(null)
        cell20.setOnClickListener(null)
        cell21.setOnClickListener(null)
        cell22.setOnClickListener(null)
        cell23.setOnClickListener(null)
        cell24.setOnClickListener(null)
        cell30.setOnClickListener(null)
        cell31.setOnClickListener(null)
        cell32.setOnClickListener(null)
        cell33.setOnClickListener(null)
        cell34.setOnClickListener(null)
        cell40.setOnClickListener(null)
        cell41.setOnClickListener(null)
        cell42.setOnClickListener(null)
        cell43.setOnClickListener(null)
        cell44.setOnClickListener(null)
    }

    private fun setOnClickListenerActivating() {
        cell00.setOnClickListener(this)
        cell01.setOnClickListener(this)
        cell02.setOnClickListener(this)
        cell03.setOnClickListener(this)
        cell04.setOnClickListener(this)
        cell10.setOnClickListener(this)
        cell11.setOnClickListener(this)
        cell12.setOnClickListener(this)
        cell13.setOnClickListener(this)
        cell14.setOnClickListener(this)
        cell20.setOnClickListener(this)
        cell21.setOnClickListener(this)
        cell22.setOnClickListener(this)
        cell23.setOnClickListener(this)
        cell24.setOnClickListener(this)
        cell30.setOnClickListener(this)
        cell31.setOnClickListener(this)
        cell32.setOnClickListener(this)
        cell33.setOnClickListener(this)
        cell34.setOnClickListener(this)
        cell40.setOnClickListener(this)
        cell41.setOnClickListener(this)
        cell42.setOnClickListener(this)
        cell43.setOnClickListener(this)
        cell44.setOnClickListener(this)
    }

    private fun gameBoardSetUp() {
        randomNumberPool.shuffled()
        remainCount = 0
        for(i in 0..4){
            for(j in 0..4){
                gameBoard[i][j]= randomNumberPool[Random.nextInt(randomNumberPool.size)]
                if(gameBoard[i][j] != 0 && gameBoard[i][j] != 1){
                    remainCount++
                }
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

        //우하단 코너 셋업
        val hs = pref.getInt("HIGHSCORE",0)
        highScoreText.setText(hs.toString())
        currentScoreText.setText((currentScore+previousScore).toString())
        Log.d("tag","(gameBoardSideSetUp)currentScore : $currentScore, previousScore : $previousScore")
    }

    override fun onClick(v: View) {
        val view = v!!.resources.getResourceEntryName(v.id)
        if(view.startsWith("cell")) {
            val row = Integer.parseInt(view[4].toString())
            val col = Integer.parseInt(view[5].toString())
            val selectedView: ImageButton = findViewById(v.id)
            when (gameBoard[row][col]) {
                0 -> {
                    gameOver(selectedView)
                }
                1 -> {
                    selectedView.setImageResource(R.drawable.cell1)
                }
                2 -> {
                    selectedView.setImageResource(R.drawable.cell2)
                    remainCount = not0or1(remainCount, gameBoard[row][col])
                }
                3 -> {
                    selectedView.setImageResource(R.drawable.cell3)
                    remainCount = not0or1(remainCount, gameBoard[row][col])
                }
            }
            v.setOnClickListener(null)
            when (remainCount) {
                0 -> gameClear()
            }
        }
    }

    private fun not0or1(rC: Int, cellValue : Int) : Int{
        if(currentScore==0)
            currentScore=1
        currentScore *= cellValue
        return rC-1
    }

    private fun gameOver(selectedView : ImageButton){
        playYonDontHitSound()
        Toast.makeText(this,"Game over",Toast.LENGTH_SHORT).show()
        selectedView.setImageResource(R.drawable.cell0)
        setOnClickListenerDeactivating()
        bgmPlayer.release()
        val handler = Handler()
        handler.postDelayed({
            initCurrentPreviousScore()
            finish()
        }, 1000)
    }

    private fun gameClear(){
        playWowSound()
        bgmPlayer.release()
        Log.d("tag","(clear)Current score : $currentScore")
        Toast.makeText(this,"You've got $currentScore in this game!",Toast.LENGTH_SHORT).show()
        setOnClickListenerDeactivating()
        previousScore += currentScore
        currentScore=0
        highScoreComparison()
        stage++
        val handler = Handler()
        handler.postDelayed({
            finish()
            startActivity(getIntent())
        }, 1500)
    }

    private fun playYonDontHitSound(){
        soundPool.play(soundYouDontHit, 1.0f, 1.0f,0,0,1.0f)
    }

    private fun playWowSound(){
        soundPool.play(soundWow, 1.0f, 1.0f,0,0,1.0f)
    }

    private fun initCurrentPreviousScore(){
        currentScore=0
        previousScore=0
        currentScoreText.setText("0")
    }

    private fun highScoreComparison(){
        if(highScore <= previousScore){
            highScore = previousScore
            editor.putInt("HIGHSCORE", highScore)
            editor.commit()
        }
    }

    override fun onResume() {
        playBGM()
        super.onResume()
    }

    override fun onPause() {
        bgmPlayer.release()
        super.onPause()
    }

}