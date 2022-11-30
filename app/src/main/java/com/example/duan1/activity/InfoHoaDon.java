package com.example.duan1.activity;

import static com.example.duan1.utilities.Constants.BOARD_ID;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.os.Bundle;
import android.widget.TextView;

import com.example.duan1.Dao.BillDao;
import com.example.duan1.Dao.DoanhThuDAO;
import com.example.duan1.Dao.FoodBoardDAO;
import com.example.duan1.Model.Food;
import com.example.duan1.Model.FoodBoard;
import com.example.duan1.R;
import com.example.duan1.adapter.InfoAdapter;

import java.util.List;

public class InfoHoaDon extends AppCompatActivity {
    private FoodBoardDAO foodBoardDAO;
    private TextView tv_number_board, tv_price_bill,tv_datebill;
    private List<Food> listFood;
    private DoanhThuDAO doanhThuDAO;
    private BillDao billDao;
    private List<FoodBoard> listFoodBoard;
    RecyclerView Recy_info;
    private InfoAdapter adapter;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_info_hoa_don);
        tv_number_board = findViewById(R.id.tv_number_board);
        tv_datebill= findViewById(R.id.tv_datebill);
        tv_price_bill= findViewById(R.id.tv_price_bill);
        Recy_info= findViewById(R.id.Recy_info);
        foodBoardDAO = new FoodBoardDAO(this);

        tv_number_board.setText(getIntent().getStringExtra("numberBoard"));
        tv_price_bill.setText(getIntent().getStringExtra("billPrice"));
        tv_datebill.setText(getIntent().getStringExtra("billDate"));

        listFoodBoard = foodBoardDAO.getFoodByBoardId(BOARD_ID);
        adapter = new InfoAdapter(InfoHoaDon.this, listFoodBoard);
        Recy_info.setLayoutManager(new LinearLayoutManager(this));
        Recy_info.setAdapter(adapter);

    }
}