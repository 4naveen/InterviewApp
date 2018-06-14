package com.naveen.interviewapp

import android.content.Context
import android.content.Intent
import android.support.v7.widget.CardView
import android.support.v7.widget.RecyclerView
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView

/**
 * Created by User on 6/3/2017.
 */

class QuesListAdapter(internal var questionList: ArrayList<Question>, private val context: Context) : RecyclerView.Adapter<QuesListAdapter.ViewHolder>() {
    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): QuesListAdapter.ViewHolder {
        val view = LayoutInflater.from(parent.context).inflate(R.layout.indi_view_ques, parent, false)
        return ViewHolder(view)
    }

    override fun onBindViewHolder(holder: QuesListAdapter.ViewHolder, position: Int) {
        val question = questionList[position]

        holder.questions.text="Q"+(position+1)+". "+question.question

    }

    override fun getItemCount(): Int {
        return questionList.size
    }

    inner class ViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) , View.OnClickListener {
        override fun onClick(v: View?) {
            val question = questionList[adapterPosition]
            val i = Intent(context, AnswerActivity::class.java)
            i.putExtra("answer",question.answer)
            i.putExtra("ques",question.question)
            i.putExtra("pos",adapterPosition)
            i.putParcelableArrayListExtra("qlist",questionList)
            context.startActivity(i)
        }
        internal var questions: TextView
        init {
            itemView.setOnClickListener(this)
            questions = itemView.findViewById<View>(R.id.question) as TextView

        }
    }
}
