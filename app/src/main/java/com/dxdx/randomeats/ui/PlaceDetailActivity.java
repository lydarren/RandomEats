package com.dxdx.randomeats.ui;

import androidx.appcompat.app.AppCompatActivity;
import androidx.lifecycle.Observer;
import androidx.lifecycle.ViewModelProviders;
import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;

import android.os.Bundle;
import android.view.View;
import android.widget.TextView;

import com.dxdx.randomeats.R;
import com.dxdx.randomeats.models.Result;
import com.dxdx.randomeats.viewmodels.PlaceDetailViewModel;

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

        mViewModel.getPlace().observe(this, new Observer<Result>() {
            @Override
            public void onChanged(Result result) {
                if(result != null){
                    mNameText.setText(result.getName());
                    mAddressText.setText(result.getFormattedAddress());
                    mNumberText.setText(result.getFormattedPhoneNumber());
                    mTimeText.setText(result.getName());
                    mRatingText.setText(String.valueOf(result.getRating()));
                    mDescriptionText.setText(result.getTypes().toString());
                }
            }
        });
    }

    @OnClick(R.id.next_button)
    public void onNextButtonClick(View v){
        mViewModel.getNextPlace();
    }
}
