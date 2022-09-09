package com.example.checkrun;

import android.location.Location;

import androidx.appcompat.app.ActionBar;
import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentTransaction;

import com.example.checkrun.Home.HomeFragment;
import com.example.checkrun.Profile.ProfileFragment;
import com.example.checkrun.Settings.SettingsFragment;
import com.example.checkrun.Training.TrainingFragment;

import org.w3c.dom.Document;
import org.w3c.dom.Element;
import org.w3c.dom.NamedNodeMap;
import org.w3c.dom.Node;
import org.w3c.dom.NodeList;
import org.xml.sax.SAXException;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import javax.xml.parsers.DocumentBuilder;
import javax.xml.parsers.DocumentBuilderFactory;
import javax.xml.parsers.ParserConfigurationException;

public class Utilities {

    public static void insertFragment(AppCompatActivity activity, Fragment fragment, String tag) {
        FragmentTransaction transaction = activity.getSupportFragmentManager().beginTransaction();
        // Replace whatever is in the fragment_container_view with this fragment
        transaction.replace(R.id.fragment_container_view, fragment, tag);
        //add the transaction to the back stack so the user can navigate back except for the HomeFragment
        //TODO Sistemare il back stack per evitare schermata bianca activity
        if (!((fragment instanceof HomeFragment) || (fragment instanceof TrainingFragment) || (fragment instanceof SettingsFragment) || (fragment instanceof ProfileFragment))) {
            transaction.addToBackStack(tag);
        }
        // Commit the transaction
        transaction.commit();
    }

    public static void setUpToolbar(AppCompatActivity activity, String title) {
        ActionBar actionBar = activity.getSupportActionBar();
        if (actionBar == null) {
            //create a toolbar that act as SupportActionBar
            Toolbar toolbar = new Toolbar(activity);
            activity.setSupportActionBar(toolbar);
        } else {
            activity.getSupportActionBar().setTitle(title);
        }
    }

    public static List<GpxNode> decodeGPX(File file){
        List<GpxNode> list = new ArrayList<GpxNode>();

        DocumentBuilderFactory documentBuilderFactory = DocumentBuilderFactory.newInstance();
        try {
            DocumentBuilder documentBuilder = documentBuilderFactory.newDocumentBuilder();
            FileInputStream fileInputStream = new FileInputStream(file);
            Document document = documentBuilder.parse(fileInputStream);
            Element elementRoot = document.getDocumentElement();

            NodeList nodelist_trkpt = elementRoot.getElementsByTagName("trkpt");

            for(int i = 0; i < nodelist_trkpt.getLength(); i++){

                Node node = nodelist_trkpt.item(i);
                NamedNodeMap attributes = node.getAttributes();

                String newLatitude = attributes.getNamedItem("lat").getTextContent();
                Double newLatitude_double = Double.parseDouble(newLatitude);

                String newLongitude = attributes.getNamedItem("lon").getTextContent();
                Double newLongitude_double = Double.parseDouble(newLongitude);

                String newLocationName = newLatitude + ":" + newLongitude;
                Location newLocation = new Location(newLocationName);
                newLocation.setLatitude(newLatitude_double);
                newLocation.setLongitude(newLongitude_double);

                NodeList childNodes = node.getChildNodes();

                String newEle = searchNodeListForTarget(childNodes, "ele");
                String newTime = searchNodeListForTarget(childNodes, "time");

                //insert in list
                GpxNode newGpxNode = new GpxNode(newLocation, newEle, newTime);
                list.add(newGpxNode);

            }

            fileInputStream.close();

        } catch (ParserConfigurationException e) {
            e.printStackTrace();
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        } catch (SAXException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        }

        return list;
    }

    public static String searchNodeListForTarget(NodeList src, String target){

        for(int i = 0; i < src.getLength(); i++){
            Node n = src.item(i);
            if (n.getNodeName().equals(target)){
                return n.getFirstChild().getNodeValue();
            }
        }
        return "";
    }
}
