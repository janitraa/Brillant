package id.ac.ui.cs.mobileprogramming.janitra.brillant.ui

import android.opengl.GLES10
import android.opengl.GLSurfaceView
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.fragment.app.Fragment
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProviders
import id.ac.ui.cs.mobileprogramming.janitra.brillant.util.InjectorUtils
import id.ac.ui.cs.mobileprogramming.janitra.brillant.vm.TaskViewModel
//import id.ac.ui.cs.mobileprogramming.janitra.brillant.databinding.FragmentHomepageBinding
import id.ac.ui.cs.mobileprogramming.janitra.brillant.R
import kotlinx.android.synthetic.main.fragment_homepage.view.*
import javax.microedition.khronos.opengles.GL10

class HomepageFragment: Fragment() {
    private lateinit var mView: View
    private lateinit var taskViewModel: TaskViewModel


    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        mView = inflater.inflate(R.layout.fragment_homepage, container, false)
//        val binding = FragmentHomepageBinding.inflate(inflater, container, false)
//        this.mView = binding.root

        mView.glSurface.setRenderer(GLRenderer())

        setTaskViewModel()

        return mView
    }

    fun setTaskViewModel() {
        val taskFactory = InjectorUtils.provideTaskViewModelFactory(mView.context)

        taskViewModel = ViewModelProviders.of(this, taskFactory)
            .get(TaskViewModel::class.java)
        taskViewModel.getUnique().observe(this, Observer { listUnique ->
            showCurrentTaskNumber(listUnique.size)
        })
    }

    fun showCurrentTaskNumber(size: Int) {
        val taskNumber: TextView = mView.findViewById(R.id.task_number)
        taskNumber.setText("$size tasks")
    }

    class GLRenderer : GLSurfaceView.Renderer {
        var color = 0f
        var colorVelocity = 1f/60f

        override fun onDrawFrame(gl: GL10){
            if (color > 1 || color < 0){
                colorVelocity = -colorVelocity
            }
            color += colorVelocity

            gl.glClearColor(color * 0.5f, color, color, 1f)
            gl.glClear(GLES10.GL_COLOR_BUFFER_BIT)
        }

        override fun onSurfaceCreated(p0: GL10?, p1: javax.microedition.khronos.egl.EGLConfig?) {}
        override fun onSurfaceChanged(gl: GL10, width: Int, height: Int){}
    }
}