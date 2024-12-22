package com.example.coc_char

import android.content.Intent
import android.os.Bundle
import androidx.activity.enableEdgeToEdge
import androidx.appcompat.app.AppCompatActivity
import androidx.core.view.ViewCompat
import androidx.core.view.WindowInsetsCompat
import com.bumptech.glide.Glide
import com.example.coc_char.databinding.ActivityDetailCharBinding

class DetailCharActivity : AppCompatActivity() {

    companion object {
        const val EXTRA_CHAR = "Data"
    }

    private lateinit var binding: ActivityDetailCharBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        binding = ActivityDetailCharBinding.inflate(layoutInflater)
        setContentView(binding.root)    

        val data = intent.getParcelableExtra<Char>(EXTRA_CHAR)

        binding.tvCharName.text = data?.name
        binding.tvDesc.text = data?.description
        if(data?.name ==  "Healer"){
            binding.tvDmg.text = data.heal
        }
        binding.tvDmgDetail.text = " : " + data?.dmg.toString()
        binding.tvHpDetail.text =  " : " + data?.hp.toString()
        binding.tvAttackTypeDetail.text =  " : " + data?.attackType.toString()
        Glide.with(this)
            .load(data?.photo)
            .into(binding.imgItemPhoto)

        binding.actionShare.setOnClickListener {
            val shareText: String
            if(data?.name == "Healer"){
                shareText = "Name: ${data?.name}" +
                        "\nDescription: ${data?.description}" +
                        "\nHealing: ${data?.dmg}" +
                        "\nHP: ${data?.hp}" +
                        "\nAttack Type: ${data?.attackType}"
            }else{
                shareText = "Name: ${data?.name}" +
                        "\nDescription: ${data?.description}" +
                        "\nDamage: ${data?.dmg}" +
                        "\nHP: ${data?.hp}" +
                        "\nAttack Type: ${data?.attackType}"
            }
            val intent = Intent(Intent.ACTION_SEND)
            intent.type = "text/plain"
            intent.putExtra(Intent.EXTRA_SUBJECT, "Sharing ${data?.name}")
            intent.putExtra(Intent.EXTRA_TEXT, shareText)
            startActivity(Intent.createChooser(intent, "Share to"))
        }
    }
}