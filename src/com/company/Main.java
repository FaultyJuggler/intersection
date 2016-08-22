package com.company;

public class Main {

    public static void main(String[] args)
    {
        LinkedListNode A = new LinkedListNode( 1 );
        LinkedListNode B = new LinkedListNode( 10 );

        LinkedListNode builder = A;
        for( int i = 0; i < 10; i++ )
        {
            builder.next = new LinkedListNode( i );
            builder = builder.next;
        }

        builder = B;
        for( int i = 20; i < 25; i++ )
        {
            builder.next = new LinkedListNode( i );
            builder = builder.next;
        }

//        LinkedListNode intersection = A;
//        for( int i = 0; i < 3; i++ )
//        {
//            intersection = intersection.next;
//        }
//
//        builder.next = intersection;

        A.print();
        B.print();

        LinkedListNode intersection = null;

        int lengthA = 1;
        int lengthB = 1;
        LinkedListNode checkA = A;
        LinkedListNode checkB = B;


        while( checkA.next != null )
        {
            checkA = checkA.next;
            lengthA++;
        }
        System.out.println( "length of A: " + lengthA );

        while( checkB.next != null )
        {
            checkB = checkB.next;
            lengthB++;
        }
        System.out.println( "length of B: " + lengthB );

        checkA.print();
        checkB.print();

        if( checkB != checkA )
        {
            System.out.println("no intersection");
            System.exit(0);
        }
        else
        {
            checkA = A;
            checkB = B;

            if( lengthA > lengthB )
            {
                for( int i = 0; i < lengthA - lengthB; i++ )
                {
                    checkA = checkA.next;
                }

            }
            else if( lengthB > lengthA )
            {
                for( int i = 0; i < lengthB - lengthA; i++ )
                {
                    System.out.println(checkB.data);
                    checkB = checkB.next;
                }

            }

            while( checkA.next != checkB.next )
            {
                checkA = checkA.next;
                checkB = checkB.next;
                intersection = checkA.next;
            }

            System.out.println( intersection.data );
            System.exit(1);
        }

    }
}
