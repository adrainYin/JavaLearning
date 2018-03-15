import java.util.Arrays;

class BTree {

    /**
     * 将结点作为BTree的一个内部类处理操作
     */
    public class Node{
        private Comparable data;
        private Node left;
        private Node right;

        public Node(Comparable data){
            this.data = data;
        }

        public void addNode(Node newNode){
            //用比较器比较数据
            if(this.data.compareTo(newNode.data) > 0){
                if (this.left == null){
                    this.left = newNode;
                }
                else {
                    this.left.addNode(newNode);
                }
            }
            else {
                if (this.right == null){
                    this.right = newNode;
                }
                else this.right.addNode(newNode);
            }
        }
        public void toArrayNode(){
            if (this.left != null){
                this.left.toArrayNode();
            }
            //回调函数？
            BTree.this.reData[BTree.this.foot++] = this.data;
            if (this.right != null){
                this.right.toArrayNode();
            }

        }

    }

    //这个根结点是作为这个二叉树的一个属性
    private Node root;
    private int count;
    private  int foot;
    private Object[] reData;
    //输出二叉树的所有结点
    private Object[] toArray(){
        this.foot = 0;
        this.reData = new Object[this.count];
        this.root.toArrayNode();
        return this.reData;
    }


    public void add(Object data){
        Node newNode = new Node((Comparable)data);
        if (data == null){
            return ;
        }
        if (this.root == null){
            this.root = newNode;
        }else {
            this.root.addNode(newNode);
        }
        this.count++;
    }

    public static void main(String args[]){
        BTree bTree = new BTree();
        bTree.add(20);
        bTree.add(10);
        bTree.add(15);
        Object[] objects =  bTree.toArray();
        System.out.println(Arrays.toString(objects));

    }
}
