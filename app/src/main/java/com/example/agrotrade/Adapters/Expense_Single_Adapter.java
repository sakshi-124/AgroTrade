package com.example.agrotrade.Adapters;

import android.app.Activity;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.TextView;

import com.example.agrotrade.Model.Expense_MasterList_Model;
import com.example.agrotrade.R;

import java.util.ArrayList;

public class Expense_Single_Adapter extends ArrayAdapter<Expense_MasterList_Model> {
    Activity activity;
    int layoutResourceId;
    ArrayList<Expense_MasterList_Model> data=new ArrayList<Expense_MasterList_Model>();
    Expense_MasterList_Model expense;

    public Expense_Single_Adapter(Activity activity, int layoutResourceId, ArrayList<Expense_MasterList_Model> data) {
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



            holder.expense_date= (TextView) row.findViewById(R.id.expense_date);
            holder.expense_f_cost=(TextView)row.findViewById(R.id.expense_f_cost);
            holder.expense_p_cost=(TextView)row.findViewById(R.id.expense_p_cost);
            holder.expense_half_no_worker=(TextView)row.findViewById(R.id.expense_half_no_worker);
            holder.expense_half_sal=(TextView)row.findViewById(R.id.expense_half_sal);
            holder.expense_full_no_worker=(TextView)row.findViewById(R.id.expense_full_no_worker);
            holder.expense_full_sal=(TextView)row.findViewById(R.id.expense_full_sal);
            holder.expense_extra_cost=(TextView)row.findViewById(R.id.expense_extra_cost);
            holder.expense_total_income=(TextView)row.findViewById(R.id.expense_total_income);

            row.setTag(holder);
        }
        else
        {
            holder= (Expense_Single_Adapter.ExpenseHolder) row.getTag();
        }

        expense = data.get(position);

        String expense_date,expense_pro_id,expense_f_cost,expense_p_cost,expense_half_no_worker,
                expense_half_sal, expense_full_no_worker,expense_full_sal,expense_extra_cost,expense_total_income;
        holder.expense_date.setText(expense.getExpense_date());
        holder.expense_f_cost.setText(expense.getExpense_f_cost());
        holder.expense_p_cost.setText(expense.getExpense_p_cost());
        holder.expense_half_no_worker.setText(expense.getExpense_half_no_worker());
        holder.expense_half_sal.setText(expense.getExpense_half_sal());
        holder.expense_full_no_worker.setText(expense.getExpense_full_no_worker());
        holder.expense_full_sal.setText(expense.getExpense_full_sal());
        holder.expense_extra_cost.setText(expense.getExpense_extra_cost());
        holder.expense_total_income.setText(expense.getExpense_total_income());


       /* Picasso.get()
                .load(crop_info.getImg())
                .placeholder(R.mipmap.ic_launcher)
                .resize(200,200)
                .into(holder.img); */

        return row;
    }
    class ExpenseHolder{
        TextView expense_date,expense_pro_id,expense_f_cost,expense_p_cost,expense_half_no_worker,expense_half_sal, expense_full_no_worker,expense_full_sal,expense_extra_cost,expense_total_income;
    }
}
