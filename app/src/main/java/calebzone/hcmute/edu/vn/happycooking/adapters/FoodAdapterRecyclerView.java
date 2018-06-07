package calebzone.hcmute.edu.vn.happycooking.adapters;

import android.content.Context;
import android.support.annotation.NonNull;
import android.support.v7.widget.RecyclerView;
import android.text.Layout;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.ImageView;
import android.widget.TextView;

import java.util.ArrayList;
import java.util.List;

import calebzone.hcmute.edu.vn.happycooking.R;
import calebzone.hcmute.edu.vn.happycooking.model.Food;

public class FoodAdapterRecyclerView extends RecyclerView.Adapter<FoodAdapterRecyclerView.ViewHolder> {

    private Context currentContext;
    private int currentLayout;
    private ArrayList<Food> foodList;

    public FoodAdapterRecyclerView(Context currentContext, int currentLayout, ArrayList<Food> foodList) {
        this.currentContext = currentContext;
        this.currentLayout = currentLayout;
        this.foodList = foodList;
    }

    @NonNull
    @Override
    public ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        LayoutInflater layoutInflater = LayoutInflater.from(parent.getContext());
        View view = layoutInflater.inflate(currentLayout, parent, false);
        /* cách code 2
        view = LayoutInflater.from(parent.getContext())
                    .inflate(currentLayout,parent,false);*/
        return new ViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull ViewHolder holder, int position) {

        holder.txtTileFood.setText(foodList.get(position).getTitle());
        holder.txtTypeFood.setText(foodList.get(position).getType());
        holder.imgFood.setImageResource(foodList.get(position).getImage());
    }

    @Override
    public int getItemCount() {
        return foodList.size();
    }

    public class ViewHolder extends RecyclerView.ViewHolder {
        TextView txtTileFood;
        TextView txtTypeFood;
        ImageView imgFood;

        public ViewHolder(View itemView) {
            super(itemView);
            txtTileFood = (TextView) itemView.findViewById(R.id.title_food);
            txtTypeFood = (TextView) itemView.findViewById(R.id.typeFood);
            imgFood = (ImageView) itemView.findViewById(R.id.image_food);
        }
    }

}
