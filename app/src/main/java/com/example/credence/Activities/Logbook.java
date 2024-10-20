package com.example.credence.Activities;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Toast;

import androidx.activity.EdgeToEdge;
import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.graphics.Insets;
import androidx.core.view.ViewCompat;
import androidx.core.view.WindowInsetsCompat;
import androidx.recyclerview.widget.LinearLayoutManager;

import com.example.credence.R;
import com.example.credence.databinding.ActivityLogbookBinding;
import com.google.android.gms.tasks.OnFailureListener;
import com.google.android.gms.tasks.OnSuccessListener;
import com.google.firebase.auth.AuthResult;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.firestore.DocumentSnapshot;
import com.google.firebase.firestore.FirebaseFirestore;
import com.google.firebase.firestore.QuerySnapshot;

import java.util.List;

public class Logbook extends AppCompatActivity implements OnItemsClick{

    ActivityLogbookBinding binding;
    private ExpenseAdapter expenseAdapter;
    Intent intent_expense;
    Intent intent_income;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        EdgeToEdge.enable(this);
        binding=ActivityLogbookBinding.inflate(getLayoutInflater());
        setContentView(binding.getRoot());
        ViewCompat.setOnApplyWindowInsetsListener(findViewById(R.id.main), (v, insets) -> {
            Insets systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars());
            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom);
            return insets;
        });

            expenseAdapter = new ExpenseAdapter(this, this);
            binding.recycler.setAdapter(expenseAdapter);
            binding.recycler.setLayoutManager(new LinearLayoutManager(this));


            intent_expense = new Intent(Logbook.this, AddExpenseActivity.class);
            intent_income = new Intent(Logbook.this, AddIncomeActivity.class);

            binding.addincome.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view) {
                    intent_income.putExtra("type", "Income");
                    startActivity(intent_income);
                }
            });

            binding.addexpense.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view) {
                    intent_expense.putExtra("type", "Expense");
                    startActivity(intent_expense);

                }
            });


        }

        @Override
        protected void onStart() {
            super.onStart();
            if (FirebaseAuth.getInstance().getCurrentUser() == null){
                FirebaseAuth.getInstance()
                        .signInAnonymously()
                        .addOnSuccessListener(new OnSuccessListener<AuthResult>() {
                            @Override
                            public void onSuccess(AuthResult authResult) {
                            }
                        })
                        .addOnFailureListener(new OnFailureListener() {
                            @Override
                            public void onFailure(@NonNull Exception e) {
                                Toast.makeText(Logbook.this, e.getMessage(), Toast.LENGTH_SHORT).show();
                            }
                        });
            }
        }

        @Override
        protected void onResume() {
            super.onResume();
            getData();
        }

        private void getData(){
            FirebaseFirestore
                    .getInstance()
                    .collection("expense")
                    .whereEqualTo("uid", FirebaseAuth.getInstance().getUid())
                    .get()
                    .addOnSuccessListener(new OnSuccessListener<QuerySnapshot>() {
                        @Override
                        public void onSuccess(QuerySnapshot queryDocumentSnapshots) {
                            expenseAdapter.clear();
                            List<DocumentSnapshot> dsList =queryDocumentSnapshots.getDocuments();
                            for (DocumentSnapshot ds:dsList){
                                ExpenseModel expenseModel = ds.toObject(ExpenseModel.class);
                                expenseAdapter.add(expenseModel);
                            }
                        }
                    });
        }

        @Override
        public void onClick(ExpenseModel expenseModel) {
            if (expenseModel.getType().equals("Income")) {
                intent_income.putExtra("model", expenseModel);
                startActivity(intent_income);
            }
            if (expenseModel.getType().equals("Expense")){
                intent_expense.putExtra("model", expenseModel);
                startActivity(intent_expense);
            }
        }
    }