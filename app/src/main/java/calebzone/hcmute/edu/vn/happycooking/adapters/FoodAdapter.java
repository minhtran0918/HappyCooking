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

public class FoodAdapter extends BaseAdapter {

    private Context currentContext;
    private int layout;
    private List<Food> foodList;

    public FoodAdapter(Context currentContext, int layout, List<Food> foodList) {
        this.currentContext = currentContext;
        this.layout = layout;
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

        //Truyền nội dung của context
        LayoutInflater layoutInflater = (LayoutInflater) currentContext
                .getSystemService(Context.LAYOUT_INFLATER_SERVICE);

        convertView = layoutInflater.inflate(layout,null);


        //region Referenced Component
        TextView titleFood = (TextView) convertView.findViewById(R.id.title_food);
        TextView typeFood = (TextView) convertView.findViewById(R.id.typeFood);
        ImageView imageFood = (ImageView) convertView.findViewById(R.id.image_food);
        //endregion

        //Truyền dữ liệu
        Food currentFood = foodList.get(position);
        titleFood.setText(currentFood.getTitle());
        typeFood.setText(currentFood.getType());
        imageFood.setImageResource(currentFood.getImage());

        return convertView;
    }
}
