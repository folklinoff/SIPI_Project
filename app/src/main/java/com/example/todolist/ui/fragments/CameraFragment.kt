package com.example.todolist.ui.fragments

import android.app.Activity
import android.content.ActivityNotFoundException
import android.content.ContentValues
import android.content.Intent
import android.graphics.Bitmap
import android.net.Uri
import android.os.Bundle
import android.provider.MediaStore
import android.util.Log
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Button
import android.widget.Toast
import com.example.todolist.AUTHORITY
import com.example.todolist.R
import com.example.todolist.URI_MENU
import com.example.todolist.database.App
import com.example.todolist.databinding.FragmentCameraBinding
import com.example.todolist.databinding.FragmentProjectsBinding
import com.example.todolist.entities.MemoryEntity
import com.example.todolist.provider.MemoriesContentProvider
import java.util.UUID


class CameraFragment : Fragment() {

    private var _binding: FragmentCameraBinding? = null
    private val binding get() = _binding!!
    private val REQUEST_TAKE_PHOTO = 1


    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        _binding = FragmentCameraBinding.inflate(inflater, container, false)
        return binding.root
    }

   override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)


        binding.button.setOnClickListener {
            val takePictureIntent = Intent(MediaStore.ACTION_IMAGE_CAPTURE)
            try {
                startActivityForResult(takePictureIntent, REQUEST_TAKE_PHOTO)
            } catch (e: ActivityNotFoundException) {
                e.printStackTrace()
            }
        }
    }

    override fun onActivityResult(requestCode: Int, resultCode: Int, data: Intent?) {
        super.onActivityResult(requestCode, resultCode, data)

        if (requestCode == REQUEST_TAKE_PHOTO && resultCode == Activity.RESULT_OK) {
            // Фотка сделана, извлекаем миниатюру картинки
            val thumbnailBitmap = data?.extras?.get("data") as Bitmap
            val cv=ContentValues()
            cv.put(UUID.randomUUID().toString(), "name")
            binding.imageView.setImageBitmap(thumbnailBitmap)
            Log.d("important", "onCreateInsertFragment: " + (requireActivity().application as App).memoryContentProvider)
            //(requireActivity().application as App).memoryContentProvider.insert(URI_MENU, cv )

            requireActivity().contentResolver.insert(URI_MENU, cv)


        }

        // Другой вариант с применением when
        when (requestCode) {
            REQUEST_TAKE_PHOTO -> {
                if (resultCode == Activity.RESULT_OK && data !== null) {
                    binding.imageView.setImageBitmap(data.extras?.get("data") as Bitmap)
                }
            }

            else -> {
                Toast.makeText(requireActivity(), "Wrong request code", Toast.LENGTH_SHORT).show()
            }
        }
    }

}