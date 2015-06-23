package com.davifantasia.unevengrid;

import android.content.Intent;
import android.os.Bundle;
import android.support.v4.app.NavUtils;
import android.support.v7.app.AppCompatActivity;
import android.view.Menu;
import android.view.MenuItem;
import android.widget.TextView;


public class AlternatingThreeCellsItemDetailsActivity extends AppCompatActivity {

    public static final String TITLE = "title";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_alternating_three_cells_item_details);

        init();
    }

    private void init() {
        TextView textView = (TextView) findViewById(R.id.title);

        Intent intent = getIntent();
        String title = intent.getStringExtra(TITLE);

        if (title != null) {
            if (title.length() > 0)
                textView.setText(title);
            else {
                textView.setText("Could not get Title");
            }
        }
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.menu_alternating_three_cells_item_details, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        // Handle action bar item clicks here. The action bar will
        // automatically handle clicks on the Home/Up button, so long
        // as you specify a parent activity in AndroidManifest.xml.
        int id = item.getItemId();

        // Respond to the action bar's Up/Home button
        if (id == android.R.id.home) {
            // Navigate to the parent of this activity.
            NavUtils.navigateUpFromSameTask(this);
            return true;
        }

        return super.onOptionsItemSelected(item);
    }
}
