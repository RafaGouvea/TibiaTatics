package com.tatics.tibiatatics.ui.fragments

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageButton
import android.widget.TextView
import androidx.fragment.app.Fragment
import com.github.chrisbanes.photoview.PhotoView
import com.tatics.tibiatatics.R
import com.tatics.tibiatatics.ui.extension.imageResources


class MapFragment : Fragment() {

    private val imageResources = imageResources()
    private var currentImageIndex = 8
    private lateinit var photoView: PhotoView
    private lateinit var upMapButton: ImageButton
    private lateinit var downMapButton: ImageButton
    private lateinit var floorMap: TextView

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        val view = inflater.inflate(R.layout.fragment_map, container, false)

        photoView = view.findViewById(R.id.imgMap)
        upMapButton = view.findViewById(R.id.button_up)
        downMapButton = view.findViewById(R.id.button_down)
        floorMap = view.findViewById(R.id.floor_map)

        buttons()

        return view
    }

    private fun buttons() {

        photoView.setImageResource(imageResources[currentImageIndex])

        upMapButton.setOnClickListener {
            currentImageIndex = (currentImageIndex + 1).coerceAtMost(imageResources.size - 1)
            photoView.setImageResource(imageResources[currentImageIndex])
            floorMapText(floorMap)
        }

        downMapButton.setOnClickListener {
            currentImageIndex = (currentImageIndex - 1).coerceAtLeast(imageResources.size - 16)
            photoView.setImageResource(imageResources[currentImageIndex])
            floorMapText(floorMap)
        }
    }

    private fun floorMapText(floorMap: TextView) {
        when (currentImageIndex) {
            0 -> floorMap.text = "-8"
            1 -> floorMap.text = "-7"
            2 -> floorMap.text = "-6"
            3 -> floorMap.text = "-5"
            4 -> floorMap.text = "-4"
            5 -> floorMap.text = "-3"
            6 -> floorMap.text = "-2"
            7 -> floorMap.text = "-1"
            8 -> floorMap.text = "0"
            9 -> floorMap.text = "+1"
            10 -> floorMap.text = "+2"
            11 -> floorMap.text = "+3"
            12 -> floorMap.text = "+4"
            13 -> floorMap.text = "+5"
            14 -> floorMap.text = "+6"
            15 -> floorMap.text = "+7"
        }
    }
}
