package com.bounswe2020group3.paperlayer.project

import com.bounswe2020group3.paperlayer.mvp.Mvp
import com.bounswe2020group3.paperlayer.project.data.Project
import io.reactivex.Single

interface ProjectContract {

    interface Presenter : Mvp.Presenter<View>{
        fun setView(view: View)
        fun showMessage(message: String)
        fun fetchProject(projectId: Int)
    }

    interface View: Mvp.View{
        fun getLayout(): android.view.View
        fun showToast(message: String)
        fun writeLogMessage(type:String ,tag: String,message: String)
        fun resetProjectUI()
        fun updateProjectUI(project:Project)
    }

    interface Model {
        fun getProject(projectId: Int): Single<Project>
    }

}