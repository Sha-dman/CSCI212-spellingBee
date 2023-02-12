import java.util.*;
import java.io.File;
import java.io.FileNotFoundException;
public class SpellingBee{
    public static void main(String[] args){//throws FileNotFoundException{
    
        // initialize and assign number of players
        Scanner inputInt = new Scanner(System.in);
        System.out.print("Enter number of contestants? ");
        int players = inputInt.nextInt();

        //initialize and fill list of names with loop
        ArrayList<String> names = new ArrayList<>();
        Scanner inputString = new Scanner(System.in);
        System.out.println("Enter name of contestants. ");
            for ( int i=0 ; i < players; i++){
                names.add(inputString.next()); 
        }
    
        //input wordlist file and fill into a list
        ArrayList<String> words = new ArrayList<String>();
        try (Scanner file = new Scanner(new File("wordlist.txt"))){
        while (file.hasNext()){
                words.add(file.nextLine());
            }
        }
        catch(FileNotFoundException t){
            System.out.println("File not found!");
        }

        //Spelling bee logic

        // make new list with yes and no choices
        ArrayList<String> yN = new ArrayList<String>();
        int round = 0;
        
        while ( names.size() != 1 ){
            round++;
        System.out.println("Round " + round + "\n");
            int countN = 0;
           
            // loop to store whether spelling is correct for players
            for ( int i = 0 ; i < names.size(); i++){
                // get random word from list with .get and random function
                int randomNum =(int)(Math.random() * words.size() - 1 )+1;
                String randomWord = words.get(randomNum);
                
                System.out.println("The word for " + names.get(i) + " is: " + randomWord );
                System.out.print("Answered correctly? (y/n): ");
                yN.add(inputString.next());
                if ( yN.get(i).equals("n")) countN++;
                System.out.println();
                // removes the word so it doesnt repeat
                words.remove(randomNum);
                        
            }
            // loop to see if all player put no or remove players that put no
            for ( int i = 0 ; i < yN.size()  ; i++){
                // if all players are incorrect continue to next round
                if ( countN == yN.size()) break;
                // if not remove players that got incorrect
                else if ( yN.get(i).equals("n") ){
                    names.remove(i);
                }
            }
            // clear list of yes/no choices for next round
            yN.clear();
        }
        System.out.println(names.get(0) + " wins!");
        inputInt.close();
        inputString.close();

    }
}

