package com.example.selocan;

import android.os.Bundle;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;
import android.widget.TableLayout;
import android.widget.TableRow;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;

public class MainActivity extends AppCompatActivity {

    static final String PLayer_1_o = "X";
    static final String PLayer_2_o = "O";
    boolean playerTurn = true;

    byte[][] board = new byte[3][3];  //board için
    static final byte emty_value = 0;
    static final byte PLayer_1_value = 1;
    static final byte PLayer_2_value = 2;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);


        TableLayout tbl = findViewById(R.id.table);
        for (int i = 0; i < 3; i++) {


            TableRow tblrw = (TableRow) tbl.getChildAt(i);
            for (int j = 0; j < 3; j++) {
                Button btn_rw = (Button) tblrw.getChildAt(j);
                btn_rw.setOnClickListener(new gelburaya(i, j));

            }

        }


    }

    class gelburaya implements View.OnClickListener {
        int row, col;

        public gelburaya(int row, int col) {
            this.col = col;
            this.row = row;
        }

        byte playerValue = emty_value;

        @Override
        public void onClick(View v) {


            if (board[row][col] != emty_value) {
                Toast.makeText(MainActivity.this, "CELL WAS TAKEN ASlANIM :)",
                        Toast.LENGTH_LONG).show();
                return;
            }


            if (playerTurn) {
                ((Button) v).setText(PLayer_1_o);
                board[row][col] = PLayer_1_value;
                playerValue = PLayer_1_value;


            } else {
                ((Button) v).setText(PLayer_2_o);
                board[row][col] = PLayer_2_value;
                playerValue = PLayer_2_value;
            }
            playerTurn = !playerTurn;


            int a = oyunBittiAslanım(row, col, playerValue);
            if (a > 0) {
                Toast.makeText(MainActivity.this, "player " + a + " has won", Toast.LENGTH_SHORT).show();
                disableBoard(false);

            }
        }
    }

    public int oyunBittiAslanım(int row, int col, byte playerValue) {
        //cek et bakam kolonlkarı
        boolean win = true;
        for (int r = 0; r < 3; r++) {
            if (board[r][col] != playerValue) {
                win = false;
                break;
            }

        }
        if (win) {
            return playerValue;


        }

        //rowlara bakıyorum
        win=true;
        for (int c = 0; c < 3; c++) {
            if (board[row][c] != playerValue) {
                win = false;
                break;
            }

        }
        if (win) {
            return playerValue;


        }


        //chek bakam diagonals
        win=true;
        for (int c = 0; c < 3; c++) {
            if (board[c][c] != playerValue) {
                win = false;
                break;
            }

        }

        if (win) {
            return playerValue;


        }





        return -1;
    }

    void disableBoard(boolean enable) {
        for (int i = 0; i < 3; i++) {

            TableLayout tbl = findViewById(R.id.table);
            TableRow tblrw = (TableRow) tbl.getChildAt(i);
            for (int j = 0; j < 3; j++) {
                Button btn_rw = (Button) tblrw.getChildAt(j);
                btn_rw.setEnabled(enable);

            }

        }
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
            getMenuInflater().inflate(R.menu.menu, menu);
            return super.onCreateOptionsMenu(menu);



    }





    public boolean newGame(MenuItem item) {

        disableBoard(true);
        for (int i = 0; i < 3; i++) {
            for (int j = 0; j < 3; j++) {
                board[i][j] = emty_value;
            }
        }

        TableLayout tbl = findViewById(R.id.table);
        for (int i = 0; i < 3; i++) {


            TableRow tblrw = (TableRow) tbl.getChildAt(i);
            for (int j = 0; j < 3; j++) {
                Button btn_rw = (Button) tblrw.getChildAt(j);
                btn_rw.setText("");
            }

        }

        return true;

    }

}
