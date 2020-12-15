package com.bounswe2020group3.paperlayer.invite

import com.bounswe2020group3.paperlayer.invite.data.InviteRequest
import com.bounswe2020group3.paperlayer.invite.data.InviteResponse
import com.bounswe2020group3.paperlayer.invite.data.InvitedUserResponse
import com.bounswe2020group3.paperlayer.login.data.AuthToken
import com.bounswe2020group3.paperlayer.profile.data.User
import com.bounswe2020group3.paperlayer.util.Session
import io.reactivex.Observable
import io.reactivex.Single
import io.reactivex.android.schedulers.AndroidSchedulers
import io.reactivex.schedulers.Schedulers
import io.reactivex.subjects.BehaviorSubject
import retrofit2.Retrofit
import javax.inject.Inject

class InviteModel @Inject constructor(private var sessionManager: Session, retrofit: Retrofit): InviteContract.Model {
    private var userService: InviteContract.UserService = retrofit.create(InviteContract.UserService::class.java)
    override fun inviteUsers(inviteRequest: InviteRequest): Single<InviteResponse> {
        val authToken = "Token ${sessionManager.getToken().value?.token ?: ""}"

        return userService.inviteUsers(authToken,inviteRequest)
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
    }

    override fun getAllUsers(): Observable<List<User>>? {
        return userService.getUsers()
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
    }
    override fun getAuthToken(): BehaviorSubject<AuthToken> {
        return sessionManager.getToken()
    }

    override fun getInvited(projectId: Int): Single<InvitedUserResponse> {
        return userService.getInvited(projectId)
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
    }
}