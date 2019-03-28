package com.example.modelmvp;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.text.TextUtils;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;

public class MainActivity extends AppCompatActivity implements MainView {
    private EditText edtPanjang, edtLebar, edtTinggi;
    private TextView tvHasil;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        edtPanjang  = (EditText)findViewById(R.id.edt_panjang);
        edtLebar    = (EditText)findViewById(R.id.edt_lebar);
        edtTinggi   = (EditText)findViewById(R.id.edt_tinggi);

        Button btnHitung = (Button)findViewById(R.id.btn_hitung);
        tvHasil     = (TextView)findViewById(R.id.tv_hasil);

        final MainPresenter presenter = new MainPresenter(this);
        btnHitung.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String panjang  = edtPanjang.getText().toString().trim();
                String lebar    = edtLebar.getText().toString().trim();
                String tinggi   = edtTinggi.getText().toString().trim();

                boolean isEmptyFields = false;

                if (TextUtils.isEmpty(panjang)){
                    isEmptyFields = true;
                    edtPanjang.setError("Fields Ini Tidak Boleh Kosong");
                }
                if (TextUtils.isEmpty(lebar)){
                    isEmptyFields = true;
                    edtLebar.setError("Fields Ini Tidak Boleh Kosong");
                }
                if (TextUtils.isEmpty(tinggi)){
                    isEmptyFields = true;
                    edtTinggi.setError("Fields Ini Tidak Boleh Kosong");
                }
                if (!isEmptyFields){
                    double p = Double.parseDouble(panjang);
                    double l = Double.parseDouble(lebar);
                    double t = Double.parseDouble(tinggi);

                    presenter.hitungVolume(p, l, t);
                }
            }
        });
    }

    @Override
    public void tampilVolume(MainModel model) {
        tvHasil.setText(String.valueOf(model.getVolume()));

    }
}
