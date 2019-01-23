package com.dxdx.randomeats;

import androidx.appcompat.app.AppCompatActivity;
import androidx.lifecycle.ViewModelProviders;
import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;

import android.os.Bundle;
import android.view.View;
import android.widget.TextView;

public class PlaceDetailActivity extends AppCompatActivity {

    @BindView(R.id.name_text)
    TextView mNameText;
    @BindView(R.id.address_text)
    TextView mAddressText;
    @BindView(R.id.number_text)
    TextView mNumberText;
    @BindView(R.id.time_text)
    TextView mTimeText;
    @BindView(R.id.rating_text)
    TextView mRatingText;
    @BindView(R.id.description_text)
    TextView mDescriptionText;

    PlaceDetailViewModel mViewModel;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_place_detail);
        ButterKnife.bind(this);

        mViewModel = ViewModelProviders.of(this).get(PlaceDetailViewModel.class);

    }

    @OnClick(R.id.next_button)
    public void onNextButtonClick(View v){
        mViewModel.getNextPlace();
    }
}
