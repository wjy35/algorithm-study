package _5635;
import java.io.*;
import java.util.*;

/**
 * @author 왕준영
 * @see <a href = "https://www.acmicpc.net/problem/5635">생일</a>
 * @category 
 * @Note
 */
public class Main {
    static int N;
    static Student[] studentArray;
    static Student old,young;

    static void input() throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st;
        N = Integer.parseInt(br.readLine());
        studentArray = new Student[N+1];
        for(int i=1; i<=N; i++){
            st = new StringTokenizer(br.readLine());
            studentArray[i] = new Student(st.nextToken(),st.nextToken(),st.nextToken(),st.nextToken());
            old = Student.old(old,studentArray[i]);
            young = Student.young(young,studentArray[i]);
        }
    }

    static void output() throws IOException {
        BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));
        bw.write(young.getName());
        bw.write("\n");
        bw.write(old.getName());
        bw.flush();
    }

    public static void main(String[] args) throws IOException {
        input();
        output();
    }
}

class Student {
    private String name;
    private int birthday;

    public Student(String name, String dd,String mm,String yyyy) {
        this.name = name;
        this.birthday = Integer.parseInt(yyyy)*10000+Integer.parseInt(mm)*100+Integer.parseInt(dd);
    }

    public static Student young(Student source,Student target){
        if(source==null) return target;

        return source.birthday<target.birthday ? target:source;
    }

    public static Student old(Student source,Student target){
        if(source==null) return target;

        return source.birthday<target.birthday ? source:target;
    }

    public String getName() {
        return name;
    }
}