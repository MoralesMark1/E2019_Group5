package com.group5.finalproject;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;
import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import java.util.List;

public class ClassesAdapter extends RecyclerView.Adapter <ClassesAdapter.ClassesVH> {

    List<String> items;

    public ClassesAdapter(List<String> items) {
        this.items = items;
    }

    @NonNull
    @Override
    public ClassesVH onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext())
                .inflate(R.layout.classes, parent, false);
        return new ClassesVH(view).linkAdapter(this);
    }

    @Override
    public void onBindViewHolder(@NonNull ClassesVH holder, int position) {
        holder.textView.setText(items.get(position));
    }

    @Override
    public int getItemCount() {
        return items.size();
    }

    class ClassesVH extends RecyclerView.ViewHolder{

        TextView textView;
        private ClassesAdapter adapter;

        public ClassesVH(@NonNull View itemView) {
            super(itemView);

            textView = itemView.findViewById(R.id.textView);
            itemView.findViewById(R.id.delete).setOnClickListener(view -> {
                adapter.items.remove(getAdapterPosition());
                adapter.notifyItemRemoved(getAdapterPosition());

            });
        }

        public ClassesVH linkAdapter(ClassesAdapter adapter){
            this.adapter = adapter;
            return this;
        }
    }
}
