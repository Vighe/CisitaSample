package it.framo.cisitasample2020.fragments;

import android.os.Bundle;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentTransaction;

import it.framo.cisitasample2020.R;

public class HomeFragment extends Fragment implements View.OnClickListener {

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        return inflater.inflate(R.layout.fragment_home,container,false);

    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        //referenzio Button per gerstire la logica del pulsante
        Button buttonShowList=view.findViewById(R.id.buttonShowUsers);
        //implemento OnClick sul pulsante
        buttonShowList.setOnClickListener(this);
    }

    @Override
    public void onClick(View v) {
        Log.d("CISITA","Pulsante premuto!");
        ShowListUsers();
    }

    //mostro fragment ListUsersFragment
    private void ShowListUsers(){
        ListUsersFragment listUsersFragment= new ListUsersFragment();

        //Avvio una transizione tramite FragmentManager ottenuto dalla MainActivity
        FragmentTransaction transaction= getActivity().getSupportFragmentManager().beginTransaction();

        //Sostituisco il fragment attuale con la nuova istanza di fragment del FrameLayout
        transaction.replace(R.id.FrameLayoutContainer, listUsersFragment,"LIST_USERS_FRAGMENT");


        transaction.addToBackStack(listUsersFragment.getTag());

        //Finalizzo le operazioni di transizione
        transaction.commit();
    }
}
