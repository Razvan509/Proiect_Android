package com.example.proiect_android.beans;

import androidx.annotation.NonNull;
import androidx.room.ColumnInfo;
import androidx.room.Entity;
import androidx.room.ForeignKey;
import androidx.room.PrimaryKey;
import androidx.room.TypeConverter;

import java.util.Date;


@Entity(tableName = "transactions", foreignKeys =
        {//@ForeignKey(entity = User.class, parentColumns = "id", childColumns = "user_id")
               @ForeignKey(entity = Category.class, parentColumns = "id", childColumns = "category_id")})
public class Transaction {
    @PrimaryKey(autoGenerate = true)
    @NonNull
    private int id;

    @ColumnInfo(name = "transaction_date")
    @NonNull
    private Date transactionDate;

    @ColumnInfo(name = "description")
    @NonNull
    private String description;

    @ColumnInfo(name = "amount")
    @NonNull
    private float amount;

    @ColumnInfo(name = "user_id")
    private int userId;

    @ColumnInfo(name = "category_id")
    @NonNull
    private int categoryId;

    public Transaction(String description, float amount, int categoryId, Date transactionDate){
        this.description = description;
        this.amount = amount;
        this.categoryId = categoryId;
        this.transactionDate = transactionDate;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    @NonNull
    public Date getTransactionDate() {
        return transactionDate;
    }

    public void setTransactionDate(@NonNull Date transactionDate) {
        this.transactionDate = transactionDate;
    }

    @NonNull
    public String getDescription() {
        return description;
    }

    public void setDescription(@NonNull String description) {
        this.description = description;
    }

    public float getAmount() {
        return amount;
    }

    public void setAmount(float amount) {
        this.amount = amount;
    }

    public int getUserId() {
        return userId;
    }

    public void setUserId(int userId) {
        this.userId = userId;
    }

    public int getCategoryId() {
        return categoryId;
    }

    public void setCategoryId(int categoryId) {
        this.categoryId = categoryId;
    }
}
