package com.example.load_github_profiles_in_recyclerview;

import android.os.Bundle;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.GridLayoutManager;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;
import android.widget.LinearLayout;

import org.jetbrains.annotations.NotNull;

import java.net.HttpURLConnection;
import java.util.ArrayList;
import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class BlankFragment extends Fragment {
    private RecyclerView recyclerView;
    private List<ResponseModel> responseModelList = new ArrayList<>();
    private EditText mEtUsername;
    private Button mbtnFetch;
    private ProfileAdapter profileAdapter;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        return inflater.inflate(R.layout.fragment_blank, container, false);
    }

    @Override
    public void onViewCreated(@NonNull @NotNull View view, @Nullable @org.jetbrains.annotations.Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        initView(view);
    }

    private void callApi() {
        ApiService apiServicec = Network.getInstance().create(ApiService.class);
        Call<List<ResponseModel>> call = apiServicec.getData(mEtUsername.getText().toString());
        call.enqueue(new Callback<List<ResponseModel>>() {
            @Override
            public void onResponse(Call<List<ResponseModel>> call, Response<List<ResponseModel>> response) {
                if (response.code()== HttpURLConnection.HTTP_OK){
                    responseModelList = response.body();
                    profileAdapter.setNotify(responseModelList);
                }
            }

            @Override
            public void onFailure(Call<List<ResponseModel>> call, Throwable t) {

            }
        });
    }

    private void initView(View view) {
        recyclerView = view.findViewById(R.id.recyclerview);
        mbtnFetch = view.findViewById(R.id.btnFetch);
        mEtUsername = view.findViewById(R.id.etUsername);
        mbtnFetch.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                setRecylcerview();
                callApi();
            }
        });
    }

    private void setRecylcerview() {
        profileAdapter = new ProfileAdapter(responseModelList);
        LinearLayoutManager linearLayoutManager = new LinearLayoutManager(getContext());
        recyclerView.setAdapter(profileAdapter);
        recyclerView.setLayoutManager(linearLayoutManager);

    }
}