package com.example.testkotlinproject

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Button
import android.widget.EditText
import android.widget.Toast
import kotlinx.android.synthetic.main.activity_main.*

class MainActivity : AppCompatActivity() {

    var arrayList = mutableListOf<String>()
    var toast: Toast? = null

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        addToArrayListAction()
        removeFromArrayListAction()
    }

    private fun addToArrayListAction() {
        add_btn.setOnClickListener {
            val value = add_edit_text.text.toString()
            if (checkFieldIsEmpty(
                    value,
                    add_edit_text,
                    getString(R.string.not_add_empty_text)
                )
            ) return@setOnClickListener
            if (checkTextInArray(value)) return@setOnClickListener
            if (checkFieldIsOur(
                    remove_edit_text,
                    getString(R.string.text_upper)
                )
            ) return@setOnClickListener
            add_edit_text.text.clear()
            arrayList.add(value)
            displayArray(value)
        }
    }

    private fun removeFromArrayListAction() {
        remove_btn.setOnClickListener {
            val value = remove_edit_text.text.toString()
            if (checkFieldIsEmpty(
                    value,
                    remove_edit_text,
                    getString(R.string.not_remove_empty_text)
                )
            ) return@setOnClickListener
            if (checkFieldIsOur(
                    add_edit_text,
                    getString(R.string.text_under)
                )
            ) return@setOnClickListener
            remove_edit_text.text.clear()
            findAndRemoveFromArray(value)
            displayArray(value)
        }
    }

    private fun checkFieldIsEmpty(value: String, editText: EditText, message: String): Boolean {
        if (value.isEmpty()) {
            showToast(message)
            editText.text.clear()
            return true
        }
        return false
    }

    private fun checkTextInArray(value: String): Boolean {
        for (text in arrayList) {
            if (text == value) {
                add_edit_text.text.clear()
                showToast(getString(R.string.element_has_added))
                return true
            }
        }
        return false
    }

    private fun checkFieldIsOur(editText: EditText, message: String): Boolean {
        if (editText.text.toString().isNotEmpty()) {
            showToast(message)
            return true
        }
        return false
    }

    private fun findAndRemoveFromArray(value: String) {
        var indexOfArray: Int? = null
        for ((i, text) in arrayList.withIndex()) {
            if (text != value) {
                remove_edit_text.text.clear()
                showToast(getString(R.string.element_not_found))
            }
            if (text == value) {
                indexOfArray = i
            }
        }
        if (indexOfArray != null) {
            arrayList.removeAt(indexOfArray)
        }
    }

    private fun displayArray(value: String) {
        var result = ""
        for (list in arrayList) {
            result = "$result $list \n"
        }
        if (value.isNotEmpty()) showToast(result)
    }

    private fun showToast(message: String) {
        if (toast != null) toast?.cancel()
        toast = Toast.makeText(this, message, Toast.LENGTH_LONG)
        toast?.show()
    }
}