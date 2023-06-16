package com.example.internship;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.widget.ArrayAdapter;
import android.widget.ListView;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.io.IOException;
import java.io.InputStream;
import java.util.ArrayList;
import java.util.List;

public class MainActivity extends AppCompatActivity {

    ListView listview;
    List<String> listViewValues;
    ArrayList<String> listviewdetails = new ArrayList<>();
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        listview=(ListView) findViewById(R.id.mobile_list);

        try {
            JSONObject jsonObject=new JSONObject(loadJsonFile());
            JSONArray jsonArray=jsonObject.getJSONArray("studentdetails");
            listViewValues = new ArrayList<String>();
            for(int i=0; i<jsonArray.length();i++)
            {
                JSONObject obj=jsonArray.getJSONObject(i);
                String name=obj.getString("pavan kumar");
                listViewValues.add(name);
            }
            ArrayAdapter adapter = new ArrayAdapter<String>(this, R.layout.displayname, listViewValues);
            listview.setAdapter(adapter);
        } catch (JSONException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public String loadJsonFile() throws IOException {
        String json=null;
        InputStream inputStream=this.getAssets().open("studentdetails.json");
        int size=inputStream.available();
        byte[] byteArray=new byte[size];
        inputStream.read(byteArray);
        inputStream.close();
        json=new String(byteArray, "UTF-8");
        return json;
    }
}