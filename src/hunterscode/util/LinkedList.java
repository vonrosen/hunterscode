package hunterscode.util;

public class LinkedList {

	private LinkedListNode head;
	private int size = 0;
	
	private class LinkedListNode {
		
		Object obj;
		LinkedListNode next;
		LinkedListNode prev;
		
		LinkedListNode(Object obj) {
			this.obj = obj;
		}	
	}
	
	public void add(Object obj) {

		if (head == null) {
			head = new LinkedListNode(obj);
		}
		else {
			LinkedListNode tmp = head;
			
			while (tmp.next != null) {
				tmp = tmp.next;
			}
		
			tmp.next = new LinkedListNode(obj);
			tmp.next.prev = tmp;
		}

		size++;
	}

	public void remove(int index) {

		LinkedListNode tmp = head;
		int counter = 0;
		
		while (tmp != null) {

			if (counter == index) {

				if (tmp == head) {
					
					if (head.next == null) {
						head = null;
					}
					else {
						head = head.next;
					}
				}
				else {
					tmp.prev.next = tmp.next;
					
					if (tmp.next != null) {
						tmp.next.prev = tmp.prev;
					}
				}
				size--;
				return;
			}
			
			tmp = tmp.next;
			counter++;
		}		
	}
	
	public Object get(int index) {

		LinkedListNode tmp = head;
		int counter = 0;
		
		while (tmp != null) {
		
			if (index == counter) {
				return tmp.obj;
			}
			
			tmp = tmp.next;
			counter++;
		}
		
		return null;
	}	

	public static void main(String [] args) {
		
		LinkedList ll = new LinkedList();
		
		ll.add("stuff1");
		System.out.println(ll.get(0)); //stuff1
		System.out.println(ll.getSize());
		
		ll.remove(0);
		System.out.println(ll.get(0)); //null
		System.out.println(ll.getSize());
		
		ll.add("stuff1");
		ll.add("stuff2");
		ll.add("stuff3");
		ll.add("stuff4");
		ll.add("stuff5");

		System.out.println(ll.get(1)); //stuff2
		System.out.println(ll.getSize());
		System.out.println(ll.get(4)); //stuff5
		System.out.println(ll.getSize());

		ll.remove(4);
		System.out.println(ll.get(4)); //null
		System.out.println(ll.getSize());
		System.out.println(ll.get(3)); //stuff4
		System.out.println(ll.getSize());		
		ll.remove(0);
		System.out.println(ll.get(0)); //stuff2
		System.out.println(ll.getSize());
		ll.remove(1);
		System.out.println(ll.get(1)); //stuff4
		System.out.println(ll.getSize());
	}

	public int getSize() {
		return size;
	}

	public void setSize(int size) {
		this.size = size;
	}
	
}
