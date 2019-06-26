package io.github.jr2team.jr2android.presentation.ui.profile

import io.github.jr2team.jr2android.R
import io.github.jr2team.jr2android.presentation.ui.BaseFragment
import io.github.jr2team.jr2android.presentation.viewmodel.profile.ProfileViewModel

class ProfileFragment : BaseFragment<ProfileViewModel>() {
    override var viewModel = ProfileViewModel()
    override val layoutRes: Int = R.layout.fragment_profile
    override val titleDefault: String
        get() = getString(R.string.profile_fragment_title)
}