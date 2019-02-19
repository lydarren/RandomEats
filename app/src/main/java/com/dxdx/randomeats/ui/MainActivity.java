package com.dxdx.randomeats.ui;

import androidx.appcompat.app.AppCompatActivity;
import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;

import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.Spinner;

import com.dxdx.randomeats.R;


public class MainActivity extends AppCompatActivity {

    public static final String EXTRA_SPINNER = "MainActivity.EXTRA_SPINNER";

    @BindView(R.id.distance_spinner) Spinner mSpinner;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        ButterKnife.bind(this);
        initSpinner();
    }
    
    @OnClick(R.id.submit_button)
    public void submit_button(View v){
        Intent i = new Intent(MainActivity.this, PlaceDetailActivity.class);
        i.putExtra(EXTRA_SPINNER, mSpinner.getSelectedItem().toString());
        startActivity(i);
    }

    private void initSpinner(){
        ArrayAdapter<CharSequence> adapter = ArrayAdapter.
                createFromResource(this, R.array.distance_array,
                        R.layout.support_simple_spinner_dropdown_item);
        adapter.setDropDownViewResource(R.layout.support_simple_spinner_dropdown_item);
        mSpinner.setAdapter(adapter);
    }
}
