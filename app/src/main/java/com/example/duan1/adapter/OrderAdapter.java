package com.example.duan1.adapter;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.CheckBox;
import android.widget.Filter;
import android.widget.Filterable;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.example.duan1.Dao.FoodBoardDAO;
import com.example.duan1.Model.FoodBoard;
import com.example.duan1.R;

import java.util.ArrayList;
import java.util.List;

public class OrderAdapter extends RecyclerView.Adapter<OrderAdapter.OrderViewHolder> implements Filterable {

    private Context context;
    private List<FoodBoard> listFoodBoard;
    private List<FoodBoard> listFoodBoardOld;
    private FoodBoardDAO foodBoardDAO;

    public OrderAdapter(Context context, List<FoodBoard> listFoodBoard) {
        this.context = context;
        this.listFoodBoard = listFoodBoard;
        this.listFoodBoardOld = listFoodBoard;
        this.foodBoardDAO = new FoodBoardDAO(context);
    }

    @NonNull
    @Override
    public OrderViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        LayoutInflater inf = LayoutInflater.from(parent.getContext());
        View view = inf.inflate(R.layout.item_order, parent, false);
        return new OrderViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull OrderViewHolder holder, int position) {
        FoodBoard foodBoard = listFoodBoard.get(position);
        holder.tvFoodName.setText(foodBoard.getFoodName());
        holder.tvBoardNumber.setText("Bàn số " + foodBoard.getBoardNumber());
        if (foodBoard.getFoodBoardStatus() == 2) {
            holder.chkOrder.setChecked(true);
        }else {
            holder.chkOrder.setChecked(false);
        }

        holder.chkOrder.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (holder.chkOrder.isChecked()){
                    foodBoardDAO.updateStatus(foodBoard.getFoodBoardId(), 2);
                }else {
                    foodBoardDAO.updateStatus(foodBoard.getFoodBoardId(), 1);
                }
            }
        });

        holder.itemView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                holder.chkOrder.toggle();
                if (holder.chkOrder.isChecked()){
                    foodBoardDAO.updateStatus(foodBoard.getFoodBoardId(), 2);
                }else {
                    foodBoardDAO.updateStatus(foodBoard.getFoodBoardId(), 1);
                }
            }
        });
    }

    @Override
    public int getItemCount() {
        return listFoodBoard.size();
    }

    @Override
    public Filter getFilter() {
        return new Filter() {
            @Override
            protected FilterResults performFiltering(CharSequence constraint) {
                String search = constraint.toString();
                if (search.isEmpty()){
                    listFoodBoard = listFoodBoardOld;
                }else {
                    List<FoodBoard> list = new ArrayList<>();
                    for (FoodBoard item : listFoodBoardOld) {
                        if (item.getFoodName().toLowerCase().contains(search.toLowerCase())){
                            list.add(item);
                        }
                    }
                    listFoodBoard = list;
                }
                FilterResults filterResults = new FilterResults();
                filterResults.values = listFoodBoard;

                return filterResults;
            }

            @Override
            protected void publishResults(CharSequence constraint, FilterResults results) {
                listFoodBoard = (List<FoodBoard>) results.values;
                notifyDataSetChanged();
            }
        };
    }

    public class OrderViewHolder extends RecyclerView.ViewHolder{

        protected TextView tvFoodName, tvBoardNumber;
        protected CheckBox chkOrder;

        public OrderViewHolder(@NonNull View itemView) {
            super(itemView);
            tvFoodName = itemView.findViewById(R.id.tvFoodName);
            tvBoardNumber = itemView.findViewById(R.id.tvBoardNumber);
            chkOrder = itemView.findViewById(R.id.chkOrder);
        }
    }
}
