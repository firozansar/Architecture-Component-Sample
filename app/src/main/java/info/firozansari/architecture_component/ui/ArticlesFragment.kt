package info.firozansari.architecture_component.ui

import android.os.Bundle
import android.view.View
import androidx.fragment.app.Fragment
import info.firozansari.architecture_component.R
import info.firozansari.architecture_component.ui.adapter.ArticlesAdapter
import info.firozansari.architecture_component.viewmodels.ArticlesViewModel
import kotlinx.android.synthetic.main.fragment_articles.*
import org.koin.androidx.viewmodel.ext.android.viewModel

class ArticlesFragment : Fragment(R.layout.fragment_articles) {

    private val recyclerViewAdapter = ArticlesAdapter()
    private val model by viewModel<ArticlesViewModel>()

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        setupRecyclerView()
        observeViewModelData()
    }

    private fun observeViewModelData() {
        // Not observing network state here because Room persistence is implemented
        //model.networkState?.observe(viewLifecycleOwner, Observer { recyclerViewAdapter.updateNetworkState(it) })
        model.articles.observe(viewLifecycleOwner, { recyclerViewAdapter.submitList(it) })
    }

    private fun setupRecyclerView() {
        fragment_recipes_recycler_view.adapter = recyclerViewAdapter
    }

}