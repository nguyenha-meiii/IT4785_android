package com.example.bai2

import android.os.Bundle
import android.widget.ArrayAdapter
import android.widget.Button
import android.widget.EditText
import android.widget.ListView
import android.widget.RadioGroup
import android.widget.TextView
import androidx.activity.enableEdgeToEdge
import androidx.appcompat.app.AppCompatActivity
import androidx.core.view.ViewCompat
import androidx.core.view.WindowInsetsCompat

class MainActivity : AppCompatActivity() {
    private lateinit var edtNumber: EditText
    private lateinit var radioGroup: RadioGroup
    private lateinit var btnShow: Button
    private lateinit var listView: ListView
    private lateinit var tvError: TextView
    private lateinit var adapter: ArrayAdapter<Int>
    private val numbers = mutableListOf<Int>()
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContentView(R.layout.activity_main)
        ViewCompat.setOnApplyWindowInsetsListener(findViewById(R.id.main)) { v, insets ->
            val systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars())
            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom)
            insets
        }
        edtNumber = findViewById(R.id.edtNumber)
        radioGroup = findViewById(R.id.radioGroup)
        btnShow = findViewById(R.id.btnShow)
        listView = findViewById(R.id.listView)
        tvError = findViewById(R.id.tvError)

        // Khởi tạo adapter
        adapter = ArrayAdapter(this, android.R.layout.simple_list_item_1, numbers)
        listView.adapter = adapter

        btnShow.setOnClickListener {
            processInput()
        }
    }
    private fun processInput() {
        val input = edtNumber.text.toString()

        // Kiểm tra input rỗng
        if (input.isEmpty()) {
            showError("Vui lòng nhập số!")
            return
        }

        try {
            val n = input.toInt()

            // Kiểm tra số nguyên dương
            if (n < 0) {
                showError("Vui lòng nhập số nguyên dương!")
                return
            }

            // Xóa danh sách cũ
            numbers.clear()

            // Xử lý theo loại số được chọn
            when (radioGroup.checkedRadioButtonId) {
                R.id.rbEven -> generateEvenNumbers(n)
                R.id.rbOdd -> generateOddNumbers(n)
                R.id.rbSquare -> generateSquareNumbers(n)
                else -> showError("Vui lòng chọn loại số!")
            }

            // Cập nhật ListView và xóa thông báo lỗi
            adapter.notifyDataSetChanged()
            tvError.text = ""

        } catch (e: NumberFormatException) {
            showError("Vui lòng nhập số hợp lệ!")
        }
    }

    private fun generateEvenNumbers(n: Int) {
        for (i in 0..n step 2) {
            numbers.add(i)
        }
    }

    private fun generateOddNumbers(n: Int) {
        for (i in 1..n step 2) {
            numbers.add(i)
        }
    }

    private fun generateSquareNumbers(n: Int) {
        var i = 0
        while (i * i <= n) {
            numbers.add(i * i)
            i++
        }
    }

    private fun showError(message: String) {
        tvError.text = message
        numbers.clear()
        adapter.notifyDataSetChanged()
    }
}