package com.charliea.minesweeper;

import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.provider.FontRequest;

import android.os.Bundle;
import android.view.View;
import android.widget.Button;

import java.util.ArrayList;
import java.util.concurrent.ThreadLocalRandom;


public class MainActivity extends AppCompatActivity {
    public boolean flagging = true;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        setContentView(R.layout.activity_main);

        //
        Button toggler = findViewById(R.id.toggler);
        toggler.setOnClickListener(
                new Button.OnClickListener(){
                    public void onClick(View v){
                        if(flagging) {
                            toggler.setText(getResources().getText(R.string.flag));
                            flagging=false;
                        }
                        else{
                            toggler.setText(getResources().getText(R.string.pick));
                            flagging = true;
                        }
                    }
                }
        );


        ArrayList<gameButton> btns = new ArrayList<gameButton>();
        //gross code but based on early design decision
        btns.add(findViewById(R.id._00));
        btns.add(findViewById(R.id._01));
        btns.add(findViewById(R.id._02));
        btns.add(findViewById(R.id._03));
        btns.add(findViewById(R.id._04));
        btns.add(findViewById(R.id._05));
        btns.add(findViewById(R.id._06));
        btns.add(findViewById(R.id._07));
        btns.add(findViewById(R.id._10));
        btns.add(findViewById(R.id._11));
        btns.add(findViewById(R.id._12));
        btns.add(findViewById(R.id._13));
        btns.add(findViewById(R.id._14));
        btns.add(findViewById(R.id._15));
        btns.add(findViewById(R.id._16));
        btns.add(findViewById(R.id._17));
        btns.add(findViewById(R.id._20));
        btns.add(findViewById(R.id._21));
        btns.add(findViewById(R.id._22));
        btns.add(findViewById(R.id._23));
        btns.add(findViewById(R.id._24));
        btns.add(findViewById(R.id._25));
        btns.add(findViewById(R.id._26));
        btns.add(findViewById(R.id._27));
        btns.add(findViewById(R.id._30));
        btns.add(findViewById(R.id._31));
        btns.add(findViewById(R.id._32));
        btns.add(findViewById(R.id._33));
        btns.add(findViewById(R.id._34));
        btns.add(findViewById(R.id._35));
        btns.add(findViewById(R.id._36));
        btns.add(findViewById(R.id._37));
        btns.add(findViewById(R.id._40));
        btns.add(findViewById(R.id._41));
        btns.add(findViewById(R.id._42));
        btns.add(findViewById(R.id._43));
        btns.add(findViewById(R.id._44));
        btns.add(findViewById(R.id._45));
        btns.add(findViewById(R.id._46));
        btns.add(findViewById(R.id._47));
        btns.add(findViewById(R.id._50));
        btns.add(findViewById(R.id._51));
        btns.add(findViewById(R.id._52));
        btns.add(findViewById(R.id._53));
        btns.add(findViewById(R.id._54));
        btns.add(findViewById(R.id._55));
        btns.add(findViewById(R.id._56));
        btns.add(findViewById(R.id._57));
        btns.add(findViewById(R.id._60));
        btns.add(findViewById(R.id._61));
        btns.add(findViewById(R.id._62));
        btns.add(findViewById(R.id._63));
        btns.add(findViewById(R.id._64));
        btns.add(findViewById(R.id._65));
        btns.add(findViewById(R.id._66));
        btns.add(findViewById(R.id._67));
        btns.add(findViewById(R.id._70));
        btns.add(findViewById(R.id._71));
        btns.add(findViewById(R.id._72));
        btns.add(findViewById(R.id._73));
        btns.add(findViewById(R.id._74));
        btns.add(findViewById(R.id._75));
        btns.add(findViewById(R.id._76));
        btns.add(findViewById(R.id._77));
        btns.add(findViewById(R.id._80));
        btns.add(findViewById(R.id._81));
        btns.add(findViewById(R.id._82));
        btns.add(findViewById(R.id._83));
        btns.add(findViewById(R.id._84));
        btns.add(findViewById(R.id._85));
        btns.add(findViewById(R.id._86));
        btns.add(findViewById(R.id._87));
        btns.add(findViewById(R.id._90));
        btns.add(findViewById(R.id._91));
        btns.add(findViewById(R.id._92));
        btns.add(findViewById(R.id._93));
        btns.add(findViewById(R.id._94));
        btns.add(findViewById(R.id._95));
        btns.add(findViewById(R.id._96));
        btns.add(findViewById(R.id._97));

        //initialize 4 random indices for buttons  on which to place bombs
        ArrayList<Integer> rands = new ArrayList<Integer>();
        for(int i = 0; i < 4; i++) {
            int rand = ThreadLocalRandom.current().nextInt(0, 80);
            while(rands.contains(rand)){
                rand = ThreadLocalRandom.current().nextInt(0, 80);
            }
            rands.add(rand);
        }

        for(int i : rands){
            btns.get(i).setMine();
        }


        int n = 0;
        for(gameButton i : btns){
            //check above/left for mine (nothing on n % 8=0 or n < 8)
            if( !((n%8==0) || n < 8)){
                if(btns.get(n-9).getMineStat())
                    i.incrementMines();
            }
            //check above (-8) (nothing on < 8)
            if(!(n<8)){
                if(btns.get(n-8).getMineStat())
                    i.incrementMines();
            }
            //check above right (-7) (edge on 7 15 23 31 or (n+1) %8 == 0)
            if (! ((n<8) || (((n+1)%8) == 0)) ) {
                if(btns.get(n-7).getMineStat()){
                    i.incrementMines();
                }
            }
            //check right (nothing on (n+1) % 8 == 0)
            if( !((n+1)%8==0) ){
                if(btns.get(n+1).getMineStat()){
                    i.incrementMines();
                }
            }
            //check right/below (nothing on (n+1) % 8 == 0) or (n > 71)
            if( !( ((n+1)%8==0) || (n>71) )){
                if(btns.get(n+9).getMineStat()){
                    i.incrementMines();
                }
            }
            //check below (nothing on n>71)
            if(!(n>71)){
                if(btns.get(n+8).getMineStat()){
                    i.incrementMines();
                }
            }
            //check below/left (nothing on n>71 or n%8==0)
            if(!((n>71)||(n%8==0))){
                if(btns.get(n+7).getMineStat()){
                    i.incrementMines();
                }
            }
            //check left (nothing on n%8==0)
            if(!(n%8==0)){
                if(btns.get(n-1).getMineStat()){
                    i.incrementMines();
                }
            }


            i.setOnClickListener(
                    new gameButton.OnClickListener(){
                        public void onClick(View v){
                            if(i.clicked(flagging)){
                                //lose
                            }
                        }
                    }
            );
            n++;
        }

    }
    //gameButton guy = findViewById(R.id._01);

    public void onClick(View view)
    {
        switch (view.getId()) {
            case R.id.toggler:
                Button tog = findViewById(R.id.toggler);
                if(flagging) {
                    tog.setText(getResources().getText(R.string.flag));
                    flagging=false;
                }
                else{
                    tog.setText(getResources().getText(R.string.pick));
                    flagging = true;
                }
            case R.id._02:
                gameButton g = findViewById(R.id._02);
                g.clicked(flagging);

        }
    }

}