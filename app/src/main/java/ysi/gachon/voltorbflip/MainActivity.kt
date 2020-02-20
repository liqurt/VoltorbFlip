package ysi.gachon.voltorbflip

import android.content.Intent
import android.media.MediaPlayer
import android.media.SoundPool
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Toast
import com.google.android.gms.ads.MobileAds
import kotlinx.android.synthetic.main.activity_main.*

class MainActivity : AppCompatActivity() {

    lateinit var bgmPlayer : MediaPlayer

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        MobileAds.initialize(this) {}

        start.setOnClickListener{
            bgmPlayer.release()
            val gameIntent = Intent(this, GameActivity::class.java)
            startActivity(gameIntent)
        }

        quit.setOnClickListener{
            finish()
        }
    }

    override fun onResume() {
        playBGM()
        super.onResume()
    }

    private fun playBGM() {
        bgmPlayer = MediaPlayer.create(this, R.raw.mainbgm)
        bgmPlayer.setLooping(true)
        bgmPlayer.start()
    }

    override fun onPause() {
        bgmPlayer.release()
        super.onPause()
    }

}


