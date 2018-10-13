package pl.karolmichalski.githubrepos.presentation.utils

import android.widget.ImageView
import androidx.databinding.BindingAdapter
import com.bumptech.glide.Glide
import com.bumptech.glide.load.resource.drawable.DrawableTransitionOptions
import com.bumptech.glide.request.RequestOptions
import pl.karolmichalski.githubrepos.R

@BindingAdapter("imageUrl")
fun ImageView.setImageUrl(url: String?) {
	val options = RequestOptions()
			.placeholder(R.drawable.placeholder_avatar)
	val transition = DrawableTransitionOptions()
			.crossFade()
	Glide.with(this)
			.load(url)
			.apply(options)
			.transition(transition)
			.into(this)
}