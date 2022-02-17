package com.group5.finalproject;

import android.view.LayoutInflater;
import android.view.MenuItem;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;
import androidx.annotation.NonNull;
import androidx.appcompat.widget.PopupMenu;
import androidx.recyclerview.widget.RecyclerView;

import java.util.List;

public class ClassesAdapter extends RecyclerView.Adapter <ClassesAdapter.ClassesVH> {

    private final RecyclerViewInterface recyclerViewInterface;

    List<String> items;

    public ClassesAdapter(List<String> items, RecyclerViewInterface recyclerViewInterface) {
        this.items = items;
        this.recyclerViewInterface = recyclerViewInterface;
    }

    @NonNull
    @Override
    public ClassesVH onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext())
                .inflate(R.layout.classes, parent, false);
        return new ClassesVH(view, recyclerViewInterface).linkAdapter(this);
    }

    @Override
    public void onBindViewHolder(@NonNull ClassesVH holder, int position) {
        holder.textView.setText(items.get(position));
    }

    @Override
    public int getItemCount() {
        return items.size();
    }



    public static class ClassesVH extends RecyclerView.ViewHolder implements PopupMenu.OnMenuItemClickListener {

        TextView textView;
        ImageView imageView_more;
        private ClassesAdapter adapter;

        public ClassesVH(@NonNull View itemView, RecyclerViewInterface recyclerViewInterface) {
            super(itemView);

            textView = itemView.findViewById(R.id.textView);
            imageView_more = itemView.findViewById(R.id.imageView_more);

            itemView.findViewById(R.id.imageView_more).setOnClickListener(view -> {
                showPopupMenu(imageView_more);
            });

            itemView.setOnClickListener(new View.OnClickListener() { // ito yung onClickListener para sa mga bawat item ng recycleview
                @Override
                public void onClick(View v) {
                    if (recyclerViewInterface != null){
                        int pos = getAdapterPosition();

                        if (pos != RecyclerView.NO_POSITION){
                            recyclerViewInterface.onItemClick(pos);
                        }

                    }
                }
            });
        }

        public ClassesVH linkAdapter(ClassesAdapter adapter){
            this.adapter = adapter;
            return this;
        }

        private void showPopupMenu(View view){
            PopupMenu popupMenu = new PopupMenu(view.getContext(), view);
            popupMenu.inflate(R.menu.popup_menu);
            popupMenu.setOnMenuItemClickListener(this);
            popupMenu.show();

        }

        public boolean onMenuItemClick(MenuItem item) {
            switch (item.getItemId()){
                case R.id.item_delete:
                    adapter.items.remove(getAdapterPosition());
                    adapter.notifyItemRemoved(getAdapterPosition());
                    return true;

                default:
                    return false;
            }
        }
    }

}
