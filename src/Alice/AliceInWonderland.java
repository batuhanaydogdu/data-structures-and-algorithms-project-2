/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Alice;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.util.Random;
import java.util.Scanner;

public class AliceInWonderland {

    private String[] aliceStr;
    private Hash<Integer, Integer> lph;
    private int numOfKeys;

    public AliceInWonderland() {
        lph = new Hash(631);
        aliceStr = new String[2];
        numOfKeys = 0;
    }

    private void resize(int a) {
        String[] c = new String[a];

        for (int i = 0; i < aliceStr.length; i++) {
            c[i] = aliceStr[i];

        }
        aliceStr = c;
    }

    private void addToStrArr(String a) {

        if (numOfKeys > aliceStr.length / 2) {
            resize(aliceStr.length * 2);

        }
        lph.put((a.hashCode() & 0x7fffffff) % 631, numOfKeys);
        aliceStr[numOfKeys] = a;
        numOfKeys++;
    }

    private void loadFiles(String file) {
        File f = new File(file);

        String longstring;

        try {

            Scanner scn = new Scanner(new BufferedReader(new FileReader(f)));

            while (scn.hasNextLine()) {
                longstring = scn.nextLine();
                String a[] = longstring.split(" ");
                for (int i = 0; i < a.length; i++) {
                    addToStrArr(a[i]);
                }

            }
            scn.close();
        } catch (FileNotFoundException ex) {
            System.out.println("file not found");
        }

    }

    public int[] getWithString(String a) {
        int b = (a.hashCode() & 0x7fffffff) % 631;
        Object[] c = lph.getAsArray(b);

        int f = 0;
        for (int i = 0; i < c.length; i++) {
            int ak = 0;
            if (c[i] instanceof Integer) {
                ak = (int) c[i];
            }

            String alg = aliceStr[ak];

            if (alg.equalsIgnoreCase(a)) {

                f++;
            } else {
                break;
            }

        }
        
        int[] n = new int[f];

        for (int i = 0; i < n.length; i++) {

            int g = (int) c[i];
            n[i] = g;

        }
        return n;

    }

    public void myProgram(String keyword, String fileName) {
        Random rnd = new Random();
        this.loadFiles(fileName);
        int[] c = this.getWithString(keyword);
        int n = rnd.nextInt(10);
        int p = rnd.nextInt(10);
        for (int i = 0; i < c.length; i++) {
             System.out.println(c[i]);
            for (int h = c[i]-1; h > c[i] - n; h--) {
                if (h >= 0) {
                    System.out.print(aliceStr[h] + " ");
                }

            }
            System.out.print(keyword+" ");
            for (int j = c[i]+1; j < c[i] + p; j++) {
                if (j <= aliceStr.length) {
                    System.out.print(aliceStr[j] + " ");
                }

            }
            System.out.println("");

        }

    }

    public static void main(String[] args) {
        AliceInWonderland alis = new AliceInWonderland();
        alis.loadFiles("asd.txt");
        int[] c = alis.getWithString("ran");

        alis.myProgram("and", "asd.txt");

    }

}
