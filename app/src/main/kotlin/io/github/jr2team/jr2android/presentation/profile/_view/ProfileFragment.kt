package io.github.jr2team.jr2android.presentation.profile._view

import io.github.jr2team.jr2android.R
import io.github.jr2team.jr2android.presentation._base._view.BaseFragment
import io.github.jr2team.jr2android.presentation.profile._viewmodel.ProfileViewModel

class ProfileFragment : BaseFragment<ProfileViewModel>() {
    override var viewModel = ProfileViewModel()
    override val layoutRes: Int = R.layout.fragment_profile
    override val titleDefault: String
        get() = getString(R.string.profile_fragment_title)
}