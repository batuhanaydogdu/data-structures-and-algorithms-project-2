/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package datastructuresproject2;

/**
 *
 * @author win7
 */
public class BST<Se> {

    private Node root;

    public class Node {

        private int key;
        private Se Value;
        private Node left = null;
        private Node right = null;

        public Node(int key, Se Value) {
            this.key = key;
            this.Value = Value;
        }

        public Se getValue() {
            return Value;
        }

        public int getKey() {
            return key;
        }
        

        @Override
        public String toString() {
            return ("Node(k= " + key + " Value=  " + Value + ")");
        }
    }

    public BST() {
    }

    public Node getRoot() {
        return root;
    }
    

    public boolean isEmpty() {
        return root==null;
    }
    public void addNode(int key, Se Value) {
        Node n = new Node(key, Value);

        if (root == null) {
            root = n;
        } else {
            Node tmp = root;
            Node parent = root;

            while (tmp != null) {
                parent = tmp;
                if (key > parent.key) {
                    tmp = tmp.right;
                } else if (key < parent.key) {
                    tmp = tmp.left;
                }

            }
            if (parent.key > n.key) {
                parent.left = n;
            } else if (parent.key < n.key) {
                parent.right = n;
            }

        }
    }
    public void traverseInOrder(Node focus) {

        if (focus.left != null) {
            traverseInOrder(focus.left);
        }
        System.out.println(focus.toString());
        if (focus.right != null) {
            traverseInOrder(focus.right);
        }

    }
    public void traversePostOrder(Node focus) {

        if (focus.left != null) {
            traversePostOrder(focus.left);
        }
        if (focus.right != null) {
            traversePostOrder(focus.right);
        }
        System.out.println(focus.toString());

    }
    public void traversePreOrder(Node focus) {
        System.out.println(focus.toString());
        if (focus.left != null) {
            traversePreOrder(focus.left);
        }
        if (focus.right != null) {
            traversePreOrder(focus.right);
        }

    }
    public Node minSearch(Node focus) {
        if (focus.left != null) {
            return minSearch(focus.left);
        }
        return focus;
    }
    public Node maxSearch(Node focus){
        if(focus.right==null){
            return focus;
        }
        else
            return maxSearch(focus.right);
    }
        public Node searchRecursive(Node focus, int key) {
        if (focus == null) {
            return null;
        }
        if (focus.key == key) {
            return focus;
        } else if (focus.right.key > key) {
            return searchRecursive(focus.left, key);
        } else {
            return searchRecursive(focus.right, key);
        }
        

    }
        public Node deleteMinRecursive(Node focus) {
        if (focus.left == null) {
            return focus.right;
        }
        focus.left = deleteMinRecursive(focus.left);

        return focus;
    }
        public Node delete2(Node focus, int key) {

        if (focus == null) {
            return null;
        }
        if (key < focus.key) {
            focus.left = delete2(focus.left, key);
        } else if (key > focus.key) {
            focus.right = delete2(focus.right, key);
        } else {
            if (focus.right == null) {
                return focus.left;
            }
            if (focus.left == null) {
                return focus.right;
            }
            Node t = focus;
            focus = minSearch(t.right);
            focus.right = deleteMinRecursive(t.right);
            focus.left = t.left;
        }

        return focus;
    }
        public int sum(Node focus) {
        if (focus == null) {
            return 0;
        } else {
            return (sum(focus.left) + focus.key + sum(focus.right));
        }
    }
        public int maxDepth(Node focus) {
        if (focus == null) {
            return 0;
        } else {
            int rDepth = maxDepth(focus.right);
            int lDepth = maxDepth(focus.left);
            if (rDepth > lDepth) {
                return rDepth + 1;
            } else {
                return lDepth + 1;
            }
        }

    }
        public int nonWtc(Node focus){
        if(focus!=null){
            if(focus.left!=null&focus.right!=null){
                return 1+nonWtc(focus.left)+nonWtc(focus.right);
            }
        }
        return 0;
    }
        
    
    

    public int size(Node focus) {
        if (focus == null) {
            return 0;
        }
        return size(focus.left) + 1 + size(focus.right);

    }
     public static void main(String[] args) {
        BST bst = new BST();
        Customer c=new Customer("qwe","rew","erkek",12,43);
        Customer c2=new Customer("jgj","khjkhj","erkek",45,434);
       bst.addNode(12, c);
       bst.addNode(45, c2);
//        bst.traverseInOrder(bst.root);
//        System.out.println("");
//        bst.traversePostOrder(bst.root);
//        System.out.println("");
//        bst.traversePreOrder(bst.root);
//        System.out.println("");
//        System.out.println(bst.minSearch(bst.root));
//        System.out.println(25);
//        System.out.println(bst.search(25).left);
//        System.out.println(bst.searchRecursive(bst.root, 25).left);
//        System.out.println(bst.size(bst.root));
//
//        bst.traverseInOrder(bst.root);
//                System.out.println(bst.sum(bst.root));
//
//        bst.delete2(bst.root, 60);
//        System.out.println("");
//        bst.traverseInOrder(bst.root);
//        System.out.println(bst.sum(bst.root));

        System.out.println(bst.getRoot().toString());
    }
     

}
