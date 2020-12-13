package com.pru.navigationcomponentapp

import androidx.fragment.app.Fragment


abstract class BaseFragment(layoutID: Int) : Fragment(layoutID) {
    private var enableDrawer = true
    private var hideActionBar = false
    fun setupToolBar(
        title: String?,
        enableDrawer: Boolean = true,
        hideActionBar: Boolean = false
    ) {
        this.enableDrawer = enableDrawer
        this.hideActionBar = hideActionBar
        drawerCheck()
        hideActionBarCheck()
        (requireActivity() as HomeActivity).supportActionBar?.title =
            title ?: getString(R.string.app_name)
    }

    private fun hideActionBarCheck() {
        if (hideActionBar) {
            (requireActivity() as HomeActivity).supportActionBar?.hide()
        } else {
            (requireActivity() as HomeActivity).supportActionBar?.show()
        }
    }

    private fun drawerCheck() {
        if (enableDrawer) {
            openDrawer()
        } else {
            closeDrawer()
        }
    }

    private fun openDrawer() {
        (requireActivity() as HomeActivity).unLockNavigationDrawer()
    }

    private fun closeDrawer() {
        (requireActivity() as HomeActivity).lockNavigationDrawer()
    }

    override fun onResume() {
        super.onResume()
        drawerCheck()
    }

    override fun onDestroyView() {
        if (!enableDrawer) {
            enableDrawer = !enableDrawer
            drawerCheck()
        }
        if (hideActionBar) {
            hideActionBar = !hideActionBar
            hideActionBarCheck()
        }
        super.onDestroyView()
    }
}