package com.naveen.interviewapp

import android.app.ProgressDialog
import android.content.Context
import android.support.v7.app.AppCompatActivity
import android.os.Bundle
import android.support.v7.widget.DefaultItemAnimator
import android.support.v7.widget.LinearLayoutManager
import android.util.Log
import android.view.Menu
import android.view.MenuItem
import android.widget.Toast
import com.google.android.gms.ads.AdRequest
import com.naveen.interviewapp.R.id.recyclerview
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response
import kotlinx.android.synthetic.main.activity_ques_list.*

import retrofit2.converter.gson.GsonConverterFactory
import retrofit2.Retrofit
import com.google.android.gms.ads.AdView
import com.webianks.library.easyportfolio.EasyPortfolio
import com.webianks.library.easyportfolio.Project
import android.content.Intent
import android.net.ConnectivityManager
import android.net.NetworkInfo
import android.net.Uri
import android.view.View
import com.google.android.gms.ads.AdListener


class QuesListActivity : AppCompatActivity() {

    private var context: Context? = null
    private lateinit var questionList: ArrayList<Question>

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_ques_list)
        context = this@QuesListActivity
        questionList= ArrayList()

        val adRequest = AdRequest.Builder().build()
        adView.loadAd(adRequest)
        if (isOnline(applicationContext)) {
            getListResources()

        } else {
            Toast.makeText(applicationContext,"pls_check_your_internet_connection",Toast.LENGTH_SHORT).show()
        }

        val lmanager = LinearLayoutManager(context, LinearLayoutManager.VERTICAL, false)
        recyclerview.setLayoutManager(lmanager)
        recyclerview.setAdapter(QuesListAdapter(questionList, context as QuesListActivity))
        recyclerview.setItemAnimator(DefaultItemAnimator())
        adView.adListener = object: AdListener() {
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
        when (item.itemId) {R.id.about_us -> {

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
        }
        return super.onOptionsItemSelected(item)
    }
    private fun getListResources() {
        val dialog = ProgressDialog(context)
        dialog.setMessage("Please Wait..")
        //dialog.setTitle("Connecting server");
        dialog.show()
        dialog.setCancelable(false)
      /*  val retrofit = Retrofit.Builder()
                .baseUrl("https://learncodeonline.in/api/android/")
                .addConverterFactory(GsonConverterFactory.create()) //Here we are using the GsonConverterFactory to directly convert json data to object
                .build()*/

        val api = APIExecutor.getRetrofitInstance().create(ApiService::class.java)
        api.getListResources().enqueue(object : Callback<ApiResponse> {
            override fun onResponse(call: Call<ApiResponse>, response: Response<ApiResponse>?) {
                dialog.dismiss()
                if (response != null && response.body() != null) {
                    val response1 = response.body()
                    var qlist=response1.questions
                    for (item: Question in qlist!!) {
                        Log.e("response", item.question)
                    }

                    val lmanager = LinearLayoutManager(context, LinearLayoutManager.VERTICAL, false)
                    recyclerview.setLayoutManager(lmanager)
                    recyclerview.setAdapter(QuesListAdapter(qlist as ArrayList<Question>, context as QuesListActivity))
                    recyclerview.setItemAnimator(DefaultItemAnimator())
                }
            }

            override fun onFailure(call: Call<ApiResponse>, t: Throwable) {
                dialog.dismiss()
                Log.e("error msg", t.message)
                // AppUtils.showToast(context, "Server not responding");
            }
        })

    }

    fun isOnline(context: Context): Boolean {
        val cm = context.getSystemService(Context.CONNECTIVITY_SERVICE) as ConnectivityManager
        val netInfo = cm.activeNetworkInfo
        return netInfo != null && netInfo.isConnected
    }

}

