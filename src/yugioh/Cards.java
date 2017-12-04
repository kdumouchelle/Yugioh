//Kyle Dumouchelle
package yugioh;
import java.util.*;
public class Cards 
{
    ArrayList<Card> deck, hand, graveyard, banish, mons, st, extra = null;
    int turns = 0;
    public Cards()
    {
        deck = new ArrayList();
        hand = new ArrayList();
        graveyard = new ArrayList();
        banish = new ArrayList();
        mons = new ArrayList();
        st = new ArrayList();
        extra = new ArrayList();
    }
    public void shuffle()
    {
        long seed = System.nanoTime();
        Collections.shuffle(deck, new Random(seed));
    }
    
    public void draw(int num)
    {
        for(int k = 0; k < num; k++)
        {
            hand.add(deck.get(0));
            deck.remove(0);
        }
    }
    
    public void mill(int num)
    {
        for(int k = 0; k < num; k++)
        {
            graveyard.add(deck.get(0));
            deck.remove(0);
        }
    }
    
    public void moveTop(int num, Scanner input)
    {
        ArrayList<Card> location;
        System.out.println("Move cards to where");
        System.out.print("(hand/graveyard/banish/end): ");
        switch(input.next().toLowerCase())
        {
            case "hand":
                location = hand; break;
            case "graveyard":
                location = graveyard; break;
            case "banish":
                location = banish; break;
            case "end":
                System.out.println("Command Ended"); break;
            default:
                System.out.println("Illegal Location"); break;
        }
        for(int k = 0; k < num; k++)
        {
            banish.add(deck.get(0));
            deck.remove(0);
        }
    }
    
    public void external(Scanner input)
    {
        System.out.print("Summon from the Extra Deck/token (extra/token/remove(token)): ");
        switch(input.next().toLowerCase())
        {
            case "token":
                System.out.print("Name: "); input.nextLine();
                mons.add(new Card(input.nextLine())); break;
            case "remove":
                System.out.print("Name: "); input.nextLine();
              remove(mons, input.nextLine()); break;
            case "end":
                System.out.println("Command ended"); break;
            default:
                System.out.println("Illegal Operation"); break;
        }
    }
    
    public void printCards(ArrayList<Card> location)
    {
        System.out.println();
        for(Card card : location)
            System.out.println(card);
        System.out.println("Number of cards:" + location.size());
        System.out.println();
    }
    
    public void print(Scanner input)
    {
        System.out.print("Print what \n(hand/deck/graveyard/banish/mons/ST/extra): ");
        
         switch(input.next().toLowerCase())
        {
            case "hand":
                printCards(hand); break;
            case "deck":
                printCards(deck); break;
            case "banish":
                printCards(banish); break;
            case "graveyard":
                printCards(graveyard); break;
            case "mons":
                printCards(mons); break;
            case "st":
                printCards(st); break;
            case "extra":
                printCards(extra); break;
            case "end":
                System.out.println("Command ended"); break;
            default:
                System.out.println("Illegal Location"); break;
        }
    }
    
    public boolean interact(String command, Scanner input)
    {
        switch(command.toLowerCase())
        {
            case "move":
                moveCards(input); break;
            case "shuffle":
                shuffle(); break;
            case "end":
                return false;
            case "print":
                print(input); break;
            default:
                System.out.println("Illegal Command"); break;
        }
        return true;
    }
    
    public void moveCards(Scanner input)
    {
        System.out.print("Move cards from where\n(hand/deck/banish/mons/ST/extra/draw/deckTop/end): ");
        switch(input.next().toLowerCase())
        {
            case "hand":
                moveFrom(hand, input); break;
            case "deck":
                moveFrom(deck, input); break;
            case "graveyard":
                moveFrom(graveyard, input); break;
            case "banish":
                moveFrom(banish, input); break;
            case "draw":
                System.out.print("How many: ");
                draw(input.nextInt()); 
                System.out.println(); break;
            case "deckTop":
                System.out.print("How many: ");
                moveTop(input.nextInt(), input);
                System.out.println(); break;
            case "mons":
                moveFrom(mons, input); break;
            case "extra":
                moveFrom(extra, input); break;
            case "st":
                moveFrom(st, input); break;
            case "end":
                System.out.println("Command Ended"); break;
            default:
                System.out.println("Illegal Location"); break;
        }
    }
    
    public void moveFrom(ArrayList<Card> from, Scanner input)    
    {
        System.out.println("Move cards to where");
        System.out.print("(hand/deck/graveyard/banish/mons/ST/extra/end): ");
        switch(input.next().toLowerCase())
        {
            case "hand":
                moveTo(from, hand, input); break;
            case "deck":
                moveTo(from, deck, input); break;
            case "graveyard":
                moveTo(from, graveyard, input); break;
            case "banish":
                moveTo(from, banish, input); break;
            case "mons":
                moveTo(from, mons, input); break;
            case "st":
                moveTo(from, st, input); break;
            case "extra":
                moveTo(from, extra, input); break;
            case "end":
                System.out.println("Command Ended"); break;
            default:
                System.out.println("Illegal Location"); break;
        }
    }
    
    public void moveTo(ArrayList<Card> from, ArrayList<Card> to, Scanner input)
    {
       input.nextLine();
       String card = null;
       while(true)
       {   
           if(from.isEmpty())
           {
               System.out.println("No cards to move");
               break;
           }
           printCards(from);
           System.out.print("What card do you move (card name/end): ");
           card = input.nextLine();
           
           if(card.toLowerCase().equals("end")) 
           {
               System.out.println("Command ended");
               break;
           }
           System.out.println();
           
           if(remove(from,card)) to.add(new Card(card));
       }
    }
    
    public boolean remove (ArrayList<Card> location, String removeCard)
    {
        for(Card card : location)
            if(card.toString().equals(removeCard))
            {
                location.remove(card);
                return true;
            }
        return false;
    }
}
