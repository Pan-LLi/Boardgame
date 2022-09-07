/*
 * Copyright 2014, Michael T. Goodrich, Roberto Tamassia, Michael H. Goldwasser
 *
 * Developed for use with the book:
 *
 *    Data Structures and Algorithms in Java, Sixth Edition
 *    Michael T. Goodrich, Roberto Tamassia, and Michael H. Goldwasser
 *    John Wiley & Sons, 2014
 *
 * This program is free software: you can redistribute it and/or modify
 * it under the terms of the GNU General Public License as published by
 * the Free Software Foundation, either version 3 of the License, or
 * (at your option) any later version.
 *
 * This program is distributed in the hope that it will be useful,
 * but WITHOUT ANY WARRANTY; without even the implied warranty of
 * MERCHANTABILITY or FITNESS FOR A PARTICULAR PURPOSE.  See the
 * GNU General Public License for more details.
 *
 * You should have received a copy of the GNU General Public License
 * along with this program.  If not, see <http://www.gnu.org/licenses/>.
 */

/**
 * A basic doubly linked list implementation.
 *
 * @author Michael T. Goodrich
 * @author Roberto Tamassia
 * @author Michael H. Goldwasser
 */
public class DoublyLinkedList<E> {

    //---------------- nested Node class ----------------
    /**
     * Node of a doubly linked list, which stores a reference to its
     * element and to both the previous and next node in the list.
     */
    private static class Node<E> {

        /** The element stored at this node */
        private E element;               // reference to the element stored at this node

        /** A reference to the preceding node in the list */
        private Node<E> prev;            // reference to the previous node in the list

        /** A reference to the subsequent node in the list */
        private Node<E> next;            // reference to the subsequent node in the list

        /**
         * Creates a node with the given element and next node.
         *
         * @param e  the element to be stored
         * @param p  reference to a node that should precede the new node
         * @param n  reference to a node that should follow the new node
         */
        public Node(E e, Node<E> p, Node<E> n) {
            element = e;
            prev = p;
            next = n;
        }

        // public accessor methods
        /**
         * Returns the element stored at the node.
         * @return the element stored at the node
         */
        public E getElement() { return element; }

        /**
         * Returns the node that precedes this one (or null if no such node).
         * @return the preceding node
         */
        public Node<E> getPrev() { return prev; }

        /**
         * Returns the node that follows this one (or null if no such node).
         * @return the following node
         */
        public Node<E> getNext() { return next; }

        // Update methods
        /**
         * Sets the node's previous reference to point to Node n.
         * @param p    the node that should precede this one
         */
        public void setPrev(Node<E> p) { prev = p; }

        /**
         * Sets the node's next reference to point to Node n.
         * @param n    the node that should follow this one
         */
        public void setNext(Node<E> n) { next = n; }
    } //----------- end of nested Node class -----------

    // instance variables of the DoublyLinkedList
    /** Sentinel node at the beginning of the list */
    private Node<E> header;                    // header sentinel

    /** Sentinel node at the end of the list */
    private Node<E> trailer;                   // trailer sentinel

    /** Number of elements in the list (not including sentinels) */
    private int size = 0;                      // number of elements in the list

    /** Constructs a new empty list. */
    public DoublyLinkedList() {
        header = new Node<>(null, null, null);      // create header
        trailer = new Node<>(null, header, null);   // trailer is preceded by header
        header.setNext(trailer);                    // header is followed by trailer
    }

    // public accessor methods
    /**
     * Returns the number of elements in the linked list.
     * @return number of elements in the linked list
     */
    public int size() { return size; }

    /**
     * Tests whether the linked list is empty.
     * @return true if the linked list is empty, false otherwise
     */
    public boolean isEmpty() { return size == 0; }

    /**
     * Returns (but does not remove) the first element of the list.
     * @return element at the front of the list (or null if empty)
     */
    public E first() {
        if (isEmpty()) return null;
        return header.getNext().getElement();   // first element is beyond header
    }

    /**
     * Returns (but does not remove) the last element of the list.
     * @return element at the end of the list (or null if empty)
     */
    public E last() {
        if (isEmpty()) return null;
        return trailer.getPrev().getElement();    // last element is before trailer
    }

    // public update methods
    /**
     * Adds an element to the front of the list.
     * @param e   the new element to add
     */
    public void addFirst(E e) {
        addBetween(e, header, header.getNext());    // place just after the header
    }

    /**
     * Adds an element to the end of the list.
     * @param e   the new element to add
     */
    public void addLast(E e) {
        addBetween(e, trailer.getPrev(), trailer);  // place just before the trailer
    }

    /**
     * Removes and returns the first element of the list.
     * @return the removed element (or null if empty)
     */
    public E removeFirst() {
        if (isEmpty()) return null;                  // nothing to remove
        return remove(header.getNext());             // first element is beyond header
    }

    /**
     * Removes and returns the last element of the list.
     * @return the removed element (or null if empty)
     */
    public E removeLast() {
        if (isEmpty()) return null;                  // nothing to remove
        return remove(trailer.getPrev());            // last element is before trailer
    }

    // private update methods
    /**
     * Adds an element to the linked list in between the given nodes.
     * The given predecessor and successor should be neighboring each
     * other prior to the call.
     *
     * @param predecessor   node just before the location where the new element is inserted
     * @param successor     node just after the location where the new element is inserted
     */
    private void addBetween(E e, Node<E> predecessor, Node<E> successor) {
        // create and link a new node
        Node<E> newest = new Node<>(e, predecessor, successor);
        predecessor.setNext(newest);
        successor.setPrev(newest);
        size++;
    }

    /**
     * Removes the given node from the list and returns its element.
     * @param node    the node to be removed (must not be a sentinel)
     */
    private E remove(Node<E> node) {
        Node<E> predecessor = node.getPrev();
        Node<E> successor = node.getNext();
        predecessor.setNext(successor);
        successor.setPrev(predecessor);
        size--;
        return node.getElement();
    }

    /**
     * Produces a string representation of the contents of the list.
     * This exists for debugging purposes only.
     */
    public String toString() {
        StringBuilder sb = new StringBuilder("(");
        Node<E> walk = header.getNext();
        while (walk != trailer) {
            sb.append(walk.getElement());
            walk = walk.getNext();
            if (walk != trailer)
                sb.append(", ");
        }
        sb.append(")");
        return sb.toString();
    }
    public void Change(DoublyLinkedList<E> dlist,int a, int b){
        //Node<E> m=new Node<>(a, header.getNext(),trailer.getPrev());
        Node<E> current1=header;
        Node<E> current2=header;
       for(int i=0;i<a;i++){
           if(i<a){
               current1=current1.getNext();
           }
       }
       E c1=current1.getElement();
       System.out.println(c1);
        Node<E> prev1=current1.getPrev();
        Node<E> next1=current1.getNext();
        dlist.remove(current1);
        for(int j=1;j<b;j++){
            if(j<b){
                current2=current2.getNext();
            }
        }
        E c2=current2.getElement();
       //Node<E> prev1=current1.getPrev();
       //Node<E> next1=current1.getNext();
        Node<E> prev2=current2.getPrev();
        Node<E> next2=current2.getNext();
        dlist.remove(current2);
       //Node<E> m=new Node<>(c2,prev1,next1);
        //Node<E> n=new Node<>(c1,prev2,next2);
        dlist.addBetween(c2,prev1,next1);
        dlist.addBetween(c1,prev2,next2);

    }
    public void addMultiple(DoublyLinkedList<E> gameboard, ArrayList<E> number){
        //Node<E> current=header;
        for(int i=0;i< number.size();i++){
            gameboard.addLast(number.get(i));
        }
    }
    public int Playgame(DoublyLinkedList<E> board,Node<E> current1, int point1,int dice, int move){
                int a=0;
                while(a<dice) {
                    if(current1.getNext()==trailer) {
                        if (point1 >= 44) {
                            move++;
                            //count++;
                            //return Playgame(board, header, point, (int) (Math.random() * (6) + 1), move);
                            return move;
                        }
                        if (point1 < 44) {
                            current1 = header;
                            point1 = 0;
                            return Playgame(board, current1, point1, (int) (Math.random() * (6) + 1), move);
                        }
                    }
                    current1 = current1.getNext();
                    a++;
                }
                point1 += (int) current1.getElement();
                move++;
                return Playgame(board,current1, point1,(int) (Math.random() * (6) + 1), move);
    }

    public int[] Playgame4(DoublyLinkedList<E>board,Node<E> Player1,Node<E> Player2,Node<E> Player3,Node<E> Player4, int point1,int point2,int point3,int point4,int dice1,int dice2,int dice3,int dice4, int move1,int move2, int move3, int move4){
        int check1=0;
        int check2=0;
        int check3=0;
        int check4=0;
        int[] winner=new int[5];
        check1=CheckPosition(board,Player1,Player2,Player3,Player4,point1,dice1,check1);
        if(check1==2){
            move1++;
            winner[0]=1;
            winner[1]=move1;
            winner[2]=move2;
            winner[3]=move3;
            winner[4]=move4;
            return winner;
        }
        Player1=MoveNodePosition(board,check1,Player1,dice1);
        point1+=AddPoint(board,Player1,check1,point1);
        move1++;
        if(check1==1 &&Player2 !=null){Player2=ReverseNode(board,Player2);}
        if(check1==5 &&Player3 !=null){Player3=ReverseNode(board,Player2);}
        if(check1==6 &&Player4 !=null){Player4=ReverseNode(board,Player2);}
        check2=CheckPosition(board,Player2,Player1,Player3,Player4,point2,dice2,check2);
        if(check2==2){
            move2++;
            winner[0]=2;
            winner[1]=move1;
            winner[2]=move2;
            winner[3]=move3;
            winner[4]=move4;
            return winner;
        }
        Player2=MoveNodePosition(board,check2,Player2,dice2);
        point2+=AddPoint(board,Player2,check2,point2);
        if(check2==1 &&Player2 !=null){Player1=ReverseNode(board,Player1);}
        if(check2==5 &&Player3 !=null){Player3=ReverseNode(board,Player3);}
        if(check2==6 &&Player4 !=null){Player4=ReverseNode(board,Player4);}
        move2++;
        check3=CheckPosition(board,Player3,Player1,Player2,Player4,point3,dice3,check3);
        if(check3==2){
            move3++;
            winner[0]=3;
            winner[1]=move1;
            winner[2]=move2;
            winner[3]=move3;
            winner[4]=move4;
            return winner;
        }
        Player3=MoveNodePosition(board,check3,Player3,dice3);
        point3+=AddPoint(board,Player3,check3,point3);
        if(check3==1 &&Player1 !=null){Player1=ReverseNode(board,Player1);}
        if(check3==5 &&Player2 !=null){Player2=ReverseNode(board,Player2);}
        if(check3==6 &&Player4 !=null){Player4=ReverseNode(board,Player4);}
        move3++;
        check4=CheckPosition(board,Player4,Player1,Player2,Player3,point4,dice4,check4);
        if(check4==2){
            move4++;
            winner[0]=4;
            winner[1]=move1;
            winner[2]=move2;
            winner[3]=move3;
            winner[4]=move4;
            return winner;
        }
        Player4=MoveNodePosition(board,check4,Player4,dice4);
        point4+=AddPoint(board,Player4,check4,point4);
        if(check4==1 &&Player1 !=null){Player1=ReverseNode(board,Player1);}
        if(check4==5 &&Player2 !=null){Player2=ReverseNode(board,Player2);}
        if(check4==6 &&Player3 !=null){Player3=ReverseNode(board,Player3);}
        move4++;
        return Playgame4(board,Player1,Player2,Player3,Player4,point1,point2,point3,point4,(int) (Math.random() * (6) + 1),(int) (Math.random() * (6) + 1),(int) (Math.random() * (6) + 1),(int) (Math.random() * (6) + 1),move1,move2,move3,move4);
    }
    public int CheckPosition(DoublyLinkedList<E> board,Node<E> moving,Node<E> stable1,Node<E> stable2,Node<E> stable3,int point,int dice,int check) {
        int a = 0;
        check = 4;
        while (a < dice) {
            moving=moving.getNext();
            if (moving.getNext() == trailer && point >= 44) {
                check = 2;
                break;
            }

            if (moving.getNext() == trailer && point < 44) {
                check = 3;
                break;
            }
        a++;}
        if (moving == stable1) {
            check = 1;

        }
        if (moving == stable2) {
            check = 5;
        }
        if (moving == stable3) {
            check = 6;
        }

        return check;}

   public Node<E> MoveNodePosition(DoublyLinkedList<E> board, int check,Node<E> current, int dice) {
       if(check==2){
        current = null;
       }
        int n=0;
       if (check == 3) {
           current = header;
       }
       if (check == 1 || check == 4 || check == 5 || check == 6) {
           while( n < dice) {
               current = current.getNext();
               n++;
           }

   }
        return current;
   }

   public Node<E> ReverseNode(DoublyLinkedList<E> board, Node<E> hit) {
       for (int m = 0; m < 7; m++) {
           if(hit==header){
               break;
           }
           hit = hit.getPrev();
       }
       return hit;
   }

   public int AddPoint(DoublyLinkedList<E> board,Node<E> current,int check, int point){
        if(check==1|| check==4 || check==5 || check==6){
            point=(int)current.getElement();
        }
        if(check==3){
            point=0;
        }
        return point;
   }

    public Node<E> getFirst(DoublyLinkedList<E> board){
        return header;
    }


} //----------- end of DoublyLinkedList class -----------
