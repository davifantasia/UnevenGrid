package com.davifantasia.unevengrid;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import com.davifantasia.unevengrid.adapters.AlternatingThreeCellsAdapter;

import java.util.ArrayList;
import java.util.List;


public class AlternatingThreeCellsActivity extends AppCompatActivity {

    private static final int DEFAULT_NUMBER_OF_ITEMS = 4;

    private EditText mNumberOfItemsET;

    private AlternatingThreeCellsAdapter mAdapter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_alternating_three_cells);

        init();
    }

    private void init() {
        initViews();
    }

    private void initViews() {
        mNumberOfItemsET = (EditText) findViewById(R.id.number_of_items_edit_text);
        Button refreshButton = (Button) findViewById(R.id.refresh_button);
        refreshButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                int numberOfItems = Integer.valueOf(mNumberOfItemsET.getText().toString());
                setItems(numberOfItems, true);
            }
        });
        initRecyclerView();
    }

    private void initRecyclerView() {
        RecyclerView mRecyclerView = (RecyclerView) findViewById(R.id.recycler_view);
        RecyclerView.LayoutManager layoutManager = new LinearLayoutManager(this);
        mRecyclerView.setLayoutManager(layoutManager);

        List<String> items = new ArrayList<>();
        mAdapter = new AlternatingThreeCellsAdapter(this, items);
        mRecyclerView.setAdapter(mAdapter);

        setItems(DEFAULT_NUMBER_OF_ITEMS, false);
    }

    private void setItems(int numberOfItems, boolean refresh) {
        List<String> items = new ArrayList<>();

        for (int i = 0; i < numberOfItems; i++)
            items.add("Item " + (i + 1));

        mAdapter.setData(items);

        if (refresh)
            Toast.makeText(this, "Refreshed with "+ numberOfItems + " items.", Toast.LENGTH_SHORT).show();
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.menu_alternating_three_cells, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        // Handle action bar item clicks here. The action bar will
        // automatically handle clicks on the Home/Up button, so long
        // as you specify a parent activity in AndroidManifest.xml.
        int id = item.getItemId();

        //noinspection SimplifiableIfStatement
        if (id == R.id.action_settings) {
            return true;
        }

        return super.onOptionsItemSelected(item);
    }
}
