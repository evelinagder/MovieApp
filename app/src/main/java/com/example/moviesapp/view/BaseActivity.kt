package com.example.moviesapp.view

import android.os.Bundle
import androidx.annotation.AnyRes
import androidx.annotation.LayoutRes
import androidx.appcompat.app.AppCompatActivity
import androidx.databinding.DataBindingUtil
import androidx.databinding.ViewDataBinding
import androidx.fragment.app.Fragment
import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import androidx.lifecycle.ViewModelProviders

abstract class BaseActivity<B, VM> : AppCompatActivity(), IActivity<VM> where B : ViewDataBinding, VM : ViewModel {


    protected val viewModel: VM by lazy { ViewModelProviders.of(this).get(getViewModelClass()) }


    protected lateinit var binding: B


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        //get layout and binding variable ids
        val layoutId = getLayoutResId()
        val bindResId = getViewModelResId()

        //make the binding
        binding = DataBindingUtil.setContentView(this, layoutId)
        binding.lifecycleOwner = this
        binding.setVariable(bindResId, viewModel)

    }

    /**
     * @return the view model identifier for the given view
     * or use the [DEFAULT_VIEW_MODEL_ID]
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

class BaseViewModelFactory<T>(val creator: () -> T) : ViewModelProvider.Factory {
    override fun <T : ViewModel?> create(modelClass: Class<T>): T {
        return creator() as T
    }

}