package com.example.musicapp.Fragment;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.fragment.app.Fragment;

import com.example.musicapp.R;


public class LibraryFragment extends Fragment {



    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        return inflater.inflate(R.layout.fragment_library, container, false);

////        ListView danh_sach_ca_si = (ListView) .findViewById(R.id.list_casi);
//        ListView danh_sach_ca_si = (ListView) getView().findViewById(R.id.list_casi);
//        ArrayList<ListSingerModel> list_ca_si = new ArrayList<ListSingerModel>();
//
//        list_ca_si.add(new ListSingerModel("Chi Dân",R.drawable.chi_dan));
//        list_ca_si.add(new ListSingerModel("Sơn Tùng",R.drawable.son_tung));
//        ListSingerAdapter ca_si_adapter = new ListSingerAdapter(MainActivity.this, R.layout.list_singer, list_ca_si) {
//        };
//
//        danh_sach_ca_si.setAdapter(ca_si_adapter);
//
//        //Sự kiện click trong list view
//        danh_sach_ca_si.setOnItemClickListener(new AdapterView.OnItemClickListener() {
//            @Override
//            public void onItemClick(AdapterView<?> adapterView, View view, int i, long l) {
//                Intent intent =new Intent(MainActivity.this,activity_list_bai_hat.class);
//                intent.putExtra("so", i);
//                intent.putExtra("ten",list_ca_si.get(i).ten);
//                startActivity(intent);
//            }
//        });
    }
}