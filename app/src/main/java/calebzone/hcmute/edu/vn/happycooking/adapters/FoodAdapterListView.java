package calebzone.hcmute.edu.vn.happycooking.adapters;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.ImageView;
import android.widget.TextView;

import java.util.List;

import calebzone.hcmute.edu.vn.happycooking.R;
import calebzone.hcmute.edu.vn.happycooking.model.Food;

public class FoodAdapterListView extends BaseAdapter {

    private Context currentContext;
    private int currentLayout;
    private List<Food> foodList;

    public FoodAdapterListView(Context currentContext, int currentLayout, List<Food> foodList) {
        this.currentContext = currentContext;
        this.currentLayout = currentLayout;
        this.foodList = foodList;
    }

    @Override
    public int getCount() {
        return foodList.size();
    }

    @Override
    public Object getItem(int position) {
        return null;
    }

    @Override
    public long getItemId(int position) {
        return 0;
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {

        ViewHolder viewHolder;
        if (convertView == null) {
            //Truyền nội dung của context
            LayoutInflater layoutInflater = (LayoutInflater) currentContext
                    .getSystemService(Context.LAYOUT_INFLATER_SERVICE);
            convertView = layoutInflater.inflate(currentLayout, null);
            /* Cách code thứ 2
            convertView = LayoutInflater.from(currentContext)
                    .inflate(currentLayout,parent,false);*/

            viewHolder = new ViewHolder();
            //region Referenced Component
            viewHolder.txtTileFood = (TextView) convertView.findViewById(R.id.title_food);
            viewHolder.txtTypeFood = (TextView) convertView.findViewById(R.id.typeFood);
            viewHolder.imgFood = (ImageView) convertView.findViewById(R.id.image_food);
            //endregion
            convertView.setTag(viewHolder);
        }
        else{
            viewHolder = (ViewHolder) convertView.getTag();
        }

        //Gán dữ liệu
        Food currentFood = foodList.get(position);
        viewHolder.txtTileFood.setText(currentFood.getTitle());
        viewHolder.txtTypeFood.setText(currentFood.getType());
        viewHolder.imgFood.setImageResource(currentFood.getImage());

        return convertView;
    }

    private class ViewHolder {
        TextView txtTileFood;
        TextView txtTypeFood;
        ImageView imgFood;
    }
}
