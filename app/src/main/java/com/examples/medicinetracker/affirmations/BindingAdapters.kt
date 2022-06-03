/*
 * Copyright (C) 2021 The Android Open Source Project.
 *
 * Licensed under the Apache License, Version 2.0 (the "License");
 * you may not use this file except in compliance with the License.
 * You may obtain a copy of the License at
 *
 *     http://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 */

package com.examples.medicinetracker.affirmations

import android.view.View
import android.widget.ImageView
import android.widget.TextView
import androidx.core.net.toUri
import androidx.databinding.BindingAdapter
import androidx.recyclerview.widget.RecyclerView
import coil.load
import com.examples.medicinetracker.R
import com.examples.medicinetracker.affirmations.network.Affirmation
import com.examples.medicinetracker.affirmations.network.AffirmationImage
import com.examples.medicinetracker.affirmations.network.Fact
import com.examples.medicinetracker.affirmations.ui.main.*
import com.examples.medicinetracker.affirmations.ui.main.AffirmationsAdapter
import com.examples.medicinetracker.affirmations.ui.main.facts.FactsAdapter
import com.examples.medicinetracker.affirmations.ui.main.FactsStatus

@BindingAdapter("listData", "imageData")
fun bindRecyclerView(recyclerView: RecyclerView, data: List<Affirmation>?, images: List<AffirmationImage>?) {

    if(data != null && images != null){
        val adapter = AffirmationsAdapter(data, images)
        recyclerView.adapter = adapter
    }
}

@BindingAdapter("factsData")
fun bindRecyclerView2(recyclerView: RecyclerView, data: List<Fact>?) {

    if(data != null){
        val adapter = FactsAdapter(data)
        recyclerView.adapter = adapter
    }
}

@BindingAdapter("quote")
fun bindQuote(textView: TextView, quote: String?) {
    quote?.let {
        textView.text = quote
    }
}

@BindingAdapter("imageUrl")
fun bindImage(imgView: ImageView, imgUrl: String?) {
    imgUrl?.let {
        val imgUri = imgUrl.toUri().buildUpon().scheme("https").build()
        imgView.load(imgUri) {
            placeholder(R.drawable.loading_animation)
            error(R.drawable.ic_broken_image)
        }
    }
}

@BindingAdapter("fact")
fun bindFact(textView: TextView, fact: String?) {
    fact?.let {
        textView.text = fact
    }
}

@BindingAdapter("factsApiStatus")
fun bindFactStatus(statusImageView: ImageView, status: FactsStatus?) {
    when (status) {
        FactsStatus.LOADING -> {
            statusImageView.visibility = View.VISIBLE
            statusImageView.setImageResource(R.drawable.loading_animation)
        }
        FactsStatus.ERROR -> {
            statusImageView.visibility = View.VISIBLE
            statusImageView.setImageResource(R.drawable.ic_connection_error)
        }
        FactsStatus.DONE -> {
            statusImageView.visibility = View.GONE
        }
    }
}

@BindingAdapter("affirmationApiStatus")
fun bindStatus(statusImageView: ImageView, status: Status?) {
    when (status) {
        Status.LOADING -> {
            statusImageView.visibility = View.VISIBLE
            statusImageView.setImageResource(R.drawable.loading_animation)
        }
        Status.ERROR -> {
            statusImageView.visibility = View.VISIBLE
            statusImageView.setImageResource(R.drawable.ic_connection_error)
        }
        Status.DONE -> {
            statusImageView.visibility = View.GONE
        }
    }
}
