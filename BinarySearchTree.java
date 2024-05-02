package BST;

import java.util.Scanner;

public class BinarySearchTree {
    static class Node{
        int data;
        Node left;
        Node right;

        Node(int data){
            this.data=data;
        }

    }

    public static Node insert(Node root,int val){
        if(root==null){
            root=new Node(val);
            return root;
        }
        if(root.data>val){
            //Left subtree
             root.left= insert(root.left,val);
        }
        else{
            //Right subtree
            root.right=insert(root.right,val);
        }
        return root;
    }

    public static void inorder(Node root){
        if(root==null){
            return;
        }
        inorder(root.left);
        System.out.print(root.data+" ");
        inorder(root.right);
    }

    public static void preorder(Node root){
        if(root==null){
            return;
        }
        System.out.print(root.data+" ");
        preorder(root.left);
        preorder(root.right);
    }

    public static void postorder(Node root){
        if(root==null){
            return;
        }
        postorder(root.left);
        postorder(root.right);
        System.out.print(root.data+" ");

    }

    public static boolean search(Node root , int key){
        if(root==null){
            return false;
        }
        if(root.data>key){
           return search(root.left,key);
        }
        else if(root.data<key){
            return search(root.right,key);
        }
        else if(root.data==key){
            return true;
        }
        return false;
    }

    public static Node delete(Node root, int val){
        if(root.data>val){
            root.left=delete(root.left,val);
        }
        else if(root.data<val){
            root.right=delete(root.right,val);
        }
        else{ // root.data==val
            // case 1 deleting the leaf node
            if(root.left==null && root.right==null){
                return null;
            }

            //case 2 deleting the node that has 1 child
            if(root.left==null){
                return root.right;
            }
            else if(root.right==null){
                return root.left;
            }

            //case 3 deleting the node that has 2 childs
            //here 1st we have to find inorder succesor : left most node in right subtree
            Node IS = inorderSuccessor(root.right);
            root.data=IS.data;
            root.right= delete(root.right, IS.data);

        }
        return root;
    }

      public static Node inorderSuccessor(Node root){
        while ((root.left!=null)){
                   root=root.left;
        }
        return root;
      }

      public static Node update (Node root,int oldval, int newval){
        if(search(root,oldval)==true){
            root=delete(root,oldval);
            root=insert(root,newval);
        }
        else {
            System.out.println("Old val not found ");
        }
        return root;
      }


    public static void main(String[] args) {
        int value[] = {8, 5, 3, 1, 4, 6, 10, 11, 14};
        Node root = null;
        for (int i = 0; i < value.length; i++) {
            root = insert(root, value[i]);
        }
        for (int i = 0; i < value.length; i++) {
            System.out.print(value[i] + " ");
        }
        System.out.println();
        int n;
        Scanner sc = new Scanner(System.in);
        do {
            System.out.println("You want to delete then click 1");
            System.out.println("You want to update then click 2");
            System.out.println("You want to traverse then click 3");
            System.out.println("You want to Search then click 4");


            System.out.println("You want to exist then click 0");
            n = sc.nextInt();


            switch (n) {
                case 1:
                    System.out.println("Val you want to delete");
                    int val = sc.nextInt();
                    delete(root, val);
                    break;
                case 2:
                    System.out.println("Enter the old and new val");
                    int oldval = sc.nextInt();
                    int newval = sc.nextInt();
                    update(root, oldval, newval);
                    break;
                case 3:
                    System.out.println("How you want to traverse ");
                    System.out.println("a- inorder");
                    System.out.println("b- preorder");
                    System.out.println("c- postorder");
                    String s = sc.next();
                    switch (s) {
                        case "a":
                            System.out.print("Inorder traversal: ");
                            inorder(root);
                            break;
                        case "b":
                            System.out.print("Preorder traversal: ");
                            preorder(root);
                            break;
                        case "c":
                            System.out.print("Postorder traversal: ");
                            postorder(root);
                            break;
                        default:
                            System.out.println("Error");
                    }
                        break;

                case 4:
                    System.out.println("Enter the key you want to search");
                    int key=sc.nextInt();
                    System.out.println(search(root,key));
                    break;
                case 0:
                    System.out.println("Exiting...");
                    break;
                default:
                    System.out.println("Error");

            }
        }while (n != 0) ;

        }
    }
