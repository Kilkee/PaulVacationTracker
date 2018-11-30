package com.example.delaney.paulvacationtracker;

import android.support.annotation.NonNull;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

public class EmployeeVacationAdapter extends RecyclerView.Adapter<EmployeeVacationAdapter.EmployeeVacationViewHolder>{

    private TextView mNameTextView;
    private TextView mStartDateTextView;
    private TextView mEndDateTextView;
    private TextView mReasonTextView;

    @NonNull
    @Override
    public EmployeeVacationViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View itemView = LayoutInflater.from(parent.getContext()).inflate(R.layout.employeevacation_itemview,parent,false);
        return new EmployeeVacationViewHolder(itemView);
    }

    @Override
    public void onBindViewHolder(@NonNull EmployeeVacationViewHolder employeeVacationViewHolder, int i) {

    }

    @Override
    public int getItemCount() {
        return 0;
    }

    class EmployeeVacationViewHolder extends RecyclerView.ViewHolder{

        public EmployeeVacationViewHolder(@NonNull View itemView) {
            super(itemView);
            mNameTextView = itemView.findViewById(R.id.itemview_employeename);
            mStartDateTextView = itemView.findViewById(R.id.itemview_start_date);
            mEndDateTextView = itemView.findViewById(R.id.itemview_end_date);
            mReasonTextView = itemView.findViewById(R.id.itemview_reason);
        }
    }
}
