package com.example.credence.Activities;

import android.os.Bundle;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.AutoCompleteTextView;
import android.widget.Toast;

import androidx.activity.EdgeToEdge;
import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.AppCompatButton;
import androidx.appcompat.widget.Toolbar;
import androidx.core.graphics.Insets;
import androidx.core.view.ViewCompat;
import androidx.core.view.WindowInsetsCompat;

import com.example.credence.R;
import com.example.credence.databinding.ActivityAddExpenseBinding;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.firestore.FirebaseFirestore;


import java.util.Calendar;
import java.util.UUID;

public class AddExpenseActivity extends AppCompatActivity {

    AppCompatButton save_expense;
    ActivityAddExpenseBinding binding;
    private ExpenseModel expenseModel;
    private String type;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        EdgeToEdge.enable(this);
        binding = ActivityAddExpenseBinding.inflate(getLayoutInflater());
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
            binding.editTextAddExpenseAmount.setText(String.valueOf(expenseModel.getExpense_amount()));
            binding.autoCompleteTextViewExpense.setText(expenseModel.getExpense_category());
            binding.editTextAddExpenseNote.setText(expenseModel.getExpense_note());
        }

        //Dropdown menu for expense category in activity_add_expense.xml file
        String[] expense = getResources().getStringArray(R.array.expense_category);
        ArrayAdapter<String> arrayAdapter = new ArrayAdapter<>(this, R.layout.dropdown_item, expense);
        AutoCompleteTextView autocompleteTVe = (AutoCompleteTextView)findViewById(R.id.autoCompleteTextView_expense);
        autocompleteTVe.setAdapter(arrayAdapter);

        save_expense = findViewById(R.id.save_button_expense);
        //If we are creating new field it will go to createExpense() method else it will go to createExpense() method
        if (expenseModel == null){
            save_expense.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view) {
                    createExpense();
                }
            });
        }
        else {
            save_expense.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view) {
                    updateExpense();
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

    public boolean onOptionsItemSelected(@NonNull MenuItem item) {
        int id = item.getItemId();

        deleteExpense();
        return true;

    }
//    public boolean onOptionsItemSelected(@NonNull MenuItem item) {
//        int id = item.getItemId();
//        if(id == R.id.delete_expense){
//            deleteExpense();
//            return true;
//        }
//        return false;
//    }

    private void createExpense() {

        String expenseId = UUID.randomUUID().toString();
        String expense_amount = binding.editTextAddExpenseAmount.getText().toString();
        String expense_note = binding.editTextAddExpenseNote.getText().toString();
        String expense_category = binding.autoCompleteTextViewExpense.getText().toString();
        String type = "Expense";

        if (expense_amount.isEmpty()){
            binding.editTextAddExpenseAmount.setError("Amount is empty");
            return;
        }

        if (expense_category.isEmpty()){
            binding.autoCompleteTextViewExpense.setError("Select category");
            return;
        }

        ExpenseModel expenseModel = new ExpenseModel(expenseId, expense_note, expense_category, Long.parseLong(expense_amount), Calendar.getInstance().getTimeInMillis(), type, FirebaseAuth.getInstance().getUid());

        FirebaseFirestore
                .getInstance()
                .collection("expense")
                .document(expenseId)
                .set(expenseModel);
        finish();

    }

    private void updateExpense() {

        String expenseId = expenseModel.getExpenseId();
        String expense_amount = binding.editTextAddExpenseAmount.getText().toString();
        String expense_note = binding.editTextAddExpenseNote.getText().toString();
        String expense_category = binding.autoCompleteTextViewExpense.getText().toString();
        type = "Expense";

        if (expense_amount.isEmpty()){
            binding.editTextAddExpenseAmount.setError("Amount is empty");
            return;
        }

        if (expense_category.isEmpty()){
            binding.autoCompleteTextViewExpense.setError("Select category");
            return;
        }

        ExpenseModel model = new ExpenseModel(expenseId, expense_note, expense_category, Long.parseLong(expense_amount), expenseModel.getTime(), type, FirebaseAuth.getInstance().getUid());

        FirebaseFirestore
                .getInstance()
                .collection("expense")
                .document(expenseId)
                .set(model);
        finish();
    }

    private void deleteExpense(){
        FirebaseFirestore
                .getInstance()
                .collection("expense")
                .document(expenseModel.getExpenseId())
                .delete()
                .addOnSuccessListener(aVoid -> {
                    // Inform the user of successful deletion
                    Toast.makeText(this, "Expense deleted successfully", Toast.LENGTH_SHORT).show();
                    finish(); // Close the activity
                });
        finish();
    }
}
