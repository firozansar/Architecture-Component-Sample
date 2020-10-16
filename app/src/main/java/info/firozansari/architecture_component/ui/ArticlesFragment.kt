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


class ArticlesFragment : Fragment(){

    private val recyclerViewAdapter = ArticlesAdapter()
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
        observeViewModelData()

    }


    private fun observeViewModelData() {
        // Not observing network state here because Room persistence is implemented
        //model.networkState?.observe(viewLifecycleOwner, Observer { recyclerViewAdapter.updateNetworkState(it) })
        model.articles.observe(viewLifecycleOwner, Observer { recyclerViewAdapter.submitList(it) })
    }

    private fun setupRecyclerView() {
        fragment_recipes_recycler_view.adapter = recyclerViewAdapter
    }



}