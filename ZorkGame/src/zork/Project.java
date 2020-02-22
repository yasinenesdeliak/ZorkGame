package zork;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Scanner;

public class Project {
	public static Scanner input;
	static String[][] rooms = { { "a", "d" }, { "b", "c" } };
	static String currentLocation = rooms[0][0];
	static String[][] roomsExplanations = {
			{ "" + "There is a vase on the middle of floor and there is a door which is not locked but closed on the south side of the room.",
					"I closed the previous door which is on the south side of the room and I am in the fourth room. There is an eikon which needs \n"
					+ "to use two dogheads and a dogbody. If these are in your inventory, you can use it directly.\n" },
			{ "I closed the previous door which is on the north side of the room and I am in the second room. There is just a carpet which has a camber\n"
					+ " on the right side of it and there is a room which is not locked but closed on the east side of the room.\n",
					"I closed the previous door and I am in the third room. There is a box in the middle of room and there is a door which is not locked\n"
					+ " but closed on the north side of the room.\n" } };
	static ArrayList<String> inventory = new ArrayList<String>();
	static ArrayList<String> roomA = new ArrayList<String>(Arrays.asList(Objects.dogHead1, Objects.vase, Objects.door));
	static ArrayList<String> roomB = new ArrayList<String>(
			Arrays.asList(Objects.dogHead2, Objects.carpet, Objects.door));
	static ArrayList<String> roomC = new ArrayList<String>(Arrays.asList(Objects.dogBody, Objects.box, Objects.door));
	static ArrayList<String> roomD = new ArrayList<String>(Arrays.asList(Objects.door));
	static ArrayList<String> takable = new ArrayList<String>(
			Arrays.asList(Objects.dogBody, Objects.dogHead1, Objects.dogHead2, Objects.dogEikon));
	static boolean doorStatus = false;
	static boolean exitDoorStatus = false;

	public static void open(String object) {
		if (object.equals(Objects.box)) {
			System.out.println("There is a dog body in the box!");
		} else if (object.equals(Objects.door) && !doorStatus) {
			doorStatus = true;
			System.out.println("I opened the door!");
		} else
			System.out.println("I cannot open " + object);
	};

	public static void close(String object) {
		if (object.equals(Objects.box)) {
			System.out.println("I closed the box!");
		} else if (object.equals(Objects.door) && doorStatus) {
			doorStatus = false;
			System.out.println("I closed the door!");
		} else
			System.out.println("I cannot close " + object);
	};

	public static void take(String object) {
		if (currentLocation.equals(rooms[0][0]) && roomA.contains(object) && takable.contains(object)) {
			inventory.add(object);
			System.out.println("I took " + object);
			roomA.remove(object);
			Objects.ExamineVase = "This is just a empty vase.";
		} else if (currentLocation.equals(rooms[1][0]) && roomB.contains(object) && takable.contains(object)) {
			inventory.add(object);
			System.out.println("I took " + object);
			roomB.remove(object);
			Objects.ExamineCarpet = "This just a flat carpet.";
		} else if (currentLocation.equals(rooms[1][1]) && roomC.contains(object) && takable.contains(object)) {
			inventory.add(object);
			System.out.println("I took " + object);
			roomC.remove(object);
			Objects.ExamineBox = "This just a empty box.";
		} else if (currentLocation.equals(rooms[0][1]) && roomD.contains(object) && takable.contains(object)) {
			inventory.add(object);
			System.out.println("I took " + object);
			roomD.remove(object);
		} else
			System.out.println("I can't");
	};

	public static void move(String direction) {
		if (doorStatus) {
			boolean DoesMove = false;
			if (direction.equals("south") || direction.equals("s")){
				if (currentLocation.equals(rooms[0][0])) {
					currentLocation = rooms[1][0];
					System.out.print(roomsExplanations[1][0]);
					DoesMove = true;
				} else if (currentLocation.equals(rooms[0][1])) {
					currentLocation = rooms[1][1];
					System.out.print(roomsExplanations[1][1]);
					DoesMove = true;
				}
			} else if (direction.equals("east") || direction.equals("e")) {
				if (currentLocation.equals(rooms[1][0])) {
					currentLocation = rooms[1][1];
					System.out.print(roomsExplanations[1][1]);
					DoesMove = true;
				}
			}else if (direction.equals("north") || direction.equals("n")) {
				if (currentLocation.equals(rooms[1][0])) {
					currentLocation = rooms[0][0];
					System.out.print(roomsExplanations[0][0]);
					DoesMove = true;
				} else if (currentLocation.equals(rooms[1][1])) {
					currentLocation = rooms[0][1];
					System.out.print(roomsExplanations[0][1]);
					DoesMove = true;
					if (inventory.size() != 3) {
						System.out.print(
								"There is an exit door in this room and there is a hole on the this door. \n"
								+ "To make an item for the hole you should collect more items. Go back and find all!! \n");
					} else{
						System.out.println("In the room I combined my all items and "
								+ "I converted them to an eikon. Now I have an dog eikon.");
						inventory.add(Objects.dogEikon);
						inventory.remove(Objects.dogBody);
						inventory.remove(Objects.dogHead1);
						inventory.remove(Objects.dogHead2);
					} 
				}
				} else if (direction.equals("west") || direction.equals("w")){
				if (currentLocation.equals(rooms[1][1])) {
					currentLocation = rooms[1][0];
					System.out.print(roomsExplanations[1][0]);
					DoesMove = true;
				}
			}
			if (DoesMove){
				doorStatus = false;}
			else
				System.out.println("There is no room in that way!!");
			
		} else
			System.out.println("Can't go that way.");

	};

	public static void examine(String object) {
		if ((currentLocation.equals(rooms[0][0]) && roomA.contains(object))||inventory.contains(object)) {
			if (object.equals(Objects.dogHead1)) {
				System.out.println(Objects.ExamineDogHead1);
			} else if (object.equals(Objects.vase)) {
				System.out.println(Objects.ExamineVase);
			} else if (object.equals(Objects.door)) {
				System.out.println(Objects.ExamineDoor);
			}
		} else if ((currentLocation.equals(rooms[1][0]) && roomB.contains(object))||inventory.contains(object)) {
			if (object.equals(Objects.dogHead2)) {
				System.out.println(Objects.ExamineDogHead2);
			} else if (object.equals(Objects.carpet)) {
				System.out.println(Objects.ExamineCarpet);
			} else if (object.equals(Objects.door)) {
				System.out.println(Objects.ExamineDoor);
			}
		} else if ((currentLocation.equals(rooms[1][1]) && roomC.contains(object))||inventory.contains(object)) {
			if (object.equals(Objects.dogBody)) {
				System.out.println(Objects.ExamineDogBody);
			} else if (object.equals(Objects.box)) {
				System.out.println(Objects.ExamineBox);
			} else if (object.equals(Objects.door)) {
				System.out.println(Objects.ExamineDoor);
			}
		} else if ((currentLocation.equals(rooms[0][1]) && roomD.contains(object))||inventory.contains(object)) {
			if (object.equals(Objects.dogEikon)) {
				System.out.println(Objects.ExamineDogEikon);
			} else if (object.equals(Objects.door)) {
				System.out.println(Objects.ExamineExitDoor);
			}
		} else
			System.out.println("What? What object?");
	};

	public static void use(String object) {
		if (currentLocation.equals(rooms[0][1]) && object.equals(Objects.dogEikon)) {
			System.out.println("I opened the exit door!! Victory!!!");
			exitDoorStatus = true;

		} else
			System.out.println("I can't use it!");
	};

	public static void inventory() {
		System.out.println("I am carrying: ");
		System.out.println(inventory);
	};

	public static void main(String[] args) {
		input = new Scanner(System.in);
		System.out.println("You stucked in a building. The building has 4 rooms. Let's see can you escape from the building! You are in first room now!");
		System.out.println(roomsExplanations[0][0]);
		while (!exitDoorStatus) {
			String command = input.next().toLowerCase();
			if (command.equals("inventory")) {
				inventory();
			} else if (command.equals("move")) {
				String object = input.next();
				move(object);
			} else if (command.equals("open")) {
				String object = input.next();
				open(object);
			} else if (command.equals("close")) {
				String object = input.next();
				close(object);
			} else if (command.equals("use")) {
				String object = input.nextLine().replaceAll("\\s","");
				use(object);
			} else if (command.equals("examine")) {
				String object = input.nextLine().replaceAll("\\s","");
				examine(object);
			} else if (command.equals("take")) {
				String object = input.nextLine().replaceAll("\\s","");
				take(object);
			} else
				System.out.println("That's not a verb I recognise.");
		}
	}
}
