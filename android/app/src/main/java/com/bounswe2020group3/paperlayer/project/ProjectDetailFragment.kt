package com.bounswe2020group3.paperlayer.project

import android.content.Context
import android.os.Bundle
import android.util.Log
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.View.GONE
import android.view.View.VISIBLE
import android.view.ViewGroup
import android.widget.Button
import android.widget.ImageView
import android.widget.Toast
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import androidx.core.os.bundleOf
import androidx.navigation.Navigation
import com.bounswe2020group3.paperlayer.MainActivity
import com.bounswe2020group3.paperlayer.R
import com.bounswe2020group3.paperlayer.project.data.Project
import com.bounswe2020group3.paperlayer.project.data.User
import com.bounswe2020group3.paperlayer.projectCreate.ProjectState
import com.google.android.material.chip.Chip
import com.google.android.material.tabs.TabLayout
import kotlinx.android.synthetic.main.fragment_project_detail.*
import kotlinx.android.synthetic.main.fragment_project_detail.view.*
import javax.inject.Inject

private const val TAG = "ProjectFragment"


val tagColors = arrayOf(R.color.tagColor0, R.color.tagColor1, R.color.tagColor2, R.color.tagColor3,
    R.color.tagColor4,R.color.tagColor5,R.color.tagColor6,R.color.tagColor7,
    R.color.tagColor8,R.color.tagColor9)

class ProjectDetailFragment : Fragment(),ProjectDetailContract.View, OnMemberCardClickListener {

    //Presenter object
    @Inject
    lateinit var presenter: ProjectPresenter

    //View object
    private lateinit var fragmentView: View

    //Adapter Object
    private lateinit var membersAdapter: MembersAdapter

    private lateinit var recyclerView: RecyclerView

    //Member Card List
    private val memberCardList = ArrayList<MemberCard>()

    //Current OwnerID
    private var ownerID = 0
    var collabbed = -1
    override fun onAttach(context: Context) {
        super.onAttach(context)
        (context as MainActivity).getAppComponent().inject(this)
    }

    override fun onDestroy() {
        super.onDestroy()
        this.presenter.unbind()
        writeLogMessage("i",TAG,"ProjectFragment destroyed.")
    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        val view = inflater.inflate(R.layout.fragment_project_detail, container, false)
        this.fragmentView=view
        //Set ProjectPresenter view to project fragment
        this.presenter.setView(this)
        this.presenter.bind(this)

        initRecyclerView()
        resetMemberCardList()

        //Getting bundle arguments
        val projectID = arguments?.getInt("projectID")
        if (projectID != null) {
            this.presenter.fetchProject(projectID) //fetch project and update ui
            this.presenter.fetchRequestOfMine(projectID)
        }
        else{
            writeLogMessage("e",TAG,"projectID null")
        }
        //Giving bundle arguments
        val bundle = bundleOf("projectID" to projectID )

        view.findViewById<Button>(R.id.buttonInvite).setOnClickListener{
            Navigation.findNavController(view).navigate(R.id.navigateToInviteFromProjectDetails,bundle)
        }
        view.findViewById<ImageView>(R.id.imageViewCollabRequests).setOnClickListener{
            Navigation.findNavController(view).navigate(R.id.navigateToCollabRequestsFromProject,bundle)
        }
        view.findViewById<Button>(R.id.buttonCollab).setOnClickListener{
            presenter.OnClickCollab(projectID!!,collabbed)
        }
        writeLogMessage("i",TAG,"ProjectFragment view created")
        return view
    }

    override fun getLayout(): View {
        return this.fragmentView
    }

    override fun showToast(message: String) {
        Toast.makeText(activity, message, Toast.LENGTH_LONG).show()
    }

    override fun writeLogMessage(type:String ,tag: String,message: String) {
        when(type){
            "e"-> Log.e(tag,message) //error
            "w"-> Log.w(tag,message) //warning
            "i"-> Log.i(tag,message) //information
            "d"-> Log.d(tag,message) //debug
            "v"-> Log.v(tag,message) //verbose
            else->Log.e(tag,"Type is not defined")
        }
    }

    override fun resetMemberCardList() {
        memberCardList.clear()
        membersAdapter.submitList(this.memberCardList)
        membersAdapter.notifyDataSetChanged() //notify to update recyclerview
    }

    override fun submitMemberCardList() {
        membersAdapter.submitList(this.memberCardList)
        membersAdapter.notifyDataSetChanged() //notify to update recyclerview
        writeLogMessage("i", TAG, "Member Card List Updated! " + memberCardList.size)
    }

    override fun addMemberCard(user: User) {
        memberCardList.add(
            MemberCard(user))
        writeLogMessage("i", TAG, "Member Card Added ${user.username}")
    }

    override fun updateCurrentUser(ownerID: Int) {
        this.ownerID=ownerID
        this.presenter.getProject()?.let { updateProjectUI(it) }
    }

    private fun initRecyclerView() {
        this.recyclerView = fragmentView.findViewById(R.id.recyclerViewProjectMembers)!!
        this.recyclerView.layoutManager = LinearLayoutManager(this.context)
        this.membersAdapter = MembersAdapter(this)
        this.recyclerView.adapter = membersAdapter
        writeLogMessage("i", TAG, "RecyclerView initialized.")
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        tabLayoutProject.addOnTabSelectedListener(object : TabLayout.OnTabSelectedListener{
            override fun onTabSelected(tab: TabLayout.Tab?) {
                when (tab?.position) {
                    0 -> {
                        linearLayoutProperties.visibility = VISIBLE
                        linearLayoutMembers.visibility = GONE
                    }
                    1 -> {
                        linearLayoutProperties.visibility = GONE
                        linearLayoutMembers.visibility = VISIBLE
                    }
                }
            }

            override fun onTabUnselected(tab: TabLayout.Tab?) = Unit

            override fun onTabReselected(tab: TabLayout.Tab?) = Unit

        })
        buttonEditProject.setOnClickListener {
            presenter.navigateToEditProject()
        }
    }

    //Update project UI
    override fun updateProjectUI(project: Project) {
        this.fragmentView.projectTitle.text=project.name
        this.fragmentView.projectDescription.text=project.description
        this.fragmentView.projectOwner.text=project.owner
        this.fragmentView.projectType.text=project.project_type
        this.fragmentView.projectDue.text=project.due_date
        this.fragmentView.projectState.text=project.state
        this.fragmentView.projectRequirements.text=project.requirements

        if(project.event!=null)
        {
            this.fragmentView.textViewEvents.text=project.event.title
        }

        //Adding Members
        for (member in project.members.orEmpty()){
                addMemberCard(member)
                submitMemberCardList()
        }

        //Tag Field Text
        if(project.tags.isEmpty()) {
            this.fragmentView.textViewTags.setText(R.string.project_detail_tag_not_found)
        }
        else{
            this.fragmentView.textViewTags.visibility=GONE
        }

        //Adding tags to dynamically to project tags
        if (chipGroupTags?.childCount != project.tags.size) {
            for (tag in project.tags){
                val chip = Chip(this.fragmentView.chipGroupTags.context)
                chip.text=tag.name
                chip.setChipBackgroundColorResource(tagColors[tag.color])
                this.fragmentView.chipGroupTags.addView(chip)
            }
        }


        //Hide elements if project owner is not current user
        if(ownerID!=project.ownerId)
        {
            this.fragmentView.buttonEditProject.visibility= GONE
            this.fragmentView.buttonInvite.visibility= GONE
            this.fragmentView.imageViewCollabRequests.visibility = GONE
            if(project.state.equals(ProjectState.OPEN.value, true)) {
                buttonCollab.apply {
                    visibility = VISIBLE
                    if (collabbed != -1) text = "WITHDRAW"
                }

            }
        }
        writeLogMessage("i",TAG,"Project UI Updated")
    }

    //Reset project UI
    override fun resetProjectUI() {
        this.fragmentView.projectTitle.text="Example Project"
        this.fragmentView.projectDescription.text="Example Description"
        this.fragmentView.projectOwner.text="Example Owner"
        this.fragmentView.projectType.text="Example Type"
        this.fragmentView.projectDue.text="Example Date"
        this.fragmentView.projectState.text="Example State"
        writeLogMessage("i",TAG,"Project UI Reset")
    }

    override fun onCardClickListener(userId: Int) {
        val bundle = bundleOf("userID" to userId)
        Navigation.findNavController(requireView()).navigate(R.id.navigateToUserFromProject, bundle)
    }
    override fun collabCheck(index: Int) {
        collabbed = index
        buttonCollab.text = "WITHDRAW"
    }

    override fun collabUncheck() {
        collabbed = -1
        buttonCollab.text = "COLLABORATE"
    }
}