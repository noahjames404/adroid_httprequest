package com.anonimouse.companyproject1.Activity;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;

import com.anonimouse.companyproject1.Model.SampleObject;
import com.anonimouse.companyproject1.Model.UserInfoObject;
import com.anonimouse.companyproject1.R;

import java.util.ArrayList;
import java.util.List;

public class Sample extends AppCompatActivity {

    List<SampleObject> mlistSample = new ArrayList<>();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.layout_sample);


        SampleObject sampleObject = new SampleObject();
        sampleObject.setFirstname("noah");
        sampleObject.setMiddlename("Cece");
        sampleObject.setLastname("yanga");
        mlistSample.add(sampleObject);

        Log.d("bulbol", "" + mlistSample.get(0).getFirstname().toString() + " " + mlistSample.get(0).getLastname().toString());
    }
}
