//package com.sungkyu.catchlion;
//
//
//import android.view.View;
//
//import static com.sungkyu.catchlion.MainActivity.valus;
//
///**
// * Created by sungkyu on 2017-11-13.
// */
//
//public class VirtualPlayer {
//
//    com.sungkyu.catchlion.SquareImageView[][] Mark;
//    com.sungkyu.catchlion.SquareButton[][] Board;
//    com.sungkyu.catchlion.SquareButton[][] Hand;
//    pawn_move_control pawnMoveControl = new pawn_move_control();
//
//    public VirtualPlayer(com.sungkyu.catchlion.SquareImageView mark[][],com.sungkyu.catchlion.SquareButton[][] hand,com.sungkyu.catchlion.SquareButton[][] board){
//        Mark = mark; Hand = hand; Board = board;
//    }
//
//
//    public boolean isInDanger(int[][] board,int pos_i,int pos_j,boolean lion,boolean comTurn){
//        if(lion){
//            int[] tmp =findLion(board,comTurn);
//            pos_i=tmp[0]; pos_j=tmp[1];
//        }
//        for (int i=pos_i-1;i<pos_i+2;i++){
//            for (int j=pos_j-1;j<pos_j+2;j++){
//                if(i<0||i>3||j<0||j>2) continue;
//                if(i==pos_i&&j==pos_j) continue;
//                if(comTurn) {
//                    if (board[i][j] != 0 && board[i][j] < 6) {
//                        if (pawnMoveControl.canMove(i, j, pos_i, pos_j)) {
//                            return true;
//                        }
//                    }
//                }
//                else {
//                    if (board[i][j] != 0 && board[i][j] > 5) {
//                        if (pawnMoveControl.canMove(i, j, pos_i, pos_j)) {
//                            return true;
//                        }
//                    }
//                }
//            }
//        }
//        return false;
//    }
//
//    public boolean canCatchLion(int[][] board,boolean comTurn) {
//        int lion_i=0;
//        int lion_j=0;
//        int[] tmp =findLion(board,!comTurn);
//        lion_i=tmp[0]; lion_j=tmp[1];
//
//        for (int i=lion_i-1;i<lion_i+2;i++){
//            for (int j=lion_j-1;j<lion_j+2;j++){
//                if(i<0||i>3||j<0||j>2) continue;
//                if(comTurn) {
//                    if (board[i][j] != 0 && board[i][j] < 6) {
//                        if (pawnMoveControl.canMove(i, j, lion_i, lion_j)) {
//                            return true;
//                        }
//                    }
//                }
//                else {
//                    if (board[i][j] != 0 && board[i][j] > 5) {
//                        if (pawnMoveControl.canMove(i, j, lion_i, lion_j)) {
//                            return true;
//                        }
//                    }
//                }
//            }
//        }
//        return false;
//    }
//
//    public boolean canGetTOGoal(int[][] board,boolean comTurn){
//        int pos_i;
//        int pos_j=-1;
//
//        if(comTurn) pos_i =2;
//        else pos_i=1;
//
//        for(int j =0;j<3;j++){
//            if(board[pos_i][j]==6){
//                pos_j=j;
//                break;
//            }
//        }
//
//        if(pos_j==-1) return false;
//        else{
//            for(int j = pos_j-1;j<pos_j+2;j++){
//                if(j<0||j>2) continue;
//                if(pawnMoveControl.canMove(pos_i,pos_j,3,j)){
//                    if(!isInDanger(board,3,j,true,comTurn)){
//                        return true;
//                    }
//                }
//            }
//        }
//        return false;
//    }
//
//    public int[] findLion(int[][] board,boolean comTurn){
//        int lion = 1;
//        int[] pos = new int[2];
//        if(comTurn){
//            lion =6;
//        }
//        for(int i=0;i<4;i++) {
//            for (int j = 0; j < 3; j++) {
//                if (board[i][j] == lion) {
//                    pos[0] = i;
//                    pos[1] = j;
//                    break;
//                }
//            }
//        }
//
//        return pos;
//    }
//
//    public void computeGame(){
//
//        if(canCatchLion(valus.getBoard(),true)) {
//            int lion_i = 0;
//            int lion_j = 0;
//            for (int i = 0; i < 4; i++) {
//                for (int j = 0; j < 3; j++) {
//                    if (valus.getBoard_state(i, j) == 1) {
//                        lion_i = i;
//                        lion_j = j;
//                        break;
//                    }
//                }
//            }
//            for (int i = lion_i - 1; i < lion_i + 2; i++) {
//                for (int j = lion_j - 1; j < lion_j + 2; j++) {
//                    if (i < 0 || i > 3 || j < 0 || j > 2) continue;
//                    if (valus.getBoard_state(i, j) != 0 && valus.getBoard_state(i, j) < 6) {
//                        if (pawnMoveControl.canMove(i, j, lion_i, lion_j)) {
//                            ComMove(i,j,lion_i,lion_j);
//                        }
//                    }
//                }
//            }
//        }
//
//        else if(canGetTOGoal(valus.getBoard(),true)) {
//            int pos_i = 2;
//            int pos_j = -1;
//            for (int j = 0; j < 3; j++) {
//                if (valus.getBoard_state(pos_i, j) == 6) {
//                    pos_j = j;
//                    break;
//                }
//            }
//            for (int j = pos_j - 1; j < pos_j + 2; j++) {
//                if (j < 0 || j > 2) continue;
//                if (pawnMoveControl.canMove(pos_i, pos_j, 3, j)) {
//                    if (!isInDanger(valus.getBoard(), 3, j, true, true)) {
//                        ComMove(2, pos_j, 3, j);
//                    }
//                }
//            }
//        }
//
//        else if(isInDanger(valus.getBoard(),0,0,true,true)){
//
//        }
//
//
//    }
//
//    public void ComMove(int from_i,int from_j,int to_i,int to_j){
//        Mark[from_i][from_j].setVisibility(View.VISIBLE);
//        Mark[to_i][to_j].setVisibility(View.VISIBLE);
//        this.sleep(1000);
//        pawnMoveControl.move(Board,Hand,from_i,from_j,to_i,to_j);
//        Mark[from_i][from_j].setVisibility(View.INVISIBLE);
//        this.sleep(500);
//        Mark[to_i][to_j].setVisibility(View.INVISIBLE);
//    }
//
//    public void sleep(int time){
//        try {
//            Thread.sleep(time);
//        } catch (InterruptedException e) { }
//    }
//}
