package com.naveen.interviewapp

import android.content.Intent
import android.graphics.Paint
import android.net.Uri
import android.support.v7.app.AppCompatActivity
import android.os.Bundle
import android.view.Menu
import android.view.MenuItem
import android.view.View
import com.google.android.gms.ads.AdListener
import com.google.android.gms.ads.AdRequest
import com.webianks.library.easyportfolio.EasyPortfolio
import com.webianks.library.easyportfolio.Project
import kotlinx.android.synthetic.main.activity_answer.*
import kotlinx.android.synthetic.main.activity_ques_list.*
import android.graphics.Paint.UNDERLINE_TEXT_FLAG




class AnswerActivity : AppCompatActivity() {
    var ques_id :Int=0
    lateinit var questionList: ArrayList<Question>
    lateinit var actionBar: android.support.v7.app.ActionBar

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_answer)
        actionBar = this!!.supportActionBar!!
        if (actionBar != null) {
            actionBar.setDisplayHomeAsUpEnabled(true)
        }
        val adRequest = AdRequest.Builder().build()
        adView1.loadAd(adRequest)
        val ans=intent.getStringExtra("answer")
        val ques=intent.getStringExtra("ques")
        ques_id=intent.getIntExtra("pos",0)
        questionList=intent.getParcelableArrayListExtra("qlist")
        answer.text=ans
        question.text=ques
        heading1.setPaintFlags(heading1.getPaintFlags() or Paint.UNDERLINE_TEXT_FLAG)
        heading2.setPaintFlags(heading2.getPaintFlags() or Paint.UNDERLINE_TEXT_FLAG)

        right.setOnClickListener(object : View.OnClickListener {
            override fun onClick(v: View) {
                if (ques_id < questionList.size - 1) {
                    val result = questionList.get(ques_id + 1)
                    ques_id++
                    if (ques_id==questionList.size-1){
                        right.visibility=View.INVISIBLE
                    }
                    else{

                        left.visibility=View.VISIBLE

                    }
                    question.setText(result.question)
                    answer.setText(result.answer)
                }
            }
        })

        left.setOnClickListener(object : View.OnClickListener {
            override fun onClick(v: View) {
                if (ques_id > 0) {
                    val result = questionList.get(ques_id - 1)
                    question.setText(result.question)
                    answer.setText(result.answer)
                    ques_id--
                    if (ques_id==0){
                        left.visibility=View.INVISIBLE
                    }
                    else{
                        right.visibility=View.VISIBLE

                    }
                }

            }
        })

        adView1.adListener = object: AdListener() {
            override fun onAdLoaded() {
                // Code to be executed when an ad finishes loading.

            }

            override fun onAdFailedToLoad(errorCode : Int) {
                // Code to be executed when an ad request fails.
            }

            override fun onAdOpened() {
                // Code to be executed when an ad opens an overlay that
                // covers the screen.

            }

            override fun onAdLeftApplication() {
                // Code to be executed when the user has left the app.
                val url = "https://courses.learncodeonline.in"
                val i = Intent(Intent.ACTION_VIEW)
                i.data = Uri.parse(url)
                startActivity(i)
            }

            override fun onAdClosed() {
                // Code to be executed when when the user is about to return
                // to the app after tapping on an ad.
            }
        }
    }

    override fun onCreateOptionsMenu(menu: Menu): Boolean {
        menuInflater.inflate(R.menu.main, menu)
        return super.onCreateOptionsMenu(menu)

    }

    override fun onOptionsItemSelected(item: MenuItem): Boolean {
        when (item.itemId) {
            R.id.about_us -> {

            val projectList = java.util.ArrayList<Project>()

            val project1 = Project()
            project1.setProjectName("LCRM(Product)")
            project1.setProjectDesc("Lcrm is complete functional crm and Sales System ..Lcrm concentrates on the needs of the existing customers and attract new customers with its impressive feature .this products allow you to manage leads,opportunities ,sales team target ,invoices and more to increase one's company productivity.")
            project1.projectLink = "https://play.google.com/store/apps/details?id=com.project.lorvent.lcrm"

            val project2 = Project()
            project2.setProjectName("Way2Freshers(Product)")
            project2.setProjectDesc("It helps you to find jobs with your matching skills and location and also helps to make preparation for varoius written and intervies exams and campus placements and  gives info about varoius Banks loans and schlorships provided by many government and private oraganisation")
            project2.projectLink = "https://play.google.com/store/apps/details?id=com.project.lorvent.way2freshers"

            val project3 = Project()
            project3.setProjectName("BridgeCall(Product)")
            project3.setProjectDesc("This app basically used for calling purpose of international communication. To communicate with international contacts we must have bridge numbers of respected countries, with these bridge and contact numbers we can communicate with international numbers. So if you have the bridge numbers of different countries with you, you can add them in the bridge numbers list in the app and after you can select the required calling contact number")
            project3.projectLink = "https://play.google.com/store/apps/details?id=com.project.lorvent.bridgecall"

            projectList.add(project1)
            projectList.add(project2)
            projectList.add(project3)


            EasyPortfolio.Builder(this)
                    .withGithubUrl("https://github.com/4naveen")
                    .withPlayStoreUrl("https://play.google.com/store/apps/details?id=com.project.lorvent.lcrm")
                    .withLinkedInUrl("https://www.linkedin.com/in/naveen-kumar-27581692/")
                    .withProjectList(projectList)
                    .build()
                    .start()
        }
            R.id.share-> {
                var  intentShare=Intent(Intent.ACTION_SEND);
                intentShare.setType("text/plain");
                intentShare.putExtra(Intent.EXTRA_TEXT,"https://courses.learncodeonline.in/");
                startActivity(Intent.createChooser(intentShare, "Select an action"));
            }
            android.R.id.home->{
                finish()
            }
        }
        return super.onOptionsItemSelected(item)
    }
}
