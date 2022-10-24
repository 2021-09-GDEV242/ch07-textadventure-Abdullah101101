/**
 *  This class is the main class of the "World of Zuul" application. 
 *  "World of Zuul" is a very simple, text based adventure game.  Users 
 *  can walk around some scenery. That's all. It should really be extended 
 *  to make it more interesting!
 * 
 *  To play this game, create an instance of this class and call the "play"
 *  method.
 * 
 *  This main class creates and initialises all the others: it creates all
 *  rooms, creates the parser and starts the game.  It also evaluates and
 *  executes the commands that the parser returns.
 * 
 * @author  Michael Kölling and David J. Barnes
 * @version 2016.02.29
 */

public class Game 
{
    private Parser parser;
    private Room currentRoom;
        
    /**
     * Create the game and initialise its internal map.
     */
    public Game() 
    {
        createRooms();
        parser = new Parser();
    }

    /**
     * Create all the rooms and link their exits together.
     */
    private void createRooms()
    {
        Room outside, theater, pub, lab, office;
      
        // create the rooms
        outside = new Room("outside the main entrance of the university");
        theater = new Room("in a lecture theater");
        pub = new Room("in the campus pub");
        lab = new Room("in a computing lab");
        office = new Room("in the computing admin office");
        //create items
        
        Item barBell = new Item("Bar bell",50,"  A normal barbell, that belongs in the gym");
        Item VGA = new Item("VGA cable",1, "VGA display cable");
        Item server = new Item("Server",10 ," These is a server that is in the server room");
        Item wallet = new Item("Wallet",1 ," This wallet was found in security");
        Item alienWare = new Item("Alienware gaming pc",15 ," this is a normal gaming pc found in the gaming room");
        Item graphicsCard = new Item("RTX 4090 graphics card",3 ,"Wow this thing is massive. Found in the IT helpdesk");
        Item goggles = new Item("Swimming goggle",1 ,"these are normal swimming goggles in the pool room");
        Item picture = new Item("Picture",5 ,"this looks like a picutre of the president. It belongs in the presidents room");
        Item telescope = new Item("telescope",25 ,"This is a telescope that belong in the planetarium");
        Item rock = new Item("Rock",4 ,"normal rock the belongs outside");
        Item tray = new Item("Food tray",3 ,"this looks like it belongs in the cafe");
        Item computer = new Item("Computer",10 ,"cmputer that is in the lab");
        Item ID = new Item("employee ID", 1," found in HR");
        Item oldPrinter = new Item("Old printer",30 ,"belongs in the adjunct office");
        Item monitor = new Item("Monitor",19 ," found in the hardware storage room");
        Item projector = new Item("Projector",20 ," found in the theator");
        Item cup = new Item("Cup",2 ,"This looks to be like a glass cup that belongs in the pub");
        
        // initialise room exits
        outside.setExit("east", theater);
        outside.setExit("south", lab);
        outside.setExit("west", pub);

        theater.setExit("west", outside);

        pub.setExit("east", outside);

        lab.setExit("north", outside);
        lab.setExit("east", office);

        office.setExit("west", lab);

        // assigning items to rooms
        outside.addItem(rock);
        IT.addItem(VGA);
        IT.addItem(graphicsCard);
        theater.addItem(projector);
        lab.addItem(computer);
        presidents.addItem(picture);
        cafeteria.addItem(tray);
        dataCenter.addItem(server);
        gym.addItem(barBell);
        security.addItem(wallet);
        pool.addItem(goggles);
        HR.addItem(ID);
        gaming.addItem(alienWare);
        planetarium.addItem(telescope);
        adjunct.addItem(oldPrinter);
        hardware.addItem(monitor);
        pub.addItem(cup);
    
        currentRoom = outside;  // start game outside
    }

    /**
     *  Main play routine.  Loops until end of play.
     */
    public void play() 
    {            
        printWelcome();

        // Enter the main command loop.  Here we repeatedly read commands and
        // execute them until the game is over.
                
        boolean finished = false;
        while (! finished) {
            Command command = parser.getCommand();
            finished = processCommand(command);
        }
        System.out.println("Thank you for playing.  Good bye.");
    }

    /**
     * Print out the opening message for the player.
     */
    private void printWelcome()
    {
        System.out.println();
        System.out.println("Welcome to the World of Zuul!");
        System.out.println("World of Zuul is a new, incredibly boring adventure game.");
        System.out.println("Type '" + CommandWord.HELP + "' if you need help.");
        System.out.println();
        System.out.println(currentRoom.getLongDescription());
    }

    /**
     * Given a command, process (that is: execute) the command.
     * @param command The command to be processed.
     * @return true If the command ends the game, false otherwise.
     */
    private boolean processCommand(Command command) 
    {
        boolean wantToQuit = false;

        CommandWord commandWord = command.getCommandWord();

        switch (commandWord) {
            case UNKNOWN:
                System.out.println("I don't know what you mean...");
                break;

            case HELP:
                printHelp();
                break;

            case GO:
                goRoom(command);
                break;

            case LOOK:
                look();
                break;
                
            case EAT:
                eat();
                break;
                
            case HELP:
                printHelp();
                break;
                
            case QUIT:
                wantToQuit = quit(command);
                break;
        }
        return wantToQuit;
    }

    // implementations of user commands:

    /** 
     * Try to go in one direction. If there is an exit, enter the new
     * room, otherwise print an error message.
     */
    private void goRoom(Command command) 
    {
        if(!command.hasSecondWord()) {
            // if there is no second word, we don't know where to go...
            System.out.println("Go where?");
            return;
        }

        String direction = command.getSecondWord();

        // Try to leave current room.
        Room nextRoom = currentRoom.getExit(direction);

        if (nextRoom == null) {
            System.out.println("There is no door!");
        }
        else {
            currentRoom = nextRoom;
            System.out.println(currentRoom.getLongDescription());
        }
    }
    
    /**
     * Print out some help information.
     * Here we print some stupid, cryptic message and a list of the 
     * command words.
     */
    private void printHelp() 
    {
        System.out.println("You are lost. You are alone. You wander");
        System.out.println("around at the university.");
        System.out.println();
        System.out.println("Your command words are:");
        parser.showCommands();
    }
    
    /**
     * This method gives information about the current room that the user
     * is in.
     */
    private void look(){
        System.out.println("You are " + currentRoom.getLongDescription() + "\n" + getHP()); 
    }
    
    /**
     * This allow the player to eat.
     * A user must eat inorder to move
     */
    private void eat(){
        if(hp >= 0 && hp <=9){
        hp++;
        System.out.println("Nice job, because you ate your hp increased by 1\n" + getHP());
        }
        else if (hp == 10){
            System.out.println("unable to eat when hp is full");
        }
    }   
    
    
    /** 
     * "Quit" was entered. Check the rest of the command to see
     * whether we really quit the game.
     * @return true, if this command quits the game, false otherwise.
     */
    private boolean quit(Command command) 
    {
        if(command.hasSecondWord()) {
            System.out.println("Quit what?");
            return false;
        }
        else {
            return true;  // signal that we want to quit
        }
    }
}
