package com.example.coc_char

import android.content.Intent
import android.os.Bundle
import android.view.Menu
import android.view.MenuItem
import androidx.activity.enableEdgeToEdge
import androidx.appcompat.app.AppCompatActivity
import androidx.core.view.ViewCompat
import androidx.core.view.WindowInsetsCompat
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView

class MainActivity : AppCompatActivity() {
    private lateinit var rvChar: RecyclerView
    private val list = ArrayList<Char>()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        rvChar = findViewById(R.id.rv_char)
        rvChar.setHasFixedSize(true)

        list.addAll(getListChar())
        showRecyclerList()
    }

    override fun onCreateOptionsMenu(menu: Menu?): Boolean {
        menuInflater.inflate(R.menu.main_menu, menu)
        return super.onCreateOptionsMenu(menu)
    }

    override fun onOptionsItemSelected(item: MenuItem): Boolean {
        when(item.itemId) {
            R.id.about_page -> {
                val intent = Intent(this, AboutActivity::class.java)
                startActivity(intent)
                return true
            }
            else -> return super.onOptionsItemSelected(item)
        }
        return super.onOptionsItemSelected(item)
    }

    private fun getListChar(): ArrayList<Char> {
        val charName = resources.getStringArray(R.array.char_name)
        val charDescription = resources.getStringArray(R.array.char_description)
        val charDps = resources.getStringArray(R.array.char_dmg).map { it.toInt() }
        val charHp = resources.getStringArray(R.array.char_hp).map { it.toInt() }
        val charAttackType = resources.getStringArray(R.array.char_attack_type)
        val charPhoto = resources.getStringArray(R.array.char_pict)

        val listChar = ArrayList<Char>()
        for (i in charName.indices) {
            val Char = Char(charName[i], charDescription[i], charPhoto[i], charDps[i], charHp[i], charAttackType[i])
            listChar.add(Char)
        }
        return listChar
    }

    private fun showRecyclerList() {
        rvChar.layoutManager = LinearLayoutManager(this)
        val listCharAdapter = ListCharAdapter(list)
        rvChar.adapter = listCharAdapter

        listCharAdapter.setOnItemClickCallback(object : ListCharAdapter.OnItemClickCallback {
            override fun onItemClicked(data: Char) {
                val intent = Intent(this@MainActivity, DetailCharActivity::class.java)
                intent.putExtra(DetailCharActivity.EXTRA_CHAR, data)
                startActivity(intent)
            }
        })
    }
}