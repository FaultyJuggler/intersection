package com.company;
import java.util.*;

public class LinkedListNode implements Iterable {

    LinkedListNode next = null;
    Integer data = null;

    // nodeId will prove useful for cycle detection in part (2)
    UUID nodeId = null;

    LinkedListNode()
    {
        nodeId = UUID.randomUUID();
    }

    LinkedListNode( Integer newData )
    {
        data = newData;
        nodeId = UUID.randomUUID();
    }

    public <T extends LinkedListNode> void addToHead( T newNode )
    {
        newNode.next = this.next;
        this.next = newNode;
    }

    public <T extends LinkedListNode> T removeAtHead( )
    {
        T firstNode = (T)this.next;
        if ( firstNode != null )
            this.next = firstNode.next;
        return firstNode;
    }

    public <T extends LinkedListNode> T removeAtTail( )
    {
        T currentNode = (T)this;

        if ( currentNode.next == null )
            return null;

        while( currentNode.next.next != null )
        {
            currentNode = (T)currentNode.next;
        }

        T tailNode = (T)currentNode.next;
        currentNode.next = null;

        return tailNode;
    }

    // Define an iterator for the LinkedList

    public LinkedListIterator iterator()
    {
        return new LinkedListIterator(this);
    }

    private static class LinkedListIterator implements Iterator
    {
        private LinkedListNode current = null;

        LinkedListIterator(LinkedListNode listHead)
        {
            current = listHead.next; // The first node in the linked list is dummy.
        }
        public boolean hasNext()
        {
            return (current != null);
        }
        public Integer next()
        {
            Integer returnValue = current.data;
            current = current.next;
            return returnValue;
        }
        public void remove() {
            // Not implemented
        }
    }

    public void print( )
    {
        LinkedListIterator iter = this.iterator();

        while( iter.hasNext() )
        {
            System.out.print( iter.next() + " ");
        }
        System.out.println();
    }

    public void addRandomList( )
    {
        Random random = new Random();
        int numLinks = 20 + random.nextInt(50);
        int i = 0;
        while( i < numLinks )
        {
            this.addToHead( new LinkedListNode(random.nextInt(100)) );
            i++;
        }
    }

    public void makeListLoopy( )
    {
        // Tosses a coin and decides whether to make the list cyclic

        Random random = new Random();
        if( random.nextInt(2) == 0 )
        {
            System.out.println("\n makeListLoopy: A loop was not created.\n");
            return; // no change to list
        }

        int size = 0;
        LinkedListNode currentNode = this.next;
        while ( currentNode != null )
        {
            currentNode = currentNode.next;
            size++;
        }

        int loc1 = random.nextInt(size);
        int loc2 = random.nextInt(size);
        if ( loc2 < loc1 )
        {
            int temp = loc2;
            loc2 = loc1;
            loc1 = temp;
        }
        System.out.println("\n makeListLoopy: A loop was created with cycle length = " + (loc2 - loc1) + "\n");

        currentNode = this.next;
        while ( loc1 > 0 )
        {
            currentNode = currentNode.next;
            loc1--;
            loc2--;
        }
        LinkedListNode loc1Node = currentNode;

        while ( loc2 > 0 )
        {
            currentNode = currentNode.next;
            loc2--;
        }
        currentNode.next = loc1Node;
    }
}