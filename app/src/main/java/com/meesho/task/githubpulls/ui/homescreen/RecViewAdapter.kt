package com.meesho.task.githubpulls.ui.homescreen

import android.support.v7.widget.RecyclerView
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import butterknife.BindView
import butterknife.ButterKnife
import com.bumptech.glide.Glide
import com.bumptech.glide.request.RequestOptions
import com.meesho.task.githubpulls.R
import com.meesho.task.githubpulls.data.models.api.PullRequests
import com.meesho.task.githubpulls.utils.widgets.GeneralTextView
import de.hdodenhof.circleimageview.CircleImageView

class RecViewAdapter(val data: MutableList<PullRequests>) : RecyclerView.Adapter<RecViewAdapter.ViewHolder>() {


    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        val itemView = LayoutInflater.from(parent.context).inflate(R.layout.searchlist_recycler, parent, false)
        return ViewHolder(itemView)

    }

    override fun getItemCount(): Int = data.size

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        val pullRequest = data[position]
        holder.title.text = pullRequest.title
        holder.details.text = String.format(holder.details.context.getString(R.string.list_subtitle),
                pullRequest.user.login,
                getDateFrom(pullRequest.createdAt)
        ).trim()

        Glide.with(holder.imageView.context)
                .load(pullRequest.user.avatarUrl)
                .into(holder.imageView)

        holder.pullId.text = """#${pullRequest.number}"""

    }

    private fun getDateFrom(createdAt: String): String {
        var date = ""
        for (i in 0..createdAt.length) {
            if (createdAt[i] == 'T') break
            date += createdAt[i]
        }

        //Write logic to handle time appropriately

        return date

    }

    fun updateData(requestsList: MutableList<PullRequests>) {
        data.clear()
        data.addAll(requestsList)
        notifyDataSetChanged()
    }


    inner class ViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {

        //        @BindView(R.id.card_view1) lateinit var parent: CardView
        @BindView(R.id.item_image)
        lateinit var imageView: CircleImageView
        @BindView(R.id.title)
        lateinit var title: GeneralTextView
        @BindView(R.id.details)
        lateinit var details: GeneralTextView
        @BindView(R.id.id_tv)
        lateinit var pullId: GeneralTextView

        init {
            ButterKnife.bind(this, itemView)
        }
    }
}