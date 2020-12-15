package com.bounswe2020group3.paperlayer.home


import com.bounswe2020group3.paperlayer.home.cards.EventCard
import com.bounswe2020group3.paperlayer.home.cards.MilestoneCard
import com.bounswe2020group3.paperlayer.home.data.Event
import com.bounswe2020group3.paperlayer.login.data.AuthToken
import com.bounswe2020group3.paperlayer.mvp.Mvp
import com.bounswe2020group3.paperlayer.project.data.Milestone
import com.bounswe2020group3.paperlayer.project.data.Project
import io.reactivex.Observable
import io.reactivex.Single
import io.reactivex.subjects.BehaviorSubject
import retrofit2.http.GET
import retrofit2.http.Path
import retrofit2.http.Query

interface HomeContract {
    interface EventPresenter : Mvp.Presenter<EventView> {
        fun setView(eventView: HomeContract.EventView)
        fun showMessage(message: String)
        fun fetchEvents(ownerId : Int)
        fun subscribeAuthToken()
    }
    interface EventView: Mvp.View{
        fun getLayout(): android.view.View
        fun showToast(message: String)
        fun writeLogMessage(type:String ,tag: String,message: String)

        fun resetCardList()
        fun submitCardList()
        fun addCard(card : EventCard)

    }
    interface MileStoneView: Mvp.View{
        fun getLayout(): android.view.View
        fun showToast(message: String)
        fun writeLogMessage(type:String ,tag: String,message: String)

        fun resetCardList()
        fun submitCardList()
        fun addCard(card : MilestoneCard)

    }
    interface MileStonePresenter : Mvp.Presenter<MileStoneView> {
        fun setView(milestoneView: HomeContract.MileStoneView)
        fun showMessage(message: String)
        fun fetchMilestones(ownerId : Int)
        fun subscribeAuthToken()
    }


    interface HomeView: Mvp.View{


    }

    interface Model{
        fun getAllEvents(): Observable<List<Event>>?
        fun getAuthToken(): BehaviorSubject<AuthToken>
        fun getallprojectsOfTheOwner(OwnerId:Int): Observable<List<Project>>

    }
    interface EventsService{
        @GET("/api/events/")
        fun getEvents(): Observable<List<Event>>
    }
    interface ProjectService {
        @GET("/api/projects/{projectId}/")
        fun getProject(@Path("projectId") projectId: Int): Single<Project>

        @GET("/api/projects/")
        fun getAllProjectsOfOwner(@Query("owner__id") ownerId: Int): Observable<List<Project>>
    }


}