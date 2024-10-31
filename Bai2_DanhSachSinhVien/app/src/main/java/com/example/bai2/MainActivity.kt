package com.example.myapplication

import android.os.Bundle
import android.text.Editable
import android.text.TextWatcher
import android.widget.EditText
import androidx.activity.enableEdgeToEdge
import androidx.appcompat.app.AppCompatActivity
import androidx.core.view.ViewCompat
import androidx.core.view.WindowInsetsCompat
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView

class MainActivity : AppCompatActivity() {
    private lateinit var edtSearch: EditText
    private lateinit var recyclerView: RecyclerView
    private lateinit var adapter: StudentAdapter
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContentView(R.layout.activity_main)
        ViewCompat.setOnApplyWindowInsetsListener(findViewById(R.id.main)) { v, insets ->
            val systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars())
            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom)
            insets
        }
        // Khởi tạo views
        edtSearch = findViewById(R.id.edtSearch)
        recyclerView = findViewById(R.id.recyclerView)

        // Thiết lập RecyclerView
        recyclerView.layoutManager = LinearLayoutManager(this)
        adapter = StudentAdapter()
        recyclerView.adapter = adapter

        // Tạo dữ liệu mẫu
        val sampleStudents = listOf(
            Student("Nguyễn Văn A ", "20210001"),
            Student("Trần Thị B", "20210002"),
            Student("Lê Văn C", "20210003"),
            Student("Mai Thị D", "20210004"),
            Student("Nguyễn Văn E", "20210005"),
            Student("Đỗ Văn F", "30010006"),
            Student("Vũ Văn G", "33330007"),
            Student("Mai Thị M", "20220008"),
            Student("Bùi Văn N", "20230009"),
            Student("Mai Nhật Z", "20240010")
        )

        // Cập nhật adapter với dữ liệu mẫu
        adapter.setStudents(sampleStudents)

        // Xử lý sự kiện tìm kiếm
        edtSearch.addTextChangedListener(object : TextWatcher {
            override fun beforeTextChanged(s: CharSequence?, start: Int, count: Int, after: Int) {}

            override fun onTextChanged(s: CharSequence?, start: Int, before: Int, count: Int) {
                adapter.filter(s.toString())
            }

            override fun afterTextChanged(s: Editable?) {}
        })
    }
}