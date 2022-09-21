package com.charliea.minesweeper;
import android.content.Context;
import android.util.AttributeSet;

import android.widget.Button;

import java.util.ArrayList;

public class gameButton extends androidx.appcompat.widget.AppCompatButton{
    private boolean hasMine = false;
    private boolean flagged = false;
    private int mines = 0;
    private int id;
    private boolean already_clicked = false;



    public gameButton(Context context) {
        super(context);
    }

    public gameButton(Context context, AttributeSet attrs) {
        super(context, attrs);
    }

    public gameButton(Context context, AttributeSet attrs, int defStyle) {
        super(context, attrs, defStyle);
    }

    public void setId(int i){
        this.id = i;
    }
    public int getId(){
        return this.id;
    }

    public void incrementMines(){
        this.mines+=1;
    }
    public void setMine(){
        this.hasMine = true;
    }
    public void setNumMines(int n){
        this.mines = n;
    }
    public boolean getMineStat(){
        return hasMine;
    }

    //return -1 for removing flag
    // return -2 for adding flag
    //return 0 if neutral
    //return 1 if mine clicked
    //return 2 if empty space (no adjacent bombs) clicked
    public int clicked(boolean flagging) {
        //flagging
        if (flagging) {
            if (this.flagged) {
                this.flagged = false;
                this.setText("");
                return -1;
            } else if (this.already_clicked) {
                return 0;
            }
            this.flagged = true;
            this.setText(getResources().getText(R.string.flag));
            return -2;
        }

        //not flagging
        else {
            if (this.flagged || this.already_clicked) {
                return 0;
            }
            if (this.hasMine) {
                this.setBackgroundTintList(getResources().getColorStateList(R.color.beige));
                this.setText(getResources().getText(R.string.mine));
                this.already_clicked = true;
                return 1;
            }
            // insert mine number
            else if (this.mines > 0) {
                this.already_clicked = true;
                this.setBackgroundTintList(getResources().getColorStateList(R.color.beige));

                this.setText(String.valueOf(this.mines));
                return 3;
            }

            //case blank square
            this.already_clicked = true;
            this.setBackgroundTintList(getResources().getColorStateList(R.color.beige));
            this.setText("");
            return 2;

        }
    }

    public void reveal(){
        this.already_clicked=true;
        this.setBackgroundTintList(getResources().getColorStateList(R.color.beige));
        if(this.mines > 0)
            this.setText(String.valueOf(this.mines));
        else
            this.setText("");
    }

    public int revealRecurse(ArrayList<gameButton> btns){
        //if(this.already_clicked){
        //    return 0;
        //}
        this.reveal();
        if(this.mines>0){
            return 1;
        }
        int count = 1;
        if( !((id%8==0) || id < 8)){

            if(!btns.get(id-9).isClear())
                count += btns.get(id-9).revealRecurse(btns);

        }
        //check above (-8) (nothing on < 8)
        if(!(id<8)){

            if(!btns.get(id-8).isClear() && !btns.get(id-8).flagged)
                count += btns.get(id-8).revealRecurse(btns);

        }
        /*
        //check above right (-7) (edge on 7 15 23 31 or (n+1) %8 == 0)
        if (! ((id<8) || (((id+1)%8) == 0)) ) {

            if(!btns.get(id-7).isClear())
                count += btns.get(id-7).revealRecurse(btns);
        }*/
        //check right (nothing on (n+1) % 8 == 0)
        if( !((id+1)%8==0) ){

            if(!btns.get(id+1).isClear() && !btns.get(id+1).flagged)
                count += btns.get(id+1).revealRecurse(btns);
        }
        /*
        //check right/below (nothing on (n+1) % 8 == 0) or (n > 71)
        if( !( ((id+1)%8==0) || (id>71) )){

            if(!btns.get(id+9).isClear())
                count += btns.get(id+9).revealRecurse(btns);
        }*/
        //check below (nothing on n>71)
        if(!(id>71)){

            if(!btns.get(id+8).isClear() && !btns.get(id+8).flagged)
                count += btns.get(id+8).revealRecurse(btns);
        }
        /*
        //check below/left (nothing on n>71 or n%8==0)
        if(!((id>71)||(id%8==0))){

            if(!btns.get(id+7).isClear())
                count += btns.get(id+7).revealRecurse(btns);
        }*/
        //check left (nothing on n%8==0)
        if(!(id%8==0)){

            if(!btns.get(id-1).isClear() && !btns.get(id-1).flagged)
                count += btns.get(id-1).revealRecurse(btns);
        }
        return count;
    }

    public boolean isClear(){
        return this.already_clicked;
    }


}
