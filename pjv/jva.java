package cz.cvut.fel.pjv;

public class TreeImpl{
    private Node root;
    public TreeImpl(){
        this.root=null;
    }
    @Override
    public void setTree(int[] values) {
        this.root=createTree(values);
    }
    public Node createTree(int[] valuse){
        int middle= (valuse.length/2)+1;
        NodeImpl rootNode= new NodeImpl();
        rootNode.value = valuse[middle];

        if(valuse.length>1){

        }
        return rootNode;
    }

    @Override
    public Node getRoot() {
        return null;
    }
}
