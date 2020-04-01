package it.framo.cisitasample2020;

import androidx.appcompat.app.AppCompatActivity;
import androidx.fragment.app.FragmentTransaction;

import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

import it.framo.cisitasample2020.fragments.HomeFragment;

public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        // definisce quale leyout deve mostrare la nostra schermata
        setContentView(R.layout.activity_main);

        createHomeFragment();
    }




    private void createHomeFragment(){
        HomeFragment homeFragment= new HomeFragment();
        FragmentTransaction transaction = this.getSupportFragmentManager().beginTransaction();

        transaction.add(R.id.FrameLayoutContainer,homeFragment,"HOME_FRAGMENT");

        transaction.commit();
    }

    @Override
protected void onResume(){
        super.onResume();
        Log.d("CISITA","Onresume APP Cisita");

    }


}
