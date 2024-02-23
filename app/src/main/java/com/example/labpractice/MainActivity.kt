package com.example.labpractice

// this file contains ListView ,AlertDialog and Background Changing
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.text.Layout
import android.view.View
import android.widget.AdapterView
import android.widget.ArrayAdapter
import android.widget.Button
import android.widget.EditText
import android.widget.ListView
import android.widget.RelativeLayout
import android.widget.Spinner
import androidx.appcompat.app.AlertDialog
import java.text.FieldPosition


class MainActivity : AppCompatActivity() {
    private lateinit var mainLayout: RelativeLayout

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)


        val submit = findViewById<Button>(R.id.button)
        val update = findViewById<Button>(R.id.update)
        val textField = findViewById<EditText>(R.id.editTextText)

        val colorSpinner = findViewById<Spinner>(R.id.spinner)
        val apply = findViewById<Button>(R.id.applyButton)
         mainLayout = findViewById<RelativeLayout>(R.id.mainLayout)

        var background = arrayOf("Black", "White", "Green", "Orange", "Red")
        var colorAdapter = ArrayAdapter(this, android.R.layout.simple_spinner_item, background)
        colorAdapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item)

        colorSpinner.adapter = colorAdapter

        colorSpinner.onItemSelectedListener = object : AdapterView.OnItemSelectedListener {
            override fun onItemSelected(
                parentView: AdapterView<*>,
                selectedItemView: View?,
                position: Int,
                id: Long
            ) {
                // Handle item selection, for example, change the background color
                //applyBackgroundColor(position)
            }

            override fun onNothingSelected(parent: AdapterView<*>?) {
                TODO("Not yet implemented")
            }
        }
        apply.setOnClickListener {
            // Apply background color based on the currently selected item
            val selectedPosition = colorSpinner.selectedItemPosition
            applyBackgroundColor(selectedPosition)
        }

        val listView = findViewById<ListView>(R.id.myList)

        var items = ArrayList<String>()
        var adapter = ArrayAdapter(this, android.R.layout.simple_list_item_1, items)

        submit.setOnClickListener()
        {
            var text = textField.text.toString()
            items.add(text)
            textField.text.clear()
        }
        update.setOnClickListener()
        {
            listView.adapter = adapter
        }

        listView.setOnItemClickListener { _, _, position, _ ->
            val selectedItem = items[position]
            showDialog(selectedItem)
        }
    }

    private fun showAlert(itemText: String) {
        val alertDialogBuilder = AlertDialog.Builder(this)
        alertDialogBuilder.setTitle("Item Clicked")
        alertDialogBuilder.setMessage("You clicked on: $itemText")
        alertDialogBuilder.setNegativeButton("not ok") { dialog, _ ->
            dialog.dismiss()
        }
        alertDialogBuilder.setPositiveButton("OK") { dialog, _ ->
            // Handle the 'OK' button click if needed
            dialog.dismiss()
        }

        val alertDialog = alertDialogBuilder.create()
        alertDialog.show()
    }

    private fun showDialog(item: String) {
        var alertBox = AlertDialog.Builder(this)
        alertBox.setTitle("Hello")
        alertBox.setMessage("Pressed $item")
        alertBox.setPositiveButton("Done") { dialog, _ ->
            dialog.dismiss()
        }
        val alert = alertBox.create()
        alert.show()
    }

    private fun applyBackgroundColor(position: Int)
    {
        when (position) {
            0 -> mainLayout.setBackgroundColor(getColor(R.color.black))
            1 -> mainLayout.setBackgroundColor(getColor(R.color.white))
            2 -> mainLayout.setBackgroundColor(getColor(R.color.Green))
            3 -> mainLayout.setBackgroundColor(getColor(R.color.Orange))
            4 -> mainLayout.setBackgroundColor(getColor(R.color.Red))
        }
    }
}