/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package URLTree;

/**
 *
 * @author khaledd
 */
public class Node {
    
    String nodeArr[];
    int count;

    public Node(String[] nodeArr, int count) {
        this.nodeArr = nodeArr;
        this.count = count;
    }

    public String[] getNodeArr() {
        return nodeArr;
    }

    public void setNodeArr(String[] nodeArr) {
        this.nodeArr = nodeArr;
    }

    public int getCount() {
        return count;
    }

    public void setCount(int count) {
        this.count = count;
    }
    
    
    
}
