package com.example.angel.xmlparsing;

import java.util.ArrayList;
import java.util.HashMap;
import org.w3c.dom.Document;
import org.w3c.dom.Element;
import org.w3c.dom.NodeList;
import android.app.ListActivity;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.AdapterView.OnItemClickListener;
import android.widget.ListAdapter;
import android.widget.ListView;
import android.widget.SimpleAdapter;
import android.widget.TextView;


public class MainActivity extends ListActivity{

    // All static variables
    static final String URL = "http://eulisesrdz.260mb.net/android/xml-zapata.xml";
    // XML node keys
    static final String KEY_AUTO = "auto"; //Nodo padre.
    static final String KEY_N_SERIE = "n_serie";
    static final String KEY_MARCA = "marca";
    static final String KEY_MODELO = "modelo";
    static final String KEY_COLOR = "color";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        ArrayList<HashMap<String, String>> menuItems = new ArrayList<HashMap<String, String>>();

        XMLParser parser = new XMLParser();
        String xml = parser.getXmlFromUrl(URL); // getting XML
        Document doc = parser.getDomElement(xml); // getting DOM element

        NodeList nl = doc.getElementsByTagName(KEY_AUTO);

        // looping through all item nodes <item>
        for (int i = 0; i < nl.getLength(); i++) {
            //Creando el nuevo HashMap
            HashMap<String, String> map = new HashMap<String, String>();
            Element e = (Element) nl.item(i);
            // adding each child node to HashMap key => value
            map.put(KEY_N_SERIE, parser.getValue(e, KEY_N_SERIE));
            map.put(KEY_MARCA, parser.getValue(e, KEY_MARCA));
            map.put(KEY_MODELO, parser.getValue(e, KEY_MODELO));
            map.put(KEY_COLOR, parser.getValue(e, KEY_COLOR));

            // A�adiendo HashList a ArrayList
            menuItems.add(map);
        }

        // A�adiendo menuItems a ListView
        ListAdapter adapter = new SimpleAdapter(this, menuItems,R.layout.lista_autos,
                new String[]{KEY_N_SERIE, KEY_MARCA, KEY_MODELO, KEY_COLOR}, new int[]{R.id.n_serie, R.id.txt_marca, R.id.txt_modelo, R.id.txt_color});
        setListAdapter(adapter);

        // selecting single ListView item
        ListView lv = getListView();

        lv.setOnItemClickListener(new OnItemClickListener() {

            @Override
            public void onItemClick(AdapterView<?> parent, View view,
                                    int position, long id) {
                // getting values from selected ListItem
                String lbln_serie = ((TextView) view.findViewById(R.id.n_serie)).getText().toString();
                String lblmarca = ((TextView) view.findViewById(R.id.txt_marca)).getText().toString();
                String lblmodelo = ((TextView) view.findViewById(R.id.txt_modelo)).getText().toString();
                String lblcolor = ((TextView) view.findViewById(R.id.txt_color)).getText().toString();

                // Starting new intent
                Intent in = new Intent(getApplicationContext(), vista_individual.class);
                in.putExtra(KEY_N_SERIE, lbln_serie);
                in.putExtra(KEY_MARCA, lblmarca);
                in.putExtra(KEY_MODELO, lblmodelo);
                in.putExtra(KEY_COLOR, lblcolor);
                startActivity(in);

            }
        });
    }
}
