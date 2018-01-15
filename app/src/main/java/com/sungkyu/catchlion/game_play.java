package com.sungkyu.catchlion;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.util.Log;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.LinearLayout;

import static com.sungkyu.catchlion.MainActivity.valus;

/**
 * Created by sungkyu on 2017-11-08.
 */

public class game_play extends Activity implements View.OnClickListener {


    private  com.sungkyu.catchlion.SquareButton[][] board=new com.sungkyu.catchlion.SquareButton[4][3]; //가운데 판
    private  int[][] boardId=new int[4][3];

    private com.sungkyu.catchlion.SquareButton[][] pawn = new com.sungkyu.catchlion.SquareButton[2][6];
    private int[][] pawnId = new int[2][6];

    private  com.sungkyu.catchlion.SquareImageView mark[][] = new com.sungkyu.catchlion.SquareImageView[4][3];
    private  int[][] markId = new int[4][3];

    private com.sungkyu.catchlion.SquareImageView[][] handMark = new com.sungkyu.catchlion.SquareImageView[2][6];
    private int[][] handMarkId = new int[2][6];

    private  LinearLayout p1turn,p2turn;

    pawn_move_control pawnMoveControl;



    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.game_play);

        p1turn = (LinearLayout)findViewById(R.id.p1turn);
        p2turn = (LinearLayout)findViewById(R.id.p2turn);

        valus.setTurn(1);
        valus.setPhase(0);

        pawnMoveControl = new pawn_move_control(p1turn,p2turn);
        for (int i= 0; i < 4;i++){ //가운데 판 버튼 초기화
            for(int j = 0;j<3;j++){
                boardId[i][j]=getResources().getIdentifier("Grid"+(i+1)+""+(j+1),"id",getPackageName());
                board[i][j]= (com.sungkyu.catchlion.SquareButton)findViewById(boardId[i][j]);
                board[i][j].setTag((i*3)+j);
                board[i][j].setOnClickListener(this);
            }
        }
        for (int i = 0; i < 2; i++){
            for (int j = 0; j< 6; j++){
                pawnId[i][j]=getResources().getIdentifier("p"+(i+1)+"pawn"+(j+1),"id",getPackageName());
                pawn[i][j]= (com.sungkyu.catchlion.SquareButton)findViewById(pawnId[i][j]);
                pawn[i][j].setTag(12+(i*6)+j);
                pawn[i][j].setOnClickListener(this);
            }
        }
        for (int i= 0; i < 4;i++){
            for(int j = 0;j<3;j++){
                markId[i][j]=getResources().getIdentifier("Mark"+(i+1)+""+(j+1),"id",getPackageName());
                mark[i][j]= (com.sungkyu.catchlion.SquareImageView)findViewById(markId[i][j]);
            }
        }

        for (int i = 0; i < 2; i++){
            for (int j = 0; j< 6; j++){
                handMarkId[i][j]=getResources().getIdentifier("p"+(i+1)+"Mark"+(j+1),"id",getPackageName());
                handMark[i][j]= (com.sungkyu.catchlion.SquareImageView)findViewById(handMarkId[i][j]);

            }
        }

        pawnMoveControl.setStartGame(board,pawn);



    }

    @Override
    public void onClick(View view) {
        int id =Integer.parseInt(view.getTag().toString());
        int i;
        int j;
        if(id<12){
            i = id/3; j= id %3;
        }
        else if(id>11&&id<24){
            i=(id-12)/6; j=(id-12)%6;
        }
        else {
            i=0; j=0;
        }
        switch (valus.getPhase()){
            case 0:
                if(id<12&&(valus.getTurn()==1)&&((valus.getBoard_state(i,j)>5)||valus.getBoard_state(i,j)==0)) return;
                if((valus.getTurn()==1)&&((id>17&&id<24)||((id>11&&id<18)&&valus.getPlayer_hand_pawn_state(i,j)==0))) return;
                if(id<12&&(valus.getTurn()==2)&&(valus.getBoard_state(i,j)<6)) return;
                if((valus.getTurn()==2)&&((id>11&&id<18)||((id>17&&id<24)&&valus.getPlayer_hand_pawn_state(i,j)==0))) return;


                if(id<12){
                    pawnMoveControl.where_can_move(mark,i,j);
                }

                if(id>11){
                    pawnMoveControl.where_can_UseHand(mark,handMark,i,j);
                }


                break;
            case 1:
                if(id<12){
                    if(pawnMoveControl.canMove(valus.getPre_i(),valus.getPre_j(),i,j)){
                        pawnMoveControl.move(board,pawn,valus.getPre_i(),valus.getPre_j(),i,j);
                        pawnMoveControl.resetMark(mark,0);
                    }
                    else{ //canceled
                        valus.setPhase(0);
                        pawnMoveControl.resetMark(mark,0);
                    }
                }
                else{ //canceled
                    valus.setPhase(0);
                    pawnMoveControl.resetMark(mark,0);
                }
                break;
            case 5:
                if(id<12){
                    if(pawnMoveControl.canUseHand(valus.getPre_i(),valus.getPre_j(),i,j)){
                        pawnMoveControl.useHand(board,pawn,valus.getPre_i(),valus.getPre_j(),i,j);
                        pawnMoveControl.resetMark(mark,0);
                        pawnMoveControl.resetMark(handMark,1);
                    }
                    else{ //canceled
                        valus.setPhase(0);
                        pawnMoveControl.resetMark(mark,0);
                        pawnMoveControl.resetMark(handMark,1);
                    }
                }
                else{ //canceled
                    valus.setPhase(0);
                    pawnMoveControl.resetMark(mark,0);
                    pawnMoveControl.resetMark(handMark,1);
                }
                break;
            case 10:
                Intent intent = new Intent(this, result_activity.class);
                startActivity(intent);
                finish();
                break;
        }
    }
}
