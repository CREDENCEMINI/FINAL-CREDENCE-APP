package com.example.credence.Activities;

import android.os.Bundle;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.AutoCompleteTextView;

import androidx.activity.EdgeToEdge;
import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.AppCompatButton;
import androidx.appcompat.widget.Toolbar;
import androidx.core.graphics.Insets;
import androidx.core.view.ViewCompat;
import androidx.core.view.WindowInsetsCompat;

import com.example.credence.R;
import com.example.credence.databinding.ActivityAddIncomeBinding;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.firestore.FirebaseFirestore;

import java.util.Calendar;
import java.util.UUID;

public class AddIncomeActivity extends AppCompatActivity {

    AppCompatButton save_income;
    ActivityAddIncomeBinding binding;
    private ExpenseModel expenseModel;
    private String type;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        EdgeToEdge.enable(this);
        binding = ActivityAddIncomeBinding.inflate(getLayoutInflater());
        setContentView(binding.getRoot());
        ViewCompat.setOnApplyWindowInsetsListener(findViewById(R.id.main), (v, insets) -> {
            Insets systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars());
            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom);
            return insets;
        });
        Toolbar top_menu = findViewById(R.id.top_menu);
        setSupportActionBar(top_menu);

        type = getIntent().getStringExtra("type");
        expenseModel = (ExpenseModel) getIntent().getSerializableExtra("model");

        if (expenseModel != null){
//        if (type == null){
            binding.editTextAddIncomeAmount.setText(String.valueOf(expenseModel.getExpense_amount()));
            binding.autoCompleteTextViewIncome.setText(expenseModel.getExpense_category());
            binding.editTextAddIncomeNote.setText(expenseModel.getExpense_note());
        }

        //Dropdown menu for income category in activity_add_income.xml file
        String[] income = getResources().getStringArray(R.array.income_category);
        ArrayAdapter<String> arrayAdapter = new ArrayAdapter<>(this, R.layout.dropdown_item, income);
        AutoCompleteTextView autocompleteTVe = (AutoCompleteTextView)findViewById(R.id.autoCompleteTextView_income);
        autocompleteTVe.setAdapter(arrayAdapter);

        //On clicking save button it will perform the following action
        save_income = findViewById(R.id.save_button_income);
        //If we are creating new field it will go to createIncome() method else it will go to createIncome() method
//        if (type != null) {
        if (expenseModel == null){
            save_income.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view) {
                    createIncome();
                }
            });
        }
        else{
            save_income.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view) {
                    updateIncome();
                }
            });
        }
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        MenuInflater menuInflater = getMenuInflater();
        if (expenseModel != null){
            menuInflater.inflate(R.menu.top_menu, menu);
        }
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(@NonNull MenuItem item) {
        int id = item.getItemId();
        if(id == R.id.delete){
            deleteIncome();
            return true;
        }
        return false;
    }

    private void createIncome() {

        String incomeId = UUID.randomUUID().toString();
        String income_amount = binding.editTextAddIncomeAmount.getText().toString();
        String income_note = binding.editTextAddIncomeNote.getText().toString();
        String income_category = binding.autoCompleteTextViewIncome.getText().toString();
        String type = "Income";

        if (income_amount.isEmpty()){
            binding.editTextAddIncomeAmount.setError("Amount is empty");
            return;
        }

        if (income_category.isEmpty()){
            binding.autoCompleteTextViewIncome.setError("Select category");
            return;
        }

        ExpenseModel expenseModel = new ExpenseModel(incomeId, income_note, income_category, Long.parseLong(income_amount), Calendar.getInstance().getTimeInMillis(), type, FirebaseAuth.getInstance().getUid());

        FirebaseFirestore
                .getInstance()
                .collection("expense")
                .document(incomeId)
                .set(expenseModel);
        finish();

    }

    private void updateIncome() {

        String incomeId = expenseModel.getExpenseId();
        String income_amount = binding.editTextAddIncomeAmount.getText().toString();
        String income_note = binding.editTextAddIncomeNote.getText().toString();
        String income_category = binding.autoCompleteTextViewIncome.getText().toString();
        type = "Income";

        if (income_amount.isEmpty()){
            binding.editTextAddIncomeAmount.setError("Amount is empty");
            return;
        }

        if (income_category.isEmpty()){
            binding.autoCompleteTextViewIncome.setError("Select category");
            return;
        }

        ExpenseModel model = new ExpenseModel(incomeId, income_note, income_category, Long.parseLong(income_amount), expenseModel.getTime(), type, FirebaseAuth.getInstance().getUid());

        FirebaseFirestore
                .getInstance()
                .collection("expense")
                .document(incomeId)
                .set(model);
        finish();

    }

    private void deleteIncome(){
        FirebaseFirestore
                .getInstance()
                .collection("expense")
                .document(expenseModel.getExpenseId())
                .delete();
        finish();
    }
}