package id.ac.ui.cs.mobileprogramming.janitra.brillant

import android.app.Activity
import android.content.Context
import android.content.Intent
import android.graphics.Bitmap
import android.graphics.ImageDecoder
import android.net.Uri
import android.os.Bundle
import android.view.*
import android.widget.*
import androidx.lifecycle.ViewModelProvider
import androidx.appcompat.app.AppCompatActivity
import androidx.databinding.DataBindingUtil.setContentView
import androidx.fragment.app.Fragment
import androidx.lifecycle.ViewModelProviders
import androidx.lifecycle.Observer
import id.ac.ui.cs.mobileprogramming.janitra.brillant.data.Profile
import id.ac.ui.cs.mobileprogramming.janitra.brillant.sharedpreferences.SharedPreferenceManager
import id.ac.ui.cs.mobileprogramming.janitra.brillant.vm.EditProfileViewModel
import id.ac.ui.cs.mobileprogramming.janitra.brillant.vm.ProfileViewModel
import kotlinx.android.synthetic.main.fragment_profile.*
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.GlobalScope
import kotlinx.coroutines.launch
import kotlinx.coroutines.withContext
import java.io.ByteArrayOutputStream
import java.util.*

class EditProfileActivity : AppCompatActivity() {
    val GALLERY_REQUEST_CODE = 1

    private lateinit var spManager: SharedPreferenceManager
    private lateinit var profile: Profile
    private lateinit var uploadImage: TextView
    private lateinit var viewImage: ImageView
    private lateinit var uriImage: Uri
    private lateinit var bitmap: Bitmap

    private lateinit var viewModel: EditProfileViewModel

    private lateinit var nextBtn: Button
    private lateinit var editFirstName: EditText
    private lateinit var editLastName: EditText
    private lateinit var editEmail: EditText
    private lateinit var editDob: EditText
    private lateinit var editGoals: EditText
    private lateinit var editDreamJob: EditText

    companion object {
        val EXTRA_FIRST_NAME = "id.ac.ui.cs.mobileprogrramming.janitra.brillant.EXTRA_FIRST_NAME"
        val EXTRA_LAST_NAME = "id.ac.ui.cs.mobileprogrramming.janitra.brillant.EXTRA_LAST_NAME"
        val EXTRA_EMAIL = "id.ac.ui.cs.mobileprogrramming.janitra.brillant.EXTRA_EMAIL"
        val EXTRA_DOB = "id.ac.ui.cs.mobileprogrramming.janitra.brillant.EXTRA_DOB"
        val EXTRA_GOALS = "id.ac.ui.cs.mobileprogrramming.janitra.brillant.EXTRA_GOALS"
        val EXTRA_DREAM_JOB = "id.ac.ui.cs.mobileprogrramming.janitra.brillant.EXTRA_DREAM_JOB"
    }

    override fun onCreate(savedInstanceState: Bundle?) {
//        inflater: LayoutInflater, container: ViewGroup?,
//        savedInstanceState: Bundle?
//    ): View? {
//        val view =  inflater.inflate(R.layout.activity_edit_profile, container, false)

        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_edit_profile)

        viewModel = ViewModelProviders.of(this).get(EditProfileViewModel::class.java)

//        val uploadImage: TextView = view.findViewById(R.id.upload_image)
        uploadImage = findViewById(R.id.upload_image)
        editFirstName = findViewById(R.id.edit_first_name)
        editLastName = findViewById(R.id.edit_last_name)
        editEmail = findViewById(R.id.edit_email)
        editDob = findViewById(R.id.edit_dob)
        editGoals = findViewById(R.id.edit_goals)
        editDreamJob = findViewById(R.id.edit_dream_job)
        nextBtn = findViewById(R.id.next_btn)


        uploadImage.setOnClickListener {
            uploadImage()
        }

        nextBtn.setOnClickListener {
//            val context = nextBtn.context
//            val intent = Intent(context, WelcomeActivity::class.java)
//            context.startActivity(intent)

            editProfile()
            viewModel.loaded.observe(this, Observer {
                if (it == true) {
                    Toast.makeText(this, viewModel.prof.toString(), Toast.LENGTH_LONG).show()
                    val intent = Intent(this, WelcomeActivity::class.java)
                    startActivity(intent)
                    finish()
                }
            })
        }

//        supportActionBar?.setTitle("CANCEL")
//        setTitle("Edit Profile")
//        return view
    }

    fun uploadImage() {
        var intent = Intent(Intent.ACTION_PICK)
        intent.setType("image/*")
        val mimeTypes = arrayOf("image/jpeg", "image/png")
        intent.putExtra(Intent.EXTRA_MIME_TYPES, mimeTypes)
        intent.addFlags(Intent.FLAG_GRANT_READ_URI_PERMISSION or Intent.FLAG_GRANT_WRITE_URI_PERMISSION)
        startActivityForResult(intent, GALLERY_REQUEST_CODE)
    }

//    @kotlinx.coroutines.ObsoleteCoroutinesApi
    fun editProfile() {
        val firstName: String = editFirstName.text.toString()
        val lastName: String = editLastName.text.toString()
        val email: String = editEmail.text.toString()
        val dob: String = editDob.text.toString()
        val goals: String = editGoals.text.toString()
        val dreamJob: String = editDreamJob.text.toString()

//        if (firstName.trim().isEmpty() || bitmap.isEmpty()) {
//            Toast.makeText(this, "Please insert first name and email", Toast.LENGTH_SHORT).show()
//            return
//        }
//
//        val data: Intent = Intent()
//        data.putExtra(EXTRA_FIRST_NAME, firstName)
//        data.putExtra(EXTRA_LAST_NAME, lastName)
//        data.putExtra(EXTRA_EMAIL, email)
//        data.putExtra(EXTRA_DOB, dob)
//        data.putExtra(EXTRA_GOALS, goals)
//        data.putExtra(EXTRA_DREAM_JOB, dreamJob)
//
//        setResult(RESULT_OK, data)
//        finish()
        spManager = SharedPreferenceManager(this)
        spManager.setFirstTime(false)
        viewModel.insertProfile(firstName, lastName, email, dob, goals, dreamJob, bitmap)
//        setResult(Activity.RESULT_OK)
    }

    override fun onActivityResult(requestCode: Int, resultCode: Int, data: Intent?) {
        super.onActivityResult(requestCode, resultCode, data)
        if (requestCode == 1 && resultCode == RESULT_OK) {
            try {
                uriImage = data?.data!!
                val source: ImageDecoder.Source = ImageDecoder.createSource(this.contentResolver!!, uriImage)
                bitmap = ImageDecoder.decodeBitmap(source)
                viewImage.setImageBitmap(bitmap)
                viewImage.contentDescription = "User's profile picture"
            }
            catch (e: Exception) {

            }
        }
    }

    override fun onCreateOptionsMenu(menu: Menu?): Boolean {
        menuInflater.inflate(R.menu.edit_profile, menu)
        return true
    }

//    @kotlinx.coroutines.ObsoleteCoroutinesApi
    override fun onOptionsItemSelected(item: MenuItem): Boolean {
        when (item.itemId) {
            R.id.edit_profile -> {
                editProfile()
                return true
            }

            else -> {
                return super.onOptionsItemSelected(item)
            }
        }
    }
}