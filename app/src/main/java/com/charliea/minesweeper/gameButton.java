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

    public void setTxt(String in) {
        this.setText(in);
    }

    public void clicked(){
        if(this.flagged || this.already_clicked){
            return;
        }
        if(this.hasMine){
            String res = getText(R.string.mine.toString());
            this.setText();
        }
        else if(this.mines > 0){

        }

    }

    private void changeBgColor() {
        //setBackgroundResource(isRed ? R.drawable.bg_red : R.drawable.bg_green);
        //setText(isRed? "Red" : "Green");
    }
}
