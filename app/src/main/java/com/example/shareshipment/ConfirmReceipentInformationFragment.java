package com.example.shareshipment;


import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;

public class ConfirmReceipentInformationFragment extends Fragment {

    TextView recipientPhoneNumberTextView,recipientNameTextView;
    String recipientName;

    public  ConfirmReceipentInformationFragment(String recipientName){
        this.recipientName = recipientName;
    }
    @Nullable
    @Override

    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.activity_confirm_recipent_information,container,false);
        return view;
    }
    @Override
    public void onViewCreated(View view, @Nullable Bundle savedInstanceState) {

        recipientPhoneNumberTextView =  getView().findViewById(R.id.RecipentPhoneNumber);
        recipientPhoneNumberTextView.setText(((MyApplicationData) getActivity().getApplication()).getRecipientPhoneNumber());
        recipientNameTextView =    getView().findViewById(R.id.receipentName);
        recipientNameTextView.setText(recipientName);

    }
}
