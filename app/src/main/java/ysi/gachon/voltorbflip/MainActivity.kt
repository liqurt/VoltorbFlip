package ysi.gachon.voltorbflip

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Toast
import kotlinx.android.synthetic.main.activity_main.*

class MainActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        start.setOnClickListener{
            val gameIntent = Intent(this, GameActivity::class.java)
            startActivity(gameIntent)
        }

        howtoplay.setOnClickListener{
            Toast.makeText(this,"미구현",Toast.LENGTH_SHORT).show()
            finish()
        }

        quit.setOnClickListener{
            Toast.makeText(this,"빠이",Toast.LENGTH_SHORT).show()
            finish()
        }
    }

}


