//Kyle Dumouchelle
package yugioh;
import java.util.*;
import java.io.*;
public class Yugioh 
{
    
    public static void main(String[] args) 
    {
        final String deckPick = "Crystron";
  
        try
        {
            Scanner deckRead = new Scanner(new File("./decks/" + deckPick + ".txt"));
            Cards library = new Cards();
            while(deckRead.hasNext())
            {
                int copies = deckRead.nextInt();
                String card = deckRead.nextLine().substring(1); //cuts off leading whitespace
                for(int k = 0; k < copies; k++)
                {
                    Card addCard = new Card(card);
                    library.deck.add(addCard);
                }
            }
            
            library.shuffle();
            library.draw(5);
           
            Scanner command = new Scanner(System.in);
            while(true)
            {
             System.out.print("Insert a command <move/print/shuffle/end>: ");
             if(!library.interact(command.next(), command)) //returns false for End command
                 break;
            }
        }
        catch(FileNotFoundException fnfe)
        {
            System.out.println(fnfe.getMessage());
        }
    }
}
