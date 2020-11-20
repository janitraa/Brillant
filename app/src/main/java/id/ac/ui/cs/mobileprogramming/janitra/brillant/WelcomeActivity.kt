package id.ac.ui.cs.mobileprogramming.janitra.brillant

import android.content.Intent
import android.os.Bundle
import android.widget.ImageButton
import android.widget.ImageView
import androidx.appcompat.app.AppCompatActivity
import kotlinx.android.synthetic.main.activity_welcome.*

class WelcomeActivity : AppCompatActivity() {

    lateinit var happyBtn: ImageButton
    lateinit var sadBtn: ImageButton
    lateinit var fearBtn: ImageButton
    lateinit var angryBtn: ImageButton
    lateinit var sadImg: ImageView

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_welcome)

        happyBtn = findViewById(R.id.happy)
        sadBtn = findViewById(R.id.sad)
        fearBtn = findViewById(R.id.fear)
        angryBtn = findViewById(R.id.angry)
//        sadImg = Picasso.get()
//            .load("https://img.pngio.com/free-great-job-png-free-great-jobpng-transparent-images-15006-good-job-png-1400_1163.png")
//            .placeholder(R.drawable.ic_noun_loading)
//            .resize(64, 64)
//            .memoryPolicy(MemoryPolicy.NO_CACHE, MemoryPolicy.NO_STORE)
//            .networkPolicy(NetworkPolicy.NO_CACHE, NetworkPolicy.NO_STORE)
//            .centerCrop()
//            .into(imageView)


        happyBtn.setOnClickListener{
            val context = happyBtn.context
            val intent = Intent(context, MotivationActivity::class.java)
            intent.putExtra("message", "Yeay keep it up!")
            intent.putExtra("photo", "https://www.google.com/url?sa=i&url=https%3A%2F%2Fwww.pinterest.com%2Fpin%2F692006298982723077%2F&psig=AOvVaw1_htQESJN8RxIlGhkVT1mc&ust=1605947816402000&source=images&cd=vfe&ved=0CAIQjRxqFwoTCLDa9PrbkO0CFQAAAAAdAAAAABAD")
            context.startActivity(intent)
        }

        sadBtn.setOnClickListener{
            val context = sadBtn.context
            val intent = Intent(context, MotivationActivity::class.java)
            intent.putExtra("message", "Hey, it's okay to cry. Find your happiness around you.")
            intent.putExtra("photo", "https://imagesvc.meredithcorp.io/v3/mm/image?url=https%3A%2F%2Fstatic.onecms.io%2Fwp-content%2Fuploads%2Fsites%2F23%2F2020%2F04%2F10%2Fdepression-and-anxiety-quotes-motivation-inspirational-corrie-ten-boom.jpg")
            context.startActivity(intent)
        }

        fearBtn.setOnClickListener{
            val context = fearBtn.context
            val intent = Intent(context, MotivationActivity::class.java)
            intent.putExtra("message", "Hey, don't be scared. We always here for you.")
            intent.putExtra("photo", "https://cdn.lifehack.org/wp-content/uploads/2013/08/2c847ed9c1d7f1b61cf1bba2380558a6-375x380.jpg")
            context.startActivity(intent)
        }

        angryBtn.setOnClickListener{
            val context = angryBtn.context
            val intent = Intent(context, MotivationActivity::class.java)
            intent.putExtra("message", "Hey, keep calm. Don't also ruin other people's mood.")
            intent.putExtra("photo", "https://www.ryrob.com/wp-content/uploads/2017/08/Hustle_Quotes_Motivation_-The-trouble-for-most-people-is-they-don%E2%80%99t--866x1024.jpg")
            context.startActivity(intent)
        }
    }
}