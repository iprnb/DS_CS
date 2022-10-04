class Node{
    private Node[] links;
    private int L=26;
    private boolean isEnd;

    public Node(){
        links = new Node[L];
    }
    
    public boolean containsKey(char ch){
        return links[ch-'a'] != null;
    }

    public Node get(char ch){
        return links[ch-'a'];
    }

    public void put(char ch, Node node){
        links[ch-'a'] = node;
    }

    public void remove(char ch){
        links[ch - 'a'] = null;
    }

    public void setEnd(){
        isEnd = true;
    }

    public void removeEnd(){
        isEnd = false;
    }

    public boolean isEnd(){
        return isEnd;
    }


    public int children(){
        int counter =0;
        for(int i='a'; i<='z'; i++){
            if(links[i-'a']!=null) counter++;
        }
        return counter;
    }

}


class Trie{
    private Node root;

    public Trie(){
        root = new Node();
    }

    public Node getRoot(){
        return root;
    }

    public void insert(String s){
        Node node = root;
        for(int i=0; i<s.length(); i++){
            if(!node.containsKey(s.charAt(i))){
                node.put(s.charAt(i), new Node());
            }
            node = node.get(s.charAt(i));
        }
        node.setEnd();
    }


    private Node prefix(String s){
        Node node = root;
        for(int i=0; i<s.length(); i++){
            if(node.containsKey(s.charAt(i))){
                node = node.get(s.charAt(i));
            }else{
                return null;
            }
        }
        return node;
    }

    public boolean startsWith(String s){
        Node node = prefix(s);
        return node != null;
    }

    public boolean search(String s){
        Node node = prefix(s);
        return node!=null && node.isEnd();
    }

    public void delete(String s){
        if(search(s)){
            String s1 = s;
            Node node = prefix(s);
            if(node.children()>0){
                node.removeEnd();
            }else{
                prefix(s.substring(0,s1.length()-1)).remove(s.charAt(s1.length()-1));
                s = s.substring(0,s1.length()-1);
                for(int i=s1.length()-2; i>=0; i--){
                    node = prefix(s);
                    if(node.children()==0 && !node.isEnd()){
                        prefix(s.substring(0,i)).remove(s.charAt(i));
                    } else{break;}
                    s = s.substring(0,i);
                }
            }
        }
    }

    public void printAll(Node r, String s){
        Node node = r;
        char ch = 'a';
        if(node.isEnd()){
            System.out.println(s);
        }
        while(ch<='z'){
            if(node.containsKey(ch)){
                printAll(node.get(ch), s+ch);
            }
            ch++; 
        }
    }

    
}

class trieTree{
    public static void main(String[] args) {
        Trie t = new Trie();

        t.insert("a");
        t.insert("adaptation");
        t.insert("account");
        t.insert("add");
        t.insert("river");
        t.insert("hit");
        t.insert("hi");

        t.delete("hi");

        System.out.println(t.startsWith("hi"));

        System.out.println(t.search("hi"));
        System.out.println(t.search("add"));

        t.printAll(t.getRoot(),"");

    }
}