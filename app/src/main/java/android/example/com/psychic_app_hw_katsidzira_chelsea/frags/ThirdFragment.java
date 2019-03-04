package android.example.com.psychic_app_hw_katsidzira_chelsea.frags;


import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import android.example.com.psychic_app_hw_katsidzira_chelsea.R;
import android.widget.ImageView;
import android.widget.TextView;

import java.text.DecimalFormat;

import static android.example.com.psychic_app_hw_katsidzira_chelsea.frags.SecondFragment.ANDROID_IMAGE;
import static android.example.com.psychic_app_hw_katsidzira_chelsea.frags.SecondFragment.PERCENTAGE;
import static android.example.com.psychic_app_hw_katsidzira_chelsea.frags.SecondFragment.RESULT_STRING;
import static android.example.com.psychic_app_hw_katsidzira_chelsea.frags.SecondFragment.USER_IMAGE;

public class ThirdFragment extends Fragment {
    private ImageView userPickImageView;
    private ImageView androidPickImageView;
    private TextView resultTextView;
    private TextView percentageTextView;

    public ThirdFragment() {
    }

    public static ThirdFragment newInstance() {
        return new ThirdFragment();
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        return inflater.inflate(R.layout.fragment_third, container, false);
    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        userPickImageView = view.findViewById(R.id.user_pick_imageview);
        androidPickImageView = view.findViewById(R.id.android_pick_imageview);
        resultTextView = view.findViewById(R.id.result_textview);
        percentageTextView = view.findViewById(R.id.percentage);

        Bundle args = getArguments();
        assert args != null;
        int userPickImage = args.getInt(USER_IMAGE);
        int androidPickImage = args.getInt(ANDROID_IMAGE);
        String resultString = args.getString(RESULT_STRING);
        float percentage = args.getFloat(PERCENTAGE);

        DecimalFormat decimalFormat = new DecimalFormat("#.##");
        String percentageAsString = decimalFormat.format(percentage) + "%";

        userPickImageView.setImageResource(userPickImage);
        androidPickImageView.setImageResource(androidPickImage);
        resultTextView.setText(resultString);
        percentageTextView.setText(percentageAsString);

    }
}
