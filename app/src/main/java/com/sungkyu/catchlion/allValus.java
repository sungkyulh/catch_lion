package com.sungkyu.catchlion;

import android.app.Application;
import android.graphics.Bitmap;

import static com.sungkyu.catchlion.MainActivity.valus;

/**
 * Created by sungkyu on 2017-11-10.
 */

public class allValus extends Application{
    private int game_mode; //0: single mode 1:two player mode
    private int board_state[][] = new int[4][3]; //0:blank 1:lion 2:dog 3:cat 4:chick 5:niwatori 6:2lion ...
    private int player_hand_pawn_state[][] = new int[2][6];
    private int turn;//1 : player 1's turn 2: player2's turn 0:com
    private int phase;
    private int winFlag;// 1:p1 win 2:p2win 3:nextturn if lion alives p1win 4:p2 5:draw 0:com win 7:next com win
    private int pre_i,pre_j;
    //private Bitmap lion,lion_r,cat,cat_r,dog,dog_r,chick,chick_r,niwatori,niwatori_r,background,boardGrid,invisible,mark;

    @Override
    public void onCreate() {
        super.onCreate();
        this.phase =0;
        this.winFlag=0;
    }

    @Override
    public void onTerminate() {
        super.onTerminate();
    }

    public void setGame_mode(int game_mode){
        this.game_mode=game_mode;
    }

    public int getGame_mode(){
        return this.game_mode;
    }

    public  void setBoard_state(int i,int j,int board_state){
        this.board_state[i][j]=board_state;
    }

    public int getBoard_state(int i,int j){
        return this.board_state[i][j];
    }

    public int[][] getBoard(){ return  board_state;}

    public void setPlayer_hand_pawn_state(com.sungkyu.catchlion.SquareButton[][] hand,int player,int pawn,int io){ //player : 1?2   pawn: lion,dog ...  io: 1 : input 0: output 2:reset
        if(io==1) {
            for (int i = 0; i < 6; i++) {
                if (this.player_hand_pawn_state[player-1][i] == 0) {
                    this.player_hand_pawn_state[player-1][i] = pawn;
                    this.setImages(hand,player-1,i,1);
                    return;
                }
            }
        }
        else if(io==0){
            for (int i = 0; i < 6; i++) {
                if (this.player_hand_pawn_state[player-1][i] == pawn) {
                    this.player_hand_pawn_state[player-1][i] = 0;
                    this.setImages(hand,player-1,i,1);
                    return;
                }
            }
        }
        else{
            for(int i=0;i<2;i++){
                for(int j=0;j<6;j++){
                    this.player_hand_pawn_state[i][j]=0;
                    this.setImages(hand,i,j,1);
                }
            }
        }
    }

    public int getPlayer_hand_pawn_state(int player,int pos){
        return this.player_hand_pawn_state[player][pos];
    }

    public void setTurn(int turn){
        this.turn=turn;
    }

    public int getTurn(){
        return this.turn;
    }

    public void setPhase(int phase){
        this.phase=phase;
    }

    public int getPhase(){
        return this.phase;
    }

    public void setPrePos(int i,int j){
        this.pre_i=i;
        this.pre_j=j;
    }

    public int getPre_i(){
        return this.pre_i;
    }

    public int getPre_j(){
        return this.pre_j;
    }

    public void setWinFlag(int p){
        this.winFlag=p;
    }

    public int getWinFlag(){
        return this.winFlag;
    }

    public void setImages(com.sungkyu.catchlion.SquareButton[][] button,int pos_i,int pos_j,int where){//0:board,1:hand
        int state;
        if(where==0) state = board_state[pos_i][pos_j];
        else {
            state=player_hand_pawn_state[pos_i][pos_j];
        }
        switch (state){
            case 1:
                button[pos_i][pos_j].setBackgroundResource(R.drawable.lion);
                break;
            case 2:
                button[pos_i][pos_j].setBackgroundResource(R.drawable.dog);
                break;
            case 3:
                button[pos_i][pos_j].setBackgroundResource(R.drawable.cat);
                break;
            case 4:
                button[pos_i][pos_j].setBackgroundResource(R.drawable.chick);
                break;
            case 5:
                button[pos_i][pos_j].setBackgroundResource(R.drawable.niwatori);
                break;
            case 6:
                button[pos_i][pos_j].setBackgroundResource(R.drawable.lion_r);
                break;
            case 7:
                button[pos_i][pos_j].setBackgroundResource(R.drawable.dog_r);
                break;
            case 8:
                button[pos_i][pos_j].setBackgroundResource(R.drawable.cat_r);
                break;
            case 9:
                button[pos_i][pos_j].setBackgroundResource(R.drawable.chick_r);
                break;
            case 10:
                button[pos_i][pos_j].setBackgroundResource(R.drawable.niwatori_r);
                break;
            case 0:
                button[pos_i][pos_j].setBackgroundResource(R.drawable.invisible);
                break;
        }
    }
}
