package info.firozansari.architecture_component.ui.adapter

import android.view.View
import androidx.recyclerview.widget.RecyclerView
import info.firozansari.architecture_component.Extension.gone
import info.firozansari.architecture_component.Extension.visible
import info.firozansari.architecture_component.api.NetworkState
import kotlinx.android.synthetic.main.item_repo_state.view.*

/**
 * Created by Firoz Ansari on 15/10/2020.
 */
class RepoStateViewHolder(parent: View) : RecyclerView.ViewHolder(parent) {

    fun bind(networkState: NetworkState?, callback: ArticlesAdapter.OnClickListener) {
        setVisibleRightViews(networkState)
        itemView.repo_state_button.setOnClickListener { callback.onRetryClick() }
    }

    private fun setVisibleRightViews(networkState: NetworkState?) {
        when (networkState) {
            NetworkState.FAILED -> {
                itemView.repo_state_button.visible()
                itemView.repo_error_msg.visible()
                itemView.repo_progress_bar.gone()
            }
            NetworkState.RUNNING -> {
                itemView.repo_state_button.gone()
                itemView.repo_error_msg.gone()
                itemView.repo_progress_bar.visible()
            }
            NetworkState.SUCCESS -> {
                itemView.repo_state_button.gone()
                itemView.repo_error_msg.gone()
                itemView.repo_progress_bar.gone()
            }
        }
    }
}