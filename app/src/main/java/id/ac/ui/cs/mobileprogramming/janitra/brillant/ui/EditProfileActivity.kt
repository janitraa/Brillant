package id.ac.ui.cs.mobileprogramming.janitra.brillant.ui

import android.app.DatePickerDialog
import android.content.Intent
import android.graphics.Bitmap
import android.graphics.ImageDecoder
import android.net.Uri
import android.os.Bundle
import android.text.SpannableStringBuilder
import android.widget.*
import androidx.appcompat.app.AppCompatActivity
import androidx.lifecycle.ViewModelProviders
import androidx.lifecycle.Observer
import id.ac.ui.cs.mobileprogramming.janitra.brillant.R
import id.ac.ui.cs.mobileprogramming.janitra.brillant.WelcomeActivity
import id.ac.ui.cs.mobileprogramming.janitra.brillant.sharedpreferences.SharedPreferenceManager
import id.ac.ui.cs.mobileprogramming.janitra.brillant.vm.EditProfileViewModel
import java.text.SimpleDateFormat
import java.util.*

class EditProfileActivity : AppCompatActivity(), DatePickerDialog.OnDateSetListener {
    val GALLERY_REQUEST_CODE = 1
    var mDay = 0
    var mMonth: Int = 0
    var mYear: Int = 0
    private var txtDate: String = ""

    private val cal: Calendar = Calendar.getInstance()

    private lateinit var spManager: SharedPreferenceManager
    private lateinit var uploadImage: TextView
    private lateinit var viewImage: ImageView
    private lateinit var uriImage: Uri
    private lateinit var bitmap: Bitmap

    private lateinit var viewModel: EditProfileViewModel

    private lateinit var nextBtn: Button
    private lateinit var editName: EditText
    private lateinit var editEmail: EditText
    private lateinit var btnDob: Button
    private lateinit var editGoals: EditText
    private lateinit var editDreamJob: EditText

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_edit_profile)

        spManager = SharedPreferenceManager(this)

        if (!spManager.isFirstTime()) {
            val intent = Intent(this, WelcomeActivity::class.java)
            startActivity(intent)
            finish()
        }

        viewModel = ViewModelProviders.of(this).get(EditProfileViewModel::class.java)

        uploadImage = findViewById(R.id.upload_image)
        editName = findViewById(R.id.edit_name)
        editEmail = findViewById(R.id.edit_email)
        btnDob = findViewById(R.id.btn_dob)
        editGoals = findViewById(R.id.edit_goals)
        editDreamJob = findViewById(R.id.edit_dream_job)
        nextBtn = findViewById(R.id.next_btn)

        btnDob.setOnClickListener{
            val cal = Calendar.getInstance()
            val year = cal.get(Calendar.YEAR)
            val month = cal.get(Calendar.MONTH)
            val day = cal.get(Calendar.DAY_OF_MONTH)
            val dpd = DatePickerDialog(this, this, year, month, day)
            dpd.show()
        }

        uploadImage.setOnClickListener {
            uploadImage()
        }

        nextBtn.setOnClickListener {
            editProfile()
            viewModel.loaded.observe(this, Observer {
                if (it == true) {
                    val intent = Intent(this, WelcomeActivity::class.java)
                    startActivity(intent)
                    finish()
                }
            })
        }
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
        val name: String = editName.text.toString()
        val email: String = editEmail.text.toString()
        val dob: String = txtDate
        val goals: String = editGoals.text.toString()
        val dreamJob: String = editDreamJob.text.toString()

        spManager = SharedPreferenceManager(this)
        spManager.setFirstTime(false)
        viewModel.insertProfile(name, email, dob, goals, dreamJob, bitmap)
    }

    override fun onDateSet(view: DatePicker?, year: Int, month: Int, dayOfMonth: Int){
        cal.set(Calendar.DAY_OF_MONTH, dayOfMonth)
        cal.set(Calendar.MONTH, month)
        cal.set(Calendar.YEAR, year)
        val cal = Calendar.getInstance()

        btnDob.text = SpannableStringBuilder(txtDate)

        txtDate = "$mDay/$mMonth/$mYear"

        btnDob.text = txtDate

        updateLabel()

    }

    private fun updateLabel() {
        val format = "dd/MM/yy" //In which you need put here
        val sdf = SimpleDateFormat(format, Locale.getDefault())
        txtDate = sdf.format(cal.time)
        btnDob.setText(txtDate)
    }

    override fun onActivityResult(requestCode: Int, resultCode: Int, data: Intent?) {
        super.onActivityResult(requestCode, resultCode, data)
        if (requestCode == 1 && resultCode == RESULT_OK) {
            try {
                uriImage = data?.data!!
                val source: ImageDecoder.Source = ImageDecoder.createSource(this.contentResolver!!, uriImage)
                bitmap = ImageDecoder.decodeBitmap(source)
                viewImage.setImageBitmap(bitmap)
                viewImage.contentDescription = "Profile picture"
            }
            catch (e: Exception) {

            }
        }
    }
}