/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package datastructuresproject2;

import datastructuresproject2.BST.Node;
import java.io.BufferedReader;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.util.ArrayList;
import java.util.Scanner;

/**
 *
 * @author win7
 */
public class Customer {

    private String name;
    private String surname;
    private BST<String> listofPurchases;
    private String Gender;
    private int Age;
    private static BST<Integer> BSTwithNameSurname = new BST();
    private static BST<Integer> BSTwithId = new BST();
    private static BST<Integer> BSTwithAge = new BST();
    private static int count = 1;
    private int id;
    private static ArrayList<Customer> listOfCustomers = new ArrayList();

    public Customer(String name, String surname, BST<String> listofPurchases, String Gender, int Age) {
        this.name = name;
        this.surname = surname;
        this.listofPurchases = listofPurchases;
        this.Gender = Gender;
        this.Age = Age;
        id = count;
        count++;
    }

    public Customer(String name, String surname, String Gender, int Age, int id) {
        this.name = name;
        this.surname = surname;
        this.Gender = Gender;
        this.Age = Age;
        this.id = id;
    }

    public Customer() {
        id = count;
        count++;
    }

    @Override
    public String toString() {
        return "name =" + this.name + " surname =" + this.surname + " age =" + this.Age + "gender =" + this.Gender+" id= "+this.id;

    }

    public void addToArrayList() {

        listOfCustomers.add(this);

//        if (listOfCustomers.searchRecursive(listOfCustomers.getRoot(), this.id) == null) {
//            listOfCustomers.addNode(this.id, this);
//
//        } else {
//            System.out.println("the list of customer has that item");
//        }
    }

    private static Customer creatingCustomerWithString(String longstring) {
        Customer c = new Customer();
        int v = 0;
        c.name = "";
        c.surname = "";
        c.Gender = "";
        for (int i = 0; i < longstring.length(); i++) {
            v = i;
            if (longstring.charAt(i) == '+') {

                break;
            }

            c.name = c.name + longstring.charAt(i);
        }
        for (int i = v + 1; i < longstring.length(); i++) {
            v = i;
            if (longstring.charAt(i) == ',') {
                break;
            }

            c.surname = c.surname + longstring.charAt(i);
        }
        String forid = "";
        for (int i = v + 1; i < longstring.length(); i++) {
            v = i;
            if (longstring.charAt(i) == ',') {
                break;
            }

            forid = forid + longstring.charAt(i);

        }
        c.id = Integer.valueOf(forid);
        for (int i = v + 1; i < longstring.length(); i++) {
            v = i;
            if (longstring.charAt(i) == ',') {
                break;
            }

            c.Gender = c.Gender + longstring.charAt(i);
        }
        String forage = "";
        for (int i = v + 1; i < longstring.length(); i++) {
            v = i;
            if (longstring.charAt(i) == ',') {
                break;
            }

            forage = forage + longstring.charAt(i);
        }
        c.Age = Integer.valueOf(forage);
        System.out.println("test creating");
        return c;

    }

    private void addCustomerToBSTandList() {

        addToArrayList();
        int index = listOfCustomers.indexOf(this);
        BSTwithNameSurname.addNode(this.hashCode(), index);
        BSTwithId.addNode(this.id, index);
        BSTwithAge.addNode(this.Age, index);

    }

    private static void loadFiles() {
        File f = new File("customers.txt");

        String longstring;

        try {

            Scanner scn = new Scanner(new BufferedReader(new FileReader(f)));

            while (scn.hasNextLine()) {

                longstring = scn.nextLine();
                Customer c = creatingCustomerWithString(longstring);
                c.addCustomerToBSTandList();
            }
            scn.close();
        } catch (FileNotFoundException ex) {
            System.out.println("file not found");
        }

    }

    @Override
    public int hashCode() {
        return ((this.name + " " + this.surname).hashCode() & 0x7fffffff);
    }

    public static Customer search(String namesurname) {
        Node x = BSTwithNameSurname.searchRecursive(BSTwithNameSurname.getRoot(), (namesurname.hashCode() & 0x7fffffff));
        int ind = (int) x.getValue();
        return listOfCustomers.get(ind);

    }

    public static Customer search(int id) {
        //these is an example of id bst if we change the bst type and the input
//we can do all of them for other search type
//this and other methods provide every search type which is wanted
        Node x = BSTwithId.searchRecursive(BSTwithId.getRoot(), id);
        if(x!=null){
        int ind = (int) x.getValue();
        return listOfCustomers.get(ind);
        }
        return null;
    }

    public static ArrayList<Customer> searchBetween(int a, int b) {
        ArrayList<Customer> returnlist = new ArrayList();
        for (int i = a; i <= b; i++) {
           try{
            Customer c=search(i);
            
            
            if(c!=null)    
            returnlist.add(c);
           }
           catch(Exception e){
               
           }

        }
        return returnlist;

    }
    public static ArrayList<Customer> searchBigger(int a){
        ArrayList<Customer> returnlist = new ArrayList();
        Node x=BSTwithId.maxSearch(BSTwithId.getRoot());
        
        for (int i = a; i <=x.getKey() ; i++) {
           try{
            Customer c=search(i);
            
            
            if(c!=null)    
            returnlist.add(c);
           }
           catch(Exception e){
               
           }

        }
        return returnlist;
    }
    public static ArrayList<Customer> searchSmaller(int a){
        ArrayList<Customer> returnlist = new ArrayList();
        Node x=BSTwithId.minSearch(BSTwithId.getRoot());
        
        for (int i = a; i >=x.getKey() ; i--) {
           try{
            Customer c=search(i);
            
            if(c!=null)    
            returnlist.add(c);
           }
           catch(Exception e){
               
           }

        }
        return returnlist;
    }
    public static void recordNewCustomer(){
        Scanner x=new Scanner(System.in);
        System.out.println("name");
        String name=x.next();
        System.out.println("surname");
        String surname=x.next();
        System.out.println("gender");
        String gender=x.next();
        System.out.println("id");
        int id=x.nextInt();
        System.out.println("age");
        int age=x.nextInt();
        Customer c=new Customer(name, surname, gender, age, id);
        c.addCustomerToBSTandList();
    }
    public static void deleteCustomer(int id){
      try{
        
        Customer c=search(id);
        listOfCustomers.remove(c);
        BSTwithId.delete2(BSTwithId.getRoot(), id);
        BSTwithAge.delete2(BSTwithAge.getRoot(), c.Age);
        BSTwithNameSurname.delete2(BSTwithNameSurname.getRoot(),c.hashCode());
      }
      catch(Exception ex){
          System.err.println("!!!!");
      }
    }

    public static void main(String[] args) {
        Customer c1 = new Customer("sala", "apta", "erkek", 32, 51);
        Customer c3 = new Customer("ire", "erc", "kadın", 33, 52);
        Customer c4 = new Customer("hele", "neve", "erkek", 94, 53);
        Customer c5 = new Customer("armut", "maho", "erkek", 72, 54);

        
        
        c1.addCustomerToBSTandList();
        c3.addCustomerToBSTandList();
        c4.addCustomerToBSTandList();
        c5.addCustomerToBSTandList();
        loadFiles();
        System.out.println("");
       
        System.out.println("");
        System.out.println("");

        for(int i=0;i<listOfCustomers.size();i++){
            System.out.println(listOfCustomers.get(i).toString());
        }
        System.out.println("");
       BSTwithId.traverseInOrder(BSTwithId.getRoot());
        System.out.println("");
        System.out.println("");
        
        deleteCustomer(c3.id);
        for(int i=0;i<listOfCustomers.size();i++){
            System.out.println(listOfCustomers.get(i).toString());
        }
        
        BSTwithId.traverseInOrder(BSTwithId.getRoot());
       

    }

}
