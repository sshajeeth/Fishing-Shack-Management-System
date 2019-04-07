package LinkedListImplement;

public class LinkedList {
    public Link firstLink;
    public LinkedList() {
        firstLink = null;
    }

    public boolean isEmpty() {
        return (firstLink == null);

    }

    public void insertFirst(String name, int number) {
        Link newlink = new Link(name, number);
        newlink.next = firstLink;
        firstLink = newlink;
    }

    public Link removeFirst() {
        Link linkReference = firstLink;
        if (!isEmpty()) {
            firstLink = firstLink.next;
        } else {
            System.out.println("Empty Link list");
        }
        return linkReference;
    }

    public void display() {
        Link thelink = firstLink;
        while (thelink != null) {
            thelink.display();
           // System.out.println("Next Link" + thelink.next);
            thelink = thelink.next;
            System.out.println();
        }
    }

    public Link find(String name) {
        Link thelink = firstLink;
        if (!isEmpty()) {
            while (!thelink.name.equals(name)) {
                if (thelink.next == null) {
                    return null;
                } else {
                    thelink = thelink.next;
                }

            }
        } else {
            System.out.println("Empty Linked list");
        }
        return thelink;
    }

    public Link removeLink(String name) {
        Link currentLink = firstLink;
        Link previousLink = firstLink;
        while (currentLink.name != name) {

            if (currentLink.next == null) {
                return null;
            } else {
                previousLink = currentLink;
                currentLink = currentLink.next;
            }
        }
        if (currentLink == firstLink) {
            firstLink = firstLink.next;
        } else {
            previousLink.next = currentLink.next;
        }
        return currentLink;
    }
}

class Link {
    public String name;
    public int number;
    public Link next;

    public static void main(String[] args) {
        LinkedList linklist = new LinkedList();
        linklist.insertFirst("Sinkers",1);
        linklist.insertFirst("Small",2);
        linklist.display();
    }
    public Link(String name, int number) {
        this.name = name;
        this.number = number;
    }

    public void display() {
        System.out.println(name );
    }

    public String toString() {
        return name;
    }


}
