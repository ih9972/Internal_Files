package com.example.internalfiles;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.annotation.SuppressLint;
import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.EditText;
import android.widget.TextView;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;

public class MainActivity extends AppCompatActivity {
    private final String FILENAME = "inttest.txt";
    TextView text;
    EditText editText;
    @SuppressLint("MissingInflatedId")
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        text = (TextView) findViewById(R.id.textView);
        editText = (EditText) findViewById(R.id.editTextView);
        text.setText(read());
    }
    /**
     *This method is called when the save button is clicked
     * This method sets the text in the file to be  the string in str and set the file as the text view text.
     */
    public void save(View view) {
        write(editText.getText().toString());
        text.setText(read());
    }
    /**
     *This method is called when the reset button is clicked
     * This method sets the text in the file to be empty
     */
    public void reset(View view) {
        write();
        text.setText(read());
    }

    /**
     * This method is called when the exit button is clicked
     * This method adds the text in the file to the text in the edit text and closes the app
     * @param view
     */
    public void Exit(View view) {
        write(editText.getText().toString());
        finish();
    }

    /**
     * This method is called when any button is clicked
     * @return the new text in the file
     */
    public String read(){
        try {
            FileInputStream fIS= openFileInput(FILENAME);
            InputStreamReader iSR = new InputStreamReader(fIS);
            BufferedReader bR = new BufferedReader(iSR);
            StringBuilder sB = new StringBuilder();
            String line = bR.readLine();
            String newString;
            if (line != null) {
                while (line != null) {
                    sB.append(line + "\n");
                    line = bR.readLine();
                }
                newString = sB.toString().substring(0, sB.length() - 1);
            }else{
                newString = sB.toString();
            }
            bR.close();
            iSR.close();
            fIS.close();
            return newString;
        }
        catch (IOException e) {
            Log.e("MainActivity", e.toString());
        }
        return text.getText().toString();
    }
    /**
     *This method is called when the exit and save buttons are clicked
     * This method sets the text in the file to be  the string in str and set the file as the text view text
     */
    public void write(String str) {
        try {
            FileOutputStream fOS = openFileOutput(FILENAME, MODE_PRIVATE);
            OutputStreamWriter oSW = new OutputStreamWriter(fOS);
            BufferedWriter bW = new BufferedWriter(oSW);
            bW.write(text.getText().toString() + str);
            bW.close();
            oSW.close();
            fOS.close();
        } catch (IOException e) {
            Log.e("MainActivity", e.toString());
        }
    }

    /**
     *This method is called when the reset button is clicked
     * This method sets the text in the file to be empty and set the empty file as the text view text
     */
    public void write(){
        try {
                FileOutputStream fOS = openFileOutput(FILENAME,MODE_PRIVATE);
                OutputStreamWriter oSW = new OutputStreamWriter(fOS);
                BufferedWriter bW = new BufferedWriter(oSW);
                bW.write("");
                bW.close();
                oSW.close();
                fOS.close();
            }
        catch (IOException e) {
            Log.e("MainActivity", e.toString());
        }
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {

        getMenuInflater().inflate(R.menu.main,menu);

        return super.onCreateOptionsMenu(menu);
    }


    /**
     * this method matches the credit to the option that is selected.
     * @param item The menu item that was selected.
     *
     * @return
     */
    public boolean onOptionsItemSelected(@NonNull MenuItem item) {
        String st = item.getTitle().toString();
        if (st.equals("Credits screen")) {
            Intent si = new Intent(this, credits.class);
            startActivity(si);
        }
        return super.onOptionsItemSelected(item);
    }


}