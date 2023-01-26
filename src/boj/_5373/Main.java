package _5373;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;
import java.util.HashMap;
import java.util.LinkedList;

/*
 * https://www.acmicpc.net/problem/5373
 * 큐빙
 */

@FunctionalInterface
interface Direction{
    void move();
}

class Cube{
    int side;
    int point;

    public Cube(int side, int point) {
        this.side = side;
        this.point = point;
    }
}

public class Main {
    static BufferedReader br;
    static StringTokenizer st;
    static int T,n;
    static int[] pointCycle = {2,5,8,1,4,7,0,3,6};
    static int[] pointCycle_ = {6,3,0,7,4,1,8,5,2};
    static char[] color = new char[]{'w','g','r','b','o','y'};

    static HashMap<Integer,Integer> sideCycleLR = new HashMap<>();
    static HashMap<Integer,Integer> sideCycleLR_ = new HashMap<>();
    static HashMap<Integer,Integer> sideCycleUD = new HashMap<>();
    static HashMap<Integer,Integer> sideCycleUD_ = new HashMap<>();
    static HashMap<String,Direction> cmd;
    static Cube[] cube;

    public static void main(String[] args) throws IOException {
        br = new BufferedReader(new InputStreamReader(System.in));
        T = Integer.parseInt(br.readLine());

        sideCycleLR.put(0,2);
        sideCycleLR.put(2,5);
        sideCycleLR.put(5,4);

        sideCycleLR_.put(4,5);
        sideCycleLR_.put(5,2);
        sideCycleLR_.put(2,0);

        sideCycleUD.put(2,1);
        sideCycleUD.put(1,4);
        sideCycleUD.put(4,3);
        sideCycleUD.put(3,2);

        sideCycleUD_.put(4,1);
        sideCycleUD_.put(1,2);
        sideCycleUD_.put(2,3);
        sideCycleUD_.put(3,4);


        cmd = new HashMap<>();

        cmd.put("U+",new U());
        cmd.put("U-",new U_());
        cmd.put("D+",new D());
        cmd.put("D-",new D_());
        cmd.put("L+",new L());
        cmd.put("L-",new L_());
        cmd.put("R+",new R());
        cmd.put("R-",new R_());
        cmd.put("F+",new F());
        cmd.put("F-",new F_());
        cmd.put("B+",new B());
        cmd.put("B-",new B_());

        for(int test_case=1; test_case<=T; test_case++){
            cube = new Cube[9];
            n = Integer.parseInt(br.readLine());

            for(int i=0; i<9; i++){
                cube[i] = new Cube(0,i);
            }


            LinkedList<String> inputs = new LinkedList<>();


            st = new StringTokenizer(br.readLine());

            for(int i=0; i<n; i++){
                inputs.offer(st.nextToken());
            }

            while(!inputs.isEmpty()){
                cmd.get(inputs.pollLast()).move();
            }

            for(int i=0; i<3; i++){
                System.out.print(color[cube[i].side]);
            }
            System.out.println("");
            for(int i=3; i<6; i++){
                System.out.print(color[cube[i].side]);
            }
            System.out.println("");
            for(int i=6; i<9; i++){
                System.out.print(color[cube[i].side]);
            }
            System.out.println("");
        }
    }

    public static class U implements Direction{
        @Override
        public void move() {
            for(int i=0; i<9; i++){
                if(cube[i].side==0){
                    cube[i].point = pointCycle_[cube[i].point];
                }
                else if(cube[i].side!=5){
                    if(cube[i].point==0||cube[i].point==1||cube[i].point==2){
                        cube[i].side = sideCycleUD_.get(cube[i].side);
                    }
                }
            }
        }
    }
    public static class U_ implements Direction{
        @Override
        public void move() {
            for(int i=0; i<9; i++){
                if(cube[i].side==0) {
                    cube[i].point = pointCycle[cube[i].point];
                }
                else if(cube[i].side!=5){
                    if(cube[i].point==0||cube[i].point==1||cube[i].point==2){
                        cube[i].side = sideCycleUD.get(cube[i].side);
                    }
                }
            }
        }
    }
    public static class D implements Direction{
        @Override
        public void move() {
            for(int i=0; i<9; i++){
                if(cube[i].side==5){
                    cube[i].point = pointCycle_[cube[i].point];
                }
                else if(cube[i].side!=0){
                    if(cube[i].point==6||cube[i].point==7||cube[i].point==8){
                        cube[i].side = sideCycleUD.get(cube[i].side);
                    }
                }
            }
        }
    }
    public static class D_ implements Direction{
        @Override
        public void move() {
            for(int i=0; i<9; i++){
                if(cube[i].side==5) {
                    cube[i].point = pointCycle[cube[i].point];
                }
                else if(cube[i].side!=0){
                    if(cube[i].point==6||cube[i].point==7||cube[i].point==8){
                        cube[i].side = sideCycleUD_.get(cube[i].side);
                    }
                }
            }
        }
    }
    public static class L implements Direction{
        @Override
        public void move() {
            for (int i = 0; i < 9; i++) {
                if (cube[i].side == 1) {
                    cube[i].point = pointCycle_[cube[i].point];
                }
                else if (cube[i].side == 4) {
                    if (cube[i].point == 2 || cube[i].point == 5 || cube[i].point == 8) {
                        cube[i].point = 8 - cube[i].point;
                        cube[i].side = 5;
                    }
                }
                else if (cube[i].side == 0) {
                    if (cube[i].point == 0 || cube[i].point == 3 || cube[i].point == 6) {
                        cube[i].point = 8 - cube[i].point;
                        cube[i].side = 4;
                    }
                }
                else if (cube[i].side != 3) {
                    if (cube[i].point == 0 || cube[i].point == 3 || cube[i].point == 6) {
                        cube[i].side = sideCycleLR_.get(cube[i].side);
                    }
                }
            }
        }
    }
    public static class L_ implements Direction{
        @Override
        public void move() {
            for (int i=0; i<9; i++){
                if(cube[i].side==1) {
                    cube[i].point = pointCycle[cube[i].point];
                }
                else if(cube[i].side==4){
                    if(cube[i].point==2||cube[i].point==5||cube[i].point==8){
                        cube[i].point = 8-cube[i].point;
                        cube[i].side = 0;
                    }
                }
                else if(cube[i].side==5) {
                    if(cube[i].point==0||cube[i].point==3||cube[i].point==6){
                        cube[i].point = 8-cube[i].point;
                        cube[i].side = 4;
                    }
                }
                else if(cube[i].side!=3){
                    if(cube[i].point==0||cube[i].point==3||cube[i].point==6){
                        cube[i].side=sideCycleLR.get(cube[i].side);
                    }
                }

            }
        }
    }

    public static class R implements Direction{
        @Override
        public void move() {
            for (int i = 0; i < 9; i++) {
                if (cube[i].side == 3) {
                    cube[i].point = pointCycle_[cube[i].point];
                }
                else if (cube[i].side == 4) {
                    if (cube[i].point == 0 || cube[i].point == 3 || cube[i].point == 6) {
                        cube[i].point = 8 - cube[i].point;
                        cube[i].side = 0;
                    }
                }
                else if (cube[i].side == 5) {
                    if (cube[i].point == 2 || cube[i].point == 5 || cube[i].point == 8) {
                        cube[i].point = 8 - cube[i].point;
                        cube[i].side = 4;
                    }
                }
                else if (cube[i].side != 1) {
                    if (cube[i].point == 2 || cube[i].point == 5 || cube[i].point == 8) {
                        cube[i].side = sideCycleLR.get(cube[i].side);
                    }
                }
            }
        }
    }
    public static class R_ implements Direction{
        @Override
        public void move() {
            for (int i=0; i<9; i++){
                if(cube[i].side==3) {
                    cube[i].point = pointCycle[cube[i].point];
                }
                else if(cube[i].side==4){
                    if(cube[i].point==0||cube[i].point==3||cube[i].point==6){
                        cube[i].point = 8-cube[i].point;
                        cube[i].side = 5;
                    }
                }
                else if(cube[i].side==0) {
                    if(cube[i].point==2||cube[i].point==5||cube[i].point==8){
                        cube[i].point = 8-cube[i].point;
                        cube[i].side = 4;
                    }
                }
                else if(cube[i].side!=1){
                    if(cube[i].point==2||cube[i].point==5||cube[i].point==8){
                        cube[i].side=sideCycleLR_.get(cube[i].side);
                    }
                }

            }
        }
    }

    public static class F implements Direction {

        @Override
        public void move() {
            for(int i=0; i<9; i++){
                if(cube[i].side==2) {
                    cube[i].point=pointCycle_[cube[i].point];
                }
                else if(cube[i].side==0){
                    if(cube[i].point==6){
                        cube[i].point=8;
                        cube[i].side = 1;}
                    else if(cube[i].point==7){
                        cube[i].point=5;
                        cube[i].side = 1;}
                    else if(cube[i].point==8){
                        cube[i].point=2;
                        cube[i].side = 1;
                    }
                }
                else if(cube[i].side==3){
                    if(cube[i].point==0){
                        cube[i].point=6;
                        cube[i].side=0;
                    }
                    else if(cube[i].point==3){
                        cube[i].point=7;
                        cube[i].side=0;}
                    else if(cube[i].point==6){
                        cube[i].point=8;
                        cube[i].side=0;}
                }
                else if(cube[i].side==5){
                    if(cube[i].point==0){
                        cube[i].point=6;
                        cube[i].side=3;
                    }
                    else if(cube[i].point==1){
                        cube[i].point=3;
                        cube[i].side=3;}
                    else if(cube[i].point==2){
                        cube[i].point=0;
                        cube[i].side=3;}

                }
                else if(cube[i].side==1){
                    if(cube[i].point==2){cube[i].point=0;cube[i].side=5;}
                    else if(cube[i].point==5){cube[i].point=1;cube[i].side=5;}
                    else if(cube[i].point==8){cube[i].point=2;cube[i].side=5;}
                }
            }
        }
    }
    public static class F_ implements Direction {

        @Override
        public void move() {
            for(int i=0; i<9; i++){
                if(cube[i].side==2) {
                    cube[i].point=pointCycle[cube[i].point];
                }
                else if(cube[i].side==0){
                    if(cube[i].point==6){
                        cube[i].point=0;
                        cube[i].side = 3;}
                    else if(cube[i].point==7){
                        cube[i].point=3;
                        cube[i].side = 3;}
                    else if(cube[i].point==8){
                        cube[i].point=6;
                        cube[i].side = 3;
                    }
                }
                else if(cube[i].side==3){
                    if(cube[i].point==0){cube[i].point=2;cube[i].side=5;
                    }
                    else if(cube[i].point==3){cube[i].point=1;cube[i].side=5;}
                    else if(cube[i].point==6){cube[i].point=0;cube[i].side=5;}
                }
                else if(cube[i].side==5){
                    if(cube[i].point==0){cube[i].point=2;cube[i].side=1;}
                    else if(cube[i].point==1){cube[i].point=5;cube[i].side=1;}
                    else if(cube[i].point==2){cube[i].point=8;cube[i].side=1;}

                }
                else if(cube[i].side==1){
                    if(cube[i].point==2){cube[i].point=8;cube[i].side=0;}
                    else if(cube[i].point==5){cube[i].point=7;cube[i].side=0;}
                    else if(cube[i].point==8){cube[i].point=6;cube[i].side=0;}
                }
            }
        }
    }

    public static class B implements Direction{
        @Override
        public void move() {
            for(int i=0; i<9; i++){
                if(cube[i].side==4) {
                    cube[i].point=pointCycle_[cube[i].point];
                }
                else if(cube[i].side==0){
                    if(cube[i].point==0){
                        cube[i].point=2;
                        cube[i].side = 3;
                    }
                    else if(cube[i].point==1){
                        cube[i].point=5;
                        cube[i].side = 3;
                    }
                    else if(cube[i].point==2){
                        cube[i].point=8;
                        cube[i].side = 3;
                    }
                }
                else if(cube[i].side==3){
                    if(cube[i].point==2){cube[i].point=8;cube[i].side=5;}
                    else if(cube[i].point==5){cube[i].point=7;cube[i].side=5;}
                    else if(cube[i].point==8){cube[i].point=6;cube[i].side=5;}

                }
                else if(cube[i].side==5){
                    if(cube[i].point==6){cube[i].point=0;cube[i].side=1;}
                    else if(cube[i].point==7){cube[i].point=3;cube[i].side=1;}
                    else if(cube[i].point==8){cube[i].point=6;cube[i].side=1;}

                }
                else if(cube[i].side==1){
                    if(cube[i].point==0){cube[i].point=2;cube[i].side=0;}
                    else if(cube[i].point==3){cube[i].point=1;cube[i].side=0;}
                    else if(cube[i].point==6){cube[i].point=0;cube[i].side=0;}
                }
            }
        }
    }
    public static class B_ implements Direction{
        @Override
        public void move() {
            for(int i=0; i<9; i++){
                if(cube[i].side==4) {
                    cube[i].point=pointCycle[cube[i].point];
                }
                else if(cube[i].side==0){
                    if(cube[i].point==0){
                        cube[i].point=6;
                        cube[i].side = 1;
                    }
                    else if(cube[i].point==1){
                        cube[i].point=3;
                        cube[i].side = 1;
                    }
                    else if(cube[i].point==2){
                        cube[i].point=0;
                        cube[i].side = 1;
                    }
                }
                else if(cube[i].side==3){
                    if(cube[i].point==2){cube[i].point=0;cube[i].side=0;}
                    else if(cube[i].point==5){cube[i].point=1;cube[i].side=0;}
                    else if(cube[i].point==8){cube[i].point=2;cube[i].side=0;}

                }
                else if(cube[i].side==5){
                    if(cube[i].point==6){cube[i].point=8;cube[i].side=3;}
                    else if(cube[i].point==7){cube[i].point=5;cube[i].side=3;}
                    else if(cube[i].point==8){cube[i].point=2;cube[i].side=3;}

                }
                else if(cube[i].side==1){
                    if(cube[i].point==0){cube[i].point=6;cube[i].side=5;}
                    else if(cube[i].point==3){cube[i].point=7;cube[i].side=5;}
                    else if(cube[i].point==6){cube[i].point=8;cube[i].side=5;}
                }
            }
        }
    }

}