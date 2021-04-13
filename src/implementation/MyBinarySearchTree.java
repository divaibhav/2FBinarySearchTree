package implementation;

public class MyBinarySearchTree {
    //linked list
    private Node root; // head

    public boolean isEmpty(){
        if(root == null){
            return true;
        }
        return false;
    }
    //insert
    public void insert(int element){
        Node node = new Node(element);
        if(isEmpty()){
            root = node;
        }
        else{
            Node temp = root;
            Node parent = null;
            while(temp != null){
                parent = temp;
                //checking whether element will be part of
                //left subtree or right subtree
                if(element <= temp.getData()){
                    temp = temp.getLeft();
                }
                else{
                    temp = temp.getRight();
                }
            }
            //again we have to check whether new node will be left child or right child of parent
            if(element <= parent.getData()){
                //new node will be left child
                parent.setLeft(node);
            }
            else{
                parent.setRight(node);
            }
        }
    }
    public void inOrder(Node node){
        //base condition
        if(node == null){
            return;
        }
        else{
            //recursive call
            //step 1
            inOrder(node.getLeft());
            //step 2
            System.out.print(node.getData() + ", ");
            //step 3
            inOrder(node.getRight());
        }

    }
}
