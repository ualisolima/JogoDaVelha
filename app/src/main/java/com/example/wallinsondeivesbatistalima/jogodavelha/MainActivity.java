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

public class MainActivity extends AppCompatActivity {
    private TableRow tr1, tr2, tr3, tr4;
    private TableLayout table;
    private Button b1, b2, b3, b4, b5, b6, b7, b8, b9, refresh;
    private ProgressDialog pd = null;
    private String data = null;
    private Socket socket;
    private String serverIpAddress = "52.27.20.191";
    private static final int REDIRECTED_SERVERPORT = 4444;
    public PrintWriter out;
    public BufferedReader in;
    private int j, j2;
    private boolean turn = false;
    int i1=0, i2=0, i3=0, i4=0, i5=0, i6=0, i7=0, i8=0, i9=0;
    private String str, res, res2 = null;
    private AsyncAction action;
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
        System.out.println(h + "   : paz filhos da puta");
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


    }

    protected void initBs(){
        onlineSwitch.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(CompoundButton buttonView,
                                         boolean isChecked) {

                if(!isChecked){
                    Intent intent = new Intent(getApplicationContext(), OfflineActivity.class);
                    try {
                        socket.close();
                    } catch (IOException e) {
                        e.printStackTrace();
                    }
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
                try {
                    socket.close();
                } catch (IOException e) {
                    e.printStackTrace();
                }
                finish();
                startActivity(intent);
            }
        });
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        initView();
        onlineSwitch.setChecked(true);
        initBs();
        pd = new ProgressDialog(this);
        pd.setMessage("Conectando...");
        pd.setCancelable(false);
        pd.setButton(DialogInterface.BUTTON_NEGATIVE, "Cancelar", new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialogInterface, int i) {
                pd.dismiss();
                Intent intent = new Intent(MainActivity.this, OfflineActivity.class);
                try {
                    socket.close();
                } catch (IOException e) {
                    e.printStackTrace();
                }
                finish();
                startActivity(intent);
            }
        });
        pd.show();
        //this.pd = ProgressDialog.show(this, "Carregando..", "Esperando um jogador aparecer...", true, false);
        action = new AsyncAction();
        action.execute();

    }

    protected void changeB(String b, int j){
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
                if (turn) {
                    changeB("1", j);
                    res2 = "1";

                }
            }
        });

        b2.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                    if (turn) {
                    changeB("2", j);
                    res2 = "2";
                }
            }
        });

        b3.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if (turn) {
                    changeB("3", j);
                    res2 = "3";
                }
            }
        });

        b4.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if (turn) {
                    changeB("4", j);
                    res2 = "4";
                }
            }
        });

        b5.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if (turn) {
                    changeB("5", j);
                    res2 = "5";

                }
            }
        });

        b6.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if (turn) {
                    changeB("6", j);
                    res2 = "6";
                }
            }
        });

        b7.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if (turn) {
                    changeB("7", j);
                    res2 = "7";
                }
            }
        });

        b8.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if (turn) {
                    changeB("8", j);
                    res2 = "8";
                }
            }
        });

        b9.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if (turn) {
                    changeB("9", j);
                    res2 = "9";
                }
            }
        });



    }

    private class AsyncAction extends AsyncTask<String, Void, String> {
        protected String doInBackground(String... args) {
            try {
                socket = new Socket(serverIpAddress, REDIRECTED_SERVERPORT);
            } catch (UnknownHostException e1) {
                e1.printStackTrace();
            } catch (IOException e1) {
                e1.printStackTrace();
            }
            try {
                out = new PrintWriter(new BufferedWriter(new OutputStreamWriter(socket.getOutputStream())), true);
                in = new BufferedReader(new InputStreamReader(socket.getInputStream()));
                str = in.readLine();
                MainActivity.this.initButtons();
                switch (str) {
                    case "1":
                        j = 1;
                        j2 = 2;
                        pd.dismiss();
                        turn = true;
                        while (res2 == null) {
                        }
                        turn = false;
                        out.println(res2);
                        res2 = null;
                        break;
                    case "2":
                        j = 2;
                        j2 = 1;
                        res = in.readLine();
                        pd.dismiss();
                        if (!res.equals("cancelou")) {
                            runOnUiThread(new Runnable() {
                                @Override
                                public void run() {
                                    changeB(res, j2);
                                }
                            });
                            turn = true;
                            while (res2 == null) {
                            }
                            turn = false;
                            out.println(res2);
                            res2 = null;
                        }
                        break;
                    default:
                        break;
                }
                if (res == null || !res.equals("cancelou"))
                    res = in.readLine();
                while (!res.contains("perdeu") && !res.equals("cancelou") && !res.contains("empate")) {
                    runOnUiThread(new Runnable() {
                        @Override
                        public void run() {
                            changeB(res, j2);
                        }
                    });
                    turn = true;
                    while (res2 == null) {
                    }
                    turn = false;
                    if ((i1 == i2 && i2 == i3 && i3 == j)
                            || (i4 == i5 && i5 == i6 && i6 == j)
                            || (i7 == i8 && i8 == i9 && i9 == j)
                            || (i1 == i5 && i5 == i9 && i9 == j)
                            || (i3 == i5 && i5 == i7 && i7 == j)
                            || (i1 == i4 && i4 == i7 && i7 == j)
                            || (i2 == i5 && i5 == i8 && i8 == j)
                            || (i3 == i6 && i6 == i9 && i9 == j)) {
                        runOnUiThread(new Runnable() {
                            @Override
                            public void run() {
                                new AlertDialog.Builder(MainActivity.this)
                                        .setTitle("Vencedor")
                                        .setMessage("Parabéns Você venceu!")
                                        .setPositiveButton("Ok", new DialogInterface.OnClickListener() {
                                            public void onClick(DialogInterface dialog, int which) {
                                                // continue with delete
                                            }
                                        })
                                        .setIcon(android.R.drawable.ic_dialog_alert)
                                        .show();
                            }
                        });
                        res2 = j + ":venceu:" + res2;
                        out.println(res2);
                        res2 = null;
                        break;
                    }
                    if (i1 != 0 && i2 != 0 && i3 != 0 && i4 != 0 && i5 != 0 && i6 != 0 && i7 != 0 && i8 != 0 && i9 != 0) {
                        runOnUiThread(new Runnable() {
                            @Override
                            public void run() {
                                new AlertDialog.Builder(MainActivity.this)
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
                        });
                        res2 = "empate";
                        out.println(res2);
                        res2 = null;
                        break;
                    }
                    out.println(res2);
                    res2 = null;
                    res = in.readLine();
                }
                if (res.contains("perdeu")) {
                    res = in.readLine();
                    runOnUiThread(new Runnable() {
                        @Override
                        public void run() {
                            changeB(res, j2);
                            new AlertDialog.Builder(MainActivity.this)
                                    .setTitle("Perdedor")
                                    .setMessage("Infelizmente nao foi dessa vez!")
                                    .setPositiveButton("Ok", new DialogInterface.OnClickListener() {
                                        public void onClick(DialogInterface dialog, int which) {
                                            // continue with delete
                                        }
                                    })
                                    .setIcon(android.R.drawable.ic_dialog_alert)
                                    .show();
                        }
                    });
                } else if (res.contains("cancelou")) {
                    runOnUiThread(new Runnable() {
                        @Override
                        public void run() {
                            new AlertDialog.Builder(MainActivity.this)
                                    .setTitle("Erro")
                                    .setMessage("Infelizmente parece que o outro jogador desistiu desse jogo!")
                                    .setPositiveButton("Ok", new DialogInterface.OnClickListener() {
                                        public void onClick(DialogInterface dialog, int which) {
                                            // continue with delete
                                        }
                                    })
                                    .setIcon(android.R.drawable.ic_dialog_alert)
                                    .show();
                        }
                    });
                } else if (res.contains("empate")) {
                    runOnUiThread(new Runnable() {
                        @Override
                        public void run() {

                            new AlertDialog.Builder(MainActivity.this)
                                .setTitle("Empate")
                                .setMessage("Infelizmente ninguém ganhou!")
                                .setPositiveButton("Ok", new DialogInterface.OnClickListener() {
                                    public void onClick(DialogInterface dialog, int which) {
                                        // continue with delete
                                    }
                                })
                                .setIcon(android.R.drawable.ic_dialog_alert)
                                .show();
                         }
                    });
                }

                socket.close();
            } catch (IOException e) {
            }
            return "";
        }

        protected void onPostExecute(String result) {
            //resultis the data returned from doInbackground
            //MainActivity.this.data = result;

            //if (MainActivity.this.pd != null) {
            //    MainActivity.this.pd.dismiss();
            //}
        }
    }
}