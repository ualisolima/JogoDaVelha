package com.example.wallinsondeivesbatistalima.jogodavelha;

import android.app.ProgressDialog;
import android.content.DialogInterface;
import android.content.Intent;
import android.os.AsyncTask;
import android.support.v7.app.AlertDialog;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.CompoundButton;
import android.widget.Switch;
import android.widget.TableLayout;
import android.widget.TableRow;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;
import java.io.PrintWriter;
import java.net.Socket;
import java.net.UnknownHostException;

public class OfflineActivity extends AppCompatActivity {
    private TableRow tr1, tr2, tr3, tr4;
    private TableLayout table;
    private Button b1, b2, b3, b4, b5, b6, b7, b8, b9, refresh;
    private String data = null;
    private int turn = 1;
    int i1=0, i2=0, i3=0, i4=0, i5=0, i6=0, i7=0, i8=0, i9=0;
    private Switch onlineSwitch;

    protected void initView() {

        table = (TableLayout) findViewById(R.id.table);
        tr1 = (TableRow) findViewById(R.id.tr1);
        tr2 = (TableRow) findViewById(R.id.tr2);
        tr3 = (TableRow) findViewById(R.id.tr3);
        tr4 = (TableRow) findViewById(R.id.tr4);
        refresh = (Button) findViewById(R.id.refresh);
        b1 = (Button) findViewById(R.id.b1);
        b2 = (Button) findViewById(R.id.b2);
        b3 = (Button) findViewById(R.id.b3);
        b4 = (Button) findViewById(R.id.b4);
        b5 = (Button) findViewById(R.id.b5);
        b6 = (Button) findViewById(R.id.b6);
        b7 = (Button) findViewById(R.id.b7);
        b8 = (Button) findViewById(R.id.b8);
        b9 = (Button) findViewById(R.id.b9);
        onlineSwitch = (Switch) findViewById(R.id.onlineSwitch);
        b1.setText("");
        b2.setText("");
        b3.setText("");
        b4.setText("");
        b5.setText("");
        b6.setText("");
        b7.setText("");
        b8.setText("");
        b9.setText("");
        refresh.setText("");
        onlineSwitch.setText("Online");
        int h = this.getResources().getDisplayMetrics().heightPixels;
        int w = this.getResources().getDisplayMetrics().widthPixels;
        table.getLayoutParams().height = w + ( w / 3);
        table.getLayoutParams().width = w;
        tr1.getLayoutParams().height = w / 3;
        tr1.getLayoutParams().width = w;
        tr2.getLayoutParams().height = w / 3;
        tr2.getLayoutParams().width = w;
        tr3.getLayoutParams().height = w / 3;
        tr3.getLayoutParams().width = w;
        tr4.getLayoutParams().height = w / 3;
        tr4.getLayoutParams().width = w;
        refresh.getLayoutParams().height = w / 3;
        refresh.getLayoutParams().width = w / 3 - 15;
        refresh.setBackgroundResource(R.drawable.refresh);
        b1.getLayoutParams().height = w / 3;
        b2.getLayoutParams().height = w / 3;
        b3.getLayoutParams().height = w / 3;
        b4.getLayoutParams().height = w / 3;
        b5.getLayoutParams().height = w / 3;
        b6.getLayoutParams().height = w / 3;
        b7.getLayoutParams().height = w / 3;
        b8.getLayoutParams().height = w / 3;
        b9.getLayoutParams().height = w / 3;
        b1.getLayoutParams().width = w / 3 - 15;
        b2.getLayoutParams().width = w / 3 - 15;
        b3.getLayoutParams().width = w / 3;
        b4.getLayoutParams().width = w / 3 - 15;
        b5.getLayoutParams().width = w / 3 - 15;
        b6.getLayoutParams().width = w / 3;
        b7.getLayoutParams().width = w / 3 - 15;
        b8.getLayoutParams().width = w / 3 - 15;
        b9.getLayoutParams().width = w / 3;
        onlineSwitch.getLayoutParams().width = w / 3;

        onlineSwitch.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(CompoundButton buttonView,
                                         boolean isChecked) {

                if(isChecked){
                    Intent intent = new Intent(getApplicationContext(), MainActivity.class);
                    finish();
                    startActivity(intent);

                }else{

                }

            }
        });

        refresh.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = getIntent();
                finish();
                startActivity(intent);
            }
        });

    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        System.out.print("chegou aqui");
        initView();
        initButtons();

    }

    protected void checa(){
         if ((i1 == i2 && i2 == i3 && i3 == 1)
                || (i4 == i5 && i5 == i6 && i6 == 1)
                || (i7 == i8 && i8 == i9 && i9 == 1)
                || (i1 == i5 && i5 == i9 && i9 == 1)
                || (i3 == i5 && i5 == i7 && i7 == 1)
                || (i1 == i4 && i4 == i7 && i7 == 1)
                || (i2 == i5 && i5 == i8 && i8 == 1)
                || (i3 == i6 && i6 == i9 && i9 == 1)) {

                new AlertDialog.Builder(OfflineActivity.this)
                        .setTitle("Jogador 1 Venceu")
                        .setMessage("Jogador 1 venceu!")
                        .setPositiveButton("Ok", new DialogInterface.OnClickListener() {
                            public void onClick(DialogInterface dialog, int which) {
                                // continue with delete
                            }
                        })
                        .setIcon(android.R.drawable.ic_dialog_alert)
                        .show();
             b1.setEnabled(false);
             b2.setEnabled(false);
             b3.setEnabled(false);
             b4.setEnabled(false);
             b5.setEnabled(false);
             b6.setEnabled(false);
             b7.setEnabled(false);
             b8.setEnabled(false);
             b9.setEnabled(false);

            }
            else if ((i1 == i2 && i2 == i3 && i3 == 2)
                    || (i4 == i5 && i5 == i6 && i6 == 2)
                    || (i7 == i8 && i8 == i9 && i9 == 2)
                    || (i1 == i5 && i5 == i9 && i9 == 2)
                    || (i3 == i5 && i5 == i7 && i7 == 2)
                    || (i1 == i4 && i4 == i7 && i7 == 2)
                    || (i2 == i5 && i5 == i8 && i8 == 2)
                    || (i3 == i6 && i6 == i9 && i9 == 2)) {

                new AlertDialog.Builder(OfflineActivity.this)
                        .setTitle("Jogador 2 Venceu")
                        .setMessage("Jogador 2 venceu!")
                        .setPositiveButton("Ok", new DialogInterface.OnClickListener() {
                            public void onClick(DialogInterface dialog, int which) {
                                // continue with delete
                            }
                        })
                        .setIcon(android.R.drawable.ic_dialog_alert)
                        .show();
             b1.setEnabled(false);
             b2.setEnabled(false);
             b3.setEnabled(false);
             b4.setEnabled(false);
             b5.setEnabled(false);
             b6.setEnabled(false);
             b7.setEnabled(false);
             b8.setEnabled(false);
             b9.setEnabled(false);

            }
            else if (i1 != 0 && i2 != 0 && i3 != 0 && i4 != 0 && i5 != 0 && i6 != 0 && i7 != 0 && i8 != 0 && i9 != 0) {
                new AlertDialog.Builder(OfflineActivity.this)
                        .setTitle("Empate")
                        .setMessage("Infelizmente Ninguém ganhou!")
                        .setPositiveButton("Ok", new DialogInterface.OnClickListener() {
                            public void onClick(DialogInterface dialog, int which) {
                                // continue with delete
                            }
                        })
                        .setIcon(android.R.drawable.ic_dialog_alert)
                        .show();
            }
    }

    protected void changeB(String b, int j){
        System.out.println("ENTOU AQUI LINDAMENTE : " + b + " : " + j);
        switch (j){
            case 1:
                if (b.equals("1")) {
                    i1 = j;
                    b1.setEnabled(false);
                    b1.setBackgroundResource(R.drawable.x);
                }
                else if (b.equals("2")) {
                    i2 = j;
                    b2.setEnabled(false);
                    b2.setBackgroundResource(R.drawable.x);
                }
                else if (b.equals("3")) {
                    i3 = j;
                    b3.setEnabled(false);
                    b3.setBackgroundResource(R.drawable.x);
                }
                else if (b.equals("4")) {
                    i4 =j;
                    b4.setEnabled(false);
                    b4.setBackgroundResource(R.drawable.x);
                }
                else if (b.equals("5")) {
                    i5 =j;
                    b5.setEnabled(false);
                    b5.setBackgroundResource(R.drawable.x);
                }
                else if (b.equals("6")) {
                    i6 = j;
                    b6.setEnabled(false);
                    b6.setBackgroundResource(R.drawable.x);
                }
                else if (b.equals("7")) {
                    i7 = j;
                    b7.setEnabled(false);
                    b7.setBackgroundResource(R.drawable.x);
                }
                else if (b.equals("8")) {
                    i8 = j;
                    b8.setEnabled(false);
                    b8.setBackgroundResource(R.drawable.x);
                }
                else if (b.equals("9")) {
                    i9 = j;
                    b9.setEnabled(false);
                    b9.setBackgroundResource(R.drawable.x);
                }
                break;
            case 2:
                if (b.equals("1")) {
                    i1 = j;
                    b1.setEnabled(false);
                    b1.setBackgroundResource(R.drawable.o);
                }
                else if (b.equals("2")) {
                    i2 = j;
                    b2.setEnabled(false);
                    b2.setBackgroundResource(R.drawable.o);
                }
                else if (b.equals("3")) {
                    i3 = j;
                    b3.setEnabled(false);
                    b3.setBackgroundResource(R.drawable.o);
                }
                else if (b.equals("4")) {
                    i4 = j;
                    b4.setEnabled(false);
                    b4.setBackgroundResource(R.drawable.o);
                }
                else if (b.equals("5")) {
                    i5 = j;
                    b5.setEnabled(false);
                    b5.setBackgroundResource(R.drawable.o);
                }
                else if (b.equals("6")) {
                    i6 = j;
                    b6.setEnabled(false);
                    b6.setBackgroundResource(R.drawable.o);
                }
                else if (b.equals("7")) {
                    i7 = j;
                    b7.setEnabled(false);
                    b7.setBackgroundResource(R.drawable.o);
                }
                else if (b.equals("8")) {
                    i8 = j;
                    b8.setEnabled(false);
                    b8.setBackgroundResource(R.drawable.o);
                }
                else if (b.equals("9")) {
                    i9 = j;
                    b9.setEnabled(false);
                    b9.setBackgroundResource(R.drawable.o);
                }
                break;
            default:
                break;
        }
    }

    protected void initButtons(){
        b1.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                changeB("1", turn);
                checa();
                turn = (turn % 2) + 1 ;

            }
        });

        b2.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                changeB("2", turn);
                checa();
                turn = (turn % 2) + 1 ;
            }
        });

        b3.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                changeB("3", turn);
                checa();
                turn = (turn % 2) + 1 ;

            }
        });

        b4.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                changeB("4", turn);
                checa();
                turn = (turn % 2) + 1 ;

            }
        });

        b5.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                changeB("5", turn);
                checa();
                turn = (turn % 2) + 1 ;
            }
        });

        b6.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                changeB("6", turn);
                checa();
                turn = (turn % 2) + 1 ;
            }
        });

        b7.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                changeB("7", turn);
                checa();
                turn = (turn % 2) + 1 ;
            }
        });

        b8.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                changeB("8", turn);
                checa();
                turn = (turn % 2) + 1 ;
            }
        });

        b9.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                changeB("9", turn);
                checa();
                turn = (turn % 2) + 1 ;
            }
        });



    }

}