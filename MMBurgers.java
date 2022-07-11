import java. util.*;
import java.io.*;

class Nodet{

 Vector data;
 int h;
 Nodet left, right;

 Nodet (Vector d){
  data = d;
  h=1;
 }
 public int getid(){
  int id = (int)(int)data.get(0);
  return id;
 }

 public void setid(int id){
  data.set(0,id);
  return;
 }
}

class AVL{
 Nodet root;

 public int height(Nodet nodet){
  if (nodet == null){
   return 0;
  }
  return nodet.h;
 }

 public int max(int a, int b){
 
  return (a>b)?a:b;
 }

 public Nodet rotateright(Nodet y){
  Nodet x = y.left;
  Nodet t2 = x.right;
  x.right = y;
  y.left = t2;
  y.h = max(height(y.right), height(y.left))+1;
  x.h = max(height(x.right), height(x.left))+1;
  return x;
 }


 public Nodet rotateleft(Nodet x){
  Nodet y = x.right;
  Nodet t2 = y.left;
  y.left = x;
  x.right = t2;
  x.h = max(height(x.right), height(x.left))+1;
  y.h = max(height(y.right), height(y.left))+1;
  return y;
 }

 public int getheightdiff(Nodet nodet){
  if(nodet == null){
   return 0;
  }
  return height(nodet.left)-height(nodet.right);
 }

 public Nodet insertion (Nodet node, Vector data){
  if (node == null){
   return (new Nodet(data));
  }

  if ((int)data.get(0) < node.getid()){
   node.left = insertion(node.left,data);
  }else if((int)data.get(0) > node.getid()){
   node.right = insertion(node.right,data);
  }else{
   return node;
  }

  node.h = 1+ max(height(node.left),height(node.right));
  int heightdiff1 = getheightdiff(node);

  if (heightdiff1 > 1 && (int)data.get(0)<node.left.getid()){
   return rotateright(node);

  }

  if (heightdiff1 < -1 && (int)data.get(0)>node.right.getid()){
   return rotateleft(node);
  }

  if (heightdiff1 > 1 && (int)data.get(0)>node.left.getid()){
   node.left = rotateleft(node.left);
   return rotateright(node);
  }

  if (heightdiff1 < -1 && (int)data.get(0)<node.right.getid()){
   node.right= rotateright(node.right);
   return rotateleft(node);
  }

  return node;
 }

 public Nodet minimum(Nodet node){
  Nodet temp = node; 
  while(temp.left != null){
   temp = temp.left; 
  }
  return temp;
 }

 public Nodet deletion(Nodet root, Vector data){
  if(root == null){
   return root;
  }

  if ((int)data.get(0)<root.getid()){
   root.left = deletion(root.left, data);
  }else if ((int)data.get(0)>root.getid()){
   root.right = deletion(root.right, data);
  }else{
   if((root.left == null)||(root.right == null)){
    Nodet temp = null;
    if (temp == root.left){
     temp = root.right;
    }else{
     temp = root.left;
    }

    if(temp == null){
     temp = root;
     root = null;
    }else{
     root = temp;
    }
   }else{
    Nodet temp = minimum(root.right);
    root.data = temp.data;
    root.right = deletion(root.right, temp.data);
   }
  }
  if (root == null){
   return root;
  }

  root.h = max(height(root.left), height(root.right))+1;
  int heightdiff = getheightdiff(root);
  if(heightdiff>1 && getheightdiff(root.left)>=0){
   return rotateright(root);
  }
  if(heightdiff >1 && getheightdiff(root.left)<0){
   root.left = rotateleft(root.left);
   return rotateright(root);
  }
  if(heightdiff<-1 && getheightdiff(root.right)<=0){
   return rotateleft(root);
  }
  if(heightdiff<-1 && getheightdiff(root.right)>0){
   root.right = rotateright(root.right);
   return rotateleft(root);
  }
  return root;

 }

 public Vector Searcher(Nodet root,int id){
  if (root == null){
   return null;
  }

  if(id == root.getid()){
   return root.data;
  }else if (id > root.getid()){
   return Searcher(root.right,id);
  }else{
   return Searcher(root.left,id);
  }
 }

 public void preorder(Nodet node) {
  if (node!=null){

   System.out.print(node.getid() + " ");
   preorder(node.left);
   preorder(node.right);
  }
  
 }


}

class ArrayDeque{
 public Object[] Stkarr = new Object[1];
 int f=0;
 int r=0;
 
 public int Size(){
   int a = (Stkarr.length + r - f)%Stkarr.length;

return a;
 }
  
 public void insertFirst(Object o){
   int i=0;
   if((Stkarr.length + r - f)%Stkarr.length==1 && Stkarr.length < 2){
  Object[] s2 = new Object[2];
  Stkarr = s2;
  Stkarr[0] = o;
  r=1;
   } else {
  if((Stkarr.length + r - f)%Stkarr.length == Stkarr.length-1){
    Object[] temp = new Object[Stkarr.length*2];
    int currentsize = (Stkarr.length + r - f)%Stkarr.length;
    while(i<currentsize){
   temp[i] = Stkarr[f%Stkarr.length];
   i++;
   f++;
    }
    Stkarr = temp;
    f=0;
    r=i;
  }
  
  if(f==0){
    Stkarr[Stkarr.length-1] = o;
    f=Stkarr.length-1;
  
  }
  else{
    Stkarr[f-1]= o;
    f=f-1;
  }
  
   }
 }
 
 public void insertLast(Object o){
   int i=0;
   if((Stkarr.length + r - f)%Stkarr.length==1 && Stkarr.length < 2 ){
  Object[] s2 = new Object[2];
  Stkarr = s2;
  Stkarr[0] = o;
  r=1;
   }
   else{
  if((Stkarr.length + r - f)%Stkarr.length==Stkarr.length-1){
    Object[] temp = new Object[Stkarr.length*2];
    int currentsize = (Stkarr.length + r - f)%Stkarr.length;
    while(i<currentsize){
   temp[i] = Stkarr[f%Stkarr.length];
   i++;
   f++;
    }
    Stkarr = temp;
    f=0;
    r=i;
  }
  Stkarr[r]=  o;
  r=(r+1)%Stkarr.length;
   }
 }
 
 public Object removeFirst() {
   Object removedf = null;
   if(this.Size()==0){
    return 0;
   }else{
    removedf = Stkarr[f%Stkarr.length];
    f=(f+1)%Stkarr.length;
    return removedf;
   }
   
 }
 
 public Object removeLast() {
   if(this.Size()==0){
    return 0;
   }else{
    Object removed = null;
    if(r==0){
   
   removed = Stkarr[Stkarr.length-1];
   r=Stkarr.length-1;
   
    }
    else{
   removed = Stkarr[(r-1)%Stkarr.length];
   r=(r-1)%Stkarr.length;
    }
    
    return removed;
   }
 }
  
 public Object first() {
   
   Object First= Stkarr[f%Stkarr.length];
   return First;
 }
 
 public Object last() {
  
   Object Last= Stkarr[(r-1)%Stkarr.length];
   return Last;
 }

 public String toString(){
  int i=0;
  int currentsize = (Stkarr.length + r - f)%Stkarr.length;
  String s = "[";
  if(f==r){
   System.out.println("[]");
  }
  else{
    int tp = f;
   while(i<currentsize){
     s = s + String.valueOf(Stkarr[tp%Stkarr.length]) + ", ";
     i++;
     tp++;
  }
  
    }
    s = s.substring(0, s.length()-2) + "]";
    return s;
 }
  
}

class Minheap{
    Vector data = new Vector();


    public int dad(int i){
        if(i==1){
            return 0;
        }else{
            return i/2;
        }
    }

    public int leftchild(int i){
        return(2*i);
    }

    public int rightchild(int i){
        return(2*i+1);
    }

    public void swapper(int x, int y){
        ArrayDeque temp = (ArrayDeque) data.get(x);
        data.setElementAt(data.get(y),x);
        data.setElementAt(temp,y);

    }

    public void percolatedown(int i){
        int left = leftchild(i);
        int right = rightchild(i);
        int small = i;
        
        if(left<data.size()){
            if((Integer)((ArrayDeque)data.get(left)).Size()<(Integer)((ArrayDeque)data.get(i)).Size()){
                small = left;
            }else if(((ArrayDeque)data.get(left)).Size()==((ArrayDeque)data.get(i)).Size()){
                if((Integer)((ArrayDeque)data.get(left)).first() < (Integer)((ArrayDeque)data.get(i)).first()){
                    small = left;
                }
            }

        }

        if(right<data.size()){
            if((Integer)((ArrayDeque)data.get(right)).Size()<(Integer)((ArrayDeque)data.get(small)).Size()){
                small = right;
            }else if((Integer)((ArrayDeque)data.get(right)).Size()==(Integer)((ArrayDeque)data.get(small)).Size()){
                if((Integer)((ArrayDeque)data.get(right)).first() < (Integer)((ArrayDeque)data.get(small)).first()){
                    small = right;
                }
            }

        }

        if(small != i){
            swapper(i,small);
            percolatedown(small);
        }


    }

public void percolateup(int i){
        if (i>1){
            if((Integer)((ArrayDeque)data.get(dad(i))).Size()>(Integer)((ArrayDeque)data.get(i)).Size()){
                swapper(i,dad(i));
                percolateup(dad(i));
            }else if((Integer)((ArrayDeque)data.get(dad(i))).Size()==(Integer)((ArrayDeque)data.get(i)).Size()){
                if((Integer)((ArrayDeque)data.get(dad(i))).first()>(Integer)((ArrayDeque)data.get(i)).first()){
                    swapper(i,dad(i));
                    percolateup(dad(i));
                }
            }
        }
    }


    public boolean isEmpty(){
        if(data.size()==1){
            return true;
        }else{
            return false;
        }
    }

    public void Addelement(ArrayDeque a){
        if(data.size()==1){
            data.add(a);
        }else{
            data.add(a);
            int b = data.size()-1;
            percolateup(b);
        }
    }

    public ArrayDeque findmin(){
        if(data.size()!=1){
            return (ArrayDeque)data.get(1);
        }else{
            return null;
        }
    }

    public void buildheap(){
        for(int pos=(data.size()/2); pos>=1 ; pos--){
            percolatedown(pos);
        }
    }

    
}

class Maxheap{
 Vector data = new Vector();


 public int dad(int i){
  if(i==0){
   return 0;
  }else{
   return (i-1)/2;
  }
 }

 public int leftchild(int i){
  return(2*i+1);
 }

 public int rightchild(int i){
  return(2*i+2);
 }

 public void swapper(int x, int y){
  Vector temp = (Vector) data.get(x);
  data.setElementAt(data.get(y),x);
  data.setElementAt(temp,y);

 }

 public void percolatedown(int i){
  int left = leftchild(i);
  int right = rightchild(i);
  int large = i;
  
  if(left<data.size()){
   if((Integer)((Vector)data.get(left)).get(0)>(Integer)((Vector)data.get(i)).get(0)){
    large = left;
   }

  }

  if(right<data.size()){
   if((Integer)((Vector)data.get(right)).get(0)>(Integer)((Vector)data.get(large)).get(0)){
    large = right;
   }

  }

  if(large != i){
   swapper(i,large);
   percolatedown(large);
  }


 }


 public void percolateup(int i){
  if (i>0){
   if((Integer)((Vector)data.get(dad(i))).get(0)<(Integer)((Vector)data.get(i)).get(0)){
    swapper(i,dad(i));
    percolateup(dad(i));
   }
  }
 }


 public boolean isEmpty(){
  if(data.size()==0){
   return true;
  }else{
   return false;
  }
 }

 public void Addelement(Vector a){
  if(data.size()==0){
   data.add(a);
  }else{
   data.add(a);
   int b = data.size()-1;
   percolateup(b);
  }
 }

 public Vector findmax(){
  if(data.size()!=0){
   return (Vector)data.get(0);
  }else{
   return null;
  }
 }

 public void buildheap(){
  for(int pos=(data.size()/2); pos>=0 ; pos--){
   percolatedown(pos);
  }
 }

 public void deletemax(){
  if(data.size()!=0){
   Vector root = (Vector)data.get(0);
   data.setElementAt(data.get(data.size()-1),0);
   data.remove(data.size()-1);
   percolatedown(0);
   
  }
  
 } 

 
}

public class MMBurgers implements MMBurgersInterface {

 Minheap initialq = new Minheap();
 AVL clientlist = new AVL();
 Vector Aftercounter = new Vector();
 Maxheap aftercounter = new Maxheap();
 ArrayDeque Burgerwait = new ArrayDeque();
 Vector Griddle = new Vector();
 Vector Serviceq = new Vector();
 int totalWaitTime = 0;
 int Headcount = 0;
 int emptySlots = 0;
 int waitpatty = 0;
 int globaltime = 0;
 int K = 0;
 int M = 0;

    public boolean isEmpty(){
        int check = 0;
  int i=1;
  while(i<initialq.data.size()){
   if(((ArrayDeque)initialq.data.get(i)).Size() != 1){
    check = 1;
   }
   i++;
  }
  
  if(aftercounter.data.size()!=0){
   check = 1;
  }

  if(Burgerwait.Size()!=0){
   check = 1;
  }

  if(Griddle.size()!=0){
   check = 1;
  }

  if(Serviceq.size()!=0){
   check = 1;
  }

  if(check==0){
   return true;
  }else {
   return false;
  }

} 
    
    public void setK(int k) throws IllegalNumberException{
        if(k<=0){
   throw new IllegalNumberException("Illegal Number");
  }else{
   K = k;
   Vector allqueues = new Vector();
   allqueues.add(0);
   int i = 1;
   while (i<=K){
    ArrayDeque temp = new ArrayDeque();
    temp.insertLast(i);
    allqueues.add(temp);
    i++;
   }
   initialq.data = allqueues;
  }
    }   
    
    public void setM(int m) throws IllegalNumberException{
        if(m<=0){
   throw new IllegalNumberException("Illegal Number");
  }else{
   M = m;
   emptySlots=M;
  } 
    } 

    public void advanceTime(int t) throws IllegalNumberException{
        if(t<globaltime){
   throw new IllegalNumberException("Cannot go in the past");
  }else{
   while(globaltime<=t){
    int i=1;
    while(i<initialq.data.size()){
     ArrayDeque currentq = (ArrayDeque)initialq.data.get(i);
     if(currentq.Size()>1){
      int lnum = (Integer)currentq.first();
      currentq.removeFirst();
      Vector a = (Vector)currentq.first();
      if((Integer)a.get(3)+lnum==globaltime){
       Vector order = new Vector();
       order.add(lnum);
       order.add(a.get(0));
       order.add(a.get(1));
       Aftercounter.add(order);
       currentq.removeFirst();
       if(currentq.Size()!=0){
        Vector b = (Vector)currentq.first();
        b.setElementAt(globaltime,3);
       }

       Vector temp = clientlist.Searcher(clientlist.root,(Integer)a.get(0));
       temp.setElementAt(K+1,4);
       
 
      }
      currentq.insertFirst(lnum);
       
     }
     i++; 

    }
    
    if(Aftercounter.size()!=0){
     initialq.buildheap();
     
     aftercounter.data = Aftercounter;
     aftercounter.buildheap();
 
     while(aftercounter.data.size()!=0){
      Burgerwait.insertLast(aftercounter.findmax());
      waitpatty = waitpatty + (Integer)((Vector)aftercounter.findmax()).get(2);
      aftercounter.deletemax();
     }
    }


    int j = Griddle.size()-1;
    while(j>=0){
     Vector readyorder = new Vector();
     readyorder = (Vector)Griddle.get(j);
     if ((Integer)readyorder.get(3) == globaltime){
      emptySlots = emptySlots + (Integer)readyorder.get(2);
      readyorder.setElementAt((Integer)readyorder.get(3)+1,3);
      Serviceq.add(readyorder);
      Griddle.remove(j);
      
     }
     j--;
    }

    while(emptySlots>0 && Burgerwait.Size()!= 0){
     
     Vector order = (Vector) Burgerwait.first();
     
     if ((Integer)order.get(2)<emptySlots || (Integer)order.get(2)==emptySlots){
      order.add(globaltime+10);
      Griddle.add(order);
      waitpatty = waitpatty - (Integer)order.get(2);
      Burgerwait.removeFirst();
      emptySlots=emptySlots-(Integer)order.get(2);

     }else{
      int rem = (Integer)order.get(2)-emptySlots;
      Vector remainingorder = new Vector();
      remainingorder.add((Integer)order.get(0));
      remainingorder.add((Integer)order.get(1));
      remainingorder.add(rem);

      order.add(globaltime+10);
      order.setElementAt(emptySlots,2);
      Griddle.add(order);
      Burgerwait.removeFirst();
      Burgerwait.insertFirst(remainingorder);
      waitpatty = waitpatty - emptySlots;      
      emptySlots = 0;

     }
      
     
    }

    int n = Serviceq.size()-1;
    while(n>=0){
     Vector finishedorder = (Vector) Serviceq.get(n);
     if((Integer)finishedorder.get(3)==globaltime){
      int id = (Integer)finishedorder.get(1);
      Vector receipt = clientlist.Searcher(clientlist.root,id);
      receipt.setElementAt((Integer)receipt.get(1)-(Integer)finishedorder.get(2),1);
      if ((Integer)receipt.get(1)==0){
       receipt.setElementAt(K+2,4);
       receipt.setElementAt(globaltime-(Integer)receipt.get(2),3);
       totalWaitTime = totalWaitTime + (Integer)receipt.get(3);
       Headcount++;
       
      }
      Serviceq.remove(n);
     }
     n--;
    }


    globaltime++;
   }
   globaltime = globaltime - 1;
   

  }

    }

public void arriveCustomer(int id, int t, int numb) throws IllegalNumberException{
  if(t<globaltime){
   throw new IllegalNumberException("Cannot go in the past");
  }else if(numb<=0){
   throw new IllegalNumberException("not a valid order");
  }else{
   if(t>globaltime){
    this.advanceTime(t);
   }
   Vector custemp = new Vector();
   custemp.add(id);
   custemp.add(numb);
   custemp.add(t);
   custemp.add(t);
 
   ArrayDeque temp = initialq.findmin();
   custemp.add(temp.first());
   temp.insertLast(custemp);
   initialq.percolatedown(1);
 
   clientlist.root = clientlist.insertion(clientlist.root,custemp);
  }
 
    } 

    public int customerState(int id, int t) throws IllegalNumberException{
  if(t<globaltime){
   throw new IllegalNumberException("Cannot go in the past");
  }else if(clientlist.Searcher(clientlist.root,id)==null){
   return 0;
  }else if(t>globaltime){
   this.advanceTime(t);
   Vector temp = clientlist.Searcher(clientlist.root,id);
   return (Integer)temp.get(4);
  }else{
   Vector temp = clientlist.Searcher(clientlist.root,id);
   return (Integer)temp.get(4);
  }
    } 

    public int griddleState(int t) throws IllegalNumberException{
       if(t<globaltime){
     throw new IllegalNumberException("Cannot go in the past");
    }else if(t>globaltime){
     this.advanceTime(t);
     int filledslots = M - emptySlots;
     return filledslots;

    }else{
     int filledslots = M - emptySlots;
     return filledslots;
    }
    
    } 

    public int griddleWait(int t) throws IllegalNumberException{
        if(t<globaltime){
   throw new IllegalNumberException("Cannot go in the past");
  }else if (t>globaltime){
   this.advanceTime(t);
   return waitpatty;
  }else{
   return waitpatty;
  }
    } 

    public int customerWaitTime(int id) throws IllegalNumberException{
        if(clientlist.Searcher(clientlist.root,id)==null){
   throw new IllegalNumberException("not exists");
  }else if((Integer)((Vector)clientlist.Searcher(clientlist.root,id)).get(1)!=0){
   throw new IllegalNumberException("wrong time to ask this");
  }else{
   Vector temp = clientlist.Searcher(clientlist.root,id);
   return (Integer)temp.get(3);
  } 
    } 

 public float avgWaitTime(){
    return (float)totalWaitTime/Headcount; 
    } 

    
}




// public class MMBurgers implements MMBurgersInterface {

//     public boolean isEmpty(){
//         //your implementation
// 	    //throw new java.lang.UnsupportedOperationException("Not implemented yet.");	
//         return true;
//     } 
    
//     public void setK(int k) throws IllegalNumberException{
//         //your implementation
// 	    //throw new java.lang.UnsupportedOperationException("Not implemented yet.");	
//     }   
    
//     public void setM(int m) throws IllegalNumberException{
//         //your implementation
// 	    //throw new java.lang.UnsupportedOperationException("Not implemented yet.");	
//     } 

//     public void advanceTime(int t) throws IllegalNumberException{
//         //your implementation
// 	    //throw new java.lang.UnsupportedOperationException("Not implemented yet.");	
//     } 

//     public void arriveCustomer(int id, int t, int numb) throws IllegalNumberException{
//         //your implementation
// 	    //throw new java.lang.UnsupportedOperationException("Not implemented yet.");	
//     } 

//     public int customerState(int id, int t) throws IllegalNumberException{
//         //your implementation
// 	    //throw new java.lang.UnsupportedOperationException("Not implemented yet.");	
//         return 0;
//     } 

//     public int griddleState(int t) throws IllegalNumberException{
//         //your implementation
// 	    //throw new java.lang.UnsupportedOperationException("Not implemented yet.");	
//         return 0;
//     } 

//     public int griddleWait(int t) throws IllegalNumberException{
//         //your implementation
// 	    //throw new java.lang.UnsupportedOperationException("Not implemented yet.");	
//         return 0;
//     } 

//     public int customerWaitTime(int id) throws IllegalNumberException{
//         //your implementation
// 	    //throw new java.lang.UnsupportedOperationException("Not implemented yet.");	
//         return 0;
//     } 

// 	public float avgWaitTime(){
//         //your implementation
// 	    //throw new java.lang.UnsupportedOperationException("Not implemented yet.");	
//         return 0;
//     } 

    
// }
