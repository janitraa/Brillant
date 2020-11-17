package id.ac.ui.cs.mobileprogramming.janitra.brillant

import android.app.Activity
import android.content.Context
import android.content.Intent
import android.os.Bundle
import android.view.Menu
import android.view.MenuItem
import android.widget.EditText
import android.widget.ImageView
import android.widget.TextView
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import kotlinx.android.synthetic.main.fragment_profile.*

class EditProfileActivity : AppCompatActivity() {
    val GALLERY_REQUEST_CODE = 1

    private lateinit var uploadImage: TextView
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

    override fun onCreate(savedInstanceState: Bundle?){
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_edit_profile)

        uploadImage = findViewById(R.id.upload_image)
        editFirstName = findViewById(R.id.edit_first_name)
        editLastName = findViewById(R.id.edit_last_name)
        editEmail = findViewById(R.id.edit_email)
        editDob = findViewById(R.id.edit_dob)
        editGoals = findViewById(R.id.edit_goals)
        editDreamJob = findViewById(R.id.edit_dream_job)

        uploadImage.setOnClickListener {
            uploadImage()
        }

//        supportActionBar?.setTitle("CANCEL")
        setTitle("Edit Profile")


    }

    fun uploadImage() {
        var intent = Intent(Intent.ACTION_PICK)
        intent.setType("image/*")
        val mimeTypes = arrayOf("image/jpeg", "image/png")
        intent.putExtra(Intent.EXTRA_MIME_TYPES, mimeTypes)
        intent.addFlags(Intent.FLAG_GRANT_READ_URI_PERMISSION or Intent.FLAG_GRANT_WRITE_URI_PERMISSION)
        startActivityForResult(intent, GALLERY_REQUEST_CODE)
    }

    fun editProfile() {
        val firstName: String = editFirstName.text.toString()
        val lastName: String = editLastName.text.toString()
        val email: String = editEmail.text.toString()
        val dob: String = editDob.text.toString()
        val goals: String = editGoals.text.toString()
        val dreamJob: String = editDreamJob.text.toString()

        if (firstName.trim().isEmpty() || email.trim().isEmpty()) {
            Toast.makeText(this, "Please insert first name and email", Toast.LENGTH_SHORT).show()
            return
        }

        val data: Intent = Intent()
        data.putExtra(EXTRA_FIRST_NAME, firstName)
        data.putExtra(EXTRA_LAST_NAME, lastName)
        data.putExtra(EXTRA_EMAIL, email)
        data.putExtra(EXTRA_DOB, dob)
        data.putExtra(EXTRA_GOALS, goals)
        data.putExtra(EXTRA_DREAM_JOB, dreamJob)

        setResult(RESULT_OK, data)
        finish()
    }

    override fun onCreateOptionsMenu(menu: Menu?): Boolean {
        menuInflater.inflate(R.menu.edit_profile, menu)
        return true
    }

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