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
    //pre order
    public void preOrder(Node node){
        if(node != null ){
            System.out.print(node.getData() + ", ");
            preOrder(node.getLeft());
            preOrder(node.getRight());
        }
    }
    //post order
    public void postOrder(Node node){
        if(node != null){
           postOrder(node.getLeft());
           postOrder(node.getRight());
            System.out.print(node.getData() + ", ");
        }
    }
    //searching an element
    public boolean search(int searchElement){
        boolean response = false;
        Node temp = root;
        while(temp != null){
            if(searchElement == temp.getData()){
                response = true;
                break;
            }
            else if(searchElement < temp.getData()){
                //update temp to traverse left sub tree
                temp = temp.getLeft();
            }
            else{
                //update temp to traverse right sub tree
                temp = temp.getRight();
            }
        }


        return response;
    }
    public void delete(int deletingElement){
        //traverse and find deleting element
        Node temp = root;
        Node parent  = null;
        //traverse
        while(temp != null){
            //assign parent the value temp

            if(deletingElement == temp.getData()){
                break;
            }
            else {
                parent = temp;
                if(deletingElement < temp.getData()){
                    temp = temp.getLeft();
                }
                else{
                    temp = temp.getRight();
                }
            }

        }
        //either temp will be null or holding reference of deleting node
        if(temp != null){
           //leaf case
            if(isLeaf(temp)){
                //root case
                if(temp == root){
                    root = null;
                }
                else{
                    if(deletingElement < parent.getData()){
                        parent.setLeft(null);
                    }
                    else{
                        parent.setRight(null);
                    }
                }
            }

           //single left child
            else if (hasLeftChild(temp)){
                //root case
                if(parent == null) {
                    root = root.getLeft();
                }
                else{
                    if(deletingElement < parent.getData()){
                        parent.setLeft(temp.getLeft());
                    }
                    else{
                        parent.setRight(temp.getLeft());
                    }
                }
            }
            //single right child
            else if(hasRightChild(temp)){
                //root case
                if(parent == null){
                    root = root.getRight();
                }
                else{
                    if(deletingElement < parent.getData()){
                        parent.setLeft(temp.getRight());
                    }
                    else{
                        parent.setRight(temp.getRight());
                    }
                }
            }

            //two children case
            else{
                Node successor = getSuccessor(temp);
                delete(successor.getData());
                successor.setLeft(temp.getLeft());
                successor.setRight(temp.getRight());
                //root case
                if(parent == null){
                    root = successor;
                }
                else{
                    if(deletingElement < parent.getData()){
                        parent.setLeft(successor);
                    }
                    else{
                        parent.setRight(successor);
                    }
                }
            }
                    //root
        }
        else{
            System.out.println("cannot delete as element not present");
        }
    }

    private Node getSuccessor(Node node) {
        Node temp = node.getRight();
        while(temp.getLeft() != null){
            temp = temp.getLeft();
        }
        return temp;
    }

    private boolean hasRightChild(Node temp) {
        if(temp.getRight() != null && temp.getLeft() == null){
            return true;
        }
        return false;
    }

    private boolean hasLeftChild(Node temp) {
        if(temp.getLeft() != null && temp.getRight() == null){
            return true;
        }
        return false;
    }

    private boolean isLeaf(Node temp) {
        return temp.getLeft() == null && temp.getRight() == null;
    }

    public int height(Node node){
        if(node == null){
            return -1;
        }
        else{
            return 1 + Math.max(height(node.getLeft()), height(node.getRight()));
        }
    }
}
