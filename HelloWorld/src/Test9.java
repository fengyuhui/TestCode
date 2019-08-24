import java.util.*;

class Node{
    int val;
    Map<Integer,Node>  friends = new HashMap<>();
}
public class Test9 {
    public static void main(String args[]){
        Test9 test = new Test9();
        test.method2();
    }
    public static void method2(){
        Scanner in = new Scanner(System.in);
        Integer n = in.nextInt();
        Integer m = in.nextInt();
        Map<Integer,Node>  map = new HashMap<>();

        //构建图
        for(int i = 0; i<m; i++){
            int a1 = in.nextInt();
            int a2 = in.nextInt();
            Node node1;
            if(map.containsKey(a1)) {
                node1 = map.get(a1);
                if(map.containsKey(a2)) {
                    node1.friends.put(a2,map.get(a2)) ;
                    map.get(a2).friends.put(a1,node1);
                }else{
                    Node node2 = new Node();
                    node2.val = a2;
                    map.put(a2,node2) ;
                    map.get(a2).friends.put(a1,map.get(a1));
                    map.get(a1).friends.put(a2,map.get(a2)) ;
                }
            }else{
                  node1 = new Node();
                  node1.val = a1;
                  map.put(a1,node1);
                  if (map.containsKey(a2)){
                      map.get(a1).friends.put(a2,map.get(a2));
                      map.get(a2).friends.put(a1,node1);
                  }else{
                      Node node2 = new Node();
                      node2.val = a2;
                      map.put(a2,node2);
                      map.get(a2).friends.put(a1,node1);
                      map.get(a1).friends.put(a2,map.get(a2));
                  }
            }
        }

        int num = 0;
        List<Integer> student = new ArrayList();
        Integer max = 0;
        int index = 0;
        for(Node node:map.values()){
            if(node.friends.size()!=0){
                if(node.friends.size()>max){
                    max =  node.friends.size();
                    index =    node.val;
                }

            }
        }
        while(max != 0){
             Map<Integer,Node> friends =  map.get(index).friends;
              num++;
             for(Node node : friends.values()){
                 node.friends.remove(index);
             }
             map.get(index).friends.clear();
             student.add(index);

             index = 0;
             max = 0;
             for(Node node:map.values()){
                 if(node.friends.size()!=0){
                       if(node.friends.size()>max){
                           max = node.friends.size();
                           index = node.val;
                       }

                 }
             }
        }
        System.out.println(num);
        Collections.sort(student);
        for(Integer i : student)
            System.out.print(i);
    }

}
