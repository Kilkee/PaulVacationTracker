package com.example.delaney.paulvacationtracker;

import android.content.Intent;
import android.os.Bundle;
import android.support.design.widget.FloatingActionButton;
import android.support.design.widget.Snackbar;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.util.Log;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.TextView;

import com.google.firebase.firestore.DocumentReference;
import com.google.firebase.firestore.DocumentSnapshot;
import com.google.firebase.firestore.EventListener;
import com.google.firebase.firestore.FirebaseFirestore;
import com.google.firebase.firestore.FirebaseFirestoreException;

public class EmployeeVacationDetailActivity extends AppCompatActivity {

    private DocumentReference mDocRef;
    private DocumentSnapshot mDocSnapshot;
    private TextView mEmployeeTextView;
    private TextView mStartdateTextView;
    private TextView mEnddateTextView;
    private TextView mReasonTextView;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_employee_vacation_detail);
        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);

        mEmployeeTextView = findViewById(R.id.detail_employee);
        mStartdateTextView = findViewById(R.id.detail_start_date);
        mEnddateTextView = findViewById(R.id.detail_end_date);
        mReasonTextView = findViewById(R.id.detail_reason);



        String docId = getIntent().getStringExtra(Constants.EXTRA_DOC_ID);

        //Temp test
       // mEmployeeTextView.setText(docId);
        mDocRef = FirebaseFirestore.getInstance().collection(Constants.COLLECTION_PATH).document(docId);
        mDocRef.addSnapshotListener(new EventListener<DocumentSnapshot>() {
            @Override
            public void onEvent(DocumentSnapshot documentSnapshot, FirebaseFirestoreException e) {
                if (e != null) {
                    Log.w(Constants.TAG, "Listen failed");
                    return;
                }
                if (documentSnapshot.exists()) {
                    mDocSnapshot = documentSnapshot;
                    mEmployeeTextView.setText((String)documentSnapshot.get(Constants.KEY_EMPLOYEE));
                    mStartdateTextView.setText((String)documentSnapshot.get(Constants.KEY_STARTDATE));
                    mEnddateTextView.setText((String)documentSnapshot.get(Constants.KEY_ENDDATE));
                    mReasonTextView.setText((String)documentSnapshot.get(Constants.KEY_REASON));

                }
            }

        });


        FloatingActionButton fab = (FloatingActionButton) findViewById(R.id.fab);
        fab.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Snackbar.make(view, "Replace with your own action", Snackbar.LENGTH_LONG)
                        .setAction("Action", null).show();
            }
        });
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.menu_main, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {

        switch (item.getItemId()){
            case R.id.action_delete:
                //TODO: Delete this quote and close this activity
                return true;

        }
        return super.onOptionsItemSelected(item);
    }
}
