package com.hanthust.gmail_recycleview

import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.hanthust.gmail_recycleview.R

class MainActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        val itemList = ArrayList<ItemModel>()

        itemList.add(ItemModel(R.drawable.t, "Testing", "testing email\n" +
                "This is an email for testing purpose...", "12:34 PM"))

        itemList.add(ItemModel(R.drawable.h, "Ha", "NguyenThuHa\n" +
                "Nguyen Thu Ha - 20210299", "12:35 PM"))

        itemList.add(ItemModel(R.drawable.t, "Test Email", "Testing email functionality..." +
                "\nLet's see if this works!", "12:59 PM"))

        itemList.add(ItemModel(R.drawable.c, "Congratulation!", "Chuc mung, ban da vuot qua vong phong van..." +
                "\nHay lien he qua so dien thoai....", "2:34 PM"))

        val listView = findViewById<RecyclerView>(R.id.rview)
        listView.layoutManager = LinearLayoutManager(this)
        listView.setAdapter(MyAdapter(itemList))
    }
}