package com.example.evo4_and


import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.example.evo4_and.databinding.ExpenseCellBinding
import com.example.evo4_and.model.Expense


class ExpenseCellAdapter(private val expenses: List<Expense>) : RecyclerView.Adapter<ExpenseCellAdapter.BookRowHolder>() {


    private lateinit var binding: ExpenseCellBinding

    class BookRowHolder(var binding: ExpenseCellBinding) : RecyclerView.ViewHolder(binding.root) {

        fun bind(expense: Expense) {

            binding.expenseId.text = expense.name
            binding.dateLb.text = expense.date.toString()
            binding.valueLb.text = expense.value.toString()
            binding.typelb.text = ""


        }
    }


    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): BookRowHolder {
        binding = ExpenseCellBinding.inflate(LayoutInflater.from(parent.context), parent, false)
        return BookRowHolder(binding)
    }

    override fun onBindViewHolder(holder: BookRowHolder, position: Int) {
        holder.bind(expenses[position])
    }

    override fun getItemCount(): Int {
        return expenses.size
    }
}


