package com.busara.android.smartduka.adapters;

import android.content.Context;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.busara.android.smartduka.R;
import com.busara.android.smartduka.utils.CategoryResult;

import java.util.ArrayList;

import butterknife.BindView;
import butterknife.ButterKnife;

/**
 * Created by Mauryn on 6/11/2018.
 */

public class CategoryAdapter extends RecyclerView.Adapter<CategoryAdapter.categoryAdapterViewHolder> {


    Context mContext;

    ArrayList<CategoryResult> mcategoryResults;


    public void setCategoryData(ArrayList<CategoryResult> categoryResults, Context context) {
        mcategoryResults = categoryResults;
        mContext = context;
        notifyDataSetChanged();
    }

//    public CategoryAdapter() {
//
//    }


    @Override
    public categoryAdapterViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        Context context = parent.getContext();
        int layoutIdForListItem = R.layout.content_category;

        LayoutInflater inflater = LayoutInflater.from(context);
        boolean shouldAttachtoParentImmediately = false;
        View view = inflater.inflate(layoutIdForListItem, parent, shouldAttachtoParentImmediately);
        categoryAdapterViewHolder recyclerViewHolder = new categoryAdapterViewHolder(view);
        return recyclerViewHolder;

    }

    @Override
    public void onBindViewHolder(categoryAdapterViewHolder holder, int position) {
        String name = mcategoryResults.get(position).getName();
        String description = mcategoryResults.get(position).getDescription();
        Integer id = mcategoryResults.get(position).getId();
        Boolean is_active = mcategoryResults.get(position).getIs_active();
        String created = mcategoryResults.get(position).getCreated();

        holder.mCategoryNameView.setText(name);
        holder.mCategoryDescriptionView.setText(description);


    }

    @Override
    public int getItemCount() {
        if (mcategoryResults != null) {
            return mcategoryResults.size();
        } else {
            return 0;
        }
    }

    public class categoryAdapterViewHolder extends RecyclerView.ViewHolder {
        @BindView(R.id.categoryname)
        TextView mCategoryNameView;
        @BindView(R.id.categoryDescription)
        TextView mCategoryDescriptionView;

        public categoryAdapterViewHolder(View itemView) {
            super(itemView);

            ButterKnife.bind(this, itemView);

        }


    }
}
