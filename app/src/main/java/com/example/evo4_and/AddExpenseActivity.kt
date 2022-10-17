package com.example.evo4_and

import android.app.Activity
import android.app.DatePickerDialog
import android.content.Context
import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.Menu
import android.view.MenuItem
import android.view.View
import android.view.inputmethod.InputMethodManager
import androidx.core.content.ContextCompat.startActivity
import com.example.evo4_and.dao.TypeDao
import com.example.evo4_and.databinding.ActivityAddExpenseBinding
import com.example.evo4_and.db.ExpenseDatabase
import com.example.evo4_and.model.Expense
import com.example.evo4_and.model.Type
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers.IO
import kotlinx.coroutines.launch
import java.util.*

//private lateinit var types: List<Type>
private lateinit var binding: ActivityAddExpenseBinding
var myDate = ""
var type = Type(nameType = "")
var myTypeName = ""
class AddExpenseActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityAddExpenseBinding.inflate(layoutInflater)
        setContentView(binding.root)





        binding.txtFieldType.setOnClickListener {
            hideKeyboard(it)

            binding.typelb.text = "loisirs"
            myTypeName = "loisirs"
           type = Type(nameType = myTypeName)

        }

        binding.txtFieldDate.setOnClickListener {
            val c = Calendar.getInstance()
            val year = c.get(Calendar.YEAR)
            val month = c.get(Calendar.MONTH)
            val day = c.get(Calendar.DAY_OF_MONTH)


            val dpd = DatePickerDialog(this, DatePickerDialog.OnDateSetListener { view, year, monthOfYear, dayOfMonth ->

                    myDate = ("  $dayOfMonth / $monthOfYear  / $year ")
                    binding.datelb.setText ("  $dayOfMonth / $monthOfYear  / $year ")

            }, year, month, day)

            dpd.show()
        }
        }





    override fun onCreateOptionsMenu(menu: Menu?): Boolean {
        menuInflater.inflate(R.menu.menu_add_expense, menu)
        return super.onCreateOptionsMenu(menu)
    }

    override fun onOptionsItemSelected(item: MenuItem): Boolean {
        val id: Int = item.itemId
        CoroutineScope(IO).launch {
            if (id == R.id.saveExpense) {
                val name = binding.txtFieldname.text.toString()
                val value = binding.txtFieldValue.text.toString()

                if (name.isNotBlank() && value.isNotBlank()) {
                    val expenseDao = db.ExpenseDao()
                    expenseDao.insert(
                        Expense(name = name, value = value.toFloat(), date = myDate, type = type)
                    )

                }
            }
        }
        startActivity(Intent(this, MainActivity::class.java))
        return super.onOptionsItemSelected(item)
    }

    fun Context.hideKeyboard(view: View) {
        val inputMethodManager = getSystemService(Activity.INPUT_METHOD_SERVICE) as InputMethodManager
        inputMethodManager.hideSoftInputFromWindow(view.windowToken, 0)
    }


}
