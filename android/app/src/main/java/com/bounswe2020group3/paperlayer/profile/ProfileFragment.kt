package com.bounswe2020group3.paperlayer.profile


import android.content.Context
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.core.os.bundleOf
import androidx.fragment.app.Fragment
import androidx.navigation.Navigation
import com.bounswe2020group3.paperlayer.MainActivity
import com.bounswe2020group3.paperlayer.R
import com.bounswe2020group3.paperlayer.data.user.User
import com.bounswe2020group3.paperlayer.profile.follow.FollowType
import com.squareup.picasso.Picasso
import kotlinx.android.synthetic.main.fragment_profile.*
import kotlinx.android.synthetic.main.fragment_profile.imageViewProfileAvatar
import kotlinx.android.synthetic.main.fragment_profile.layoutProfileDetail
import javax.inject.Inject


class ProfileFragment : Fragment(), ProfileContract.View {

    @Inject
    lateinit var presenter: ProfileContract.Presenter

    override fun onAttach(context: Context) {
        super.onAttach(context)
        (context as MainActivity).getAppComponent().inject(this)
    }

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?,
                              savedInstanceState: Bundle?): View? {
        presenter.bind(this)
        // Inflate the layout for this fragment
        return inflater.inflate(R.layout.fragment_profile, container, false)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        presenter.subscribeAuthUser()

        imageButtonSettings.setOnClickListener {
            Navigation.findNavController(view).navigate(R.id.navigateToProfileEditFromProfile)
        }

        val followerBundle = bundleOf("followType" to FollowType.FOLLOWER)
        val followingBundle = bundleOf("followType" to FollowType.FOLLOWING)
        val followRequestBundle = bundleOf("followType" to FollowType.FOLLOW_REQUEST)

        linearLayoutFollowers.setOnClickListener {
            Navigation.findNavController(view).navigate(R.id.navigateToFollowListFromProfile, followerBundle)
        }

        linearLayoutFollowings.setOnClickListener {
            Navigation.findNavController(view).navigate(R.id.navigateToFollowListFromProfile, followingBundle)
        }

        layoutFollowRequests.setOnClickListener {
            Navigation.findNavController(view).navigate(R.id.navigateToFollowListFromProfile, followRequestBundle)
        }
    }

    override fun onResume() {
        super.onResume()
        presenter.loadAuthUser()
    }

    override fun onDestroy() {
        super.onDestroy()
        presenter.unbind()
    }

    override fun updateProfileUI(user: User) {
        try {
            val profile = user.profile.first()
            val fullName = "${profile.name} ${profile.lastName}"

            textViewEmail.text = user.email
            textViewFullName.text = fullName
            textViewBio.text = profile.bio
            textViewBirthday.text = profile.birthday.toString()
            textViewGender.text = profile.gender
            textViewInterests.text = profile.interests
            textViewExpertise.text = profile.expertise

            // Stats
            textViewProfileFollowers.text = user.countOfFollowers.toString()
            textViewProfileFollowings.text = user.countOfFollowings.toString()
            textViewProfileFollowRequestCount.text = user.countOfFollowRequests.toString()

            val imageUrl = profile.profile_picture
            if (imageUrl != null && imageUrl != "") {
                Picasso.get().load(imageUrl).into(imageViewProfileAvatar)
            }
        } catch (e: NoSuchElementException) {
            e.printStackTrace()
            showErrorToast("An error occurred. Please try again.")
        }
    }

    override fun navigateToLogin() {
        Navigation.findNavController(requireView()).navigate(R.id.navigateToLoginFromProfile)
    }

    override fun showLoading() {
        progressBarProfile.visibility = View.VISIBLE
        layoutProfileDetail.visibility = View.GONE
    }

    override fun hideLoading() {
        progressBarProfile.visibility = View.GONE
        layoutProfileDetail.visibility = View.VISIBLE
    }

    override fun showErrorToast(message: String) {
        Toast.makeText(activity, message, Toast.LENGTH_LONG).show()
    }

    override fun showInfoToast(message: String) {
        Toast.makeText(activity, message, Toast.LENGTH_SHORT).show()
    }
}