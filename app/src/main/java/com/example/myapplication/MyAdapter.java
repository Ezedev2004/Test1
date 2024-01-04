// MyAdapter.java
package com.example.myapplication;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.view.animation.Animation;
import android.widget.CheckBox;
import android.widget.LinearLayout;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.cardview.widget.CardView;
import androidx.recyclerview.widget.RecyclerView;

import java.util.ArrayList;

import de.hdodenhof.circleimageview.CircleImageView;

public class MyAdapter extends RecyclerView.Adapter<MyAdapter.MyHolder> {

    private Context context;
    private ArrayList<MyModel> myModel;
    private MainActivity mainActivity;
    private boolean isActionMode;
    private ArrayList<MyModel> selectedItems;

    public MyAdapter(Context context, ArrayList<MyModel> myModel, MainActivity mainActivity) {
        this.context = context;
        this.myModel = myModel;
        this.mainActivity = mainActivity;
        this.isActionMode = false;
        this.selectedItems = new ArrayList<>();
    }

    public void setActionMode(boolean isActionMode, ArrayList<MyModel> selectedItems) {
        this.isActionMode = isActionMode;
        this.selectedItems = selectedItems;
        notifyDataSetChanged();
    }

    @NonNull
    @Override
    public MyHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View v = LayoutInflater.from(parent.getContext()).inflate(R.layout.layout_items, parent, false);
        return new MyHolder(v);
    }

    @Override
    public void onBindViewHolder(@NonNull MyHolder holder, int position) {
        holder.textView.setText(myModel.get(position).getText());
        holder.imageView.setImageResource(myModel.get(position).getPhoto());

        if (isActionMode) {
            holder.checkBox.setVisibility(View.VISIBLE);

            if (position == 0) {
                holder.checkboxAll.setVisibility(View.VISIBLE);
                holder.checkboxAll.setOnCheckedChangeListener((buttonView, isChecked) -> {
                    if (isChecked) {
                        // Cochez tous les autres CheckBox
                        for (MyModel model : myModel) {
                            model.setSelected(true);
                        }
                    } else {
                        // Décochez tous les autres CheckBox
                        for (MyModel model : myModel) {
                            model.setSelected(false);
                        }
                    }
                    notifyDataSetChanged();
                });
            } else {
                holder.checkboxAll.setVisibility(View.GONE);
            }

            if (myModel.get(position).isSelected()) {
                Animation anim = new MainActivity.Anim(500, holder.linearLayout);
                anim.setDuration(300);
                holder.linearLayout.startAnimation(anim);
            } else {
                holder.linearLayout.clearAnimation();
            }
        } else {
            holder.linearLayout.clearAnimation();
            holder.checkBox.setVisibility(View.GONE);
            holder.checkboxAll.setVisibility(View.GONE);
        }

        holder.cardView.setOnLongClickListener(v -> {
            mainActivity.startSelection(position);
            return true;
        });
    }

    @Override
    public int getItemCount() {
        return myModel.size();
    }

    static class MyHolder extends RecyclerView.ViewHolder {
        private CardView cardView;
        private CheckBox checkBox;
        private CheckBox checkboxAll;
        private TextView textView;
        private LinearLayout linearLayout;
        private CircleImageView imageView;

        public MyHolder(@NonNull View itemView) {
            super(itemView);
            textView = itemView.findViewById(R.id.text);
            checkBox = itemView.findViewById(R.id.checkbox);
            checkboxAll = itemView.findViewById(R.id.checkboxAll);
            cardView = itemView.findViewById(R.id.cardview);
            imageView = itemView.findViewById(R.id.imgview);
            linearLayout = itemView.findViewById(R.id.linearLayout);
        }
    }
}
