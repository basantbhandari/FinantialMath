package com.basant.yesicbap.financialmath;

import androidx.appcompat.app.AppCompatActivity;


import android.app.DatePickerDialog;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.DatePicker;
import android.widget.EditText;
import android.widget.RadioButton;
import android.widget.Toast;

import com.basant.yesicbap.financialmath.models.Note;
import com.basant.yesicbap.financialmath.persistance.NoteRepository;

import java.util.Calendar;

public class InputFormActivity extends AppCompatActivity  implements  DatePickerDialog.OnDateSetListener{


    private EditText mTitle;
    private EditText mDescription;
    private EditText mCost;
    private Button mDate;
    private Button mSave;


    private String title;
    private String description;
    private float cost;
    private boolean status;
    private String date;
    private  boolean checked = false;
    private NoteRepository mNoteRepository;
    private Note mInitialNote;





    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_input_form);

         refrencingView();



        //for date picker library
        mDate.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                showDatePickerDialog();
            }
        });


        //when save button is called
        mSave.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                saveAllData();
            }
        });




    }   //end create



    // refrencing
    private void refrencingView() {
        mTitle = findViewById(R.id.input_form_title_dynamic);
        mDescription = findViewById(R.id.input_form_reason);
        mCost = findViewById(R.id.input_form_cost);
        mDate = findViewById(R.id.input_form_date);
        mSave = findViewById(R.id.input_form_save);

        mNoteRepository = new NoteRepository(this);
        mInitialNote = new Note();
    }


    public void onRadioButtonClicked(View view) {
        // Is the button now checked ?
        checked = ((RadioButton) view).isChecked();

        // Check which radio button was clicked
        switch(view.getId()) {
            case R.id.input_form_spent:
                if (checked){
                    // spent is checked
                    status = true;
                }

                break;
            case R.id.input_form_earn:
                if (checked){
                    // cost is checked
                    status = false;
                }

                break;

        }

    }  // end radio button clicked





    //method for date picker fragment

    private void showDatePickerDialog() {
        DatePickerDialog datePickerDialog = new DatePickerDialog(
                this,
                this,
                Calendar.getInstance().get(Calendar.YEAR),
                Calendar.getInstance().get(Calendar.MONTH),
                Calendar.getInstance().get(Calendar.DAY_OF_MONTH)

        );
                datePickerDialog.show();
    }

    @Override
    public void onDateSet(DatePicker view, int year, int month, int dayOfMonth) {
         date = dayOfMonth + " : " + month + " : " + year;
         mDate.setText(date);
        //the value of date picked is assigned in date string variable
    }






    //method to save all the information
    private void saveAllData(){

        //getting values from edit text
        title = mTitle.getText().toString();
        description = mDescription.getText().toString();
        //exception handling
        if(mCost.getText().toString().trim().isEmpty()){
            Toast.makeText(InputFormActivity.this,"DATA FIELDS ARE NOT FILLED PROPERLY !!!", Toast.LENGTH_LONG).show();
        }else {
            cost = Float.parseFloat(mCost.getText().toString());
        }




        //if radio button is not checked
        //if title is not filled
        //if description is not filled
        //if cost is not filled
        //if date is not picked
        if( !checked || title.trim().isEmpty()  || description.trim().isEmpty()  || (mCost.getText().toString().trim().isEmpty())   || date.trim().isEmpty() ){
            //if one of above is true then
            Toast.makeText(InputFormActivity.this,"DATA FIELDS ARE NOT FILLED PROPERLY !!!", Toast.LENGTH_LONG).show();
        }else {
            //if all the fields are filled then
            /* we have all the information in variable
               title
               description
               cost
               status (true for spent and false for earn)
               date  (day:month:year)
             */
            mInitialNote.setTitle(title);
            mInitialNote.setDescription(description);
            mInitialNote.setCost(cost);
            mInitialNote.setStatus(status);
            mInitialNote.setTimestamp(date);
            saveNewNote();
            Toast.makeText(InputFormActivity.this, "DATA IS SUCCESSFULLY INSERTED",Toast.LENGTH_LONG).show();
             finish();
        }// end else

    }//end save

    private void saveNewNote() {
  mNoteRepository.insertNoteTask(mInitialNote);
    }


}  // end class
