package id.ac.ui.cs.mobileprogramming.janitra.brillant.ui

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import id.ac.ui.cs.mobileprogramming.janitra.brillant.R
import id.ac.ui.cs.mobileprogramming.janitra.brillant.data.Task

class TaskAdapter : RecyclerView.Adapter<TaskAdapter.ListViewHolder>() {

    private lateinit var onItemClickCallback: OnItemClickCallback

    private var listTask: List<Task> = listOf()

    override fun onCreateViewHolder(view: ViewGroup, viewType: Int): ListViewHolder {
        val itemView: View = LayoutInflater.from(view.context).inflate(R.layout.item_deadline, view, false)
        return ListViewHolder(itemView)
    }

    interface OnItemClickCallback {
        fun onItemClicked(data: Task, position: Int)
    }

    fun setOnItemClickCallback(onItemClickCallback: OnItemClickCallback) {
        this.onItemClickCallback = onItemClickCallback
    }

    override fun onBindViewHolder(holder: ListViewHolder, position: Int){

        var currentTask = listTask.get(position)
        holder.taskName.setText(currentTask.taskName)
        holder.tags.setText(currentTask.tags)
        holder.status.setText(currentTask.status)
        holder.dueDate.setText(currentTask.dueDate)

        holder.itemView.setOnClickListener {
            onItemClickCallback.onItemClicked(listTask[holder.adapterPosition], position)
        }
    }

    override fun getItemCount(): Int {
        return listTask.size
    }

    fun setTask(task: List<Task>) {
        listTask = task
        notifyDataSetChanged()
    }

    inner class ListViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {
        var taskName: TextView = itemView.findViewById(R.id.task_name)
        var tags: TextView = itemView.findViewById(R.id.tags)
        var status: TextView = itemView.findViewById(R.id.status)
        var dueDate: TextView = itemView.findViewById(R.id.due_date)

    }
}