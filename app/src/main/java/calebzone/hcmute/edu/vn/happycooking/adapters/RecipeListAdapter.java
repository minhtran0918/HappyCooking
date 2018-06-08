package calebzone.hcmute.edu.vn.happycooking.adapters;

import android.app.Activity;
import android.app.ActivityOptions;
import android.content.Context;
import android.content.Intent;
import android.os.Build;
import android.support.annotation.NonNull;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import java.util.ArrayList;

import calebzone.hcmute.edu.vn.happycooking.R;
import calebzone.hcmute.edu.vn.happycooking.activity.RecipeDetailActivity;
import calebzone.hcmute.edu.vn.happycooking.fragments.RecipeListFragment;
import calebzone.hcmute.edu.vn.happycooking.model.Food;

public class RecipeListAdapter extends RecyclerView.Adapter<RecipeListAdapter.RecipeViewHolder> {

    private Context currentContext;
    private int currentLayout;
    private ArrayList<Food> foodList;

    public RecipeListAdapter(Context currentContext, int currentLayout, ArrayList<Food> foodList) {
        this.currentContext = currentContext;
        this.currentLayout = currentLayout;
        this.foodList = foodList;
    }

    @NonNull
    @Override
    public RecipeViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        LayoutInflater layoutInflater = LayoutInflater.from(parent.getContext());
        View view = layoutInflater.inflate(currentLayout, parent, false);
        /* cách code 2
        view = LayoutInflater.from(parent.getContext())
                    .inflate(currentLayout,parent,false);*/
        return new RecipeViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull RecipeViewHolder holder, int position) {

        holder.txtTileFood.setText(foodList.get(position).getTitle());
        holder.txtTypeFood.setText(foodList.get(position).getType());
        holder.imgFood.setImageResource(foodList.get(position).getImage());
        holder.setOnItemClickListener(new RecipeViewHolder.OnItemClickListener() {
            @Override
            public void onItemClick(View view, int position) {
                long id = foodList.get(position).getId();
                /*Toast.makeText(currentContext, foodList.get(position).getTitle() + " id: " + foodList.get(position).getId(), Toast.LENGTH_SHORT).show();
                RecipeListFragment recipeListFragment = new RecipeListFragment();
                recipeListFragment.startRecipeDetailActivity(view,id);*/
                Intent intent = new Intent(currentContext, RecipeDetailActivity.class);
                // extras
                //intent.putExtra(RecipeDetailActivity.EXTRA_RECIPE_ID, id);
                ((Activity) currentContext).startActivity(intent);
            }
        });
    }

    @Override
    public int getItemCount() {
        return foodList.size();
    }

    public static class RecipeViewHolder extends RecyclerView.ViewHolder implements View.OnClickListener {

        TextView txtTileFood;
        TextView txtTypeFood;
        ImageView imgFood;

        private OnItemClickListener onItemClickListener;

        public RecipeViewHolder(View itemView) {
            super(itemView);
            txtTileFood = (TextView) itemView.findViewById(R.id.title_food);
            txtTypeFood = (TextView) itemView.findViewById(R.id.typeFood);
            imgFood = (ImageView) itemView.findViewById(R.id.image_food);
            itemView.setOnClickListener(this);
        }

        public interface OnItemClickListener {
            void onItemClick(View view, int position);
        }

        public void setOnItemClickListener(OnItemClickListener onItemClickListener) {
            this.onItemClickListener = onItemClickListener;
        }

        @Override
        public void onClick(View v) {
            int position = getAdapterPosition();
            if (position != RecyclerView.NO_POSITION) {
                onItemClickListener.onItemClick(v, position);
            }
        }
        //TODO: LongClickListener Here


    }

}
