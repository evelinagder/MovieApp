package com.example.moviesapp.view

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.annotation.AnyRes
import androidx.annotation.LayoutRes
import androidx.databinding.DataBindingUtil
import androidx.databinding.ViewDataBinding
import androidx.fragment.app.Fragment
import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProviders
import com.example.moviesapp.view.BaseViewModelFactory

abstract class BaseFragment<B, VM> : Fragment()
        where B : ViewDataBinding, VM : ViewModel {

    /**
     * The view model object for the fragment.
     */
    protected val viewModel: VM by lazy { ViewModelProviders.of(this).get(getViewModelClass()) }

    /**
     * The binding object of the fragment. This can be used for accessing views.
     */
    protected lateinit var binding: B

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View? {

        //get layout and binding variable ids
        val layoutId = getLayoutResId()
        val bindResId = getViewModelResId()

        //make the binding
        binding = DataBindingUtil.inflate(inflater, layoutId, container, false)
        binding.lifecycleOwner = this
        binding.setVariable(bindResId, viewModel)
        return binding.root
    }

    /**
     * @return the view model identifier for the given view
     */
    @AnyRes
    protected abstract fun getViewModelResId(): Int

    /**
     * @return the layout resource identifier for the given view
     */
    @LayoutRes
    protected abstract fun getLayoutResId(): Int

    protected abstract fun getViewModelClass(): Class<VM>

    inline fun <reified T : ViewModel> Fragment.getViewModel(noinline creator: (() -> T)? = null): T {
        return if (creator == null)
            ViewModelProviders.of(this).get(T::class.java)
        else
            ViewModelProviders.of(this, BaseViewModelFactory(creator)).get(T::class.java)
    }

}