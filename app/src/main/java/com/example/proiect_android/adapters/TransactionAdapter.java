package com.example.proiect_android.adapters;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.example.proiect_android.R;
import com.example.proiect_android.beans.Transaction;

import java.util.ArrayList;
import java.util.List;

public class TransactionAdapter extends RecyclerView.Adapter<TransactionAdapter.TransactionHolder> {
    private List<Transaction> transactions = new ArrayList<>();

    @NonNull
    @Override
    public TransactionHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View itemView = LayoutInflater.from(parent.getContext())
                .inflate(R.layout.transaction_item, parent, false);
        return new TransactionHolder(itemView);
    }

    @Override
    public void onBindViewHolder(@NonNull TransactionHolder holder, int position) {
        Transaction currentTransaction = transactions.get(position);
        holder.textViewCategory.setText(currentTransaction.getCategoryId());
        holder.textViewAmount.setText(currentTransaction.getAmount()+"");
        holder.textViewDescription.setText(currentTransaction.getDescription());
    }

    @Override
    public int getItemCount() {
        return transactions.size();
    }

    public void setTransactions(List<Transaction> transactions){
        this.transactions = transactions;
        notifyDataSetChanged();
    }

    class TransactionHolder extends RecyclerView.ViewHolder {
        private TextView textViewCategory;
        private TextView textViewDescription;
        private TextView textViewAmount;


        public TransactionHolder(@NonNull View itemView) {
            super(itemView);
            textViewCategory = itemView.findViewById(R.id.text_category);
            textViewDescription = itemView.findViewById(R.id.text_description);
            textViewAmount = itemView.findViewById(R.id.text_amount);
        }
    }
}
