package com.example.myapplication

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import com.example.myapplication.databinding.ActivityMainBinding
import java.lang.Exception
import java.lang.RuntimeException
import java.lang.StringBuilder
import java.security.MessageDigest

class MainActivity : AppCompatActivity() {


    private lateinit var binding:ActivityMainBinding
    private var password = ""


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)

        binding.btnHash.setOnClickListener{
            password = hashPassword(binding.hashPass.text.toString().trim()).toString().trim()
            binding.txtHash.text = password
        }
    }
    private fun hashPassword(base: String): String {
        try {
            val digest: MessageDigest = MessageDigest.getInstance("SHA-256")
            val hash: ByteArray = digest.digest(base.toByteArray(Charsets.UTF_8))
            val hexString = StringBuilder()
            for (i in hash.indices) {
                val hex = Integer.toHexString(0xff and hash[i].toInt())
                if (hex.length == 1) hexString.append('0')
                hexString.append(hex)
            }
            return hexString.toString()
        } catch (ex: Exception) {
            throw RuntimeException(ex)
        }
    }

}