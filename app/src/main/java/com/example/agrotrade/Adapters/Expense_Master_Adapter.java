package com.example.agrotrade.Adapters;

import android.app.Activity;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.TextView;

import com.example.agrotrade.Model.Expense;
import com.example.agrotrade.Model.Expense_MasterList_Model;
import com.example.agrotrade.R;

import java.util.ArrayList;

public class Expense_Master_Adapter extends ArrayAdapter<Expense_MasterList_Model> {
    Activity activity;
    int layoutResourceId;
    ArrayList<Expense_MasterList_Model> data=new ArrayList<Expense_MasterList_Model>();
    Expense_MasterList_Model expense;

    public Expense_Master_Adapter(Activity activity, int layoutResourceId, ArrayList<Expense_MasterList_Model> data) {
        super(activity, layoutResourceId, data);
        this.activity=activity;
        this.layoutResourceId=layoutResourceId;
        this.data=data;
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        View row=convertView;
        ExpenseHolder holder=null;
        if(row==null)
        {
            LayoutInflater inflater= LayoutInflater.from(activity);
            row=inflater.inflate(layoutResourceId,parent,false);
            holder=new ExpenseHolder();

            String expense_id,expense_date,expense_f_id,expense_pro_id,product_id,product_crop_name,
                    total_ferti_cost,total_p_cost,total_half_no_worker,total_half_sal,
                    total_full_no_worker,total_full_sal,total_extra_cost,total_total_income;
            holder.expense_date= (TextView) row.findViewById(R.id.expense_date);
            holder.product_crop_name=(TextView)row.findViewById(R.id.product_crop_name);
            holder.total_ferti_cost=(TextView)row.findViewById(R.id.total_ferti_cost);
            holder.total_p_cost=(TextView)row.findViewById(R.id.total_p_cost);
            holder.total_half_no_worker=(TextView)row.findViewById(R.id.total_half_no_worker);
            holder.total_half_sal=(TextView)row.findViewById(R.id.total_half_sal);
            holder.total_full_no_worker=(TextView)row.findViewById(R.id.total_full_no_worker);
            holder.total_full_sal=(TextView)row.findViewById(R.id.total_full_sal);
            holder.total_extra_cost=(TextView)row.findViewById(R.id.total_extra_cost);
            holder.total_total_income=(TextView)row.findViewById(R.id.total_total_income);

            row.setTag(holder);
        }
        else
        {
            holder= (Expense_Master_Adapter.ExpenseHolder) row.getTag();
        }

        expense = data.get(position);

String expense_id,expense_date,expense_f_id,expense_pro_id,product_id,product_crop_name,
        total_ferti_cost,total_p_cost,total_half_no_worker,total_half_sal,
        total_full_no_worker,total_full_sal,total_extra_cost,total_total_income;
        holder.expense_date.setText(expense.getExpense_date());
        holder.product_crop_name.setText(expense.getProduct_crop_name());
        holder.total_ferti_cost.setText(expense.getTotal_ferti_cost());
        holder.total_p_cost.setText(expense.getTotal_p_cost());
        holder.total_half_no_worker.setText(expense.getTotal_half_no_worker());
        holder.total_half_sal.setText(expense.getTotal_half_sal());
        holder.total_full_no_worker.setText(expense.getTotal_full_no_worker());
        holder.total_full_sal.setText(expense.getTotal_full_sal());
        holder.total_extra_cost.setText(expense.getTotal_extra_cost());
        holder.total_total_income.setText(expense.getTotal_total_income());

       /* Picasso.get()
                .load(crop_info.getImg())
                .placeholder(R.mipmap.ic_launcher)
                .resize(200,200)
                .into(holder.img); */

        return row;
    }
    class ExpenseHolder{
        TextView expense_id,expense_date,expense_f_id,expense_pro_id,product_id,product_crop_name,total_ferti_cost,total_p_cost,total_half_no_worker,total_half_sal,total_full_no_worker,total_full_sal,total_extra_cost,total_total_income;    }
}
