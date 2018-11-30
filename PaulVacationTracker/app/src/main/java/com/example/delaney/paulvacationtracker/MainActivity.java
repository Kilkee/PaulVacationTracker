package com.example.delaney.paulvacationtracker;

import android.content.DialogInterface;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.design.widget.FloatingActionButton;
import android.support.design.widget.Snackbar;
import android.support.v7.app.AlertDialog;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.Toolbar;
import android.util.Log;
import android.view.View;
import android.view.Menu;
import android.view.MenuItem;
import android.widget.TextView;

import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.OnFailureListener;
import com.google.android.gms.tasks.OnSuccessListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.firestore.DocumentReference;
import com.google.firebase.firestore.DocumentSnapshot;
import com.google.firebase.firestore.FirebaseFirestore;
import com.google.firebase.firestore.QuerySnapshot;

import java.util.HashMap;
import java.util.Map;

public class MainActivity extends AppCompatActivity {

    public static final String TAG = "EV";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);

        RecyclerView recyclerView = findViewById(R.id.recycler_view);
        recyclerView.setLayoutManager(new LinearLayoutManager(this));
        recyclerView.setHasFixedSize(true);

        EmployeeVacationAdapter movieQuoteAdapter = new EmployeeVacationAdapter();
        recyclerView.setAdapter(movieQuoteAdapter);

        // Temp Firebase testing area
//        final FirebaseFirestore db = FirebaseFirestore.getInstance();
//        db.collection("PaulVacationTracker")
//                .get()
//                .addOnCompleteListener(new OnCompleteListener<QuerySnapshot>() {
//                    @Override
//                    public void onComplete(@NonNull Task<QuerySnapshot> task) {
//                        if (task.isSuccessful()) {
//                            for (DocumentSnapshot document : task.getResult()) {
//                                Log.d(TAG, document.getId() + " => " + document.getData());
//                            }
//                        } else {
//                            Log.w(TAG, "Error getting documents.", task.getException());
//                        }
//                    }
//                });


        FloatingActionButton fab = (FloatingActionButton) findViewById(R.id.fab);
        fab.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                showAddDialog();


                // Create a new emp with a first and last name
//                Map<String, Object> emp = new HashMap<>();
//                emp.put("employee name", "John");
//                emp.put("Start Date", "Jan");
//                emp.put("End Date", "Feb");
//                emp.put("Reason", "Disney");
//
//              //   Add a new document with a generated ID
//                db.collection("PaulVacationTracker")
//                        .add(emp);
            }
        });
    }

    private void showAddDialog() {
        AlertDialog.Builder builder = new AlertDialog.Builder(this);
        View view = getLayoutInflater().inflate(R.layout.employeevacation_dialog, null, false);
        builder.setView(view);
        builder.setTitle("Add a vacation");

        final TextView employeeEditText = view.findViewById(R.id.dialog_employee_edittext);
        final TextView startDateEditText = view.findViewById(R.id.dialog_startdate_edittext);
        final TextView endDateEditText = view.findViewById(R.id.dialog_enddate_edittext);
        final TextView reasonEditText = view.findViewById(R.id.dialog_reason_edittext);


        builder.setPositiveButton(android.R.string.ok, new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialog, int i) {

                Map<String, Object> emp = new HashMap<>();
                emp.put(Constants.KEY_EMPLOYEE, employeeEditText.getText().toString());
                emp.put(Constants.KEY_STARTDATE, startDateEditText.getText().toString());
                emp.put(Constants.KEY_ENDDATE, endDateEditText.getText().toString());
                emp.put(Constants.KEY_REASON, reasonEditText.getText().toString());

                //emp.put(Constants.KEY_CREATED, new Date());
                FirebaseFirestore.getInstance().collection(Constants.COLLECTION_PATH).add(emp);
            }
        });





        builder.setNegativeButton(android.R.string.cancel, null);
        builder.create().show();

    }


}
