package com.example.todolist.ui.alertDialogs

import android.R
import android.app.AlertDialog
import android.app.Dialog
import android.content.Context
import android.content.DialogInterface
import android.os.Bundle
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.EditText
import android.widget.Toast
import androidx.fragment.app.DialogFragment
import androidx.fragment.app.viewModels
import androidx.lifecycle.lifecycleScope
import com.example.todolist.database.App
import com.example.todolist.database.ProjectsRepository
import com.example.todolist.entities.Project
import com.example.todolist.ui.activities.MainActivity
import com.example.todolist.ui.fragments.ProjectsFragment
import kotlinx.coroutines.GlobalScope
import kotlinx.coroutines.coroutineScope
import kotlinx.coroutines.launch
import java.util.Date

const val TAG = "onCreateDialog"

class NewProjectDialogFragment : DialogFragment() {

    private var listener:DialogListener?=null

    override fun onAttach(context: Context) {
        super.onAttach(context)
        try {
            listener = context as DialogListener
        } catch (e: ClassCastException) {
            throw ClassCastException("$context must implement DialogListener")
        }
    }
    override fun onCreateDialog(savedInstanceState: Bundle?): Dialog {

        return activity?.let {
            val builder = AlertDialog.Builder(it)
            builder.setTitle("Create new project!")

            val customLayout: View =
                layoutInflater.inflate(com.example.todolist.R.layout.alert_dialog_new_project, null)
            builder.setView(customLayout)
            val editText =
                customLayout.findViewById<EditText>(com.example.todolist.R.id.editTextProjectName)

            builder.setPositiveButton("Save new project") { dialog, id ->
                dialog.cancel()

                if (editText.text.isEmpty()) {
                    Toast.makeText(context, "Fill in neccesary input field", Toast.LENGTH_SHORT)
                        .show()
                } else {
                    val data = editText.text.toString()
                    listener?.onFinishDialog(data)

                }


            }
            builder.create()
        } ?: throw IllegalStateException("Activity cannot be null")

    }
}