package com.example.evo4_and

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import androidx.room.Room
import com.example.evo4_and.databinding.ActivityMainBinding
import com.example.evo4_and.db.ExpenseDatabase
import com.example.evo4_and.model.Expense
import com.example.evo4_and.model.Type
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers.IO
import kotlinx.coroutines.launch
import android.view.Menu
import android.view.MenuItem


private lateinit var binding: ActivityMainBinding
lateinit var db:ExpenseDatabase


class MainActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)

        db = Room.databaseBuilder( this, ExpenseDatabase:: class.java, "Library.db" )
            .build()

        CoroutineScope(IO).launch {
            val types = db.TypeDao().getAll()
            if (types.isNullOrEmpty()) {
                db.TypeDao().insert(Type(nameType = "Alcool"))
                db.TypeDao().insert(Type(nameType = "Drogue"))
                db.TypeDao().insert(Type(nameType = "Sortie"))
                db.TypeDao().insert(Type(nameType = "MiamMiam"))
                db.TypeDao().insert(Type(nameType = "Sex"))
                db.ExpenseDao().insert(Expense(name = "Chinois", value = 45.0f, date = "05 / 8 / 2021",  type = Type(nameType = "Alcool") ))

            }
        }

        db.ExpenseDao().getAll().observeForever {
            setupRecyclerView(expenses = it)
        }
    }

    override fun onCreateOptionsMenu(menu: Menu?): Boolean {
        menuInflater.inflate(R.menu.menu, menu)
        return super.onCreateOptionsMenu(menu)
    }

    override fun onOptionsItemSelected(item: MenuItem): Boolean {
        val id: Int = item.itemId
        if (id == R.id.addExpense) {
            startActivity(Intent(this, AddExpenseActivity::class.java))
        }
        return super.onOptionsItemSelected(item)
    }

    private fun setupRecyclerView(expenses: List<Expense>) {
        val recyclerView = binding?.expenseRecyclerView
        recyclerView?.layoutManager = LinearLayoutManager(this, RecyclerView.VERTICAL, false)
        recyclerView?.adapter = ExpenseCellAdapter(expenses)
    }
}