package com.charliea.minesweeper;
import android.content.Context;
import android.util.AttributeSet;

import android.widget.Button;

public class gameButton extends androidx.appcompat.widget.AppCompatButton{
    private boolean hasMine = false;
    private boolean flagged = false;
    private int mines = 0;
    public int x;
    public int y;
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

    public boolean clicked(boolean flagging) {
        //flagging
        if (flagging) {
            if (this.flagged) {
                this.flagged = false;
                this.setText("");
                return false;
            } else if (this.already_clicked) {
                return false;
            }
            this.flagged = true;
            this.setText(getResources().getText(R.string.flag));
            return false;
        }

        //not flagging
        else {
            if (this.flagged || this.already_clicked) {
                return false;
            }
            if (this.hasMine) {
                this.setBackgroundTintList(getResources().getColorStateList(R.color.beige));
                this.setText(getResources().getText(R.string.mine));
                this.already_clicked = true;
                return true;
            }
            // insert mine number
            else if (this.mines > 0) {
                this.already_clicked = true;
                this.setBackgroundTintList(getResources().getColorStateList(R.color.beige));

                this.setText(String.valueOf(this.mines));
                return false;
            }

            this.already_clicked = true;
            this.setBackgroundTintList(getResources().getColorStateList(R.color.beige));
            this.setText("");
            return false;

        }
    }





}
