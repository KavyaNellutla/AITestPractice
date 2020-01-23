package com.example.testapplication.Adapter;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.CompoundButton;
import android.widget.Switch;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.example.testapplication.ItemClickListener;
import com.example.testapplication.Model.TitleModel;
import com.example.testapplication.R;
import com.example.testapplication.Utilities.AppUtils;

import java.util.List;

public class TitleRecyclerAdapter extends RecyclerView.Adapter<TitleRecyclerAdapter.ViewHolder> {
    private List<TitleModel> titleModels;
    private int selectedcount = 0;
    private ItemClickListener itemClickListener;

    public TitleRecyclerAdapter(List<TitleModel> titleModels, ItemClickListener itemClickListener) {
        this.titleModels= titleModels;
       this.itemClickListener= itemClickListener;
    }

    @NonNull
    @Override
    public ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.item_recycler_layout,parent,false);
        return new ViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull final ViewHolder holder, final int position) {
        holder.title.setText(titleModels.get(position).getTitle());
        holder.Created_at.setText(AppUtils.getFormattedDate(titleModels.get(position).getDate()));
        holder.selected_switch.setChecked(titleModels.get(position).isSwitchChecked());
        holder.selected_switch.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(CompoundButton buttonView, boolean isChecked) {
                if(isChecked){
                    selectedcount++;
                    titleModels.get(position).setSwitchChecked(true);
                }else{
                    selectedcount--;
                    titleModels.get(position).setSwitchChecked(false);
                }
                itemClickListener.onItemClick(position,selectedcount);
            }
        });
        holder.itemView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                holder.selected_switch.performClick();
            }
        });

    }
@Override
public int getItemViewType(int position){
        return position;
}
    @Override
    public int getItemCount() {
        return titleModels.size();
    }

    public class ViewHolder extends RecyclerView.ViewHolder {
      private   TextView title;
        private TextView Created_at;
        private Switch selected_switch;
        public ViewHolder(@NonNull View itemView) {
            super(itemView);
            title=itemView.findViewById(R.id.Title_text);
            Created_at = itemView.findViewById(R.id.created_date);
            selected_switch= itemView.findViewById(R.id.switch_select);
        }
    }
}
