package com.bounswe2020group3.paperlayer.project

import android.content.Context
import android.os.Bundle
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.fragment.app.Fragment
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.bounswe2020group3.paperlayer.MainActivity
import com.bounswe2020group3.paperlayer.R
import kotlinx.android.synthetic.main.fragment_projects.*
import javax.inject.Inject


private const val TAG = "ProjectMainFragment"

class ProjectsFragment : Fragment(), ProjectsContract.View, OnCardClickListener {

    //Presenter object
    @Inject
    lateinit var presenter: ProjectMainPresenter

    //View object
    private lateinit var fragmentView: View

    //Adapter Object
    private lateinit var projectAdapter: ProjectAdapter

    // Declare Context variable at class level in Fragment
    private lateinit var mContext: Context
    private lateinit var recyclerView: RecyclerView

    //Project Card List
    private val projectCardList = ArrayList<ProjectCard>()


    override fun onAttach(context: Context) {
        super.onAttach(context)
        (context as MainActivity).getAppComponent().inject(this)
        mContext = context
    }

    override fun onDestroy() {
        super.onDestroy()
        resetProjectCardList()
        this.presenter.unbind()
        writeLogMessage("i", TAG, "ProjectMainFragment destroyed.")
    }

    override fun onCreateView(
            inflater: LayoutInflater, container: ViewGroup?,
            savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        val view = inflater.inflate(R.layout.fragment_projects, container, false)
        this.fragmentView = view
        initRecyclerView()
        resetProjectCardList()
        this.presenter.bind(this)
        writeLogMessage("i", TAG, "ProjectMainFragment view created")
        return view
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        buttonAddProject.setOnClickListener {
            presenter.onNewProjectButtonClicked()
        }
    }

    override fun getLayout(): View {
        return this.fragmentView
    }

    override fun showToast(message: String) {
        Toast.makeText(activity, message, Toast.LENGTH_LONG).show()
    }

    override fun writeLogMessage(type: String, tag: String, message: String) {
        when (type) {
            "e" -> Log.e(tag, message) //error
            "w" -> Log.w(tag, message) //warning
            "i" -> Log.i(tag, message) //information
            "d" -> Log.d(tag, message) //debug
            "v" -> Log.v(tag, message) //verbose
            else -> Log.e(tag, "Type is not defined")
        }
    }


    override fun resetProjectCardList() {
        projectCardList.clear()
        projectAdapter.submitList(this.projectCardList)
        projectAdapter.notifyDataSetChanged() //notify to update recyclerview
    }

    override fun submitProjectCardList() {
        projectAdapter.submitList(this.projectCardList)
        projectAdapter.notifyDataSetChanged() //notify to update recyclerview
        writeLogMessage("i", TAG, "Project Card List Updated! " + projectCardList.size)
    }

    override fun addProjectCard(projectName: String, projectBody: String, projectOwner: String, projectId: Int, projectType: String) {
        projectCardList.add(
                ProjectCard(projectName,
                        projectBody,
                        projectOwner, projectId, projectType))
        writeLogMessage("i", TAG, "Project Card Added $projectName ")
    }

    //Used for testing purposes
    private fun addExampleProjectList() {
        //val projectCardList=ArrayList<ProjectCard>()
        projectCardList.add(
                ProjectCard("Covid19 Search",
                        "We are so close to developing vaccine for covid19",
                        "crazyDoctor", 1, "journal"))
        projectCardList.add(ProjectCard("Embedded System Class Research",
                "How to improve cmpe443 class before students make a riot",
                "crazyProf", 2, "Conference"))
        projectCardList.add(ProjectCard("Eating Fruits affects performance in coding",
                "Research about how eating fruits while coding affects performance of programmers",
                "crazyCoder", 3, "journal"))
        projectAdapter.submitList(projectCardList)
    }


    private fun initRecyclerView() {
        this.recyclerView = fragmentView.findViewById(R.id.recyclerViewProjects)!!
        this.recyclerView.layoutManager = LinearLayoutManager(this.context)
        this.projectAdapter = ProjectAdapter(this)
        this.recyclerView.adapter = projectAdapter

        writeLogMessage("i", TAG, "RecyclerView initialized.")
    }

    override fun onViewButtonClick(item: ProjectCard, position: Int) {
        this.presenter.onViewProjectButtonClicked(item, position)
    }

    override fun onEditButtonClick(item: ProjectCard, position: Int) {
        this.presenter.onEditProjectButtonClicked(item, position)
    }

}