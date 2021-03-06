package calebzone.hcmute.edu.vn.happycooking.adapters;

import android.app.Activity;
import android.app.ActivityOptions;
import android.content.Context;
import android.content.Intent;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.os.Build;
import android.support.annotation.NonNull;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import com.squareup.picasso.Picasso;

import java.util.ArrayList;
import java.util.Locale;

import calebzone.hcmute.edu.vn.happycooking.R;
import calebzone.hcmute.edu.vn.happycooking.activity.RecipeDetailActivity;
import calebzone.hcmute.edu.vn.happycooking.database.model.RecipeModel;
import calebzone.hcmute.edu.vn.happycooking.fragments.RecipeListFragment;

public class RecipeListAdapter extends RecyclerView.Adapter<RecipeListAdapter.RecipeViewHolder> {

    private Context currentContext;
    private int currentLayout;
    private ArrayList<RecipeModel> recipeList;
    private ArrayList<RecipeModel> recipeListBase;

    public RecipeListAdapter(Context currentContext, int currentLayout, ArrayList<RecipeModel> recipeList) {
        this.currentContext = currentContext;
        this.currentLayout = currentLayout;
        this.recipeList = recipeList;
        this.recipeListBase = new ArrayList<RecipeModel>();
        this.recipeListBase.addAll(recipeList);
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

        holder.txtTileRecipe.setText(recipeList.get(position).getName());
        Picasso.get()
                .load(recipeList.get(position).getImage())
                .placeholder(R.drawable.placeholder_empty)
                .into(holder.imgRecipe);
        holder.setOnItemClickListener(new RecipeViewHolder.OnItemClickListener() {
            @Override
            public void onItemClick(View view, int position) {
                //int id = Integer.getInteger(recipeList.get(position).getId());
                /*Toast.makeText(currentContext, foodList.get(position).getTitle() + " id: " + foodList.get(position).getId(), Toast.LENGTH_SHORT).show();
                RecipeListFragment recipeListFragment = new RecipeListFragment();
                recipeListFragment.startRecipeDetailActivity(view,id);*/
                Intent intent = RecipeDetailActivity.newIntent(currentContext, recipeList.get(position));
                ((Activity) currentContext).startActivity(intent);
                //((Activity) currentContext).overridePendingTransition(R.anim.enter_exit_detail, R.anim.enter_exit_detail);
            }
        });
    }

    @Override
    public int getItemCount() {
        return recipeList.size();
    }

    public static class RecipeViewHolder extends RecyclerView.ViewHolder implements View.OnClickListener {

        TextView txtTileRecipe;
        ImageView imgRecipe;

        private OnItemClickListener onItemClickListener;

        public RecipeViewHolder(View itemView) {
            super(itemView);
            txtTileRecipe = (TextView) itemView.findViewById(R.id.title_recipe);
            imgRecipe = (ImageView) itemView.findViewById(R.id.image_recipe);
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
    public void filter(String charText){
        charText = charText.toLowerCase(Locale.getDefault());
        recipeList.clear();
        if(charText.length() == 0){
            recipeList.addAll(recipeListBase);
        }else{
            for(RecipeModel var : recipeListBase){
                if(var.getName().toLowerCase(Locale.getDefault()).contains(charText)){
                    recipeList.add(var);
                }
            }
        }
        notifyDataSetChanged();
    }
}
