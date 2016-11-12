package com.wubydax.gymlishwebsites.data;

import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.wubydax.gymlishwebsites.R;

import java.util.ArrayList;

import static com.wubydax.gymlishwebsites.R.id.desc_text;
import static com.wubydax.gymlishwebsites.R.id.title;

/**
 * Created by dax on 12/11/16.
 */

public class SitesAdapter extends RecyclerView.Adapter<SitesAdapter.SitesHolder> {
    private ArrayList<WebsiteInfo> mInfoArrayList;
    private OnWebsiteClickListener mOnWebsiteClickListener;

    public SitesAdapter(OnWebsiteClickListener onWebsiteClickListener) {
        mOnWebsiteClickListener = onWebsiteClickListener;
    }

    @Override
    public SitesHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        return new SitesHolder(LayoutInflater.from(parent.getContext()).inflate(R.layout.item_layout, parent, false));
    }

    @Override
    public void onBindViewHolder(SitesHolder holder, int position) {
        WebsiteInfo websiteInfo = mInfoArrayList.get(position);
        holder.title.setText(websiteInfo.title);
        holder.description.setText(websiteInfo.description);
    }

    @Override
    public int getItemCount() {
        return mInfoArrayList != null ? mInfoArrayList.size() : 0;
    }

    public void updateData(ArrayList<WebsiteInfo> list) {
        mInfoArrayList = list;
        notifyDataSetChanged();
    }

    class SitesHolder extends RecyclerView.ViewHolder {
        TextView title, description;

        SitesHolder(View itemView) {
            super(itemView);
            title = (TextView) itemView.findViewById(R.id.title_text);
            description = (TextView) itemView.findViewById(desc_text);
            itemView.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view) {
                    if(mOnWebsiteClickListener != null) mOnWebsiteClickListener.OnWebsiteClick(mInfoArrayList.get(getAdapterPosition()));
                }
            });
        }
    }

    public interface OnWebsiteClickListener {
        void OnWebsiteClick(WebsiteInfo websiteInfo);
    }
}
