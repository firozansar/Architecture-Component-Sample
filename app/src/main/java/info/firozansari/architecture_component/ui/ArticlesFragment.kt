package info.firozansari.architecture_component.ui

import android.os.Bundle
import androidx.fragment.app.Fragment
import androidx.lifecycle.Observer
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import info.firozansari.architecture_component.Extension.gone
import info.firozansari.architecture_component.Extension.visible
import info.firozansari.architecture_component.R
import info.firozansari.architecture_component.api.NetworkState
import info.firozansari.architecture_component.ui.adapter.ArticlesAdapter
import info.firozansari.architecture_component.viewmodels.ArticlesViewModel
import kotlinx.android.synthetic.main.fragment_articles.*
import org.koin.androidx.viewmodel.ext.android.viewModel


class ArticlesFragment : Fragment(), ArticlesAdapter.OnClickListener {

    private val recyclerViewAdapter = ArticlesAdapter(this)
    private val model by viewModel<ArticlesViewModel>()

    override fun onCreateView(
            inflater: LayoutInflater, container: ViewGroup?,
            savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        return inflater.inflate(R.layout.fragment_articles, container, false)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        setupRecyclerView()
        setupListeners()
        observeViewModelData()

    }

    private fun setupListeners() {
        fragment_button_load_data.setOnClickListener { model.refreshAllList() }
    }

    private fun observeViewModelData() {
        // Not observing network state here because Room persistence is implemented
        //model.networkState?.observe(viewLifecycleOwner, Observer { recyclerViewAdapter.updateNetworkState(it) })
        model.articles.observe(viewLifecycleOwner, Observer { recyclerViewAdapter.submitList(it) })
    }

    private fun setupRecyclerView() {
        fragment_recipes_recycler_view.adapter = recyclerViewAdapter
    }

    //Override onRetryClick from ArticlesAdapter.OnClickListener
    override fun onRetryClick() {
        model.refreshFailedRequest()
    }

    //Override whenListIsUpdated from ArticlesAdapter.OnClickListener
    override fun whenListIsUpdated(size: Int, networkState: NetworkState?) {
        setInitialStates()
        if (size == 0) {
            setSizeZeroInitialState()
            when (networkState) {
                NetworkState.SUCCESS -> {
                    fragment_text_network.text = getString(R.string.article_empty)
                    fragment_text_network.visible()
                    fragment_button_load_data.gone()
                }
                NetworkState.FAILED -> {
                    fragment_text_network.text = getString(R.string.error_msg)
                    fragment_image_warning.visible()
                    fragment_text_network.visible()
                    fragment_button_load_data.visible()
                }
                NetworkState.RUNNING -> {
                    fragment_progress_bar.visible()
                }
            }
        }
    }

    private fun setSizeZeroInitialState() {
        fragment_text_network.text = getString(R.string.article_empty)
        fragment_text_network.visible()
    }

    private fun setInitialStates() {
        fragment_button_load_data.gone()
        fragment_image_warning.gone()
        fragment_text_network.gone()
        fragment_progress_bar.gone()
    }

}