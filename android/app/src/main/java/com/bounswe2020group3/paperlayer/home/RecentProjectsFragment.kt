package com.bounswe2020group3.paperlayer.home

import android.content.Context
import android.os.Bundle
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.core.os.bundleOf
import androidx.fragment.app.Fragment
import androidx.navigation.Navigation
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.bounswe2020group3.paperlayer.MainActivity
import com.bounswe2020group3.paperlayer.R
import com.bounswe2020group3.paperlayer.home.cards.ProjectUpdateCard
import com.bounswe2020group3.paperlayer.home.adaptors.OnCardClickListener
import com.bounswe2020group3.paperlayer.home.adaptors.ProjectAdaptor
import com.google.android.material.bottomnavigation.BottomNavigationView
import javax.inject.Inject
private const val TAG = "RecentProjectsFragment"

class RecentProjectsFragment : Fragment(), HomeContract.RecentProjectsView, OnCardClickListener {

    @Inject
    lateinit var presenter : RecentProjectsPresenter

    lateinit var fragment_view : View
    private lateinit var mContext: Context

    private lateinit var recyclerView: RecyclerView
    private lateinit var viewAdapter: ProjectAdaptor
    private lateinit var viewManager: RecyclerView.LayoutManager

    private val projectslist = ArrayList<ProjectUpdateCard>()

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View? {
        val view = inflater.inflate(R.layout.fragment_projectupdates, container, false)
        fragment_view = view
        view.findViewById<BottomNavigationView>(R.id.bottomNavigationView).setOnNavigationItemSelectedListener {item ->
            when(item.itemId){
                R.id.eventFragment ->{Navigation.findNavController(view).navigate(R.id.navigateToEventsFromProjectUpdates)}
                R.id.projectUpdateFragment ->{}
                R.id.milestoneFragment ->{Navigation.findNavController(view).navigate(R.id.navigateToMilestonesFromProjectUpdates)}

            }
            true
        }

        this.presenter.bind(this)
        initRecyclerView()
        resetCardList()

        writeLogMessage("i",TAG,"event fragment has been created.")
        return view
    }

    
    override fun getLayout(): View {
        TODO("Not yet implemented")
    }

    override fun showToast(message: String) {
        Toast.makeText(activity, message, Toast.LENGTH_LONG).show()
    }
    override fun writeLogMessage(type: String, tag: String, message: String) {
        when(type){
            "e"-> Log.e(tag,message) //error
            "w"-> Log.w(tag,message) //warning
            "i"-> Log.i(tag,message) //information
            "d"-> Log.d(tag,message) //debug
            "v"-> Log.v(tag,message) //verbose
            else-> Log.e(tag,"Type is not defined")
        }
    }
    private fun initRecyclerView(){
        viewManager = LinearLayoutManager(this.context)
        viewAdapter= ProjectAdaptor(this)
        recyclerView = fragment_view.findViewById<RecyclerView>(R.id.recyclerViewUpdates).apply{
            setHasFixedSize(true)
            layoutManager = viewManager
            adapter = viewAdapter
        }


    }

    override fun cardCheck(id: Int,collabId: Int, position: Int) {
        projectslist[position].requestSent = collabId


        submitCardList()
        viewAdapter.notifyDataSetChanged()
    }

    override fun cardUncheck(id: Int, position: Int) {
        projectslist[position].requestSent = -1


        submitCardList()
        viewAdapter.notifyDataSetChanged()
    }

    override fun resetCardList() {
        projectslist.clear()
        viewAdapter.submitList(this.projectslist)
        viewAdapter.notifyDataSetChanged() //notify to update recyclerview
    }

    override fun submitCardList() {
        viewAdapter.submitList(this.projectslist)
        viewAdapter.notifyDataSetChanged() //notify to update recyclerview
        writeLogMessage("i", TAG,"Project Card List Updated! " + projectslist.size)
    }

    override fun addCard(card: ProjectUpdateCard) {
        projectslist.add(card)
    }

    override fun onAttach(context: Context) {
        super.onAttach(context)
        (context as MainActivity).getAppComponent().inject(this)
        mContext=context
    }

    override fun onCollabButtonClick(item: ProjectUpdateCard, position: Int) {
        this.presenter.OnCollabButtonClicked(item,position)
    }

    override fun onViewButtonClick(item: ProjectUpdateCard, position: Int) {
        val bundle = bundleOf(("projectID" to item.projectId),("requestSent" to item.requestSent) )

        Navigation.findNavController(fragment_view).navigate(R.id.navigateToProjectFromRecentProjects,bundle)    }


}