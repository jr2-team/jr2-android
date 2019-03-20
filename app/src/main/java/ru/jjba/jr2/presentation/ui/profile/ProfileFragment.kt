package ru.jjba.jr2.presentation.ui.profile

import ru.jjba.jr2.R
import ru.jjba.jr2.presentation.ui.base.BaseFragment
import ru.jjba.jr2.presentation.viewmodel.profile.ProfileViewModel

class ProfileFragment : BaseFragment<ProfileViewModel>() {
    override var viewModel = ProfileViewModel()
    override val layoutRes: Int = R.layout.fragment_profile
    override val titleDefault: String
        get() = getString(R.string.profile_fragment_title)
}