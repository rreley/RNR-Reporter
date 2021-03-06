package com.example.rnr_reporter.RecyclerView;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.coordinatorlayout.widget.CoordinatorLayout;
import androidx.recyclerview.widget.RecyclerView;

import com.example.rnr_reporter.DataBase.Entities.Injury;
import com.example.rnr_reporter.DataBase.Entities.Property;
import com.example.rnr_reporter.DataBase.Entities.Situation;
import com.example.rnr_reporter.R;

import java.util.ArrayList;
import java.util.List;

public class AdapterList extends RecyclerView.Adapter{

    private List<Injury> mAllInjuries = new ArrayList<>();
    private List<Property> mAllProperties = new ArrayList<>();
    private List<Situation> mAllSituations = new ArrayList<>();

    private Context mContext;

    public AdapterList(Context context) {
        mContext = context;
    }

    private OnItemClickListener mOnItemClickListener;

    @NonNull
    @Override
    public RecyclerView.ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int ViewType) {
        RecyclerView.ViewHolder vh;

        switch (ViewType) {
            case 0:
                View injuryItemView = LayoutInflater.from(parent.getContext()).inflate(R.layout.injury_item_layout, parent, false);
                vh = new InjuryViewHolder(injuryItemView);
                break;
            case 1:
                View propertyItemView = LayoutInflater.from(parent.getContext()).inflate(R.layout.property_item_layout, parent, false);
                vh = new PropertyViewHolder(propertyItemView);
                break;
            default:
                View situationItemView = LayoutInflater.from(parent.getContext()).inflate(R.layout.situation_item_layout, parent, false);
                vh = new SituationViewHolder(situationItemView);
                break;
        }
        return vh;
    }

    @Override
    public int getItemViewType(int position) {
        if((position + 1) <= mAllInjuries.size()) {
            return 0;
        }
        else if((position + 1) > mAllInjuries.size() && (position + 1) - mAllInjuries.size() <= mAllProperties.size()) {
            return 1;
        }
        else {
            return 2;
        }
    }

    @Override
    public void onBindViewHolder(@NonNull RecyclerView.ViewHolder holder, int position) {
        int viewType = getItemViewType(position);
        switch(viewType) {
           case 0:
               InjuryViewHolder injuryViewHolder = (InjuryViewHolder)holder;
               Injury mInjury = mAllInjuries.get(position);
               injuryViewHolder.name.setText(mInjury.getName());
               injuryViewHolder.date.setText(mInjury.getDate());
               injuryViewHolder.description.setText(mInjury.getDescription());
               break;
           case 1:
               PropertyViewHolder propertyViewHolder = (PropertyViewHolder)holder;
               Property mProperty = mAllProperties.get(position - mAllInjuries.size());
               propertyViewHolder.name.setText(mProperty.getPropertyDamaged());
               propertyViewHolder.date.setText(mProperty.getDate());
               propertyViewHolder.description.setText(mProperty.getDescription());
               break;
           case 2:
               SituationViewHolder situationViewHolder = (SituationViewHolder)holder;
               Situation mSituation = mAllSituations.get(position - (mAllInjuries.size() + mAllProperties.size()));
               situationViewHolder.date.setText(mSituation.getDate());
               situationViewHolder.description.setText(mSituation.getDescription());
               break;
       }

    }

    @Override
    public int getItemCount() {
        return this.mAllInjuries.size() + mAllProperties.size() + mAllSituations.size();
    }

    public void setInjuryForms(List<Injury> p_injuries) {
        this.mAllInjuries = p_injuries;
        notifyDataSetChanged();
    }

    public void setPropertyForms(List<Property> p_properties) {
        this.mAllProperties = p_properties;
        notifyDataSetChanged();
    }

    public void setSituationForms(List<Situation> p_situations) {
        this.mAllSituations = p_situations;
        notifyDataSetChanged();
    }

    public class InjuryViewHolder extends RecyclerView.ViewHolder {
        public TextView name;
        public TextView date;
        public TextView description;
        public View lyt_parent;

        public InjuryViewHolder(View v) {
            super(v);
            name = v.findViewById(R.id.name);
            date = v.findViewById(R.id.date);
            description = v.findViewById(R.id.description);
            lyt_parent = (CoordinatorLayout)v.findViewById(R.id.lyt_parent);

        /*    lyt_parent.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view) {
                }
            }); */
        }
    }

    public class PropertyViewHolder extends RecyclerView.ViewHolder {
        public TextView name;
        public TextView date;
        public TextView description;
        public View lyt_parent;

        public PropertyViewHolder(View v) {
            super(v);
            name = v.findViewById(R.id.name);
            date = v.findViewById(R.id.date);
            description = v.findViewById(R.id.description);
            lyt_parent = v.findViewById(R.id.lyt_parent);
        }

    }

    public class SituationViewHolder extends RecyclerView.ViewHolder {
        public TextView date;
        public TextView description;
        public View lyt_parent;

        public SituationViewHolder(View v) {
            super(v);
            date = v.findViewById(R.id.date);
            description = v.findViewById(R.id.description);
            lyt_parent = v.findViewById(R.id.lyt_parent);
        }
    }

   public interface OnItemClickListener {
        void onItemClick(View view, Injury obj, int position);
    }

    public void setOnItemClickListener(final OnItemClickListener p_itemClickListener) {
        this.mOnItemClickListener = p_itemClickListener;
    }

}
