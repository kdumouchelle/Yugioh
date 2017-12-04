//Kyle Dumouchelle
package yugioh;
import java.util.*;
import java.io.*;

public class Card 
{
    private String name;
    private String type; //monster, spell, or trap    
    private String monVar; //normal, effect, ritual, fusion, synchro, xyz
    private boolean penVar; //is pendulum
    private String monType; //beast, dragon, sea serpent, etc.
    private String monAtt; //FIRE, WATER, EARTH, DARK, LIGHT, WIND, DIVINE
    private String stType; //normal, field, continuous, quick-play, counter
    ArrayList<Card> xyzMaterials = null; //will remain null unless addMaterials is used
    
    public Card(String cardName)
    {
        name = cardName;
    }
    
    public void addMaterials(Card material)
    {
        xyzMaterials.add(material);
    }
    public void detach(Card material)
    {
        xyzMaterials.remove(material);
    }
    
    //setter methods
    public void setType(String cardType)
    {
        if(cardType.equals("monster") || cardType.equals("spell") || cardType.equals("trap"))
            type = cardType;            
    }
    
    //getter methods
    public String getType()
    {
        if(type.equals("spell") || type.equals("trap"))
            return stType;
        if(type.equals("monster"))
            return monType;
        return "no type specified";
    }
    
    @Override public String toString()
    {
        return name;
    }
    public boolean equals(Card card)
    {
        if (this.name.equals(card.toString()))
            return true;
        return false;
    }
}
