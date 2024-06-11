package com.sagar.thapathaliapp2.fragment;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.sagar.thapathaliapp2.ClassItem;
import com.sagar.thapathaliapp2.adapters.ClassAdapter;
import com.sagar.thapathaliapp2.session.SessionManager;
import com.sagar.thapathaliapp2.R;

import java.util.ArrayList;
import java.util.List;


public class HomeFragment extends Fragment {

    TextView st_name,st_rollno;
    com.google.android.material.imageview.ShapeableImageView studentPP;

    List<ClassItem> classList;
    RecyclerView homeRecyclerView;
    ClassAdapter classAdapter;
    LinearLayoutManager layoutManager;

    SessionManager sessionManager;



    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {

        // Inflate the layout for this fragment
        View rootView = inflater.inflate(R.layout.fragment_home, container, false);

        sessionManager = new SessionManager(requireContext());

        st_name = (TextView) rootView.findViewById(R.id.st_name);
        st_rollno = (TextView) rootView.findViewById((R.id.st_rollno));
        studentPP = (com.google.android.material.imageview.ShapeableImageView) rootView.findViewById(R.id.st_pp);

        setStudentDetails();

        classList = generateClassItems();
        homeRecyclerView = rootView.findViewById(R.id.recyclerView);
        layoutManager = new LinearLayoutManager(requireContext(),LinearLayoutManager.HORIZONTAL,false);
        homeRecyclerView.setLayoutManager(layoutManager);
        classAdapter = new ClassAdapter(classList);
        homeRecyclerView.setAdapter(classAdapter);

        return rootView;
    }

    public List<ClassItem> generateClassItems(){
        List<ClassItem> classItems = new ArrayList<>();
        classItems.add(new ClassItem("HK Sir","Eng. Mathematics-I"));
        classItems.add(new ClassItem("Sharmila Mam","Eng. Chemistry"));
        classItems.add(new ClassItem("PKC Mam","Eng. Chemistry"));
        classItems.add(new ClassItem("KKR Sir","Electronics"));
        classItems.add(new ClassItem("Baldev Sir","Eng. Math"));
        return classItems;
    }

    public void setStudentDetails(){
        String studentName = sessionManager.getSessionDetails("key_session_name");
        String studentRollno = sessionManager.getSessionDetails("key_session_rollno");

        st_name.setText(studentName);
        st_rollno.setText(studentRollno);

    }


}