package com.sungkyu.catchlion;

import android.content.Intent;
import android.view.View;
import android.widget.LinearLayout;

import static android.R.attr.x;
import static com.sungkyu.catchlion.MainActivity.valus;

/**
 * Created by sungkyu on 2017-11-10.
 */

public class pawn_move_control {

    int drawFlag;
    int[][] moveRecord;
    LinearLayout p1turn,p2turn;

    public pawn_move_control(LinearLayout p1t,LinearLayout p2t){
        drawFlag=0;
        moveRecord=new int[6][4];
        p1turn = p1t;
        p2turn = p2t;
    }

    public void setStartGame(com.sungkyu.catchlion.SquareButton[][] button,com.sungkyu.catchlion.SquareButton[][] hand){
        valus.setBoard_state(0,0,7); valus.setBoard_state(0,1,6); valus.setBoard_state(0,2,8);
        valus.setBoard_state(1,0,0); valus.setBoard_state(1,1,9); valus.setBoard_state(1,2,0);
        valus.setBoard_state(2,0,0); valus.setBoard_state(2,1,4); valus.setBoard_state(2,2,0);
        valus.setBoard_state(3,0,3); valus.setBoard_state(3,1,1); valus.setBoard_state(3,2,2);
        valus.setPlayer_hand_pawn_state(hand,0,0,2);
        for(int i=0;i<4;i++){
            for (int j = 0; j<3;j++){
                valus.setImages(button,i,j,0);
            }
        }
        for (int i=0;i<2;i++){
            for (int j=0;j<6;j++){
                valus.setImages(hand,i,j,1);
            }
        }
    }

    public void resetMark(com.sungkyu.catchlion.SquareImageView[][] mark,int where){ // 0:board 1:hand
        if(where==0){
            for (int i=0;i<4;i++){
                for(int j=0;j<3;j++){
                    mark[i][j].setVisibility(View.INVISIBLE);
                }
            }
        }
        else if(where==1){
            for (int i=0;i<2;i++){
                for(int j=0;j<6;j++){
                    mark[i][j].setVisibility(View.INVISIBLE);
                }
            }
        }
    }

    public void move(com.sungkyu.catchlion.SquareButton[][] button,com.sungkyu.catchlion.SquareButton[][] hand,int from_i,int from_j,int to_i,int to_j){ //called after canMove


        valus.setPhase(0);
        if (valus.getTurn()==1) {
            switch (valus.getBoard_state(to_i, to_j)) {
                case 6:
                    valus.setPlayer_hand_pawn_state(hand,1,valus.getBoard_state(to_i,to_j)-5,1);
                    victory(1);
                    break;
                case 7:
                case 8:
                case 9:
                    valus.setPlayer_hand_pawn_state(hand,1,valus.getBoard_state(to_i,to_j)-5,1);
                    //sethandsetate
                    break;
                case 10:
                    valus.setPlayer_hand_pawn_state(hand,1,4,1);
                    //sethandsetate
                    break;
                default:
                    break;
            }
            if(valus.getBoard_state(from_i,from_j)==4&&to_i==0){
                valus.setBoard_state(from_i,from_j,5);
            }
            if (valus.getBoard_state(from_i,from_j)==1&&to_i==0){
                if (valus.getWinFlag()!=4) {
                    valus.setWinFlag(3);
                }
            }
        }

        else if (valus.getTurn()==2) {
            switch (valus.getBoard_state(to_i, to_j)) {
                case 1:
                    valus.setPlayer_hand_pawn_state(hand,2,valus.getBoard_state(to_i,to_j)+5,1);
                    victory(2);
                    break;
                case 2:
                case 3:
                case 4:
                    valus.setPlayer_hand_pawn_state(hand,2,valus.getBoard_state(to_i,to_j)+5,1);
                    break;
                case 5:
                    valus.setPlayer_hand_pawn_state(hand,2,9,1);
                    break;
                case 0:
                    break;
            }
            if(valus.getBoard_state(from_i,from_j)==9&&to_i==3){
                valus.setBoard_state(from_i,from_j,10);
            }
            if (valus.getBoard_state(from_i,from_j)==6&&to_i==3){
                if (valus.getWinFlag()!=3) {
                    valus.setWinFlag(4);
                }
            }
        }
        valus.setBoard_state(to_i,to_j,valus.getBoard_state(from_i,from_j));
        valus.setImages(button,to_i,to_j,0);
        valus.setBoard_state(from_i,from_j,0);
        valus.setImages(button,from_i,from_j,0);
        draw_fig(from_i,from_j,to_i,to_j,1);
        turnEnd();
    }

    public boolean canMove(int from_i,int from_j,int to_i,int to_j){
        int pawn_type =valus.getBoard_state(from_i,from_j);

        if(to_i<0||to_i>3||to_j<0||to_j>2){ //out of board
            return false;
        }

        if(from_i==to_i && from_j == to_j){ //no move
            return false;
        }


        int to_state =valus.getBoard_state(to_i,to_j);

        int diff_i=from_i-to_i;
        int diff_j=from_j-to_j;

        switch (pawn_type){
            case 1: //p1 lion
                if(to_state!=0&&to_state<6) return false;
                if(diff_i>-2 && diff_i<2 && diff_j>-2 && diff_j<2) return true;
                break;
            case 2: //p1 dog
                if(to_state!=0&&to_state<6) return false;
                if((diff_i==1&&diff_j==0)||(diff_i==-1&&diff_j==0)||(diff_i==0&&diff_j==1)||(diff_i==0&&diff_j==-1)) return true;
                break;
            case 3: //p1 cat
                if(to_state!=0&&to_state<6) return false;
                if((diff_i==1&&diff_j==1)||(diff_i==1&&diff_j==-1)||(diff_i==-1&&diff_j==1)||(diff_i==-1&&diff_j==-1)) return true;
                break;
            case 4: //p1 chick
                if(to_state!=0&&to_state<6) return false;
                if((diff_i==1&&diff_j==0)) return true;
                break;
            case 5: //p1 niwatori
                if(to_state!=0&&to_state<6) return false;
                if(diff_i>-2 && diff_i<2 && diff_j>-2 && diff_j<2){
                    if((diff_i==-1&&diff_j==1)||(diff_i==-1&&diff_j==-1)) return false;
                    return true;
                }
                break;
            case 6: //p2 lion
                if(to_state>5) return false;
                if(diff_i>-2 && diff_i<2 && diff_j>-2 && diff_j<2) return true;
                break;
            case 7: //p2 dog
                if(to_state>5) return false;
                if((diff_i==1&&diff_j==0)||(diff_i==-1&&diff_j==0)||(diff_i==0&&diff_j==1)||(diff_i==0&&diff_j==-1)) return true;
                break;
            case 8: //p2 cat
                if(to_state>5) return false;
                if((diff_i==1&&diff_j==1)||(diff_i==1&&diff_j==-1)||(diff_i==-1&&diff_j==1)||(diff_i==-1&&diff_j==-1)) return true;
                break;
            case 9: //p1 chick
                if(to_state>5) return false;
                if((diff_i==-1&&diff_j==0)) return true;
                break;
            case 10: //p2 niwatori
                if(to_state>5) return false;
                if(diff_i>-2 && diff_i<2 && diff_j>-2 && diff_j<2){
                    if((diff_i==1&&diff_j==1)||(diff_i==1&&diff_j==-1)) return false;
                    return true;
                }
        }

        return false;
    }

    public  void where_can_move(com.sungkyu.catchlion.SquareImageView[][] Mark ,int pos_i,int pos_j){
        for(int i = pos_i-1;i<pos_i+2;i++){
            for (int j = pos_j -1;j<pos_j+2;j++){
                if(canMove(pos_i,pos_j,i,j)){
                    Mark[i][j].setVisibility(View.VISIBLE);
                }
            }
        }
        Mark[pos_i][pos_j].setVisibility(View.VISIBLE);
        valus.setPhase(1);
        valus.setPrePos(pos_i,pos_j);
    }

    public void turnEnd(){
        if(valus.getGame_mode()==1){
            if(valus.getTurn()==1){
                if(valus.getWinFlag()==4){
                    victory(2);
                }
                else {
                    p1turn.setVisibility(View.INVISIBLE);
                    p2turn.setVisibility(View.VISIBLE);
                    valus.setTurn(2);
                }
            }
            else{
                if(valus.getWinFlag()==3){
                    victory(1);
                }
                else {
                    p1turn.setVisibility(View.VISIBLE);
                    p2turn.setVisibility(View.INVISIBLE);
                    valus.setTurn(1);
                }
            }
        }
        else if(valus.getGame_mode()==0){
            if(valus.getTurn()==1){
                if(valus.getWinFlag()==7){
                    victory(6);
                }
                else {
                    valus.setTurn(0);
                }
            }
            else{
                if(valus.getWinFlag()==3){
                    victory(1);
                }
                else {
                    valus.setTurn(1);
                }
            }
        }
    }

    public void useHand(com.sungkyu.catchlion.SquareButton[][] button,com.sungkyu.catchlion.SquareButton[][] hand,int from_i,int from_j,int to_i,int to_j){
        int use_pawn=valus.getPlayer_hand_pawn_state(from_i,from_j);
        valus.setBoard_state(to_i,to_j,use_pawn);
        valus.setImages(button,to_i,to_j,0);
        valus.setPlayer_hand_pawn_state(hand,from_i+1,use_pawn,0);
        draw_fig(0,0,0,0,0);
        valus.setPhase(0);
        turnEnd();
    }

    public boolean canUseHand(int from_i,int from_j,int to_i,int to_j){
        int pawn_type =valus.getPlayer_hand_pawn_state(from_i,from_j);
        if(valus.getBoard_state(to_i,to_j)==0) return true;
        return false;
    }

    public void where_can_UseHand(com.sungkyu.catchlion.SquareImageView[][] Mark,com.sungkyu.catchlion.SquareImageView[][] HandMark ,int pos_i,int pos_j){
        for (int i=0;i<4;i++){
            for (int j =0;j<3;j++){
                if(canUseHand(pos_i,pos_j,i,j)){
                    Mark[i][j].setVisibility(View.VISIBLE);
                }
            }
        }
        HandMark[pos_i][pos_j].setVisibility(View.VISIBLE);
        valus.setPhase(5);
        valus.setPrePos(pos_i,pos_j);
    }

    public void draw_fig(int from_x,int from_y,int to_x,int to_y,int set) { // set 0: useHand, 1:Move
        if (set == 1) {
            if (drawFlag == 0 || drawFlag == 1) {
                moveRecord[drawFlag][0] = from_x;
                moveRecord[drawFlag][1] = from_y;
                moveRecord[drawFlag][2] = to_x;
                moveRecord[drawFlag][3] = to_y;
                drawFlag++;
            }
            else {
                if ((moveRecord[drawFlag - 2][0] == to_x) && (moveRecord[drawFlag - 2][1] == to_y) &&
                        (moveRecord[drawFlag - 2][2] == from_x) && (moveRecord[drawFlag - 2][3] == from_y)) {
                    moveRecord[drawFlag][0] = from_x;
                    moveRecord[drawFlag][1] = from_y;
                    moveRecord[drawFlag][2] = to_x;
                    moveRecord[drawFlag][3] = to_y;
                    drawFlag++;
                }
                else{
                    drawFlag=0;
                }
            }

            if(drawFlag==6){
                victory(5);
            }

        }

        else{
            drawFlag=0;
        }
    }

    public void victory(int player){
        valus.setWinFlag(player);
        valus.setPhase(10);
        return;
    }

}
