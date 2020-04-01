package it.framo.cisitasample2020.fragments;

import android.os.Bundle;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.RecyclerView;

import com.loopj.android.http.AsyncHttpClient;
import com.loopj.android.http.AsyncHttpResponseHandler;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.io.UnsupportedEncodingException;
import java.text.DateFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;

import cz.msebera.android.httpclient.Header;
import it.framo.cisitasample2020.R;
import it.framo.cisitasample2020.models.User;

public class ListUsersFragment extends Fragment {

    private RecyclerView recylerView;

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        return inflater.inflate(R.layout.fragment_list_users, container, false);
    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);

        recylerView =(RecyclerView)view.findViewById(R.id.my_recycler_view);



        // use a linear layout manager
        executeDonwloadData();
    }

    /**
     * Eseguo operazione di download dei dati
     * https://private-241152-cisitatest.apiary-mock.com/users
     */
    public void executeDonwloadData() {

        //istazione client http per dialogare con il mio back-end
        AsyncHttpClient client = new AsyncHttpClient();
        // eseguo chimata HTTP tramite metodo GET e delego la risposta alle 2 callback "onSuccess" e "onFailure"
        client.get("https://private-241152-cisitatest.apiary-mock.com/users", new AsyncHttpResponseHandler() {
            @Override
            public void onSuccess(int statusCode, Header[] headers, byte[] responseBody) {
                ArrayList<User> listUsers=new ArrayList();
                try {


                    String responseString = new String(responseBody, "UTF-8");
                   // Log.d("CISITA", "onSuccess chiamata avvenuta con successo: " + responseString);

                    //istanziamo il nostro JSON object a partire dalla stringa ottenuta dal server
                    JSONObject jsonObject=new JSONObject(responseString);
                    //ottengo il json array dal json object tramite la chiave 'users'
                    JSONArray jsonArray= jsonObject.getJSONArray("users");

                    //ciclo json array per serializzare il contenuto
                    for(int index=0; index<jsonArray.length();index++){
                        //ottengo json object che rappresenti User tramite posizione del json array
                        JSONObject userJsonObject = jsonArray.getJSONObject(index);


                   User currentUser=new User();
                   //Inizio serializzazione mappatura da JSON a oggetto JAVA
                   currentUser.name=userJsonObject.getString("name");
                   currentUser.surname=userJsonObject.getString("surname");
                   currentUser.visibile=userJsonObject.getBoolean("visible");
                   currentUser.age=userJsonObject.getInt("age");

                   //formato data da stringa a oggetto
                        DateFormat dateFormat=new SimpleDateFormat("yyyy-dd-mm HH:mm");
                        Date dateRegistration= dateFormat.parse(userJsonObject.getString("dateRegistration"));
                        currentUser.date=dateRegistration;

                   //Log.d("CISITA","User index: "+index+" toString: "+currentUser.getFullName());

                    listUsers.add(currentUser);
                    }


                } catch (UnsupportedEncodingException e) {
                    e.printStackTrace();
                } catch (JSONException e) {
                    e.printStackTrace();
                } catch (ParseException e) {
                    e.printStackTrace();
                }

                Log.d("CISITA","List Users count: "+listUsers.size());
            }

            @Override
            public void onFailure(int statusCode, Header[] headers, byte[] responseBody, Throwable error) {
                Log.d("CISITA", "onFaiulre errore nella chiamata: " + error.getLocalizedMessage());
            }
        });

    }


}
