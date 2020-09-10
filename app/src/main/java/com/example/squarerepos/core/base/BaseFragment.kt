package com.example.squarerepos.core.base

import android.content.Context
import androidx.annotation.LayoutRes
import androidx.fragment.app.Fragment
import javax.inject.Inject

abstract class BaseFragment(@LayoutRes layout: Int) : Fragment(layout) {
    @Inject
    lateinit var viewModelFactory: ViewModelFactory

    override fun onAttach(context: Context) {
        super.onAttach(context)
        (requireActivity().application as BaseApplication).appComponent.inject(this)
    }
}