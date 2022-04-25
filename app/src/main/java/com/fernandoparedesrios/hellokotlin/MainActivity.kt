package com.fernandoparedesrios.hellokotlin

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.speech.tts.TextToSpeech
import android.widget.Button
import android.widget.TextView
import java.util.*

class MainActivity : AppCompatActivity(), TextToSpeech.OnInitListener {
//? significa que la variable puede ser null
    private var tts: TextToSpeech? = null

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        tts = TextToSpeech(this, this)

//        var message: String = findViewById<TextView>(R.id.textView).text.toString()
//        Log.i("message textView", message)
        findViewById<Button>(R.id.btnPlay).setOnClickListener { speak() }
    }

    private fun speak(){
        var message: String = findViewById<TextView>(R.id.etMessage).text.toString()
//        validacion
        if(message.isEmpty() ){
            findViewById<TextView>(R.id.tvStatus).text = getString(R.string.tts_no_message)
            message = "Really? Ya pon algo en el edit text"
        }
//!! indica que la variable no sera null
        tts!!.speak(message, TextToSpeech.QUEUE_FLUSH, null, "")
    }



    override fun onInit(status: Int) {
        if (status == TextToSpeech.SUCCESS){
            findViewById<TextView>(R.id.tvStatus).text = getString(R.string.tts_active)
            tts!!.language = Locale("ES")
        } else {
            findViewById<TextView>(R.id.tvStatus).text = getString(R.string.tts_no_active)
        }
    }

    override fun onDestroy() {
        if (tts != null) {
            tts!!.stop()
            tts!!.shutdown()
        }
        super.onDestroy()
    }
}